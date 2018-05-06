package com.tlb.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;

@SuppressWarnings("rawtypes")
public interface BaseDao<T> {
	
	public void flush();
	
	public void save(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public void delete(T o);
	
	public int count(String hql, Object... param);
	
	public int countBySql(String sql, Object... param);
	
	public T get(Class c, Serializable id);

	public T get(String hql, Object... param);
	
	public List<T> find(String hql, Object... param);
	
	public List<T> find(String hql, int page, int rows, Object... param);
	
	public Pager<T> getForPager(String hql, PageParam page, Object... param);
	
	public List<Map<String, Object>> getForMapBySql(String sql, Object... param);
	
	public Pager<List<Map<String, Object>>> getForPagerBySql(String sql, PageParam page, Object... param);
	
}
