package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.LogRecord;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: LogRecordService 
* @Description: 操作日志记录
* @author htt
* @date 2017-11-27 下午4:25:39 
*
 */
public interface LogRecordService {
	
	/**
	 * 
	* @Title: add 
	* @Description: 新增
	* @param logRecord
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int add(LogRecord logRecord);
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param userName  用户名
	* @param type 操作类型
	* @param moduleName 操作模块
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByAll (String userName, String type, String moduleName, 
			String startTime, String endTime, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelByAll 
	* @Description: 导出
	* @param userName  用户名
	* @param type 操作类型
	* @param moduleName 操作模块
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelByAll (String userName, String type, String moduleName, String startTime, String endTime);

}
