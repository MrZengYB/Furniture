package com.tlb.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlb.admin.service.UserService;
import com.tlb.common.JsonUtil;
import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.dao.TTlbYhDao;
import com.tlb.entity.TTlbYh;

@Component
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService{

	@Resource
	private TTlbYhDao tTlbYhDao;
	
	@Transactional(readOnly = true)
	public Map<String, Object> toList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("url", "/admin/user/user_list");
		return model;
	}

	@Transactional(readOnly = true)
	public String getUserDatas(PageParam page, String name) {
		Pager<TTlbYh> pager = this.tTlbYhDao.getTTlbYhs(page, name);
		return JsonUtil.toStringFromObject(pager.putMapObject());
	}

	@Transactional(readOnly = true)
	public Map<String, Object> toAdd() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("url", "/admin/user/user_input");
		return model;
	}
	
	@Transactional(readOnly = true)
	public String checkUsernameExist(String username, String yhid) {
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYh(username);
		if (tTlbYh == null) {
			return JsonUtil.toResOfFail("用户已存在");
		} else if (tTlbYh.getYhid().equals(yhid)){
			return JsonUtil.toResOfFail("用户已存在");
		} else {
			return JsonUtil.toRes("用户不存在");
		}
	}

	@Transactional
	public String saveUser(TTlbYh param) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYh(param.getUsername());
		if (tTlbYh == null && (param.getYhid().equals("") || param.getYhid() == null)) { //新建用户
			param.setYhid(null);
			param.setYhsfsc(false);
			param.setIsAccountEnabled(true);
			param.setIsAccountExpired(false);
			param.setIsAccountLocked(false);
			param.setIsCredentialsExpired(false);
			param.setLoginFailureCount(0);
			param.setYhtx("/views/admin/homePage/img/user01.png");
			param.setPassword(encoder.encodePassword(param.getPassword(), param.getUsername()));
			this.tTlbYhDao.saveTTlbYh(param);
		} else { //修改用户
			BeanUtils.copyProperties(param, tTlbYh, new String[]{"yhid", "cjsj","yhsfsc",
					"IsAccountEnabled","isAccountExpired", "isAccountLocked", "isCredentialsExpired", "loginFailureCount"});
			tTlbYh.setPassword(encoder.encodePassword(param.getPassword(), param.getUsername()));
			tTlbYh.setIsAccountEnabled(true);
			this.tTlbYhDao.saveTTlbYh(tTlbYh);
		}
		return JsonUtil.toRes("保存成功");
	}

	@Transactional(readOnly = true)
	public Map<String, Object> toEdit(String yhid) {
		Map<String, Object> model = new HashMap<String, Object>();
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYhByYhid(yhid);
		model.put("data", tTlbYh);
		model.put("url", "/admin/user/user_input");
		return model;
	}

	@Transactional
	public String deleteUser(String yhid) {
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYhByYhid(yhid);
		tTlbYh.setYhsfsc(true);
		this.tTlbYhDao.saveTTlbYh(tTlbYh);
		return JsonUtil.toRes("删除成功");
	}

	@Transactional(readOnly = true)
	public String getUserList(String shid) {
		List<Map<String, Object>> list = this.tTlbYhDao.getUserList(shid);
		return JsonUtil.toString(list);
	}

}
