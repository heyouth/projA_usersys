package com.nendeu.usersys.common.exception;
/**
 * 工具异常-Date异常类
 * @author Hellow
 *
 */
public class DateException extends RuntimeException{
	/**
	 * 构造方法
	 * @param arg0 产生异常的原因
	 */
	public DateException(Throwable arg0) {
		super(arg0);
	}
	
	/**
	 * 构造方法
	 * @param arg0 异常的详细信息
	 * @param arg1 产生异常的原因
	 */
	public DateException(String arg0,Throwable arg1) {
		super(arg0,arg1);
	}
}
