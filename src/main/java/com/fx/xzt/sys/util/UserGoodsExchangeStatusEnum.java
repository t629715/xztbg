package com.fx.xzt.sys.util;

public enum UserGoodsExchangeStatusEnum {
	success(1,"已发放"),
	audit(0,"未发放");
	private UserGoodsExchangeStatusEnum(Integer index, String name) {
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
		for (UserGoodsExchangeStatusEnum u : UserGoodsExchangeStatusEnum.values()) {
			if (u.index==index) {
				return u.name;
			}
		}
		return "";
	}
}
