package com.fx.xzt.sys.util;

public enum AccountRecordActionEnum {
	CZ("10", "充值"), 
	TX("20", "提现"), 
	BS("30", "比赛");
	
	private AccountRecordActionEnum(String index, String name) {
		this.index = index;
		this.name = name;
	}

	private String index;
	private String name;

	

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(String index) {
		for (AccountRecordActionEnum u : AccountRecordActionEnum.values()) {
			if (u.index.equals(index)) {
				return u.name;
			}
		}
		return "";
	}
}
