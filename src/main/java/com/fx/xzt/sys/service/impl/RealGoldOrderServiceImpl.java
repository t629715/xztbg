package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.RealGoldOrder;
import com.fx.xzt.sys.mapper.RealGoldOrderMapper;
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RealGoldOrderServiceImpl.java
 * @Description: 实金交易订单
 * @date 2017-09-26 16:56
 */
@Service
public class RealGoldOrderServiceImpl extends BaseService<RealGoldOrder> implements RealGoldOrderService {

    @Resource
    RealGoldOrderMapper realGoldOrderMapper;

    /**
     *  实金交易查询
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime  买入开始时间
     * @param endTime  买入结束是时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束是时间
     * @param agentName  代理商用户名
     * @param brokerName 经纪人用户名
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> selectByRealGoldOrderAll(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = realGoldOrderMapper.selectByRealGoldOrderAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
    }

    /**
     *  实金交易--导出
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime  买入开始时间
     * @param endTime  买入结束是时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束是时间
     * @param agentName  代理商用户名
     * @param brokerName 经纪人用户名
     * @return
     */
    public List<Map<String, Object>> excelRealGoldOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        List<Map<String, Object>> list = realGoldOrderMapper.selectByRealGoldOrderAll(map);
        return list;
    }

    /**
     * 实金交易统计
     * @return
     */
    public Map<String, Object> selectByRealGoldCount() {
        return realGoldOrderMapper.selectByRealGoldCount();
    }
}
