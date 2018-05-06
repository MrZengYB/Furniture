package com.tlb.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.tlb.dao.TTlbYhDao;
import com.tlb.entity.TTlbYh;

@SuppressWarnings("deprecation")
public class UserSecurityServiceImpl {

	@Resource
	private TTlbYhDao tTlbYhDao;
	
	public TTlbYh getLoginClientInfo(String username) {
		TTlbYh tTlbYh = this.tTlbYhDao.getTTlbYh(username);
		Date nowDate = new Date();
		if (tTlbYh == null || (tTlbYh.getLoginFailureCount() >= 3) 
				||(tTlbYh.getSdrq() != null && tTlbYh.getSdrq().getTime() > nowDate.getTime())) {
			return null;
		}
//		权限类控制
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>(); 
		GrantedAuthority gayInit = new GrantedAuthorityImpl("ROLE_USER_LOGIN");
		authSet.add(gayInit);
		tTlbYh.setAuthorities(authSet);
		return tTlbYh;
	}
}
