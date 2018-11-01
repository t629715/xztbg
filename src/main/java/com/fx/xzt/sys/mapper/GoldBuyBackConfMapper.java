package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.GoldBuyBackConf;

import java.util.List;
import java.util.Map;

public interface GoldBuyBackConfMapper {

    List<Map<String, Object>> selectGoldBuyBack(GoldBuyBackConf goldBuyBackConf);

    int insertGoldBuyBack(GoldBuyBackConf goldBuyBackConf);

}