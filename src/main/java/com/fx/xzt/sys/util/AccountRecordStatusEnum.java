package com.fx.xzt.sys.util;

public enum AccountRecordStatusEnum {
	success(1,"成功"),
	failure(2,"失败"),
	audit(0,"审核中");
	private AccountRecordStatusEnum(Integer index, String name) {
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
		for (AccountRecordStatusEnum u : AccountRecordStatusEnum.values()) {
			if (u.index==index) {
				return u.name;
			}
		}
		return "";
	}
}
