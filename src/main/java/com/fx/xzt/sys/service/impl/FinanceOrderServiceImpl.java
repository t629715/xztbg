package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.FinanceOrder;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: FinanceOrderServiceImpl.java
 * @Description:
 * @date 2017-09-27 13:28
 */
@Service
public class FinanceOrderServiceImpl extends BaseService<FinanceOrder> implements FinanceOrderService {

    @Resource
    FinanceOrderMapper financeOrderMapper;

    /**
     *  理财交易查询
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime 买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param status 订单状态 1：持有中；2：已赎回
     * @param type  类型 1：随意存；2：黄金看涨
     * @param nper  期数
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> selectByFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                                              String agentName, String brokerName, Integer status, Integer type, String nper, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = financeOrderMapper.selectByFinanceOrder(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
    }

    /**
     *  理财交易查询-导出
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime 买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param status 订单状态 1：持有中；2：已赎回
     * @param type  类型 1：随意存；2：黄金看涨
     * @param nper  期数
     * @return
     */
    public List<Map<String, Object>> excelFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                                       String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("orderState", status);
        map.put("type", type);
        map.put("nper", nper);
        List<Map<String, Object>> list = financeOrderMapper.selectByFinanceOrder(map);
        return list;
    }

    /**
     *  理财订单金额统计
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime 买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param status 订单状态 1：持有中；2：已赎回
     * @param type  类型 1：随意存；2：黄金看涨
     * @param nper  期数
     * @return
     */
    public Map<String, Object> selectByFinanceOrderCount(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                                         String agentName, String brokerName, Integer status, Integer type, String nper) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("orderState", status);
        map.put("type", type);
        map.put("nper", nper);
        return financeOrderMapper.selectByFinanceOrderCount(map);
    }
}
