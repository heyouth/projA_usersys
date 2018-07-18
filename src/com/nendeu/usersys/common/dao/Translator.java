package com.nendeu.usersys.common.dao;

/**
 * 转译器
 * 能够将数据库中某些简化意义字段转义为实际涵义
 * @author Heyouth
 *
 */
public class Translator {
	/**
	 * 转义方法联合调度方法
	 * @param value 转义值
	 * @param type 转化数据类型
	 * @return 实际涵义
	 */
//	public String UnitedTranslate(int value,String type) {
//		return sense;
//	}
//	public String UnitedTranslate(String value,String type) {
//		return sense;
//	}
	public String UnitedTranslate(int value) {//默认为查询权限
		return translatorSupervalue(value);
	}

	/**
	 * 转义方法-用户权限
	 * @param value 用户权限值
	 * @return 用户权限
	 */
	private String translatorSupervalue(int value) {
		String sense = "";
		if(value==2) {
			sense = "管理员";
		}
		else {
			sense = "普通用户";
		}
		return sense;
	}
}
