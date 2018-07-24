package com.nendeu.usersys.usermgr.business.controller;

import java.util.List;

import com.nendeu.usersys.usermgr.business.domain.UserDto;
import com.nendeu.usersys.usermgr.business.service.UserService;
import com.nendeu.usersys.usermgr.business.service.UserServiceImpl;

/**
 * 用户Controller类
 * @author Heyouth
 *
 */
public class UserController{
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";
	//获取用户Service实例,用以业务处理
	UserService userMgrService = UserServiceImpl.getInstance();
	/**
	 * 用户Controller登录方法:传递用户名以及密码
	 * @param sid 登录用户名
	 * @param password 登录密码
	 * @return 用户Dto实例
	 */
	public UserDto doLogin(int sid,String password) {
		UserDto dto = null;
		try {
			//调用用户Service实例获取用户信息
			dto = userMgrService.login(sid,password);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller登录异常\n"+e.getMessage());
		}
		return dto;
	}
	/**
	 * 用户Controller注册方法：传递注册用户信息
	 * @param dto 注册用户信息
	 * @return 更新记录条目数
	 */
	public int register(UserDto dto) {
		int rs = 0;
		try {
			//调用用户Service实例传递添加的用户信息
			rs = userMgrService.register(dto);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller注册异常\n"+e.getMessage());
		}
		return rs;
	} 

	/**
	 * 用户Controller管理员添加用户方法：传递添加用户信息
	 * @param dto 添加用户信息
	 * @return 更新记录条目数
	 */
	public int registerSuper(UserDto dto) {
		int rs = 0;
		try {
			//调用用户Service实例传递添加的用户信息
			rs = userMgrService.registerSuper(dto);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller添加异常\n"+e.getMessage());
		}
		return rs;
	}
	
	/**
	 * 用户Controller管理员删除用户方法：传递删除用户名
	 * @param sid 删除用户的用户名
	 * @return 更新记录条目数
	 */
	public int deleteUser(int sid) {
		int rs = 0;
		try {
			//调用用户Service实例传递需删除的用户名
			rs = userMgrService.deleteUser(sid);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller删除异常\n"+e.getMessage());
		}
		return rs;
	}

	/**
	 * 用户Controller用户更新信息方法：传递用户更新信息
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	public UserDto updateNormal(UserDto dto) {
		UserDto newdto = null;
		try {
			//调用用户Service实例更新用户信息
			newdto= userMgrService.updateNormal(dto);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller更新异常\n"+e.getMessage());
		}
		return newdto;
	}

	/**
	 * 用户Controller更新密码方法：传递用户名以及密码
	 * @param sid 用户名
	 * @param password 新密码 
	 * @return 旧密码
	 */
	public String updatePwd(int sid,String password) {
		String newpwd = null;
		try {
			//调用用户Service实例更新用户信息
			newpwd= userMgrService.updatePassword(sid,password);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller更新异常\n"+e.getMessage());
		}
		return newpwd;
	}
	
	/**
	 * 用户Controller根据用户名查找用户信息方法：传递用户名
	 * @param sid 用户名
	 * @return 更新后信息
	 */
	public UserDto searchUserBySid(int sid) {
		UserDto dto = null;
		try {
			//调用用户Service实例获取用户信息
			dto = userMgrService.searchUserBySid(sid);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller查询异常\n"+e.getMessage());
		}
		return dto;
	}
	
	/**
	 * 用户Controller管理员更新方法：传递更新用户信息
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	public int updateAdmin(UserDto dto) {
		int rs = 0;
		try {
			//调用用户Service实例更新用户信息
			rs = userMgrService.updateAdmin(dto);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller更新异常\n"+e.getMessage());
		}
		return rs;
	}
	
	/**
	 * 用户Controller获取所有用户信息方法
	 * @return 用户信息集合
	 */
	public List<UserDto> getAllUser() {
		List<UserDto> ldto = null;
		try {
			//调用用户Service实例更新用户信息
			ldto = userMgrService.getAllUser();
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller更新异常\n"+e.getMessage());
		}
		return ldto;
	}
	
	/**
	 * 用户Controller分页获取所有用户信息方法：传递页码和分页大小
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getAllUser(int pageNo,int pageSize) {
		List<UserDto> ldto = null;
		try {
			//调用用户Service实例更新用户信息
			ldto = userMgrService.getAllUser(pageNo,pageSize);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller查询异常\n"+e.getMessage());
		}
		return ldto;
	}

	/**
	 * 用户Controller分页获取有效用户信息方法：传递页码和分页大小
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getValidUser(int pageNo, int pageSize) {
		List<UserDto> ldto = null;
		try {
			//调用用户Service实例更新用户信息
			ldto = userMgrService.getValidUser(pageNo,pageSize);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller查询异常\n"+e.getMessage());
		}
		return ldto;
	}
	
	/**
	 * 
	 * 用户Controller用户名分页模糊查询方法：传递查询字符串、页码和分页大小
	 * @param searchStr 查询字符串
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> fuzzSearchByName(String searchStr, int pageNo, int pageSize) {
		List<UserDto> ldto = null;
		try {
			//调用用户Service实例更新用户信息
			ldto = userMgrService.fuzzSearchByName(searchStr,pageNo,pageSize);
		}catch(Exception e) {
			System.out.println(Ca+"用户Controller查询异常\n"+e.getMessage());
		}
		return ldto;
	}
	
	public static void main(String[] args) {
		UserController userMgrController = new UserController();
		UserDto dto = userMgrController.doLogin(2016874203,"123456");
		System.out.println(dto.getSupervalue());
	}
}
