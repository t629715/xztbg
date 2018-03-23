package com.fx.xzt.sys.controller;

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
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserGoldAccountActivityRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserGoldAccountActivityRecordController 
* @Description: 活动-黄金领取
* @author htt
* @date 2017-12-6 上午10:24:47 
*
 */
@Controller
@RequestMapping("/userGoldAccountActivityRecord")
public class UserGoldAccountActivityRecordController {

	@Resource
	UserGoldAccountActivityRecordService recordService;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @param userName
	* @param startTime
	* @param endTime
	* @param agentName
	* @param brokerName
	* @param activityType
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
    		String agentName, String brokerName, Integer activityType, 
    		 @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("活动黄金领取查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJLQ.getName());
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
                PageInfo<Map<String, Object>> pageInfo = recordService.selectByAll(userName, startTime, endTime, agentName, brokerName, activityType, isView, pageNum, pageSize);
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
	* @Title: excelByAll 
	* @Description: 导出
	* @param request
	* @param response
	* @param userName
	* @param startTime
	* @param endTime
	* @param agentName
	* @param brokerName
	* @param activityType
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelByAll")
    @ResponseBody
    public void excelByAll(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer activityType) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("活动黄金领取导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJLQ.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
    	try {
            String tieleName = "活动黄金领取";
            String excelName = "活动黄金领取";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            String isView = "0";
	        if (role != null && role.get("roleIsView") != null) {
	            isView = role.get("roleIsView").toString();
	        }
            if (users != null) {
                List<Map<String, Object>> list = recordService.excelAll(userName, startTime, endTime, agentName, brokerName, activityType, isView);
                if (list != null && list.size() > 0) {
                	for (Map<String, Object> map : list) {
                        map.put("activityType", ConstantUtil.activityType.toMap().get(map.get("activityType").toString()));
                	}
                }
                POIUtils poi = new POIUtils();
                String[] heads = {"用户账号", "代理商",  "经纪人", "黄金克重", "描述", "活动类型", "领取时间"};
                String[] colums = {"userName", "agentName", "brokerName", "amount", "description", "activityType", "createTime"};
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
	
}
