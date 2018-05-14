package com.fx.xzt.sys.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.fx.xzt.sys.service.InVestGoldOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InVestGoldOrderController 
* @Description: 投资金条订单
* @author htt
* @date 2018-4-23 下午2:41:11 
*
 */
@Controller
@RequestMapping("/inVestGoldOrder")
public class InVestGoldOrderController {
	
	@Resource
    LogRecordService logRecordService;
	@Resource
	InVestGoldOrderService inVestGoldOrderService;
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @param userName
	* @param startTime
	* @param endTime
	* @param regStartTime
	* @param regEndTime
	* @param agentName
	* @param brokerName
	* @param status
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
    public Object selectByAll(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer status, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实金投资交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String agentNameStr = agentName;
                String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                PageInfo<Map<String, Object>> pageInfo = inVestGoldOrderService.selectByAll(userName, startTime, endTime, regStartTime, 
                		regEndTime, agentNameStr, brokerName, status, isView, pageNum, pageSize);
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
	* @param userName
	* @param startTime
	* @param endTime
	* @param regStartTime
	* @param regEndTime
	* @param agentName
	* @param brokerName
	* @param status
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelAll")
    @ResponseBody
    public void excelAll(HttpServletRequest request, HttpServletResponse response, 
    		String userName, String startTime, String endTime, String regStartTime, String regEndTime,  
    		String agentName, String brokerName, Integer status) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("实金投资交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQJY.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
    	try {
            String tieleName = "实金投资交易";
            String excelName = "实金投资交易";
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
                List<Map<String, Object>> list = inVestGoldOrderService.excelByAll(userName, startTime, endTime, regStartTime,
                		regEndTime, agentNameStr, brokerName, status, isView);
                if (list != null && list.size() > 0) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    df.setRoundingMode(RoundingMode.HALF_UP);
                    for (Map<String, Object> map : list) {
                        if (map.get("upOrDown").toString().equals("0")){
                            if (map.get("openPositionPrice") != null && map.get("pointCount") != null){
                                Double openPositionPrice = (Double) map.get("openPositionPrice")+(Double)map.get("pointCount");
                                map.put("openPositionPrice",df.format(openPositionPrice));
                            }
                        }else if (map.get("upOrDown").toString().equals("1")){
                            if (map.get("closePositionPrice") != null && map.get("pointCount") != null){
                                Double closePositionPrice = (Double) map.get("closePositionPrice")+(Double)map.get("pointCount");
                                map.put("closePositionPrice",df.format(closePositionPrice));
                            }
                        }
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
                    	Object costObj = map.get("cost");

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
                        if (costObj != null && costObj != "") {
                        	Double cost = Double.valueOf(costObj.toString());
                        	map.put("cost", cost/100);
                        }
                    }
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    if (users.getPid() != null && users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间",  "经纪人", "交易订单号", "合约类型", "方向", "黄金克数", "建仓前余额", "建仓后余额",
                                "合约金额", "买入金额", "交易成本", "卡券抵扣", "买入点数", "卖出点数", "建仓时间", "平仓时间", "盈亏", "交易分成"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productName", "upOrDown", "handNumber", "buyPreRmb", "buyAfterRmb",
                                "ensureAmount", "ensureAmount","cost","voucherValue", "openPositionPrice", "closePositionPrice", "createTime", "endTime", "profitLossNumber", "shareAmount"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "合约类型", "方向", "黄金克数", "建仓前余额", "建仓后余额",
                                "合约金额", "买入金额", "交易成本", "卡券抵扣","买入点数", "卖出点数", "建仓时间", "平仓时间", "盈亏"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productName", "upOrDown", "handNumber", "buyPreRmb", "buyAfterRmb",
                                "ensureAmount", "ensureAmount","cost","voucherValue",  "openPositionPrice", "closePositionPrice", "createTime", "endTime", "profitLossNumber"};
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

}
