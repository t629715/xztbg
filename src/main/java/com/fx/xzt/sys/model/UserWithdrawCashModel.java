package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.UserWithdrawCash;

public class UserWithdrawCashModel extends UserWithdrawCash{
	private String bankName;
	
	private String statusName;
	
	private String withdrawtimeString;
	
	private String finishtimeString;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getWithdrawtimeString() {
		return withdrawtimeString;
	}

	public void setWithdrawtimeString(String withdrawtimeString) {
		this.withdrawtimeString = withdrawtimeString;
	}

	public String getFinishtimeString() {
		return finishtimeString;
	}

	public void setFinishtimeString(String finishtimeString) {
		this.finishtimeString = finishtimeString;
	}
	
	
	
}
