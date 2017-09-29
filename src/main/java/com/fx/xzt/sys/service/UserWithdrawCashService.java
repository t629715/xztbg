package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UserWithdrawCash;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.github.pagehelper.PageInfo;

public interface UserWithdrawCashService extends IService<UserWithdrawCash>{
	PageInfo<UserWithdrawCashModel> getByAll(String userName,String startTime,String endTime,String status,Integer pageNum,Integer pageSize);
	/**
	 * 全局更新
	 * @param userWithdrawCash
	 * @return
	 */
	int updateByIdSelective(UserWithdrawCash userWithdrawCash);
	/**
	 * 导出Excel
	 * @param userName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	List<UserWithdrawCashModel> getByAllExcel(String userName,String startTime,String endTime,String status);
	/**
	 * 提现
	 */
	int updateByIdStatus(String withdrawid);
	/**
	 * 拒绝  退回 然后 在Account中余额加入金额
	 */
	int updateWithdrawCashAndAccount(String withdrawid);
	/**
	 * 根据id查询
	 */
	UserWithdrawCash selectByIdKey(String withdrawid);
	
	/**
	 * 
	* @Title: selectByWithdrawCash 
	* @Description: 现金提取查询
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束时间
	* @param agentName  代理商用户名
	* @param brokerName 经纪人用户名
	* @param status   状态 0：审核中 1：已完成
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByWithdrawCash (String userName, String startTime, String endTime, 
			String agentName, String brokerName, Integer status ,Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: excelWithdrawCash 
	* @Description: 现金提取--导出
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束时间
	* @param agentName  代理商用户名
	* @param brokerName 经纪人用户名
	* @param status 状态 0：审核中 1：已完成
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> excelWithdrawCash (String userName, String startTime, String endTime, 
			String agentName, String brokerName, Integer status);
	
	/**
	 * 
	* @Title: selectByWithdrawCashCount 
	* @Description: 现金提取查询--金额统计
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束是时间
	* @param agentName  代理商用户名
	* @param brokerName  经纪人用户名
	* @param status  状态 0：审核中 1：已完成
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> selectByWithdrawCashCount (String userName, String startTime, String endTime, 
			String agentName, String brokerName, Integer status);
	
	
}
