package com.nendeu.usersys.usermgr.business.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

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
	 * @return Service实例
	 */
	public static UserService getInstance() {
		return userService;
	}
	
	/**
	 * 用户Service登录方法
	 * @param sid 用户登录名
	 * @param password 登录密码
	 * @return 用户dto
	 */
	public UserDto login(int sid,String password) {
		
		Connection conn = null;
		//用户信息
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

	/**
	 * 用户Service注册方法
	 * @param dto 用于存储用户信息
	 * @return 更新记录条目数
	 */
	public int register(UserDto dto) {
		Connection conn = null;
		int rs = 0;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			rs = userMgrDao.signUp(dto);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service注册异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return rs;
	}
	
	/**
	 * 用户Service管理员添加用户信息方法
	 * @param dto 用于存储用户信息
	 * @return 更新记录条目数
	 */
	public int registerSuper(UserDto dto) {
		Connection conn = null;
		int rs = 0;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			rs = userMgrDao.signUpSuper(dto);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service注册异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return rs;
	}

	/**
	 * 用户Service管理员删除用户信息方法
	 * @param sid 删除用户名
	 * @return 更新记录条目数
	 */
	public int deleteUser(int sid) {
		Connection conn = null;
		int rs = 0;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			rs = userMgrDao.deleteUserById(sid);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service注册异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return rs;
	}
	
	/**
	 * 用户Service普通用户更新信息方法
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	public UserDto updateNormal(UserDto dto) {
		UserDto newdto = null;
		Connection conn = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			newdto = userMgrDao.updateNormal(dto);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service更新异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return newdto;
	}

	/**
	 * 用户Service 修改密码方法
	 * @param sid 修改用户名
	 * @param password 修改密码
	 * @return 修改后密码
	 */
	public String updatePassword(int sid, String password) {
		Connection conn = null;
		String newpwd = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			newpwd = userMgrDao.updateSpwdById(sid,password);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service修改密码异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return newpwd;
	}

	/**
	 * 用户Service 根据用户名查询用户方法
	 * @param sid 用户名
	 * @return 用户信息
	 */
	public UserDto searchUserBySid(int sid) {
		Connection conn = null;
		UserDto dto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			dto = userMgrDao.searchBySid(sid);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户更新异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return dto;
	}

	/**
	 * 用户Service 管理员更新用户名信息方法
	 * @param dto 更新信息
	 * @return 更新条目数
	 */
	public int updateAdmin(UserDto dto) {
		int rs = 0;
		Connection conn = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//开启连接事务
			DBUtil.beginTransaction(conn);
			//调用用户dao进行登录操作
			rs = userMgrDao.updateAdmin(dto);
			//提交连接事务
			DBUtil.commit(conn);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"用户Service更新异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return rs;
	}

	/**
	 * 用户Service 管理员获取所有用户名信息方法
	 * @return 所有用户信息
	 */
	public List<UserDto> getAllUser() {
		Connection conn = null;
		List<UserDto> ldto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			ldto = userMgrDao.getAllUser();
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"查询用户信息异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return ldto;
	}

	/**
	 * 用户Service 管理员分页获取所以用户名信息方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getAllUser(int pageNo, int pageSize) {
		Connection conn = null;
		List<UserDto> ldto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			ldto = userMgrDao.getAllUser(pageNo,pageSize);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"查询用户信息异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return ldto;
	}

	/**
	 * 用户Service 管理员分页获取有效的用户名信息方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getValidUser(int pageNo, int pageSize) {
		Connection conn = null;
		List<UserDto> ldto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			ldto = userMgrDao.getValidUser(pageNo,pageSize);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"查询用户信息异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return ldto;
	}
	
	/**
	 * 用户Service分页模糊查询具体方法：传递查询字符串、页码和分页大小
	 * @param searchStr 查询字符串
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> fuzzSearchByName(String searchStr, int pageNo, int pageSize) {

		Connection conn = null;
		List<UserDto> ldto = null;
		try {
			//调用数据库工具类过去连接对象
			conn = DBUtil.getConnection();
			//调用Dao工厂类获得用户Dao对象
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn,EnumType.USER_DAO);
			//调用用户dao进行登录操作
			ldto = userMgrDao.fuzzSearchByName(searchStr,pageNo,pageSize);
		}catch(DaoException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException(Ca+"查询用户信息异常", e);
		}
		finally {
			DBUtil.closeConnection(conn);
		}
		return ldto;
	}
	
	public static void main(String[] args) {
		UserServiceImpl service = (UserServiceImpl) UserServiceImpl.getInstance();
//		UserDto dto =service.login(2016874203,"123456");
//		System.out.println(dto.getEmail());

		Date date = new Date();
		UserDto dto = new UserDto();
		dto.setSid(2016874214);
		dto.setSname("Natah");
		dto.setSpwd("123456");
		dto.setEmail("Natah@qq.com");
		dto.setBrithday(date);
		dto.setSupervalue(2);
		dto.setStatus(2);
		service.register(dto);
	}
}
