package com.fx.xzt.sys.service;



import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FinanceConfService extends IService<FinanceConf> {
    /**
     * 获取所有的理财产品并分页
     * @param pageNum 当前页码
     * @param pageSize 每页显示多少条
     * @return PageInfo
     */
    PageInfo<FinanceConf> getFinanceConfs(Integer pageNum, Integer pageSize);

    /**
     * 根据产品编号删除产品
     * @param productNo 产品编号
     * @return Boolean
     */
    Boolean removeFinanceConfByProductNo(Integer productNo);
}
