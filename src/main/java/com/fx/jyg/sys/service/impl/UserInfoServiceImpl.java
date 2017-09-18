package com.fx.jyg.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.UserInfo;
import com.fx.jyg.sys.mapper.UserInfoMapper;
import com.fx.jyg.sys.model.UserInfoModel;
import com.fx.jyg.sys.service.UserInfoService;
import com.fx.jyg.sys.util.UserInfoApproveStateEnum;
import com.fx.jyg.sys.util.UsersInfoAuthStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService{
	@Resource
	UserInfoMapper userInfoMapper;
	
	public PageInfo<UserInfoModel> getfindAll(String userName, String realName, Integer authStatic, String iDCard,
			String applyTimeStart, String applyTimeEnd, String approveTimeStart, String approveTimeEnd, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("authStatic", authStatic);
		map.put("iDCard", iDCard);
		map.put("applyTimeStart", applyTimeStart);
		map.put("applyTimeEnd", applyTimeEnd);
		map.put("approveTimeStart", approveTimeStart);
		map.put("approveTimeEnd", approveTimeEnd);
		PageHelper.startPage(pageNum,pageSize);
		List<UserInfoModel> list = userInfoMapper.getByAll(map);
		PageInfo<UserInfoModel> pagehelper = new PageInfo<UserInfoModel>(list);
		return pagehelper;
	}

	public int editUserInfo(int type,Long userId) {
		UserInfo u = new UserInfo();
		u.setUserid(userId);
		u.setRealnameauthapprovetime(new Date());
		if(type==1){
			u.setRealnameauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStatePass.getIndex());
			u.setRealnameauthstatus((short)UsersInfoAuthStatus.AuthstatusPass.getIndex());
		}else if(type==0){
			u.setRealnameauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStateNoPass.getIndex());
			u.setRealnameauthstatus((short)UsersInfoAuthStatus.AuthstatusNoPass.getIndex());
			u.setIdcard("");
			u.setIdcardpath("");
			u.setRealname("");
			u.setIDCardBackPath("");
		}else{
			return 0;
		}
		return userInfoMapper.editUserInfo(u);
	}
	
	//以下是 优顾认证
	
	public int editYGUserInfo(int type, Long userId) {
		UserInfo u = new UserInfo();
		u.setUserid(userId);
		u.setCertificateauthapprovetime(new Date());
		if(type==1){
			u.setCertificateauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStatePass.getIndex());
			u.setCertificateauthstatus((short)UsersInfoAuthStatus.AuthstatusPass.getIndex());
		}else if(type==0){
			u.setCertificateauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStateNoPass.getIndex());
			u.setCertificateauthstatus((short)UsersInfoAuthStatus.AuthstatusNoPass.getIndex());
			u.setCertificatepath("");
		}else{
			return 0;
		}
		return userInfoMapper.editUserInfo(u);
	}

	public PageInfo<UserInfoModel> getByAccountMessage(String userName, String status, String realName,
			String accountNum, String phone, String startTime, String endTime, String registerFrom, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("status", status);
		map.put("realName", realName);
		map.put("accountNum", accountNum);
		map.put("phone", phone);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("registerFrom", registerFrom);
		PageHelper.startPage(pageNum,pageSize);
		List<UserInfoModel> list = userInfoMapper.getByAccountMessage(map);
		return new PageInfo<UserInfoModel>(list);
	}
}
