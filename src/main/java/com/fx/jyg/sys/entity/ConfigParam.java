package com.fx.jyg.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Config_Param")
public class ConfigParam {
	@Column(name="ParamName")
    private String paramName;
	@Column(name="ParamValue")
    private String paramValue;
	@Column(name="ValueType")
    private String valueType;
	@Column(name="Description")
    private String description;
	@Column(name="CacheRegion")
    private Short cacheRegion;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getCacheRegion() {
		return cacheRegion;
	}

	public void setCacheRegion(Short cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

    
}