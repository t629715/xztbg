package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.rabbitmq.OrderCloseDirective;
import com.fx.xzt.redis.RedisService;
import com.fx.xzt.sys.entity.*;
import com.fx.xzt.sys.service.GoldRightDealConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.JsonUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private RabbitTemplate rabbitTemplate;
    @Resource
    private TopicExchange exchange;
    private final static String goldRoutingKey = "FX.SGE.AUTD";
    private final static String routingKey = "FX.SGE.AUTD.FORCE.CLOSE.REPOSITORY";

    @RequestMapping(value = "/getAllGoldRight",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllGoldRight(HttpServletRequest request) {
        logger.debug("获取金权规则接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = goldRightDealConfService.getAllGoldRight(1,10);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
                response.setMsg("操作成功！");
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
        return response;
    }


    /**
     * 修改 金权规则信息
     * @param request
     * @param id
     * @param name
     * @param contract
     * @param buyPercent
     * @param pointCount
     * @param volatility
     * @param minGramPerOrder
     * @param maxGramPerOrder
     * @param maxPositionCount
     * @param maxBuyCountPerDay
     * @param stopProfitSet
     * @param blowingUpSet
     * @return
     */
    @RequestMapping(value = "/modifyGoldRightDealConf",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyGoldRightDealConf(HttpServletRequest request, String id, String name, Integer contract,
                                    Float  buyPercent, Double pointCount, Double volatility,
                                    Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                    Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet,Integer status) {
        logger.debug("获取修改进群规则信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Long ids = new Long(id);
                Boolean b = goldRightDealConfService.updateByPrimaryKey(ids, name, contract,
                         buyPercent, pointCount, volatility,
                         minGramPerOrder, maxGramPerOrder, maxPositionCount,
                         maxBuyCountPerDay, stopProfitSet, blowingUpSet, status);
                if (b){
                    GoldRightDealConf goldRightDealConf = goldRightDealConfService.getGoldRight(new Long(id));
                    String key = "fx_xzt_gold_right_deal_cofing_"+goldRightDealConf.getId();
                    String goldRightConf = JsonUtils.toJSONString(goldRightDealConf);
                    System.out.println(key+"---------"+goldRightConf);
                    System.out.println(redisService.get(key));
                    redisService.put(key,goldRightConf);
                    System.out.println(redisService.get(key));
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(b);
                    response.setMsg("操作成功！");
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
        return response;
    }

    @RequestMapping("/force-close-repository")
    @ResponseBody
    public CommonResponse forceCloseRepository(HttpServletRequest request) {
        CommonResponse result = new CommonResponse();
        //TODO:权限用户判断
        HttpSession httpSession = request.getSession();
        Users users = (Users) httpSession.getAttribute("currentUser");

        OrderCloseDirective directive = new OrderCloseDirective();
        directive.setContractCode(goldRoutingKey);
        directive.setDirective(1);
        directive.setOperator(users.getUserName());
        directive.setDirectiveDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        logger.info("{}在{}对{}发送了平仓指令", directive.getOperator(), sdf.format(directive.getDirectiveDate()), directive.getContractCode());

        //携带确认数据 因为是手动强平，所以不用
        CorrelationData correlationData = new CorrelationData(JSON.toJSONString(directive));

        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, directive, correlationData);

        return result;
    }
}
