package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.entity.Users;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.service.UserLoginService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
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
	 * 实名认证操作
	 * @param type 1 通过  -1 不通过
	 * @param userId
	 * @return 1成功 0失败
	 * @throws Exception 
	 */
	@RequestMapping(value="/certification")
	@ResponseBody
	public Object certification(@RequestParam  Integer type,@RequestParam  Long userId, @RequestParam String IDCard) throws Exception{
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
		cr.setMsg("操作失败！");
		try {
			if (type > 0 && userId > 0) {
				int flag = userInfoService.editUserInfo(type, userId, IDCard);
				if (flag > 0) {
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
					cr.setMsg("操作成功！");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return cr;
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
	public Object selectByRegisterMessage(String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentName, String brokerName, String attribution, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		CommonResponse cr = new CommonResponse();
		try {
			PageInfo<Map<String, Object>> pageInfo = userLoginService.getByRegisterMessage(userName, startTime, endTime, registerFrom, registerIp,
					lastStartTime, lastEndTime, lastLoginFrom, agentName, brokerName, attribution, pageNum, pageSize);
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			cr.setData(pageInfo);
			cr.setMsg("操作成功！");
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return cr;
	}
	
	/**
	 * 
	* @Title: getByAttributionPro 
	* @Description: 注册信息查询-归属地获取
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByAttributionPro")
	@ResponseBody
	public Object getByAttributionPro() {
		CommonResponse cr = new CommonResponse();
		try {
			List<Map<String, Object>> list = userLoginService.getByAttributionPro();
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			cr.setData(list);
			cr.setMsg("操作成功！");
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return cr;
	}

	/**
	 * 更新注册信息-状态
	 * @param status
	 * @param userId
	 * @return 1成功 0失败
	 */
	@RequestMapping(value="/updateRegisterStatusById")
	@ResponseBody
	public Object updateRegisterStatusById(@RequestParam  Short status,@RequestParam  Long userId){
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
		cr.setMsg("操作失败！");
		try {
			if (status > 0 && userId > 0) {
				UserLogin u = new UserLogin();
				u.setStatus(status);
				u.setUserid(userId);
				int flag = userLoginService.updateByIdSelective(u);
				if (flag > 0) {
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
					cr.setMsg("操作成功！");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return cr;
	}

	/**
	 * 导出excel--注册信息
	 */
	@RequestMapping(value="/excelRegisterMessage")
	@ResponseBody
	public void excelRegisterMessage(HttpServletRequest request, HttpServletResponse response,String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentsName, String brokerName, String attribution){
		List<Map<String, Object>> list = userLoginService.getExcelByRegister(userName,startTime,endTime,registerFrom,registerIp,lastStartTime,
				lastEndTime,lastLoginFrom,agentsName,brokerName,attribution);
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> u : list) {
				String name = ConstantUtil.userStatus.toMap().get(u.get("Status").toString());
				u.put("Status", name);
			}
		}
		POIUtils poi = new POIUtils();
		String[] heads = {"用户账号","代理商","经纪人","注册时间","注册来源","注册IP","归属地","最后一次登录时间","最后一次登录方式","最后一次登录IP","状态"};
		String[] colums = {"UserName","agentsName","brokerName","RegisterTime","RegisterFrom","RegisterIp","attribution","lastlogintime","lastloginfrom","lastfromIp","Status"};
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
	public Object selectByRealNameAuth(String userName, String realName, String applyTimeStart, String applyTimeEnd, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
		CommonResponse cr = new CommonResponse();
		try {
			PageInfo<Map<String, Object>> pageInfo = userInfoService.getByRealNameAuth(userName, realName, applyTimeStart, applyTimeEnd, pageNum, pageSize);
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			cr.setData(pageInfo);
			cr.setMsg("操作成功！");
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return cr;
	}

	/**
	 * 账户信息
	 */
	@RequestMapping(value="/selectByAccountMessage")
	@ResponseBody
	public Object selectByAccountMessage(String userName,String agentsName, String brokerName,String startTime,String endTime,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
		CommonResponse cr = new CommonResponse();
		try {
			PageInfo<Map<String, Object>> pageInfo = userInfoService.getByAccountMessage(userName, agentsName, brokerName, startTime, endTime, pageNum, pageSize);
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			cr.setData(pageInfo);
			cr.setMsg("操作成功！");
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return cr;
	}

	/**
	 * 导出excel--账户信息
	 */
	@RequestMapping(value="/excelAccountMessage")
	@ResponseBody
	public void excelAccountMessage(HttpServletRequest request, HttpServletResponse response,String userName,String agentName, String brokerName,String startTime,String endTime){
		List<Map<String, Object>> list = userInfoService.getExcelAccount(userName,agentName, brokerName,startTime,endTime);
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
	public String selectAccountCount(String userName,String agentName, String brokerName,String startTime,String endTime){
		CommonResponse cr = new CommonResponse();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map = userInfoService.getByAccountCount(userName,agentName, brokerName,startTime,endTime);
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			cr.setData(map);
			cr.setMsg("操作成功！");
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return JSON.toJSONString(cr);
	}

	@RequestMapping(value="/selectSubClients")
	@ResponseBody
	public Object selectSubClients(HttpServletRequest request,String userName,String agentsName, String brokerName,String startTime,String endTime,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
		CommonResponse cr = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null){
				agentsName = users.getUserName();
				PageInfo<Map<String, Object>> pageInfo = userInfoService.getSubClients(userName, agentsName, brokerName, pageNum, pageSize);
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				cr.setData(pageInfo);
				cr.setMsg("操作成功！");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return cr;
	}
	/**
	 * 导出excel--账户信息
	 */
	@RequestMapping(value="/excelSubClients")
	@ResponseBody
	public void excelSubClients(HttpServletRequest request, HttpServletResponse response,String userName,String agentName, String brokerName,String startTime,String endTime){
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		if (users != null){
			agentName = users.getUserName();
			List<Map<String, Object>> list = userInfoService.getExcelSubClientsAccount(userName,agentName, brokerName);
			POIUtils poi = new POIUtils();
			String[] heads = {"用户账号","代理商","经纪人","人民币余额","人民币冻结","人民币理财","利息","黄金"};
			String[] colums = {"userName","agentName","brokerName","rmb","frozenRmb","finance","totalIncome","gold"};
			poi.doExport(request, response, list, "账户信息", "账户信息", heads, colums);
		}

	}

	/**
	 * 下级客户-金额统计
	 * @param userName
	 * @param agentName
	 * @param brokerName
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value="/selectSubClientsCount")
	@ResponseBody
	public String selectSubClientsCount(HttpServletRequest request, String userName,String agentName, String brokerName,String startTime,String endTime){
		CommonResponse cr = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null){
				agentName = users.getUserName();
				Map<String,Object> map = new HashMap<String,Object>();
				map = userInfoService.getSubClientsAccountCount(userName,agentName, brokerName);
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				cr.setData(map);
				cr.setMsg("操作成功！");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		return JSON.toJSONString(cr);
	}


	/**
	 * 变更经纪人
	 */
	@RequestMapping(value="/cheageBroker")
	@ResponseBody
	 public CommonResponse cheageBroker(HttpServletRequest request,String userId,Long  brokerId){
	 	CommonResponse response = new CommonResponse();
	 	try{
	 		HttpSession session = request.getSession();
	 		Users users = (Users)session.getAttribute("currentUser");
	 		if (users != null){
	 			int msg = userInfoService.changeBroker(Long.valueOf(userId),brokerId);
	 			if (msg>0){
	 				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更成功");
	 			}else{
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更失败");
				}
			}
		}catch (Exception e){
	 		e.printStackTrace();
		}
		return response;
	 }
}
