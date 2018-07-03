package com.fx.xzt.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserGoldAccount {
    private Long id;

    private Long userId;

    private Double gold;

    private Double frozenGold;

    private Date updateTime;

    private Long agentId;

    private Long brokerId;

    private BigDecimal totalIncome;

    private Double financeGold;

    private Long averagePrice;

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

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }

    public Double getFrozenGold() {
        return frozenGold;
    }

    public void setFrozenGold(Double frozenGold) {
        this.frozenGold = frozenGold;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getFinanceGold() {
        return financeGold;
    }

    public void setFinanceGold(Double financeGold) {
        this.financeGold = financeGold;
    }

    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }
}