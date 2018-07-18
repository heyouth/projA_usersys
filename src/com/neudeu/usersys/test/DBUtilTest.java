package com.neudeu.usersys.test;

import java.sql.Connection;

import org.junit.Test;

import com.nendeu.usersys.common.util.DBUtil;

public class DBUtilTest {
	private static Connection conn = null;
	@Test
	public void testGetConnection() {
		conn = DBUtil.getConnection();
		System.out.println("获取数据库连接-测试完成");
	}

	@Test
	public void testCloseConnection() {
		DBUtil.closeConnection(conn);
		System.out.println("关闭数据库连接-测试完成");
	}

}
