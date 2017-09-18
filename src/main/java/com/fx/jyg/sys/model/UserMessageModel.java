package com.fx.jyg.sys.model;

import com.fx.jyg.sys.entity.UserMessage;

public class UserMessageModel extends UserMessage {
	private String msgType;
	private String userName;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
