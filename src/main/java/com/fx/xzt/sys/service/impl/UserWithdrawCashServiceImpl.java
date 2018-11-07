package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.UserWithdrawCash;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.mapper.UserAccountRecordMapper;
import com.fx.xzt.sys.mapper.UserInfoMapper;
import com.fx.xzt.sys.mapper.UserWithdrawCashMapper;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.UserWithdrawCashService;
import com.fx.xzt.sys.util.AccountRecordStatusEnum;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.UserWithdrawCashStatusEnum;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserWithdrawCashServiceImpl extends BaseService<UserWithdrawCash> implements UserWithdrawCashService{

	@Resource
	UserWithdrawCashMapper userWithdrawCashMapper;
	
	@Resource
	UserAccountRecordMapper userAccountRecordMapper;
	@Resource
	UserAccountMapper userAccountMapper;
	@Resource
	UserInfoMapper userInfoMapper;
	
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
			/*userAccountRecord.setUserid(selectUWC.getUserid());
			userAccountRecord.setCreatetime(sdf.format(selectUWC.getWithdrawtime()));
			userAccountRecord.setStatus(AccountRecordStatusEnum.success.getIndex());*/
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

	/**
	 * 现金提取查询
	 */
	public PageInfo<Map<String, Object>> selectByWithdrawCash(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("status", status);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userWithdrawCashMapper.selectByWithdrawCash(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 现金提取--导出
	 */
	public List<Map<String, Object>> excelWithdrawCash(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("status", status);
        map.put("isView", isView);
        List<Map<String, Object>> list = userWithdrawCashMapper.selectByWithdrawCash(map);
        return list;
	}

	/**
	 * 现金提取查询--金额统计
	 */
	public Map<String, Object> selectByWithdrawCashCount(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
        /*map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("status", status);
		return userWithdrawCashMapper.selectByWithdrawCashCount(map);
	}
	
	/**
	 * 现金提取--审核通过
	 */
	@Transactional
	public int auditPassedById(String withdrawid) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserWithdrawCash u = new UserWithdrawCash();
		u.setWithdrawid(withdrawid);
		u.setStatus(Short.decode(ConstantUtil.withdrawCashStatus.YWC.toString()));
		u.setFinishtime(sdf.parse(sdf.format(new Date())));
		int msg = userWithdrawCashMapper.updateByIdSelective(u);
		return msg;
	}

	/**
	 * 现金提取--审核不通过
	 */
	@Transactional
	public int auditNoPassedById(String withdrawid) throws ParseException {
		//更新提现状态
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserWithdrawCash u = new UserWithdrawCash();
		u.setWithdrawid(withdrawid);
		u.setStatus(Short.decode(ConstantUtil.withdrawCashStatus.SHBTG.toString()));
		u.setFinishtime(sdf.parse(sdf.format(new Date())));
		int flag1 = userWithdrawCashMapper.updateByIdSelective(u);
		//新增账户记录
		UserWithdrawCash withdrawCash = userWithdrawCashMapper.selectByIdKey(withdrawid);
		UserAccountRecord record = new UserAccountRecord();
		if (withdrawCash.getUserid() != null) {
			List<Map<String, Object>> list = userInfoMapper.selectUserInfoById(withdrawCash.getUserid());
			if (list != null && list.size() == 1) {
				Map<String, Object> map = list.get(0);
				if (map.get("agentId") != null) {
					record.setAgentId(Long.valueOf(map.get("agentId").toString()));
				}
				if (map.get("brokerId") != null) {
					record.setBrokerId(Long.getLong(map.get("brokerId").toString()));
				}
			}
		}
		record.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
		record.setUserId(withdrawCash.getUserid());
		record.setUserName(withdrawCash.getUsername());
		record.setSide(ConstantUtil.USER_ACCOUNT_RECORD_SIDE_J);
		record.setAction(ConstantUtil.USER_ACCOUNT_RECORD_ACTION_TXJJFX);
		record.setStatus(ConstantUtil.USER_ACCOUNT_RECORD_STATUS_YSH);
		record.setRmb(withdrawCash.getWithdrawamt());
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		record.setDescription("提现拒绝返现");
		int flag2 = userAccountRecordMapper.add(record);
		//更新账户余额
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("user_id", withdrawCash.getUserid());
		map.put("rmb", withdrawCash.getWithdrawamt());
		int flag3 = userAccountMapper.updateByWithdrawCashNoPass(map);
		if (flag1 > 0 && flag2 >0 && flag3 > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
