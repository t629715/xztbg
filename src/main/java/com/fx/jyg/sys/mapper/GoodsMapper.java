/*
 * @ClassName GoodsMapper
 * @Description 
 * @version 1.0
 * @Date 2017-08-28 09:30:02
 */
package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.Goods;
import com.fx.jyg.sys.model.GoodsModel;

@Repository
public interface GoodsMapper extends BaseMapper<Goods>{
	/**
	 * 查询商品列表
	 * @param map
	 * @return
	 */
	List<GoodsModel> getByGoodsAll(Map<String,Object> map);
	
	int edit(Goods goods);
	
	int add(Goods goods);
	
	int deleteById(String goodsID);
}