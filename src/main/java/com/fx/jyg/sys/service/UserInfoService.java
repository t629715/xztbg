package com.fx.jyg.sys.service;

import com.fx.jyg.sys.entity.UserInfo;
import com.fx.jyg.sys.model.UserInfoModel;
import com.github.pagehelper.PageInfo;

/**
 * 
* @Title: UserInfoService.java 
* @Package com.fx.jyg.sys.service 
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 上午9:16:23 
* @version V1.0
 */
public interface UserInfoService extends IService<UserInfo>{
	/**
	 * 获取实名认证列表
	 * @param userName 账号
	 * @param realName 真实姓名
	 * @param authStatic 认证状态
	 * @param iDCard 身份证号
	 * @param applyTimeStart 申请开始时间
	 * @param applyTimeEnd
	 * @param approveTimeStart 审核开始时间
	 * @param approveTimeEnd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<UserInfoModel> getfindAll(String userName,String realName,Integer authStatic,String iDCard,String applyTimeStart,String applyTimeEnd,String approveTimeStart,String approveTimeEnd,Integer pageNum,Integer pageSize);
	/**
	 * 实名认证审核
	 * @param type 修改的类型 1 通过 0 不通过
	 * @return
	 */
	int editUserInfo(int type,Long userId);
	
	//以下是 优顾认证 type 修改的类型 1 通过 0 不通过
	
	int editYGUserInfo(int type,Long userId);
	/**
	 * 账户信息列表 
	 */
	PageInfo<UserInfoModel> getByAccountMessage(String userName,String status,String realName,String accountNum,String phone,String startTime,String endTime,String registerFrom,Integer pageNum,Integer pageSize);
}
