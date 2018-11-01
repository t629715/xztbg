package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.GoldBuyBackConf;
import com.fx.xzt.sys.mapper.GoldBuyBackConfMapper;
import com.fx.xzt.sys.service.GoldBuyBackConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/11/1/001
 */
@Service
public class GoldBuyBackConfServiceImpl extends BaseService<GoldBuyBackConf> implements GoldBuyBackConfService {
    @Autowired
    GoldBuyBackConfMapper goldBuyBackConfMapper;
    @Override
    public List<Map<String, Object>> selectGoldBuyBackConf() {
        List<Map<String,Object>> list=goldBuyBackConfMapper.selectGoldBuyBack();
        for (Map m:list){
            m.put("id",m.get("id").toString());

        }
        return list;
    }

    @Override
    public int updateGoldBuyBack(GoldBuyBackConf goldBuyBackConf) {
        goldBuyBackConf.setId(goldBuyBackConf.getId());
        goldBuyBackConf.setCompanyAddress(goldBuyBackConf.getCompanyAddress());
        goldBuyBackConf.setCompanyPhone(goldBuyBackConf.getCompanyPhone());
        goldBuyBackConf.setIsStatus(goldBuyBackConf.getIsStatus());
        goldBuyBackConf
        return 0;
    }


}
