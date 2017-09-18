package com.fx.jyg.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.UserGoodsExchange;
import com.fx.jyg.sys.mapper.UserGoodsExchangeMapper;
import com.fx.jyg.sys.model.UserGoodsExchangeModel;
import com.fx.jyg.sys.service.UserGoodsExchangeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserGoodsExchangeServiceImpl extends BaseService<UserGoodsExchange> implements UserGoodsExchangeService{
	
	@Resource
	UserGoodsExchangeMapper userGoodsExchangeMapper;
	
	public PageInfo<UserGoodsExchangeModel> getByAll(String goodsName, String userName, String startTime,
			String endTime, String goodsType, Short status, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsName", goodsName);
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("goodsType", goodsType);
		map.put("status", status);
		PageHelper.startPage(pageNum,pageSize);
		List<UserGoodsExchangeModel> list = userGoodsExchangeMapper.getByAll(map);
		return  new PageInfo<UserGoodsExchangeModel>(list);
	}

	public int edit(UserGoodsExchange userGoodsExchange) {
		return userGoodsExchangeMapper.edit(userGoodsExchange);
	}

}
