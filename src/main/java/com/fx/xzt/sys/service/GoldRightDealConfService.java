package com.fx.xzt.sys.service;



import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.github.pagehelper.PageInfo;

public interface GoldRightDealConfService extends IService<GoldRightDealConf> {
    /**
     * 获取所有的理财产品并分页
     * @param pageNum 当前页码
     * @param pageSize 每页显示多少条
     * @return PageInfo
     */
    PageInfo<GoldRightDealConf> getAllGoldRight(Integer pageNum, Integer pageSize);

    /**
     * 根据id修改金权规则
     * @param id
     * @param name
     * @param contract
     * @param buyPercent
     * @param pointCount
     * @param volatility
     * @param minGramPerOrder
     * @param maxGramPerOrder
     * @param maxPositionCount
     * @param maxBuyCountPerDay
     * @param stopProfitSet
     * @param blowingUpSet
     * @return
     */
    Boolean updateByPrimaryKey(Integer id, String name, Integer contract,
                               Integer buyPercent, Double pointCount, Integer volatility,
                               Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet);

}
