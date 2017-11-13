package com.fx.xzt.sys.service;

import java.util.List;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.github.pagehelper.PageInfo;

public interface UserAccountRecordService extends IService<UserAccountRecord>{
	/**
	 * 查询全部消费记录
	 */
	PageInfo<UserAccountRecord> getAll(String userName,String startTime,String endTime,Integer pageNum,Integer pageSize);
	/**
	 * 导出excel
	 */
	List<UserAccountRecord> getExcelAll(String userName,String startTime,String endTime);
	/**
	 * 奖励发放记录
	 */
	PageInfo<UserAccountRecord> getGameAward(String userName,String startTime,String endTime,Integer pageNum,Integer pageSize);
	/**
	 * 奖励发放记录 导出excel
	 */
	List<UserAccountRecord> getGameAwardExcel(String userName,String startTime,String endTime);
	
	/**
	 * 添加
	 */
	int add(UserAccountRecord userAccountRecord);
	/**
	 * 修改
	 */
	int updateByIdSelective(UserAccountRecord userAccountRecord);
	
	/**
	 * 
	* @Title: updateByWithdrawId 
	* @Description: 提现审核后更新用户账户记录记录状态
	* @param userAccountRecord
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int updateByWithdrawId(UserAccountRecord userAccountRecord);
}
