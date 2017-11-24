package com.fx.xzt.sys.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.service.UserLoginService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.StringUtil;
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
			if (type != 0 && userId > 0) {
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
	public Object updateRegisterStatusById(@RequestParam  Short status,@RequestParam  String userId){
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
		cr.setMsg("操作失败！");
		try {
			if (status != null && StringUtil.isNotEmpty(userId)) {
				UserLogin u = new UserLogin();
				u.setStatus(status);
				u.setUserid(Long.parseLong(userId));
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
		String[] heads = {"用户账号","代理商","经纪人","注册时间","注册来源","注册IP","归属地省","归属地市","最后一次登录时间","最后一次登录方式","最后一次登录IP","状态"};
		String[] colums = {"UserName","agentsName","brokerName","RegisterTime","RegisterFrom","RegisterIp","attributionProvince",
				"attribution","lastlogintime","lastloginfrom","lastfromip","Status"};
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
	public void excelAccountMessage(HttpServletRequest request, HttpServletResponse response,String userName,String agentsName, String brokerName,String startTime,String endTime){
		List<Map<String, Object>> list = userInfoService.getExcelAccount(userName,agentsName, brokerName,startTime,endTime);
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
	public String selectAccountCount(String userName,String agentsName, String brokerName,String startTime,String endTime){
		CommonResponse cr = new CommonResponse();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map = userInfoService.getByAccountCount(userName,agentsName, brokerName,startTime,endTime);
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
	
	/**
	 * 
	* @Title: getByUserAnalysis 
	* @Description: 用户分析查询
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom  登录来源
	* @param agentName 代理商id
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByUserAnalysis")
	@ResponseBody
	public Object getByUserAnalysis(HttpServletRequest request, String type, String startTime, String endTime, String loginFrom, String agentName,
			 @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
		CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
                PageInfo<Map<String, Object>> pageInfo = userInfoService.getByUserAnalysis(sTime, eTime, loginFrom, agentName, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
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
	 * 
	* @Title: getByUserAnalysisCount 
	* @Description: 用户分析查询--统计
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom  登录来源
	* @param agentName 代理商id
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByUserAnalysisCount")
	@ResponseBody
	public Object getByUserAnalysisCount(HttpServletRequest request, String type, String startTime, String endTime, String loginFrom, String agentName) throws Exception {
		CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
                List<Map<String, Object>> list = userInfoService.getByUserAnalysisCount(sTime, eTime, loginFrom, agentName);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
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
	 * 
	* @Title: excelByUserAnalysis 
	* @Description: 用户分析查询--导出
	* @param request
	* @param response
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom 来源
	* @param agentName 代理商id
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value = "/excelByUserAnalysis")
	@ResponseBody
	public void excelByUserAnalysis(HttpServletRequest request, HttpServletResponse response, String type, String startTime, String endTime,
			String loginFrom, String agentName) throws Exception {
		try {
			String tieleName = "用户分析";
			String excelName = "用户分析";
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
			if (users != null) {
				if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
				List<Map<String, Object>> list = userInfoService.excelByUserAnalysis(sTime, eTime, loginFrom, agentName);
				if (list != null && list.size() > 0) {
					for (Map<String, Object> map : list) {
						Object xrjblObj = map.get("xrjbl");
						Object xjqjyblObj = map.get("xjqjybl");
						Object xhjwzblObj = map.get("xhjwzbl");
						Object xsycblObj = map.get("xsycbl");
						Object xswhjblObj = map.get("xswhjbl");
						
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
						if (xhjwzblObj != null && xhjwzblObj != "") {
	                    	Double xhjwzbl = Double.valueOf(xhjwzblObj.toString());
	                    	DecimalFormat df = new DecimalFormat("0.00%");
	                    	map.put("xhjwzbl", df.format(xhjwzbl));
	                    }
						if (xsycblObj != null && xsycblObj != "") {
	                    	Double xsycbl = Double.valueOf(xsycblObj.toString());
	                    	DecimalFormat df = new DecimalFormat("0.00%");
	                    	map.put("xsycbl", df.format(xsycbl));
	                    }
						if (xswhjblObj != null && xswhjblObj != "") {
	                    	Double xswhjbl = Double.valueOf(xswhjblObj.toString());
	                    	DecimalFormat df = new DecimalFormat("0.00%");
	                    	map.put("xswhjbl", df.format(xswhjbl));
	                    }
					}
				}
				POIUtils poi = new POIUtils();
				String[] heads = { "日期", "合计入金用户", "新入金用户", "新入金用户比例", 
						"金权交易合计交易用户", "金权交易新交易用户", "金权交易新交易用户比例", "黄金稳赚合计交易用户",
						"黄金稳赚新交易用户", "黄金稳赚新交易用户比例", "实物黄金合计交易用户", "实物黄金新交易用户",
						"实物黄金新交易用户比例", "随意存合计交易用户", "随意存新交易用户", "随意存新交易用户比例",
						"新注册用户", "总用户", "总入金用户" };
				String[] colums = { "date", "hjrj", "xrj", "xrjbl",
						"hjjqjy", "xjqjy", "xjqjybl", "hjhjwz",
						"xhjwz", "xhjwzbl", "hjswhj", "xswhj",
						"xswhjbl", "hjsyc", "xsyc", "xsycbl",
						"xzc", "zzc", "zrj" };
				poi.doExport(request, response, list, tieleName, excelName,
						heads, colums);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* @Title: getByUserAttribute 
	* @Description: 用户属性查询
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom  登录来源
	* @param agentName 代理商id
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByUserAttribute")
	@ResponseBody
	public Object getByUserAttribute(HttpServletRequest request, String type, String startTime, String endTime, String loginFrom, String agentName,
			 @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
		CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
                PageInfo<Map<String, Object>> pageInfo = userInfoService.getByUserAttribute(sTime, eTime, loginFrom, agentName, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
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
	 * 
	* @Title: getByUserAttributeCount 
	* @Description: 用户属性查询--统计
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom  登录来源
	* @param agentName 代理商id
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByUserAttributeCount")
	@ResponseBody
	public Object getByUserAttributeCount(HttpServletRequest request, String type, String startTime, String endTime, String loginFrom, String agentName) throws Exception {
		CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
                List<Map<String, Object>> list = userInfoService.getByUserAttributeCount(sTime, eTime, loginFrom, agentName);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
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
	 * 
	* @Title: excelByUserAttribute 
	* @Description: 用户属性查询--导出
	* @param request
	* @param response
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param loginFrom 来源
	* @param agentName 代理商id
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value = "/excelByUserAttribute")
	@ResponseBody
	public void excelByUserAttribute(HttpServletRequest request, HttpServletResponse response, String type, String startTime, String endTime,
			String loginFrom, String agentName) throws Exception {
		try {
			String tieleName = "用户分析";
			String excelName = "用户分析";
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
			if (users != null) {
				if (type != null && type.equals(ConstantUtil.USER_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.USER_NPER_JSST)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -29, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
            			eTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            	}
				List<Map<String, Object>> list = userInfoService.excelByUserAttribute(sTime, eTime, loginFrom, agentName);
				POIUtils poi = new POIUtils();
				String[] heads = { "日期", "登录用户", "总用户", "男", "女", "性别未确定", "苹果用户", "安卓用户", "其他" };
				String[] colums = { "date", "dlNum", "zNum", "nNum", "nvNum", "wqdNum", "pgNum", "azNum", "qtNum" };
				poi.doExport(request, response, list, tieleName, excelName,
						heads, colums);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
