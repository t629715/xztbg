package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.GoldIncomeRecord;
import com.fx.xzt.sys.mapper.GoldIncomeRecordMapper;
import com.fx.xzt.sys.service.GoldIncomeRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldIncomeRecordServiceImpl 
* @Description: 黄金收益记录
* @author htt
* @date 2017-9-30 下午2:22:25 
*
 */
@Service
public class GoldIncomeRecordServiceImpl extends BaseService<GoldIncomeRecord> implements GoldIncomeRecordService {
	
	@Resource
	GoldIncomeRecordMapper goldIncomeRecordMapper;

	/**
	 * 黄金收益记录-查询
	 */
	public PageInfo<Map<String, Object>> selectByGoldIncome(String userName,
			String startTime, String endTime, String startTypeTime, String endTypeTime, 
			String agentName, String brokerName, Integer type, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("startTypeTime", startTypeTime);
        map.put("endTypeTime", endTypeTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("type", type);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = goldIncomeRecordMapper.selectByGoldIncome(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 黄金收益记录查询-导出
	 */
	public List<Map<String, Object>> excelGoldIncome(String userName,
			String startTime, String endTime, String startTypeTime, String endTypeTime, 
			String agentName, String brokerName, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("startTypeTime", startTypeTime);
        map.put("endTypeTime", endTypeTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("type", type);
        List<Map<String, Object>> list = goldIncomeRecordMapper.selectByGoldIncome(map);
        return list;
	}

	/**
	 * 黄金收益记录查询-收益支出
	 */
	public Map<String, Object> selectByGoldIncomeCount(String userName,
			String startTime, String endTime, String startTypeTime, String endTypeTime, 
			String agentName, String brokerName, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("startTypeTime", startTypeTime);
        map.put("endTypeTime", endTypeTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("type", type);
        return goldIncomeRecordMapper.selectByGoldIncomeCount(map);
	}


}
