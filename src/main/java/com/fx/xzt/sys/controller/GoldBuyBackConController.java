package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.GoldBuyBackConf;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldBuyBackConfService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/11/1/001
 */
@Controller
@RequestMapping("/goldBuyBackConf")
public class GoldBuyBackConController {
    @Resource
    GoldBuyBackConfService goldBuyBackConfService;
    @Resource
    LogRecordService logRecordService;

    @RequestMapping(value="/selectGoldBuyBackConf")
    @ResponseBody
    public Object selectGoldBuyBackConf(HttpServletRequest request)throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("回购规则设定查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGGZSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                List<Map<String, Object>> list = goldBuyBackConfService.selectGoldBuyBackConf();
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }

        }catch (Exception e){
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

    @RequestMapping(value="/updateGoldBuyBackConf")
    @ResponseBody
    public Object updateGoldBuyBackConf(HttpServletRequest request,GoldBuyBackConf goldBuyBackConf)throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("回购规则设定修改");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGGZSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                Integer s = goldBuyBackConfService.updateGoldBuyBack(goldBuyBackConf);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(s);
                cr.setMsg("修改成功！");
                log.setUserId(users.getId());
                log.setContent("修改成功");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("修改失败！");
            }

        }catch (Exception e){
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("修改失败！");
            throw e;

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

}
