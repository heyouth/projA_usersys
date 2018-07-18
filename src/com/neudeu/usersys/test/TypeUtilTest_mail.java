package com.neudeu.usersys.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nendeu.usersys.common.util.TypeUtil;

public class TypeUtilTest_mail {

	@Test
	public void testCheckMail() {
		String vaild_mail = "1183766607@qq.com";
		String invaild_mail = "111@ee.com";
		System.out.println(vaild_mail+"的检测结果为："+TypeUtil.checkMail(vaild_mail));
		System.out.println(invaild_mail+"的检测结果为："+TypeUtil.checkMail(invaild_mail));
	}

}
