package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.entity.InfoNotice;
import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.service.InfoNoticeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoNotice")
public class InfoNoticeController {
	@Resource
	InfoNoticeService infoNoticeService;
	
	/**
	 * 添加
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(InfoNotice infoNotice,HttpSession httpSession){
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		infoNotice.setOperator(u.getPhone()+"");
		int msg = infoNoticeService.add(infoNotice);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 编辑
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(InfoNotice infoNotice,HttpSession httpSession){
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		infoNotice.setOperator(u.getPhone()+"");
		int msg = infoNoticeService.edit(infoNotice);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 删除
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoNoticeService.deleteById(serialNo);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 列表
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param operator
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/seelctAll")
	@ResponseBody
	public PageInfo<InfoNotice> seelctAll(String title, String startTime, String endTime, String operator,
			Integer pageNum, Integer pageSize){
		return infoNoticeService.getInfoNoticeAll(title, startTime, endTime, operator, pageNum, pageSize);
	}
	/**
	 * 
	 */
	@RequestMapping(value="/upDown")
	@ResponseBody
	public Map<String,Object> upDown(Long upSerialNo, Long downSerialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoNoticeService.upDown(upSerialNo, downSerialNo);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public InfoNotice selectBySerialNo(Long serialNo){
		return infoNoticeService.getBySerialNo(serialNo);
	}
}
