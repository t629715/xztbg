package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class UserLoginInfo implements Serializable {
    private Long serialid;

    private Long userid;

    private String username;

    private Date logintime;

    private String loginfrom;

    private String fromip;

    private String localip;

    private String instance;

    private static final long serialVersionUID = 1L;

    public Long getSerialid() {
        return serialid;
    }

    public void setSerialid(Long serialid) {
        this.serialid = serialid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLoginfrom() {
        return loginfrom;
    }

    public void setLoginfrom(String loginfrom) {
        this.loginfrom = loginfrom == null ? null : loginfrom.trim();
    }

    public String getFromip() {
        return fromip;
    }

    public void setFromip(String fromip) {
        this.fromip = fromip == null ? null : fromip.trim();
    }

    public String getLocalip() {
        return localip;
    }

    public void setLocalip(String localip) {
        this.localip = localip == null ? null : localip.trim();
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance == null ? null : instance.trim();
    }
}