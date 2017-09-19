package com.fx.xzt.sys.model;

import java.util.Date;

import com.fx.xzt.sys.entity.UsersUserRole;

public class UsersUserRoleModel extends UsersUserRole {
	private String uname;
	private String rname;
	private Date createTime;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
