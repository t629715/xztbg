package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.model.UserMessageModel;

@Repository
public interface UserMessageMapper extends BaseMapper<UserMessage>{
	/**
	 * 查询 全部消息
	 * @param map
	 * @return
	 */
	List<UserMessageModel> selectByAll(Map<String,Object> map);
	
	int add(UserMessage userMessage);
}
