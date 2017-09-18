package com.fx.jyg.sys.service;

import com.fx.jyg.sys.entity.Goods;
import com.fx.jyg.sys.model.GoodsModel;
import com.github.pagehelper.PageInfo;

public interface GoodsService extends IService<Goods>{
	int edit(Goods goods);
	
	int add(Goods goods);
	
	int deleteById(String goodsID);
	/**
	 * 查询列表集合
	 * @return
	 */
	PageInfo<GoodsModel> getByGoodsAll(String goodsName,String startTime,String endTime,Short status,String goodsType,Integer pageNum,Integer pageSize);
	/**
	 * 上线  1/ 0下线
	 */
	int offlineOrOnline(String goodsID,Integer type);
}
