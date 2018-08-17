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
import com.fx.xzt.sys.service.StandardUserService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: StandardUserController 
* @Description: 标准户统计
* @author htt
* @date 2018-2-3 下午2:29:02 
*
 */
@Controller
@RequestMapping("/standardUser")
public class StandardUserController {

	@Resource
	StandardUserService standardUserService;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByStandardUser 
	* @Description: 查询统计
	* @param userName 用户名
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param bzh 是否标准户1：是；0：否
	* @param pageNum
	* @param pageSize
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByStandardUser")
    @ResponseBody
    public Object selectByStandardUser(HttpServletRequest request, String userName, String agentName, String brokerName, 
			String startTime, String endTime, String regStartTime, String regEndTime, String bzh,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("标准户统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.BZHTJ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                
                String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                
                PageInfo<Map<String, Object>> pageInfo = standardUserService.getByStandardUser(userName, agentNameStr, brokerName, 
                		startTime, endTime, regStartTime, regEndTime, bzh, isView, pageNum, pageSize);
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
	* @Title: excelStandardUser 
	* @Description: 查询统计--导出
	* @param userName 用户名
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param bzh 是否标准户1：是；0：否
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelStandardUser")
    @ResponseBody
    public void excelStandardUser(HttpServletRequest request, HttpServletResponse response, String userName, String agentName, String brokerName, 
			String startTime, String endTime, String regStartTime, String regEndTime, String bzh) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("标准户统计导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.BZHTJ.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
    	try {
            String tieleName = "标准户统计";
            String excelName = "标准户统计";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                List<Map<String, Object>> list = standardUserService.excelByStandardUser(userName, agentName, brokerName, 
                		startTime, endTime, regStartTime, regEndTime, bzh, isView);
                POIUtils poi = new POIUtils();
                String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "真实姓名", "是否标准户"};
                String[] colums = {"UserName", "RegisterTime", "agentName", "brokerName", "RealName", "bzh"};
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
