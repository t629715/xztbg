package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.FinanceConfNewplayer;
import com.fx.xzt.sys.util.CommonResponse;
/**
 * @author:tianliya
 * @CreateTime:2018-01-12 13:49
 * @Description:新手理财产品业务层接口
 **/
public interface FinanceConfNewplayerService {
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:46
     * @Description：获取所有的新手理财产品
     * @param pageNum
     * @param pageSize
     * @return
     */
   CommonResponse getAllFinanceConfsNew(Integer pageNum, Integer pageSize);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:46
     * @Description：根据id修改新手专属理财产品
     * @param financeConfNewplayer
     * @return
     */
    CommonResponse modifyFinanceConf(FinanceConfNewplayer financeConfNewplayer);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 16:05
     * @Description：根据id删除新手专属理财
     * @param id
     * @return
     */
    CommonResponse removeById(Long id);
}
