package com.fx.xzt.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.service.UserLoginService;

@Service
public class UserLoginServiceImpl extends BaseService<UserLogin> implements UserLoginService{
	
	@Resource
	UserLoginMapper userLoginMapper;

	public int updateByIdSelective(UserLogin userLogin) {
		return userLoginMapper.updateByIdSelective(userLogin);
	}
	
	
}
