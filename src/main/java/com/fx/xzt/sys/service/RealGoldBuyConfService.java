package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.RealGoldBuyConf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 贵金属业务逻辑接口
 * tianliya
 */
public interface RealGoldBuyConfService extends IService<RealGoldBuyConf> {
    /**
     * 获取所有的贵金属
     * @return
     */
    public List<Map<String,Object>> selectAll();

    /**
     * 根据主键修改贵金属信息
     * @param id
     * @param name
     * @param productNo
     * @param cycle
     * @param redeemMethod
     * @param settleMethod
     * @param calcMethod
     * @param calcStartPoint
     * @return
     */
    public int modify(Long id, String name, String productNo, Integer cycle,
                        Short redeemMethod, Short settleMethod, Short calcMethod,
                        Float calcStartPoint,Float yearIncomPercent);

    /**
     * 根据主键删除贵金属信息
     * @param id
     * @return
     */
    public int deleteById(Long id);


    public int modify(Long id, String name,Float buyPoundage,Float sellPoundage,
                      Double maxBuyCount,Double minBuyCount);

}
