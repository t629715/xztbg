package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.InfoComment;
import com.fx.xzt.sys.model.InfoCommentModel;
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
