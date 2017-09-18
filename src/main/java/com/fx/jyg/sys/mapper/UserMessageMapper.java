package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UserMessage;
import com.fx.jyg.sys.model.UserMessageModel;

@Repository
public interface UserMessageMapper extends BaseMapper<UserMessage>{
	/**
	 * 查询 全部消息
	 * @param map
	 * @return
	 */
	List<UserMessageModel> selectByAll(Map<String,Object> map);
}
