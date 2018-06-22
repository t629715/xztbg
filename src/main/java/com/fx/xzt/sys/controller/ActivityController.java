package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Activity;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.ActivityService;
import com.fx.xzt.sys.service.DealOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author htt
 * @ClassName: DealOrderController.java
 * @Description: 金权交易Controller
 * @date 2017-09-25 14:02
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    ActivityService activityService;
    @Resource
    LogRecordService logRecordService;

    Logger logger = LoggerFactory.getLogger(ActivityController.class);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 16:19
     * @Description：获取所有的活动
     */
    @RequestMapping(value = "/getActivities")
    @ResponseBody
    public Object getActivities(HttpServletRequest request) throws ParseException {

        CommonResponse cr = new CommonResponse();

        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("活动查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HDKZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));

        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                log.setUserId(users.getId());
                cr = activityService.getActivities();
                if (cr.getCode() == 1001) {
                    log.setContent("查询成功");
                }
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
     * @CreateTime：2018/6/12 16:20
     * @Description：删除活动
     */
    @RequestMapping(value = "/removeActivity")
    @ResponseBody
    public CommonResponse removeActivity(HttpServletRequest request, Long id) throws Exception {
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("活动控制-删除活动");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.HDKZ.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        CommonResponse commonResponse = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                log.setUserId(users.getId());
                commonResponse = activityService.removeActivity(id);
                if (commonResponse.getCode() == 1001) {
                    log.setContent("删除成功");
                }
            } else {
                commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                commonResponse.setMsg("操作失败！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 16:26
     * @Description：下架活动
     */
    @RequestMapping(value = "/modifyActivity")
    @ResponseBody
    public CommonResponse modifyActivity(HttpServletRequest request, Activity activity) throws Exception {
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        if (activity.getIsPopup() != null) {
            log.setTitle("活动控制-修改活动是否首页弹出");
            if (activity.getIsPopup() == 0) {
                log.setContent("取消弹出失败");
            } else {
                log.setContent("设置弹出失败");
            }
        } else {
            log.setTitle("活动控制-修改活动");

            log.setContent("修改失败");
        }
        log.setModuleName(ConstantUtil.logRecordModule.HDKZ.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        CommonResponse commonResponse = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                log.setUserId(users.getId());
                commonResponse = activityService.modifyActivity(activity);
                if (commonResponse.getCode() == 1001) {
                    log.setContent("成功");
                }
            } else {
                commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                commonResponse.setMsg("操作失败！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 16:26
     * @Description：下架活动
     */
    @RequestMapping(value = "/addActivity")
    @ResponseBody
    public CommonResponse addActivity(HttpServletRequest request, Activity activity) throws Exception {
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("进入活动控制-添加活动接口");
        LogRecord log = new LogRecord();
        log.setTitle("活动控制-添加活动");
        log.setContent("添加失败");
        log.setModuleName(ConstantUtil.logRecordModule.HDKZ.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        CommonResponse commonResponse = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                log.setUserId(users.getId());
                commonResponse = activityService.addActivity(activity);
                if (commonResponse.getCode() == 1001) {
                    log.setContent("成功");
                }
            } else {
                commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                commonResponse.setMsg("操作失败！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return commonResponse;
    }


}
