package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.DeliveryGoldConf;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @date 2018/8/30 13:47
 */
@Repository
public interface DeliveryGoldConfMapper extends BaseMapper<DeliveryGoldConf>{
    /**
     * 获取所有的金权交易交割黄金规格信息
     * @return
     */
    List selectAll();

    /**
     * 根据id查询金权交易交割黄金规格信息
     * @param id
     * @return
     */
    DeliveryGoldConf selectById(Integer id);
    /**
     * 根据id修改金权规则
     * @param map
     * @return
     */
    int modifyDeliveryGoldConf(Map map);
}
