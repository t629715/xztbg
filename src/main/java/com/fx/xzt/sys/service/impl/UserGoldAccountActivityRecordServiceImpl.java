package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserGoldAccountActivityRecord;
import com.fx.xzt.sys.mapper.UserGoldAccountActivityRecordMapper;
import com.fx.xzt.sys.service.UserGoldAccountActivityRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserGoldAccountActivityRecordServiceImpl 
* @Description: 活动黄金领取
* @author htt
* @date 2017-12-5 下午5:43:40 
*
 */
@Service
public class UserGoldAccountActivityRecordServiceImpl extends BaseService<UserGoldAccountActivityRecord> implements UserGoldAccountActivityRecordService {

	@Resource
	UserGoldAccountActivityRecordMapper mapper;
	
	/**
	 * 查询
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer activityType, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("activityType", activityType);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelAll(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer activityType) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("activityType", activityType);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        return list;
	}


}
