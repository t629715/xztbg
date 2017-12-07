package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ActivityWinners 
* @Description: 活动领取
* @author htt
* @date 2017-12-5 下午4:49:50 
*
 */
public class ActivityWinners implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long actId;
	private Long aprId;
	private Long userId;
	private Double prizeValue;
	private String prizeName;
	private Integer prizeType;
	private String userPhone;
	private String userRealName;
	private Date winTime;
	private Date takeStartTime;
	private Date takeEndTime;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getActId() {
		return actId;
	}
	public void setActId(Long actId) {
		this.actId = actId;
	}
	
	public Long getAprId() {
		return aprId;
	}
	
	public void setAprId(Long aprId) {
		this.aprId = aprId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Double getPrizeValue() {
		return prizeValue;
	}
	
	public void setPrizeValue(Double prizeValue) {
		this.prizeValue = prizeValue;
	}
	
	public String getPrizeName() {
		return prizeName;
	}
	
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	
	public Integer getPrizeType() {
		return prizeType;
	}
	
	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserRealName() {
		return userRealName;
	}
	
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	public Date getWinTime() {
		return winTime;
	}
	
	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}
	
	public Date getTakeStartTime() {
		return takeStartTime;
	}
	
	public void setTakeStartTime(Date takeStartTime) {
		this.takeStartTime = takeStartTime;
	}
	
	public Date getTakeEndTime() {
		return takeEndTime;
	}
	
	public void setTakeEndTime(Date takeEndTime) {
		this.takeEndTime = takeEndTime;
	}


}
