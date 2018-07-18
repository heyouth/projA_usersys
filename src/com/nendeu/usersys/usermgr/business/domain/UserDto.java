package com.nendeu.usersys.usermgr.business.domain;

import java.util.Date;

/**
 * User模型
 * @author Hellow
 *
 */
public class UserDto {
	private int id;
	private int sid;
	private String sname;
	//用户密码
	private String spwd;
	private String email;
	//用户权限
	private int supervalue;
	//用户状态
	private int status;
	private Date brithday;
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSupervalue() {
		return supervalue;
	}
	public void setSupervalue(int supervalue) {
		this.supervalue = supervalue;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
