package com.fx.xzt.sys.entity;

public class RealGoldBuyConf {
    private Long id;

    private String name;

    private Float yearIncomPercent;

    private Float buyPoundage;

    private Float sellPoundage;

    private Double maxBuyCount;

    private Double minBuyCount;

    private String productNo;

    private Integer cycle;

    private Short redeemMethod;

    private Short settleMethod;

    private Short calcMethod;

    private Float clacStartPoint;

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

    public Float getYearIncomPercent() {
        return yearIncomPercent;
    }

    public void setYearIncomPercent(Float yearIncomPercent) {
        this.yearIncomPercent = yearIncomPercent;
    }

    public Float getBuyPoundage() {
        return buyPoundage;
    }

    public void setBuyPoundage(Float buyPoundage) {
        this.buyPoundage = buyPoundage;
    }

    public Float getSellPoundage() {
        return sellPoundage;
    }

    public void setSellPoundage(Float sellPoundage) {
        this.sellPoundage = sellPoundage;
    }

    public Double getMaxBuyCount() {
        return maxBuyCount;
    }

    public void setMaxBuyCount(Double maxBuyCount) {
        this.maxBuyCount = maxBuyCount;
    }

    public Double getMinBuyCount() {
        return minBuyCount;
    }

    public void setMinBuyCount(Double minBuyCount) {
        this.minBuyCount = minBuyCount;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
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

    public Short getCalcMethod() {
        return calcMethod;
    }

    public void setCalcMethod(Short calcMethod) {
        this.calcMethod = calcMethod;
    }

    public Float getClacStartPoint() {
        return clacStartPoint;
    }

    public void setClacStartPoint(Float clacStartPoint) {
        this.clacStartPoint = clacStartPoint;
    }
}