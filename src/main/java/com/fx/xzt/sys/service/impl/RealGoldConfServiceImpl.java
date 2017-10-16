package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.RealGoldConf;
import com.fx.xzt.sys.mapper.RealGoldConfMapper;
import com.fx.xzt.sys.service.RealGoldConfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    /**
     * 获取实金买卖设定
     * @return
     */
    @Override
    public List<Map<String, Object>> getRealGoldConf() {
        return realGoldConfMapper.getRealGoldConf();
    }

    /**
     * 编辑实金买卖设定
     * @param id
     * @param name
     * @param buyPoundage
     * @param insurance
     * @param logisticsFee
     * @param sellPoundage
     * @param maxBuyCount
     * @return
     */
    @Override
    public int edit(Long id, String name,Integer buyPoundage, Integer insurance, Integer logisticsFee,
                    Integer sellPoundage, Long maxBuyCount) {
        Map map = new HashMap();
        map.put("id",id);//主键
        map.put("name",name);//产品名字
        map.put("buyPoundage",buyPoundage);//买入手续费每克
        map.put("insurance",insurance);//保险费每克
        map.put("logisticsFee",logisticsFee);//物流费每次
        map.put("sellPoundage",sellPoundage);//卖出手续费每科
        map.put("maxBuyCount",maxBuyCount);//买入上线
        return realGoldConfMapper.edit(map);
    }
}
