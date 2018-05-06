package com.tlb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tlb.dao.TTlbJscdDao;
import com.tlb.entity.TTlbJscd;

@Component
public class TTlbJscdDaoImpl extends BaseDaoImpl<TTlbJscd> implements TTlbJscdDao {

	public List<TTlbJscd> getTTlbJscdsByJsid(String jsid) {
		return this.find("from TTlbJscd where jsid = ? ", jsid);
	}

	public void saveTTlbJscd(TTlbJscd tSysJsCd) {
		this.saveOrUpdate(tSysJsCd);
	}

	public void deleteTTlbJscd(TTlbJscd tSysJsCd) {
		this.delete(tSysJsCd);
	}

}
