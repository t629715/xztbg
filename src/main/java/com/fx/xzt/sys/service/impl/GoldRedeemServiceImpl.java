package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.GoldRedeem;
import com.fx.xzt.sys.mapper.GoldRedeemMapper;
import com.fx.xzt.sys.service.GoldRedeemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldRedeemServiceImpl 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:46:43 
*
 */
@Service
public class GoldRedeemServiceImpl extends BaseService<GoldRedeem> implements GoldRedeemService {
	
	@Resource
	GoldRedeemMapper goldRedeemMapper;

	/**
	 * 黄金赎回查询
	 */
	public PageInfo<Map<String, Object>> selectByGoldRedeem(String userName, String startTime, String endTime, 
			String channelName, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = goldRedeemMapper.selectByGoldRedeem(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 黄金赎回-导出
	 */
	public List<Map<String, Object>> excelGoldRedeem(String userName, String startTime, String endTime, String channelName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        List<Map<String, Object>> list = goldRedeemMapper.selectByGoldRedeem(map);
        return list;
	}

	/**
	 * 黄金赎回--黄金克重统计
	 */
	public Map<String, Object> selectByGoldRedeemCount(String userName, String startTime, String endTime, String channelName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        return goldRedeemMapper.selectByGoldRedeemCount(map);
	}

	/**
	 * 黄金赎回--新增
	 */
	@Transactional
	public int insertGoldRedeem(GoldRedeem goldRedeem) {
		return goldRedeemMapper.insertGoldRedeem(goldRedeem);
	}


}
