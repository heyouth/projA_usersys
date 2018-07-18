package com.nendeu.usersys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.nendeu.usersys.common.exception.DaoException;
import com.nendeu.usersys.common.util.DBUtil;
import com.nendeu.usersys.common.util.TypeUtil;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

/**
 * 用户Dao实现类
 * @author Heyouth
 *
 */
public class UserDaoImpl implements UserDao {
	//测试用指示常量
	public static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";
	//连接对象
	private Connection conn;
	
	/**
	 * 构造方法-获取连接对象
	 * @param conn 连接对象
	 */
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * 用户登录
	 * @param sid 登录用户名
	 * @param password 登录密码
	 * @return 用户信息
	 */
	public UserDto login(int sid, String password) {
		//用户模型
		UserDto dto = null;
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			prestmt = conn.prepareStatement("select * from T_USER where SID = ? and SPWD = ? ");
			prestmt.setInt(1,sid);
			prestmt.setString(2,password);
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			if(rs.next()) {
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
			}
			else {
				System.out.println(Ca+"结果集为空");
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"用户登录查询失败", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return dto;
	}
	
	/**
	 * 
	 * @param dto 插入
	 * @return
	 */
	public int signUp(UserDto dto) {
		//数据流插入记录返回结果（更新条目数）
		int rs = 0;
		//预编译对象
		PreparedStatement prestmt = null;
		try {
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("insert into T_USER(ID,SID,SNAME,SPWD,EMAIL,BRITHDAY) "
			+"VALUES(NULL,?,?,?,?,?)");
			prestmt.setInt(1,dto.getSid());
			prestmt.setString(2,dto.getSname());
			prestmt.setString(3,dto.getSpwd());
			prestmt.setString(4,dto.getEmail());
			prestmt.setDate(5,new java.sql.Date(dto.getBrithday().getTime()));
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			rs = prestmt.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(Ca+"用户注册异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
		}
		return rs;
	}
	
	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		UserDaoImpl dao = new UserDaoImpl(conn);
//		UserDto dto = userdao.login(2016874202,"123456");
//		System.out.println(Ca+dto.getSupervalue());
		Date date = new Date();
		UserDto dto = new UserDto();
		dto.setSid(2016874212);
		dto.setSname("Natah");
		dto.setSpwd("123456");
		dto.setEmail("Natah@qq.com");
		dto.setBrithday(date);
		dto.setSupervalue(2);
		dto.setStatus(2);
		DBUtil.beginTransaction(conn);
		System.out.println(Ca+"更新条目数："+dao.signUp(dto));
		DBUtil.commit(conn);
		DBUtil.closeConnection(conn);
//		System.out.println(dto.getEmail());
	}
}
