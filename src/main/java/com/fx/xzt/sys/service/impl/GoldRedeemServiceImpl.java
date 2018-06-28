package com.fx.xzt.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.GoldRedeem;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.mapper.GoldRedeemMapper;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.mapper.UserAccountRecordMapper;
import com.fx.xzt.sys.service.GoldRedeemService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldRedeemServiceImpl 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:46:43 
*
 */
@Service
public class GoldRedeemServiceImpl extends BaseService<GoldRedeem> implements GoldRedeemService {
	
	@Resource
	GoldRedeemMapper goldRedeemMapper;
	@Resource
	UserAccountMapper userAccountMapper;
	@Resource
	UserAccountRecordMapper userAccountRecordMapper;

	/**
	 * 黄金赎回查询
	 */
	public PageInfo<Map<String, Object>> selectByGoldRedeem(String userName, String startTime, String endTime, 
			String channelName, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = goldRedeemMapper.selectByGoldRedeem(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 黄金赎回-导出
	 */
	public List<Map<String, Object>> excelGoldRedeem(String userName, String startTime, String endTime, String channelName, String isView) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        map.put("isView", isView);
        List<Map<String, Object>> list = goldRedeemMapper.selectByGoldRedeem(map);
        return list;
	}

	/**
	 * 黄金赎回--黄金克重统计
	 */
	public Map<String, Object> selectByGoldRedeemCount(String userName, String startTime, String endTime, String channelName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("channelName", channelName);
        return goldRedeemMapper.selectByGoldRedeemCount(map);
	}

	/**
	 * 黄金赎回--新增
	 */
	@Transactional
	public int insertGoldRedeem(GoldRedeem goldRedeem) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//新增黄金赎回数据
			goldRedeem.setCreateTime(sdf.parse(sdf.format(new Date())));
			int flag1 = goldRedeemMapper.insertGoldRedeem(goldRedeem);
			//新增账户记录
			UserAccountRecord record = new UserAccountRecord();
			record.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
			record.setUserId(goldRedeem.getUserId());
			record.setUserName(goldRedeem.getUserName());
			record.setSide(ConstantUtil.USER_ACCOUNT_RECORD_SIDE_J);
			record.setAction(ConstantUtil.USER_ACCOUNT_RECORD_ACTION_HJSH);
			record.setStatus(ConstantUtil.USER_ACCOUNT_RECORD_STATUS_YSH);
			record.setRmb(goldRedeem.getAmount());
			record.setCreateTime(sdf.parse(sdf.format(new Date())));
			record.setDescription("黄金赎回");
			int flag2 = userAccountRecordMapper.add(record);
			//更新账户余额
			Map<String, Object> map = new  HashMap<String, Object>();
			map.put("id", goldRedeem.getAccountId());
			map.put("rmb", goldRedeem.getAmount());
			int flag3 = userAccountMapper.updateByGoldReedm(map);
			if (flag1 > 0 && flag2 >0 && flag3 > 0) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}


}
