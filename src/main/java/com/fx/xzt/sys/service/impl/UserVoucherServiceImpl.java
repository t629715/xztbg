package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserVoucher;
import com.fx.xzt.sys.mapper.UserVoucherMapper;
import com.fx.xzt.sys.service.UserVoucherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserVoucherServiceImpl 
* @Description: 优惠券信息
* @author htt
* @date 2017-11-8 下午3:43:54 
*
 */
@Service
public class UserVoucherServiceImpl extends BaseService<UserVoucher> implements UserVoucherService  {

    @Resource
    UserVoucherMapper userVoucherMapper;

	/**
	 * 优惠券查询
	 */
	public PageInfo<Map<String, Object>> selectByUserVoucher(String userName, String startTime, String endTime, String useStartTime,
			String useEndTime, String agentName, String brokerName, Integer useState, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
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
        map.put("useStatus", useState);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userVoucherMapper.selectByUserVoucher(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 优惠券查询导出
	 */
	public List<Map<String, Object>> excelUserVoucher(String userName, String startTime, String endTime, String useStartTime,
			String useEndTime, String agentName, String brokerName, Integer useState, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
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
     /*   map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("useState", useState);
        map.put("isView", isView);
        List<Map<String, Object>> list = userVoucherMapper.selectByUserVoucher(map);
        return list;
	}
}
