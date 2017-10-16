package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.RealGoldConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RealGoldConfMapper extends BaseMapper<RealGoldConf>{
    /**
     * 获取实金买卖设定 tianliya
     * @return
     */
    List<Map<String, Object>> getRealGoldConf();

    /**
     * 编辑
     * @param map
     * @return
     */
    int edit(Map<String, Object> map);
}