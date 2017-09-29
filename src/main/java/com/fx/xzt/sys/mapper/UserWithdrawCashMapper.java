package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UserWithdrawCash;
import com.fx.xzt.sys.model.UserWithdrawCashModel;

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
	
	/**
	 * 
	* @Title: selectByWithdrawCash 
	* @Description: 现金提取查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByWithdrawCash (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByWithdrawCashCount 
	* @Description: 现金提取查询-金额总计
	* @param map
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> selectByWithdrawCashCount (Map<String,Object> map);
}
