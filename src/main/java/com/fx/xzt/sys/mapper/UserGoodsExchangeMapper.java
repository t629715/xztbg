/*
 * @ClassName UserGoodsExchangeMapper
 * @Description 
 * @version 1.0
 * @Date 2017-08-28 16:55:33
 */
package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserGoodsExchange;
import com.fx.xzt.sys.model.UserGoodsExchangeModel;

@Repository
public interface UserGoodsExchangeMapper extends BaseMapper<UserGoodsExchange>{
	
	int edit(UserGoodsExchange userGoodsExchange);
	/**
	 * 兑换订单列表
	 * @param map
	 * @return
	 */
	List<UserGoodsExchangeModel> getByAll(Map<String,Object> map);
}