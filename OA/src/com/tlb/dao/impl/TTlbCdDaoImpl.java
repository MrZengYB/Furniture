package com.tlb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tlb.dao.TTlbCdDao;
import com.tlb.entity.TTlbCd;

@Component
public class TTlbCdDaoImpl extends BaseDaoImpl<TTlbCd> implements TTlbCdDao{

	public List<Map<String, Object>> getTTlbCds() {
		return this.getForMapBySql("select * from t_tlb_cd where sfxs = 1 and sfsc = 0 ");
	}

}
