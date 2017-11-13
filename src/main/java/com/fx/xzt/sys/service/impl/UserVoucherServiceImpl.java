package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
			String useEndTime, String agentName, String brokerName, Integer useState, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("useStatus", useState);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userVoucherMapper.selectByUserVoucher(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 优惠券查询导出
	 */
	public List<Map<String, Object>> excelUserVoucher(String userName, String startTime, String endTime, String useStartTime,
			String useEndTime, String agentName, String brokerName, Integer useState) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("useState", useState);
        List<Map<String, Object>> list = userVoucherMapper.selectByUserVoucher(map);
        return list;
	}
}
