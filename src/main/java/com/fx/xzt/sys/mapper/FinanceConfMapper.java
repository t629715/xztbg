package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.FinanceConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: FinanceConfMapper.java
 * @Description: 理财产品设定
 * @date 2017-10-09 11:21
 */
@Repository
public interface FinanceConfMapper extends BaseMapper<FinanceConf>{
    /**
     * 获取所有的理财产品
     * @return list
     */
    List<FinanceConf> selectFinanceConfs();

    /**
     * 根据产品编号删除产品
     * @param productNo
     * @return int
     */
    int deleteFinanceConfByProductNo(Integer productNo);
}