package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.GoldBuyBackConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoldBuyBackConfMapper extends BaseMapper<GoldBuyBackConf> {

    List<Map<String, Object>> selectGoldBuyBack();

    int updateGoldBuyBackByid(GoldBuyBackConf goldBuyBackConf);
    int updateBuyBackByid(GoldBuyBackConf goldBuyBackConf);
}