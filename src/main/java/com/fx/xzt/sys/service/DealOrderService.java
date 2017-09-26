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

    PageInfo<Map<String, Object>> selectByDealOrderAll(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> excelDealOrderMessage(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState);

    Map<String, Object> selectByDealOrderCount();

}
