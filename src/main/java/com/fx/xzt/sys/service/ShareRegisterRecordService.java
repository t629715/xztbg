package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: ShareRegisterRecordService 
* @Description: 分享注册
* @author htt
* @date 2018-3-5 上午11:01:39 
*
 */
public interface ShareRegisterRecordService {
    
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param userName 用户名
	* @param acceptPrize 领奖的标识，0未领奖，1领奖
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByAll(String userName, String acceptPrize, String startTime, String endTime, 
    		String agentName, String brokerName, Integer pageNum, Integer pageSize);

    /**
     * 
    * @Title: excelAll 
    * @Description: 导出
    * @param userName 用户名
    * @param acceptPrize 领奖的标识，0未领奖，1领奖
    * @param startTime 开始时间
    * @param endTime 结束时间
    * @param agentName 代理商id
    * @param brokerName 经纪人id
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelAll(String userName, String acceptPrize, String startTime, String endTime, 
    		String agentName, String brokerName);

}
