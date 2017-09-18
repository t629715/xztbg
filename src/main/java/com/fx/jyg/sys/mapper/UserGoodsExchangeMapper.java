/*
 * @ClassName UserGoodsExchangeMapper
 * @Description 
 * @version 1.0
 * @Date 2017-08-28 16:55:33
 */
package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UserGoodsExchange;
import com.fx.jyg.sys.model.UserGoodsExchangeModel;

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