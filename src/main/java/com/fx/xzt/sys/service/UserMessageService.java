package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.model.UserMessageModel;
import com.github.pagehelper.PageInfo;

public interface UserMessageService extends IService<UserMessage>{
	/**
	 * 查询 全部消息
	 * @param map
	 * @return
	 */
	PageInfo<UserMessageModel> selectByAll(Integer pageNum, Integer pageSize);
	
	/**
	 * 
	* @Title: add 
	* @Description: 新增
	* @param userMessage
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int add(UserMessage userMessage);
}
