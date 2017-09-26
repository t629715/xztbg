package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author htt
 * @ClassName: DealOrder.java
 * @Description: 金权交易订单
 * @date 2017-09-25 13:23
 */
public class DealOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String orderNo;
    private Long userId;
    private Long productId;
    private Integer upOrDown;
    private Double openPositionPrice;
    private Double closePositionPrice;
    private Double pointCount;
    private Integer handNumber;
    private Integer orderState;
    private Double endProfit;
    private Integer endProfitNumber;
    private Double endLoss;
    private Integer endLossNumber;
    private Integer poundage;
    private Integer ensureAmount;
    private Integer profitLoss;
    private Integer profitLossNumber;
    private Integer display;
    private Date createTime;
    private Date endTime;
    private Integer closePositionMethod;
    private Integer closePositionUser;
    private String orderIp;
    private Integer voucherValue;
    private Double voucherDeductible;
    private Long voucherId;
    private String serverId;
    private Double buyPreRmb;
    private Double buyAfterRmb;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(Integer upOrDown) {
        this.upOrDown = upOrDown;
    }

    public Double getOpenPositionPrice() {
        return openPositionPrice;
    }

    public void setOpenPositionPrice(Double openPositionPrice) {
        this.openPositionPrice = openPositionPrice;
    }

    public Double getClosePositionPrice() {
        return closePositionPrice;
    }

    public void setClosePositionPrice(Double closePositionPrice) {
        this.closePositionPrice = closePositionPrice;
    }

    public Double getPointCount() {
        return pointCount;
    }

    public void setPointCount(Double pointCount) {
        this.pointCount = pointCount;
    }

    public Integer getHandNumber() {
        return handNumber;
    }

    public void setHandNumber(Integer handNumber) {
        this.handNumber = handNumber;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Double getEndProfit() {
        return endProfit;
    }

    public void setEndProfit(Double endProfit) {
        this.endProfit = endProfit;
    }

    public Integer getEndProfitNumber() {
        return endProfitNumber;
    }

    public void setEndProfitNumber(Integer endProfitNumber) {
        this.endProfitNumber = endProfitNumber;
    }

    public Double getEndLoss() {
        return endLoss;
    }

    public void setEndLoss(Double endLoss) {
        this.endLoss = endLoss;
    }

    public Integer getEndLossNumber() {
        return endLossNumber;
    }

    public void setEndLossNumber(Integer endLossNumber) {
        this.endLossNumber = endLossNumber;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    public Integer getEnsureAmount() {
        return ensureAmount;
    }

    public void setEnsureAmount(Integer ensureAmount) {
        this.ensureAmount = ensureAmount;
    }

    public Integer getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(Integer profitLoss) {
        this.profitLoss = profitLoss;
    }

    public Integer getProfitLossNumber() {
        return profitLossNumber;
    }

    public void setProfitLossNumber(Integer profitLossNumber) {
        this.profitLossNumber = profitLossNumber;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getClosePositionMethod() {
        return closePositionMethod;
    }

    public void setClosePositionMethod(Integer closePositionMethod) {
        this.closePositionMethod = closePositionMethod;
    }

    public Integer getClosePositionUser() {
        return closePositionUser;
    }

    public void setClosePositionUser(Integer closePositionUser) {
        this.closePositionUser = closePositionUser;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public Integer getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(Integer voucherValue) {
        this.voucherValue = voucherValue;
    }

    public Double getVoucherDeductible() {
        return voucherDeductible;
    }

    public void setVoucherDeductible(Double voucherDeductible) {
        this.voucherDeductible = voucherDeductible;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Double getBuyPreRmb() {
        return buyPreRmb;
    }

    public void setBuyPreRmb(Double buyPreRmb) {
        this.buyPreRmb = buyPreRmb;
    }

    public Double getBuyAfterRmb() {
        return buyAfterRmb;
    }

    public void setBuyAfterRmb(Double buyAfterRmb) {
        this.buyAfterRmb = buyAfterRmb;
    }
}
