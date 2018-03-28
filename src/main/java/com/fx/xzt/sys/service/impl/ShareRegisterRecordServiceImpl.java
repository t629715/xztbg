package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.ShareRegisterRecord;
import com.fx.xzt.sys.mapper.ShareRegisterRecordMapper;
import com.fx.xzt.sys.service.ShareRegisterRecordService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: ShareRegisterRecordServiceImpl 
* @Description: 分享注册
* @author htt
* @date 2018-3-5 上午11:05:23 
*
 */
@Service
public class ShareRegisterRecordServiceImpl extends BaseService<ShareRegisterRecord> implements ShareRegisterRecordService {
	
	@Resource
	ShareRegisterRecordMapper shareRegisterRecordMapper;

	/**
	 * 查询
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String acceptPrize, String startTime, String endTime,
			String agentName, String brokerName, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("acceptPrize", acceptPrize);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = shareRegisterRecordMapper.selectByAll(map);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map1 : list) {
                map1.put("acceptPrize", ConstantUtil.acceptPrize.toMap().get(map1.get("acceptPrize").toString()));
            }
        }
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 */
	public List<Map<String, Object>> excelAll(String userName, String acceptPrize, String startTime, String endTime,
			String agentName, String brokerName, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("acceptPrize", acceptPrize);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("isView", isView);
        List<Map<String, Object>> list = shareRegisterRecordMapper.selectByAll(map);
        return list;
	}


}
