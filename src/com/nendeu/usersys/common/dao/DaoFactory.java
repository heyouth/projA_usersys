package com.nendeu.usersys.common.dao;

import java.sql.Connection;

import com.nendeu.usersys.common.exception.ServiceException;
import com.nendeu.usersys.usermgr.business.dao.UserDao;
import com.nendeu.usersys.usermgr.business.dao.UserDaoImpl;
/**
 * Dao工厂类(工厂方法模式)
 * @author Heyouth
 *
 */
public class DaoFactory {
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";
	
	/**
	 * 获取Dao方法:根据type类型返回指定的Dao实例
	 * @param conn 连接对象
	 * @param type 请求Dao的类型
	 * @return Dao实例
	 */
	public static BaseDao getDao(Connection conn,String type) {
		//判断dao请求类型
		if("user".equals(type)) {
			UserDao dao = new UserDaoImpl(conn);
			return dao;
		}
		else if("order".equals(type)) {
			System.out.println(Ca+"模拟输出order的dao实例");
		}
		else {
			throw new ServiceException(Ca+"找不到对应的工厂方法类!");
		}
		return null;
	}
}
