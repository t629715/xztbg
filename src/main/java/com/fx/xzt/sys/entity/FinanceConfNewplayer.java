package com.fx.xzt.sys.entity;

import java.io.Serializable;
/**
 * @author:tianliya
 * @CreateTime:2018/1/12 13:29
 * @Description:finance_conf_newplayer 映射的实体类
 **/
public class FinanceConfNewplayer implements Serializable{
    /**
     * 主键
     */
    private Long id;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 产品名字
     */
    private String productName;
    /**
     * 年化收益率
     */
    private Float yearIncomPercent;
    /**
     * 最大浮动收益率
     */
    private Float floatPercentMax;
    /**
     * 交易周期
     */
    private Integer cycle;
    /**
     * 起投黄金重量，单位克
     */
    private Integer minWeight;
    /**
     * 最大购买重量
     */
    private Integer maxWeight;
    /**
     * 1:t+1 n:t+n 计算方式
     */
    private Integer calcMethod;
    /**
     * 1:手动赎回2:自动赎回
     */
    private Short redeemMethod;
    /**
     * 1:赎回时付2:按月付息3:按天付息
     */
    private Short settleMethod;
    /**
     * 收益发放方式：1一次性还本付息
     */
    private Short allocationMethod;
    /**
     * 类型；2：黄金看涨，3：黄金看跌
     */
    private Integer type;
    /**
     * 发售金重，单位克
     */
    private Long sellAmount;
    /**
     * 风险类型
     */
    private String riskType;
    /**
     * 预计年化收益
     */
    private String estimatePercent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getYearIncomPercent() {
        return yearIncomPercent;
    }

    public void setYearIncomPercent(Float yearIncomPercent) {
        this.yearIncomPercent = yearIncomPercent;
    }

    public Float getFloatPercentMax() {
        return floatPercentMax;
    }

    public void setFloatPercentMax(Float floatPercentMax) {
        this.floatPercentMax = floatPercentMax;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Integer minWeight) {
        this.minWeight = minWeight;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getCalcMethod() {
        return calcMethod;
    }

    public void setCalcMethod(Integer calcMethod) {
        this.calcMethod = calcMethod;
    }

    public Short getRedeemMethod() {
        return redeemMethod;
    }

    public void setRedeemMethod(Short redeemMethod) {
        this.redeemMethod = redeemMethod;
    }

    public Short getSettleMethod() {
        return settleMethod;
    }

    public void setSettleMethod(Short settleMethod) {
        this.settleMethod = settleMethod;
    }

    public Short getAllocationMethod() {
        return allocationMethod;
    }

    public void setAllocationMethod(Short allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(Long sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    public String getEstimatePercent() {
        return estimatePercent;
    }

    public void setEstimatePercent(String estimatePercent) {
        this.estimatePercent = estimatePercent == null ? null : estimatePercent.trim();
    }
}