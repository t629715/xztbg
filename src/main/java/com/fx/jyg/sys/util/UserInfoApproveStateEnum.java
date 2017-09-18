package com.fx.jyg.sys.util;

public enum UserInfoApproveStateEnum {
	AuthApproveState(0, "未审核"),
	AuthApproveStatePass(1, "通过"), 
	AuthApproveStateNoPass(-1,"未通过");
	private UserInfoApproveStateEnum(int index, String name) {
		this.index = index;
		this.name = name;
	}

	private int index;
	private String name;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(int index) {
		for (UserInfoApproveStateEnum u : UserInfoApproveStateEnum.values()) {
			if (u.index == index) {
				return u.name;
			}
		}
		return "";
	}
}
