package com.fx.xzt.sys.controller;

import java.text.DecimalFormat;
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
import com.fx.xzt.sys.service.GoldIncomeRecordService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldIncomeRecordController 
* @Description: 黄金收益记录
* @author htt
* @date 2017-9-30 下午2:28:38 
*
 */
@Controller
@RequestMapping("/goldIncomeRecord")
public class GoldIncomeRecordController {

	@Resource
	GoldIncomeRecordService goldIncomeRecordService;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByGoldIncome 
	* @Description: 黄金收益查询
	* @param request
	* @param userName   用户账号
	* @param startTime  发放开始时间
	* @param endTime    发放结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param type       类型1：昨天；2：近7天；3：本月；4：上个月
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldIncome")
    @ResponseBody
    public Object selectByGoldIncome(HttpServletRequest request, String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer type, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
        CommonResponse cr = new CommonResponse();
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金收益结算查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSYJS.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            String startTypeTime = "";
            String endTypeTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
            	if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_BY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_SGY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(
            				DateUtil.modify(today, 0, -1, 0, 0, 0, 0), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
                PageInfo<Map<String, Object>> pageInfo = goldIncomeRecordService.selectByGoldIncome(userName, startTime, endTime, 
                		startTypeTime, endTypeTime, agentName, brokerName, type, isView, pageNum, pageSize);
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
	* @Title: excelGoldIncome 
	* @Description: 黄金收益查询-导出
	* @param request
	* @param response
	* @param userName   用户账号
	* @param startTime  发放开始时间
	* @param endTime    发放结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param type       类型1：昨天；2：近7天；3：本月；4：上个月
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelGoldIncome")
    @ResponseBody
    public void excelGoldIncome(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer type) throws Exception{
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金收益结算导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSYJS.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            String tieleName = "黄金收益记录";
            String excelName = "黄金收益记录";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            String startTypeTime = "";
            String endTypeTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
            	if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_BY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_SGY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(
            				DateUtil.modify(today, 0, -1, 0, 0, 0, 0), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.getLastDateOfMonth(DateUtil.convertDateToString(
            				DateUtil.modify(today, 0, -1, 0, 0, 0, 0), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
                List<Map<String, Object>> list = goldIncomeRecordService.excelGoldIncome(userName, startTime, endTime, 
                		startTypeTime, endTypeTime, agentName, brokerName, type, isView);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	Object incomeObj =  map.get("income");
                    	Object totalIncomObj =  map.get("totalIncom");
                    	//Object priceObj =  map.get("price");
                    	Object incomePercentObj = map.get("incomePercent");
                    	Object registerTimeObj = map.get("registerTime");
                    	Object createTimeObj = map.get("createTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
               		 	
	               		if (createTimeObj != null && createTimeObj != "") {
	               			map.put("createTime", sdf.format(sdf.parse(createTimeObj.toString())));
	                    }
                    	
                        if (incomeObj != null && incomeObj != "") {
                        	Double income = Double.valueOf(incomeObj.toString());
                        	map.put("income", income/100);
                        }
                        if (totalIncomObj != null && totalIncomObj != "") {
                        	Double totalIncom = Double.valueOf(totalIncomObj.toString());
                        	map.put("totalIncom", totalIncom/100);
                        }
                        /*if (priceObj != null && priceObj != "") {
                        	Double price = Double.valueOf(priceObj.toString());
                        	map.put("price", price/100);
                        }*/
                        if (incomePercentObj != null && incomePercentObj != "") {
                        	Double incomePercent = Double.valueOf(incomePercentObj.toString());
                        	DecimalFormat df = new DecimalFormat("0.00%");
                        	map.put("incomePercent", df.format(incomePercent));
                        }
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "代理商",  "经纪人", "结算时所持黄金克重", "收盘金价", "发放收益", "已发放收益总计", "收益率", "发放时间"};
                    String[] colums = {"userName", "agentName", "brokerName", "gram", "price", "income", "totalIncom", "incomePercent", "createTime"};
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
	* @Title: selectByGoldIncomeCount 
	* @Description: 黄金收益查询-统计
	* @param request
	* @param userName   用户账号
	* @param startTime  发放开始时间
	* @param endTime    发放结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param type       类型1：昨天；2：近7天；3：本月；4：上个月
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
    @RequestMapping(value="/selectByGoldIncomeCount")
    @ResponseBody
    public Object selectByGoldIncomeCount(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer type) throws Exception{
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金收益结算统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSYJS.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String startTypeTime = "";
            String endTypeTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		startTypeTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_BY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.GOLD_INCOME_RECORD_TYPE_SGY)) {
            		startTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(
            				DateUtil.modify(today, 0, -1, 0, 0, 0, 0), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            		endTypeTime = DateUtil.getLastDateOfMonth(DateUtil.convertDateToString(
            				DateUtil.modify(today, 0, -1, 0, 0, 0, 0), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
                Map<String, Object> map = new HashMap<String, Object>();
                map = goldIncomeRecordService.selectByGoldIncomeCount(userName, startTime, endTime, 
                		startTypeTime, endTypeTime, agentName, brokerName, type);
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
    * @Title: selectByGoldGramCount 
    * @Description: 黄金收益查询-黄金克重统计
    * @param request
    * @return
    * @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/selectByGoldGramCount")
    @ResponseBody
    public Object selectByGoldGramCount(HttpServletRequest request) throws Exception{
        CommonResponse cr = new CommonResponse();
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金收益结算黄金总克重统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSYJS.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {           	
                Map<String, Object> map = new HashMap<String, Object>();
                map = goldIncomeRecordService.selectByGoldGramCount();
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
