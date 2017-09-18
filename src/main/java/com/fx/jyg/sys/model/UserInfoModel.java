package com.fx.jyg.sys.model;

import java.math.BigDecimal;

import com.fx.jyg.sys.entity.UserInfo;

public class UserInfoModel extends UserInfo{
	
	private String userIdString;
	
	private String userName;
	
	private Integer status;

	private String bankName;
	
	private String accountNum;
	
	private  BigDecimal balanceAmt;
	
	private  BigDecimal uBalanceAmt;
	
	private  BigDecimal uFrozenAmt;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public BigDecimal getuBalanceAmt() {
		return uBalanceAmt;
	}

	public void setuBalanceAmt(BigDecimal uBalanceAmt) {
		this.uBalanceAmt = uBalanceAmt;
	}

	public BigDecimal getuFrozenAmt() {
		return uFrozenAmt;
	}

	public void setuFrozenAmt(BigDecimal uFrozenAmt) {
		this.uFrozenAmt = uFrozenAmt;
	}

	public String getUserIdString() {
		return userIdString;
	}

	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}
	
	
	
}
