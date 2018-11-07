package com.fx.xzt.sfserver.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: tianliya
 * @date: 2018/10/29
 * @description: 顺丰相关接口工具类
 */
@Component
public class SfXmlUtil {


    /**
     * fetch 生成顺丰下单接口xml
     *
     * @return
     * @author: tianliya
     * @time: 2018/10/29
     */
    public static String getOrderServiceRequestXml(Map<String, String> params, String clientCode) {
        //订单号
        String orderCode = params.get("orderNo");
        String jContact = params.get("jContact");
        String jTel = params.get("jTel");
        String jAddress = params.get("jAddress");

        String jCompany = params.get("jCompany");
        //收件人
        String dContact = params.get("dContact");
        //收件人电话
        String dTel = params.get("dTel");
        //收件人详细地址
        String dAddress = params.get("dAddress");
        //商品名称
        String itemName = params.get("itemName");
        //商品数量
        String itemCount = params.get("itemCount");
        //客户月结卡号
        String custid = params.get("custid");

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<Request service='OrderService' lang='zh-CN'>");
        strBuilder.append("<Head>" + clientCode + "</Head>");
        strBuilder.append("<Body>");
        strBuilder.append("<Order").append(" ");
        strBuilder.append("orderid='" + orderCode.toString().trim() + "" + "'").append(" ");
        //返回顺丰运单号
        strBuilder.append("is_gen_bill_no='1'").append(" ");
        //寄件方信息
        strBuilder.append("j_company='" + jCompany + "'").append(" ");
        strBuilder.append("j_contact='" + jContact + "'").append(" ");
        strBuilder.append("j_tel='" + jTel + "'").append(" ");
        strBuilder.append("j_address='" + jAddress + "'").append(" ");
        //收件方信息
        strBuilder.append("d_contact='" + dContact.trim() + "'").append(" ");
        strBuilder.append("d_tel='" + dTel.trim() + "'").append(" ");
        strBuilder.append("d_address='" + dAddress.trim() + "'").append(" ");
        strBuilder.append("custid='" + custid.trim() + "'").append(" ");


        strBuilder.append(" > ");
        //货物信息
        strBuilder.append("<Cargo").append(" ");
        strBuilder.append("name='" + itemName + "'").append(" ");
        strBuilder.append("count='" + itemCount + "'").append(" ");
        strBuilder.append(">");
        strBuilder.append("</Cargo>");

        strBuilder.append("</Order>");
        strBuilder.append("</Body>");
        strBuilder.append("</Request>");

        return strBuilder.toString();
    }


    /**
     * fetch 生成下单结果查询xml
     *
     * @param clientCode
     * @param orderId
     * @return
     * @author: tianliya
     * @time: 2018/10/29
     */
    public static String getOrderSearchServiceRequestXml(String clientCode, String orderId) {
        //客户订单号
        String orderNo = orderId;
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<Request service='OrderSearchService' lang='zh-CN'>");
        strBuilder.append("<Head>" + clientCode + "</Head>");
        strBuilder.append("<Body>");
        strBuilder.append("<OrderSearch").append(" ");
        strBuilder.append("orderid='" + orderNo + "'").append(" /> ");
        strBuilder.append("</Body>");
        strBuilder.append("</Request>");
        return strBuilder.toString();
    }


    /**
     * fetch 生成顺丰路由查询接口xml
     *
     * @param clientCode
     * @param mailNo
     * @return
     * @author: tianliya
     * @time: 2018/10/29
     */
    public static String getRouteServiceRequestXml(String clientCode, String mailNo) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<Request service='RouteService' lang='zh-CN'>");
        strBuilder.append("<Head>" + clientCode + "</Head>");
        strBuilder.append("<Body>");
        strBuilder.append("<RouteRequest").append(" ");
        strBuilder.append("tracking_type='1'").append(" ");
        strBuilder.append("method_type='1'").append(" ");
        //运单号
        strBuilder.append("tracking_number='" + mailNo + "'").append(" >");
        strBuilder.append("</RouteRequest>");
        strBuilder.append("</Body>");
        strBuilder.append("</Request>");
        return strBuilder.toString();
    }


    /**
     * fetch 生成取消订单接口xml
     *
     * @param clientCode
     * @param orderNo
     * @return
     * @author: tianliya
     * @time: 2018/10/29
     */
    public static String getConfirmRequestXml(String clientCode, String orderNo) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<Request service='OrderConfirmService' lang='zh-CN'>");
        strBuilder.append("<Head>").append(clientCode).append("</Head>");
        strBuilder.append("<Body>");
        strBuilder.append("<OrderConfirm").append(" ");
        strBuilder.append("orderid='").append(orderNo).append("' ");
        strBuilder.append("dealtype='2'>").append(" ");
        strBuilder.append("</OrderConfirm>");
        strBuilder.append("</Body>");
        strBuilder.append("</Request>");
        return strBuilder.toString();
    }


    public static Map xmltoMap(String xml) {
        try {
            Map map = new ConcurrentHashMap();
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                map.put(elm.attributeValue("label"), elm.getText());
                elm = null;
            }
            node = null;
            nodeElement = null;
            document = null;
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
