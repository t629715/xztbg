package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: StandardUser
 * @Description: 标准户
 * @author htt
 * @date 2018-2-3 下午3:18:58
 * 
 */
public class StandardUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String UserID;
	private String UserName;
	private String agentId;
	private String brokerId;
	private String agentName;
	private String brokerName;
	private Date RegisterTime;
	private String rjCount;
	private String cjCount;
	private String bzh;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public Date getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(Date registerTime) {
		RegisterTime = registerTime;
	}

	public String getRjCount() {
		return rjCount;
	}

	public void setRjCount(String rjCount) {
		this.rjCount = rjCount;
	}

	public String getCjCount() {
		return cjCount;
	}

	public void setCjCount(String cjCount) {
		this.cjCount = cjCount;
	}

	public String getBzh() {
		return bzh;
	}

	public void setBzh(String bzh) {
		this.bzh = bzh;
	}

}
