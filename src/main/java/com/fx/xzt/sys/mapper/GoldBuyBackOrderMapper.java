package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.GoldBuyBackOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoldBuyBackOrderMapper extends BaseMapper<GoldBuyBackOrder> {

    List<Map<String, Object>> selectYgGold(Map<String, Object> map);

    /**
     * 确认收货无误
     * @param goldBuyBackOrder
     * @return
     */
    int updateState(GoldBuyBackOrder goldBuyBackOrder);

    int updateById(GoldBuyBackOrder record);


}