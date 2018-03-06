package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ShareRegisterRecord 
* @Description: 分享注册记录
* @author htt
* @date 2018-3-5 上午10:32:29 
*
 */
public class ShareRegisterRecord implements Serializable {
	
	private static final long serialVersionUID = -6971448494947194765L;
	
	private Long id;
	private Long shareUserId;
	private Long newUserId;
	private Short acceptPrize;
	private Date createTime;
	private Date updateTime;
	private String remark;
	private Long agentId;
	private Long brokerId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getShareUserId() {
		return shareUserId;
	}
	
	public void setShareUserId(Long shareUserId) {
		this.shareUserId = shareUserId;
	}
	
	public Long getNewUserId() {
		return newUserId;
	}
	
	public void setNewUserId(Long newUserId) {
		this.newUserId = newUserId;
	}
	
	public Short getAcceptPrize() {
		return acceptPrize;
	}
	
	public void setAcceptPrize(Short acceptPrize) {
		this.acceptPrize = acceptPrize;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getAgentId() {
		return agentId;
	}
	
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	public Long getBrokerId() {
		return brokerId;
	}
	
	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}
	
}
