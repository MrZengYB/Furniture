package com.tlb.dao;

import java.util.List;

import com.tlb.entity.TTlbJscd;


public interface TTlbJscdDao extends BaseDao<TTlbJscd> {
	
	/**
	 * 根据角色id获取列表.
	 */
	public List<TTlbJscd> getTTlbJscdsByJsid(String jsid);

	/**
	 * 保存角色菜单中间表.
	 */
	public void saveTTlbJscd(TTlbJscd tSysJsCd);
	
	/**
	 * 删除角色菜单中间表.
	 */
	public void deleteTTlbJscd(TTlbJscd tSysJsCd);
	
}
