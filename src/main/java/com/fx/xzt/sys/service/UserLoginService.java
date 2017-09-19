package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.UserLogin;

public interface UserLoginService extends IService<UserLogin>{
	/**
	 * 启用/禁用
	 * @param userLogin
	 * @return
	 */
	int updateByIdSelective(UserLogin userLogin);
	
}
