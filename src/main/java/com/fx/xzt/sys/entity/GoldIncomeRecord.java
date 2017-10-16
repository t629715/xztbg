package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: GoldIncomeRecord 
* @Description: 黄金收益结算
* @author htt
* @date 2017-9-30 下午1:31:42 
*
 */
public class GoldIncomeRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;           //主键
	private Long userId;       //用户逐渐
	private Integer income;    //收益，单位分
	private Integer totalIncom;//总收益，单位分
	private Date createTime;   //创建时间
	private Float gram;        //结算时克重
	private Integer price;     //收盘金价，单位分
	private Float incomePercent;//收益率
	
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
	
	public Integer getIncome() {
		return income;
	}
	
	public void setIncome(Integer income) {
		this.income = income;
	}
	
	public Integer getTotalIncom() {
		return totalIncom;
	}
	
	public void setTotalIncom(Integer totalIncom) {
		this.totalIncom = totalIncom;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Float getGram() {
		return gram;
	}
	
	public void setGram(Float gram) {
		this.gram = gram;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Float getIncomePercent() {
		return incomePercent;
	}
	
	public void setIncomePercent(Float incomePercent) {
		this.incomePercent = incomePercent;
	}
	

}
