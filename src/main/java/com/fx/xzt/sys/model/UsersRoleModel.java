package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.UsersUserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UsersRoleModel implements Serializable{
	private Integer infoId;
	private String roleName;
	private List<Users> usersList;
	private Date createTime;
	private String userNames;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
}
