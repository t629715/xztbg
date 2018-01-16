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
import com.fx.xzt.sys.service.DealOrderService;
import com.fx.xzt.sys.service.FinanceNewplayerOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: FinanceNewplayerOrderController 
* @Description: 新手理财专享交易
* @author htt
* @date 2018-1-12 下午1:39:54 
*
 */
@Controller
@RequestMapping("/financeNewplayerOrder")
public class FinanceNewplayerOrderController {
	
    @Resource
    DealOrderService dealOrderService;
    @Resource
    LogRecordService logRecordService;
    @Resource
    FinanceNewplayerOrderService orderService;

    /**
     * 
    * @Title: selectByAll 
    * @Description: 查询
    * @param request
    * @param userName    用户名
    * @param orderNo     订单号
    * @param startTime   开始是时间
    * @param endTime     结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime   注册结束时间
    * @param agentName   代理商id
    * @param brokerName  经纪人id
    * @param status  状态
    * @param type    类型
    * @param pageNum
    * @param pageSize
    * @return
    * @throws ParseException    设定文件 
    * @return Object    返回类型 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/selectByAll")
    @ResponseBody
    public Object selectByAll(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, String status, String type,
    		String redeemStartTime, String redeemEndTime, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("新手理财专享交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XSLC.getName());
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
                PageInfo<Map<String, Object>> pageInfo = orderService.selectByAll(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, status, type, redeemStartTime, redeemEndTime, pageNum, pageSize);
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
    * @Title: excelAll 
    * @Description: 导出
    * @param request
    * @param response
    * @param userName    用户名
    * @param orderNo     订单号
    * @param startTime   开始是时间
    * @param endTime     结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime   注册结束时间
    * @param agentName   代理商id
    * @param brokerName  经纪人id
    * @param status  状态
    * @param type    类型
    * @param type
    * @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/excelAll")
    @ResponseBody
    public void excelAll(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime,  String agentName, String brokerName, String status, String type, String redeemStartTime, String redeemEndTime) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("新手理财专享交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.XSLC.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
    	try {
            String tieleName = "新手理财专享";
            String excelName = "新手理财专享";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                List<Map<String, Object>> list = orderService.excelAll(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, status, type, redeemStartTime, redeemEndTime);
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
                    POIUtils poi = new POIUtils();
                    tieleName = "新手理财专享";
                    excelName = "新手理财专享";
                    //判断是否为代理商账户
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出", "交易分成"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0){
                        if (status != null && status.equals("2")) {
                            tieleName = "新手理财专享结算";
                            excelName = "新手理财专享结算";
                        }
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    }
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
    * @Title: selectByAllCount 
    * @Description: 统计
    * @param request
    * @param userName    用户名
    * @param orderNo     订单号
    * @param startTime   开始是时间
    * @param endTime     结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime   注册结束时间
    * @param agentName   代理商id
    * @param brokerName  经纪人id
    * @param status  状态
    * @param type    类型
    * @return
    * @throws ParseException    设定文件 
    * @return Object    返回类型 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/selectByAllCount")
    @ResponseBody
    public Object selectByAllCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime,  String agentName, String brokerName, String status, String type, String redeemStartTime, String redeemEndTime) throws ParseException{
        CommonResponse cr = new CommonResponse();
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("新手理财专享交易统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XSLC.getName());
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
                Map<String, Object> map = new HashMap<String, Object>();
                map = orderService.selectByAllCount(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, status, type, redeemStartTime, redeemEndTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map);
                cr.setMsg("查询成功！");
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
        return cr;
    }


}
