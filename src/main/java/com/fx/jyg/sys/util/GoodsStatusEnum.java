package com.fx.jyg.sys.util;

public enum GoodsStatusEnum {
	SX(1,"上线"),
	XX(0,"下线"),
	BJ(-1,"编辑");
	private GoodsStatusEnum(Integer index, String name) {
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
		for (GoodsStatusEnum u : GoodsStatusEnum.values()) {
			if (u.index==index) {
				return u.name;
			}
		}
		return "";
	}
}
