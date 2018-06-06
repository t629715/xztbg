package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.InfoPush;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoPushService;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by tianliya on 2017/10/15.
 */
@Controller
@RequestMapping("/infoPush")
public class InfoInfoPushController {
    @Autowired
    InfoPushService infoPushService;
    @Resource
    LogRecordService logRecordService;

    /**
     * 获取首页推送资讯  tianliya
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/getAllPushes",method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse getAllPushes(HttpServletRequest request, Integer pageNum, Integer pageSize) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("查询首页推送资讯信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.CXTSZX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                response = infoPushService.getAllPushes(pageNum,pageSize);
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * @param request
     * @return
     * @Author: tianliya
     * @Decription:删除选中的推送资讯信息
     * @Date 2017/10/15 22:13
    */
    @RequestMapping(value="/removeInfoPush",method=RequestMethod.POST)
    @ResponseBody
    public CommonResponse removeInfoPush(HttpServletRequest request, String id) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("删除推送的资讯信息");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.XODZX.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            log.setUserId(users.getId());
            if (users != null) {
                response = infoPushService.removeById(id);
                if (response.getCode() == Constant.RESCODE_SUCCESS_MSG) {
                    log.setContent("删除成功");
                }
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }


    /**
     * 发布
     * @param request
     * @return
     */
    @RequestMapping(value="/addPushInfo")
    @ResponseBody
    public CommonResponse addPushInfo(HttpServletRequest request, InfoPush infoPush) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("添加首页推送资讯");
        log.setContent("添加失败");
        log.setModuleName(ConstantUtil.logRecordModule.TJTSZX.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            log.setUserId(users.getId());
            if (users != null) {
                response = infoPushService.addOnePush(infoPush);
                if (response.getCode() == Constant.RESCODE_SUCCESS_MSG) {
                    log.setContent("发布成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }
}
