package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.FinanceOrder;
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
public interface FinanceOrderMapper extends BaseMapper<FinanceOrder> {

    /**
     *  理财交易订单查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByFinanceOrder(Map<String, Object> map);

    /**
     * 理财交易订单查询-金额统计
     * @param map
     * @return
     */
    Map<String, Object> selectByFinanceOrderCount(Map<String, Object> map);
}
