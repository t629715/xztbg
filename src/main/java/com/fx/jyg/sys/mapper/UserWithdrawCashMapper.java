package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.jyg.sys.entity.UserWithdrawCash;
import com.fx.jyg.sys.model.UserWithdrawCashModel;

public interface UserWithdrawCashMapper extends BaseMapper<UserWithdrawCash>{
	/**
	 * 更新
	 * @param userWithdrawCash
	 * @return
	 */
	int updateByIdSelective(UserWithdrawCash userWithdrawCash);
	/**
	 * 获取出金集合
	 */
	List<UserWithdrawCashModel> getByAll(Map<String,Object> map);
	/**
	 * 拒绝  退回 然后 在Account中余额加入金额
	 */
	int updateWithdrawCashAndAccount(Map<String,Object> map);
	
	/**
	 * 根据id查询
	 */
	UserWithdrawCash selectByIdKey(String withdrawid);
}
