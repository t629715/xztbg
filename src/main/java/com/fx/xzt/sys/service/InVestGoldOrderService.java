package com.fx.xzt.sys.service;

import java.text.ParseException;
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
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态
	* @param payType 支付方式
	* @param isView
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView, Integer pageNum, Integer pageSize) throws ParseException;
	
	/**
	 * 
	* @Title: excelByAll 
	* @Description: 导出
	* @param userName 用户名
	* @param startTime开始时间
	* @param endTime 结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态
	* @param payType 支付方式
	* @param isView
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView) throws ParseException;
	
	/**
	 * 
	* @Title: countByAll 
	* @Description: 统计
	* @param userName 用户名
	* @param startTime开始时间
	* @param endTime 结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态
	* @param payType 支付方式
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> countByAll(String userName, String startTime,
			String endTime, String agentName, String brokerName, Integer status, Integer payType);
	
	/**
	 * 
	* @Title: addLogisticsNoById 
	* @Description: 新增物流单号
	* @param logisticsNo 物流单号
	* @param id id
	* @param operatorId 操作人id
	* @param operatorName 操作人用户名
	* @return
	* @throws ParseException    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int addLogisticsNoById(String logisticsNo, Long id, Long operatorId, String operatorName) throws ParseException;
	
	/**
	 * 
	* @Title: updateLogisticsNoById 
	* @Description: 修改物流单号
	* @param logisticsNo  物流单号
	* @param id id
	* @param operatorId 操作人id
	* @param operatorName 操作人用户名
	* @return    设定文件 
	* @return int    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	int updateLogisticsNoById(String logisticsNo, Long id, Long operatorId, String operatorName) throws ParseException;
	
	/**
	 * 
	* @Title: updateStatusById 
	* @Description: 修改订单状态
	* @param status  状态
	* @param id id
	* @param operatorId 操作人id
	* @param operatorName 操作人用户名
	* @return    设定文件 
	* @return int    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	int updateStatusById(String status, Long id, Long operatorId, String operatorName) throws ParseException;

}