package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: UserGoldAccountActivityRecordService 
* @Description: 活动-黄金领取
* @author htt
* @date 2017-12-5 下午5:25:49 
*
 */
public interface UserGoldAccountActivityRecordService {
  
	/**
	 * 
	* @Title: selectByDealOrder 
	* @Description: 黄金领取-查询
	* @param userName   用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param agentName 代理商用户名
	* @param brokerName 经纪人用户名
	* @param activityType 活动类型
	 * @param isView 
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer activityType, String isView, Integer pageNum, Integer pageSize);

    /**
     * 
    * @Title: excelDealOrderMessage 
    * @Description: 黄金领取-导出
	* @param userName   用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param agentName 代理商用户名
	* @param brokerName 经纪人用户名
	* @param activityType 活动类型
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelAll(String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer activityType, String isView);

}
