package com.nendeu.usersys.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nendeu.usersys.common.exception.DaoException;
/**
 * 数据库工具类-数据库连接
 * @author Heyouth
 *
 */
public class DBUtil {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "scott";
	private static final String PASSWORD = "123456";
	/**
	 * 连接数据库并返回数据库连接对象
	 * @return 返回数据库的连接对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");									//加载服务器驱动
			conn = DriverManager.getConnection(URL,USER,PASSWORD);						//获取数据库对象
		}catch (SQLException e) {
			//抛出自定义异常
			throw new DaoException("数据库连接驱动加载异常", e);
		}catch(ClassNotFoundException e) {
			//抛出自定义异常
			throw new DaoException("数据库连接驱动加载异常", e);
		}
		return conn;																	//返回连接对象
	}
	public static void closeConnection(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("ERROR:数据库关闭异常！");
			
		}
	}
	public static void closeStatement(Statement stmt) {
		try {
			if(stmt!=null) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("ERROR:会话关闭异常!");
			e.printStackTrace();
		}
	}
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(SQLException e) {
			System.out.println("ERROR:结果集关闭异常!");
			e.printStackTrace();
		}
	}
	public static void beginTransaction(Connection conn) {
		try {
			//事务自动提交模式转换为false
			conn.setAutoCommit(false);										
		}catch(SQLException e) {
			System.out.println("ERROR:开启事务异常！");
			e.printStackTrace();
		}	
	}
	public static void commit(Connection conn) {
		try {
			//提交事务
			conn.commit();
			//将自动提交模式设定为true
			conn.setAutoCommit(true);
		}catch(SQLException e) {
			System.out.println("ERROR:提交事务异常！");
		}
	}
	public static void rollbrack(Connection conn) {
		try {
			//回滚事务
			conn.rollback();
			//将自动提交模式设定为true
			conn.setAutoCommit(true);
		}catch(SQLException e) {
			System.out.println("ERROR:回滚事务异常！");
		}
	}
	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		DBUtil.closeConnection(conn);
	}

}
