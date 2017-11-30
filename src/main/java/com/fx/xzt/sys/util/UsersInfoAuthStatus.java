package com.fx.xzt.sys.util;

public enum UsersInfoAuthStatus {
	AuthstatusPass(1,"已实名"),
	AuthstatusNoPass(3,"未通过");
	private UsersInfoAuthStatus(int index, String name) {
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

	public static String getName(int index,int type) {
		for (UsersInfoAuthStatus u : UsersInfoAuthStatus.values()) {
			if (u.index == index) {
				if(type==1){
					return index==0 ? "未认证":"已认证";
				}
				return u.name;
			}
		}
		return "";
	}
}
