package com.fx.xzt.sys.entity;

import java.util.Date;

public class InvestGoldConf {
    private Long id;

    private String name;

    private Integer goldWeight;

    private Double withdrawService;

    private Integer minBuyCount;

    private Integer maxBuyCount;

    private Integer logisticsFee;

    private String imgUrl;

    private Short status;

    private Date createTime;

    private Date updateTime;

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

    public Integer getGoldWeight() {
        return goldWeight;
    }

    public void setGoldWeight(Integer goldWeight) {
        this.goldWeight = goldWeight;
    }

    public Double getWithdrawService() {
        return withdrawService;
    }

    public void setWithdrawService(Double withdrawService) {
        this.withdrawService = withdrawService;
    }

    public Integer getMinBuyCount() {
        return minBuyCount;
    }

    public void setMinBuyCount(Integer minBuyCount) {
        this.minBuyCount = minBuyCount;
    }

    public Integer getMaxBuyCount() {
        return maxBuyCount;
    }

    public void setMaxBuyCount(Integer maxBuyCount) {
        this.maxBuyCount = maxBuyCount;
    }

    public Integer getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(Integer logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {

        return "DeliveryGoldConf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goldWeight=" + goldWeight +
                ", withdrawService=" + withdrawService +
                ", logisticsFee=" + logisticsFee +
                ", minBuyCount=" + minBuyCount +
                ", maxBuyCount=" + maxBuyCount +
                ", imgUrl=" + imgUrl +
                ", status=" + status +
                ", createTime=" + createTime +'\'' +
                ", updateTime=" + updateTime +'\'' +
                '}';
    }

}