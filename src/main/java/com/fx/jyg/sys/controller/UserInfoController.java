package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.model.UserInfoModel;
import com.fx.jyg.sys.service.UserInfoService;
import com.github.pagehelper.PageInfo;


/**
 * 
* @Title: UserInfoController.java 
* @Package com.fx.jyg.sys.controller 
* @author SYan  
* @date 2017年8月11日 下午5:48:52 
* @version V1.0
 */
@Controller
@RequestMapping(value="/userInfo")
public class UserInfoController {
	
	@Resource
	UserInfoService userInfoService;
	
	/**
	 * 获取认证集合
	 */
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserInfoModel> selectByAll(String userName,String realName,Integer authStatic,String iDCard,String applyTimeStart,String applyTimeEnd,String approveTimeStart,String approveTimeEnd,Integer pageNum,Integer pageSize){
		return userInfoService.getfindAll(userName, realName, authStatic, iDCard, applyTimeStart, applyTimeEnd, approveTimeStart, approveTimeEnd, pageNum, pageSize);
	}
	/**
	 * 认证
	 * @param type 1 通过  2 不通过
	 * @param userId
	 * @return 1成功 0失败
	 */
	@RequestMapping(value="/certification")
	@ResponseBody
	public Map<String,Object> certification(Integer type,Long userId){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userInfoService.editUserInfo(type, userId);
		map.put("msg", msg);
		return map;
	}
	
	
	/**
	 * 优顾认证
	 */
	@RequestMapping(value="/certificationYG")
	@ResponseBody
	public Map<String,Object> certificationYG(Integer type,Long userId){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userInfoService.editYGUserInfo(type, userId);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 账户信息
	 */
	@RequestMapping(value="/selectByAccountMessage")
	@ResponseBody
	public PageInfo<UserInfoModel> selectByAccountMessage(String userName,String status,String realName,String accountNum,String phone,String startTime,String endTime,String registerFrom,Integer pageNum,Integer pageSize){
		return userInfoService.getByAccountMessage(userName, status, realName, accountNum, phone, startTime, endTime, registerFrom, pageNum, pageSize);
	}
}
