package com.fx.xzt.sys.service;



import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface FinanceConfService extends IService<FinanceConf> {
    /**
     * 获取所有的理财产品并分页
     * @param pageNum 当前页码
     * @param pageSize 每页显示多少条
     * @return PageInfo
     */
    PageInfo<Map<String,Object>> getFinanceConfs(Integer pageNum, Integer pageSize);

    /**
     * 根据产品编号删除产品
     * @param id 产品主键
     * @return Boolean
     */
    Boolean removeFinanceConfById(Long id, Integer Type);

    /**
     * 根据id修改产品信息
     * @param id
     * @param productNo
     * @param productName
     * @param yearIncomPercent
     * @param cycle
     * @param minMoney
     * @param calcMethod
     * @param redeemMethod
     * @param settleMethod
     * @return
     */
    Boolean modifyFinanceConf(Integer id, String productNo, String productName,
                              Float yearIncomPercent, Integer cycle, Float minMoney,
                              Integer calcMethod, Short redeemMethod, Short settleMethod,Integer type,
                              Integer nper);
}
