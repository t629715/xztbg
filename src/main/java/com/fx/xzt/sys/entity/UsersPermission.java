package com.fx.xzt.sys.entity;

import java.io.Serializable;
/**
 * 
* @Title: UsersPermission.java 
* @Package com.fx.xzt.sys.entity
* @Description: TODO
* @author SYan  
* @date 2017年8月15日 下午5:54:04 
* @version V1.0
 */
public class UsersPermission implements Serializable {
    private Integer id;

    private String text;

    private String icon;

    private String label;

    private String translate;

    private String sref;

    private Integer pid;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate == null ? null : translate.trim();
    }

    public String getSref() {
        return sref;
    }

    public void setSref(String sref) {
        this.sref = sref == null ? null : sref.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}