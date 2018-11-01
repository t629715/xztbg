package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.GoldBuyBackOrder;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/10/30/030
 */
public interface GoldBuyBackOrderService extends IService<GoldBuyBackOrder>{
    /**
     * 回购查询

     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> selectGoldBuyBackOrder(String userName, String orderNo, String agentName, String brokerName,
                                                         String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
                                                         String subEndTime, String expStartTime, String expEndTime, Integer pageNum, Integer pageSize);
    List<Map<String, Object>> excelGoldBuyBackOrder(String userName, String orderNo, String agentName, String brokerName,
                                                    String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
                                                    String subEndTime, String expStartTime, String expEndTime);

    int updateState(String id);
}
