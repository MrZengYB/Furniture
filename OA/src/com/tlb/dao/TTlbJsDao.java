package com.tlb.dao;

import java.util.List;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.entity.TTlbJs;


public interface TTlbJsDao extends BaseDao<TTlbJs> {
	
	/**
	 * 获取有已启用的角色.
	 */
	public List<TTlbJs> getAllActiveTTlbJs();
	
	/**
	 * 根据名称模糊匹配分页数据.
	 */
	public Pager<TTlbJs> getTTlbJses(PageParam page, String name);

	/**
	 * 根据主键jsid获取单条数据.
	 */
	public TTlbJs getTTlbJs(String jsid);

	/**
	 * 保存角色表数据.
	 */
	public void saveTTlbJs(TTlbJs tSysJs);

	/**
	 * 删除角色表数据.
	 */
	public void deleteTTlbJs(TTlbJs tSysJs);
	
	/**
	 * 根据名称获取到单条数据.
	 */
	public TTlbJs getTTlbJsByName(String name);
}
