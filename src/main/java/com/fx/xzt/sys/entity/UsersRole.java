package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @Title: UsersRole.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月15日 下午6:03:30 
* @version V1.0
 */
public class UsersRole implements Serializable {
	private Integer id;

    private String name;

    private Date createTime;
    
    private Integer isView;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getIsView() {
		return isView;
	}

	public void setIsView(Integer isView) {
		this.isView = isView;
	}
    
}