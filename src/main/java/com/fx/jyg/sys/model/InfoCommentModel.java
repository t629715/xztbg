package com.fx.jyg.sys.model;

import com.fx.jyg.sys.entity.InfoComment;

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
