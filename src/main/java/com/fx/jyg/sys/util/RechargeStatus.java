package com.fx.jyg.sys.util;

public enum RechargeStatus {
	success(1,"成功"),failure(0,"失败");
	private RechargeStatus(Integer index, String name) {
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
		for (RechargeStatus u : RechargeStatus.values()) {
			if (u.index == index) {
				return u.name;
			}
		}
		return "";
	}
}
