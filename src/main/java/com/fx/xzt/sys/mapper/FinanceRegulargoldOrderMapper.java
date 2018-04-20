package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.FinanceRegulargoldOrder;

import java.util.List;
import java.util.Map;

public interface FinanceRegulargoldOrderMapper extends BaseMapper<FinanceRegulargoldOrder>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/18 14:34
     * @Description：根据条件分页获取数据
     * @param conditions
     * @return
     */
    List<Map> selectAllByConditions(Map<String, Object> conditions);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/18 17:45
     * @Description：导出所有满足条件的数据
     * @param conditions
     * @return
     */
    List<Map> exportAllByConditions(Map<String, Object> conditions);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/19 15:06
     * @Description：定期金买入统计
     * @param conditions
     * @return
     */
    Integer selectTotalCount(Map<String, Object> conditions);

}