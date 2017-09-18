package com.fx.jyg.sys.util;

public enum RechargeChannelEnum {
	YL("01", "银联"), 
	WX("02", "微信"), 
	ZFB("03", "支付宝");
	
	private RechargeChannelEnum(String index, String name) {
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
		for (RechargeChannelEnum u : RechargeChannelEnum.values()) {
			if (u.index.equals(index)) {
				return u.name;
			}
		}
		return "";
	}
}
