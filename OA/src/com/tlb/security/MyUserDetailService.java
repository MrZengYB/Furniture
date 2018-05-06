package com.tlb.security;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tlb.entity.TTlbYh;

public class MyUserDetailService implements UserDetailsService{

	@Resource
	private UserSecurityServiceImpl userSecurityServiceImpl;
	
	//登陆验证时，通过username获取用户的所有权限信息，  
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用  
    public UserDetails loadUserByUsername(String username)   
            throws UsernameNotFoundException, DataAccessException {
    	
    	TTlbYh user = this.userSecurityServiceImpl.getLoginClientInfo(username);
        return user;    
        }   
}
