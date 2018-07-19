package com.fx.xzt.sys.controller;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.FinanceRegulargoldProduct;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceConfRegulargoldService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tianliya
 * @ClassName: FinanceConfController.java
 * @Description:
 * @date 2017-10-09 14:30
 */
@Controller
@RequestMapping("/financeConfRegulargold")
public class FinanceConfRegulargoldController {
    private static final Logger logger = LoggerFactory.getLogger(FinanceConfRegulargoldController.class);
    @Resource
    FinanceConfRegulargoldService financeConfRegulargoldService;

    @Resource
    LogRecordService logRecordService;


    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:55
     * @Description：获取所有的金生金信息
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAllFinanceConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllFinanceConf(HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException, GlobalException {
        logger.debug("获取所有的定期产品信息");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取理财产品信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.DQJGL.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                response = financeConfRegulargoldService.getByConditions(pageNum, pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("请登录！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 14:03
     * @Description：根据id删除定期金理财产品
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeById",method=RequestMethod.POST)
    @ResponseBody
    public Object removeById(HttpServletRequest request, String id) throws ParseException, GlobalException {
        logger.debug("删除定期金理财产品");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("删除定期金理财产品");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.DQJGL.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                response = financeConfRegulargoldService.deleteOneById(id.toString());
                if (response.getCode() == 1000){
                    log.setUserId(users.getId());
                    log.setContent("删除成功");
                }
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setMsg("请登录！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 14:06
     * @Description：获取修改定期金理财产品
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifySelective",method=RequestMethod.POST)
    @ResponseBody
    public Object modifySelective(HttpServletRequest request, FinanceRegulargoldProduct financeConfRegulargold) throws ParseException, GlobalException {
        logger.debug("获取修改定期金理财产品");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取修改定期金理财产品");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.DQJGL.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                response = financeConfRegulargoldService.modifyOneSelective(financeConfRegulargold);
                if (response.getCode() == 1000){
                    log.setUserId(users.getId());
                    log.setContent("修改成功");
                }
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setMsg("请登录！");
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }
}
