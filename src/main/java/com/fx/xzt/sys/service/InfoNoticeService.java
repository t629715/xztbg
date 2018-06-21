package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InfoNotice;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface InfoNoticeService extends IService<InfoNotice>{
	int add(InfoNotice infoNotice);

	int edit(InfoNotice infoNotice);

	int deleteById(Long serialNo);

	PageInfo<Map<String, Object>> getInfoNoticeAll(String title,String startTime,String endTime,String operator, Integer pageNum, Integer pageSize);

	InfoNotice getBySerialNo(Long serialNo);
	
	int upDown(Long upSerialNo,Long downSerialNo);

	/**
	 * 获取所有发布人
	 * @return
	 */
	List<Map<String, Object>> getOperators();
	
	
}
