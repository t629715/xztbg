package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.entity.RealGoldConf;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description:
 * @date 13:16 2017/10/16
 */
public interface RealGoldConfService extends IService<RealGoldConf>{
    /**
     * 获取实金买卖设定  tianliya
     * @return
     */
    List<Map<String, Object>> getRealGoldConf();

    /**
     * 编辑实金买卖设定 tianliya
     * @param id
     * @param insurance
     * @param logisticsFee
     * @return
     */
    int edit(Long id, Integer insurance, Integer logisticsFee);
}
