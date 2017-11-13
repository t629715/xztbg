package com.fx.xzt.sys.controller;

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

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InOutGoldService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InOutGoldController 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:40:45 
*
 */
@Controller
@RequestMapping("/inOutGold")
public class InOutGoldController {

	@Resource
	InOutGoldService inOutGoldService;
	
	/**
	 * 
	* @Title: selectByInOutGold 
	* @Description: 出入金查询
	* @param request
	* @param userName
	* @param agentName
	* @param brokerName
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByInOutGold")
    @ResponseBody
    public Object selectByInOutGold(HttpServletRequest request, String userName,  String agentName, String brokerName, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = inOutGoldService.selectByInOutGold(userName, agentName, brokerName, pageNum, pageSize);
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
	* @Title: excelInOutGold 
	* @Description: 出入金查询导出
	* @param request
	* @param response
	* @param userName
	* @param agentName
	* @param brokerName    设定文件 
	* @return void    返回类型 
	 * @throws Exception 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelInOutGold")
    @ResponseBody
    public void excelInOutGold(HttpServletRequest request, HttpServletResponse response, String userName, String agentName, String brokerName) throws Exception{
        try {
            String tieleName = "出入金查询";
            String excelName = "出入金查询";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getUserName();
                }
                List<Map<String, Object>> list = inOutGoldService.excelInOutGold(userName, agentNameStr, brokerName);
                if (list != null && list.size() > 0) {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for (Map<String, Object> map : list) {
                    	Object registerTimeObj = map.get("registerTime");
                    	
               		 	if (registerTimeObj != null && registerTimeObj != "") {
               		 		map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                        }
                    	map.put("cj", StringUtil.fundsHandle(map.get("cj")));
                    	map.put("rj", StringUtil.fundsHandle(map.get("rj")));
                    	map.put("finance", StringUtil.fundsHandle(map.get("finance")));
                    	map.put("totalIncome", StringUtil.fundsHandle(map.get("totalIncome")));
                    	map.put("sjAmount", StringUtil.fundsHandle(map.get("sjAmount")));
                    	map.put("cbf", StringUtil.fundsHandle(map.get("cbf")));
                    }
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    String[] heads = {"姓名", "用户账号",  "代理商", "经纪人", "注册时间", "入金", "出金", "成本", "利息", "理财", "买入黄金资金"};
                    String[] colums = {"realName", "userName", "agentName", "brokerName", "registerTime", "cj", "rj", "cbf", "totalIncome", "finance","sjAmount"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	/**
	 * 
	* @Title: selectByRechargeChannel 
	* @Description: 出入金分析--支付渠道分析
	* @param request
	* @param type 1：今天；2：昨天；3：近7天；4：本月
	* @param startTime
	* @param endTime
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByRechargeChannel")
    @ResponseBody
    public Object selectByRechargeChannel(HttpServletRequest request, String type, String startTime, String endTime) throws Exception {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = startTime;
            		eTime = endTime;
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByRechargeChannel(type, sTime, eTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
	* @Title: selectByAgent 
	* @Description: 出入金分析--运营商出入金分析
	* @param request
	* @param type 1：今天；2：昨天；3：近7天；4：本月
	* @param startTime
	* @param endTime
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByAgent")
    @ResponseBody
    public Object selectByAgent(HttpServletRequest request, String type, String startTime, String endTime) throws Exception {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = startTime;
            		eTime = endTime;
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByAgent(type, sTime, eTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
	* @Title: selectByAgentNet 
	* @Description: 出入金分析-运营商净入金分析
	* @param request
	* @param type 1：今天；2：昨天；3：近7天；4：本月
	* @param startTime
	* @param endTime
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByAgentNet")
    @ResponseBody
    public Object selectByAgentNet(HttpServletRequest request, String type, String startTime, String endTime) throws Exception {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd HH:mm:ss");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd HH:mm:ss");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = startTime;
            		eTime = endTime;
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByAgentNet(type, sTime, eTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
