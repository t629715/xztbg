package com.fx.xzt.sys.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: UserAccountRecord 
* @Description: 用户账户
* @author htt
* @date 2017-11-7 下午4:48:21 
*
 */
public class UserAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;          //id
    private Long userId;      //用户id
    private Long rmb;         //人民币金额
    private Long finance;
    private Long frozenRmb;
    private Long totalIncome;
    private Long agentId;
    private Long brokerId;
    
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

	public Long getRmb() {
		return rmb;
	}

	public void setRmb(Long rmb) {
		this.rmb = rmb;
	}

	public Long getFinance() {
		return finance;
	}

	public void setFinance(Long finance) {
		this.finance = finance;
	}

	public Long getFrozenRmb() {
		return frozenRmb;
	}

	public void setFrozenRmb(Long frozenRmb) {
		this.frozenRmb = frozenRmb;
	}

	public Long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
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