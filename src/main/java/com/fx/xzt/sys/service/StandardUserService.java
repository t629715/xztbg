package com.fx.xzt.sys.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: StandardUserService 
* @Description: 标准户查询统计
* @author htt
* @date 2018-2-3 下午2:17:53 
*
 */
public interface StandardUserService {
	
	/**
	 * 
	* @Title: getByStandardUser 
	* @Description: 查询统计
	* @param userName 用户名
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param bzh 是否标准户1：是；0：否
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> getByStandardUser(String userName, String agentName, String brokerName, 
			String startTime, String endTime, String regStartTime, String regEndTime, String bzh, Integer pageNum, Integer pageSize) throws ParseException;
	
	/**
	 * 
	* @Title: excelByStandardUser 
	* @Description: 导出
	* @param userName 用户名
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param bzh 是否标准户1：是；0：否
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelByStandardUser(String userName, String agentName, String brokerName, 
			String startTime, String endTime, String regStartTime, String regEndTime, String bzh) throws ParseException;

}
