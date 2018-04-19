package com.fx.xzt.sys.controller;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.fx.xzt.sys.service.FinanceRegulargoldOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

/**
 * @author htt
 * @ClassName: FinanceOrderController.java
 * @Description:
 * @date 2017-09-27 14:13
 */
@Controller
@RequestMapping("/financeRegulargoldOrder")
public class FinanceRegulargoldOrderController {

    @Resource
    FinanceRegulargoldOrderService financeRegulargoldOrderService;
    @Resource
    LogRecordService logRecordService;
    

    /**
     * 定期金交易查询
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
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectByFinanceOrder")
    @ResponseBody
    public Object selectByFinanceOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, String buyType,  Integer pageNum, Integer pageSize) throws ParseException, GlobalException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	CommonResponse cr = new CommonResponse();
    	//操作日志
        LogRecord log = new LogRecord();
        log.setTitle("定期金交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.DQJJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "1";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                if (users.getPid() == null){

                }else if (users.getPid() == 1){
                    agentName = users.getId()+"";
                }else {
                    brokerName = users.getId()+"";
                }
                cr = financeRegulargoldOrderService.getAllByConditions(userName, orderNo, startTime, endTime, regStartTime, regEndTime,
                        redeemStartTime, redeemEndTime, buyType, agentName, brokerName, status, isView, pageNum, pageSize);
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setMsg("请登录！");
            }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setMsg("操作失败！");
            throw e;
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
     */
    @RequestMapping(value = "/exportFinanceOrder")
    @ResponseBody
    public void excelFinanceOrder(HttpServletRequest request, HttpServletResponse response,String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                  String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, String buyType, Integer pageNum, Integer pageSize) throws ParseException, GlobalException {
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("定期金交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));

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
		        if (users.getPid() == null){

                }else if (users.getPid() == 1){
		            agentName = users.getId()+"";
                }else {
		            brokerName = users.getId()+"";
                }
                List<Map> list = financeRegulargoldOrderService.exportAllByConditions(userName, orderNo, startTime, endTime, regStartTime, regEndTime,
                        redeemStartTime, redeemEndTime, buyType, agentName, brokerName, status, isView, pageNum, pageSize);
                if (list != null && list.size() > 0) {
                    POIUtils poi = new POIUtils();
                    //理财产品交易导出
                    // if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_LCCP ) {
                    tieleName = "定期金交易";
                    excelName = "定期金交易";
                    //判断是否为代理商账户
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "代理商","经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入价", "买入克重",  "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出", "交易分成"};
                        String[] colums = {"userName", "registerTime", "agentName","brokerName", "orderNo", "productNo", "productName", "productCycle", "yearIncomPercent",
                                "initialPrice", "gram", "buyAmount", "buyTime", "redeemTime", "status", "income", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0){
                        if (status != null && status == 2) {
                            tieleName = "定期金交易";
                            excelName = "定期金交易";
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
                }

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
    /*@RequestMapping(value = "/selectByFinanceOrderCount")
    @ResponseBody*/
    /*public Object selectByFinanceOrderCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
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
    }*/
}
