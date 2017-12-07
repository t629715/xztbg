package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: DealOrderController.java
 * @Description: 金权交易Controller
 * @date 2017-09-25 14:02
 */
@Controller
@RequestMapping("/dealOrder")
public class DealOrderController {
	
    @Resource
    DealOrderService dealOrderService;
    @Resource
    LogRecordService logRecordService;

    /**
     * 金权交易查询
     * @param userName   用户名
     * @param orderNo    订单号
     * @param startTime  建仓开始时间
     * @param endTime    建仓结束时间
     * @param agentName  代理商用户名
     * @param brokerName 经纪人用户名
     * @param orderState 订单状态
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/selectByDealOrder")
    @ResponseBody
    public Object selectByDealOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, Integer isUseCard,
    		String upOrDown, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金权交易交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQJY.getName());
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
                PageInfo<Map<String, Object>> pageInfo = dealOrderService.selectByDealOrder(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, orderState, isUseCard, upOrDown, pageNum, pageSize);
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
     * 金权交易导出
     * @param request
     * @param response
     * @param userName
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param regStartTime
     * @param regEndTime
     * @param agentName
     * @param brokerName
     * @param orderState
     * @throws Exception 
     */
    @RequestMapping(value="/excelDealOrderMessage")
    @ResponseBody
    public void excelDealOrderMessage(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime,  String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金权交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
    	try {
            String tieleName = "金权交易";
            String excelName = "金权交易";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                List<Map<String, Object>> list = dealOrderService.excelDealOrderMessage(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, orderState, isUseCard, upOrDown);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        map.put("upOrDown", ConstantUtil.dealOrderUpOrDown.toMap().get(map.get("upOrDown").toString()));
                        
                        Object buyPreRmbObj =  map.get("buyPreRmb");
                        Object buyAfterRmbObj =  map.get("buyAfterRmb");
                        Object ensureAmountObj =  map.get("ensureAmount");
                        Object profitLossNumberObj =  map.get("profitLossNumber");
                        Object voucherValueObj =  map.get("voucherValue");
                        Object shareAmountObj = map.get("shareAmount");
                        Object registerTimeObj = map.get("registerTime");
                    	Object createTimeObj = map.get("createTime");
                    	Object endTimeObj = map.get("endTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (createTimeObj != null && createTimeObj != "") {
	               			map.put("createTime", sdf.format(sdf.parse(createTimeObj.toString())));
	                    }
	               		
	               		if (endTimeObj != null && endTimeObj != "") {
	               			map.put("endTime", sdf.format(sdf.parse(endTimeObj.toString())));
	                    }
                        
                        if (buyPreRmbObj != null && buyPreRmbObj != "") {
                        	Double buyPreRmb = Double.valueOf(buyPreRmbObj.toString());
                        	map.put("buyPreRmb", buyPreRmb/100);
                        }
                        if (buyAfterRmbObj != null && buyAfterRmbObj != "") {
                        	Double buyAfterRmb = Double.valueOf(buyAfterRmbObj.toString());
                        	map.put("buyAfterRmb", buyAfterRmb/100);
                        }
                        if (ensureAmountObj != null && ensureAmountObj != "") {
                        	Double ensureAmount = Double.valueOf(ensureAmountObj.toString());
                        	map.put("ensureAmount", ensureAmount/100);
                        }
                        if (profitLossNumberObj != null && profitLossNumberObj != "") {
                        	Double profitLossNumber = Double.valueOf(profitLossNumberObj.toString()) - Double.valueOf(ensureAmountObj.toString());
                        	map.put("profitLossNumber", profitLossNumber/100);
                        }
                        if (voucherValueObj != null && voucherValueObj != "") {
                        	Double voucherValue = Double.valueOf(voucherValueObj.toString());
                        	map.put("voucherValue", voucherValue/100);
                        }
                        if (shareAmountObj != null && shareAmountObj != "") {
                        	Double shareAmount = Double.valueOf(shareAmountObj.toString());
                        	map.put("shareAmount", shareAmount/100);
                        }
                    }
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    if (users.getPid() != null && users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间",  "经纪人", "交易订单号", "合约类型", "方向", "黄金克数", "建仓前余额", "建仓后余额",
                                "合约金额", "买入金额", "交易成本", "买入点数", "卖出点数", "建仓时间", "平仓时间", "盈亏", "交易分成"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productName", "upOrDown", "handNumber", "buyPreRmb", "buyAfterRmb",
                                "ensureAmount", "ensureAmount", "ensureAmount", "openPositionPrice", "closePositionPrice", "createTime", "endTime", "profitLossNumber", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "合约类型", "方向", "黄金克数", "建仓前余额", "建仓后余额",
                                "合约金额", "买入金额", "交易成本", "买入点数", "卖出点数", "建仓时间", "平仓时间", "盈亏"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productName", "upOrDown", "handNumber", "buyPreRmb", "buyAfterRmb",
                                "ensureAmount", "ensureAmount", "ensureAmount", "openPositionPrice", "closePositionPrice", "createTime", "endTime", "profitLossNumber"};
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
     *  金权交易查询-金额统计
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/selectByDealOrderCount")
    @ResponseBody
    public Object selectByDealOrderCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime,  String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown) throws ParseException{
        CommonResponse cr = new CommonResponse();
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金权交易统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQJY.getName());
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
                Map<String, Object> map1 = new HashMap<String, Object>();
                map = dealOrderService.selectByDealOrderCount(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, orderState, isUseCard, upOrDown);
                map1 = dealOrderService.selectByDealOrderCount2(userName, orderNo, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, orderState, isUseCard, upOrDown);
                map.put("enSureAmountSumYpc", map1.get("enSureAmountSumYpc"));
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

    /**
     * 获取对冲套利信息 -- tianliya
     * @param request
     * @return
     * @date 2017-10-12 14:02
     */
    @RequestMapping(value="/getHedgeArbitrage1")
    @ResponseBody
    public Object getHedgeArbitrage1(HttpServletRequest request){
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = dealOrderService.selectHandNumBuyAmount();
                Map map1 = new HashMap();
                map1.put("gramUp",map.get("gramUp"));
                map1.put("avgUp",map.get("avgUp"));
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map1);
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
        }
        return cr;
    }
    @RequestMapping(value="/getHedgeArbitrage2")
    @ResponseBody
    public Object getHedgeArbitrage2(HttpServletRequest request){
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = dealOrderService.selectHandNumBuyAmount();
                List list = new ArrayList();
                Map map1 = new HashMap();
                map1.put("gramDown",map.get("gramDown"));
                map1.put("avgDown",map.get("avgDown"));
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map1);
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
        }
        return cr;
    }
    @RequestMapping(value="/getHedgeArbitrage3")
    @ResponseBody
    public Object getHedgeArbitrage3(HttpServletRequest request){
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = dealOrderService.selectHandNumBuyAmount();
                List list = new ArrayList();
                Map map1 = new HashMap();
                map1.put("netValue",map.get("netValue"));
                map1.put("profit",map.get("profit"));
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map1);
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
        }
        return cr;
    }

}
