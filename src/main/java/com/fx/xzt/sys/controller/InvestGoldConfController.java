package com.fx.xzt.sys.controller;

import com.fx.xzt.redis.RedisService;

import com.fx.xzt.sys.entity.InvestGoldConf;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InvestGoldConfService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.JsonUtils;
import com.github.pagehelper.PageInfo;
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
import java.util.Map;

/**
 * Description：
 * Author: liaijiao
 * Date:  2018/10/9 10:45
 */
@Controller
@RequestMapping("/investGoldConf")
public class InvestGoldConfController {
    private static final Logger logger = LoggerFactory.getLogger(InvestGoldConfController.class);
    @Resource
    InvestGoldConfService investGoldConfService;
    @Resource
    RedisService redisService;
    @Resource
    LogRecordService logRecordService;
    @RequestMapping(value = "/getAllInvestGoldConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllInvestGoldConf(HttpServletRequest request) throws ParseException {
        logger.debug("获取投资金条规格信息接口");
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("获取投资金条规格信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JTTZCPGZ.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = investGoldConfService.getAllInvestGoldConf(1,10);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
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

    @RequestMapping(value = "/modifyInvestGoldConf",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyInvestGoldConf(HttpServletRequest request, String id, String name, Integer goldWeight, String withdrawService,
                                         Integer minBuyCount, Integer maxBuyCount, String logisticsFee, String imgUrl) throws ParseException {
        logger.debug("获取修改金权交易交割黄金规格信息接口");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改交割规则");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQGZSD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Long ids = new Long(id);
                Boolean b = investGoldConfService.updateByPrimaryKey(ids,name,goldWeight,withdrawService,minBuyCount,maxBuyCount,logisticsFee,imgUrl);
                if (b){
                    InvestGoldConf investGoldConf = investGoldConfService.getInvestGoldConf(ids);
                    String key = "fx_xzt_invest_gold_conf"+investGoldConf.getId();
                    String deliveryGold = JsonUtils.toJSONString(investGoldConf);
                    redisService.put(key,deliveryGold);
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(b);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("修改成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                    response.setData(b);
                    response.setMsg("操作失败！");
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
