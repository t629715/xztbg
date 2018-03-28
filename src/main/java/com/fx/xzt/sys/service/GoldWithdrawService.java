package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldWithdrawService 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午1:54:06 
*
 */
public interface GoldWithdrawService {

	/**
	 * 
	* @Title: selectByGoldWithdraw 
	* @Description: 黄金提取查询
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束时间
	* @param agentName   代理商账号
	* @param brokerName  经纪人账号
	* @param status  状态1:未发货2:已发货
	 * @param isView 
	* @param pageNum  页数
	* @param pageSize 条数
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
    PageInfo<Map<String, Object>> selectByGoldWithdraw(String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status, String isView, Integer pageNum, Integer pageSize);
    
    /**
     * 
    * @Title: excelGoldWithdraw 
    * @Description: 黄金提取查询-导出
    * @param userName   用户账号
    * @param startTime  申请开始时间
    * @param endTime   申请结束时间
    * @param agentName 代理商账号
    * @param brokerName  经纪人账号
    * @param status 状态1:未发货2:已发货
     * @param isView 
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelGoldWithdraw(String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status, String isView);
    
    /**
     * 
    * @Title: selectByGoldWithdrawCount 
    * @Description: 黄金提取查询--黄金、保险费、物流费用统计
    * @param userName   用户账号
    * @param startTime  申请开始时间
    * @param endTime  申请结束时间
    * @param agentName  代理商账号
    * @param brokerName  经纪人账号
    * @param status  状态1:未发货2:已发货
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByGoldWithdrawCount(String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status);
    
    /**
     * 
    * @Title: addLogisticsNoById 
    * @Description: 黄金提取-物流单号添加
    * @param logisticsNo  物流单号
    * @param status  状态
    * @param sendTime 发货时间
    * @param id
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int addLogisticsNoById(String logisticsNo, Short status, String sendTime, Long id);
    
    /**
     * 
    * @Title: updateLogisticsNoById 
    * @Description: 黄金提取-物流单号更改
    * @param logisticsNo   物流单号
    * @param updateTime 修改时间
    * @param id id
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int updateLogisticsNoById(String logisticsNo, String updateTime, Long id);

}
