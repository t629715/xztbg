package com.fx.xzt.sys.controller;


import com.fx.xzt.sys.entity.ConfigParam;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.ConfigParamService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.INEG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.wu
 * @Created time : 2018/10/9 9:44
 * @Description :
 */
@RestController
@RequestMapping("/investGold")
public class PrevClosePriceController {

    @Autowired
    private ConfigParamService configParamService;

    @Autowired
    private LogRecordService logRecordService;

    private static final Logger logger = LoggerFactory.getLogger(PrevClosePriceController.class);

    @RequestMapping("/getInestGoldPrice")
    public CommonResponse getPrevClosePrice(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Users users = (Users) httpSession.getAttribute("currentUser");
        LogRecord logRecord = new LogRecord();
        logRecord.setUserId(users.getId());
        logRecord.setContent("查询成功");
        logRecord.setTitle("查询金条投资报价");
        logRecord.setModuleName(ConstantUtil.logRecordModule.JTTZ.getName());
        logRecord.setType(ConstantUtil.logRecordType.CX.getIndex());
        logRecord.setCreateTime(new Date());
        logRecord.setIp(IPUtil.getHost(request));
        CommonResponse cr = new CommonResponse();
        try {
            ConfigParam prevClosePx = configParamService.selectConfigParamByKey("prevClosePx");
            List<ConfigParam> result = new ArrayList<>(1);
            result.add(prevClosePx);
            cr.setMsg("操作成功!");
            cr.setCode(Constant.RESCODE_SUCCESS_MSG);
            cr.setData(result);
        } catch (Exception e) {
            logRecord.setContent("查询失败");
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(logRecord);
        AuditLog.info(logRecord.toString());
        return cr;
    }

    @RequestMapping("/updateInestGoldPrice")
    public CommonResponse updatePrevClosePrice(HttpServletRequest request, String setPrice) {
        try{
           Double.valueOf(setPrice);
        }catch(Exception e){
            return   new CommonResponse(Integer.valueOf(1003),"输入错误!",null);
        }
        HttpSession httpSession = request.getSession();
        Users users = (Users) httpSession.getAttribute("currentUser");
        LogRecord logRecord = new LogRecord();
        logRecord.setUserId(users.getId());
        logRecord.setTitle("修改金条投资报价");
        logRecord.setModuleName(ConstantUtil.logRecordModule.JTTZ.getName());
        logRecord.setType(ConstantUtil.logRecordType.XG.getIndex());
        logRecord.setCreateTime(new Date());
        logRecord.setIp(IPUtil.getHost(request));
        CommonResponse cr = new CommonResponse();
        try {
            ConfigParam prevClosePx = configParamService.selectConfigParamByKey("prevClosePx");
            logRecord.setContent("金条投资修改当前报价由" + prevClosePx.getParamValue() + "改为" + setPrice + "成功");
            int status = configParamService.update("prevClosePx", setPrice, null, null, null);
            if (status == 1) {
                logRecord.setContent("金条投资修改当前报价由" + prevClosePx.getParamValue() + "改为" + setPrice + "失败");
                cr.setCode(Constant.RESCODE_SUCCESS);
                cr.setMsg("操作成功!");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setMsg("操作失败!");
            }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setMsg("操作失败!");
            logRecord.setContent("修改失败");
            throw e;
        }
        logRecordService.add(logRecord);
        AuditLog.info(logRecord.toString());
        return cr;
    }

}
