package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
			String brokerName, Integer activityType, String isView, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
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
        map.put("activityType", activityType);
        map.put("isView", isView);
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
			String brokerName, Integer activityType, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
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
        map.put("activityType", activityType);
        map.put("isView", isView);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        return list;
	}


}
