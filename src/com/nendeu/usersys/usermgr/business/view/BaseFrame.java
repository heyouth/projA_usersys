package com.nendeu.usersys.usermgr.business.view;

import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 视图接口，所以视图类都继承自该接口
 * @author Heyouth
 *
 */
public interface BaseFrame {
	/**
	 * 视图-显示页面
	 */
	public void show();
	/**
	 * 视图-显示查找页面
	 */
	public void searchShow();
	/**
	 * 视图-注册用户页面
	 */
	public void addShow();
	/**
	 * 视图-增加用户页面
	 * @return 更新记录条目数
	 */
	public void updateShow(String type,UserDto dto);
	/**
	 * 视图-展示当前用户信息
	 */
	public void msgShow();
}
