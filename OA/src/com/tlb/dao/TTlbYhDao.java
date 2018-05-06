package com.tlb.dao;

import java.util.List;
import java.util.Map;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.entity.TTlbYh;

public interface TTlbYhDao extends BaseDao<TTlbYh>{

	/**
	 * 通过用户名来获取用户实体.
	 */
	public TTlbYh getTTlbYh(String username);

	/**
	 * 根据分页参数以及关键字获取用户数据.
	 */
	public Pager<TTlbYh> getTTlbYhs(PageParam page, String name);

	/**
	 * 通过用户id来获取用户实体.
	 */
	public TTlbYh getTTlbYhByYhid(String yhid);

	/**
	 * 保存用户.
	 */
	public void saveTTlbYh(TTlbYh tTlbYh);

	/**
	 * 获取所有的用户.
	 */
	public List<Map<String, Object>> getUserList(String shid);

}
