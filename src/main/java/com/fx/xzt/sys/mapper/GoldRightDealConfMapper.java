package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.GoldRightDealConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: GoldRightDealConfMapper.java
 * @Description: 金权规则设定
 * @date 2017-10-10 12:47
 */
@Repository
public interface GoldRightDealConfMapper extends BaseMapper<GoldRightDealConf>{
    /**
     * 获取金权规则信息
     * @param
     * @return
     */
    List<GoldRightDealConf> selectAll();

    /**
     * 根据id修改金权规则
     * @param map
     * @return
     */
    int modifyGoldRightCong(Map map);

}