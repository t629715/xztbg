package com.fx.xzt.sys.entity;


import java.io.Serializable;

public class FinanceConf implements Serializable{
    private static final long serialVersionUID = 1L;

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
     *年化收益率
     */
    private Float yearIncomPercent;

    /**
     *周期
     */
    private Integer cycle;

    /**
     *最小起投金额
     */
    private Float minMoney;

    /**
     *计息方式
     */
    private Integer calcMethod;

    /**
     *赎回方式
     */
    private Short redeemMethod;

    /**
     *结算方式
     */
    private Short settleMethod;

    /**
     *浮动收益率
     */
    private Float floatPercent;

    /**
     *期数
     */
    private Integer nper;

    /**
     *产品类型
     */
    private Integer type;

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

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Float getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Float minMoney) {
        this.minMoney = minMoney;
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

    public Float getFloatPercent() {
        return floatPercent;
    }

    public void setFloatPercent(Float floatPercent) {
        this.floatPercent = floatPercent;
    }

    public Integer getNper() {
        return nper;
    }

    public void setNper(Integer nper) {
        this.nper = nper;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}