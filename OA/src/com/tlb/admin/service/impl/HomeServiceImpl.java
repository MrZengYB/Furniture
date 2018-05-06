package com.tlb.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlb.common.JsonUtil;
import com.tlb.common.RequestUtil;
import com.tlb.dao.TTlbCdDao;
import com.tlb.dao.TTlbYhDao;
import com.tlb.entity.TTlbYh;
import com.tlb.admin.service.HomeService;

@Component
public class HomeServiceImpl implements HomeService {

	@Resource
	private TTlbYhDao tTlbYhDao;

	@Resource
	private TTlbCdDao tTlbCdDao;
	
	@Transactional
	public Map<String, Object> getLoginPath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("model", "WEB-INF/views/login.jsp");
		model.put("state", true);
		return model;
	}

	@Transactional( readOnly = true)
	public String getTTlbYh() {
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYh(RequestUtil.getUsername());
		return JsonUtil.toStringFromObject(tTlbYh);
	}

	@Transactional( readOnly = true)
	public String getMenus() {
//		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYh(RequestUtil.getUsername());
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
//		Map<TTlbCd, List<TTlbCd>> map = new HashMap<TTlbCd, List<TTlbCd>>();
		return JsonUtil.toString(djcd);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> getHomePagePath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("model", "/admin/homePage");
		return model;
	}

}
