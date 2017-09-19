package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.InfoComment;

public class InfoCommentModel extends InfoComment {
	private String userName;
	private String title;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
