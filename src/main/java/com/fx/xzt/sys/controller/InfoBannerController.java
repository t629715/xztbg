package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/insertBanner",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertBanner(InfoBanner infoBanner){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.add(infoBanner);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 编辑
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(InfoBanner infoBanner){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.edit(infoBanner);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 删除
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoBannerService.deleteById(serialNo);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 修改图片显示的顺序
	 * @param upSerialNo 交换的上一条
	 * @param downSerialNo 交换的另一条
	 * @return
	 */
	@RequestMapping(value="/upDown",method=RequestMethod.POST)
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
	@RequestMapping(value="/selectByPageAll",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<InfoBanner> selectByPageAll(Integer page,Integer pageNum,Integer pageSize){
		return infoBannerService.getByPageAll(page, pageNum, pageSize);
	}

	/**
	 * 获取广告图片  tianliya
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getAdPic",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAdPic(Short page){
		return infoBannerService.getAdPic(page);
	}



}
