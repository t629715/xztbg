package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.InfoBanner;
import com.github.pagehelper.PageInfo;

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
	 * @param upSerialNo   上一个
	 * @param downSerialNo 下一个
	 * @return
	 */
	int up(Long upSerialNo,Long downSerialNo);

	/**
	 * 根据序号获取图片
	 * @param serialNo
	 * @return
	 */
	InfoBanner selectById(Long serialNo);

	/**
	 * 修改图片的显示顺序
	 * @param sortNo1
	 * @param sortNo2
	 * @return
	 */
	int modifySeriaNo(Integer  sortNo1, Long sortNo2);
}
