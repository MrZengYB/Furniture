package com.tlb.dao;

import java.util.List;
import java.util.Map;

import com.tlb.entity.TTlbCd;

public interface TTlbCdDao extends BaseDao<TTlbCd>{

	public List<Map<String, Object>> getTTlbCds();

}
