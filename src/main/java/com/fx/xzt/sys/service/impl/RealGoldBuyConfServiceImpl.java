package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.RealGoldBuyConf;
import com.fx.xzt.sys.mapper.RealGoldBuyConfMapper;
import com.fx.xzt.sys.service.RealGoldBuyConfService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 贵金属业务逻辑实现类
 * tianliya
 */
@Service
public class RealGoldBuyConfServiceImpl extends BaseService<RealGoldBuyConf> implements RealGoldBuyConfService{
    @Resource
    RealGoldBuyConfMapper realGoldBuyConfMapper;

    /**
     * 获取所有的贵金属信息
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAll() {
        List<Map<String,Object>> list = realGoldBuyConfMapper.selectAllRealGoldBuyConf();
        for (Map m:list){
            m.put("id",m.get("id").toString());
        }
        return list;
    }

    /**
     * 修改所有的贵金属信息
     * @param id
     * @param name
     * @param productNo
     * @param cycle
     * @param redeemMethod
     * @param settleMethod
     * @param calcMethod
     * @param calcStartPoint
     * @return
     */
    @Override
    public int modify(Long id, String name, String productNo,
                      Integer cycle, Short redeemMethod, Short settleMethod,
                      Short calcMethod, Float calcStartPoint) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("productNo",productNo);
        map.put("cycle",cycle);
        map.put("redeemMethod",redeemMethod);
        map.put("settleMethod",settleMethod);
        map.put("calcMethod",calcMethod);
        map.put("calcStartPoint",calcStartPoint);
        return realGoldBuyConfMapper.updateById(map);
    }
    /**
     * 修改所有的贵金属信息
     * @param id
     * @param name
     * @return
     */
    @Override
    public int modify(Long id, String name,Float buyPoundage,Float sellPoundage,Double maxBuyCount,Double minBuyCount

                      ) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("buyPoundage",buyPoundage);
        map.put("sellPoundage",sellPoundage);
        map.put("maxBuyCount",maxBuyCount);
        map.put("minBuyCount",minBuyCount);
        return realGoldBuyConfMapper.updateById(map);
    }
    /**
     * 根据主键删除贵金属信息
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return realGoldBuyConfMapper.deleteById(id);
    }
}
