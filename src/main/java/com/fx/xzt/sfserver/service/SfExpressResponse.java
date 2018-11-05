package com.fx.xzt.sfserver.service;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SfExpressResponse
 * @Description: 顺丰接口调用返还响应对象
 * @Author: tianliya
 * @Date: 2018/10/26
 * @Version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class SfExpressResponse implements Serializable {
    //响应状态
    @XmlElement(name = "Head")
    private String Head;
    //响应失败原因
    @XmlElement(name = "ERROR")
    private ERROR ERROR;
    //响应结果
    @XmlElement(name = "Body")
    private Body Body;


    @XmlAccessorType(XmlAccessType.NONE)
    public static class ERROR {
        @XmlAttribute(name = "code")
        private String code;
        @XmlValue
        private String text;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "ERROR{" +
                    "code='" + code + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Body {
        @XmlElement(name = "OrderResponse")
        private OrderResponse OrderResponse;
        @XmlElement(name = "RouteResponse")
        private RouteResponse RouteResponse;

        public SfExpressResponse.OrderResponse getOrderResponse() {
            return OrderResponse;
        }

        public void setOrderResponse(SfExpressResponse.OrderResponse orderResponse) {
            OrderResponse = orderResponse;
        }

        public SfExpressResponse.RouteResponse getRouteResponse() {
            return RouteResponse;
        }

        public void setRouteResponse(SfExpressResponse.RouteResponse routeResponse) {
            RouteResponse = routeResponse;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "OrderResponse=" + OrderResponse +
                    ", RouteResponse=" + RouteResponse +
                    '}';
        }
    }

    @XmlRootElement(name = "OrderResponse")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class OrderResponse {
        //订单号
        @XmlAttribute(name = "orderid")
        private String orderId;
        //运单号
        @XmlAttribute(name = "mailno")
        private String mailNo;
        //原寄地区域代码(可用于顺丰电子运单标签打印)
        @XmlAttribute(name = "origincode")
        private String originCode;
        //目的地区域代码(可用于顺丰电子运单标签打印)
        @XmlAttribute(name = "destcode")
        private String destCode;
        //筛单结果：1：人工确认 2：可收派 3：不可以收派
        @XmlAttribute(name = "filter_result")
        private String filterResult;


        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getMailNo() {
            return mailNo;
        }

        public void setMailNo(String mailNo) {
            this.mailNo = mailNo;
        }

        public String getOriginCode() {
            return originCode;
        }

        public void setOriginCode(String originCode) {
            this.originCode = originCode;
        }

        public String getDestCode() {
            return destCode;
        }

        public void setDestCode(String destCode) {
            this.destCode = destCode;
        }

        public String getFilterResult() {
            return filterResult;
        }

        public void setFilterResult(String filterResult) {
            this.filterResult = filterResult;
        }

        @Override
        public String toString() {
            return "OrderResponse{" +
                    "orderId='" + orderId + '\'' +
                    ", mailNo='" + mailNo + '\'' +
                    ", originCode='" + originCode + '\'' +
                    ", destCode='" + destCode + '\'' +
                    ", filterResult='" + filterResult + '\'' +
                    '}';
        }
    }

    @XmlRootElement(name = "RouteResponse")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class RouteResponse {
        //运单号
        @XmlAttribute(name = "mailno")
        private String mailNo;
        //路由
        @XmlElement(name = "Route")
        private List<Route> Route;

        public String getMailNo() {
            return mailNo;
        }

        public void setMailNo(String mailNo) {
            this.mailNo = mailNo;
        }

        public List<SfExpressResponse.Route> getRoute() {
            return Route;
        }

        public void setRoute(List<SfExpressResponse.Route> route) {
            Route = route;
        }

        @Override
        public String toString() {
            return "RouteResponse{" +
                    "mailNo='" + mailNo + '\'' +
                    ", Route=" + Route +
                    '}';
        }
    }

    @XmlRootElement(name = "Route")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class Route {
        //路由节点发生的时间
        @XmlAttribute(name = "accept_time")
        private String acceptTime;

        //路由节点具体描述
        @XmlAttribute(name = "remark")
        private String remark;

        //路由节点操作码
        @XmlAttribute(name = "opcode")
        private String opcode;

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOpcode() {
            return opcode;
        }

        public void setOpcode(String opcode) {
            this.opcode = opcode;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "acceptTime='" + acceptTime + '\'' +
                    ", remark='" + remark + '\'' +
                    ", opcode='" + opcode + '\'' +
                    '}';
        }
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public SfExpressResponse.ERROR getERROR() {
        return ERROR;
    }

    public void setERROR(SfExpressResponse.ERROR ERROR) {
        this.ERROR = ERROR;
    }

    public SfExpressResponse.Body getBody() {
        return Body;
    }

    public void setBody(SfExpressResponse.Body body) {
        Body = body;
    }

    @Override
    public String toString() {
        return "SfExpressResponse{" +
                "Head='" + Head + '\'' +
                ", ERROR=" + ERROR +
                ", Body=" + Body +
                '}';
    }
}
