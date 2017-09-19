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
	
	int edit(InfoBanner infoBanner);
	
	int add(InfoBanner infoBanner);
	
	int deleteById(Long serialNo);
	
	InfoBanner selectById(Long serialNo);
}