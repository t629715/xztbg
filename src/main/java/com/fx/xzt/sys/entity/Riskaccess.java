package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author htt
 * @ClassName: Riskaccess.java
 * @Description: 风险评测
 * @date 2017-09-22 10:11
 */
public class Riskaccess implements Serializable {

    private Long accessId;   //风险ID
    private Long userId;        //用户ID
    private Integer accessMark; //评估得分
    private String accessLevel; //评估等级
    private Integer mark1;
    private Integer mark2;
    private Integer mark3;
    private Integer mark4;
    private Integer mark5;
    private Integer mark6;
    private Integer mark7;
    private Integer mark8;
    private Integer mark9;
    private Integer mark10;
    private Date accessTime;     //评估时间
    private Integer accessState; //评估状态0：未评估1：已评估

    public Riskaccess() {
        super();
    }

    public Riskaccess(Long accessId, Long userId, Integer accessMark, String accessLevel, Integer mark1, Integer mark2, Integer mark3, Integer mark4, Integer mark5, Integer mark6, Integer mark7, Integer mark8, Integer mark9, Integer mark10, Date accessTime, Integer accessState) {
        this.accessId = accessId;
        this.userId = userId;
        this.accessMark = accessMark;
        this.accessLevel = accessLevel;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.mark5 = mark5;
        this.mark6 = mark6;
        this.mark7 = mark7;
        this.mark8 = mark8;
        this.mark9 = mark9;
        this.mark10 = mark10;
        this.accessTime = accessTime;
        this.accessState = accessState;
    }

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAccessMark() {
        return accessMark;
    }

    public void setAccessMark(Integer accessMark) {
        this.accessMark = accessMark;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Integer getMark1() {
        return mark1;
    }

    public void setMark1(Integer mark1) {
        this.mark1 = mark1;
    }

    public Integer getMark2() {
        return mark2;
    }

    public void setMark2(Integer mark2) {
        this.mark2 = mark2;
    }

    public Integer getMark3() {
        return mark3;
    }

    public void setMark3(Integer mark3) {
        this.mark3 = mark3;
    }

    public Integer getMark4() {
        return mark4;
    }

    public void setMark4(Integer mark4) {
        this.mark4 = mark4;
    }

    public Integer getMark5() {
        return mark5;
    }

    public void setMark5(Integer mark5) {
        this.mark5 = mark5;
    }

    public Integer getMark6() {
        return mark6;
    }

    public void setMark6(Integer mark6) {
        this.mark6 = mark6;
    }

    public Integer getMark7() {
        return mark7;
    }

    public void setMark7(Integer mark7) {
        this.mark7 = mark7;
    }

    public Integer getMark8() {
        return mark8;
    }

    public void setMark8(Integer mark8) {
        this.mark8 = mark8;
    }

    public Integer getMark9() {
        return mark9;
    }

    public void setMark9(Integer mark9) {
        this.mark9 = mark9;
    }

    public Integer getMark10() {
        return mark10;
    }

    public void setMark10(Integer mark10) {
        this.mark10 = mark10;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getAccessState() {
        return accessState;
    }

    public void setAccessState(Integer accessState) {
        this.accessState = accessState;
    }
}
