package com.fx.xzt.sys.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.rabbitmq.RabbitmqService;
import com.fx.xzt.redis.RedisService;
import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldRightDealConfService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.JsonUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
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
 * @author tianliya
 * @ClassName: GoldRightDealConfController.java
 * @Description:
 * @date 2017-10-10 13:30
 */
@Controller
@RequestMapping("/goldRightDealConf")
public class GoldRightDealConfController {
    private static final Logger logger = LoggerFactory.getLogger(GoldRightDealConfController.class);
    @Resource
    RedisService redisService;
    @Resource
    GoldRightDealConfService goldRightDealConfService;
    @Resource
    private RabbitmqService rabbitmqService;
    @Resource
    LogRecordService logRecordService;

    @RequestMapping(value = "/getAllGoldRight",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllGoldRight(HttpServletRequest request) throws ParseException {
        logger.debug("获取金权规则接口");
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("获取金权规则");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQGZSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = goldRightDealConfService.getAllGoldRight(1,10);
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


    /**
     * 修改 金权规则信息
     * @param request
     * @param id 产品id
     * @param name 产品名字
     * @param contract 产品合约
     * @param buyPercent 买入金额
     * @param pointCount 点差
     * @param pointCountDown 买跌点差
     * @param pointCountUp 买涨点差
     * @param deliveryMax 交割最大百分比
     * @param deliveryMin 交割最小百分比
     * @param minProfitPercent 最低止盈点
     * @param volatility 最下小数波动
     * @param stopLossSet 最大止损设置
     * @param minLossPercent 最小持仓克数
     * @param volatilityProfitLoss 最小波动盈亏
     * @param minGramPerOrder 最小持仓克数
     * @param maxGramPerOrder 最大持仓克数
     * @param maxPositionCount 最大建仓次数
     * @param maxBuyCountPerDay
     * @param stopProfitSet 止盈设置
     * @param blowingUpSet
     * @param status
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/modifyGoldRightDealConf",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyGoldRightDealConf(HttpServletRequest request, String id, String name, Integer contract,
                                          Float  buyPercent, Double pointCount, BigDecimal pointCountDown, BigDecimal pointCountUp, BigDecimal deliveryMax, BigDecimal  deliveryMin, Float minProfitPercent, Double volatility,
                                          Double stopLossSet, Float minLossPercent, Double volatilityProfitLoss,
                                          Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                          Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet, Integer status) throws ParseException {
        logger.debug("获取修改进群规则信息接口");
        CommonResponse response = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("修改金权规则");
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
                Boolean b = goldRightDealConfService.updateByPrimaryKey(ids, name, contract,
                         buyPercent, pointCount,pointCountDown,pointCountUp,deliveryMax, deliveryMin, minProfitPercent,volatility,
                        stopLossSet, minLossPercent, volatilityProfitLoss,
                         minGramPerOrder, maxGramPerOrder, maxPositionCount,
                         maxBuyCountPerDay, stopProfitSet, blowingUpSet, status);
                if (b){
                    GoldRightDealConf goldRightDealConf = goldRightDealConfService.getGoldRight(new Long(id));
                    String key = "fx_xzt_gold_right_deal_cofing_"+goldRightDealConf.getId();
                    String goldRightConf = JsonUtils.toJSONString(goldRightDealConf);
                    redisService.put(key,goldRightConf);
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

    @RequestMapping("/force-close-repository")
    @ResponseBody
    public CommonResponse forceCloseRepository(HttpServletRequest request) throws ParseException {
        CommonResponse result = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("强制平仓");
        log.setContent("平仓失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQGZSD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        //TODO:权限用户判断
        HttpSession httpSession = request.getSession();
        Users users = (Users) httpSession.getAttribute("currentUser");
        if (users != null){
            rabbitmqService.sendForceCloseDirectiveByUserName(users.getUserName());
            log.setContent("平仓成功");
            log.setUserId(users.getId());
            result.setData(true);
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return result;
    }
}
