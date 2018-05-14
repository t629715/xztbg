package com.fx.xzt.sys.entity;

import java.util.Date;

public class FinanceRegulargoldOrder {
    private Long orderId;

    private Long userId;

    private Long productId;

    private String productNo;

    private String productName;

    private Short productType;

    private Float productYearIncomPercent;

    private Integer productCycle;

    private Integer productMinGram;

    private Integer productMaxGram;

    private Short productSettleMethod;

    private Short buyType;

    private Long buyAmount;

    private Date buyTime;

    private Date expireTime;

    private Short status;

    private Long incomeGram;

    private Integer buyPrice;

    private Long backTotalGram;

    private Long voucherValue;

    private Double voucherDeductible;

    private Long voucherId;

    private Long agentId;

    private Long brokerId;

    private Double gram;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Short getProductType() {
        return productType;
    }

    public void setProductType(Short productType) {
        this.productType = productType;
    }

    public Float getProductYearIncomPercent() {
        return productYearIncomPercent;
    }

    public void setProductYearIncomPercent(Float productYearIncomPercent) {
        this.productYearIncomPercent = productYearIncomPercent;
    }

    public Integer getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(Integer productCycle) {
        this.productCycle = productCycle;
    }

    public Integer getProductMinGram() {
        return productMinGram;
    }

    public void setProductMinGram(Integer productMinGram) {
        this.productMinGram = productMinGram;
    }

    public Integer getProductMaxGram() {
        return productMaxGram;
    }

    public void setProductMaxGram(Integer productMaxGram) {
        this.productMaxGram = productMaxGram;
    }

    public Short getProductSettleMethod() {
        return productSettleMethod;
    }

    public void setProductSettleMethod(Short productSettleMethod) {
        this.productSettleMethod = productSettleMethod;
    }

    public Short getBuyType() {
        return buyType;
    }

    public void setBuyType(Short buyType) {
        this.buyType = buyType;
    }

    public Long getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Long buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getIncomeGram() {
        return incomeGram;
    }

    public void setIncomeGram(Long incomeGram) {
        this.incomeGram = incomeGram;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getBackTotalGram() {
        return backTotalGram;
    }

    public void setBackTotalGram(Long backTotalGram) {
        this.backTotalGram = backTotalGram;
    }

    public Long getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(Long voucherValue) {
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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public Double getGram() {
        return gram;
    }

    public void setGram(Double gram) {
        this.gram = gram;
    }
}