package com.fx.xzt.sys.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserAccount;

/**
 * 
* @ClassName: UserAccountMapper 
* @Description: 用户账户余额
* @author htt
* @date 2017-11-7 下午4:22:49 
*
 */
@Repository
public interface UserAccountMapper extends BaseMapper<UserAccount> {
	
	/**
	 * 
	* @Title: updateByGoldReedm 
	* @Description: 黄金赎回成功更改账户余额
	* @param map
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int updateByGoldReedm(Map<String, Object> map);
	
	/**
	 * 
	* @Title: updateByWithdrawCashNoPass 
	* @Description: 提现拒绝更改账户余额
	* @param map
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int updateByWithdrawCashNoPass(Map<String, Object> map);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 12:24
	 * @Description：更新操作锁表  行级锁
	 * @param userId
	 * @return
	 */
	UserAccount lockUserAccount(Long userId);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 12:35
	 * @Description：充值后修改账户余额
	 * @param userAccount
	 * @return
	 */
	Integer updateForRecharge(UserAccount userAccount);

}
