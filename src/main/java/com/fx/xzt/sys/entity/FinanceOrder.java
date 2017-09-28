package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author htt
 * @ClassName: FinanceOrder.java
 * @Description: 理财订单
 * @date 2017-09-27 11:14
 */
public class FinanceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;          //主键
    private Long userId;      //用户主键
    private String orderNo;   //订单号
    private Long productId;   //产品主键
    private String prooductNo;//产品编号
    private String productName;//产品名称
    private Integer buyAmount;  //买入金额
    private Date buyTime;     //买入时间
    private Date redeemTime;    //赎回时间
    private Integer status;   //1持有中；2已赎回
    private Integer income;   //收益支出
    private Integer initialPrice; //初始价
    private Integer clearPrice;   //结算价

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProoductNo() {
        return prooductNo;
    }

    public void setProoductNo(String prooductNo) {
        this.prooductNo = prooductNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getClearPrice() {
        return clearPrice;
    }

    public void setClearPrice(Integer clearPrice) {
        this.clearPrice = clearPrice;
    }
}
