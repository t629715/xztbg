package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.LogRecord;

/**
 * 
* @ClassName: LogRecordMapper 
* @Description: 操作日志记录
* @author htt
* @date 2017-11-27 下午4:26:00 
*
 */
@Repository
public interface LogRecordMapper extends BaseMapper<LogRecord> {
	
	/**
	 * 
	* @Title: add 
	* @Description: 添加
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
	* @Description: 查询	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAll (Map<String,Object> map);

}
