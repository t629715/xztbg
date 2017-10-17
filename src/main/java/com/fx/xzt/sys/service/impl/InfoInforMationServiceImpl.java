package com.fx.xzt.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fx.xzt.sys.entity.InfoInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.mapper.InfoInforMationMapper;
import com.fx.xzt.sys.service.InfoInforMationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
* @Title: InfoInforMationServiceImpl.java 
* @Package com.fx.xzt.sys.service.impl
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 下午5:12:02 
* @version V1.0
 */
@Service
public class InfoInforMationServiceImpl extends BaseService<InfoInformation> implements InfoInforMationService{
	
	@Resource
	InfoInforMationMapper infoInforMationMapper;
	/**
	 * 获取信息发布列表
	 */
	public PageInfo<Map<String, Object>> getByAll(String title, String startTime, String endTime,
											  Integer state, String operator, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("state", state);
		map.put("operator", operator);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = infoInforMationMapper.getByAll(map);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}
	/**
	 *  
	 *  内容没有添加
	 *  
	 */
	@Transactional
	public int posted(InfoInformation i) {
		i.setReleasetime(new Date());
		i.setCreatetime(new Date());
		i.setState((short)1);
		//i.setTopState((short)0);
		return infoInforMationMapper.posted(i);
	}
	public Map<String, Object> getById(Long serialNo) {
		return infoInforMationMapper.getById(serialNo);
	}
	@Transactional
	public int edit(InfoInformation i) {

		return infoInforMationMapper.edit(i);
	}
	@Transactional
	public int deleteById(Long serialNo) {
		return infoInforMationMapper.deleteById(serialNo);
	}
	@Transactional
	public int editTopState(InfoInformation i) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("off", 0);
		map.put("stick", 1);
		int msg = infoInforMationMapper.editTopState(map);
		/*if(i.getTopState()!=1){
			i.setTopState((short)1);
			msg = edit(i);
		}*/
		return msg;
	}
	
}
