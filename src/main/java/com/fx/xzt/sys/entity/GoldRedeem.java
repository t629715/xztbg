package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: GoldRedeem 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:32:31 
*
 */
public class GoldRedeem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;        //id
	private Long userId;    //用户账号
	private Float gram;     //克重
	private Double price;  //赎回价格
	private Long amount; //赎回金额
	private Date createTime;//赎回时间
	private Long poundage;  //赎回手续
	private String accountId; //用户账户id
	private String userName;
	private String userIdString;
	
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
	
	public Float getGram() {
		return gram;
	}
	
	public void setGram(Float gram) {
		this.gram = gram;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getPoundage() {
		return poundage;
	}
	
	public void setPoundage(Long poundage) {
		this.poundage = poundage;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIdString() {
		return userIdString;
	}

	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}

	public String toString() {
		return "GoldRedeem [id=" + id + ", userId=" + userId + ", gram=" + gram
				+ ", price=" + price + ", amount=" + amount + ", createTime="
				+ createTime + ", poundage=" + poundage + ", accountId="
				+ accountId + ", userName=" + userName + ", userIdString="
				+ userIdString + "]";
	}
	
}
