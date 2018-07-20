package com.nendeu.usersys.common.util;

/**
 * 转译器
 * 能够将数据库中某些简化意义字段转义为实际涵义
 * @author Heyouth
 *
 */
public class Translator {

	/**
	 * 用户权限转义方法：将SUPERVALUE字段转换为实际涵义
	 * @param value 用户权限值
	 * @return 用户权限涵义
	 */
	public static String translatorSupervalue(int value) {
		String sense = "";
		if(value==2) {
			sense = "管理员   ";
		}
		else {
			sense = "普通用户";
		}
		return sense;
	}
	
	/**
	 * 账户状态转义方法：将STATUS字段转换为实际涵义
	 * @param value 账户状态值
	 * @return 账户状态涵义
	 */
	public static String translatorStatus(int value) {
		String sense = "";
		if(value==0) {
			sense = "冻结";
		}
		else {
			sense = "正常";
		}
		return sense;
	}
}
