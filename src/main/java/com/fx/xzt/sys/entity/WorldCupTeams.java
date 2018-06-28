package com.fx.xzt.sys.entity;

public class WorldCupTeams {
    private Long id;

    private String teamName;

    private String teamImg;

    private Integer base;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamImg() {
        return teamImg;
    }

    public void setTeamImg(String teamImg) {
        this.teamImg = teamImg == null ? null : teamImg.trim();
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }
}