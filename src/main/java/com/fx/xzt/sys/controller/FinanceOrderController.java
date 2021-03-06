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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: FinanceOrderController.java
 * @Description:
 * @date 2017-09-27 14:13
 */
@Controller
@RequestMapping("/financeOrder")
public class FinanceOrderController {

    @Resource
    FinanceOrderService financeOrderService;
    @Resource
    LogRecordService logRecordService;
    

    /**
     * 理财交易查询
     *
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         类型 1：随意存
     * @param nper         期数
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectByFinanceOrder")
    @ResponseBody
    public Object selectByFinanceOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	CommonResponse cr = new CommonResponse();
    	//操作日志
        LogRecord log = new LogRecord();
        log.setTitle("理财交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("理财收益结算查询");
        	log.setModuleName(ConstantUtil.logRecordModule.LCSYJS.getName());
        }
        
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
                PageInfo<Map<String, Object>> pageInfo = financeOrderService.selectByFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime,
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper, isView, pageNum, pageSize);
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
     * 理财交易查询-导出
     *
     * @param request
     * @param response
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         类型 1：理财产品
     * @param nper         期数
     */
    @RequestMapping(value = "/excelFinanceOrder")
    @ResponseBody
    public void excelFinanceOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException {
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("理财交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("理财收益结算导出");
        	log.setModuleName(ConstantUtil.logRecordModule.LCSYJS.getName());
        }
        
        try {
            String tieleName = "";
            String excelName = "";
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
                List<Map<String, Object>> list = financeOrderService.excelFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, 
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper, isView);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	if (map.get("status") != null && map.get("status") != "") {
                    		map.put("status", ConstantUtil.financeOrderStatus.toMap().get(map.get("status").toString()));
                    	}
                    	
                    	Object buyAmountObj =  map.get("buyAmount");
                    	Object initialPriceObj =  map.get("initialPrice");
                    	Object incomeObj =  map.get("income");
                    	Object yearIncomPercentObj =  map.get("yearIncomPercent");
                    	Object shareAmountObj = map.get("shareAmount");
                    	Object registerTimeObj = map.get("registerTime");
                    	Object buyTimeObj = map.get("buyTime");
                    	Object redeemTimeObj = map.get("redeemTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (buyTimeObj != null && buyTimeObj != "") {
	               			map.put("buyTime", sdf.format(sdf.parse(buyTimeObj.toString())));
	                    }
	               		
	               		if (redeemTimeObj != null && redeemTimeObj != "") {
	               			map.put("redeemTime", sdf.format(sdf.parse(redeemTimeObj.toString())));
	               		}
                    	
                    	if (buyAmountObj != null && buyAmountObj != "") {
                        	Double buyAmount = Double.valueOf(buyAmountObj.toString());
                        	map.put("buyAmount", buyAmount/100);
                        }
                    	
                    	if (initialPriceObj != null && initialPriceObj != "") {
                        	Double initialPrice = Double.valueOf(initialPriceObj.toString());
                        	map.put("initialPrice", initialPrice/100);
                        }
                    	
                    	if (incomeObj != null && incomeObj != "") {
                        	Double income = Double.valueOf(incomeObj.toString());
                        	map.put("income", income/100);
                        }
                    	if (yearIncomPercentObj != null && yearIncomPercentObj != "") {
                        	Double yearIncomPercent = Double.valueOf(yearIncomPercentObj.toString());
                        	DecimalFormat df = new DecimalFormat("0.00%");
                        	map.put("yearIncomPercent", df.format(yearIncomPercent));
                        }
                    	 if (shareAmountObj != null && shareAmountObj != "") {
                         	Double shareAmount = Double.valueOf(shareAmountObj.toString());
                         	map.put("shareAmount", shareAmount/100);
                         }
                    }
                }
                POIUtils poi = new POIUtils();
                //理财产品交易导出
               // if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_LCCP ) {
                    tieleName = "理财交易";
                    excelName = "理财交易";
                    //判断是否为代理商账户
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入价", "买入克重",  "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出", "交易分成"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", 
                        		"initialPrice", "gram", "buyAmount", "buyTime", "redeemTime", "status", "income", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0){
                        if (status != null && status == 2) {
                            tieleName = "理财收益结算";
                            excelName = "理财收益结算";
                        }
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入价", "买入克重","买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", 
                        		"initialPrice", "gram", "buyAmount", "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    }
                    log.setUserId(users.getId());
                    log.setContent("导出成功，共：" + list.size() + "条数据");
              //  }
                //黄金稳赚交易导出
                /*if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_HJWZ) {
                    tieleName = "黄金稳赚交易";
                    excelName = "黄金稳赚交易";
                    //判断当时账户是否为代理商
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "赎回金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        if (status != null && status == 2) {
                            tieleName = "黄金稳赚看涨结算";
                            excelName = "黄金稳赚看涨结算";
                        }
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "赎回金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    }
                }*/
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
    }

    /**
     * 理财交易查询-统计
     *
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectByFinanceOrderCount")
    @ResponseBody
    public Object selectByFinanceOrderCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException {
        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("理财交易统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("理财收益统计查询");
        	log.setModuleName(ConstantUtil.logRecordModule.LCSYJS.getName());
        }
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                	agentNameStr = users.getId().toString();
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map = financeOrderService.selectByFinanceOrderCount(userName, orderNo, startTime, endTime, regStartTime, regEndTime, 
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper);
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
     * 黄金理财交易查询
     *
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         黄金理财类型 2：黄金看涨；3：黄金看跌
     * @param nper         期数
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectByGoldFinanceOrder")
    @ResponseBody
    public Object selectByGoldFinanceOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金稳赚交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJWZJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("黄金稳赚结算查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJWZJS.getName());
        }
        
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
                PageInfo<Map<String, Object>> pageInfo = financeOrderService.selectByGoldFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime,
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper, isView, pageNum, pageSize);
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
     * 黄金交易查询-导出
     *
     * @param request
     * @param response
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         黄金理财类型 2：黄金看涨；3：黄金看跌
     * @param nper         期数
     */
    @RequestMapping(value = "/excelGoldFinanceOrder")
    @ResponseBody
    public void excelGoldFinanceOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException {
    	
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金稳赚交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJWZJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("黄金稳赚结算导出");
        	log.setModuleName(ConstantUtil.logRecordModule.HJWZJS.getName());
        }
        try {
            String tieleName = "";
            String excelName = "";
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
                List<Map<String, Object>> list = financeOrderService.excelGoldFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, 
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper, isView);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	if (map.get("status") != null && map.get("status") != "") {
                    		map.put("status", ConstantUtil.financeOrderStatus.toMap().get(map.get("status").toString()));
                    	}
                    	
                    	Object buyAmountObj =  map.get("buyAmount");
                    	Object incomeObj =  map.get("income");
                    	Object yearIncomPercentObj =  map.get("yearIncomPercent");
                    	Object shareAmountObj = map.get("shareAmount");
                    	Object registerTimeObj = map.get("registerTime");
                    	Object buyTimeObj = map.get("buyTime");
                    	Object redeemTimeObj = map.get("redeemTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (buyTimeObj != null && buyTimeObj != "") {
	               			map.put("buyTime", sdf.format(sdf.parse(buyTimeObj.toString())));
	                    }
	               		
	               		if (redeemTimeObj != null && redeemTimeObj != "") {
	               			map.put("redeemTime", sdf.format(sdf.parse(redeemTimeObj.toString())));
	               		}
	               		
                    	if (buyAmountObj != null && buyAmountObj != "") {
                        	Double buyAmount = Double.valueOf(buyAmountObj.toString());
                        	map.put("buyAmount", buyAmount/100);
                        }
                    	if (incomeObj != null && incomeObj != "") {
                        	Double income = Double.valueOf(incomeObj.toString());
                        	map.put("income", income/100);
                        }
                    	if (yearIncomPercentObj != null && yearIncomPercentObj != "") {
                        	Double yearIncomPercent = Double.valueOf(yearIncomPercentObj.toString());
                        	DecimalFormat df = new DecimalFormat("0.00%");
                        	map.put("yearIncomPercent", df.format(yearIncomPercent));
                        }
                    	if (shareAmountObj != null && shareAmountObj != "") {
                         	Double shareAmount = Double.valueOf(shareAmountObj.toString());
                         	map.put("shareAmount", shareAmount/100);
                         }
                    }
                }
                POIUtils poi = new POIUtils();

                //黄金稳赚交易导出
              //  if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_HJWZ) {
                    tieleName = "黄金稳赚交易";
                    excelName = "黄金稳赚交易";
                    //判断当时账户是否为代理商
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "交易订单号", "经纪人", "产品编号", "产品名称", "期数", "周期", "收益率", "浮动收益率", "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出", "收益分成"};
                        String[] colums = {"userName", "orderNo", "brokerName", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "floatPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        if (status != null && status == 2) {
                            tieleName = "黄金稳赚看涨结算";
                            excelName = "黄金稳赚看涨结算";
                            String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "浮动收益率", "赎回金额",
                                    "买入时间", "赎回时间", "状态", "收益支出"};
                            String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "floatPercent", "buyAmount",
                                    "buyTime", "redeemTime", "status", "income"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        } else {
                        	String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "初始价", "结算价", "浮动收益率", "买入金额",
                                    "买入时间", "赎回时间", "状态", "收益支出"};
                            String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", 
                            		"initialPrice", "clearPrice", "floatPercent", "buyAmount",
                                    "buyTime", "redeemTime", "status", "income"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        } 
                    }
                    log.setUserId(users.getId());
                    log.setContent("导出成功，共：" + list.size() + "条数据");
                }
           // }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
    }
    
    /**
     * 黄金理财交易查询-统计
     *
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectByGoldFinanceOrderCount")
    @ResponseBody
    public Object selectByGoldFinanceOrderCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException {
        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金稳赚交易统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJWZJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        if (status != null && status == 2) {
        	log.setTitle("黄金稳赚结算统计查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJWZJS.getName());
        }
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                	agentNameStr = users.getId().toString();
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map = financeOrderService.selectByGoldFinanceOrderCount(userName, orderNo, startTime, endTime, regStartTime, regEndTime, 
                		redeemStartTime, redeemEndTime, agentNameStr, brokerName, status, type, nper);
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
