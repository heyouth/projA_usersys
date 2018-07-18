package com.nendeu.usersys.usermgr.business.service;

import com.nendeu.usersys.usermgr.business.domain.UserDto;

public interface UserService {

	/**
	 * 用户Service登录方法
	 * @param sid 用户登录名
	 * @param password 登录密码
	 * @return 用户dto
	 */
	UserDto login(int sid, String password);
	
	/**
	 * 用户Service注册
	 * @param dto 用于存储用户信息
	 * @return
	 */
	int register(UserDto dto);
}
