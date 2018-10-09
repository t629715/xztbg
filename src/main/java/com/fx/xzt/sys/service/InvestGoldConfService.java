package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InvestGoldConf;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Description：
 * Author: liaijiao
 * Date:  2018/10/9 9:36
 */
public interface InvestGoldConfService {
    /**
     * 获取所有的投资金条配置信息
     * @param pageNum
     * @param pageSize
     * @return
     */

    PageInfo<Map<String, Object>> getAllInvestGoldConf(Integer pageNum, Integer pageSize);

    /**
     * 根据id修改
     * @param id
     * @param name 产品名称
     * @param goldWeight 金条规格
     * @param withdrawService 每笔提金服务费
     * @param minBuyCount
     * @param maxBuyCount
     * @param logisticsFee
     * @param imgUrl
     * @return
     */
    Boolean updateByPrimaryKey(Long id, String name, Integer goldWeight, String withdrawService, Integer minBuyCount,
                               Integer maxBuyCount, String logisticsFee, String imgUrl);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    InvestGoldConf getInvestGoldConf(Long id);
}
