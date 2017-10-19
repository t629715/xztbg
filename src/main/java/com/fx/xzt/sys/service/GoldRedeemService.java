package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldRedeemService 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:40:41 
*
 */
public interface GoldRedeemService {
	
	/**
	 * 
	* @Title: selectByGoldRedeem 
	* @Description: 黄金赎回查询
	* @param userName    用户账号
	* @param startTime   赎回开始时间
	* @param endTime   赎回结束时间
	* @param channelName  渠道商用户账号
	* @param pageNum  页数
	* @param pageSize  条数
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByGoldRedeem(String userName, String startTime, String endTime, 
    		String channelName, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelGoldRedeem 
	* @Description: 黄金赎回查询--导出
	* @param userName   用户账号
	* @param startTime  赎回开始时间
	* @param endTime  赎回结束时间
	* @param channelName  渠道商用户名
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelGoldRedeem(String userName, String startTime, String endTime, String channelName);
	
	/**
	 * 
	* @Title: selectByGoldRedeemCount 
	* @Description: 黄金赎回查询--黄金克重统计
	* @param userName   用户账号
	* @param startTime 赎回开始时间
	* @param endTime  赎回结束时间
	* @param channelName  渠道商用户账号
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> selectByGoldRedeemCount(String userName, String startTime, String endTime, String channelName);

}
