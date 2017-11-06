package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.RealGoldBuyConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RealGoldBuyConfMapper extends BaseMapper<RealGoldBuyConf>{
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 获取所有的贵金属信息
     * @return
     */
    List<Map<String, Object>> selectAllRealGoldBuyConf();

    /**
     * 根据id修改贵金属信息
     * @param map
     * @return
     */
    int updateById(Map<String,Object> map);
}