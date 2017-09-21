package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SYan
 * @version V1.0
 * @Title: UserInfo.java
 * @Package com.fx.xzt.sys.entity
 * @Description: TODO
 * @date 2017年8月14日 下午3:02:31
 */
public class UserInfo implements Serializable {

    private Long userid;
    private String nickname;
    private String profilephotopath;
    private Date registertime;
    private String registerfrom;
    private String phoneno;
    private String deliveryaddress;
    private String realname;
    private String idcard;
    private String idcardpath;
    private String idcardbackpath;
    private Date realnameauthapplytime;
    private Date realnameauthapprovetime;
    private Short realnameauthapprovestate;
    private Short realnameauthstatus;
    private String agentId;
    private String brokerId;
    private String attribution;
    private String registerIp;

    private static final long serialVersionUID = 1L;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilephotopath() {
        return profilephotopath;
    }

    public void setProfilephotopath(String profilephotopath) {
        this.profilephotopath = profilephotopath;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getRegisterfrom() {
        return registerfrom;
    }

    public void setRegisterfrom(String registerfrom) {
        this.registerfrom = registerfrom;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardpath() {
        return idcardpath;
    }

    public void setIdcardpath(String idcardpath) {
        this.idcardpath = idcardpath;
    }

    public String getIdcardbackpath() {
        return idcardbackpath;
    }

    public void setIdcardbackpath(String idcardbackpath) {
        this.idcardbackpath = idcardbackpath;
    }

    public Date getRealnameauthapplytime() {
        return realnameauthapplytime;
    }

    public void setRealnameauthapplytime(Date realnameauthapplytime) {
        this.realnameauthapplytime = realnameauthapplytime;
    }

    public Date getRealnameauthapprovetime() {
        return realnameauthapprovetime;
    }

    public void setRealnameauthapprovetime(Date realnameauthapprovetime) {
        this.realnameauthapprovetime = realnameauthapprovetime;
    }

    public Short getRealnameauthapprovestate() {
        return realnameauthapprovestate;
    }

    public void setRealnameauthapprovestate(Short realnameauthapprovestate) {
        this.realnameauthapprovestate = realnameauthapprovestate;
    }

    public Short getRealnameauthstatus() {
        return realnameauthstatus;
    }

    public void setRealnameauthstatus(Short realnameauthstatus) {
        this.realnameauthstatus = realnameauthstatus;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }
}