package com.fx.xzt.sys.entity;

import java.io.Serializable;

public class GoldRightDealConf implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     *产品id
     */
    private Long id;

    /**
     *产品名称
     */
    private String name;

    /**
     *单手合约(一手=10克)
     */
    private Integer contract;

    /**
     *买入金额 ;百分之多少100:代表全额购买
     */
    private Integer buyPercent;

    /**
     *点差
     */
    private Double pointCount;

    /**
     *最小小数波动位数
     */
    private Integer volatility;

    /**
     *单笔交易最小克数
     */
    private Integer minGramPerOrder;

    /**
     *单笔交易最大克数
     */
    private Integer maxGramPerOrder;

    /**
     *产品持仓最大手数
     */
    private Integer maxHandCount;

    /**
     *每天最大建仓次数
     */
    private Integer maxBuyCountPerDay;

    /**
     *产品最大持仓数
     */
    private Integer maxPositionCount;

    /**
     *手续费
     */
    private Integer poundage;

    /**
     *最小波动盈亏
     */
    private Double volatilityProfitLoss;

    /**
     *止盈设置
     */
    private Double stopProfitSet;

    /**
     *止损设置
     */
    private Double stopLossSet;

    /**
     *爆仓设置
     */
    private Integer blowingUpSet;

    /**
     *1:开市2:闭市
     */
    private Integer status;

    /**
     *交易时间(09:00-11:30,13:30-15:00) 表示两个时间段
     */
    private String dealTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public Integer getBuyPercent() {
        return buyPercent;
    }

    public void setBuyPercent(Integer buyPercent) {
        this.buyPercent = buyPercent;
    }

    public Double getPointCount() {
        return pointCount;
    }

    public void setPointCount(Double pointCount) {
        this.pointCount = pointCount;
    }

    public Integer getVolatility() {
        return volatility;
    }

    public void setVolatility(Integer volatility) {
        this.volatility = volatility;
    }

    public Integer getMinGramPerOrder() {
        return minGramPerOrder;
    }

    public void setMinGramPerOrder(Integer minGramPerOrder) {
        this.minGramPerOrder = minGramPerOrder;
    }

    public Integer getMaxGramPerOrder() {
        return maxGramPerOrder;
    }

    public void setMaxGramPerOrder(Integer maxGramPerOrder) {
        this.maxGramPerOrder = maxGramPerOrder;
    }

    public Integer getMaxHandCount() {
        return maxHandCount;
    }

    public void setMaxHandCount(Integer maxHandCount) {
        this.maxHandCount = maxHandCount;
    }

    public Integer getMaxBuyCountPerDay() {
        return maxBuyCountPerDay;
    }

    public void setMaxBuyCountPerDay(Integer maxBuyCountPerDay) {
        this.maxBuyCountPerDay = maxBuyCountPerDay;
    }

    public Integer getMaxPositionCount() {
        return maxPositionCount;
    }

    public void setMaxPositionCount(Integer maxPositionCount) {
        this.maxPositionCount = maxPositionCount;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    public Double getVolatilityProfitLoss() {
        return volatilityProfitLoss;
    }

    public void setVolatilityProfitLoss(Double volatilityProfitLoss) {
        this.volatilityProfitLoss = volatilityProfitLoss;
    }

    public Double getStopProfitSet() {
        return stopProfitSet;
    }

    public void setStopProfitSet(Double stopProfitSet) {
        this.stopProfitSet = stopProfitSet;
    }

    public Double getStopLossSet() {
        return stopLossSet;
    }

    public void setStopLossSet(Double stopLossSet) {
        this.stopLossSet = stopLossSet;
    }

    public Integer getBlowingUpSet() {
        return blowingUpSet;
    }

    public void setBlowingUpSet(Integer blowingUpSet) {
        this.blowingUpSet = blowingUpSet;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime == null ? null : dealTime.trim();
    }
}