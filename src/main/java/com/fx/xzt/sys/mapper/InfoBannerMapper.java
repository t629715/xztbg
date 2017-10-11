/*
 * @ClassName InfoBannerMapper
 * @Description 
 * @version 1.0
 * @Date 2017-08-25 13:45:24
 */
package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InfoBanner;

@Repository
public interface InfoBannerMapper extends BaseMapper<InfoBanner>{
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<InfoBanner> getByPageAll(Map<String,Object> map);

	/**
	 * 修改图片信息
	 * @param infoBanner
	 * @return
	 */
	int edit(InfoBanner infoBanner);

	/**
	 * 添加图片
	 * @param infoBanner
	 * @return
	 */
	int add(InfoBanner infoBanner);

	/**
	 * 根据序号删除图片
	 * @param serialNo
	 * @return
	 */
	int deleteBySeriaNo(Long serialNo);

	/**
	 * 根据序号获取图片
	 * @param serialNo
	 * @return
	 */
	InfoBanner selectBySeriaNo(Long serialNo);
}