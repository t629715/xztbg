package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.FinanceRegulargoldProduct;

import java.util.List;

public interface FinanceRegulargoldProductMapper extends BaseMapper<FinanceRegulargoldProduct>{


    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 10:18
     * @Description：删除稳赚金
     * @param id
     * @return
     */
    int deleteOne(Long id);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 10:19
     * @Description：添加稳赚金产品
     * @param record
     * @return
     */
    int insertOne(FinanceRegulargoldProduct record);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 10:19
     * @Description：获取所有的稳赚金
     * @return
     */
    List<FinanceRegulargoldProduct> getByAll();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 10:19
     * @Description：修改选中的稳赚金
     * @param record
     * @return
     */
    int modifyOneSelective(FinanceRegulargoldProduct record);
}