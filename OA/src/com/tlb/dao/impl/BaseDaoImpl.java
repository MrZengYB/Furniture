package com.tlb.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.dao.BaseDao;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void flush() {
		this.getCurrentSession().flush();
	}
	
	public void save(T o) {
		this.getCurrentSession().save(o);
		this.flush();
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
		this.flush();
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
		this.flush();
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
		this.flush();
	}

	public int count(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery("select count(*) " + hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return Integer.valueOf(q.uniqueResult().toString());
	}
	
	public int countBySql(String sql, Object... param) {
		if(sql.lastIndexOf("order by") != -1) sql = sql.substring(0, sql.lastIndexOf("order by"));
		SQLQuery query = this.getCurrentSession().createSQLQuery("select count(*) from (" + sql +") t");
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	public T get(Class c, Serializable id) {
		return (T)this.getCurrentSession().get(c, id);
	}
	
	public T get(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (T)q.uniqueResult();
	}

	public List<T> find(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}
	
	public List<T> find(String hql, int page, int rows, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	public Pager<T> getForPager(String hql, PageParam page, Object... param) {
		Pager<T> pager = new Pager<T>();
		List<T> list = this.find(hql, page.getPage(), page.getRows(), param);
		if(hql.indexOf("order by") != -1) hql = hql.substring(0, hql.indexOf("order by"));
		int count = this.count(hql, param);
		pager.setDatas(list);
		pager.setTotal(count);
		pager.setPageCount(count % page.getRows() == 0? count / page.getRows() : (count / page.getRows()) + 1);
		pager.setPageIndex(page.getPage());
		return pager;
	}
	
	public List<Map<String, Object>> getForMapBySql(String sql, Object... param) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		List lists = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		for(int i=0;i<lists.size();i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			for(String key : ((Map<String, Object>) lists.get(i)).keySet()) {
				Object obj = ((Map<String, Object>) lists.get(i)).get(key);
				map.put(key.toLowerCase(), obj);
			}
			listmap.add(map);
		}
		return listmap;
	}

	public Pager<List<Map<String, Object>>> getForPagerBySql(String sql, PageParam page, Object... param) {
		Pager pager = new Pager();
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		List lists = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows()).list();
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		for(int i=0;i<lists.size();i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			for(String key : ((Map<String, Object>) lists.get(i)).keySet()) {
				Object obj = ((Map<String, Object>) lists.get(i)).get(key);
				map.put(key.toLowerCase(), obj);
			}
			listmap.add(map);
		}
		int count = this.countBySql(sql, param);
		pager.setDatas(listmap);
		pager.setTotal(count);
		pager.setPageCount(count % page.getRows() == 0? count / page.getRows() : (count / page.getRows()) + 1);
		pager.setPageIndex(page.getPage());
		return pager;
	}
}
