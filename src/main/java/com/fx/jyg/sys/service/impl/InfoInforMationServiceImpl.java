package com.fx.jyg.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.jyg.sys.entity.InfoInforMation;
import com.fx.jyg.sys.mapper.InfoInforMationMapper;
import com.fx.jyg.sys.service.InfoInforMationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
* @Title: InfoInforMationServiceImpl.java 
* @Package com.fx.jyg.sys.service.impl 
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 下午5:12:02 
* @version V1.0
 */
@Service
public class InfoInforMationServiceImpl extends BaseService<InfoInforMation> implements InfoInforMationService{
	
	@Resource
	InfoInforMationMapper infoInforMationMapper;
	/**
	 * 获取信息发布列表
	 */
	public PageInfo<InfoInforMation> getByAll(String title, String startTime, String endTime,
			Integer state, String operator, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("state", state);
		map.put("operator", operator);
		PageHelper.startPage(pageNum,pageSize);
		List<InfoInforMation> list = infoInforMationMapper.getByAll(map);
		PageInfo<InfoInforMation> pagehelper = new PageInfo<InfoInforMation>(list);
		return pagehelper;
	}
	/**
	 *  
	 *  内容没有添加
	 *  
	 */
	@Transactional
	public int posted(InfoInforMation i) {
		i.setReleasetime(new Date());
		i.setCreatetime(new Date());
		i.setState((short)1);
		i.setTopState((short)0);
		return infoInforMationMapper.posted(i);
	}
	public InfoInforMation getById(Long serialNo) {
		return infoInforMationMapper.getById(serialNo);
	}
	@Transactional
	public int edit(InfoInforMation i) {
		return infoInforMationMapper.edit(i);
	}
	@Transactional
	public int deleteById(Long serialNo) {
		return infoInforMationMapper.deleteById(serialNo);
	}
	@Transactional
	public int editTopState(InfoInforMation i) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("off", 0);
		map.put("stick", 1);
		int msg = infoInforMationMapper.editTopState(map);
		if(i.getTopState()!=1){
			i.setTopState((short)1);
			msg = edit(i);
		}
		return msg;
	}
	
}
