package com.fx.xzt.sys.entity;

import java.util.Date;

public class UserGoldAccountRecord {
    private Long id;

    private Long userId;

    private Double amount;

    private Long rmbAmount;

    private String description;

    private Short type;

    private Date createTime;

    private Long agentId;

    private Long brokerId;

    private Long goldPrice;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getRmbAmount() {
        return rmbAmount;
    }

    public void setRmbAmount(Long rmbAmount) {
        this.rmbAmount = rmbAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public Long getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(Long goldPrice) {
        this.goldPrice = goldPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}