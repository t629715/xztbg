package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: DealOrderService.java
 * @Description: 金权交易
 * @date 2017-09-25 13:42
 */
public interface OrderAnalysisService {

    PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime, String agentName,Integer pageNum, Integer pageSize);



}
