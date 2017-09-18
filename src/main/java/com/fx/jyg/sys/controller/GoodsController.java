package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.entity.Goods;
import com.fx.jyg.sys.model.GoodsModel;
import com.fx.jyg.sys.service.GoodsService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Resource
	GoodsService goodsService;
	
	/**
	 * 获取列表
	 * @return
	 */
	@RequestMapping(value="/selectByGoodsAll")
	@ResponseBody
	public PageInfo<GoodsModel> selectByGoodsAll(String goodsName, String startTime, String endTime, Short status,
			String goodsType, Integer pageNum, Integer pageSize){
		return goodsService.getByGoodsAll(goodsName, startTime, endTime, status, goodsType, pageNum, pageSize);
				
	}
	/**
	 * 上线下线
	 * @param goodsID
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/offlineOrOnline")
	@ResponseBody
	public Map<String,Object> offlineOrOnline(String goodsID, Integer type){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.offlineOrOnline(goodsID, type);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 添加
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(Goods goods){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.add(goods);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(Goods goods){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.edit(goods);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(String goodsID){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.deleteById(goodsID);
		map.put("msg", msg);
		return map;
	}
}
