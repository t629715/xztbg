package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.RealGoldConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.RealGoldBuyConfService;
import com.fx.xzt.sys.service.RealGoldConfService;
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: RealGoldConfController.java
 * @Description: 实金买卖设定Controller
 * @date 2017-10-16 13:02
 */
@Controller
@RequestMapping("/realGoldConf")
public class RealGoldConfController {

    @Resource
    RealGoldConfService realGoldConfService;
    @Resource
    RealGoldBuyConfService realGoldBuyConfService;
    @Resource
    LogRecordService logRecordService;


    /**
     * 获取实金买卖设定
     * @param request
     * @return
     */
    @RequestMapping(value="/getRealGoldConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getRealGoldConf(HttpServletRequest request) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("查询实金买卖设定");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJMMSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List list = realGoldConfService.getRealGoldConf();
                if (list != null && list.size() != 0){
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(list);
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("操作成功");
                }else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(list);
                    cr.setMsg("操作失败！");
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
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

    /**
     * 编辑实金买卖设定
     * @param request
     * @param id
     * @param name
     * @param buyPoundage
     * @param insurance
     * @param logisticsFee
     * @param sellPoundage
     * @param maxBuyCount
     * @return
     */
    @RequestMapping(value="/editRealGoldConf",method=RequestMethod.POST)
    @ResponseBody
    public Object eidtRealGoldConf(HttpServletRequest request, Long id,Long sid, String name,
                                   Integer insurance, Integer logisticsFee,
                                   Float sellPoundage, Float buyPoundage,Double maxBuyCount,Double minBuyCount) throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改实金买卖设定");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJMMSD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = realGoldConfService.edit(sid,insurance, logisticsFee);
                realGoldBuyConfService.modify(id,name,buyPoundage,sellPoundage,maxBuyCount,minBuyCount);
                if (i != 0){
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(i);
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("修改成功");
                }else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(i);
                    cr.setMsg("操作失败！");
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
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

}