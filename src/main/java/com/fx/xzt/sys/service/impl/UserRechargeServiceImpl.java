package com.fx.xzt.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.mapper.UserRechargeMapper;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.fx.xzt.sys.service.UserRechargeService;
import com.fx.xzt.sys.util.RechargeChannelEnum;
import com.fx.xzt.sys.util.RechargeStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserRechargeServiceImpl extends BaseService<UserRecharge> implements UserRechargeService{
	@Resource
	UserRechargeMapper userRechargeMapper;

	public PageInfo<UserRechargeModel> getAll(String username, String rechargeid, String merchantordernum, String startTime,
			String endTime, String rechargechannel, Short status, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("rechargeid", rechargeid);
		map.put("merchantordernum", merchantordernum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("rechargechannel", rechargechannel);
		map.put("status", status);
		PageHelper.startPage(pageNum,pageSize);
		List<UserRechargeModel> list = userRechargeMapper.getByAll(map);
		for(UserRechargeModel m : list){
			if(!StringUtils.isBlank(m.getRechargechannel()))
			m.setRechargeChannelName(RechargeChannelEnum.getName(m.getRechargechannel()));
			if(m.getStatus()!=null)
			m.setStatusName(RechargeStatus.getName(m.getStatus()));
		}
		return new PageInfo<UserRechargeModel>(list);
	}

	public List<UserRechargeModel> getExcelAll(String username, String rechargeid,
			String merchantordernum, String startTime, String endTime, String rechargechannel, Short status) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("rechargeid", rechargeid);
		map.put("merchantordernum", merchantordernum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("rechargechannel", rechargechannel);
		map.put("status", status);
		List<UserRechargeModel> list = userRechargeMapper.getByAll(map);
		for(UserRechargeModel m : list){
			if(!StringUtils.isBlank(m.getRechargechannel()))
			m.setRechargeChannelName(RechargeChannelEnum.getName(m.getRechargechannel()));
			if(m.getStatus()!=null)
			m.setStatusName(RechargeStatus.getName(m.getStatus()));
			if(m.getRechargetime()!=null)
			m.setFormatRechargetime(sdf.format(m.getRechargetime()));
		}
		return list;
	}

	/**
	 * 现金充值记录查询
	 */
	public PageInfo<Map<String, Object>> selectByRecharge(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status, String isView ,
			Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
        map.put("status", status);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userRechargeMapper.selectByRecharge(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 现金充值记录导出
	 */
	public List<Map<String, Object>> excelRecharge(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status, String isView ) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
        map.put("status", status);
        map.put("isView", isView);
        List<Map<String, Object>> list = userRechargeMapper.selectByRecharge(map);
        return list;
	}

	/**
	 * 现金充值查询-统计
	 */
	public Map<String, Object> selectByRechargeCount(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
        map.put("status", status);
        return userRechargeMapper.selectByRechargeCount(map);
	}

	
}
