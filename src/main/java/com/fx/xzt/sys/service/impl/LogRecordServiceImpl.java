package com.fx.xzt.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.mapper.LogRecordMapper;
import com.fx.xzt.sys.service.LogRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	/**
	 * 查询
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String type, String moduleName, 
			String startTime, String endTime, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("type", type);
        map.put("moduleName", moduleName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = logRecordMapper.selectByAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelByAll(String userName, String type,
			String moduleName, String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("type", type);
        map.put("moduleName", moduleName);
        map.put("endTime", endTime);
        map.put("endTime", endTime);
        List<Map<String, Object>> list = logRecordMapper.selectByAll(map);
        return list;
	}



}
