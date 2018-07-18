package com.nendeu.usersys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nendeu.usersys.common.exception.DaoException;
import com.nendeu.usersys.common.exception.EmailException;

/**
 * 数据类型工具类
 * @author Heyouth
 *
 */
public class TypeUtil {
	/**
	 * 带日期模式的日期转字符串方法
	 * @param date 转换的日期对象
	 * @param format 转换的日期模式
	 * @return 日期字符串
	 */
	public static String DateTostr(Date date,String format) {
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		str = sdf.format(date);
		return str;
	}
	/**
	 * 日期模式默认为“yyyy-MM-dd”的日期转字符串方法
	 * @param date 转换的日期对象
	 * @return 日期字符串
	 */
	public static String DateTostr(Date date) {
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		str = sdf.format(date);
		return str;
	}
	/**
	 * 带日期模式的字符串转换日期方法
	 * @param str 转换的字符串
	 * @param format 转换的日期模式
	 * @return 转换后的日期
	 */
	public static Date strToDate(String str,String format) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(str);
		}catch (ParseException e) {
			throw new DaoException("字符串转化日期异常！",e);
//			System.out.println("ERROR:字符串转换日期类型异常！");
//			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 日期模式默认为“yyyy-MM-dd”的字符串转换日期方法
	 * @param str 转换的字符串
	 * @return 转换后的日期
	 */
	public static Date strToDate(String str) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(str);
		}catch (ParseException e) {
			throw new DaoException("字符串转化日期异常！",e);
//			System.out.println("ERROR:字符串转换日期类型异常！");
//			e.printStackTrace();
		}
		return date;
	}
	public static boolean checkMail(String email) {
		boolean flag = false;
		String regEx = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		try {
			if(email.matches(regEx)) {
				flag = true;
			}
		}catch(Exception e) {
			throw new EmailException("Email正则匹配表达式匹配异常",e );
		}
		return flag;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
