package com.fx.xzt.sys.entity;

import jxl.write.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author htt
 * @ClassName: RealGoldOrder.java
 * @Description:  实金交易订单
 * @date 2017-09-26 16:02
 */
public class RealGoldOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;         //ID
    private String orderNo;  //订单号
    private Long userId;     //用户ID
    private Long goldProductId;  //黄金产品ID
    private Float buyPrice;  //买入价
    private Integer gram;  //买入克数
    private Long rmbAmount;  //买入金额
    private Integer fee;  //手续费
    private Date buyTime;  //买入时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoldProductId() {
        return goldProductId;
    }

    public void setGoldProductId(Long goldProductId) {
        this.goldProductId = goldProductId;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getGram() {
        return gram;
    }

    public void setGram(Integer gram) {
        this.gram = gram;
    }

    public Long getRmbAmount() {
        return rmbAmount;
    }

    public void setRmbAmount(Long rmbAmount) {
        this.rmbAmount = rmbAmount;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
}
