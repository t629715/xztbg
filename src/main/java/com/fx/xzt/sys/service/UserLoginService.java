package com.fx.xzt.sys.service;
import com.github.pagehelper.PageInfo;


import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.model.UserLoginModel;

import java.util.List;


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
	 * @return
	 */
	PageInfo<UserLoginModel> getByRegisterMessage(String userName, String startTime, String endTime,
											  String registerFrom, String registerIp, String lastStartTime,
											  String lastEndTime, String lastLoginFrom, String agentsName,
											  String brokerName, String attribution, Integer pageNum,
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
	 * @return
	 */
	List<UserLoginModel> getExcelByRegister(String userName, String startTime, String endTime,
													String registerFrom, String registerIp, String lastStartTime,
													String lastEndTime, String lastLoginFrom,
													String agentsName, String brokerName, String attribution);
	
}
