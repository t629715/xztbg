package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

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
}
