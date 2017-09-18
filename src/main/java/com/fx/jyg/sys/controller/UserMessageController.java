package com.fx.jyg.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.model.UserMessageModel;
import com.fx.jyg.sys.service.UserMessageService;
import com.github.pagehelper.PageInfo;

/**
 * 后台消息列表
* @Title: UserMessageController.java 
* @Package com.fx.jyg.sys.controller 
* @Description: TODO
* @author SYan  
* @date 2017年8月24日 上午10:12:33 
* @version V1.0
 */
@Controller
@RequestMapping(value="/userMessage")
public class UserMessageController {
	@Resource
	UserMessageService userMessageService;
	
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserMessageModel> selectByAll(Integer pageNum, Integer pageSize){
		return userMessageService.selectByAll(pageNum, pageSize);
	}
}
