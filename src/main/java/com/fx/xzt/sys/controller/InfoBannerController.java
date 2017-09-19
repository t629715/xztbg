package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.InfoBanner;
import com.fx.xzt.sys.service.InfoBannerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoBanner")
public class InfoBannerController {
	
	@Resource
	InfoBannerService infoBannerService;
	
	/**
	 * 添加
	 * @param infoInforMation
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/insertBanner")
	@ResponseBody
	public Map<String,Object> insertBanner(InfoBanner infoBanner){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.add(infoBanner);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 编辑
	 * @param infoInforMation
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(InfoBanner infoBanner){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.edit(infoBanner);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 * @param infoInforMation
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.deleteById(serialNo);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 向上向下
	 */
	@RequestMapping(value="/upDown")
	@ResponseBody
	public Map<String,Object> upDown(Long upSerialNo,Long downSerialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.up(upSerialNo, downSerialNo);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 根据page查询列表
	 */
	@RequestMapping(value="/selectByPageAll")
	@ResponseBody
	public PageInfo<InfoBanner> selectByPageAll(Integer page,Integer pageNum,Integer pageSize){
		return infoBannerService.getByPageAll(page, pageNum, pageSize);
	}
}
