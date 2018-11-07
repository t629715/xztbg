package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
			String redeemStartTime, String redeemEndTime, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = orderMapper.selectByAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelAll(String userName, String orderNo, String startTime, String endTime, String regStartTime,
			String regEndTime, String agentName, String brokerName, String status, String type, String redeemStartTime, String redeemEndTime, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        map.put("isView", isView);
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
        map.put("status", status);
        map.put("type", type);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
        Map<String, Object> map1 = orderMapper.selectByAllCount(map);
        return map1;
	}

   
}
