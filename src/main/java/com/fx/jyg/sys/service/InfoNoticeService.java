package com.fx.jyg.sys.service;

import com.fx.jyg.sys.entity.InfoNotice;
import com.github.pagehelper.PageInfo;

public interface InfoNoticeService extends IService<InfoNotice>{
	int add(InfoNotice infoNotice);

	int edit(InfoNotice infoNotice);

	int deleteById(Long serialNo);

	PageInfo<InfoNotice> getInfoNoticeAll(String title,String startTime,String endTime,String operator, Integer pageNum, Integer pageSize);

	InfoNotice getBySerialNo(Long serialNo);
	
	int upDown(Long upSerialNo,Long downSerialNo);
	
	
}
