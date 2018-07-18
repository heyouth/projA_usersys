package com.nendeu.usersys.usermgr.business.view;

import com.nendeu.usersys.usermgr.business.domain.UserDto;

public interface BaseFrame {
	/**
	 * 行为-显示页面
	 */
	public void show();
	/**
	 * 行为-显示查找页面
	 */
	public void searchShow();
	/**
	 * 行为-增加用户页面
	 * @param type
	 */
	public void addShow(String type);
	/**
	 * 行为-更新用户信息页面
	 * @param type
	 * @param dto
	 */
	public void updateShow(String type,UserDto dto);
}
