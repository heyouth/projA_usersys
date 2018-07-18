package com.nendeu.usersys.usermgr.business.controller;

import com.nendeu.usersys.usermgr.business.domain.UserDto;
import com.nendeu.usersys.usermgr.business.service.UserService;
import com.nendeu.usersys.usermgr.business.service.UserServiceImpl;

/**
 * 用户Controller类
 * @author Heyouth
 *
 */
public class UserController implements Controller {
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";
	//获取用户Service实例,用以业务处理
	UserService userMgrService = UserServiceImpl.getInstance();
	/**
	 * 
	 * @param sid 登录用户名
	 * @param password 登录密码
	 * @return 用户dto
	 */
	public UserDto doLogin(int sid,String password) {
		UserDto dto = null;
		try {
			//调用用户Service实例获取用户信息
			dto = userMgrService.login(sid,password);
		}catch(Exception e) {
			System.out.println(Ca+"用户登录异常\n"+e.getMessage());
		}
		return dto;
	}
	public static void main(String[] args) {
		UserController userMgrController = new UserController();
		UserDto dto = userMgrController.doLogin(2016874203,"123456");
		System.out.println(dto.getSupervalue());
	}
}
