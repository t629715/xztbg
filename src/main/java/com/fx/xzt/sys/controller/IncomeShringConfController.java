package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.IncomeSharingConf;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.IncomeSharingConfService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianliya
 * @Description:
 * @date 2017-10-09 14:30
 */
@Controller
@RequestMapping("/incomeShringConf")
public class IncomeShringConfController {
    private static final Logger logger = LoggerFactory.getLogger(IncomeShringConfController.class);
    @Resource
    IncomeSharingConfService incomeSharingConfService;
    @Resource
    LogRecordService logRecordService;


    @RequestMapping(value = "/getIncomeSharingConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getIncomeSharingConf(HttpServletRequest request, Long userId) throws ParseException {
        logger.debug("获取理财产品信息接口");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询代理商分成设置");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                IncomeSharingConf incomeSharingConf = incomeSharingConfService.getIncomeSharingConf(userId);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(incomeSharingConf);
                response.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                logger.debug("没有登录");
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            logger.debug("没有登录");
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }
    /**
     * 分成设置
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifySharing",method=RequestMethod.POST)
    @ResponseBody
    public Object modifySharing(HttpServletRequest request, IncomeSharingConf incomeSharingConf) throws ParseException {
        logger.debug("修改分成设置");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改分成设置");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int b = incomeSharingConfService.updateIncomeShringConf(incomeSharingConf);
                if (b>0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(b);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("修改分成设置成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                    response.setData(b);
                    response.setMsg("操作失败！");
                    log.setUserId(users.getId());
                    log.setContent("修改分成设置失败");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }
}
