package com.fx.xzt.sys.service;
import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UserLogin;
import com.github.pagehelper.PageInfo;


public interface UserLoginService extends IService<UserLogin>{
	/**
	 * 启用/禁用
	 * @param userLogin
	 * @return
	 */
	int updateByIdSelective(UserLogin userLogin);

	/**
	 * 获取注册信息列表
	 * @param userName   用户账号
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param registerFrom 注册来源
	 * @param registerIp 注册ip
	 * @param lastStartTime 最后一次登录时间
	 * @param lastEndTime 最后一次登录时间
	 * @param lastLoginFrom 最后一次登录来源
	 * @param agentsName 代理商用户名
	 * @param brokerName 经纪人用户名
	 * @param attribution 归属地
	 * @param isView 是否允许查看全部用户名；0：否；1：是
	 * @return
	 */
	PageInfo<Map<String, Object>> getByRegisterMessage(String userName, String startTime, String endTime,
									   String registerFrom, String registerIp, String lastStartTime,
									   String lastEndTime, String lastLoginFrom, String agentName,
									   String brokerName, String attribution, String isView, Integer pageNum,
									   Integer pageSize);

	/**
	 * 导出注册信息列表
	 * @param userName   用户账号
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param registerFrom 注册来源
	 * @param registerIp 注册ip
	 * @param lastStartTime 最后一次登录时间
	 * @param lastEndTime 最后一次登录时间
	 * @param lastLoginFrom 最后一次登录来源
	 * @param agentsName 代理商用户名
	 * @param brokerName 经纪人用户名
	 * @param attribution 归属地
	 * @param isView 是否允许查看全部用户名；0：否；1：是
	 * @return
	 */
	List<Map<String, Object>> getExcelByRegister(String userName, String startTime, String endTime,
													String registerFrom, String registerIp, String lastStartTime,
													String lastEndTime, String lastLoginFrom,
													String agentsName, String brokerName, String attribution, String isView);
	
	/**
	 * 
	* @Title: getByAccount 
	* @Description: 根据用户名获取用户账户信息
	* @param userName
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByAccount(String userName);
	
	/**
	 * 
	* @Title: getByAttributionPro 
	* @Description: 注册信息查询-归属地获取
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByAttributionPro();
	
}
