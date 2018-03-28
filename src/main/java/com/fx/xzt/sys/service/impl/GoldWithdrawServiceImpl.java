package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.GoldWithdraw;
import com.fx.xzt.sys.mapper.GoldWithdrawMapper;
import com.fx.xzt.sys.service.GoldWithdrawService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldWithdrawServiceImpl 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午2:04:58 
*
 */
@Service
public class GoldWithdrawServiceImpl extends BaseService<GoldWithdraw> implements  GoldWithdrawService{
	
	@Resource
	GoldWithdrawMapper goldWithdrawMapper;

	/**
	 * 黄金提取查询
	 */
	public PageInfo<Map<String, Object>> selectByGoldWithdraw(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = goldWithdrawMapper.selectByGoldWithdraw(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 黄金提取查询-导出
	 */
	public List<Map<String, Object>> excelGoldWithdraw(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("isView", isView);
        List<Map<String, Object>> list = goldWithdrawMapper.selectByGoldWithdraw(map);
        return list;
	}

	/**
	 * 黄金提取查询-黄金、物流费、保险费统计
	 */
	public Map<String, Object> selectByGoldWithdrawCount(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        return goldWithdrawMapper.selectByGoldWithdrawCount(map);
	}

	/**
	 * 黄金提取--添加物流单号
	 */
	public int addLogisticsNoById(String logisticsNo, Short status, String sendTime, Long id) {
		GoldWithdraw goldWithdraw = new GoldWithdraw();
		goldWithdraw.setId(id);
		goldWithdraw.setLogisticsNo(logisticsNo);
		goldWithdraw.setStatus(status);
		goldWithdraw.setSendTimeString(sendTime);
		return goldWithdrawMapper.updateById(goldWithdraw);
	}

	/**
	 * 黄金提取--物流单号变更
	 */
	public int updateLogisticsNoById(String logisticsNo, String updateTime, Long id) {
		GoldWithdraw goldWithdraw = new GoldWithdraw();
		goldWithdraw.setId(id);
		goldWithdraw.setLogisticsNo(logisticsNo);
		goldWithdraw.setUpdateTimeString(updateTime);
		return goldWithdrawMapper.updateById(goldWithdraw);
	}


}
