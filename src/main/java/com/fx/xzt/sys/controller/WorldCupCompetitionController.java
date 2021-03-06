package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.sys.service.UserMessageService;
import com.fx.xzt.sys.service.UserWithdrawCashService;
import com.fx.xzt.sys.service.WorldCupCompetitionService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.IdUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
@RequestMapping("/worldCupController")
public class WorldCupCompetitionController {
	@Resource
	WorldCupCompetitionService worldCupCompetitionService;

	@Resource
    LogRecordService logRecordService;

	private final Logger logger = LoggerFactory.getLogger(WorldCupCompetitionController.class);
	/**
	 * 
	* @Title: getAllCompetitons
	* @Description: 获取所有的赛程
	* @param request
	 * @throws ParseException
	* @throws 
	* @author tianliya
	 */
	@RequestMapping(value="/getAllCompetitons")
    @ResponseBody
    public Object getAllCompetitons(HttpServletRequest request) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取所有的赛程信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SCKZ.getName());
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
                cr = worldCupCompetitionService.getAllCompetitons(users);
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
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 15:38
     * @Description：修改赛程信息
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/modifyCompetiton",method= RequestMethod.POST )
    @ResponseBody
    public Object modifyCompetiton(HttpServletRequest request, WorldCupCompetition worldCupCompetition) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改选中的赛程信息");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.SCKZ.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            if (worldCupCompetition.getTeamB() != null && worldCupCompetition.getTeamA() != null){
                logger.info("A队的名字：{}---B队的名字：{}",worldCupCompetition.getTeamAName(),worldCupCompetition.getTeamBName());
            }
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String isView = "0";
                if (role != null && role.get("roleIsView") != null) {
                    isView = role.get("roleIsView").toString();
                }
                cr = worldCupCompetitionService.updateByPrimaryKeySelective(worldCupCompetition);
                log.setUserId(users.getId());
                log.setContent("修改成功");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
            log.setUserId(users.getId());
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
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 15:38
     * @Description：修改赛程信息
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/getGuessResult")
    @ResponseBody
    public Object getGuessResult(HttpServletRequest request) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取竞猜统计");
        log.setContent("获取成功");
        log.setModuleName(ConstantUtil.logRecordModule.SCKZ.getName());
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
                cr = worldCupCompetitionService.selectGuessResult(users);
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
            log.setUserId(users.getId());
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
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 15:38
     * @Description：输入赛程比分
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/inputScore")
    @ResponseBody
    public Object inputScore(HttpServletRequest request,WorldCupCompetition worldCupCompetition) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("输入赛程比分");
        log.setContent("操作成功");
        log.setModuleName(ConstantUtil.logRecordModule.SCKZ.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
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
                cr = worldCupCompetitionService.inputScore(users,worldCupCompetition);
                log.setUserId(users.getId());
                log.setContent("操作成功");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
            log.setUserId(users.getId());
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
