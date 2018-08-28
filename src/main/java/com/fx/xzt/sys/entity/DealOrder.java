package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author htt
 * @ClassName: DealOrder.java
 * @Description: 金权交易订单
 * @date 2017-09-25 13:23
 */
public class DealOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    private Long id;

    /**
     * 交易订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     *产品id
     */
    private Long productId;

    /**
     * 0：涨；1：跌
     */
    private Integer upOrDown;

    /**
     * 买入价/建仓价
     */
    private Double openPositionPrice;

    /**
     * 平仓价
     */
    private Double closePositionPrice;

    /**
     * 点数
     */
    private Double pointCount;

    /**
     *买涨点差
     */
    private BigDecimal pointCountUp;

    /**
     *买跌点差
     */
    private BigDecimal pointCountDown;

    /**
     * 克数/手数
     */
    private Integer handNumber;

    /**
     * 0：交易中；1：平仓（终止交易);2:待定,3:已交割
     */
    private Integer orderState;

    /**
     * 止盈点
     */
    private Double endProfit;

    /**
     *交割最小点数(黄金单价)
     */
    private BigDecimal deliveryMinPoint;

    /**
     *交割最大点数(黄金单价)
     */
    private BigDecimal deliveryMaxPoint;

    /**
     *进入待定状态时间
     */
    private Date intoDeliveryDate;

    private Integer endProfitNumber;
    private Double endLoss;
    private Integer endLossNumber;
    private Integer poundage;

    /**
     *手续费率(0.01代表1%)
     */
    private Integer poundageRate;

    private Integer ensureAmount;

    /**
     *买入金额(订单金额+手续费)
     */
    private Integer buyAmount;

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

    public BigDecimal getPointCountUp() {
        return pointCountUp;
    }

    public void setPointCountUp(BigDecimal pointCountUp) {
        this.pointCountUp = pointCountUp;
    }

    public BigDecimal getPointCountDown() {
        return pointCountDown;
    }

    public void setPointCountDown(BigDecimal pointCountDown) {
        this.pointCountDown = pointCountDown;
    }

    public BigDecimal getDeliveryMinPoint() {
        return deliveryMinPoint;
    }

    public void setDeliveryMinPoint(BigDecimal deliveryMinPoint) {
        this.deliveryMinPoint = deliveryMinPoint;
    }

    public BigDecimal getDeliveryMaxPoint() {
        return deliveryMaxPoint;
    }

    public void setDeliveryMaxPoint(BigDecimal deliveryMaxPoint) {
        this.deliveryMaxPoint = deliveryMaxPoint;
    }

    public Date getIntoDeliveryDate() {
        return intoDeliveryDate;
    }

    public void setIntoDeliveryDate(Date intoDeliveryDate) {
        this.intoDeliveryDate = intoDeliveryDate;
    }

    public Integer getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(Integer poundageRate) {
        this.poundageRate = poundageRate;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }
}
