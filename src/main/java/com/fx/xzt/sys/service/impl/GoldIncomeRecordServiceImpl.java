package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
			String agentName, String brokerName, Integer type, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("startTypeTime", startTypeTime);
        map.put("endTypeTime", endTypeTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
        /*map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("type", type);
        map.put("isView", isView);
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
			String agentName, String brokerName, Integer type, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("startTypeTime", startTypeTime);
        map.put("endTypeTime", endTypeTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("type", type);
        map.put("isView", isView);
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
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
        /*map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("type", type);
        return goldIncomeRecordMapper.selectByGoldIncomeCount(map);
	}

	/**
	 * 黄金收益查询--黄金总重
	 */
	public Map<String, Object> selectByGoldGramCount() {
		return goldIncomeRecordMapper.selectByGoldGramCount();
	}


}
