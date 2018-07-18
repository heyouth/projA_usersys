package com.neudeu.usersys.test;
import java.util.Date;

import org.junit.Test;

import com.nendeu.usersys.common.util.TypeUtil;

public class TypeUtilTest_date {
	@Test
	public void testDateTostrDateString() {
		Date date = new Date();
		String format = "MM-dd-yyyy";
		String str = TypeUtil.DateTostr(date,format);
		System.out.println("测试一日期："+str+"\n测试成功！");
	}

	@Test
	public void testDateTostrDate() {
		Date date = new Date();
		String str = TypeUtil.DateTostr(date);
		System.out.println("测试二日期："+str+"\n测试成功！");
	}

	@Test
	public void testStrToDateStringString() {
		String str = "7-16-2018";
		String format = "MM-dd-yyyy";
		Date date = TypeUtil.strToDate(str, format);
		System.out.println("测试三日期："+date+"\n测试成功！");
	}

	@Test
	public void testStrToDateString() {
		String str = "2018-7-16";
		Date date = TypeUtil.strToDate(str);
		System.out.println("测试四日期："+date+"\n测试成功！");
	}

}
