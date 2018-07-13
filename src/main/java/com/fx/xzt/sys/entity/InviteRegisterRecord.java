package com.fx.xzt.sys.entity;


import java.util.Date;

public class InviteRegisterRecord  {

  private Long id;
  private  Long shareUserid;
  private Long newUserid;
  private Short acceptPrize;
  private Date createTime;
  private Date updateTime;
  private  String remark;
  private Long agentId;
  private Long brokerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareUserid() {
        return shareUserid;
    }

    public void setShareUserid(Long shareUserid) {
        this.shareUserid = shareUserid;
    }

    public Long getNewUserid() {
        return newUserid;
    }

    public void setNewUserid(Long newUserid) {
        this.newUserid = newUserid;
    }

    public Short getAcceptPrize() {
        return acceptPrize;
    }

    public void setAcceptPrize(Short acceptPrize) {
        this.acceptPrize = acceptPrize;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
