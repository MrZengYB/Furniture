package com.tlb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.dao.TTlbJsDao;
import com.tlb.entity.TTlbJs;

@Component
public class TTlbJsDaoImpl extends BaseDaoImpl<TTlbJs> implements TTlbJsDao {

	public List<TTlbJs> getAllActiveTTlbJs() {
		return this.find("from TTlbJs where sfxs = 1 order by cjsj asc");
	}

	public Pager<TTlbJs> getTTlbJses(PageParam page, String name) {
		return this.getForPager("from TTlbJs where mc like ? order by cjsj desc", page, "%" + name + "%");
	}

	public TTlbJs getTTlbJs(String jsid) {
		return this.get(TTlbJs.class, jsid);
	}

	public void saveTTlbJs(TTlbJs tSysJs) {
		this.saveOrUpdate(tSysJs);
	}

	public void deleteTTlbJs(TTlbJs tSysJs) {
		this.delete(tSysJs);
	}

	public TTlbJs getTTlbJsByName(String name) {
		return this.get("from TTlbJs where sfxs = 1 and mc = ? ", name);
	}

}
