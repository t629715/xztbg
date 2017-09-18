package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UserLoginInfo;
/**
 * 
* @Title: UserLoginInfoMapper.java 
* @Package com.fx.jyg.sys.mapper 
* @Description: TODO
* @author SYan  
* @date 2017年8月10日 下午2:35:15 
* @version V1.0
 */
@Repository
public interface UserLoginInfoMapper extends BaseMapper<UserLoginInfo>{
	List<UserLoginInfo> getFindList(Map<String,Object> map);
}
