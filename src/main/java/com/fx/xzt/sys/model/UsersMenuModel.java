package com.fx.xzt.sys.model;

import java.util.ArrayList;
import java.util.List;

import com.fx.xzt.sys.util.StringUtil;

/**
 * 
* @ClassName: UsersMenuModel 
* @Description: 用户菜单
* @author htt
* @date 2017-10-17 下午6:26:01 
*
 */
public class UsersMenuModel {
	private Integer id;    //菜单id
	private Integer pid;   //菜单pid
	private String index;  //菜单跳转路径
	private String icon;   //菜单图标
	private String title;  //菜单名称
	private List<UsersMenuModel> subs = new ArrayList<UsersMenuModel>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIndex() {
		return StringUtil.convertNullToEmpty(index);
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getIcon() {
		return StringUtil.convertNullToEmpty(icon);
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getTitle() {
		return StringUtil.convertNullToEmpty(title);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<UsersMenuModel> getSubs() {
		return subs;
	}
	
	public void setSubs(List<UsersMenuModel> subs) {
		this.subs = subs;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
}
