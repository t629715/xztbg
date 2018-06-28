package com.fx.xzt.sys.entity;

import java.math.BigDecimal;

public class SaveGoldConf {
    private Long id;

    private Long buyFee;

    private Long saleFee;

    private BigDecimal buyMin;

    private BigDecimal buyMax;

    private String name;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyFee() {
        return buyFee;
    }

    public void setBuyFee(Long buyFee) {
        this.buyFee = buyFee;
    }

    public Long getSaleFee() {
        return saleFee;
    }

    public void setSaleFee(Long saleFee) {
        this.saleFee = saleFee;
    }

    public BigDecimal getBuyMin() {
        return buyMin;
    }

    public void setBuyMin(BigDecimal buyMin) {
        this.buyMin = buyMin;
    }

    public BigDecimal getBuyMax() {
        return buyMax;
    }

    public void setBuyMax(BigDecimal buyMax) {
        this.buyMax = buyMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}