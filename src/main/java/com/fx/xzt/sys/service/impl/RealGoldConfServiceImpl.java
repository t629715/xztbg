package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.RealGoldConf;
import com.fx.xzt.sys.mapper.RealGoldBuyConfMapper;
import com.fx.xzt.sys.mapper.RealGoldConfMapper;
import com.fx.xzt.sys.service.RealGoldConfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description: 实金买卖设定实现类
 * @date 13:19 2017/10/16
 */
@Service
public class RealGoldConfServiceImpl extends BaseService<RealGoldConf> implements RealGoldConfService {
    @Resource
    private RealGoldConfMapper realGoldConfMapper;
    @Resource
    RealGoldBuyConfMapper realGoldBuyConfMapper;
    /**
     * 获取实金买卖设定
     * @return
     */
    @Override
    public List<Map<String, Object>> getRealGoldConf() {
        List<Map<String, Object>> list = realGoldConfMapper.getRealGoldConf();
        List realGoldConf = new ArrayList();
        for (Map<String, Object> m:list){
            String s = m.get("id").toString();
            int insurance = Integer.parseInt(m.get("insurance").toString());
            int logisticsFee = Integer.parseInt(m.get("logisticsFee").toString());
            m = realGoldBuyConfMapper.selectRealGoldBuyConf();
            m.put("insurance",insurance);
            m.put("logisticsFee",logisticsFee);
            m.put("s",s);
            realGoldConf.add(m);
        }
        return realGoldConf;
    }

    /**
     * 编辑实金买卖设定
     * @param id
     * @param insurance
     * @param logisticsFee
     * @return
     */
    @Override
    public int edit(Long id,Integer insurance, Integer logisticsFee) {
        Map map = new HashMap();
        map.put("id",id);//主键
        map.put("insurance",insurance);//保险费每克
        map.put("logisticsFee",logisticsFee);//物流费每次
        return realGoldConfMapper.edit(map);
    }
}
