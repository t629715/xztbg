package com.fx.xzt.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.InfoBanner;
import com.fx.xzt.sys.mapper.InfoBannerMapper;
import com.fx.xzt.sys.service.InfoBannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InfoBannerServiceImpl extends BaseService<InfoBanner> implements InfoBannerService{

	@Resource
	InfoBannerMapper infoBannerMapper;

	/**
	 * 根据page类型获取图片
	 * @param page
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<InfoBanner> getByPageAll(Integer page,Integer pageNum,Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		PageHelper.startPage(pageNum,pageSize);
		List<InfoBanner> list = infoBannerMapper.getByPageAll(map);
		return new PageInfo<InfoBanner>(list);
	}

	/**
	 * 修改图片
	 * @param infoBanner
	 * @return
	 */
	public int edit(InfoBanner infoBanner) {
		infoBanner.setModifyTime(new Date());
		return infoBannerMapper.edit(infoBanner);
	}

	/**
	 * 添加图片
	 * @param infoBanner
	 * @return
	 */
	public int add(InfoBanner infoBanner) {
		infoBanner.setCreateTime(new Date());
		return infoBannerMapper.add(infoBanner);
	}

	/**
	 * 根据编号删除图片
	 * @param serialNo
	 * @return
	 */
	public int deleteById(Long serialNo) {
		return infoBannerMapper.deleteBySeriaNo(serialNo);
	}

	/**
	 * 向上  乡下
	 * @param upSerialNo   上一个
	 * @param downSerialNo 下一个
	 * @return
	 */
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

	/**
	 * 根据编号查询图片
	 * @param serialNo
	 * @return
	 */
	public InfoBanner selectById(Long serialNo) {
		return infoBannerMapper.selectBySeriaNo(serialNo);
	}

	/**
	 * 获取广告图片
	 * @param page
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getAdPic(Short page) {
		return infoBannerMapper.selectAdPic(page);
	}
}
