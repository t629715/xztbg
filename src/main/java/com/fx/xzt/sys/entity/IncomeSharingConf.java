package com.fx.xzt.sys.entity;

public class IncomeSharingConf {
    private Long id;

    private Double realGoldPercent;

    private Double randomPercent;

    private Double goldRightPercent;

    private Double goldPercent;

    private Long agentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRealGoldPercent() {
        return realGoldPercent;
    }

    public void setRealGoldPercent(Double realGoldPercent) {
        this.realGoldPercent = realGoldPercent;
    }

    public Double getRandomPercent() {
        return randomPercent;
    }

    public void setRandomPercent(Double randomPercent) {
        this.randomPercent = randomPercent;
    }

    public Double getGoldRightPercent() {
        return goldRightPercent;
    }

    public void setGoldRightPercent(Double goldRightPercent) {
        this.goldRightPercent = goldRightPercent;
    }

    public Double getGoldPercent() {
        return goldPercent;
    }

    public void setGoldPercent(Double goldPercent) {
        this.goldPercent = goldPercent;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
}