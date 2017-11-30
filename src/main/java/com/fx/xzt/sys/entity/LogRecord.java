package com.fx.xzt.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;

/**
 * 
* @ClassName: LogRecoed 
* @Description: 日志记录
* @author htt
* @date 2017-11-27 下午4:12:09 
*
 */
public class LogRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;          //id
	private String title;     //标题
	private String content;   //内容
	private String moduleName;//模块
	private Integer type;     //操作类型
	private String ip;        //IP
	private Long userId;      //操作人
	private Date createTime;  //操作时间
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return "LogRecord [ID=" + id + "::标题=" + title + "::内容="
				+ content + "::模块=" + moduleName
				+ "::类型=" + ConstantUtil.logRecordType.toMap().get(type)
				+ "::操作IP=" + ip + "::操作用户ID=" + userId + "::操作时间="
				+ DateUtil.convertDateToString(createTime, "yyyy-MM-dd HH:mm:ss") + "]";
	}

	
}
