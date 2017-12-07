package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: UserGoldAccountActivityRecord 
* @Description: 活动黄金领取
* @author htt
* @date 2017-12-5 下午5:16:36 
*
 */
public class UserGoldAccountActivityRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Double amount;
	private String description;
	private Short activityType;
	private Short type;
	private Date createTime;
	
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
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Short getActivityType() {
		return activityType;
	}
	
	public void setActivityType(Short activityType) {
		this.activityType = activityType;
	}
	
	public Short getType() {
		return type;
	}
	
	public void setType(Short type) {
		this.type = type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
