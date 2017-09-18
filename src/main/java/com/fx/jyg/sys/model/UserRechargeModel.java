package com.fx.jyg.sys.model;

import com.fx.jyg.sys.entity.UserRecharge;

public class UserRechargeModel extends UserRecharge {
	private String rechargeChannelName;
	private String statusName;
	private String formatRechargetime;

	public String getRechargeChannelName() {
		return rechargeChannelName;
	}

	public void setRechargeChannelName(String rechargeChannelName) {
		this.rechargeChannelName = rechargeChannelName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFormatRechargetime() {
		return formatRechargetime;
	}

	public void setFormatRechargetime(String formatRechargetime) {
		this.formatRechargetime = formatRechargetime;
	}

}
