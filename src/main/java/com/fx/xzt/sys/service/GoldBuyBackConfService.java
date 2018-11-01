package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.GoldBuyBackConf;

import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/11/1/001
 */
public interface GoldBuyBackConfService extends IService<GoldBuyBackConf> {

    List<Map<String, Object>> selectGoldBuyBackConf();

    int updateGoldBuyBack(GoldBuyBackConf goldBuyBackConf);
}
