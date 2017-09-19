package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InfoInforMation;

@Repository
public interface InfoInforMationMapper extends BaseMapper<InfoInforMation>{
	
	List<InfoInforMation> getByAll(Map<String,Object> map);
	
	int posted(InfoInforMation i);
	
	/**
	 * 查看
	 */
	InfoInforMation getById(Long serialNo);
	/**
	 * 修改
	 */
	int edit(InfoInforMation i);
	/**
	 * 删除
	 */
	int deleteById(Long serialNo);
	/**
	 * 取消置顶
	 */
	int editTopState(Map<String,Object> map);
}
