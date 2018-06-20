package com.fx.xzt.sys.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.entity.ConfigParam;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.service.ConfigParamService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.service.UserLoginService;
import com.fx.xzt.sys.service.UserMessageService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.IdUtil;
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
	@Resource
    LogRecordService logRecordService;
	@Resource
	ConfigParamService configParamService;
	@Resource
	UserMessageService userMessageService;
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
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
	public Object certification(HttpServletRequest request, @RequestParam  Integer type,@RequestParam  Long userId, @RequestParam String IDCard) throws Exception{
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
		cr.setMsg("操作失败！");
		
		//系统消息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserMessage message = new UserMessage();
		message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
		message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
		message.setMsgTime(sdf.parse(sdf.format(new Date())));
		message.setUserID(userId);
		
		//操作日志
        LogRecord log = new LogRecord();
        log.setTitle("实名认证审核");
        log.setContent("审核失败");
        log.setModuleName(ConstantUtil.logRecordModule.SMRZ.getName());
        log.setType(ConstantUtil.logRecordType.SH.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
			 if (users != null) {
				 if (type != 0 && userId > 0) {
						int flag = userInfoService.editUserInfo(type, userId, IDCard);
						if (flag > 0) {
							cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
							cr.setMsg("操作成功！");
							//系统消息
							if (type == 1) {
								message.setMsgContent("您的资料审核已通过！");
							} else if (type == -1) {
								message.setMsgContent("您的资料审核未通过，上传信息不符合标准，请重新上传！");
							}
							userMessageService.add(message);
							//操作日志
							log.setUserId(users.getId());
			                log.setContent("审核成功；信息：userId：" + userId + ";;type:" + type + ";;IDCard:" + IDCard);
						}
					}
			 } else {
				 cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
	             cr.setData("{}");
	             cr.setMsg("无操作权限！");
			 }
		} catch (Exception e) {
			throw e;
		}
		logRecordService.add(log);
        AuditLog.info(log.toString());
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
	 * @throws ParseException 
	 */
	@RequestMapping(value="/selectByRegisterMessage")
	@ResponseBody
	public Object selectByRegisterMessage(HttpServletRequest request, String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentName, String brokerName, String attribution, String attributionProvince, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
		CommonResponse cr = new CommonResponse();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("注册信息查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZCXX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
			 if (users != null) {
				 String isView = "0";
		         if (role != null && role.get("roleIsView") != null) {
		              isView = role.get("roleIsView").toString();
		          }
				  PageInfo<Map<String, Object>> pageInfo = userLoginService.getByRegisterMessage(userName, startTime, endTime, registerFrom, registerIp,
							lastStartTime, lastEndTime, lastLoginFrom, agentName, brokerName, attribution, isView, pageNum, pageSize);
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					cr.setData(pageInfo);
					cr.setMsg("操作成功！");
	                log.setUserId(users.getId());
	                log.setContent("查询成功");
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
		logRecordService.add(log);
        AuditLog.info(log.toString());
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
	 * @throws ParseException 
	 */
	@RequestMapping(value="/updateRegisterStatusById")
	@ResponseBody
	public Object updateRegisterStatusById(HttpServletRequest request, @RequestParam  Short status,@RequestParam  String userId) throws ParseException{
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
		cr.setMsg("操作失败！");
		
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("注册信息状态修改");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZCXX.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
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
                log.setUserId(users.getId());
                log.setContent("修改成功；信息：ID:" + userId + ";;status:" + status);
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
			
		} catch (Exception e) {
			throw e;
		}
		logRecordService.add(log);
        AuditLog.info(log.toString());
		return cr;
	}

	/**
	 * 导出excel--注册信息
	 * @throws ParseException 
	 */
	@RequestMapping(value="/excelRegisterMessage")
	@ResponseBody
	public void excelRegisterMessage(HttpServletRequest request, HttpServletResponse response,String userName, String startTime, String endTime,
		String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
		String agentName, String brokerName, String attribution) throws ParseException{
		
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("注册成信息导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZCXX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "0";
		         if (role != null && role.get("roleIsView") != null) {
		              isView = role.get("roleIsView").toString();
		          }
            	List<Map<String, Object>> list = userLoginService.getExcelByRegister(userName,startTime,endTime,registerFrom,registerIp,lastStartTime,
        				lastEndTime,lastLoginFrom,agentName,brokerName,attribution, isView);
        		if (list != null && !list.isEmpty()) {
        			for (Map<String, Object> u : list) {
        				String name = ConstantUtil.userStatus.toMap().get(u.get("Status").toString());
        				u.put("Status", name);
        				
        				Object RegisterTimeObj = u.get("RegisterTime");
        				if (RegisterTimeObj != null && RegisterTimeObj != "") {
        					u.put("RegisterTime", sdf.format(sdf.parse(RegisterTimeObj.toString())));
        				}
        				
        				Object lastlogintimeObj = u.get("lastlogintime");
        				if (lastlogintimeObj != null && lastlogintimeObj != "") {
        					u.put("lastlogintime", sdf.format(sdf.parse(lastlogintimeObj.toString())));
        				}
        			}
        		}
        		POIUtils poi = new POIUtils();
        		String[] heads = {"用户账号","代理商","经纪人","注册时间","注册来源","注册IP","归属地省","归属地市","最后一次登录时间","最后一次登录方式","最后一次登录IP","状态"};
        		String[] colums = {"UserName","agentName","brokerName","RegisterTime","RegisterFrom","RegisterIp","attributionProvince",
        				"attribution","lastlogintime","lastloginfrom","lastfromip","Status"};
        		poi.doExport(request, response, list, "注册信息", "注册信息", heads, colums);
        		log.setUserId(users.getId());
                log.setContent("导出成功，共：" + list.size() + "条数据");
            }
		}catch (Exception e) {
                throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString()); 
		
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
	 * @throws ParseException 
	 */
	@RequestMapping(value="/selectByRealNameAuth")
	@ResponseBody
	public Object selectByRealNameAuth(HttpServletRequest request, String userName, String realName, String applyTimeStart, String applyTimeEnd, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException{
		CommonResponse cr = new CommonResponse();
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实名认证查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SMRZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	//获取图片地址
            	ConfigParam configParam = configParamService.selectConfigParamByKey(ConstantUtil.PHOTO_URL);
            	String purl = "";
            	if (configParam != null) {
            		purl = configParam.getParamValue();
            	}
            	
            	String isView = "0";
		         if (role != null && role.get("roleIsView") != null) {
		              isView = role.get("roleIsView").toString();
		          }
            	
            	PageInfo<Map<String, Object>> pageInfo = userInfoService.getByRealNameAuth(userName, realName, applyTimeStart, applyTimeEnd, isView, pageNum, pageSize);
            	List<Map<String, Object>> list = pageInfo.getList();
    			/*if (list != null && list.size() > 0) {
    				for (Map<String, Object> map : list) {
    					if (map.get("IDCardPath") != null && map.get("IDCardPath") != "") {
    						map.put("IDCardPath", purl + map.get("IDCardPath"));
    					}
    					if (map.get("IDCardBackPath") != null && map.get("IDCardBackPath") != "") {
    						map.put("IDCardBackPath", purl + map.get("IDCardBackPath"));
    					}
    				}
    			}*/
            	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
    			cr.setData(pageInfo);
    			cr.setMsg("操作成功！");
    			log.setUserId(users.getId());
                log.setContent("查询成功");
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
		logRecordService.add(log);
        AuditLog.info(log.toString());
		return cr;
	}
	
	/**
	 * 实名认证已审核记录查询
	 * @param userName
	 * @param realName
	 * @param applyTimeStart
	 * @param applyTimeEnd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/selectByRealNameAuthApprove")
	@ResponseBody
	public Object selectByRealNameAuthApprove(HttpServletRequest request, String userName, String realName, String state, 
			String applyTimeStart, String applyTimeEnd, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException{
		CommonResponse cr = new CommonResponse();
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实名认证已审核记录查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SMRZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	//获取图片地址
            	ConfigParam configParam = configParamService.selectConfigParamByKey(ConstantUtil.PHOTO_URL);
            	String purl = "";
            	if (configParam != null) {
            		purl = configParam.getParamValue();
            	}
            	
            	String isView = "0";
		         if (role != null && role.get("roleIsView") != null) {
		              isView = role.get("roleIsView").toString();
		          }
            	
            	PageInfo<Map<String, Object>> pageInfo = userInfoService.getByRealNameAuthApprove(userName, realName, state, applyTimeStart, applyTimeEnd, isView, pageNum, pageSize);
            	List<Map<String, Object>> list = pageInfo.getList();
    			if (list != null && list.size() > 0) {
    				for (Map<String, Object> map : list) {
    					/*if (map.get("IDCardPath") != null && map.get("IDCardPath") != "") {
    						map.put("IDCardPath", purl + map.get("IDCardPath"));
    					}
    					if (map.get("IDCardBackPath") != null && map.get("IDCardBackPath") != "") {
    						map.put("IDCardBackPath", purl + map.get("IDCardBackPath"));
    					}*/
    					map.put("RealNameAuthApproveState", ConstantUtil.authApproveState.toMap().get(map.get("RealNameAuthApproveState").toString()));
    				}
    			}
            	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
    			cr.setData(pageInfo);
    			cr.setMsg("操作成功！");
    			log.setUserId(users.getId());
                log.setContent("查询成功");
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
		logRecordService.add(log);
        AuditLog.info(log.toString());
		return cr;
	}

	/**
	 * 账户信息
	 * @throws ParseException 
	 */
	@RequestMapping(value="/selectByAccountMessage")
	@ResponseBody
	public Object selectByAccountMessage(HttpServletRequest request, String userName,String agentsName, String brokerName,
			String startTime,String endTime,@RequestParam Integer pageNum,@RequestParam Integer pageSize) throws ParseException{
		CommonResponse cr = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("账户信息查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZHXX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
			 if (users != null) {
				 String isView = "0";
		         if (role != null && role.get("roleIsView") != null) {
		              isView = role.get("roleIsView").toString();
		          }
				 PageInfo<Map<String, Object>> pageInfo = userInfoService.getByAccountMessage(userName, agentsName, brokerName, startTime, endTime, isView, pageNum, pageSize);
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					cr.setData(pageInfo);
					cr.setMsg("操作成功！");
	                log.setUserId(users.getId());
	                log.setContent("查询成功");
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
		logRecordService.add(log);
        AuditLog.info(log.toString());
		return cr;
	}

	/**
	 * 导出excel--账户信息
	 * @throws ParseException 
	 */
	@RequestMapping(value="/excelAccountMessage")
	@ResponseBody
	public void excelAccountMessage(HttpServletRequest request, HttpServletResponse response,String userName,
			String agentsName, String brokerName,String startTime,String endTime) throws ParseException{
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("账户信息导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZHXX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		        	isView = role.get("roleIsView").toString();
		        }
        		List<Map<String, Object>> list = userInfoService.getExcelAccount(userName,agentsName, brokerName,startTime,endTime, isView);
        		POIUtils poi = new POIUtils();
        		String[] heads = {"用户账号","昵称","姓名","注册时间","代理商","经纪人","身份证号","银行卡","支付宝","人民币余额","黄金","稳赚金","黄金收益","黄金单位成本价"};
        		String[] colums = {"userName","nickName","RealName","registerTime","agentName","brokerName","idcard","alipayNumber","accountNum","rmb","gold","financeGold","totalIncome","averagePrice"};
        		poi.doExport(request, response, list, "账户信息", "账户信息", heads, colums);
        		log.setUserId(users.getId());
                log.setContent("导出成功，共：" + list.size() + "条数据");
            }
		}catch (Exception e) {
                throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
	}

	/**
	 * 获取账户信息列表--金额统计
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/selectAccountCount")
	@ResponseBody
	public String selectAccountCount(HttpServletRequest request, String userName,String agentsName, String brokerName,
			String startTime,String endTime) throws ParseException{
		CommonResponse cr = new CommonResponse();
		
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("账户信息统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.ZHXX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		
		try {
			HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	Map<String,Object> map = new HashMap<String,Object>();
    			map = userInfoService.getByAccountCount(userName,agentsName, brokerName,startTime,endTime);
    			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
    			cr.setData(map);
    			cr.setMsg("操作成功！");
    			log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
            	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("无操作权限！");
            }
			
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		logRecordService.add(log);
        AuditLog.info(log.toString());
		return JSON.toJSONString(cr);
	}

	@RequestMapping(value="/selectSubClients")
	@ResponseBody
	public Object selectSubClients(HttpServletRequest request,String userName,String agentsName, String brokerName,String startTime,String endTime,@RequestParam Integer pageNum,@RequestParam Integer pageSize) throws ParseException {
		CommonResponse cr = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("查询下级客户");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.XJKH.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
			if (users != null){
				agentsName = users.getUserName();
				String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
				PageInfo<Map<String, Object>> pageInfo = userInfoService.getSubClients(userName, agentsName, brokerName, isView, pageNum, pageSize);
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				cr.setData(pageInfo);
				cr.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("查询成功");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return cr;
	}
	/**
	 * 导出excel--账户信息
	 */
	@RequestMapping(value="/excelSubClients")
	@ResponseBody
	public void excelSubClients(HttpServletRequest request, HttpServletResponse response,String userName,
			String agentName, String brokerName,String startTime,String endTime){
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
		if (users != null){
			agentName = users.getUserName();
			String isView = "0";
	        if (role != null && role.get("roleIsView") != null) {
	            isView = role.get("roleIsView").toString();
	        }
			List<Map<String, Object>> list = userInfoService.getExcelSubClientsAccount(userName,agentName, brokerName, isView);
			POIUtils poi = new POIUtils();
			String[] heads = {"用户账号","代理商","经纪人","人民币余额","黄金","稳赚金","黄金收益","黄金成本价"};
			String[] colums = {"userName","agentName","brokerName","rmb","gold","financeGold","totalIncome","averagePrice"};
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
	public String selectSubClientsCount(HttpServletRequest request, String userName,String agentName, String brokerName,String startTime,String endTime) throws ParseException {
		CommonResponse cr = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("下级客户-金额统计");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.XJKH.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
				log.setUserId(users.getId());
				log.setContent("查询成功");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return JSON.toJSONString(cr);
	}


	/**
	 * 变更经纪人
	 */
	@RequestMapping(value="/cheageBroker")
	@ResponseBody
	 public CommonResponse cheageBroker(HttpServletRequest request,String userId,Long  brokerId) throws ParseException {
	 	CommonResponse response = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("变更经济人");
		log.setContent("变更失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZHXX.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
	 	try{
	 		HttpSession session = request.getSession();
	 		Users users = (Users)session.getAttribute("currentUser");
	 		if (users != null){
	 			int msg = userInfoService.changeBroker(Long.valueOf(userId),brokerId);
	 			if (msg>0){
	 				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更成功");
					log.setUserId(users.getId());
					log.setContent("变更成功");
	 			}else{
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更失败");
				}
			}
		}catch (Exception e){
	 		e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	 }

	/**
	 * 变更经纪人
	 */
	@RequestMapping(value="/alertAgentAndBroker")
	@ResponseBody
	public CommonResponse alertAgentAndBroker(HttpServletRequest request, String userId, String realName, String idcard,
			Long  brokerId, Long agentId) throws ParseException {
		CommonResponse response = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("变更经济人");
		log.setContent("变更失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZHXX.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try{
			HttpSession session = request.getSession();
			Users users = (Users)session.getAttribute("currentUser");
			if (users != null){
				int msg = userInfoService.alertAgentAndBroker(realName, idcard, Long.valueOf(userId), brokerId,agentId);
				if (msg>0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更成功");
					log.setUserId(users.getId());
					log.setContent("变更成功");
				}else{
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(msg);
					response.setMsg("变更失败");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	
	/**
	 * 
	* @Title: getByUserAnalysis 
	* @Description: 用户分析查询
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param registerFrom  登录来源
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
	public Object getByUserAnalysis(HttpServletRequest request, String type, String startTime, String endTime, String registerFrom, String agentName,
			 @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
		CommonResponse cr = new CommonResponse();
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户分析查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
                PageInfo<Map<String, Object>> pageInfo = userInfoService.getByUserAnalysis(sTime, eTime, registerFrom, agentName, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
	}
	
	/**
	 * 
	* @Title: getByUserAnalysisCount 
	* @Description: 用户分析查询--统计
	* @param request
	* @param startTime  开始时间
	* @param endTime  结束时间
	* @param registerFrom  登录来源
	* @param agentName 代理商id
	* @param
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/getByUserAnalysisCount")
	@ResponseBody
	public Object getByUserAnalysisCount(HttpServletRequest request, String type, String startTime, String endTime, String registerFrom, String agentName) throws Exception {
		CommonResponse cr = new CommonResponse();
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户分析统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
                List<Map<String, Object>> list = userInfoService.getByUserAnalysisCount(sTime, eTime, registerFrom, agentName);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
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
	* @param registerFrom 来源
	* @param agentName 代理商id
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value = "/excelByUserAnalysis")
	@ResponseBody
	public void excelByUserAnalysis(HttpServletRequest request, HttpServletResponse response, String type, String startTime, String endTime,
			String registerFrom, String agentName) throws Exception {
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户分析导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHFX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
				List<Map<String, Object>> list = userInfoService.excelByUserAnalysis(sTime, eTime, registerFrom, agentName);
				POIUtils poi = new POIUtils();
				String[] heads = { "日期", "合计入金用户", "新入金用户", "新入金用户比例", 
						"金权交易合计交易用户", "金权交易新交易用户", "金权交易新交易用户比例", "稳赚金合计交易用户",
						"稳赚金交易用户", "稳赚金新交易用户比例", "存金宝合计交易用户", "存金宝新交易用户",
						"存金宝新交易用户比例", "新注册用户", "总交易用户", "总用户", "总入金用户" };
				String[] colums = { "date", "hjrj", "xrj", "xrjbl",
						"hjjqjy", "xjqjy", "xjqjybl", "hjdqj",
						"xdqj", "xdqjbl", "hjcjb", "xcjb",
						"xcjbbl", "xzc", "zjy", "zzc", "zrj" };
				poi.doExport(request, response, list, tieleName, excelName,
						heads, colums);
				log.setUserId(users.getId());
                log.setContent("导出成功，共：" + list.size() + "条数据");
			}
		} catch (Exception e) {
			throw e;
		}
		logRecordService.add(log);
        AuditLog.info(log.toString());
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
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户属性查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHSX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
                log.setUserId(users.getId());
                log.setContent("查询成功");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
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
	* @param
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
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户属性统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHSX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
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
                log.setUserId(users.getId());
                log.setContent("查询成功");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
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
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("客户属性导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.KHSX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			String tieleName = "客户属性分析";
			String excelName = "客户属性分析";
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
				String[] colums = { "date", "dlNum", "zNum", "nNum", "nvNum", "wzNum", "pgNum", "azNum", "qtNum" };
				poi.doExport(request, response, list, tieleName, excelName,
						heads, colums);
				log.setUserId(users.getId());
		        log.setContent("导出成功，共：" + list.size() + "条数据");
			}
		} catch (Exception e) {
			throw e;
		}
		logRecordService.add(log);
        AuditLog.info(log.toString());
	}
}
