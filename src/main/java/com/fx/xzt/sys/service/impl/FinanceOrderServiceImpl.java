package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.FinanceOrder;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        map.put("isView", isView);
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
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        map.put("isView", isView);
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
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        return financeOrderMapper.selectByFinanceOrderCount(map);
    }

    /**
     * 黄金理财--查询
     */
	public PageInfo<Map<String, Object>> selectByGoldFinanceOrder(String userName, String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String redeemStartTime, String redeemEndTime, String agentName, String brokerName,
			Integer status, Integer type, String nper, String isView, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = financeOrderMapper.selectByGoldFinanceOrder(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 黄金理财-导出
	 */
	public List<Map<String, Object>> excelGoldFinanceOrder(String userName, String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String redeemStartTime, String redeemEndTime, String agentName, String brokerName,
			Integer status, Integer type, String nper, String isView) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        map.put("isView", isView);
        List<Map<String, Object>> list = financeOrderMapper.selectByGoldFinanceOrder(map);
        return list;
	}

	/**
	 * 黄金理财--统计
	 */
	public Map<String, Object> selectByGoldFinanceOrderCount(String userName, String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String redeemStartTime, String redeemEndTime, String agentName, String brokerName,
			Integer status, Integer type, String nper) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("redeemStartTime", redeemStartTime);
        map.put("redeemEndTime", redeemEndTime);
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
        map.put("status", status);
        map.put("type", type);
        map.put("nper", nper);
        return financeOrderMapper.selectByGoldFinanceOrderCount(map);
	}
}
