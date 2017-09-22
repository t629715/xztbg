package com.fx.xzt.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.mapper.UserInfoMapper;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.util.UserInfoApproveStateEnum;
import com.fx.xzt.sys.util.UsersInfoAuthStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService{
	@Resource
	UserInfoMapper userInfoMapper;
	
	public PageInfo<UserInfoModel> getfindAll(String userName, String realName, String applyTimeStart, String applyTimeEnd, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("applyTimeStart", applyTimeStart);
		map.put("applyTimeEnd", applyTimeEnd);
		//map.put("realNameAuthApproveState", 0);
		PageHelper.startPage(pageNum,pageSize);
		List<UserInfoModel> list = userInfoMapper.getByAll(map);
		PageInfo<UserInfoModel> pagehelper = new PageInfo<UserInfoModel>(list);
		return pagehelper;
	}

	@Transactional
	public int editUserInfo(int type,Long userId) {
		UserInfo u = new UserInfo();
		u.setUserid(userId);
		u.setRealnameauthapprovetime(new Date());
		if(type==1){
			u.setRealnameauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStatePass.getIndex());
			u.setRealnameauthstatus((short)UsersInfoAuthStatus.AuthstatusPass.getIndex());
		}else if(type==-1){
			u.setRealnameauthapprovestate((short)UserInfoApproveStateEnum.AuthApproveStateNoPass.getIndex());
			u.setRealnameauthstatus((short)UsersInfoAuthStatus.AuthstatusNoPass.getIndex());
			u.setIdcard("");
			u.setIdcardpath("");
			u.setRealname("");
			u.setIdcardbackpath("");
		}else{
			return 0;
		}
		return userInfoMapper.editUserInfo(u);
	}
	
	//以下是 优顾认证----注释htt
	
	public int editYGUserInfo(int type, Long userId) {
		/*UserInfo u = new UserInfo();
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
		return userInfoMapper.editUserInfo(u);*/
		return -1;
	}

	public PageInfo<Map<String, Object>> getByAccountMessage(String userName,String agentsName, String brokerName,String startTime,String endTime,Integer pageNum,Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("agentsName", agentsName);
		map.put("brokerName", brokerName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByAccountMessage(map);
		return new PageInfo<Map<String, Object>>(list);
	}

	/**
	 * 实名认证列表
	 * @param userName 用户账号
	 * @param realName 用户姓名
	 * @param applyTimeStart 申请开始时间
	 * @param applyTimeEnd 申请结束时间
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Map<String, Object>> getByRealNameAuth(String userName, String realName, String applyTimeStart, String applyTimeEnd, Integer pageNum,
											  Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("applyTimeStart", applyTimeStart);
		map.put("applyTimeEnd", applyTimeEnd);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByRealNameAuth(map);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}

	/**
	 * 导出账户信息
	 * @param userName  用户名
	 * @param agentName 代理商用户名
	 * @param brokerName 经纪人用户名
	 * @param startTime  注册开始时间
	 * @param endTime  注册结束时间
	 * @return
	 */
	public List<Map<String, Object>> getExcelAccount(String userName, String agentName, String brokerName, String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("agentName", agentName);
		map.put("brokerName", brokerName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<Map<String, Object>> list = userInfoMapper.getByAccountMessage(map);
		return list;
	}

	/**
	 * 获取账户信息列表金额黄金统计
	 * @return
	 */
	public Map<String,Object> getByAccountCount() {
		return userInfoMapper.getByAccountCount();
	}
}
