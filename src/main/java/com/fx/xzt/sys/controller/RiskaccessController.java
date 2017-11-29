package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.RiskaccessService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: RiskaccessController.java
 * @Description: 风险评测Controller
 * @date 2017-09-22 13:03
 */
@Controller
@RequestMapping("/riskaccess")
public class RiskaccessController {

    @Resource
    private RiskaccessService riskaccessService;
    @Resource
    LogRecordService logRecordService;

    /**
     *  风险评测列表
     * @param userName   用户名
     * @param realName   真实姓名
     * @param startTime  评测开始时间
     * @param endTime    评测结束时间
     * @param accessLevel 评测等级
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/selectByRiskaccessAll")
    @ResponseBody
    public Object selectByRiskaccessAll(HttpServletRequest request, String userName, String realName, String startTime, String endTime, String accessLevel, @RequestParam Integer pageNum, @RequestParam  Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("风险评测查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.FXPC.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
			 if (users != null) {
				 PageInfo<Map<String, Object>> pageInfo = riskaccessService.getByRiskaccessAll(userName, realName, startTime, endTime, accessLevel, pageNum, pageSize);
		            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
		            cr.setData(pageInfo);
		            cr.setMsg("操作成功！");
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
     *  更改风险评测等级
     * @param level
     * @param accessId
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value="/updateLevelById")
    @ResponseBody
    public Object updateLevelById(HttpServletRequest request, @RequestParam String level, @RequestParam String accessId) throws ParseException {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        //操作日志
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("风险评测等级修改");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.FXPC.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
			 if (users != null) {
				 if (StringUtil.isNotEmpty(level) && StringUtil.isNotEmpty(accessId)) {
		                int flag = riskaccessService.updateLevelById(level, accessId);
		                if (flag > 0) {
		                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
		                    cr.setMsg("操作成功！");
		                    log.setUserId(users.getId());
			                log.setContent("修改成功；信息：accessId：" + accessId + ";;level:" + level);
		                }
		            }
			 } else {
				 cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
	             cr.setData("{}");
	             cr.setMsg("无操作权限！");
			 }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
}
