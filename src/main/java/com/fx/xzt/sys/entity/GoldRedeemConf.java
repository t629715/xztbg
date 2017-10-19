package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: GoldRedeemConf 
* @Description: 黄金赎回配置
* @author htt
* @date 2017-10-19 下午4:21:14 
*
 */
public class GoldRedeemConf implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;                   //id
	private Integer price;             //赎回价格
	private Double poundagePercent;    //手续费百分比
	private Date createTime;           //创建时间
	private Integer isEnable;          //是否启用1：启用；0：禁用
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Double getPoundagePercent() {
		return poundagePercent;
	}
	
	public void setPoundagePercent(Double poundagePercent) {
		this.poundagePercent = poundagePercent;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getIsEnable() {
		return isEnable;
	}
	
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
