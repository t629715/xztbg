package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.InfoBanner;
import com.github.pagehelper.PageInfo;

public interface InfoBannerService extends IService<InfoBanner>{
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	PageInfo<InfoBanner> getByPageAll(Integer page,Integer pageNum,
			Integer pageSize);
	//更新
	int edit(InfoBanner infoBanner);
	//添加
	int add(InfoBanner infoBanner);
	//删除
	int deleteById(Long serialNo);
	/**
	 * 向上向下
	 * @param upSerialNo   上一个
	 * @param downSerialNo 下一个
	 * @return
	 */
	int up(Long upSerialNo,Long downSerialNo);
	
	InfoBanner selectById(Long serialNo);
}
