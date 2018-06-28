package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: UserVoucherFinanceService 
* @Description: 加息券信息
* @author htt
* @date 2017-11-8 下午3:42:14 
*
 */
public interface UserVoucherFinanceService {
    
	/**
	 * 
	* @Title: selectByUserVoucher 
	* @Description: 加息券查询
	* @param userName   用户名
	* @param startTime  领取开始时间
	* @param endTime    领取结束时间
	* @param useStartTime 使用开始时间
	* @param useEndTime   使用结束时间
	* @param agentName    运营商用户名
	* @param brokerName   经纪人用户名
	* @param useState  使用状态
	 * @param isView 
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByUserVoucherFinance(String userName, String startTime, String endTime, String useStartTime, String useEndTime, 
    		String agentName, String brokerName, Integer useState, String isView, Integer pageNum, Integer pageSize);

   /**
    * 
   * @Title: excelUserVoucher 
   * @Description: 加息券查询导出
   * @param userName   用户名
   * @param startTime  领取开始时间
   * @param endTime    领取结束时间
   * @param useStartTime 使用开始时间
   * @param useEndTime   使用结束时间
   * @param agentName    运营商用户名
   * @param brokerName   经纪人用户名
   * @param useState  使用状态
   * @return    设定文件 
   * @return List<Map<String,Object>>    返回类型 
   * @throws 
   * @author htt
    */
    List<Map<String, Object>> excelUserVoucherFinance(String userName, String startTime, String endTime, String useStartTime, String useEndTime, 
    		String agentName, String brokerName, Integer useState, String isView);


}
