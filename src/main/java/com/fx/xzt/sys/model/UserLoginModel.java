package com.fx.xzt.sys.model;

import com.fx.xzt.sys.entity.UserLogin;

import java.util.Date;

/**
 * @author htt
 * @ClassName: UserLoginModel.java
 * @Description: 注册信息Model
 * @date 2017-09-20 10:09
 */
public class UserLoginModel extends UserLogin {

    private Long agentsId;       //代理商userId
    private Long brokerId;       //经纪人userId
    private String agentsName;     //代理商userName
    private String brokerName;     //经纪人userName
    private Date registerTime;     //注册时间
    private String registerFrom;   //注册来源
    private String registerIp;     //注册ip
    private String attribution;    //归属地
    private Date lastLoginTime;    //最后一次登录时间
    private String lastLoginFrom;  //最后一次登录来源
    private String lastFromIp;     //最后一次登录ip
    private String statusName;     //注册状态

    public Long getAgentsId() {
        return agentsId;
    }

    public void setAgentsId(Long agentsId) {
        this.agentsId = agentsId;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getAgentsName() {
        return agentsName;
    }

    public void setAgentsName(String agentsName) {
        this.agentsName = agentsName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    public String getLastFromIp() {
        return lastFromIp;
    }

    public void setLastFromIp(String lastFromIp) {
        this.lastFromIp = lastFromIp;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
