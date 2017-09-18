package com.fx.jyg.sys.service;

import com.fx.jyg.sys.entity.UserGoodsExchange;
import com.fx.jyg.sys.model.UserGoodsExchangeModel;
import com.github.pagehelper.PageInfo;

public interface UserGoodsExchangeService extends IService<UserGoodsExchange>{
	/**
	 * 
		查询兑换订单列表
	 * @return
	 */
	public PageInfo<UserGoodsExchangeModel> getByAll(String goodsName,String userName,String startTime,String endTime,
			String goodsType,Short status,Integer pageNum,Integer pageSize);
	/**
	 * 修改
	 * @param userGoodsExchange
	 * @return
	 */
	int edit(UserGoodsExchange userGoodsExchange);
}
