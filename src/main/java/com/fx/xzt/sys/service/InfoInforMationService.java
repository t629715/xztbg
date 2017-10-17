package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InfoInformation;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface InfoInforMationService extends IService<InfoInformation>{
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
	PageInfo<Map<String, Object>> getByAll(String title,String startTime,String endTime,Integer state,String operator,Integer pageNum,Integer pageSize);
	/**
	 * 发布
	 */
	int posted(InfoInformation i);
	/**
	 * 查看
	 */
	Map<String, Object> getById(Long serialNo);
	/**
	 * 修改
	 */
	int edit(InfoInformation i);
	/**
	 * 删除
	 */
	int deleteById(Long serialNo);
	/**
	 * 置顶 / 取消置顶
	 */
	/*int editTopState(Map<String, Object> map);*/
}
