package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.SaveGoldConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.RealGoldBuyConfService;
import com.fx.xzt.sys.service.RealGoldConfService;
import com.fx.xzt.sys.service.SaveGoldConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tianliya
 * @ClassName: RealGoldConfController.java
 * @Description: 存金宝设定Controller
 * @date 2017-10-16 13:02
 */
@Controller
@RequestMapping("/saveGoldConf")
public class SaveGoldConfController {

    @Resource
    SaveGoldConfService saveGoldConfService;
    @Resource
    LogRecordService logRecordService;


    /**
     * 获取存金宝产品
     * @param request
     * @return
     */
    @RequestMapping(value="/getSaveGoldConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getSaveGoldConf(HttpServletRequest request) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询存金宝产品列表");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJMMSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                log.setUserId(users.getId());
                cr = saveGoldConfService.getAllProduct(1,10);
                log.setContent("操作成功");
                }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

    /**
     * 编辑存金宝
     */
    @RequestMapping(value="/editSaveGoldConf",method=RequestMethod.POST)
    @ResponseBody
    public Object editSaveGoldConf(HttpServletRequest request, SaveGoldConf saveGoldConf) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改选中的存金宝");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJMMSD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                cr = saveGoldConfService.modifyById(saveGoldConf);
                log.setUserId(users.getId());
                log.setContent("修改成功");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setMsg("操作失败！");
            }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }



}