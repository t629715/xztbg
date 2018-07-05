package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.ActivityService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.WorldCupRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/worldCupRecordController")
public class WorldCupRecordController {
    @Resource
    LogRecordService logRecordService;
    @Resource
    WorldCupRecordService worldCupRecordService;

    @Resource
    ActivityService activityService;

    /**
     * 竞猜各国家队冠军人次 laj
     * @param request
     * @return
     * @throws ParseException
     */

    @RequestMapping(value="/getCount")
    @ResponseBody
    public Object getCount(HttpServletRequest request) throws ParseException {


      CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取竞猜各国家队冠军人次");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CSDCX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                cr = worldCupRecordService.getCountGuess();
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

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;

    }


    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 15:38
     * @Description：赛程结算
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/settlement")
    @ResponseBody
    public Object settlement(HttpServletRequest request,WorldCupCompetition worldCupCompetition) throws ParseException {
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
                cr  = (CommonResponse) activityService.worldCupSettlement(worldCupCompetition.getId(),new Short("1"));
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
    @RequestMapping(value="/getWinnerGuess")
    @ResponseBody
    public Object getWinnerGuess(HttpServletRequest request,Short isGuessing, Integer pageNum, Integer pageSize) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询用户猜测世界冠军结果");
        log.setContent("操作成功");
        log.setModuleName(ConstantUtil.logRecordModule.GJJGCX.getName());
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
                cr  =  worldCupRecordService.getGuessWinner(users,isGuessing,pageNum,pageSize);
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

    @RequestMapping(value="/exportWinner")
    public void exportWinner(HttpServletRequest request, HttpServletResponse response,
                              Short isGuessing) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询用户猜测世界冠军结果");
        log.setContent("操作成功");
        log.setModuleName(ConstantUtil.logRecordModule.GJJGCX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                cr = worldCupRecordService.exportGuessWinner(users,isGuessing);
                POIUtils poi = new POIUtils();
                String[] heads = {"账号","竞猜时间","竞猜结果"};
                String[] columns = {"userName","guessingTime","state"};
                poi.doExport(request, response, (List<Map<String, Object>>) cr.getData(), "竞猜冠军结果", "竞猜冠军结果", heads, columns);
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

    }

}
