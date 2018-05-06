package com.tlb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tlb.common.PageParam;
import com.tlb.common.Pager;
import com.tlb.dao.TTlbYhDao;
import com.tlb.entity.TTlbYh;

@Component
public class TTlbYhDaoImpl extends BaseDaoImpl<TTlbYh> implements TTlbYhDao{

	public TTlbYh getTTlbYh(String username) {
		TTlbYh tTlbYh = this.get("from TTlbYh where yhsfsc = false and yhmc = ? ", username);
		return tTlbYh;
	}

	public Pager<TTlbYh> getTTlbYhs(PageParam page, String name) {
		return this.getForPager("from TTlbYh where (zsxm like ? or username like ?) and yhsfsc = false order by cjsj desc", page, "%" + name + "%", "%" + name + "%");
	}

	public TTlbYh getTTlbYhByYhid(String yhid) {
		return this.get(TTlbYh.class, yhid);
	}

	public void saveTTlbYh(TTlbYh tTlbYh) {
		this.saveOrUpdate(tTlbYh);
	}

	public List<Map<String, Object>> getUserList(String shid) {
		return this.getForMapBySql("select a.*, b.shid  from t_tlb_yh a " +
				"left join t_tlb_sh b on (a.yhid = b.yhid and b.shid = ?) " +
				"where a.yhsfsc = 0 order by a.cjsj desc ", shid);
	}

}
