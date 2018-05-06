package com.tlb.admin.service;

import java.util.Map;

import com.tlb.common.PageParam;
import com.tlb.entity.TTlbJs;


public interface RoleService {
	
	/**
	 * 获取有效角色数据.
	 * @param adminId 用户id
	 * @return 返回数据
	 */
	public String getAllActiveRoles(String adminId);
	
	/**
	 * 角色列表页面索引.
	 * @return 返回页面
	 */
	public Map<String, Object> roleList();

	/**
	 * 根据关键字模糊匹配角色分页数据.
	 * @param page 分页参数
	 * @param keyword 关键字
	 * @return 返回数据
	 */
	public String getRoles(PageParam page, String keyword);

	/**
	 * 添加角色页面索引.
	 * @return
	 */
	public Map<String, Object> addRole();

	/**
	 * 编辑角色页面索引.
	 * @param jsid 角色ID
	 * @return 页面+数据
	 */
	public Map<String, Object> editRole(String jsid);
	
	/**
	 * 保存角色数据.
	 * @param menus 菜单id数组
	 * @param param 实体
	 * @return 结果json串
	 */
	public String saveRole(TTlbJs param, String [] menus);

	/**
	 * 删除角色数据.
	 * @param jsid 角色id
	 * @return 结果json串
	 */
	public String deleteRole(String jsid);

	/**
	 * 获取用户所在经销商的菜单.
	 * @param jsid 角色ID
	 * @return 数据
	 */
	public String getActivationMenus(String jsid);

	/**
	 * 判断角色名称是否已存在.
	 * @param jsid 角色ID
	 * @param mc 名称
	 * @return 数据
	 */
	public String toCheckMcExist(String jsid, String mc);

	/**
	 * 判断角色是否正在被使用.
	 * @param jsid 角色ID
	 * @return 数据
	 */
	public String toCheckJsUsed(String jsid);
	
}
