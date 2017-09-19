package com.fx.xzt.sys.util;

public enum UserWithdrawCashStatusEnum {
	success(1,"已完成"),failure(0,"审核中"),reject(-1,"拒绝");
	private UserWithdrawCashStatusEnum(Integer index, String name) {
		this.index = index.shortValue();
		this.name = name;
	}

	private Short index;
	private String name;

	public Short getIndex() {
		return index;
	}

	public void setIndex(Short index) {
		this.index = index;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(Short index) {
		for (UserWithdrawCashStatusEnum u : UserWithdrawCashStatusEnum.values()) {
			if (u.index == index) {
				return u.name;
			}
		}
		return "";
	}
}
