package com.tlb.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlb.admin.service.RoleService;
import com.tlb.common.JsonUtil;
import com.tlb.common.NullUtils;
import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.dao.TTlbJsDao;
import com.tlb.dao.TTlbJscdDao;
import com.tlb.dao.TTlbYhjsDao;
import com.tlb.entity.TTlbJs;
import com.tlb.entity.TTlbJscd;
import com.tlb.entity.TTlbYhjs;

@Component
@SuppressWarnings("unchecked")
public class RoleServiceImpl implements RoleService {

	@Resource
	private TTlbJsDao tSysJsDao;
	@Resource
	private TTlbYhjsDao tSysYhJsDao;
	@Resource
	private TTlbJscdDao tSysJsCdDao;

	@Transactional(readOnly = true)
	public String getAllActiveRoles(String adminId) {
		List<TTlbJs> roles = this.tSysJsDao.getAllActiveTTlbJs();
		Map<String, TTlbJs> maps = new HashMap<String, TTlbJs>();
		for (TTlbJs role : roles) {
			maps.put(role.getJsid(), role);
		}
		List<TTlbYhjs> adminRoles = tSysYhJsDao.getTTlbYhjses(adminId);
		for (TTlbYhjs adminRole : adminRoles) {
			if (maps.containsKey(adminRole.getJsid())) {
				// maps.get(adminRole.getJsid()).setChecked(true);
			}
		}
		return JsonUtil.toString(maps.values());
	}

	@Transactional(readOnly = true)
	public Map<String, Object> roleList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("model", "admin/role/role_list");
		return model;
	}

	@Transactional(readOnly = true)
	public String getRoles(PageParam page, String keyword) {
		Pager<TTlbJs> pager = this.tSysJsDao.getTTlbJses(page, keyword);
		return JsonUtil.toStringFromObject(pager.putMapObject());
	}

	@Transactional(readOnly = true)
	public Map<String, Object> addRole() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("model", "admin/role/role_input");
		return model;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> editRole(String jsid) {
		Map<String, Object> model = new HashMap<String, Object>();
		TTlbJs tSysJs = this.tSysJsDao.getTTlbJs(jsid);
		List<TTlbJscd> tSysJsCds = this.tSysJsCdDao.getTTlbJscdsByJsid(jsid);
		List<String> jscdids = new ArrayList<String>();
		for (TTlbJscd tSysJsCd : tSysJsCds) {
			jscdids.add(tSysJsCd.getXtcdid());
		}
		model.put("data", tSysJs);
		model.put("jscdids", jscdids.toString());
		model.put("model", "admin/role/role_input");
		return model;
	}

	@Transactional
	public String saveRole(TTlbJs param, String[] menus) {
		TTlbJs role = null;
		if (NullUtils.isEmpty(param.getJsid())) {
			role = new TTlbJs();
		} else {
			role = this.tSysJsDao.getTTlbJs(param.getJsid());
		}
		BeanUtils.copyProperties(param, role, new String[] { "jsid", "cjsj" });
		this.tSysJsDao.saveTTlbJs(role);

		// 处理权限问题
		List<TTlbJscd> roleMenus = this.tSysJsCdDao.getTTlbJscdsByJsid(role.getJsid());
		Map<String, TTlbJscd> maps = new HashMap<String, TTlbJscd>();
		for (TTlbJscd roleMenu : roleMenus) {
			maps.put(roleMenu.getXtcdid(), roleMenu);
		}
		if (menus != null) {
			for (String menu : menus) {
				if (maps.containsKey(menu)) {
					maps.remove(menu);
				} else {
					TTlbJscd newTTlbJscd = new TTlbJscd();
					newTTlbJscd.setXtcdid(menu);
					newTTlbJscd.setJsid(role.getJsid());
					this.tSysJsCdDao.saveTTlbJscd(newTTlbJscd);
				}
			}
		}
		for (TTlbJscd roleMenu : maps.values()) {
			this.tSysJsCdDao.deleteTTlbJscd(roleMenu);
		}
		return JsonUtil.toRes("保存成功");
	}

	@Transactional
	public String deleteRole(String id) {
		TTlbJs role = this.tSysJsDao.getTTlbJs(id);
		if (role != null) {
			// 清除角色菜单中间表里面的相关数据
			List<TTlbJscd> roleMenus = this.tSysJsCdDao.getTTlbJscdsByJsid(role.getJsid());
			for (TTlbJscd roleMenu : roleMenus) {
				this.tSysJsCdDao.deleteTTlbJscd(roleMenu);
			}
			// 清除用户角色中间表里的相关数据
			List<TTlbYhjs> admins = this.tSysYhJsDao.getTTlbYhjssByJsid(role.getJsid());
			for (TTlbYhjs admin : admins) {
				this.tSysYhJsDao.deleteTTlbYhjs(admin);
			}
			this.tSysJsDao.deleteTTlbJs(role);
		}
		return JsonUtil.toRes("删除成功");
	}

	@Transactional(readOnly = true)
	public String getActivationMenus(String jsid) {
		return jsid;
//		List<TSysXtcdsslx> menus = this.tSysXtcdsslxDao.getXtcdsslxs(ElementConst.Sys_Yhlx_Ptzh1);
//		List<TSysXtcd> menusSysXtcds = new ArrayList<TSysXtcd>();
//		Map<String, Object> maps = new HashMap<String, Object>();
//		for (TSysXtcdsslx tSysXtcdsslx : menus) {
//			getMaps(maps, tSysXtcdsslx.getXtcdid());
//		}
//		for (Object map : maps.values()) {
//			menusSysXtcds.add((TSysXtcd) map);
//		}
//		return JsonUtil.toString(menusSysXtcds);
	}

//	public void getMaps(Map<String, Object> maps, String xtcdid) {
//		TSysXtcd tSysXtcd = this.tSysXtcdDao.getXtcds(xtcdid);
//		if (tSysXtcd == null) {
//			return;
//		} else {
//			if (!maps.containsKey(tSysXtcd.getXtcdid())) {
//				maps.put(tSysXtcd.getXtcdid(), tSysXtcd);
//				if (!maps.containsKey(tSysXtcd.getSjxtcdid()) && NullUtils.isNotEmpty(tSysXtcd.getSjxtcdid())
//						&& tSysXtcd.getSjxtcdid() != tSysXtcd.getXtcdid()) {
//					getMaps(maps, tSysXtcd.getSjxtcdid());
//				}
//			}
//		}
//	}

	@Transactional(readOnly = true)
	public String toCheckMcExist(String jsid, String mc) {
		TTlbJs tSysJs = this.tSysJsDao.getTTlbJsByName(mc);
		if (tSysJs == null) {
			return JsonUtil.toRes("true");
		} else if (tSysJs.getJsid().equals(jsid)) {
			return JsonUtil.toRes("true");
		} else {
			return JsonUtil.toRes("false");
		}
	}

	@Transactional(readOnly = true)
	public String toCheckJsUsed(String jsid) {
		List<TTlbYhjs> list = this.tSysYhJsDao.getTTlbYhjssByJsid(jsid);
		if (list.size() == 0) {
			return JsonUtil.toRes("true");
		} else {
			return JsonUtil.toRes("false");
		}
	}

}