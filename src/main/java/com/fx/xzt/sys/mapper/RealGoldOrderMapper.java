package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.RealGoldOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RealGoldOrderMapper.java
 * @Description: 是金交易订单
 * @date 2017-09-26 16:51
 */
@Repository
public interface RealGoldOrderMapper extends BaseMapper<RealGoldOrder> {

    /**
     *  实金交易订单查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByRealGoldOrder(Map<String,Object> map);

    /**
     *  是金交易订单-统计
     * @return
     */
    Map<String, Object> selectByRealGoldCount(Map<String,Object> map);

    /**
     * 实金交易记录分析
     * @return
     * @Author:  tianliya
     * @Date:12:04 2017/10/19
     */
    Map<String, Object> realGoldOrderAnalysis(Map<String,Object> map);
};
