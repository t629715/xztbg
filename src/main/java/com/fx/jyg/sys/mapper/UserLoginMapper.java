package com.fx.jyg.sys.mapper;


import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UserLogin;

/**
 * 
* @Title: UserLoginMapper.java 
* @Package com.fx.jyg.sys.mapper 
* @Description: TODO
* @author SYan  
* @date 2017年8月21日 下午4:16:44 
* @version V1.0
 */
@Repository
public interface UserLoginMapper extends BaseMapper<UserLogin>{
	int updateByIdSelective(UserLogin userLogin);
}
