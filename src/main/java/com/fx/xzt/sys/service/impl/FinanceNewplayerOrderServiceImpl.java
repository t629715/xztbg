package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.FinanceNewplayerOrder;
import com.fx.xzt.sys.mapper.FinanceNewplayerOrderMapper;
import com.fx.xzt.sys.service.FinanceNewplayerOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: DealOrderServiceImpl.java
 * @Description:
 * @date 2017-09-25 13:46
 */
@Service
public class FinanceNewplayerOrderServiceImpl extends BaseService<FinanceNewplayerOrder> implements FinanceNewplayerOrderService  {

    @Resource
    FinanceNewplayerOrderMapper orderMapper;

    /**
     * 查询
     */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String agentName, String brokerName, String status, String type,
			String redeemStartTime, String redeemEndTime, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = orderMapper.selectByAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelAll(String userName, String orderNo, String startTime, String endTime, String regStartTime,
			String regEndTime, String agentName, String brokerName, String status, String type, String redeemStartTime, String redeemEndTime) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        List<Map<String, Object>> list = orderMapper.selectByAll(map);
        return list;
	}

	/**
	 * 查询统计
	 */
	public Map<String, Object> selectByAllCount(String userName,
			String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String agentName,
			String brokerName, String status, String type, String redeemStartTime, String redeemEndTime) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        Map<String, Object> map1 = orderMapper.selectByAllCount(map);
        return map1;
	}

   
}
