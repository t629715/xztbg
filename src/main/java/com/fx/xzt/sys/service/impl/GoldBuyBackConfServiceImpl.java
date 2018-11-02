package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.GoldBuyBackConf;
import com.fx.xzt.sys.mapper.GoldBuyBackConfMapper;
import com.fx.xzt.sys.service.GoldBuyBackConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        System.out.print(list+"*********");
        for (Map m:list){
            m.put("id",m.get("id").toString());

        }
        return list;
    }

    @Override
    @Transactional
    public int updateGoldBuyBack(GoldBuyBackConf goldBuyBackConf) {

      // goldBuyBackConf.setId(goldBuyBackConf.getId());
        goldBuyBackConf.setCompanyAddress(goldBuyBackConf.getCompanyAddress());
        goldBuyBackConf.setCompanyPhone(goldBuyBackConf.getCompanyPhone());
        goldBuyBackConf.setIsStatus(goldBuyBackConf.getIsStatus());
        goldBuyBackConf.setPickUpTime(goldBuyBackConf.getPickUpTime());
        goldBuyBackConf.setPreNum(goldBuyBackConf.getPreNum());
        goldBuyBackConf.setInvalidTime(goldBuyBackConf.getInvalidTime());
        goldBuyBackConf.setState(goldBuyBackConf.getState());
        goldBuyBackConf.setRevisePrice(goldBuyBackConf.getRevisePrice());
        goldBuyBackConf.setReviseValue(goldBuyBackConf.getReviseValue());
        goldBuyBackConf.setName(goldBuyBackConf.getName());
        goldBuyBackConf.setCreateBy(goldBuyBackConf.getCreateBy());
        goldBuyBackConf.setGmtModified(new Date());
        return goldBuyBackConfMapper.updateGoldBuyBackByid(goldBuyBackConf);
    }


}
