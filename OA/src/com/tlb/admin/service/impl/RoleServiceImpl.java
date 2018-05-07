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
import com.tlb.dao.TTlbCdDao;
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
	private TTlbJsDao tTlbJsDao;
	
	@Resource
	private TTlbYhjsDao tTlbYhJsDao;
	
	@Resource
	private TTlbJscdDao tTlbJsCdDao;
	
	@Resource
	private TTlbCdDao tTlbCdDao;

	@Transactional(readOnly = true)
	public String getAllActiveRoles(String adminId) {
		List<TTlbJs> roles = this.tTlbJsDao.getAllActiveTTlbJs();
		Map<String, TTlbJs> maps = new HashMap<String, TTlbJs>();
		for (TTlbJs role : roles) {
			maps.put(role.getJsid(), role);
		}
		List<TTlbYhjs> adminRoles = tTlbYhJsDao.getTTlbYhjses(adminId);
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
		model.put("model", "/admin/role/role_list");
		return model;
	}

	@Transactional(readOnly = true)
	public String getRoles(PageParam page, String keyword) {
		Pager<TTlbJs> pager = this.tTlbJsDao.getTTlbJses(page, keyword);
		return JsonUtil.toStringFromObject(pager.putMapObject());
	}

	@Transactional(readOnly = true)
	public Map<String, Object> addRole() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("model", "/admin/role/role_input");
		return model;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> editRole(String jsid) {
		Map<String, Object> model = new HashMap<String, Object>();
		TTlbJs tTlbJs = this.tTlbJsDao.getTTlbJs(jsid);
		List<TTlbJscd> tTlbJsCds = this.tTlbJsCdDao.getTTlbJscdsByJsid(jsid);
		List<String> jscdids = new ArrayList<String>();
		for (TTlbJscd tTlbJsCd : tTlbJsCds) {
			jscdids.add(tTlbJsCd.getXtcdid());
		}
		model.put("data", tTlbJs);
		model.put("jscdids", jscdids.toString());
		model.put("model", "/admin/role/role_input");
		return model;
	}

	@Transactional
	public String saveRole(TTlbJs param, String[] menus) {
		TTlbJs role = null;
		if (NullUtils.isEmpty(param.getJsid())) {
			role = new TTlbJs();
		} else {
			role = this.tTlbJsDao.getTTlbJs(param.getJsid());
		}
		BeanUtils.copyProperties(param, role, new String[] { "jsid", "cjsj" });
		this.tTlbJsDao.saveTTlbJs(role);

		// 处理权限问题
		List<TTlbJscd> roleMenus = this.tTlbJsCdDao.getTTlbJscdsByJsid(role.getJsid());
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
					this.tTlbJsCdDao.saveTTlbJscd(newTTlbJscd);
				}
			}
		}
		for (TTlbJscd roleMenu : maps.values()) {
			this.tTlbJsCdDao.deleteTTlbJscd(roleMenu);
		}
		return JsonUtil.toRes("保存成功");
	}

	@Transactional
	public String deleteRole(String id) {
		TTlbJs role = this.tTlbJsDao.getTTlbJs(id);
		if (role != null) {
			// 清除角色菜单中间表里面的相关数据
			List<TTlbJscd> roleMenus = this.tTlbJsCdDao.getTTlbJscdsByJsid(role.getJsid());
			for (TTlbJscd roleMenu : roleMenus) {
				this.tTlbJsCdDao.deleteTTlbJscd(roleMenu);
			}
			// 清除用户角色中间表里的相关数据
			List<TTlbYhjs> admins = this.tTlbYhJsDao.getTTlbYhjssByJsid(role.getJsid());
			for (TTlbYhjs admin : admins) {
				this.tTlbYhJsDao.deleteTTlbYhjs(admin);
			}
			this.tTlbJsDao.deleteTTlbJs(role);
		}
		return JsonUtil.toRes("删除成功");
	}

	@Transactional(readOnly = true)
	public String getActivationMenus(String jsid) {
		List<Map<String, Object>> list = this.tTlbCdDao.getTTlbCds();
		List<Map<String, Object>> djcd = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : list) {
			if (map.get("sjcdid") == null || map.get("sjcdid") == "") { //是最顶层菜单
				List<Map<String, Object>> zcd = new ArrayList<Map<String,Object>>();
				for (Map<String, Object> map1 : list) {
					if (map != null && map1 != null && map1.get("sjcdid") != null) {
						if (map1.get("sjcdid").equals(map.get("cdid"))) {
							zcd.add(map1);
						}
					}
				}
				map.put("zcd", zcd);
				djcd.add(map);
			}
		}
		return JsonUtil.toString(list);
	}

	@Transactional(readOnly = true)
	public String toCheckMcExist(String jsid, String mc) {
		TTlbJs tTlbJs = this.tTlbJsDao.getTTlbJsByName(mc);
		if (tTlbJs == null) {
			return JsonUtil.toRes("true");
		} else if (tTlbJs.getJsid().equals(jsid)) {
			return JsonUtil.toRes("true");
		} else {
			return JsonUtil.toRes("false");
		}
	}

	@Transactional(readOnly = true)
	public String toCheckJsUsed(String jsid) {
		List<TTlbYhjs> list = this.tTlbYhJsDao.getTTlbYhjssByJsid(jsid);
		if (list.size() == 0) {
			return JsonUtil.toRes("true");
		} else {
			return JsonUtil.toRes("false");
		}
	}

}