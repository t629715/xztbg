package com.fx.xzt.sys.controller;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.service.UserMessageService;
import com.fx.xzt.sys.service.UserWithdrawCashService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.IdUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userWithdrawCash")
public class UserWithdrawCashController {
	@Resource
	UserWithdrawCashService userWithdrawCashService;
	@Resource
	UserAccountRecordService userAccountRecordService;
	@Resource
    LogRecordService logRecordService;
	@Resource
	UserMessageService userMessageService;
	
	/**
	 * 出金管理 集合 
	 * @return
	 */
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public PageInfo<UserWithdrawCashModel> selectAll(String userName, String startTime, String endTime, String status,
			Integer pageNum, Integer pageSize){
		return userWithdrawCashService.getByAll(userName, startTime, endTime, status, pageNum, pageSize);
	}
	
	/**
	 * 出金管理导出excel
	 */
	@RequestMapping(value="/excelRecharge")
	public void excelRecharge(HttpServletRequest request, HttpServletResponse response,
			String userName, String startTime, String endTime, String status){

		List<UserWithdrawCashModel> list = userWithdrawCashService.getByAllExcel(userName, startTime, endTime, status);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","提现金额","绑定银行","绑定银行卡号","冻结时间","完成时间","状态"};
		String[] columns = {"username","withdrawamt","bankName","accountnum","withdrawtimeString","finishtimeString","statusName"};
		poi.doExport(request, response, list, "出金记录", "出金记录", heads, columns);
	}
	/**
	 * 修改状态 体现完成
	 */
	@RequestMapping(value="/editStatus")
	@ResponseBody
	public Map<String,Object> editStatus(HttpServletRequest request,String withdrawid,Integer type) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("修改用户提现状态");
		log.setContent("修改失败失败");
		log.setModuleName("账号管理");
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		int msg = 0;
		if(type==1){
			//提现
			msg = userWithdrawCashService.updateByIdStatus(withdrawid);
		}else if(type==2){
			//拒绝
			msg = userWithdrawCashService.updateWithdrawCashAndAccount(withdrawid);
		}
		if (msg >0){
			log.setContent("修改成功");
			log.setUserId(users.getId());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", msg);
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
	
	/**
	 * 
	* @Title: selectByWithdrawCash 
	* @Description: 现金提取查询
	* @param request
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束时间
	* @param agentName  代理商用户名
	* @param brokerName 经纪人用户名
	* @param status 状态 0：审核中 1：已完成
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByWithdrawCash")
    @ResponseBody
    public Object selectByWithdrawCash(HttpServletRequest request, String userName, String startTime, String endTime, 
			String agentName, String brokerName, Integer status, 
			@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金提取查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJTQ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = userWithdrawCashService.selectByWithdrawCash(userName, startTime, endTime, 
                		agentName, brokerName, status, pageNum, pageSize);
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
	* @Title: excelWithdrawCash 
	* @Description: 现金提取-导出
	* @param request  
	* @param response
	* @param userName  用户账号
	* @param startTime 申请开始键
	* @param endTime  申请结束时间
	* @param agentName 代理商用户名
	* @param brokerName 经纪人用户名
	* @param status    状态 0：审核中 1：已完成
	* @return void    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelWithdrawCash")
    @ResponseBody
    public void excelWithdrawCash(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
			String agentName, String brokerName, Integer status) throws Exception{
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金提取导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJTQ.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            String tieleName = "现金提取";
            String excelName = "现金提取";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                List<Map<String, Object>> list = userWithdrawCashService.excelWithdrawCash(userName, startTime, endTime, agentNameStr, brokerName, status);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        map.put("status", ConstantUtil.withdrawCashStatus.toMap().get(map.get("status").toString()));
                        map.put("type", ConstantUtil.withdrawCashType.toMap().get(map.get("type").toString()));
                        
                        Object amtObj =  map.get("withdrawAmt");
                        Object poundageObj = map.get("poundage");
                        Object registerTimeObj = map.get("registerTime");
                    	Object withdrawTimeObj = map.get("withdrawTime");
                    	Object finishTimeObj = map.get("finishTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (withdrawTimeObj != null && withdrawTimeObj != "") {
	               			map.put("withdrawTime", sdf.format(sdf.parse(withdrawTimeObj.toString())));
	                    }
	               		
	               		if (finishTimeObj != null && finishTimeObj != "") {
	               			map.put("finishTime", sdf.format(sdf.parse(finishTimeObj.toString())));
	                    }
                        
                        if (amtObj != null && amtObj != "") {
                        	Double amt = Double.valueOf(amtObj.toString());
                        	map.put("withdrawAmt", amt/100);
                        }
                        if (poundageObj != null && poundageObj != "") {
                        	Double poundage = Double.valueOf(poundageObj.toString());
                        	map.put("poundage", poundage/100);
                        }
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号","代理商","经纪人","提取金额","手续费","提取类型","所属银行","银行卡号","申请提取时间", "审核时间",  "状态"};
                    String[] colums = {"userName", "agentName", "brokerName", "withdrawAmt", "poundage", "type","bankName","accountNum", "withdrawTime", "finishTime", "status"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    log.setUserId(users.getId());
                    log.setContent("导出成功，共：" + list.size() + "条数据");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
    }
	
	/**
	 * 
	* @Title: selectByWithdrawCashCount 
	* @Description: 现金提取查询-统计
	* @param request
	* @param userName    用户账号
	* @param startTime   申请开始时间
	* @param endTime     申请结束时间
	* @param agentName   代理商用户名
	* @param brokerName  经纪人用户名
	* @param status 状态 0：审核中 1：已完成
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByWithdrawCashCount")
    @ResponseBody
    public Object selectByWithdrawCashCount(HttpServletRequest request, String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status) throws ParseException{
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金提取统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJTQ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = userWithdrawCashService.selectByWithdrawCashCount(userName, startTime, endTime, agentName, brokerName, status);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map);
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
	* @Title: auditPassedById 
	* @Description: 提现审核
	* @param request
	* @param withdrawid
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/auditPassedById")
	@ResponseBody
	public Object auditPassedById(HttpServletRequest request,  String withdrawid, Long userId) throws Exception{
		CommonResponse cr = new CommonResponse();
		
		//系统消息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserMessage message = new UserMessage();
		message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
		message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
		message.setMsgTime(sdf.parse(sdf.format(new Date())));
		message.setUserID(userId);
		
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("现金提取审核");
		log.setContent("审核通过失败");
		log.setModuleName(ConstantUtil.logRecordModule.XJTQ.getName());
		log.setType(ConstantUtil.logRecordType.SH.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int flag = userWithdrawCashService.auditPassedById(withdrawid);
                if (flag > 0) {
                	UserAccountRecord record = new UserAccountRecord();
                	record.setWithdrawId(withdrawid);
                	userAccountRecordService.updateByWithdrawId(record);
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                	cr.setData("{}");
                	cr.setMsg("操作成功！");
                	//系统消息
					message.setMsgContent("您的提现审核已通过，需要1-3个工作日到账，请耐心等待！");
					userMessageService.add(message);
                	//操作日志
                	log.setUserId(users.getId());
                    log.setContent("审核通过成功；信息：withdrawid:" + withdrawid);
                } else {
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                	cr.setData("{}");
                	cr.setMsg("操作失败！");
                }
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
	* @Title: auditNoPassedById 
	* @Description: 提现拒绝
	* @param request
	* @param withdrawid
	* @param userId
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/auditNoPassedById")
	@ResponseBody
	public Object auditNoPassedById(HttpServletRequest request,  String withdrawid, Long userId) throws Exception{
		CommonResponse cr = new CommonResponse();
		
		//系统消息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserMessage message = new UserMessage();
		message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
		message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
		message.setMsgTime(sdf.parse(sdf.format(new Date())));
		message.setUserID(userId);
		
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("现金提取审核");
		log.setContent("审核不通过失败");
		log.setModuleName(ConstantUtil.logRecordModule.XJTQ.getName());
		log.setType(ConstantUtil.logRecordType.SH.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            
            if (users != null) {
                int flag = userWithdrawCashService.auditNoPassedById(withdrawid);
                if (flag > 0) {
                	UserAccountRecord record = new UserAccountRecord();
                	record.setWithdrawId(withdrawid);
                	userAccountRecordService.updateByWithdrawId(record);
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                	cr.setData("{}");
                	cr.setMsg("操作成功！");
                	//系统消息
					message.setMsgContent("您的提现审核未通过，请检查您的提现信息是否正确！");
					userMessageService.add(message);
                	//操作日志
                	log.setUserId(users.getId());
                    log.setContent("审核不通过成功；信息：withdrawid:" + withdrawid);
                } else {
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                	cr.setData("{}");
                	cr.setMsg("操作失败！");
                }
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
}
