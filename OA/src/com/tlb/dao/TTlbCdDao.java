package com.tlb.dao;

import java.util.List;
import java.util.Map;

import com.tlb.entity.TTlbCd;

public interface TTlbCdDao extends BaseDao<TTlbCd>{

	/**
	 * 获取所有菜单.
	 */
	public List<Map<String, Object>> getTTlbCds();

	/**
	 * 获取指定cdid的菜单.
	 */
	public TTlbCd getTTlbCd(String cdid);

}
