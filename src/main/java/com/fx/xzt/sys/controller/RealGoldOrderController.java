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
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: RealGoldOrderController.java
 * @Description: 实金交易Controller
 * @date 2017-09-26 17:02
 */
@Controller
@RequestMapping("/realGoldOrder")
public class RealGoldOrderController {

    @Resource
    RealGoldOrderService realGoldOrderService;
    @Resource
    LogRecordService logRecordService;

    /**
     *  实金交易查询
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime  买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/selectByRealGoldOrder")
    @ResponseBody
    public Object selectByRealGoldOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                           String agentName, String brokerName, String isNovice, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实金交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJJY.getName());
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
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                PageInfo<Map<String, Object>> pageInfo = realGoldOrderService.selectByRealGoldOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, isNovice, isView, pageNum, pageSize);
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
     * 实金交易-导出
     * @param request
     * @param response
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime  买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/excelRealGoldOrder")
    @ResponseBody
    public void excelRealGoldOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                   String agentName, String brokerName, String isNovice) throws Exception {
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实金交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            String tieleName = "实金交易";
            String excelName = "实金交易";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                List<Map<String, Object>> list = realGoldOrderService.excelRealGoldOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, isNovice, isView);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	
                    	map.put("isNovice", ConstantUtil.isNovice.toMap().get(map.get("isNovice").toString()));
                    	
                        Object rmbAmountObj =  map.get("rmbAmount");
                        Object feeObj =  map.get("fee");
                        Object shareAmountObj = map.get("shareAmount");
                        Object registerTimeObj = map.get("registerTime");
                    	Object buyTimeObj = map.get("buyTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (buyTimeObj != null && buyTimeObj != "") {
	               			map.put("buyTime", sdf.format(sdf.parse(buyTimeObj.toString())));
	                    }
                        
                        if (rmbAmountObj != null && rmbAmountObj != "") {
                        	Double rmbAmount = Double.valueOf(rmbAmountObj.toString());
                        	map.put("rmbAmount", rmbAmount/100);
                        }
                        if (feeObj != null && feeObj != "") {
                        	Double fee = Double.valueOf(feeObj.toString());
                        	map.put("fee", fee/100);
                        }
                        if (shareAmountObj != null && shareAmountObj != "") {
                         	Double shareAmount = Double.valueOf(shareAmountObj.toString());
                         	map.put("shareAmount", shareAmount/100);
                        }
                    }
                }
                POIUtils poi = new POIUtils();
                //判断是否为代理商账户
                if (users.getPid() != null &&  users.getPid() == 1) {
                    String[] heads = {"用户账号","注册时间","经纪人","交易订单号","合约类型","买入价","优惠金额","优惠价","黄金克数","买入金额","买入时间", "交易分成","新手专享"};
                    String[] colums = {"userName","registerTime","brokerName","orderNo","productName","buyPrice",
                    		"discount","discountPrice","gram","rmbAmount","buyTime", "shareAmount", "isNovice"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                } else if (users.getPid() == null || users.getPid() == 0){
                    String[] heads = {"用户账号","注册时间","代理商","经纪人","交易订单号","合约类型","买入价","优惠金额","优惠价","黄金克数","买入金额","手续费", "买入时间","新手专享"};
                    String[] colums = {"userName","registerTime","agentName","brokerName","orderNo","productName","buyPrice",
                    		"discount","discountPrice","gram","rmbAmount","fee", "buyTime", "isNovice"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
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
     *  实金交易金额统计
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/selectByRealGoldCount")
    @ResponseBody
    public Object selectByRealGoldCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, String isNovice) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实金交易交易统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map = realGoldOrderService.selectByRealGoldCount(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, isNovice);
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

}