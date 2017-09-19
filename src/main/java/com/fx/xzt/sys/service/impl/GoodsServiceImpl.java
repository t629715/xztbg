package com.fx.xzt.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.Goods;
import com.fx.xzt.sys.mapper.GoodsMapper;
import com.fx.xzt.sys.model.GoodsModel;
import com.fx.xzt.sys.service.GoodsService;
import com.fx.xzt.sys.util.GoodsStatusEnum;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GoodsServiceImpl extends BaseService<Goods> implements GoodsService{
	
	@Resource
	GoodsMapper goodsMapper;
	
	public int edit(Goods goods) {
		return goodsMapper.edit(goods);
	}
	@Transactional
	public int add(Goods goods) {
		goods.setGoodsID("G"+IdUtil.generateyymmddhhMMssSSSAnd4Random());
		goods.setStatus(GoodsStatusEnum.BJ.getIndex());
		return goodsMapper.add(goods);
	}
	@Transactional
	public int deleteById(String goodsID) {
		return goodsMapper.deleteById(goodsID);
	}
	
	public PageInfo<GoodsModel> getByGoodsAll(String goodsName, String startTime, String endTime, Short status,
			String goodsType, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsName", goodsName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", status);
		map.put("goodsType", goodsType);
		PageHelper.startPage(pageNum,pageSize);
		List<GoodsModel> list = goodsMapper.getByGoodsAll(map);
		return new PageInfo<GoodsModel>(list);
	}
	@Transactional
	public int offlineOrOnline(String goodsID, Integer type) {
		Goods g = new Goods();
		g.setGoodsID(goodsID);
		if(type==1){
			g.setStatus(GoodsStatusEnum.SX.getIndex());
			g.setOnlineTime(new Date());
		}else if(type==0){
			g.setStatus(GoodsStatusEnum.XX.getIndex());
			g.setExpiryTime(new Date());
		}
		return edit(g);
	}

}
