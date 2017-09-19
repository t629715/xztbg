package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.UserGoodsExchange;
import com.fx.xzt.sys.model.UserGoodsExchangeModel;
import com.fx.xzt.sys.service.UserGoodsExchangeService;
import com.fx.xzt.sys.util.UserGoodsExchangeStatusEnum;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/userGoodsExchange")
public class UserGoodsExchangeController {
	@Resource
	UserGoodsExchangeService userGoodsExchangeService;
	
	/**
	 * 集合显示
	 * @return
	 */
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserGoodsExchangeModel> selectByAll(String goodsName, String userName, String startTime,
			String endTime, String goodsType, Short status, Integer pageNum, Integer pageSize){
		return userGoodsExchangeService.getByAll(goodsName, userName, startTime, endTime, goodsType, status, pageNum, pageSize);
	}
	
	/**
	 * 修改 认证
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(UserGoodsExchange userGoodsExchange){
		Map<String,Object> map = new HashMap<String,Object>();
		userGoodsExchange.setStatus(UserGoodsExchangeStatusEnum.success.getIndex());
		int msg = userGoodsExchangeService.edit(userGoodsExchange);
		map.put("msg", msg);
		return map;
	}
	
}
