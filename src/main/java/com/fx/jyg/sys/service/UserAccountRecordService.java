package com.fx.jyg.sys.service;

import java.util.List;

import com.fx.jyg.sys.entity.UserAccountRecord;
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
}
