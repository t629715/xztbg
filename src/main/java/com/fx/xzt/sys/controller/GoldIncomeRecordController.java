package com.fx.xzt.sys.controller;

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

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldIncomeRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
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
            		endTypeTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
                PageInfo<Map<String, Object>> pageInfo = goldIncomeRecordService.selectByGoldIncome(userName, startTime, endTime, 
                		startTypeTime, endTypeTime, agentName, brokerName, type, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
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
            // e.printStackTrace();
        }
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

        try {
            String tieleName = "黄金收益记录";
            String excelName = "黄金收益记录";
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
                List<Map<String, Object>> list = goldIncomeRecordService.excelGoldIncome(userName, startTime, endTime, 
                		startTypeTime, endTypeTime, agentName, brokerName, type);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	Object incomeObj =  map.get("income");
                    	Object totalIncomObj =  map.get("totalIncom");
                    	Object priceObj =  map.get("price");
                        if (incomeObj != null && incomeObj != "") {
                        	Double income = Double.valueOf(incomeObj.toString());
                        	map.put("income", income/100);
                        }
                        if (totalIncomObj != null && totalIncomObj != "") {
                        	Double totalIncom = Double.valueOf(totalIncomObj.toString());
                        	map.put("totalIncom", totalIncom/100);
                        }
                        if (priceObj != null && priceObj != "") {
                        	Double price = Double.valueOf(priceObj.toString());
                        	map.put("price", price/100);
                        }
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "代理商",  "经纪人", "结算时所持黄金克重", "收盘金价", "发放收益", "已发放收益总计", "收益率", "发放时间"};
                    String[] colums = {"userName", "agentName", "brokerName", "gram", "price", "income", "totalIncom", "incomePercent", "createTime"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
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
        return cr;
    }

}
