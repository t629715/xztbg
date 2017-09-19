package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InfoInforMation;
import com.github.pagehelper.PageInfo;

public interface InfoInforMationService extends IService<InfoInforMation>{
	/**
	 * 列表查询
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @param operator
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<InfoInforMation> getByAll(String title,String startTime,String endTime,Integer state,String operator,Integer pageNum,Integer pageSize);
	/**
	 * 发布
	 */
	int posted(InfoInforMation i);
	/**
	 * 查看
	 */
	InfoInforMation getById(Long serialNo);
	/**
	 * 修改
	 */
	int edit(InfoInforMation i);
	/**
	 * 删除
	 */
	int deleteById(Long serialNo);
	/**
	 * 置顶 / 取消置顶
	 */
	int editTopState(InfoInforMation i);
}
