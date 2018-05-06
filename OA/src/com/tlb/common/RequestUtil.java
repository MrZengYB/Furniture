package com.tlb.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * request工具类.
 */
public class RequestUtil {
	
	/**
	 * 获取当前登录用户.
	 */
	public static String getUsername() {
		if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) return "";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
	/**
	 * 获取当前登录用户已有的权限.
	 */
	public static List<String> getActionsByLoginUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> resLst = new ArrayList<String>();
		for (GrantedAuthority granted : userDetails.getAuthorities()) {
			resLst.add(granted.getAuthority());
		}
		return resLst;
	}
	
}
