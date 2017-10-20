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
	* @Title: selectByDealOrder 
	* @Description: 出入金查询
	* @param userName  用户账号
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param pageNum 页数
	* @param pageSize 条数
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByDealOrder(String userName, String agentName, String brokerName, 
			Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelDealOrder 
	* @Description: 出入金查询--导出
	* @param userName  用户账号
	* @param agentName 代理商账号
	* @param brokerName 经纪人账号
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelDealOrder(String userName, String agentName, String brokerName);

}
