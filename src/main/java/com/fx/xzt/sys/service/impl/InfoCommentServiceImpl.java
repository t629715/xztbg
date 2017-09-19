package com.fx.xzt.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.InfoComment;
import com.fx.xzt.sys.mapper.InfoCommentMapper;
import com.fx.xzt.sys.model.InfoCommentModel;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.InfoCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InfoCommentServiceImpl extends BaseService<InfoComment> implements InfoCommentService {
	@Resource
	InfoCommentMapper infoCommentMapper;

	public int updateByIdSelective(InfoComment infoComment) {
		return infoCommentMapper.updateByIdSelective(infoComment);
	}

	public int deleteByIdKey(Long serialNo) {
		return infoCommentMapper.deleteByIdKey(serialNo);
	}

	public PageInfo<InfoCommentModel> getCommentAll(Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<InfoCommentModel> list = infoCommentMapper.getCommentAll();
		return new PageInfo<InfoCommentModel>(list);
	}

	public InfoCommentModel selectByIdKey(Long serialNo) {
		return infoCommentMapper.selectByIdKey(serialNo);
	}

}
