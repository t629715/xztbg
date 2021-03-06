package com.fx.xzt.sys.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.UserAccount;
import com.fx.xzt.sys.entity.UserGoldAccount;
import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.mapper.UserGoldAccountMapper;
import com.fx.xzt.sys.mapper.UserInfoMapper;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.UserInfoApproveStateEnum;
import com.fx.xzt.sys.util.UsersInfoAuthStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService{
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserLoginMapper userLoginMapper;

	@Resource
	UserGoldAccountMapper userGoldAccountMapper;

	@Resource
	UserAccountMapper userAccountMapper;

	private final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
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
	public int editUserInfo(int type,Long userId, String IDCard) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserInfo u = new UserInfo();
		u.setUserid(userId);
		u.setRealnameauthapprovetime(sdf.parse(sdf.format(new Date())));
		if(type==1){
			if (StringUtil.isNotEmpty(IDCard) && IDCard.length() == 15) {
				Integer n = Integer.parseInt(IDCard.substring(IDCard.length() - 1));
				if (n % 2 == 0) {
					u.setSex(Short.valueOf("2"));//女
				} else if (n % 2 == 1) {
					u.setSex(Short.valueOf("1"));//男
				}
			} else if (StringUtil.isNotEmpty(IDCard) && IDCard.length() == 18) {
				Integer n = Integer.parseInt(IDCard.substring(IDCard.length() - 2, IDCard.length() - 1));
				if (n % 2 == 0) {
					u.setSex(Short.valueOf("2"));//女
				} else if (n % 2 == 1) {
					u.setSex(Short.valueOf("1"));//男
				}
			}
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

	/**
	 * 账户信息
	 * @throws ParseException 
	 */
	public PageInfo<Map<String, Object>> getByAccountMessage(String userName,String agentsName, String brokerName,
			String startTime,String endTime, String isView, Integer pageNum,Integer pageSize) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		if(!StringUtils.isBlank(agentsName))	{
			String [ ] agentNames=agentsName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentsName", list);
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

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("isView", isView);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByAccountMessage(map);
		handleAccountMessage(list);
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
	public PageInfo<Map<String, Object>> getByRealNameAuth(String userName, String realName, 
			String applyTimeStart, String applyTimeEnd, String isView , Integer pageNum,
											  Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("applyTimeStart", applyTimeStart);
		map.put("applyTimeEnd", applyTimeEnd);
		map.put("isView", isView);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByRealNameAuth(map);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}
	
	/**
	 * 实名认证已审核列表
	 * @param userName 用户账号
	 * @param realName 用户姓名
	 * @param applyTimeStart 申请开始时间
	 * @param applyTimeEnd 申请结束时间
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Map<String, Object>> getByRealNameAuthApprove(String userName, String realName, String state, 
			String applyTimeStart, String applyTimeEnd, String isView , Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("state", state);
		map.put("applyTimeStart", applyTimeStart);
		map.put("applyTimeEnd", applyTimeEnd);
		map.put("isView", isView);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByRealNameAuthApprove(map);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}

	/**
	 * 导出账户信息
	 * @param userName  用户名
	 * @param brokerName 经纪人用户名
	 * @param startTime  注册开始时间
	 * @param endTime  注册结束时间
	 * @return
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> getExcelAccount(String userName, String agentsName, String brokerName, 
			String startTime, String endTime, String isView) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		if(!StringUtils.isBlank(agentsName))	{
			String [ ] agentNames=agentsName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentsName", list);
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

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("isView", isView);
		List<Map<String, Object>> list = userInfoMapper.getByAccountMessage(map);
		handleAccountMessage(list);
		return list;
	}
	
	/**
	 * 
	* @Title: handleAccountMessage 
	* @Description: 账户信息数据处理
	* @param list    设定文件 
	* @return void    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	public void handleAccountMessage(List<Map<String, Object>> list) throws ParseException {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object rmbObj = map.get("rmb");
				if (rmbObj != null && rmbObj != "") {
					Double rmb = Double.valueOf(rmbObj.toString());
					map.put("rmb", rmb/100);
				}
				Object averagePriceObj = map.get("averagePrice");
				if (averagePriceObj != null && averagePriceObj != "") {
					Double averagePrice = Double.valueOf(averagePriceObj.toString());
					map.put("averagePrice", averagePrice/100);
				}
				Object registerTimeObj = map.get("registerTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (registerTimeObj != null && registerTimeObj != "") {
					map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
				}
			}
		}
	}

	/**
	 * 获取账户信息列表金额黄金统计
	 * @return
	 */
	public Map<String,Object> getByAccountCount(String userName, String agentsName, String brokerName, 
			String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		if(!StringUtils.isBlank(agentsName))	{
			String [ ] agentNames=agentsName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentsName", list);
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

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		Map<String,Object> map1 = userInfoMapper.getByAccountCount(map);
		if (map1 != null && map1.size() > 0) {
			Object rmbSumObj = map1.get("rmbSum");
			if (rmbSumObj != null && rmbSumObj != "") {
				Double rmbSum = Double.valueOf(rmbSumObj.toString());
				map1.put("rmbSum", rmbSum/100);
			}
			DecimalFormat dFormat = new DecimalFormat("######0.00");  
			Object cbjObj = map1.get("cbj");
			if (cbjObj != null && cbjObj != "") {
				Double cbj = Double.valueOf(cbjObj.toString());
				map1.put("cbj", dFormat.format(cbj/100));
			}
		}
		return map1;
	}

	/**
	 * 获取下级客户信息
	 * @param userName
	 * @param agentName
	 * @param brokerName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Map<String,Object>> getSubClients(String userName, String agentName, String brokerName, String isView , Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
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

		map.put("isView", isView);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getSubClients(map);
		for (Map m:list){
			m.put("userId",m.get("userId").toString());
		}
		return new PageInfo<Map<String, Object>>(list);
	}

	/**
	 * 导出下级客户信息
	 * @param userName
	 * @param agentName
	 * @param brokerName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getExcelSubClientsAccount(String userName, String agentName, String brokerName, String isView) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
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

		map.put("isView", isView);
		List<Map<String, Object>> list = userInfoMapper.getSubClients(map);

		return list;
	}

	/**
	 * 获取下级客户的账户统计
	 * @param userName
	 * @param agentName
	 * @param brokerName
	 * @return
	 */
	@Override
	public Map<String, Object> getSubClientsAccountCount(String userName, String agentName, String brokerName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
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

		return userInfoMapper.getSubClientsAccountCount(map);
	}

	@Override
	public int changeBroker(Long userId, Long brokerId) {
		Map map = new HashMap();
		map.put("userId",userId);
		map.put("brokerId",brokerId);
		UserLogin userLogin = new UserLogin();
		userLogin.setUserid(userId);
		userLogin.setBrokerId(brokerId);
		userLoginMapper.updateByIdSelective(userLogin);
		return userInfoMapper.updateUserInfoBrokerId(map);
	}
	
	/**
	 * 信息变更 
	 */
	public int alertAgentAndBroker(String realName, String idcard, Long userId, Long brokerId,Long agentId) {
		int flag = 0;
		if (userId != null) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",userId);
			map.put("realName", realName);
			map.put("idcard", idcard);
			map.put("brokerId", brokerId);
			map.put("agentId", agentId);
			try{
				if (realName == null && idcard == null){
					try{
						flag = userInfoMapper.alertAgentAndBroker(map);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("变更用户归属-user_info-用户id：{}  -代理商id：{}  -经纪人id：{}，错误信息：{}",userId,agentId,brokerId,e.getMessage().toString());
						throw new GlobalException("变更用户归属","变更用户归属异常");
					}
					try{
						UserLogin userLogin = new UserLogin();
						userLogin.setUserid(userId);
						userLogin.setBrokerId(brokerId);
						userLogin.setAgentId(agentId);
						flag = userLoginMapper.updateByIdSelective(userLogin);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("变更用户归属-User_Login-用户id：{}  -代理商id：{}  -经纪人id：{}，错误信息：{}",userId,agentId,brokerId,e.getMessage().toString());
						throw new GlobalException("变更用户归属","变更用户归属异常");
					}
					try{
						UserGoldAccount userGoldAccount = new UserGoldAccount();
						userGoldAccount.setBrokerId(brokerId);
						userGoldAccount.setAgentId(agentId);
						userGoldAccount.setUserId(userId);
						flag = userGoldAccountMapper.updateOne(userGoldAccount);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("变更用户归属-user_gold_account-用户id：{}  -代理商id：{}  -经纪人id：{}，错误信息：{}",userId,agentId,brokerId,e.getMessage().toString());
						throw new GlobalException("变更用户归属","变更用户归属异常");
					}
					try{
						UserAccount userAccount = new UserAccount();
						userAccount.setId(userId);
						userAccount.setAgentId(agentId);
						userAccount.setBrokerId(brokerId);
						flag = userAccountMapper.updateUserAccount(userAccount);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("变更用户归属-user_account-用户id：{}  -代理商id：{}  -经纪人id：{}，错误信息：{}",userId,agentId,brokerId,e.getMessage().toString());
						throw new GlobalException("变更用户归属","变更用户归属异常");
					}
				}else {
					flag = userInfoMapper.modifyRealNameInfo(map);
					if (flag > 0){
						return flag;
					}
				}

			}catch (Exception e){
				logger.error("修改用户的UserInfo表信息异常-{}，参数-realName:{},idcard：{}，userId：{}，",e.getStackTrace().toString(),realName,idcard,userId);
				e.printStackTrace();
				throw new GlobalException("修改用户的UserInfo信息","修改用户的UserInfo信息异常");
			}

			if (flag > 0 && agentId != null) {
				UserLogin userLogin = new UserLogin();
				userLogin.setAgentId(agentId);
				userLogin.setBrokerId(brokerId);
				userLogin.setUserid(userId);
				try {
					flag = userLoginMapper.updateByIdSelective(userLogin);
				}catch (Exception e){
					logger.error("修改用户的UserLogin表信息异常-{}，参数-agentId:{},brokerId：{}，userId：{}",e.getStackTrace().toString(),agentId,brokerId,userId);
					e.printStackTrace();
					throw new GlobalException("修改用户的UserLogin信息","修改用户的UserLogin信息异常");
				}

				if (flag > 0){
					UserGoldAccount userGoldAccount = new UserGoldAccount();
					userGoldAccount.setUserId(userId);
					userGoldAccount.setAgentId(agentId);
					userGoldAccount.setBrokerId(brokerId);
					try{
						flag = userGoldAccountMapper.updateOneBuyUserId(userGoldAccount);
					}catch (Exception e){
						logger.error("修改用户的UserGoldAccount表信息异常-{}，参数-agentId:{},brokerId：{}，userId：{}",e.getStackTrace().toString(),agentId,brokerId,userId);
						e.printStackTrace();
						throw new GlobalException("修改用户的UserGoldAccount信息","修改用户的UserGoldAccount信息异常");
					}
				}
			}
		}
		
		return flag;
	}

	/**
	 * 用户分析查询
	 */
	@Override
	public PageInfo<Map<String, Object>> getByUserAnalysis(String startTime, String endTime, String loginFrom, String agentName,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("registerFrom", loginFrom);
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
		/*map.put("agentName", agentName);*/
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByUserAnalysis(map);
		handleUserAnalysis(list);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}
	
	/**
	 * 用户分析查询--导出
	 */
	public List<Map<String, Object>> excelByUserAnalysis(String startTime, String endTime, String loginFrom, String agentName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("registerFrom", loginFrom);
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
		//map.put("agentName", agentName);
		List<Map<String, Object>> list = userInfoMapper.getByUserAnalysis(map);
		handleUserAnalysis(list);
		return list;
	}
	
	/**
	 * 
	* @Title: handleUserAnalysis 
	* @Description: 用户分析查询数据处理
	* @param list    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void handleUserAnalysis(List<Map<String, Object>> list) {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object xrjblObj = map.get("xrjbl");
				Object xjqjyblObj = map.get("xjqjybl");
				Object xdqjblObj = map.get("xdqjbl");
				Object xcjbblObj = map.get("xcjbbl");
				
				if (xrjblObj != null && xrjblObj != "") {
                	Double xrjbl = Double.valueOf(xrjblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map.put("xrjbl", df.format(xrjbl));
                }
				if (xjqjyblObj != null && xjqjyblObj != "") {
                	Double xjqjybl = Double.valueOf(xjqjyblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map.put("xjqjybl", df.format(xjqjybl));
                }
				if (xdqjblObj != null && xdqjblObj != "") {
                	Double xdqjbl = Double.valueOf(xdqjblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map.put("xdqjbl", df.format(xdqjbl));
                }
				if (xcjbblObj != null && xcjbblObj != "") {
                	Double xcjbbl = Double.valueOf(xcjbblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map.put("xcjbbl", df.format(xcjbbl));
                }
			}
		}
	}

	/**
	 * 用户分析查询--统计
	 */
	public List<Map<String, Object>> getByUserAnalysisCount(String startTime, String endTime, String loginFrom, String agentName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("registerFrom", loginFrom);
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
		//map.put("agentName", agentName);
		List<Map<String, Object>> list = userInfoMapper.getByUserAnalysisCount(map);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map1 : list) {
				Object xrjblObj = map1.get("xrjbl");
				Object xjyblObj = map1.get("xjybl");
				
				if (xrjblObj != null && xrjblObj != "") {
                	Double xrjbl = Double.valueOf(xrjblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map1.put("xrjbl", df.format(xrjbl));
                }
				if (xjyblObj != null && xjyblObj != "") {
                	Double xjybl = Double.valueOf(xjyblObj.toString());
                	DecimalFormat df = new DecimalFormat("0.00%");
                	map1.put("xjybl", df.format(xjybl));
                }
			}
		}
		return list;
	}

	/**
	 * 用户属性查询
	 */
	public PageInfo<Map<String, Object>> getByUserAttribute(String startTime, String endTime, String loginFrom, String agentName,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("loginFrom", loginFrom);
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
		//map.put("agentName", agentName);
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = userInfoMapper.getByUserAttribute(map);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
		return pagehelper;
	}

	/**
	 * 用户属性查询--统计
	 */
	public List<Map<String, Object>> getByUserAttributeCount(String startTime, String endTime, String loginFrom, String agentName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("loginFrom", loginFrom);
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
	//	map.put("agentName", agentName);
		List<Map<String, Object>> list = userInfoMapper.getByUserAttributeCount(map);
		return list;
	}

	/**
	 * 用户属性查询--导出
	 */
	public List<Map<String, Object>> excelByUserAttribute(String startTime, String endTime, String loginFrom, String agentName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("loginFrom", loginFrom);
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
		//map.put("agentName", agentName);
		List<Map<String, Object>> list = userInfoMapper.getByUserAttribute(map);
		return list;
	}

	public List<Map<String, Object>> selectUserInfoById(Long userID) {
		return userInfoMapper.selectUserInfoById(userID);
	}
}
