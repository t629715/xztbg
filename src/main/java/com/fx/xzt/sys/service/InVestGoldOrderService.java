package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InVestGoldOrderService 
* @Description: 金条投资订单
* @author htt
* @date 2018-4-19 下午3:01:33 
*
 */
public interface InVestGoldOrderService {
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态 0:待支付1:未发货2:已发货3、已完成4:未发货已取消5:未支付已关闭
	* @param isView
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer status, 
    		String isView, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelByAll 
	* @Description: 导出
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态 0:待支付1:未发货2:已发货3、已完成4:未发货已取消5:未支付已关闭
	* @param isView
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer status, 
    		String isView);

}
