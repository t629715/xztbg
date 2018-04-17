package com.fx.xzt.sys.service;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.FinanceConfRegulargold;
import com.fx.xzt.sys.util.CommonResponse;

/**
 * @author:tianliya
 * @CreateTime:2018-04-17 11:24
 * @Description:定期金产品设定相关业务接口
 **/
public interface FinanceConfRegulargoldService  {
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:03
     * @Description：添加定期产品
     * @param confRegulargold
     * @return
     */
    CommonResponse addOne(FinanceConfRegulargold confRegulargold) throws GlobalException;

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:03
     * @Description：根据id删除定期产品
     * @param id
     * @return
     */
    CommonResponse deleteOneById(String id) throws GlobalException;

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:04
     * @Description：修改选中的定期产品
     * @param financeConfRegulargold
     * @return
     */
    CommonResponse modifyOneSelective(FinanceConfRegulargold financeConfRegulargold) throws GlobalException;

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:04
     * @Description：根据条件分页查询定期产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonResponse getByConditions(Integer pageNum, Integer pageSize) throws GlobalException;
}
