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
                                                Short upOrDown,Short orderState, Short profitLoss,Long agentId,
                                                Integer pageNum, Integer pageSize) throws ParseException;
    Map orderAnalysisCount(String startTime, String endTime,
                                                 Short upOrDown,Short orderState, Short profitLoss,Long agentId,
                                                 Integer pageNum, Integer pageSize);



}
