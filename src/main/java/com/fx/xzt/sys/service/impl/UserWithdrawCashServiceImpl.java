package com.fx.xzt.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.UserWithdrawCash;
import com.fx.xzt.sys.mapper.UserAccountRecordMapper;
import com.fx.xzt.sys.mapper.UserWithdrawCashMapper;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.UserWithdrawCashService;
import com.fx.xzt.sys.util.AccountRecordStatusEnum;
import com.fx.xzt.sys.util.UserWithdrawCashStatusEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserWithdrawCashServiceImpl extends BaseService<UserWithdrawCash> implements UserWithdrawCashService{

	@Resource
	UserWithdrawCashMapper userWithdrawCashMapper;
	
	@Resource
	UserAccountRecordMapper userAccountRecordMapper;
	
	public PageInfo<UserWithdrawCashModel> getByAll(String userName, String startTime, String endTime, String status,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", status);
		PageHelper.startPage(pageNum,pageSize);
		List<UserWithdrawCashModel> list = userWithdrawCashMapper.getByAll(map);
		for (UserWithdrawCashModel uw : list) {
			if(uw.getStatus()!=null)
			uw.setStatusName(UserWithdrawCashStatusEnum.getName(uw.getStatus()));
		}
		return new PageInfo<UserWithdrawCashModel>(list);
	}

	public int updateByIdSelective(UserWithdrawCash userWithdrawCash) {
		return userWithdrawCashMapper.updateByIdSelective(userWithdrawCash);
	}

	public List<UserWithdrawCashModel> getByAllExcel(String userName, String startTime, String endTime, String status) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", status);
		List<UserWithdrawCashModel> list = userWithdrawCashMapper.getByAll(map);
		for (UserWithdrawCashModel uw : list) {
			if(uw.getStatus()!=null)
				uw.setStatusName(UserWithdrawCashStatusEnum.getName(uw.getStatus()));
			if(uw.getWithdrawtime()!=null)
				uw.setWithdrawtimeString(sdf.format(uw.getWithdrawtime()));
			if(uw.getFinishtime()!=null)
				uw.setFinishtimeString(sdf.format(uw.getFinishtime()));
		}
		return list;
	}
	@Transactional
	public int updateByIdStatus(String withdrawid){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserWithdrawCash u = new UserWithdrawCash();
		u.setWithdrawid(withdrawid);
		u.setStatus(UserWithdrawCashStatusEnum.success.getIndex());
		u.setFinishtime(new Date());
		int msg =userWithdrawCashMapper.updateByIdSelective(u);
		//在 User_AccountRecord 添加信息
		if(msg > 0){
			UserWithdrawCash selectUWC = userWithdrawCashMapper.selectByIdKey(withdrawid);
			UserAccountRecord userAccountRecord = new UserAccountRecord();
			userAccountRecord.setUserid(selectUWC.getUserid());
			userAccountRecord.setCreatetime(sdf.format(selectUWC.getWithdrawtime()));
			userAccountRecord.setStatus(AccountRecordStatusEnum.success.getIndex());
			msg = userAccountRecordMapper.updateByIdSelective(userAccountRecord);
			if(msg <=0){
				msg = 0;
				throw new RuntimeException("更新失败"); 
			}
		}else{
			msg = -1;
		}
		return msg;
	}
	@Transactional
	public int updateWithdrawCashAndAccount(String withdrawid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("withdrawid", withdrawid);
		map.put("status", UserWithdrawCashStatusEnum.reject.getIndex());
		map.put("statusAccountRecord", AccountRecordStatusEnum.failure.getIndex());
		int msg = userWithdrawCashMapper.updateWithdrawCashAndAccount(map);
		if(msg <=0 ){
			throw new NullPointerException("更新失败"); 
		}
		return msg;
	}

	public UserWithdrawCash selectByIdKey(String withdrawid) {
		return userWithdrawCashMapper.selectByIdKey(withdrawid);
	}
	
}
