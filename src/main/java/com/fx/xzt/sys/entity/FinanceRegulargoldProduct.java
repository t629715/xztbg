package com.fx.xzt.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceRegulargoldProduct {
    private Long id;

    private String productNo;

    private String productName;

    private Short productType;

    private BigDecimal productYearIncomPercent;

    private Integer productCycle;

    private Integer productMinGram;

    private Integer productMaxGram;

    private Short productSettleMethod;

    private Short productStatus;

    private String createBy;

    private Date createAt;

    private String productDesc;

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

    public BigDecimal getProductYearIncomPercent() {
        return productYearIncomPercent;
    }

    public void setProductYearIncomPercent(BigDecimal productYearIncomPercent) {
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

    public Short getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Short productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}