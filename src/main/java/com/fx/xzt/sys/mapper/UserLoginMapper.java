package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.model.UserLoginModel;
import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserLogin;

import java.util.List;
import java.util.Map;

/**
 * 
* @Title: UserLoginMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月21日 下午4:16:44 
* @version V1.0
 */
@Repository
public interface UserLoginMapper extends BaseMapper<UserLogin>{
	int updateByIdSelective(UserLogin userLogin);

	List<UserLoginModel> getByRegisterMessage(Map<String,Object> map);

}
