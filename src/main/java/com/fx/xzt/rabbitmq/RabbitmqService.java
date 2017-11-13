package com.fx.xzt.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @date 2017/11/10
 */
@Service
public class RabbitmqService {
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqService.class);
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.gold.topic.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.gold.route.key}")
    private String goldRoutingKey;
    @Value("${gold.force.close.repository.routing}")
    private String routingKey;

    /**
     * 发送强平指令
     * @param userName 发送人
     */
    public void sendForceCloseDirectiveByUserName(String userName) {

        OrderCloseDirective directive = new OrderCloseDirective();
        directive.setContractCode(goldRoutingKey);
        directive.setDirective(1);
        directive.setOperator(userName);
        directive.setDirectiveDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("{}在{}对{}发送了平仓指令", directive.getOperator(), sdf.format(directive.getDirectiveDate()), directive.getContractCode());

        //携带确认数据 因为是手动强平，所以不用
        CorrelationData correlationData = new CorrelationData(JSON.toJSONString(directive));

        rabbitTemplate.convertAndSend(exchangeName, routingKey, directive, correlationData);
    }
}
