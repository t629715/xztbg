package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现
* @Title: UserWithdrawCash.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月23日 下午1:48:36 
* @version V1.0
 */
public class UserWithdrawCash implements Serializable {
    private String withdrawid;

    private Long userid;

    private String username;

    private BigDecimal withdrawamt;

    private String accountnum;

    private Date withdrawtime;

    private Date finishtime;

    private Short status;

    private static final long serialVersionUID = 1L;

    public String getWithdrawid() {
        return withdrawid;
    }

    public void setWithdrawid(String withdrawid) {
        this.withdrawid = withdrawid == null ? null : withdrawid.trim();
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

    public BigDecimal getWithdrawamt() {
        return withdrawamt;
    }

    public void setWithdrawamt(BigDecimal withdrawamt) {
        this.withdrawamt = withdrawamt;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum == null ? null : accountnum.trim();
    }

    public Date getWithdrawtime() {
        return withdrawtime;
    }

    public void setWithdrawtime(Date withdrawtime) {
        this.withdrawtime = withdrawtime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}