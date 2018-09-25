package com.fx.xzt.sys.controller;

import com.fx.xzt.rabbitmq.RabbitmqService;
import com.fx.xzt.redis.RedisService;
import com.fx.xzt.sys.entity.DeliveryGoldConf;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.DeliveryGoldConfService;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author  liaijiao
 * @date 2018/8/30 15:31
 */
@Controller
@RequestMapping("/deliveryGoldConf")
public class DeliveryGoldConfController {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryGoldConfController.class);
    @Resource
    RedisService redisService;
    @Resource
    LogRecordService logRecordService;
    @Resource
    DeliveryGoldConfService deliveryGoldConfService;

    /**
     * 查询所有的金权交易交割黄金规格信息
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getAllDeliveryGoldConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllDeliveryGoldConf(HttpServletRequest request) throws ParseException {
        logger.debug("获取金权交易交割黄金规格信息接口");
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("获取交割规则");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JQGZSD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = deliveryGoldConfService.getAllDeliveryGoldConf(1,10);
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
     * 修改金权交易交割黄金规格信息
     * @param request
     * @param id
     * @param handlingFee 手续费率
     * @param processingFee 加工费(分)
     * @param invoiceFee 发票费率
     * @param logisticsFee 物流费(分)
     * @param custodyFee 保管费(分) 分/克天
     * @param custodyStartDate 免收保管费天数
     * @param type 交割状态
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/modifyDeliveryGoldConf",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyDeliveryGoldConf(HttpServletRequest request, String id,BigDecimal handlingFee,String  processingFee,BigDecimal invoiceFee,String logisticsFee,
                                         String custodyFee,Integer custodyStartDate,Integer type) throws ParseException {
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
                Integer ids = new Integer(id);
                Boolean b = deliveryGoldConfService.updateByPrimaryKey(ids,handlingFee,processingFee,invoiceFee,logisticsFee,custodyFee,custodyStartDate,type);
                if (b){
                    DeliveryGoldConf deliveryGoldConf = deliveryGoldConfService.getDeliveryGold(ids);
                    String key = "fx_xzt_delivery_gold_conf"+deliveryGoldConf.getId();
                    String deliveryGold = JsonUtils.toJSONString(deliveryGoldConf);
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
