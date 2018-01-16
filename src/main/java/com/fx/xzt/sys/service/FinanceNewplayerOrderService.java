package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: FinanceNewplayerOrderrService 
* @Description: 新手理财交易订单
* @author htt
* @date 2018-1-11 下午3:54:21 
*
 */
public interface FinanceNewplayerOrderService {
   
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param userName   用户名
	* @param orderNo    订单号
	* @param startTime  开始时间
	* @param endTime    结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param agentName 代理商用户名
	* @param brokerName 经纪人用户名
	* @param status 状态
	* @param type 类型
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByAll(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, String status, 
    		String type, String redeemStartTime, String redeemEndTime,Integer pageNum, Integer pageSize);

   /**
    * 
   * @Title: excelAll 
   * @Description: 导出
   * @param userName  用户名
   * @param orderNo  订单号
   * @param startTime  开始时间
   * @param endTime  结束时间
   * @param regStartTime  注册开始时间
   * @param regEndTime 注册结束时间
   * @param agentName 代理商用户名
   * @param brokerName 经纪人用户名
   * @param status 状态
   * @param type  类型
   * @return    设定文件 
   * @return List<Map<String,Object>>    返回类型 
   * @throws 
   * @author htt
    */
    List<Map<String, Object>> excelAll(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, String status, 
    		String type, String redeemStartTime, String redeemEndTime);

    /**
     * 
    * @Title: selectByAllCount 
    * @Description: 查询统计
    * @param userName  用户名
    * @param orderNo  订单号
    * @param startTime  开始时间
    * @param endTime  结束时间
    * @param regStartTime  注册开始时间
    * @param regEndTime 注册结束时间
    * @param agentName 代理商用户名
    * @param brokerName 经纪人用户名
    * @param status 状态
    * @param type  类型
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByAllCount(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, String status, 
    		String type, String redeemStartTime, String redeemEndTime);
    
}
