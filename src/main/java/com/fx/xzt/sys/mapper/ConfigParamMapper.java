package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.ConfigParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigParamMapper extends BaseMapper<ConfigParam>{
    /**
     * 根据主键查询数据
     */
    List<ConfigParam> selectConfigParamByKey(String key);
}