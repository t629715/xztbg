package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.mapper.GoldRightDealConfMapper;
import com.fx.xzt.sys.service.GoldRightDealConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoldRightDealConfServiceImpl extends BaseService<GoldRightDealConf> implements GoldRightDealConfService {
    @Resource
    private GoldRightDealConfMapper goldRightDealConfMapper;
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(GoldRightDealConfServiceImpl.class);

    /**
     * 查询所有金权规则信息
     * @param pageNum 当前页码
     * @param pageSize 每页显示多少条
     * @return
     */
    @Override
    public PageInfo<GoldRightDealConf> getAllGoldRight(Integer pageNum, Integer pageSize) {
        logger.debug("查询所有金权规则信息");
        PageHelper.startPage(pageNum,pageSize,true);
        List<GoldRightDealConf> goldRightDealConfs = goldRightDealConfMapper.selectAll();
        return new PageInfo<GoldRightDealConf>(goldRightDealConfs);
    }


    /**
     * 根据id修改金权规则
     * @param id
     * @param name
     * @param contract
     * @param buyPercent
     * @param pointCount
     * @param volatility
     * @param minGramPerOrder
     * @param maxGramPerOrder
     * @param maxPositionCount
     * @param maxBuyCountPerDay
     * @param stopProfitSet
     * @param blowingUpSet
     * @return
     */
    @Override
    public Boolean updateByPrimaryKey(Integer id, String name, Integer contract,
                                      Integer buyPercent, Double pointCount, Integer volatility,
                                      Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                      Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet) {
        Map map = new HashMap();
        map.put("id",id);//产品id
        map.put("name",name);//产品名字
        map.put("contract",contract);//产品合约
        map.put("buyPercent",buyPercent);//买入金额
        map.put("pointCount",pointCount);//点差
        map.put("volatility",volatility);//最下小数波动
        map.put("minGramPerOrder",minGramPerOrder);//最小持仓克数
        map.put("maxGramPerOrder",maxGramPerOrder);//最大持仓克数
        map.put("maxPositionCount",maxPositionCount);//最大建仓次数
        map.put("maxBuyCountPerDay",maxBuyCountPerDay);//
        map.put("stopProfitSet",stopProfitSet);//止盈设置
        map.put("blowingUpSet",blowingUpSet);//止损设置
        int i = goldRightDealConfMapper.modifyByPrimaryKey(map);
        if (i != 0){
            return true;
        }
        return false;
    }
}
