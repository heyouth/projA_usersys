package com.nendeu.usersys.usermgr.business.dao;

import java.util.Vector;

import com.nendeu.usersys.common.dao.BaseDao;
import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 用户Dao接口
 * 所以用户Dao实例都继承自该接口
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao {
	/**
	 * 用户登录方法
	 * @param sid 登录用户名
	 * @param password 登录密码
	 * @return 用户信息
	 */
	public UserDto login(int sid,String password);

	
	
}
