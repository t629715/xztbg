package com.fx.jyg.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.service.UsersService;

@Controller
@RequestMapping("/redis")
public class RedisController {
	
	@Resource
	private UsersService userService;
	
	@ResponseBody
	@RequestMapping(value="/userInfo",method=RequestMethod.GET)
	public String getUserInfo(@RequestParam Long uid){
		Users userInfo = userService.getUserInfo(uid);
		if(userInfo!=null){
			return "您要获取的用户名称是："+userInfo.getUserName();
		}
		return "获取用户信息失败";
	}

}
