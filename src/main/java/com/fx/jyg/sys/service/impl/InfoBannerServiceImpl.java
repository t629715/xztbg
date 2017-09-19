package com.fx.jyg.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.InfoBanner;
import com.fx.jyg.sys.entity.UserAccountRecord;
import com.fx.jyg.sys.mapper.InfoBannerMapper;
import com.fx.jyg.sys.service.InfoBannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InfoBannerServiceImpl extends BaseService<InfoBanner> implements InfoBannerService{

	@Resource
	InfoBannerMapper infoBannerMapper;
	
	public PageInfo<InfoBanner> getByPageAll(Integer page,Integer pageNum,Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		PageHelper.startPage(pageNum,pageSize);
		List<InfoBanner> list = infoBannerMapper.getByPageAll(map);
		return new PageInfo<InfoBanner>(list);
	}

	public int edit(InfoBanner infoBanner) {
		infoBanner.setModifyTime(new Date());
		return infoBannerMapper.edit(infoBanner);
	}

	public int add(InfoBanner infoBanner) {
		infoBanner.setCreateTime(new Date());
		return infoBannerMapper.add(infoBanner);
	}

	public int deleteById(Long serialNo) {
		return infoBannerMapper.deleteById(serialNo);
	}

	public int up(Long upSerialNo, Long downSerialNo) {
		InfoBanner upib = selectById(upSerialNo);
		InfoBanner downib = selectById(downSerialNo);
		int msg = 0;
		int upSort = upib.getSortNo();
		int downSort = downib.getSortNo();
		upib.setSortNo(downSort);
		downib.setSortNo(upSort);
		msg = edit(upib);
		if(msg == 0)
			throw new NullPointerException("更新失败"); 	
		msg = edit(downib);
		if(msg == 0)
			throw new NullPointerException("更新失败"); 
		return msg;
	}

	public InfoBanner selectById(Long serialNo) {
		return infoBannerMapper.selectById(serialNo);
	}
	
}