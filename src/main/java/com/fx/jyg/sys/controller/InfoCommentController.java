package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.entity.InfoComment;
import com.fx.jyg.sys.model.InfoCommentModel;
import com.fx.jyg.sys.service.InfoCommentService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="infoComment")
public class InfoCommentController {
	@Resource
	InfoCommentService infoCommentService;
	
	/**
	 * 更新 评论 只更新 内容 和 状态
	 * @param infoComment
	 * @return
	 */
	@RequestMapping(value="/updateComment")
	@ResponseBody
	public Map<String,Object> updateComment(InfoComment infoComment){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoCommentService.updateByIdSelective(infoComment);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 * @param infoComment
	 * @return
	 */
	@RequestMapping(value="/deleteComment")
	@ResponseBody
	public Map<String,Object> deleteComment(Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoCommentService.deleteByIdKey(serialNo);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 查询评论列表
	 */
	@RequestMapping(value="/selectCommentAll")
	@ResponseBody
	public PageInfo<InfoCommentModel> selectCommentAll(Integer pageNum,
			Integer pageSize){
		return infoCommentService.getCommentAll(pageNum, pageSize);
	}
}
