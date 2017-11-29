package com.fx.xzt.sys.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.mapper.LogRecordMapper;
import com.fx.xzt.sys.service.LogRecordService;

/**
 * 
* @ClassName: LogRecordServiceImpl 
* @Description: 操作日志记录
* @author htt
* @date 2017-11-27 下午4:55:31 
*
 */
@Service
public class LogRecordServiceImpl extends BaseService<LogRecord> implements LogRecordService {
	@Resource
	LogRecordMapper logRecordMapper;

	/**
	 * 添加
	 */
	public int add(LogRecord logRecord) {
		return logRecordMapper.add(logRecord);
	}



}
