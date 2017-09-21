package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.UserInfo;

public class UserInfoModel extends UserInfo{
	
	private String userIdString;
	private String userName;
	private Integer status;
	private String agentName;
	private String brokerName;
	private String bankName;
	private String accountNum;
	private Integer rmb;
	private Integer finance;
	private Integer frozenRmb;
	private Integer totalIncome;
	private Float gold;
	private Float frozenGold;

	public String getUserIdString() {
		return userIdString;
	}

	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public Integer getRmb() {
		return rmb;
	}

	public void setRmb(Integer rmb) {
		this.rmb = rmb;
	}

	public Integer getFinance() {
		return finance;
	}

	public void setFinance(Integer finance) {
		this.finance = finance;
	}

	public Integer getFrozenRmb() {
		return frozenRmb;
	}

	public void setFrozenRmb(Integer frozenRmb) {
		this.frozenRmb = frozenRmb;
	}

	public Integer getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Float getGold() {
		return gold;
	}

	public void setGold(Float gold) {
		this.gold = gold;
	}

	public Float getFrozenGold() {
		return frozenGold;
	}

	public void setFrozenGold(Float frozenGold) {
		this.frozenGold = frozenGold;
	}
}
