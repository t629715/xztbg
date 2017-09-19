package com.fx.xzt.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserLoginInfo;
import com.fx.xzt.sys.mapper.UserLoginInfoMapper;
import com.fx.xzt.sys.service.UserLoginInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
* @Title: UserLoginInfoServiceImpl.java 
* @Package com.fx.xzt.sys.service.impl
* @Description: TODO
* @author SYan  
* @date 2017年8月11日 下午2:19:04 
* @version V1.0
 */
@Service
public class UserLoginInfoServiceImpl extends BaseService<UserLoginInfo> implements UserLoginInfoService{
	@Resource
	UserLoginInfoMapper userLoginInfoMapper;

	public PageInfo<UserLoginInfo> getfindAll(String userName,String loginFrom,String startTime,String endTime,Integer pageNum,Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("loginFrom", loginFrom);
		if(!StringUtils.isBlank(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isBlank(startTime)){
			map.put("endTime", endTime);
		}
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		PageHelper.startPage(pageNum,pageSize);
		List<UserLoginInfo> list = userLoginInfoMapper.getFindList(map);
		PageInfo<UserLoginInfo> pagehelper = new PageInfo<UserLoginInfo>(list);
		return pagehelper;
	}
}
