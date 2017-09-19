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

	
}
