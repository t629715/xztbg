package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RealGoldOrderService.java
 * @Description:
 * @date 2017-09-26 16:54
 */
public interface RealGoldOrderService {

    PageInfo<Map<String, Object>> selectByRealGoldOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, String isNovice, String isView, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> excelRealGoldOrder(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, String isNovice, String isView);

    Map<String, Object> selectByRealGoldCount(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, String isNovice);

}
