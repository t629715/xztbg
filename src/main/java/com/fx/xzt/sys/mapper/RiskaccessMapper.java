package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.Riskaccess;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RiskaccessMapper.java
 * @Description:
 * @date 2017-09-22 10:35
 */
@Repository
public interface RiskaccessMapper extends BaseMapper<Riskaccess> {

    /**
     *  查询风险评测信息
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByRiskaccessAll (Map<String,Object> map);

    int updateById(Riskaccess riskaccess);
}
