package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.Users;

public class UsersModel extends Users{
	private String rname;
	private Long rid;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
	
}
