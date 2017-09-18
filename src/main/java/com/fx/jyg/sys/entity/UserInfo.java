package com.fx.jyg.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @Title: UserInfo.java 
* @Package com.fx.jyg.sys.entity 
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 下午3:02:31 
* @version V1.0
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

    private Date realnameauthapplytime;

    private Date realnameauthapprovetime;

    private Short realnameauthapprovestate;

    private Short realnameauthstatus;

    private String certificatepath;

    private Date certificateauthapplytime;

    private Date certificateauthapprovetime;

    private Short certificateauthapprovestate;

    private Short certificateauthstatus;
    
    /**
     * @Fields IDCardBackPath 身份证图片反面路径
     */
    private String IDCardBackPath;

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
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getProfilephotopath() {
        return profilephotopath;
    }

    public void setProfilephotopath(String profilephotopath) {
        this.profilephotopath = profilephotopath == null ? null : profilephotopath.trim();
    }
    public String getIDCardBackPath() {
        return IDCardBackPath;
    }

    public void setIDCardBackPath(String IDCardBackPath) {
        this.IDCardBackPath = IDCardBackPath == null ? null : IDCardBackPath.trim();
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
        this.registerfrom = registerfrom == null ? null : registerfrom.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress == null ? null : deliveryaddress.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdcardpath() {
        return idcardpath;
    }

    public void setIdcardpath(String idcardpath) {
        this.idcardpath = idcardpath == null ? null : idcardpath.trim();
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

    public String getCertificatepath() {
        return certificatepath;
    }

    public void setCertificatepath(String certificatepath) {
        this.certificatepath = certificatepath == null ? null : certificatepath.trim();
    }

    public Date getCertificateauthapplytime() {
        return certificateauthapplytime;
    }

    public void setCertificateauthapplytime(Date certificateauthapplytime) {
        this.certificateauthapplytime = certificateauthapplytime;
    }

    public Date getCertificateauthapprovetime() {
        return certificateauthapprovetime;
    }

    public void setCertificateauthapprovetime(Date certificateauthapprovetime) {
        this.certificateauthapprovetime = certificateauthapprovetime;
    }

    public Short getCertificateauthapprovestate() {
        return certificateauthapprovestate;
    }

    public void setCertificateauthapprovestate(Short certificateauthapprovestate) {
        this.certificateauthapprovestate = certificateauthapprovestate;
    }

    public Short getCertificateauthstatus() {
        return certificateauthstatus;
    }

    public void setCertificateauthstatus(Short certificateauthstatus) {
        this.certificateauthstatus = certificateauthstatus;
    }
}