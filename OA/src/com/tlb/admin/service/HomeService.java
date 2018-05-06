package com.tlb.admin.service;

import java.util.Map;

public interface HomeService {

	/**
	 * 登录页索引.
	 * @return 页面
	 */
	public Map<String, Object> getLoginPath();

	/**
	 * 获取当前登录的用户.
	 * @return 数据
	 */
	public String getTTlbYh();

	/**
	 * 获取当前登录用户所拥有的菜单.
	 * @return 数据
	 */
	public String getMenus();

	/**
	 * 主页索引.
	 * @return 页面
	 */
	public Map<String, Object> getHomePagePath();

}
