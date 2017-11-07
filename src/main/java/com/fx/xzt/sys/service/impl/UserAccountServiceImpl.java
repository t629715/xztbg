package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.UserAccount;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.service.UserAccountService;

/**
 * 
* @ClassName: GoldWithdrawServiceImpl 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午2:04:58 
*
 */
@Service
public class UserAccountServiceImpl extends BaseService<UserAccount> implements  UserAccountService{
	
	@Resource
	UserAccountMapper userAccountMapper;

	/**
	 * 黄金赎回成功更改账户余额
	 */
	@Transactional
	public int updateByGoldReedm(Long rmb, Long id) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("id", id);
		map.put("rmb", rmb);
		return userAccountMapper.updateByGoldReedm(map);
	}


}
