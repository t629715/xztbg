package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.DealOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: DealOrderMapper.java
 * @Description:金权交易订单
 * @date 2017-09-25 13:38
 */
@Repository
public interface DealOrderMapper extends BaseMapper<DealOrder> {

    /**
     *  金权交易查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByDealOrderAll (Map<String,Object> map);

    /**
     *  金权交易统计
     * @return
     */
    Map<String, Object> selectByDealOrderCount();
 }
