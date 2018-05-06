package com.tlb.dao;

import java.util.List;

import com.tlb.entity.TTlbYhjs;


public interface TTlbYhjsDao extends BaseDao<TTlbYhjs> {
	
	/**
	 * 根据yhid获取用户角色中间表数据.
	 */
	public List<TTlbYhjs> getTTlbYhjses(String yhid);
	
	/**
	 * 根据yhid获取用户角色中间表数据.
	 */
	public TTlbYhjs getTTlbYhjs(String yhid);
	
	/**
	 * 保存用户角色中间表数据.
	 */
	public void saveTTlbYhjs(TTlbYhjs tSysYhJs);
	
	/**
	 * 删除用户角色中间表数据.
	 */
	public void deleteTTlbYhjs(TTlbYhjs tSysYhJs);
	
	/**
	 * 查询表所有数据.
	 */
	public List<TTlbYhjs> getTTlbYhjses();

	/**
	 * 根据jsid获取用户角色中间表数据.
	 */
	public List<TTlbYhjs> getTTlbYhjssByJsid(String jsid);
}
