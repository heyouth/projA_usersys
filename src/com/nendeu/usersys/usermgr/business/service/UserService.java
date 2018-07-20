package com.nendeu.usersys.usermgr.business.service;

import java.util.List;

import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 用户Service接口，所以的用户Service类都继承自该接口
 * @author Heyouth
 *
 */
public interface UserService {

	/**
	 * 用户Service登录方法
	 * @param sid 用户登录名
	 * @param password 登录密码
	 * @return 用户dto
	 */
	UserDto login(int sid, String password);
	
	/**
	 * 用户Service普通用户注册方法
	 * @param dto 用于存储用户信息
	 * @return 更新记录条目数
	 */
	int register(UserDto dto);
	

	/**
	 * 用户Service管理员添加用户信息方法
	 * @param dto 用于存储用户信息
	 * @return 更新记录条目数
	 */
	int registerSuper(UserDto dto);
	
	/**
	 * 用户Service管理员删除用户方法
	 * @param sid 用户名
	 * @return 更新记录条目数
	 */
	int deleteUser(int sid);
	
	/**
	 * 用户Service普通用户更新信息方法
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	UserDto updateNormal(UserDto dto);

	
	/**
	 * 用户Service 修改密码方法
	 * @param sid
	 * @param password
	 * @return
	 */
	String updatePassword(int sid, String password);

	/**
	 * 用户Service 根据用户名查询用户方法
	 * @param sid 用户名
	 * @return 用户信息
	 */
	UserDto searchUserBySid(int sid);

	/**
	 * 用户Service 管理员更新用户名信息方法
	 * @param dto 更新信息
	 * @return 更新条目数
	 */
	int updateAdmin(UserDto dto);

	/**
	 * 用户Service 管理员获取所有用户名信息方法
	 * @return 所有用户信息
	 */
	List<UserDto> getAllUser();
	

	/**
	 * 用户Service 管理员分页获取所以用户名信息方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	List<UserDto> getAllUser(int pageNo,int pageSize);
	
	/**
	 * 用户Service 管理员分页获取有效的用户名信息方法
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<UserDto> getValidUser(int pageNo, int pageSize);

	/**
	 * 用户Service分页模糊查询方法：传递查询字符串、页码和分页大小
	 * @param searchStr 查询字符串
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	List<UserDto> fuzzSearchByName(String searchStr, int pageNo, int pageSize);

}
