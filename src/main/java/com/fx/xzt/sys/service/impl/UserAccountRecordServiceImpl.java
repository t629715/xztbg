package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.mapper.UserAccountRecordMapper;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.sys.util.AccountRecordActionEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserAccountRecordServiceImpl extends BaseService<UserAccountRecord> implements UserAccountRecordService{
	
	@Resource
	UserAccountRecordMapper userAccountRecordMapper;

	public PageInfo<UserAccountRecord> getAll(String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum,pageSize);
		List<UserAccountRecord> list = userAccountRecordMapper.getAll(map);
		for(UserAccountRecord u : list){
			if(!StringUtils.isBlank(u.getAction()))
				u.setAction(AccountRecordActionEnum.getName(u.getAction()));
		}
		return new PageInfo<UserAccountRecord>(list);
	}

	public List<UserAccountRecord> getExcelAll(String userName, String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<UserAccountRecord> list = userAccountRecordMapper.getAll(map);
		for(UserAccountRecord u : list){
			if(!StringUtils.isBlank(u.getAction()))
				u.setAction(AccountRecordActionEnum.getName(u.getAction()));
		/*	if(u.getCreatetime()!=null)
				u.setCreatetime(sdf.format(u.getCreatetime()));*/
		}
		return list;
	}

	public PageInfo<UserAccountRecord> getGameAward(String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum,pageSize);
		List<UserAccountRecord> list = userAccountRecordMapper.getGameAward(map);
		for(UserAccountRecord u : list){
			if(!StringUtils.isBlank(u.getAction()))
				u.setAction(AccountRecordActionEnum.getName(u.getAction()));
		}
		return new PageInfo<UserAccountRecord>(list);
	}

	public List<UserAccountRecord> getGameAwardExcel(String userName, String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<UserAccountRecord> list = userAccountRecordMapper.getGameAward(map);
		for(UserAccountRecord u : list){
			if(!StringUtils.isBlank(u.getAction()))
				u.setAction(AccountRecordActionEnum.getName(u.getAction()));
		}
		return list;
	}

	public int add(UserAccountRecord userAccountRecord) {
		return userAccountRecordMapper.add(userAccountRecord);
	}

	public int updateByIdSelective(UserAccountRecord userAccountRecord) {
		return userAccountRecordMapper.updateByIdSelective(userAccountRecord);
	}
	
	
}
