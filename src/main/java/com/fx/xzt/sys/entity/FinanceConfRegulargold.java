package com.fx.xzt.sys.entity;

public class FinanceConfRegulargold {
    private Long id;

    private String productNo;

    private String productName;

    private Short productType;

    private Float yearIncomPercent;

    private Integer cycle;

    private Integer minGram;

    private Integer maxGram;

    private Short settleMethod;

    private Short productStatus;

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

    public Short getProductType() {
        return productType;
    }

    public void setProductType(Short productType) {
        this.productType = productType;
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

    public Integer getMinGram() {
        return minGram;
    }

    public void setMinGram(Integer minGram) {
        this.minGram = minGram;
    }

    public Integer getMaxGram() {
        return maxGram;
    }

    public void setMaxGram(Integer maxGram) {
        this.maxGram = maxGram;
    }

    public Short getSettleMethod() {
        return settleMethod;
    }

    public void setSettleMethod(Short settleMethod) {
        this.settleMethod = settleMethod;
    }

    public Short getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Short productStatus) {
        this.productStatus = productStatus;
    }
}