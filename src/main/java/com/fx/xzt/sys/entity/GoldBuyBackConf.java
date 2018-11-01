package com.fx.xzt.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GoldBuyBackConf  {
    private Long id;

    private String companyAddress;

    private String companyPhone;

    private String name;

    private String pickUpTime;

    private BigDecimal reviseValue;

    private BigDecimal revisePrice;

    private Short type;

    private Short state;

    private Boolean isStatus;

    private Integer preNum;

    private Integer invalidTime;

    private String spare;

    private Date gtmCreate;

    private Date gmtModified;

    private Long createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime == null ? null : pickUpTime.trim();
    }

    public BigDecimal getReviseValue() {
        return reviseValue;
    }

    public void setReviseValue(BigDecimal reviseValue) {
        this.reviseValue = reviseValue;
    }

    public BigDecimal getRevisePrice() {
        return revisePrice;
    }

    public void setRevisePrice(BigDecimal revisePrice) {
        this.revisePrice = revisePrice;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Boolean getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    public Integer getPreNum() {
        return preNum;
    }

    public void setPreNum(Integer preNum) {
        this.preNum = preNum;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getSpare() {
        return spare;
    }

    public void setSpare(String spare) {
        this.spare = spare == null ? null : spare.trim();
    }

    public Date getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(Date gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
}