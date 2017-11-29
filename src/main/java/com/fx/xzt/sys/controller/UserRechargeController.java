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
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserRechargeService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @Title: UserRechargeController.java 
* @Package com.fx.xzt.sys.controller
* @Description: 现金充值
* @author SYan  
* @date 2017年8月22日 上午11:19:17 
* @version V1.0
 */
@Controller
@RequestMapping(value="/userRecharge")
public class UserRechargeController {
	@Resource
	UserRechargeService userRechargeService;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByUserRecharge 
	* @Description: 现金充值记录查询
	* @param request
	* @param userName   用户账号
	* @param startTime  开始时间
	* @param endTime    结束时间
	* @param agentName  代理商用户名
	* @param brokerName 经纪人用户名
	* @param rechargechannel  充值渠道
	* @param status  充值状态  0：失败 1：成功
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByUserRecharge")
    @ResponseBody
    public Object selectByUserRecharge(HttpServletRequest request, String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金充值查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJCZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = userRechargeService.selectByRecharge(userName, startTime, endTime, 
                		agentName, brokerName, rechargechannel, status, pageNum, pageSize);
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
	* @Title: excelUserRecharge 
	* @Description: 现金充值记录-导出
	* @param request
	* @param response
	* @param userName   用户账号
	* @param startTime  开始时间
	* @param endTime   结束是时间
	* @param agentName  代理商用户名
	* @param brokerName  经纪人用户名
	* @param rechargechannel 渠道
	* @param status    设定文件 
	* @return void    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelUserRecharge")
    @ResponseBody
    public void excelUserRecharge(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status) throws Exception{
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金充值导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJCZ.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            String tieleName = "现金充值";
            String excelName = "现金充值";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                List<Map<String, Object>> list = userRechargeService.excelRecharge(userName, startTime, endTime, agentNameStr, brokerName, rechargechannel, status);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	Object RMBAmtObj =  map.get("RMBAmt");
                    	Object RechargeTimeObj = map.get("RechargeTime");
                    	
               		 	if (RechargeTimeObj != null && RechargeTimeObj != "") {
               		 		map.put("RechargeTime", sdf.format(sdf.parse(RechargeTimeObj.toString())));
                        }
                    	
                    	if (RMBAmtObj != null && RMBAmtObj != "") {
                        	Double RMBAmt = Double.valueOf(RMBAmtObj.toString());
                        	map.put("RMBAmt", RMBAmt/100);
                        }
                        map.put("Status", ConstantUtil.rechargeStatus.toMap().get(map.get("Status").toString()));
                        map.put("RechargeChannel", ConstantUtil.rechargeChannel.toMap().get(map.get("RechargeChannel").toString()));
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "充值金额", "代理商", "经纪人", "充值渠道", "充值时间"};
                    String[] colums = {"UserName", "RMBAmt", "agentName", "brokerName","RechargeChannel", "RechargeTime"};
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
	* @Title: selectByUserRechargeCount 
	* @Description: 现金充值记录-统计
	* @param request
	* @param userName
	* @param startTime
	* @param endTime
	* @param agentName
	* @param brokerName
	* @param rechargechannel
	* @param status
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByUserRechargeCount")
    @ResponseBody
    public Object selectByUserRechargeCount(HttpServletRequest request, String userName, String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel, Integer status) throws ParseException{
        CommonResponse cr = new CommonResponse();
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("现金充值统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XJCZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = userRechargeService.selectByRechargeCount(userName, startTime, endTime, agentName, brokerName, rechargechannel, status);
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
	 * 获取充值记录 集合 
	 * @return
	 */
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public PageInfo<UserRechargeModel> selectAll(String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status,Integer pageNum,Integer pageSize){
		return userRechargeService.getAll(username, rechargeid, merchantordernum, startTime, endTime, rechargechannel, status, pageNum, pageSize);
	}
	/**
	 * 导出excel
	 */
	@RequestMapping(value="/excelRecharge")
	public void excelRecharge(HttpServletRequest request, HttpServletResponse response,String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status){
		List<UserRechargeModel> list = userRechargeService.getExcelAll(username, rechargeid, merchantordernum, startTime, endTime, rechargechannel, status);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","充值流水号","商户订单号","人民币","优币","渠道","充值时间","充值状态"};
		String[] columns = {"username","rechargeid","merchantordernum","rmbamt","uamt","rechargeChannelName","formatRechargetime","statusName"};
		poi.doExport(request, response, list, "充值记录", "充值记录", heads, columns);
	}
}
