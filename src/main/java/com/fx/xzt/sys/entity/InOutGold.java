package com.fx.xzt.sys.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: InOutGold 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午3:02:11 
*
 */
public class InOutGold implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String userName;
    private String realName;
    private Long agentId;
    private String agentName;
    private Long brokeId;
    private String brokeName;
    private Integer finance;
    private Integer totalIncome;
    private Integer rj;
    private Integer cj;
    private Integer amount;
    private Double gold;
    private Integer cbf;
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Long getBrokeId() {
		return brokeId;
	}

	public void setBrokeId(Long brokeId) {
		this.brokeId = brokeId;
	}

	public String getBrokeName() {
		return brokeName;
	}

	public void setBrokeName(String brokeName) {
		this.brokeName = brokeName;
	}

	public Integer getFinance() {
		return finance;
	}

	public void setFinance(Integer finance) {
		this.finance = finance;
	}

	public Integer getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Integer getRj() {
		return rj;
	}

	public void setRj(Integer rj) {
		this.rj = rj;
	}

	public Integer getCj() {
		return cj;
	}

	public void setCj(Integer cj) {
		this.cj = cj;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public Integer getCbf() {
		return cbf;
	}

	public void setCbf(Integer cbf) {
		this.cbf = cbf;
	}
}
