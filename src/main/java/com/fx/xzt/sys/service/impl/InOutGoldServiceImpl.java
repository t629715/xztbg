package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.InOutGold;
import com.fx.xzt.sys.mapper.InOutGoldMapper;
import com.fx.xzt.sys.service.InOutGoldService;
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
	public PageInfo<Map<String, Object>> selectByDealOrder(String userName,
			String agentName, String brokerName, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = inOutGoldMapper.selectByInOutGold(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 出入金查询--导出
	 */
	public List<Map<String, Object>> excelDealOrder(String userName,
			String agentName, String brokerName) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        List<Map<String, Object>> list = inOutGoldMapper.selectByInOutGold(map);
        return list;
	}


}
