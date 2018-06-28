package com.fx.xzt.sys.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.util.CommonResponse;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: SaveGoldRecordService 
* @Description: 存金记录
* @author htt
* @date 2018-5-29 下午2:29:33 
*
 */
public interface SaveGoldRecordService {
	
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
	* @param type 类型1：买入；4：卖出
	* @param isView 
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type,
    		String isView, Integer pageNum, Integer pageSize) throws ParseException;
	
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
	* @param type 类型1：买入；4：卖出
	* @param isView
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type,
    		String isView) throws ParseException;
	
	/**
	 * 
	* @Title: countByAll 
	* @Description: 统计
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param type 类型1：买入；4：卖出
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> countByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/11 11:06
	 * @Description：存金宝买入总金额
	 * @return
	 */
	CommonResponse countBuyGold();

}
