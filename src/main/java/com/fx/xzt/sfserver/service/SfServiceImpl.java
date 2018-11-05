package com.fx.xzt.sfserver.service;

import com.fx.xzt.sfserver.util.SfXmlUtil;
import com.fx.xzt.sfserver.util.XmlConvert;
import com.sf.csim.express.service.CallExpressServiceTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author: tianliya
 * @date: 2018/10/29
 * @description: 顺丰相关业务逻辑层
 */
@Component
public class SfServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(SfServiceImpl.class);
    /**
     * 顺丰下单返回的head
     */
    private final String SF_RES_HEAD_OK = "OK";
    /**
     * 商户号
     */
    private String clientCode = "QQDZSW";
    /**
     *  验证码
     */
    private String checkword = "cjUeKvweJix4AfUL9zke9LlDUlfmd2NK";
    /**
     * 月结卡卡号id
     */
    private String reqURL = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";

    /**
     * fetch 下单
     * @author: tianliya
     * @time: 2018/10/29
     * @param
     * @return
     */
    public SfExpressResponse generatorOrder(Map<String, String> params) {
        String xmlStr = SfXmlUtil.getOrderServiceRequestXml(params,clientCode);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, xmlStr, clientCode, checkword);
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 查询订单结果
     * @author: tianliya
     * @time: 2018/10/30
     * @param orderId
     * @return
     */
    public SfExpressResponse queryOrder(String orderId) {
        String xmlStr = SfXmlUtil.getOrderSearchServiceRequestXml(clientCode,orderId);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, xmlStr, clientCode, checkword);
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 取消订单
     * @author: tianliya
     * @time: 2018/10/30
     * @param orderId
     * @return
     */
    public SfExpressResponse cancelOrder(String orderId) {
        String xmlStr = SfXmlUtil.getConfirmRequestXml(clientCode,orderId);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, xmlStr, clientCode, checkword);
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 查询订单信息
     * @author: tianliya
     * @time: 2018/10/30
     * @param mailNo
     * @return
     */
    public SfExpressResponse queryRouter(String mailNo) {
        String xmlStr = SfXmlUtil.getRouteServiceRequestXml(clientCode,mailNo);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, xmlStr, clientCode, checkword);
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }
    private SfExpressResponse xmlConvertEntity(String xmlStr){
        XmlConvert xmlConvert = new XmlConvert(SfExpressResponse.class);
        SfExpressResponse sfExpressResponse = (SfExpressResponse) xmlConvert.unmarshal(xmlStr);
        return sfExpressResponse;
    }

    public List getRouter( String mailNo){
        SfExpressResponse sfExpressResponse = this.queryRouter(mailNo);
        if (sfExpressResponse != null){
            if (this.SF_RES_HEAD_OK.equals(sfExpressResponse.getHead())){
                List tempRouter = sfExpressResponse.getBody().getRouteResponse().getRoute();
                Collections.reverse(tempRouter);
                return tempRouter;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
