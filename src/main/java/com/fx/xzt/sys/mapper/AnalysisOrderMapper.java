package com.fx.xzt.sys.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: FinanceOrderMapper.java
 * @Description: 理财交易订单
 * @date 2017-09-27 11:21
 */
@Repository
public interface AnalysisOrderMapper extends BaseMapper<Integer>{


    /**
     * 交易分析
     * @param map
     * @return
     * @Author:  tianliya
     * @Date:11:54 2017/10/19
     */
    List<Map<String, Object>> getAnalysis(Map<String, Object> map);

    /**
     * 交易分析统计
     * @param map
     * @return
     */
    Map<String, Object> getAnalysisCount(Map<String, Object> map);

    /**
     * 交易分析总条数
     * @return
     */
    int getTotal(Map<String, Object> map);
}
