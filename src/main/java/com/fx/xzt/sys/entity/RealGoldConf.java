package com.fx.xzt.sys.entity;

import java.io.Serializable;

/**
* @Author:  tianliya
* @Description:real_gold_conf实体类
* @Date:13:00 2017/10/16
*/
public class RealGoldConf implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     *主键
     */
    private Long id;
    /**
     *产品名称
     */
    private String name;
    /**
     *买入手续费每克
     */
    private Integer buyPoundage;
    /**
     *保险费每克
     */
    private Integer insurance;
    /**
     *物流费每次
     */
    private Integer logisticsFee;
    /**
     *卖出手续费每克
     */
    private Integer sellPoundage;
    /**
     *买入上限
     */
    private Long maxBuyCount;
    /**
     *金条规格，如10g
     */
    private Integer goldWeight;

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

    public Integer getBuyPoundage() {
        return buyPoundage;
    }

    public void setBuyPoundage(Integer buyPoundage) {
        this.buyPoundage = buyPoundage;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public Integer getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(Integer logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public Integer getSellPoundage() {
        return sellPoundage;
    }

    public void setSellPoundage(Integer sellPoundage) {
        this.sellPoundage = sellPoundage;
    }

    public Long getMaxBuyCount() {
        return maxBuyCount;
    }

    public void setMaxBuyCount(Long maxBuyCount) {
        this.maxBuyCount = maxBuyCount;
    }

    public Integer getGoldWeight() {
        return goldWeight;
    }

    public void setGoldWeight(Integer goldWeight) {
        this.goldWeight = goldWeight;
    }
}