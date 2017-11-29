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
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> selectByDealOrder(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, 
    		Integer isUseCard,String upOrDown, Integer pageNum, Integer pageSize);

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
    List<Map<String, Object>> excelDealOrderMessage(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown);

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
     * 获取对冲套利的信息
     * @return
     */
    Map<String, Object> selectHandNumBuyAmount();

}
