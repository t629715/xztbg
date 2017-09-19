package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 账户记录
* @Title: UserAccountRecord.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月22日 下午5:12:27 
* @version V1.0
 */
public class UserAccountRecord implements Serializable {
    private Long serialno;

    private Long userid;

    private String username;

    private String side;

    private String action;

    private BigDecimal rmbatm;

    private BigDecimal uamt;

    private String createtime;

    private Short status;

    private static final long serialVersionUID = 1L;

    public Long getSerialno() {
        return serialno;
    }

    public void setSerialno(Long serialno) {
        this.serialno = serialno;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side == null ? null : side.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public BigDecimal getRmbatm() {
        return rmbatm;
    }

    public void setRmbatm(BigDecimal rmbatm) {
        this.rmbatm = rmbatm;
    }

    public BigDecimal getUamt() {
        return uamt;
    }

    public void setUamt(BigDecimal uamt) {
        this.uamt = uamt;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}