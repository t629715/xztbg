package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.UserLoginInfo;
import com.fx.xzt.sys.service.UserLoginInfoService;
import com.fx.xzt.sys.service.UserLoginService;
import com.github.pagehelper.PageInfo;

/**
 * 
* @Title: UserLoginInfoController.java 
* @Package com.fx.xzt.sys.controller
* @Description: TODO
* @author SYan  
* @date 2017年8月10日 下午3:59:42 
* @version V1.0
 */
@Controller
@RequestMapping("/userLoginInfo")
public class UserLoginInfoController {
	
	@Resource
	UserLoginInfoService userLoginInfoService;
	
	@Resource
	UserLoginService userLoginService;
	
	/**
	 * 获取登陆信息
	 * @param userName
	 * @param loginFrom
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectByAll")
	@ResponseBody
	public PageInfo<UserLoginInfo> selectByAll(String userName,String loginFrom,String startTime,String endTime,Integer pageNum,Integer pageSize){
		return userLoginInfoService.getfindAll(userName,loginFrom,startTime,endTime,pageNum,pageSize);
	}
	
	/**
	 * 修改账号状态  启用/禁用
	 */
	@RequestMapping("/updateLoginStatus")
	@ResponseBody
	public Map<String,Object> updateLoginStatus(UserLogin userLogin){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userLoginService.updateByIdSelective(userLogin);
		map.put("msg", msg);
		return map;
	}
}
