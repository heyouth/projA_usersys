package com.nendeu.usersys.usermgr.business.service;

import java.sql.Connection;

import com.nendeu.usersys.common.constant.EnumType;
import com.nendeu.usersys.common.dao.DaoFactory;
import com.nendeu.usersys.common.exception.DaoException;
import com.nendeu.usersys.common.exception.ServiceException;
import com.nendeu.usersys.common.util.DBUtil;
import com.nendeu.usersys.usermgr.business.dao.UserDao;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

/**
 * 用户Service类（单例模式）
 * @author Heyouth
 *
 */
public class UserServiceImpl implements UserService {
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";
	/**
	 * 用户Service类唯一实例(饿汉式)
	 */
	private static UserServiceImpl userService = new UserServiceImpl();
	
	/**
	 * 获取唯一的Service实例
	 * @return
	 */
	public static UserService getInstance() {
		return userService;
	}
	
	public UserDto login(int sid,String password) {
		Connection conn = null;
		UserDto dto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			dto = userMgrDao.login(sid, password);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户登录异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return dto;
	}
	public static void main(String[] args) {
		UserServiceImpl service = (UserServiceImpl) UserServiceImpl.getInstance();
		UserDto dto =service.login(2016874203,"123456");
		System.out.println(dto.getEmail());
	}
}
