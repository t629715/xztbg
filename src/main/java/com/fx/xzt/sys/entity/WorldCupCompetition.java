package com.fx.xzt.sys.entity;

import java.util.Date;

public class WorldCupCompetition {
    private Long id;

    private Short teamA;

    private String teamAName;

    private String teamAFlag;

    private Short teamB;

    private String teamBName;

    private String teamBFlag;

    private Date startTime;

    private Integer course;

    private Short win;

    private Short teamAScore;

    private Short teamBScore;

    private Short type;

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