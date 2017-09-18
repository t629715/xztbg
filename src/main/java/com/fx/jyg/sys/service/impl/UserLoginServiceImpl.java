package com.fx.jyg.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.UserLogin;
import com.fx.jyg.sys.mapper.UserLoginMapper;
import com.fx.jyg.sys.service.UserLoginService;

@Service
public class UserLoginServiceImpl extends BaseService<UserLogin> implements UserLoginService{
	
	@Resource
	UserLoginMapper userLoginMapper;

	public int updateByIdSelective(UserLogin userLogin) {
		return userLoginMapper.updateByIdSelective(userLogin);
	}
	
	
}
