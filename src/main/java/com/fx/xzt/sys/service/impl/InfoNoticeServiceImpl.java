package com.fx.xzt.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.InfoNotice;
import com.fx.xzt.sys.mapper.InfoNoticeMapper;
import com.fx.xzt.sys.service.InfoNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InfoNoticeServiceImpl extends BaseService<InfoNotice> implements InfoNoticeService{
	
	@Resource
	InfoNoticeMapper infoNoticeMapper;

	@Transactional
	public int add(InfoNotice infoNotice) {
		infoNotice.setCreateTime(new Date());
		infoNotice.setValid((short)1);
		return infoNoticeMapper.add(infoNotice);
	}

	@Transactional
	public int edit(InfoNotice infoNotice) {
		infoNotice.setModifyTime(new Date());
		return infoNoticeMapper.edit(infoNotice);
	}

	@Transactional
	public int deleteById(Long serialNo) {
		return infoNoticeMapper.deleteById(serialNo);
	}

	public PageInfo<Map<String, Object>> getInfoNoticeAll(String title, String startTime, String endTime, String operator,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		if (startTime != null && startTime != ""){
			Date startDate = null;
			startDate = DateUtil.convertTimeMillisToDate(Long.parseLong(startTime));
			startTime = DateUtils.formatDateByMidLine(startDate);
		}

		if (endTime != null && endTime != ""){
			Date endDate = null;
			endDate = DateUtil.convertTimeMillisToDate(Long.parseLong(endTime));
			endTime = DateUtils.formatDateByMidLine(endDate);
		}
		map.put("title", title);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("operator", operator);
		PageHelper.startPage(pageNum,pageSize);
		return new PageInfo<Map<String, Object>>(infoNoticeMapper.getInfoNoticeAll(map));
	}

	public InfoNotice getBySerialNo(Long serialNo) {
		return infoNoticeMapper.getBySerialNo(serialNo);
	}

	@Transactional
	public int upDown(Long upSerialNo, Long downSerialNo) {
		InfoNotice up = getBySerialNo(upSerialNo);
		InfoNotice down = getBySerialNo(downSerialNo);
		int sort = up.getSortNo();
		up.setSortNo(down.getSortNo());
		down.setSortNo(sort);
		int msg = -1;
		msg = edit(up);
		if(msg>0){
			msg=edit(down);
		}else{
			throw new RuntimeException("更新失败"); 
		}
		return msg;
	}
	/**
	 * 获取所有的发布人  tianliya
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getOperators() {
		List<Map<String, Object>> map = infoNoticeMapper.selectOperators();
		return map;
	}
}
