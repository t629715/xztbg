package com.fx.jyg.sys.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.InfoComment;
import com.fx.jyg.sys.model.InfoCommentModel;

@Repository
public interface InfoCommentMapper extends BaseMapper<InfoComment>{
	int updateByIdSelective(InfoComment infoComment);
	int deleteByIdKey(Long serialNo);
	/**
	 * 获取评论集合
	 * @return
	 */
	List<InfoCommentModel> getCommentAll();
	InfoCommentModel selectByIdKey(Long serialNo);
}
