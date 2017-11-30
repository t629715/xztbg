package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.mapper.UserMessageMapper;
import com.fx.xzt.sys.model.UserMessageModel;
import com.fx.xzt.sys.service.UserMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserMessageServiceImpl extends BaseService<UserMessage> implements UserMessageService{
	
	@Resource
	UserMessageMapper userMessageMapper;
	
	public PageInfo<UserMessageModel> selectByAll(Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		PageHelper.startPage(pageNum,pageSize);
		List<UserMessageModel> list = userMessageMapper.selectByAll(map);
		return new PageInfo<UserMessageModel>(list);
	}

	/**
	 * 新增
	 */
	public int add(UserMessage userMessage) {
		return userMessageMapper.add(userMessage);
	}

}
