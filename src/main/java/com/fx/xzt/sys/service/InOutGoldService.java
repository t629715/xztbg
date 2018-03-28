package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InOutGoldService 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:34:55 
*
 */
public interface InOutGoldService {
	
	/**
	 * 
	* @Title: selectByInOutGold 
	* @Description: 出入金查询
	* @param userName  用户账号
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	 * @param isView 
	* @param pageNum 页数
	* @param pageSize 条数
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByInOutGold(String userName, String agentName, String brokerName, 
			String isView, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelInOutGold 
	* @Description: 出入金查询--导出
	* @param userName  用户账号
	* @param agentName 代理商账号
	* @param brokerName 经纪人账号
	 * @param isView 
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelInOutGold(String userName, String agentName, String brokerName, String isView);
	
	/**
	 * 
	* @Title: selectByRechargeChannel 
	* @Description: 出入金分析--支付渠道分析
	* @param type   1：今天；2：昨天；3：近7天；4：本月
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByRechargeChannel(String type, String startTime, String endTime, String channel);
	
	/**
	 * 
	* @Title: selectByAgent 
	* @Description: 出入金分析--运营商出入金分析
	* @param type 1：今天；2：昨天；3：近7天；4：本月
	* @param startTime
	* @param endTime
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAgent (String type, String startTime, String endTime);
	
	/**
	 * 
	* @Title: selectByAgentNet 
	* @Description: 出入金分析-运营商净入金分析
	* @param type 1：今天；2：昨天；3：近7天；4：本月
	* @param startTime
	* @param endTime
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAgentNet (String type, String startTime, String endTime);

}
