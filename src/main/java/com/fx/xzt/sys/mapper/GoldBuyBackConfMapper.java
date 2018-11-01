package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.GoldBuyBackConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoldBuyBackConfMapper {

    List<Map<String, Object>> selectGoldBuyBack();

    int insertGoldBuyBack(GoldBuyBackConf goldBuyBackConf);
    int updateGoldBuyBackByid(GoldBuyBackConf goldBuyBackConf);
}