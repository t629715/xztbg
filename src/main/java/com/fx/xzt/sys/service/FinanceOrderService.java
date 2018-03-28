package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: FinanceOrderService.java
 * @Description:
 * @date 2017-09-27 13:23
 */
public interface FinanceOrderService {

    PageInfo<Map<String, Object>> selectByFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> excelFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView) throws ParseException;

    Map<String, Object> selectByFinanceOrderCount( String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper);

    /**
     * 
    * @Title: selectByGoldFinanceOrder 
    * @Description: 黄金理财订单查询
    * @param userName 用户账号
    * @param orderNo 订单号
    * @param startTime 买入开始时间
    * @param endTime 买入结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime 注册结束时间
    * @param redeemStartTime 赎回开始时间
    * @param redeemEndTime 赎回结束时间
    * @param agentName 代理商用户id
    * @param brokerName 经纪人用户id
    * @param status 状态1：持有中；2：已赎回
    * @param type 1：理财；2：黄金理财
    * @param nper 周期
    * @param pageNum
    * @param pageSize
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    PageInfo<Map<String, Object>> selectByGoldFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView, Integer pageNum, Integer pageSize);

    /**
     * 
    * @Title: excelGoldFinanceOrder 
    * @Description: 黄金理财订单导出
    * @param userName 用户账号
    * @param orderNo 订单号
    * @param startTime 买入开始时间
    * @param endTime 买入结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime 注册结束时间
    * @param redeemStartTime 赎回开始时间
    * @param redeemEndTime 赎回结束时间
    * @param agentName 代理商用户id
    * @param brokerName 经纪人用户id
    * @param status 状态1：持有中；2：已赎回
    * @param type 1：理财；2：黄金理财
    * @param nper 周期
     * @param isView 
    * @return
    * @throws ParseException    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelGoldFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper, String isView) throws ParseException;
    
    /**
     * 
    * @Title: selectByGoldFinanceOrderCount 
    * @Description: 黄金理财--统计
    * @param userName 用户账号
    * @param orderNo 订单号
    * @param startTime 买入开始时间
    * @param endTime 买入结束时间
    * @param regStartTime 注册开始时间
    * @param regEndTime 注册结束时间
    * @param redeemStartTime 赎回开始时间
    * @param redeemEndTime 赎回结束时间
    * @param agentName 代理商用户id
    * @param brokerName 经纪人用户id
    * @param status 状态1：持有中；2：已赎回
    * @param type 1：理财；2：黄金理财
    * @param nper 周期
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByGoldFinanceOrderCount( String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
    		String redeemStartTime, String redeemEndTime, String agentName, String brokerName, Integer status, Integer type, String nper);
}
