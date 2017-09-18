package com.fx.jyg.sys.service;


import com.fx.jyg.sys.entity.InfoComment;
import com.fx.jyg.sys.model.InfoCommentModel;
import com.github.pagehelper.PageInfo;

public interface InfoCommentService extends IService<InfoComment>{
	int updateByIdSelective(InfoComment infoComment);
	int deleteByIdKey(Long serialNo);
	/**
	 * 获取评论集合
	 * @return
	 */
	PageInfo<InfoCommentModel> getCommentAll(Integer pageNum,
			Integer pageSize);
	InfoCommentModel selectByIdKey(Long serialNo);
}
