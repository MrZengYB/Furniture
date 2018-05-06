package com.tlb.admin.service;

import java.util.Map;

import com.tlb.common.PageParam;
import com.tlb.entity.TTlbYh;

public interface UserService {

	/**
	 * 用户列表页索引.
	 * @return 页面
	 */
	public Map<String, Object> toList();

	/**
	 * 获取用户分页数据.
	 * @param page 分页参数
	 * @param name 关键字
	 * @return 数据
	 */
	public String getUserDatas(PageParam page, String name);

	/**
	 * 用户添加页索引.
	 * @return 页面
	 */
	public Map<String, Object> toAdd();
	
	/**
	 * 检查用户名是否已存在.
	 * @param username 用户名
	 * @param yhid 用户id
	 * @return 数据
	 */
	public String checkUsernameExist(String username, String yhid);

	/**
	 * 保存用户.
	 * @param param 用户实体类
	 * @return 数据
	 */
	public String saveUser(TTlbYh param);

	/**
	 * 用户修改页索引.
	 * @param yhid 用户id
	 * @return 页面  + 数据
	 */
	public Map<String, Object> toEdit(String yhid);

	/**
	 * 删除用户.
	 * @param yhid 用户id
	 * @return 数据
	 */
	public String deleteUser(String yhid);

	/**
	 * 获取用户列表.
	 * @param shid 商户id
	 * @return 数据
	 */
	public String getUserList(String shid);

}
