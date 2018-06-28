package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserVoucherFinance;
import com.fx.xzt.sys.mapper.UserVoucherFinanceMapper;
import com.fx.xzt.sys.service.UserVoucherFinanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserVoucherFinanceServiceImpl 
* @Description: 加息券信息
* @author htt
* @date 2017-11-8 下午3:47:55 
*
 */
@Service
public class UserVoucherFinanceServiceImpl extends BaseService<UserVoucherFinance> implements UserVoucherFinanceService  {

    @Resource
    UserVoucherFinanceMapper userVoucherFinanceMapper;

	/**
	 * 加息券查询
	 */
	public PageInfo<Map<String, Object>> selectByUserVoucherFinance(String userName, String startTime, String endTime, String useStartTime, String useEndTime, 
			String agentName, String brokerName, Integer useState, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("useStatus", useState);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userVoucherFinanceMapper.selectByUserVoucherFinance(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 加息券导出
	 */
	public List<Map<String, Object>> excelUserVoucherFinance(String userName, String startTime, String endTime, String useStartTime,
			String useEndTime, String agentName, String brokerName, Integer useState, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("useStartTime", useStartTime);
        map.put("useEndTime", useEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("useState", useState);
        map.put("isView", isView);
        List<Map<String, Object>> list = userVoucherFinanceMapper.selectByUserVoucherFinance(map);
        return list;
	}
}
