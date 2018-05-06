package com.tlb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tlb.dao.TTlbYhjsDao;
import com.tlb.entity.TTlbYhjs;

@Component
public class TTlbYhjsDaoImpl extends BaseDaoImpl<TTlbYhjs> implements TTlbYhjsDao {

	public List<TTlbYhjs> getTTlbYhjses(String yhid) {
		return this.find("from TTlbYhjs where yhid = ?", yhid);
	}

	public TTlbYhjs getTTlbYhjs(String yhid) {
		return this.get("from TTlbYhjs where yhid = ?", yhid);
	}
	
	public void saveTTlbYhjs(TTlbYhjs adminRole) {
		this.saveOrUpdate(adminRole);
	}

	public void deleteTTlbYhjs(TTlbYhjs adminRole) {
		this.delete(adminRole);
	}

	public List<TTlbYhjs> getTTlbYhjses() {
		return this.find("from TTlbYhjs");
	}

	public List<TTlbYhjs> getTTlbYhjssByJsid(String jsid) {
		return this.find("from TTlbYhjs where jsid = ?", jsid);
	}

}
