package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.Goods;

public class GoodsModel extends Goods{
	
	private static final long serialVersionUID = 1L;
	
	private String statusName;
	
	private String goodsTypeName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
}
