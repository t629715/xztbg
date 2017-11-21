package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.InfoBanner;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface InfoBannerService extends IService<InfoBanner>{
	/**
	 * 查询列表
	 * @param
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
	 * @param upSortNo   上一个
	 * @param upSortNo 下一个
	 * @return
	 */
	int up(Integer upSortNo,Integer downSortNo);

	/**
	 * 根据序号获取图片
	 * @param serialNo
	 * @return
	 */
	InfoBanner selectById(Long serialNo);

	/**
	 * 获取用于广告位的图片 -- tianilya
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> getAdPic(Short page,Integer capacity);

}
