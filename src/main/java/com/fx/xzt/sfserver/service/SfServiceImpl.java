package com.fx.xzt.sfserver.service;

import com.fx.xzt.sfserver.util.SfXmlUtil;
import com.fx.xzt.sfserver.util.XmlConvert;
import com.fx.xzt.sys.mapper.ConfigParamMapper;
import com.sf.csim.express.service.CallExpressServiceTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private String SF_CLIENT_CODE = "SF_CLIENT_CODE";
    /**
     *  验证码
     */
    private String SF_CHECK_WORD = "SF_CHECK_WORD";
    /**
     * 月结卡卡号id
     */
    private String SF_REQ_URL = "SF_REQ_URL";

    @Resource
    private ConfigParamMapper configParamMapper;

    /**
     * fetch 下单
     * @author:
     * @time: 2018/10/29
     * @param
     * @return
     */

    public SfExpressResponse generatorOrder(Map<String, String> params) {
        String xmlStr = SfXmlUtil.getOrderServiceRequestXml(params,configParamMapper.getValueByName(this.SF_CLIENT_CODE));
        logger.info("请求完成，返回报文:{}", xmlStr);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(configParamMapper.getValueByName(this.SF_REQ_URL),
                xmlStr, configParamMapper.getValueByName(this.SF_CLIENT_CODE), configParamMapper.getValueByName(this.SF_CHECK_WORD));
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 查询订单结果
     * @author:
     * @time: 2018/10/30
     * @param orderId
     * @return
     */
    public SfExpressResponse queryOrder(String orderId) {
        String xmlStr = SfXmlUtil.getOrderSearchServiceRequestXml(configParamMapper.getValueByName(this.SF_CLIENT_CODE),orderId);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(configParamMapper.getValueByName(this.SF_REQ_URL),
                xmlStr, configParamMapper.getValueByName(this.SF_CLIENT_CODE), configParamMapper.getValueByName(this.SF_CHECK_WORD));
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 取消订单
     * @author:
     * @time: 2018/10/30
     * @param orderId
     * @return
     */
    public SfExpressResponse cancelOrder(String orderId) {
        String xmlStr = SfXmlUtil.getConfirmRequestXml(configParamMapper.getValueByName(this.SF_CLIENT_CODE),orderId);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(configParamMapper.getValueByName(this.SF_REQ_URL),
                xmlStr, configParamMapper.getValueByName(this.SF_CLIENT_CODE), configParamMapper.getValueByName(this.SF_CHECK_WORD));
        logger.info("请求完成，返回报文:{}", respXml);
        return this.xmlConvertEntity(respXml);
    }

    /**
     * fetch 查询订单信息
     * @author:
     * @time: 2018/10/30
     * @param mailNo
     * @return
     */
    public SfExpressResponse queryRouter(String mailNo) {
        String xmlStr = SfXmlUtil.getRouteServiceRequestXml(configParamMapper.getValueByName(this.SF_CLIENT_CODE),mailNo);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(configParamMapper.getValueByName(this.SF_REQ_URL),
                xmlStr, configParamMapper.getValueByName(this.SF_CLIENT_CODE), configParamMapper.getValueByName(this.SF_CHECK_WORD));
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
        List tempRouter = new ArrayList();
        if (sfExpressResponse != null){
            if (this.SF_RES_HEAD_OK.equals(sfExpressResponse.getHead())){
                if (sfExpressResponse.getBody() != null){
                    if (sfExpressResponse.getBody().getRouteResponse() != null){
                        tempRouter = sfExpressResponse.getBody().getRouteResponse().getRoute();
                        Collections.reverse(tempRouter);
                        return tempRouter;
                    }
                }
            }
        }
        return tempRouter;
    }
}
