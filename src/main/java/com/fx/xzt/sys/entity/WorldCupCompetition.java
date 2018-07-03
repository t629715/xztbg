package com.fx.xzt.sys.entity;

import java.util.Date;

public class WorldCupCompetition {
    private Long id;

    private Short teamA;//球队a

    private String teamAName;//球队a名字

    private String teamAFlag;//球队a国旗

    private Short teamB;//球队b

    private String teamBName;//球队b名字

    private String teamBFlag;//球队b国旗

    private Date startTime;//开始时间

    private Integer course;//1:1/4赛,2:半决赛3:季军赛4:决赛

    private Short win;//胜利的球队

    private Short teamAScore;//球队a进球数

    private Short teamBScore;//球队b进球数

    private Short type;//0:未开始1:已结束

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getTeamA() {
        return teamA;
    }

    public void setTeamA(Short teamA) {
        this.teamA = teamA;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName == null ? null : teamAName.trim();
    }

    public String getTeamAFlag() {
        return teamAFlag;
    }

    public void setTeamAFlag(String teamAFlag) {
        this.teamAFlag = teamAFlag == null ? null : teamAFlag.trim();
    }

    public Short getTeamB() {
        return teamB;
    }

    public void setTeamB(Short teamB) {
        this.teamB = teamB;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName == null ? null : teamBName.trim();
    }

    public String getTeamBFlag() {
        return teamBFlag;
    }

    public void setTeamBFlag(String teamBFlag) {
        this.teamBFlag = teamBFlag == null ? null : teamBFlag.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Short getWin() {
        return win;
    }

    public void setWin(Short win) {
        this.win = win;
    }

    public Short getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(Short teamAScore) {
        this.teamAScore = teamAScore;
    }

    public Short getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(Short teamBScore) {
        this.teamBScore = teamBScore;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}