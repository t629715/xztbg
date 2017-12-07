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

}
