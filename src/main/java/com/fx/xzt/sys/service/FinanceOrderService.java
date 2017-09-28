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
                                                       String agentName, String brokerName, Integer status, Integer type, String nper, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> excelFinanceOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                                String agentName, String brokerName, Integer status, Integer type, String nper) throws ParseException;

    Map<String, Object> selectByFinanceOrderCount( String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                                   String agentName, String brokerName, Integer status, Integer type, String nper);
}
