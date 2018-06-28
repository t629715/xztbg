package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: DealOrderService.java
 * @Description: 金权交易
 * @date 2017-09-25 13:42
 */
public interface DealOrderService {
    /**
     * 获取交易信息
     * @param userName
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param regStartTime
     * @param regEndTime
     * @param agentName
     * @param brokerName
     * @param orderState
     * @param isView 
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> selectByDealOrder(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, 
    		Integer isUseCard,String upOrDown, String isView, Integer pageNum, Integer pageSize);

    /**
     *
     * @param userName
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param regStartTime
     * @param regEndTime
     * @param agentName
     * @param brokerName
     * @param orderState
     * @param isView 
     * @return
     */
    List<Map<String, Object>> excelDealOrderMessage(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown, String isView);

    /**
     *
     * @param userName
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param regStartTime
     * @param regEndTime
     * @param agentName
     * @param brokerName
     * @param orderState
     * @return
     */
    Map<String, Object> selectByDealOrderCount(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown);
    
    /**
     * 
    * @Title: selectByDealOrderCount2 
    * @Description: 金权交易金额--持仓中保证金统计
    * @param userName
    * @param orderNo
    * @param startTime
    * @param endTime
    * @param regStartTime
    * @param regEndTime
    * @param agentName
    * @param brokerName
    * @param orderState
    * @param isUseCard
    * @param upOrDown
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByDealOrderCount2(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown);

    /**
     * 获取对冲套利的信息
     * @return
     */
    Map<String, Object> selectHandNumBuyAmount();

}
