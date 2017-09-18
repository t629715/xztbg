package com.fx.jyg.sys.service;


import com.fx.jyg.sys.entity.UserLogin;

public interface UserLoginService extends IService<UserLogin>{
	/**
	 * 启用/禁用
	 * @param userLogin
	 * @return
	 */
	int updateByIdSelective(UserLogin userLogin);
	
}
