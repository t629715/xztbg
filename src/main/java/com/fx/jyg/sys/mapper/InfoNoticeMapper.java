/*
 * @ClassName InfoNoticeMapper
 * @Description 
 * @version 1.0
 * @Date 2017-09-11 14:52:57
 */
package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.InfoNotice;

@Repository
public interface InfoNoticeMapper extends BaseMapper<InfoNotice> {
	int add(InfoNotice infoNotice);

	int edit(InfoNotice infoNotice);

	int deleteById(Long serialNo);

	List<InfoNotice> getInfoNoticeAll(Map<String, Object> map);

	InfoNotice getBySerialNo(Long serialNo);
}