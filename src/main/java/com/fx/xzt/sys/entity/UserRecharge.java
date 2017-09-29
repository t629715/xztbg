package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 充值
* @Title: UserRecharge.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月22日 上午9:26:02 
* @version V1.0
 */
public class UserRecharge implements Serializable {
    private String rechargeid;

    private Long userid;

    private String username;

    private Integer rmbamt;

    private BigDecimal uamt;

    private Date rechargetime;

    private String rechargechannel;

    private String platformname;

    private String merchantordernum;

    private Short status;

    private static final long serialVersionUID = 1L;

    public String getRechargeid() {
        return rechargeid;
    }

    public void setRechargeid(String rechargeid) {
        this.rechargeid = rechargeid == null ? null : rechargeid.trim();
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

    public Integer getRmbamt() {
        return rmbamt;
    }

    public void setRmbamt(Integer rmbamt) {
        this.rmbamt = rmbamt;
    }

    public BigDecimal getUamt() {
        return uamt;
    }

    public void setUamt(BigDecimal uamt) {
        this.uamt = uamt;
    }

    public Date getRechargetime() {
        return rechargetime;
    }

    public void setRechargetime(Date rechargetime) {
        this.rechargetime = rechargetime;
    }

    public String getRechargechannel() {
        return rechargechannel;
    }

    public void setRechargechannel(String rechargechannel) {
        this.rechargechannel = rechargechannel == null ? null : rechargechannel.trim();
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname == null ? null : platformname.trim();
    }

    public String getMerchantordernum() {
        return merchantordernum;
    }

    public void setMerchantordernum(String merchantordernum) {
        this.merchantordernum = merchantordernum == null ? null : merchantordernum.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}