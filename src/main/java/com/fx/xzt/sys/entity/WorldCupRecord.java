package com.fx.xzt.sys.entity;

import java.util.Date;

public class WorldCupRecord {
    private Long id;

    private Long userId;

    private Long competitionId;

    private Date guessingTime;

    private Long guessingWinner;

    private Short guessingAScore;

    private Short guessingBScore;

    private Short isGuessing;

    private Short settlement;

    private Date settlementTime;

    private Short type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Date getGuessingTime() {
        return guessingTime;
    }

    public void setGuessingTime(Date guessingTime) {
        this.guessingTime = guessingTime;
    }

    public Long getGuessingWinner() {
        return guessingWinner;
    }

    public void setGuessingWinner(Long guessingWinner) {
        this.guessingWinner = guessingWinner;
    }

    public Short getGuessingAScore() {
        return guessingAScore;
    }

    public void setGuessingAScore(Short guessingAScore) {
        this.guessingAScore = guessingAScore;
    }

    public Short getGuessingBScore() {
        return guessingBScore;
    }

    public void setGuessingBScore(Short guessingBScore) {
        this.guessingBScore = guessingBScore;
    }

    public Short getIsGuessing() {
        return isGuessing;
    }

    public void setIsGuessing(Short isGuessing) {
        this.isGuessing = isGuessing;
    }

    public Short getSettlement() {
        return settlement;
    }

    public void setSettlement(Short settlement) {
        this.settlement = settlement;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}