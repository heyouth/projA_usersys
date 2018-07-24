package com.nendeu.usersys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nendeu.usersys.common.exception.DaoException;
import com.nendeu.usersys.common.util.DBUtil;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

/**
 * 用户Dao具体实现类
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
	 * 用户dao登录具体实现方法
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
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
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
	 * 用户dao普通用户注册具体实现方法
	 * @param dto 增加的用户信息
	 * @return 更新记录的条目数
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
	
	/**
	 * 用户dao管理员添加具体实现方法
	 * @param dto 增加的用户信息
	 * @return 更新记录的条目数
	 */
	public int signUpSuper(UserDto dto) {
		//数据流插入记录返回结果（更新条目数）
		int rs = 0;
		//预编译对象
		PreparedStatement prestmt = null;
		try {
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("insert into T_USER(ID,SID,SNAME,SPWD,EMAIL,BRITHDAY,SUPERVALUE) "
			+"VALUES(NULL,?,?,?,?,?,?)");
			prestmt.setInt(1,dto.getSid());
			prestmt.setString(2,dto.getSname());
			prestmt.setString(3,dto.getSpwd());
			prestmt.setString(4,dto.getEmail());
			prestmt.setDate(5,new java.sql.Date(dto.getBrithday().getTime()));
			prestmt.setInt(6,dto.getSupervalue());
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			rs = prestmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
//			throw new DaoException(Ca+"用户注册异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
		}
		return rs;
	}
	
	/**
	 * 用户dao普通用户更新信息具体实现方法
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	public UserDto updateNormal(UserDto dto) {
		//数据流插入记录返回结果（更新条目数）
		UserDto newdto = null;
		//预编译对象
		PreparedStatement prestmt = null;
		try {
			
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("update T_USER set SNAME=?,EMAIL=?,BRITHDAY=? where SID=?");
			prestmt.setString(1,dto.getSname());
			prestmt.setString(2,dto.getEmail());
			prestmt.setDate(3,new java.sql.Date(dto.getBrithday().getTime()));
			prestmt.setInt(4,dto.getSid());
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			int rs = prestmt.executeUpdate();
			
			if(rs<=0) {
				System.err.println(Ca+"更新用户信息失败！");
			}
			else {
				newdto = searchBySid(dto.getSid());
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"用户注册异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
		}
		return newdto;
	}
	
	/**
	 * 用户dao管理员删除具体实现方法
	 * @param sid 删除的用户名
	 * @return 更新记录的条目数
	 */
	public int deleteUserById(int sid) {
		//数据流插入记录返回结果（更新条目数）
		int rs = 0;
		//预编译对象
		PreparedStatement prestmt = null;
		try {
			
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("update T_USER set STATUS=0 where SID=?");
			prestmt.setInt(1,sid);
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			rs = prestmt.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(Ca+"用户注册异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
		}
		return rs;
	}
	
	/**
	 * 用户dao根据id查找用户具体实现方法
	 * @param sid 用户名
	 * @return 用户信息
	 */
	public UserDto searchBySid(int sid) {
		//用户模型
		UserDto dto = null;
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			prestmt = conn.prepareStatement("select * from T_USER where SID = ?");
			prestmt.setInt(1,sid);
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			if(rs.next()) {
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
			}
			else {
				System.out.println(Ca+"结果集为空");
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"查询用户异常", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return dto;
	}

	/**
	 * 用户dao更新密码具体实现方法
	 * @param sid 用户名
	 * @param password 新密码
	 * @return 更新密码
	 */
	public String updateSpwdById(int sid, String password) {
		//数据流插入记录返回结果（更新条目数）
		String newpwd = null;
		//预编译对象
		PreparedStatement prestmt = null;
		//储存返回记录的结果集
		ResultSet rs = null;
		try {
			
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("update T_USER set SPWD=? where SID=?");
			prestmt.setString(1,password);
			prestmt.setInt(2,sid);
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			int num = prestmt.executeUpdate();
			
			if(num<=0) {
				System.err.println(Ca+"更新用户信息失败！");
			}
			else {
				prestmt = conn.prepareStatement("select SPWD from T_USER where SID=?");
				prestmt.setInt(1,sid);
				
				rs = prestmt.executeQuery();
				
				if(rs.next()) {
					newpwd = rs.getString("SPWD");
				}
				else {
					System.out.println(Ca+"获取用户信息异常。");
				}
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"用户更新异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
			DBUtil.closeResultSet(rs);
		}
		return newpwd;
	}
	
	/**
	 * 用户dao管理员更新信息具体实现方法
	 * @param dto 更新信息
	 * @return 更新条目数
	 */
	public int updateAdmin(UserDto dto) {
		//数据流插入记录返回结果（更新条目数）
		int rs = 0;
		//预编译对象
		PreparedStatement prestmt = null;
		try {
			
			//在预编译对象中设置SQL插入记录语句
			prestmt = conn.prepareStatement("update T_USER set SNAME=?,SPWD=?,EMAIL=?,BRITHDAY=? where SID=?");
			prestmt.setString(1,dto.getSname());
			prestmt.setString(2,dto.getEmail());
			prestmt.setString(3,dto.getSpwd());
			prestmt.setDate(4,new java.sql.Date(dto.getBrithday().getTime()));
			prestmt.setInt(5,dto.getSid());
			
			//预编译对象执行SQL更新，并将更新条目返回给rs
			rs = prestmt.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(Ca+"更新用户异常", e);
		}finally {
			DBUtil.closeStatement(prestmt);
		}
		return rs;
	}
	
	/**
	 * 用户dao管理员获取所有用户信息具体实现方法
	 * @return 用户信息集合
	 */
	public List<UserDto> getAllUser() {
		//用户集合
		List<UserDto> ldto = new ArrayList<UserDto>();
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			prestmt = conn.prepareStatement("select * from T_USER order by ID");
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			while(rs.next()) {
				//媒介用户模型
				UserDto dto = new UserDto();
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
				ldto.add(dto);
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"查询用户异常", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return ldto;
	}
	
	/**
	 * 用户dao管理员分页获取所有用户信息具体实现方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getAllUser(int pageNo, int pageSize) {
		//用户集合
		List<UserDto> ldto = new ArrayList<UserDto>();
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			prestmt = conn.prepareStatement("select * from "
											+ "(select T2.*,rownum RN from "
												+ "(select T1.* from T_USER T1 order by ID) T2) "
												+ "where RN>? and RN<=?");
			
			//设置需获取信息的编号
			prestmt.setInt(1,(pageNo-1)*pageSize);
			prestmt.setInt(2,pageNo*pageSize);
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			while(rs.next()) {
				//媒介用户模型
				UserDto dto = new UserDto();
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
				ldto.add(dto);
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"查询用户异常", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return ldto;
	}
	
	/**
	 * 用户dao管理员分页获取有效用户信息具体实现方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getValidUser(int pageNo, int pageSize) {
		//用户集合
		List<UserDto> ldto = new ArrayList<UserDto>();
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			prestmt = conn.prepareStatement("select * from "
											+ "(select T2.*,rownum RN from "
												+ "(select T1.* from T_USER T1 where STATUS=1 order by ID) T2) "
												+ "where RN>? and RN<=?");
			
			//设置需获取信息的编号
			prestmt.setInt(1,(pageNo-1)*pageSize);
			prestmt.setInt(2,pageNo*pageSize);
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			while(rs.next()) {
				//媒介用户模型
				UserDto dto = new UserDto();
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
				ldto.add(dto);
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"查询用户异常", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return ldto;
	}
	
	/**
	 * 用户dao分页模糊具体实现方法
	 * @param searchStr 查询字符串
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> fuzzSearchByName(String searchStr, int pageNo, int pageSize) {

		//用户集合
		List<UserDto> ldto = new ArrayList<UserDto>();
		//预编译对象
		PreparedStatement prestmt = null;
		//结果集
		ResultSet rs = null;
		try {
			//设置查询语句
			String sql = "select * from "
						+ "(select T2.*,rownum RN from "
							+ "(select T1.* from T_USER T1 where SNAME like ? order by ID)"
					 + " T2) where RN>? and RN<=?";
			prestmt = conn.prepareStatement(sql);
			
			//设置需获取信息的编号
			prestmt.setString(1,"%"+searchStr+"%");
			prestmt.setInt(2,(pageNo-1)*pageSize);
			prestmt.setInt(3,pageNo*pageSize);
			
			//执行查询语句再利用结果集接收查询结果
			rs = prestmt.executeQuery();
			
			//判断结果集是否为空
			while(rs.next()) {
				//媒介用户模型
				UserDto dto = new UserDto();
				dto = new UserDto();
				dto.setId(rs.getInt("ID"));
				dto.setSid(rs.getInt("SID"));
				dto.setSname(rs.getString("SNAME"));
				dto.setSpwd(rs.getString("SPWD"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setBrithday(rs.getDate("BRITHDAY"));
				dto.setSupervalue(rs.getInt("SUPERVALUE"));
				dto.setStatus(rs.getInt("STATUS"));
				ldto.add(dto);
			}
		}catch(SQLException e) {
			throw new DaoException(Ca+"查询用户异常\n", e);
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(prestmt);
		}
		return ldto;
	}
	
	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		UserDaoImpl dao = new UserDaoImpl(conn);
		DBUtil.beginTransaction(conn);
		//登录测试
//		UserDto dto = userdao.login(2016874202,"123456");
//		System.out.println(Ca+dto.getSupervalue());
		
		//插入-注册测试
//		Date date = new Date();
//		UserDto dto = new UserDto();
//		dto.setSid(2016874212);
//		dto.setSname("Natah");
//		dto.setSpwd("123456");
//		dto.setEmail("Natah@qq.com");
//		dto.setBrithday(date);
//		dto.setSupervalue(2);
//		dto.setStatus(2);
//		System.out.println(Ca+"更新条目数："+dao.signUp(dto));
		
		//更新测试
//		String str = "1996-07-05";
//		Date date = TypeUtil.strToDate(str);
//		UserDto dto = new UserDto();
//		dto.setSid(2016874212);
//		dto.setSname("Natan");
//		dto.setSpwd("123456");
//		dto.setEmail("Natah@qq.com");
//		dto.setBrithday(date);
//		dto.setSupervalue(2);
//		dto.setStatus(2);
//		dto = dao.updateNormal(dto);
//		System.out.println(dto.getId());
//		System.out.println(dto.getSid());
//		System.out.println(dto.getSname());
//		System.out.println(dto.getSpwd());
//		System.out.println(dto.getBrithday());
//		System.out.println(dto.getSupervalue());
//		System.out.println(dto.getStatus());
		
		//修改密码测试
//		String pwd = "123456";
//		pwd = dao.updateSpwdById(2016874240, pwd);
//		System.out.println(Ca+pwd);
		
//		System.out.println(dto.getEmail());


		//获取所有用户信息测试-分页
//		List<UserDto> ldto = dao.getAllUser(3,5);
//		System.out.println(ldto.size());
//		for(int i=0;ldto.size()>i;i++) {
//		UserDto dto = ldto.get(i);
//		System.out.println(dto.getId());
//		System.out.println(dto.getSid());
//		System.out.println(dto.getSname());
//		System.out.println(dto.getSpwd());
//		System.out.println(dto.getBrithday());
//		System.out.println(dto.getSupervalue());
//		System.out.println(dto.getStatus());
//		}
		
		//查找测试
//		UserDto dto = dao.searchBySid(1);
//		System.out.println(dto.getId());
//		System.out.println(dto.getSid());
//		System.out.println(dto.getSname());
//		System.out.println(dto.getSpwd());
//		System.out.println(dto.getBrithday());
//		System.out.println(dto.getSupervalue());
//		System.out.println(dto.getStatus());
		
		//删除测试
//		System.out.println(dao.deleteUserById(2016874233));
		

		List<UserDto> ldto = dao.fuzzSearchByName("W", 1, 5);
		for(int i=0;ldto.size()>i;i++) {
			UserDto dto = ldto.get(i);
			System.out.println(dto.getId());
			System.out.println(dto.getSid());
			System.out.println(dto.getSname());
			System.out.println(dto.getSpwd());
			System.out.println(dto.getBrithday());
			System.out.println(dto.getSupervalue());
			System.out.println(dto.getStatus());
			}
		
		
		DBUtil.commit(conn);
		DBUtil.closeConnection(conn);
	}
}
