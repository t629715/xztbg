package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.model.UserLoginModel;
import com.fx.xzt.sys.service.UserLoginService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.UserInfoService;
import com.github.pagehelper.PageInfo;


/**
 * 
* @Title: UserInfoController.java 
* @Package com.fx.xzt.sys.controller
* @author SYan  
* @date 2017年8月11日 下午5:48:52 
* @version V1.0
 */
@Controller
@RequestMapping(value="/userInfo")
public class UserInfoController {
	
	@Resource
	UserInfoService userInfoService;
	@Resource
	UserLoginService userLoginService;
	
	/**
	 * 获取认证集合
	 */
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserInfoModel> selectByAll(String userName,String realName,String applyTimeStart,String applyTimeEnd,Integer pageNum,Integer pageSize){
		return userInfoService.getfindAll(userName, realName, applyTimeStart, applyTimeEnd, pageNum, pageSize);
	}
	/**
	 * 认证
	 * @param type 1 通过  0 不通过
	 * @param userId
	 * @return 1成功 0失败
	 */
	@RequestMapping(value="/certification")
	@ResponseBody
	public Map<String,Object> certification(Integer type,Long userId){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userInfoService.editUserInfo(type, userId);
		map.put("msg", msg);
		return map;
	}
	
	
	/**
	 * 优顾认证
	 */
	@RequestMapping(value="/certificationYG")
	@ResponseBody
	public Map<String,Object> certificationYG(Integer type,Long userId){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userInfoService.editYGUserInfo(type, userId);
		map.put("msg", msg);
		return map;
	}
	
	/*----------htt-----------*/
	/**
	 * 注册信息
	 */
	@RequestMapping(value="/selectByRegisterMessage")
	@ResponseBody
	public PageInfo<UserLoginModel> selectByRegisterMessage(String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentsName, String brokerName, String attribution, Integer pageNum, Integer pageSize) {
		return userLoginService.getByRegisterMessage(userName, startTime, endTime, registerFrom, registerIp,
				lastStartTime, lastEndTime, lastLoginFrom, agentsName, brokerName, attribution, pageNum, pageSize);
	}

	/**
	 * 导出excel--注册信息
	 */
	@RequestMapping(value="/excelRegisterMessage")
	@ResponseBody
	public void excelRegisterMessage(HttpServletRequest request, HttpServletResponse response,String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentsName, String brokerName, String attribution){
		List<UserLoginModel> list = userLoginService.getExcelByRegister(userName,startTime,endTime,registerFrom,registerIp,lastStartTime,
				lastEndTime,lastLoginFrom,agentsName,brokerName,attribution);
		if (list != null && !list.isEmpty()) {
			for (UserLoginModel u : list) {
				if (u.getStatus() != null) {
					String statusName = ConstantUtil.userStatus.toMap().get(u.getStatus());
					u.setStatusName(statusName);
				}
			}
		}
		POIUtils poi = new POIUtils();
		String[] heads = {"用户账号","代理商","经纪人","注册时间","注册来源","注册IP","归属地","最后一次登录时间","最后一次登录方式","最后一次登录IP","状态"};
		String[] colums = {"username","agentsName","brokerName","registerTime","registerFrom","registerIp","attribution","lastLoginTime","lastLoginFrom","lastFromIp","statusName"};
		poi.doExport(request, response, list, "注册信息", "注册信息", heads, colums);
	}

	/**
	 * 实名认证查询
	 * @param userName
	 * @param realName
	 * @param applyTimeStart
	 * @param applyTimeEnd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/selectByRealNameAuth")
	@ResponseBody
	public PageInfo<UserInfoModel> selectByRealNameAuth(String userName,String realName,String applyTimeStart,String applyTimeEnd,Integer pageNum,Integer pageSize){
		return userInfoService.getByRealNameAuth(userName, realName, applyTimeStart, applyTimeEnd, pageNum, pageSize);
	}

	/**
	 * 账户信息
	 */
	@RequestMapping(value="/selectByAccountMessage")
	@ResponseBody
	public PageInfo<UserInfoModel> selectByAccountMessage(String userName,String agentsName, String brokerName,String startTime,String endTime,Integer pageNum,Integer pageSize){
		return userInfoService.getByAccountMessage(userName, agentsName, brokerName, startTime, endTime, pageNum, pageSize);
	}

	/**
	 * 导出excel--账户信息
	 */
	@RequestMapping(value="/excelAccountMessage")
	@ResponseBody
	public void excelAccountMessage(HttpServletRequest request, HttpServletResponse response,String userName,String agentName, String brokerName,String startTime,String endTime){
		List<UserInfoModel> list = userInfoService.getExcelAccount(userName,agentName, brokerName,startTime,endTime);
		POIUtils poi = new POIUtils();
		String[] heads = {"用户账号","姓名","注册时间","代理商","经纪人","身份证号","银行卡","人民币余额","人民币冻结","人民币理财","利息","黄金"};
		String[] colums = {"userName","realname","registertime","agentName","brokerName","idcard","accountNum","rmb","frozenRmb","finance","totalIncome","gold"};
		poi.doExport(request, response, list, "账户信息", "账户信息", heads, colums);
	}

	/**
	 * 获取账户信息列表--金额统计
	 * @return
	 */
	@RequestMapping(value="/selectAccountCount")
	@ResponseBody
	public Map<String,Object> selectAccountCount(){
		Map<String,Object> map = new HashMap<String,Object>();
		map = userInfoService.getByAccountCount();
		return map;
	}
}
