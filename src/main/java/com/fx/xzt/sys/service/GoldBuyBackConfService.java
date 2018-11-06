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
    /**
     * 回购规则设置查询
     * @return
     */
    List<Map<String, Object>> selectGoldBuyBackConf();

    /**
     * 回购规则设置修改
     * @param goldBuyBackConf
     * @return
     */
    int updateGoldBuyBack(GoldBuyBackConf goldBuyBackConf);

    /**
     * 回购设置
     * @return
     */
    List<Map<String, Object>> selectGoldBuyBack();
    int updateBuyBack(GoldBuyBackConf goldBuyBackConf);
}
