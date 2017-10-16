package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldIncomeRecordService 
* @Description: 黄金收益记录
* @author htt
* @date 2017-9-30 下午2:17:27 
*
 */
public interface GoldIncomeRecordService {

	/**
	 * 
	* @Title: selectByGoldIncome 
	* @Description: 黄金收益记录查询
	* @param userName   用户账号
	* @param startTime  发放开始时间
	* @param endTime  发放结束时间
	* @param agentName 代理商用户名
	* @param brokerName 经纪人用户名
	* @param type 类型1：昨天；2：近7天；3：本月；4：上个月
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByGoldIncome(String userName, String startTime, String endTime, 
    		String startTypeTime, String endTypeTime, String agentName, String brokerName, 
    		Integer type, Integer pageNum, Integer pageSize);
    
    /**
     * 
    * @Title: excelGoldIncome 
    * @Description: 黄金收益记录查询-导出
    * @param userName 用户名
    * @param startTime 发放开始时间
    * @param endTime 发放结束时间
    * @param agentName 代理商用户名
    * @param brokerName 经纪人用户名
    * @param type 类型1：昨天；2：近7天；3：本月；4：上个月
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelGoldIncome(String userName, String startTime, String endTime, 
    		String startTypeTime, String endTypeTime, String agentName, String brokerName, Integer type);
    
    /**
     * 
    * @Title: selectByGoldIncomeCount 
    * @Description: 黄金收益记录--收益支出统计
    * @param userName   用户名
    * @param startTime   发放开始时间
    * @param endTime  发放结束时间
    * @param agentName 代理商用户名
    * @param brokerName 经纪人用户名
    * @param type 类型1：昨天；2：近7天；3：本月；4：上个月
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByGoldIncomeCount(String userName, String startTime, String endTime, 
    		String startTypeTime, String endTypeTime, String agentName, String brokerName, Integer type);

}
