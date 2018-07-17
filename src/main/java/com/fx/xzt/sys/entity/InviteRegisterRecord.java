package com.fx.xzt.sys.entity;


import java.io.Serializable;
import java.util.Date;

public class InviteRegisterRecord  implements Serializable {
  private static final long serialVersionUID = -6971448494947194765L;
  private Long id;
  private  Long shareUserid;//分享的用户id
  private Long newUserid;//新注册用户id
  private Short acceptPrize;//领奖的标识，0未领奖，1领奖
  private Date createTime;//创建时间
  private Date updateTime;//修改时间
  private  String remark;//备注
  private Long agentId;//代理商
  private Long brokerId;//经纪人

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
