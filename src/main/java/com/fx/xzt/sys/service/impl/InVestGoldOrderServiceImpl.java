package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.InVestGoldOrder;
import com.fx.xzt.sys.mapper.InVestGoldOrderMapper;
import com.fx.xzt.sys.service.InVestGoldOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InVestGoldOrderServiceImpl 
* @Description: 金条投资订单
* @author htt
* @date 2018-4-19 下午3:43:02 
*
 */
@Service
public class InVestGoldOrderServiceImpl extends BaseService<InVestGoldOrder> implements InVestGoldOrderService {
	
	@Resource
	InVestGoldOrderMapper mapper;
	

	/**
	 * 查询
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, String regStartTime,
			String regEndTime, String agentName, String brokerName, Integer status, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, String regStartTime,
			String regEndTime, String agentName, String brokerName, Integer status, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("isView", isView);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        return list;
	}


}
