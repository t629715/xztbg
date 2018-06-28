package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.InOutGold;
import com.fx.xzt.sys.mapper.InOutGoldMapper;
import com.fx.xzt.sys.service.InOutGoldService;
import com.fx.xzt.sys.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InOutGoldServiceImpl 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:38:22 
*
 */
@Service
public class InOutGoldServiceImpl extends BaseService<InOutGold> implements InOutGoldService {

	@Resource
	InOutGoldMapper inOutGoldMapper;
	
	/**
	 * 出入金查询
	 */
	public PageInfo<Map<String, Object>> selectByInOutGold(String userName,
			String agentName, String brokerName, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = inOutGoldMapper.selectByInOutGold(map);
        handelInOutGold(list);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 出入金查询--导出
	 */
	public List<Map<String, Object>> excelInOutGold(String userName,
			String agentName, String brokerName, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("isView", isView);
        List<Map<String, Object>> list = inOutGoldMapper.selectByInOutGold(map);
        handelInOutGold(list);
        return list;
	}
	
	/**
	 * 
	* @Title: handelInOutGold 
	* @Description: 出入金数据处理
	* @param list    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void handelInOutGold (List<Map<String, Object>> list) {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object rmbObj = map.get("rmb");
				if (rmbObj != null && rmbObj != "") {
					Double rmb = Double.valueOf(rmbObj.toString());
					map.put("rmb", rmb/100);
				}
				Object rjObj = map.get("rj");
				if (rjObj != null && rjObj != "") {
					Double rj = Double.valueOf(rjObj.toString());
					map.put("rj", rj/100);
				}
				Object cjObj = map.get("cj");
				if (cjObj != null && cjObj != "") {
					Double cj = Double.valueOf(cjObj.toString());
					map.put("cj", cj/100);
				}
				Object jrjObj = map.get("jrj");
				if (jrjObj != null && jrjObj != "") {
					Double jrj = Double.valueOf(jrjObj.toString());
					map.put("jrj", jrj/100);
				}
            }
		}
	}

	/**
	 * 出入金分析--支付渠道分析
	 */
	public List<Map<String, Object>> selectByRechargeChannel(String type,
			String startTime, String endTime, String channel) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channel", channel);
        List<Map<String, Object>> list = inOutGoldMapper.selectByRechargeChannel(map);
        if (list != null && list.size() > 0) {
        	for (Map<String, Object> map1 : list) {
        		map1.put("rmbAmt", StringUtil.fundsHandle(map1.get("rmbAmt")));
        	}
        }
		return list;
	}

	/**
	 * 出入金分析--运营商出入金分析
	 */
	public List<Map<String, Object>> selectByAgent(String type,
			String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Map<String, Object>> list = inOutGoldMapper.selectByAgent(map);
        if (list != null && list.size() > 0) {
        	for (Map<String, Object> map1 : list) {
        		map1.put("rjSum", StringUtil.fundsHandle(map1.get("rjSum")));
        		map1.put("cjSum", StringUtil.fundsHandle(map1.get("cjSum")));
        	}
        }
		return list;
	}

	/**
	 * 出入金分析-运营商净入金分析
	 */
	public List<Map<String, Object>> selectByAgentNet(String type,
			String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Map<String, Object>> list = inOutGoldMapper.selectByAgentNet(map);
        if (list != null && list.size() > 0) {
        	for (Map<String, Object> map1 : list) {
        		map1.put("jrjSum", StringUtil.fundsHandle(map1.get("jrjSum")));
        	}
        }
		return list;
	}


}
