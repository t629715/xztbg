package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: DealOrderService.java
 * @Description: 金权交易
 * @date 2017-09-25 13:42
 */
public interface OrderAnalysisService {

    PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime, String agentName,
                                                Integer upOrDown,Integer orderState, Integer profitLoss,Long agentId,
                                                Integer pageNum, Integer pageSize) throws ParseException;
    Map orderAnalysisCount(String startTime, String endTime,
                           Integer upOrDown,Integer orderState, Integer profitLoss,Long agentId,
                                                 Integer pageNum, Integer pageSize);

    List<Map<String, Object>> exportAnalysis(String startTime, String endTime, String agentName,
                                             Integer upOrDown,Integer orderState, Integer profitLoss,Long agentId
                                                );


}
