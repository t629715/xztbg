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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InOutGoldService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.PayWayService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.log.AuditLog;
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
	@Resource
    LogRecordService logRecordService;
	@Resource
    PayWayService payWayService;
	
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
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByInOutGold")
    @ResponseBody
    public Object selectByInOutGold(HttpServletRequest request, String userName,  String agentName, String brokerName, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("出入金查询查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJCX.getName());
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
                PageInfo<Map<String, Object>> pageInfo = inOutGoldService.selectByInOutGold(userName, agentName, brokerName, 
                		isView, pageNum, pageSize);
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
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("出入金查询导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJCX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
            String tieleName = "出入金查询";
            String excelName = "出入金查询";
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
                    agentNameStr = users.getUserName();
                }
                List<Map<String, Object>> list = inOutGoldService.excelInOutGold(userName, agentNameStr, brokerName, isView);
                POIUtils poi = new POIUtils();
                //判断是否为代理商账户
                String[] heads = {"姓名", "用户账号",  "代理商", "经纪人", "注册时间", "入金", "出金", "净入金", "账户余额", "黄金收益", "提金", "理财", "黄金资产买入", "黄金资产卖出"};
                String[] colums = {"realName", "userName", "agentName", "brokerName", "registerTime", "rj", "cj", "jrj", "rmb", "totalIncome", "tj","lc", "hjb", "hjs"};
                poi.doExport(request, response, list, tieleName, excelName, heads, colums);
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
    public Object selectByRechargeChannel(HttpServletRequest request, String type, String startTime, String endTime, String channel) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("出入金分析-支付渠道分析查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd"), "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
                        Long dateMs = DateUtil.convertStringToDate(endTime, "yyyy-MM-dd").getTime()+24*3600*1000;
            			eTime = DateUtil.convertDateToString(new Date(dateMs), "yyyy-MM-dd");
            		}
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByRechargeChannel(type, sTime, eTime, channel);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
      //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("出入金分析-运营商出入金分析查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd"), "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
                        Long dateMs = DateUtil.convertStringToDate(endTime, "yyyy-MM-dd").getTime()+24*3600*1000;
                        eTime = DateUtil.convertDateToString(new Date(dateMs), "yyyy-MM-dd");
            		}
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByAgent(type, sTime, eTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("出入金分析-运营商净入金分析查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            String sTime = "";
            String eTime = "";
            Date today = DateUtil.getTodayZeroDate();
            if (users != null) {
            	if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JT)) {
            		Date tomorrow = DateUtil.modify(today, 0, 0, 1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");;
            		eTime = DateUtil.convertDateToString(tomorrow, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_ZT)) {
            		Date yesterday = DateUtil.modify(today, 0, 0, -1, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(yesterday, "yyyy-MM-dd");
            		eTime = DateUtil.convertDateToString(today, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_JQT)) {
            		Date jqt = DateUtil.modify(today, 0, 0, -6, 0, 0, 0);
            		sTime = DateUtil.convertDateToString(jqt, "yyyy-MM-dd");
            	} else if (type != null && type.equals(ConstantUtil.IN_OUT_GOLD_NPER_BY)) {
            		sTime = DateUtil.getFirstDateOfMonth(DateUtil.convertDateToString(today, "yyyy-MM-dd"), "yyyy-MM-dd");
            	}
            	if (StringUtil.isNotEmpty(startTime) || StringUtil.isNotEmpty(endTime)) {
            		sTime = "";
            		eTime = "";
            		if (StringUtil.isNotEmpty(startTime)) {
            			sTime = DateUtil.convertDateToString(DateUtil.convertStringToDate(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
            		}
            		if (StringUtil.isNotEmpty(endTime)) {
                        Long dateMs = DateUtil.convertStringToDate(endTime, "yyyy-MM-dd").getTime()+24*3600*1000;
                        eTime = DateUtil.convertDateToString(new Date(dateMs), "yyyy-MM-dd");
            		}
            	}
            	List<Map<String, Object>> list = inOutGoldService.selectByAgentNet(type, sTime, eTime);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
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
     * @Title: getPayWaysForDevice
     * @Description: 查询设备的支付方式
     * @param request
     * @return
     * @throws Exception    设定文件
     * @return Object    返回类型
     * @throws
     * @author htt
     */
    @RequestMapping(value="/getPayWaysForDevice")
    @ResponseBody
    public Object getPayWaysForDevice(HttpServletRequest request) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询不同设备的支付方式");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map map = new HashMap();

                List<Map<String, Object>> list = payWayService.getDevices();
                map.put("list",list);
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
     * @Title: editPayWaysForDevice
     * @Description: 修改设备的支付方式
     * @param request
     * @return
     * @throws Exception    设定文件
     * @return Object    返回类型
     * @throws
     * @author htt
     */
    @RequestMapping(value="/editPayWaysForDevice", method= RequestMethod.POST)
    @ResponseBody
    public Object editPayWaysForDevice(HttpServletRequest request,Short payDevice, String[] payWays) throws Exception {


        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改设备的支付方式");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.CRJFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {

                int msg = payWayService.editPayWays(payDevice,payWays);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(msg);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("修改成功");
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
