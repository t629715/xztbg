package com.fx.jyg.sys.service;

import java.util.List;

import com.fx.jyg.sys.entity.UserWithdrawCash;
import com.fx.jyg.sys.model.UserWithdrawCashModel;
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
	
	
}
