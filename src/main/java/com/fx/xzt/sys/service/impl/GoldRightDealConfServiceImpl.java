package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.mapper.GoldRightDealConfMapper;
import com.fx.xzt.sys.service.GoldRightDealConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public PageInfo<Map<String, Object>> getAllGoldRight(Integer pageNum, Integer pageSize) {
        logger.debug("查询所有金权规则信息");
        PageHelper.startPage(pageNum,pageSize,true);
        List<Map<String, Object>> goldRightDealConfs = goldRightDealConfMapper.selectAll();

        for (Map m:goldRightDealConfs){
            m.put("id",m.get("id").toString());

        }
        return new PageInfo<Map<String, Object>>(goldRightDealConfs);
    }


    /**
     *
     * @param id 产品id
     * @param name 产品名字
     * @param contract 产品合约
     * @param buyPercent 买入金额
     * @param pointCount 点差
     * @param pointCountDown 买跌点差
     * @param pointCountUp 买涨点差
     * @param deliveryMax 交割最大百分比
     * @param deliveryMin 交割最小百分比
     * @param minProfitPercent 最低止盈点
     * @param volatility 最下小数波动
     * @param stopLossSet 最大止损设置
     * @param minLossPercent 最小持仓克数
     * @param volatilityProfitLoss 最小波动盈亏
     * @param minGramPerOrder 最小持仓克数
     * @param maxGramPerOrder 最大持仓克数
     * @param maxPositionCount 最大建仓次数
     * @param maxBuyCountPerDay
     * @param stopProfitSet 止盈设置
     * @param blowingUpSet
     * @param status
     * @return
     */
    @Transactional
    public Boolean updateByPrimaryKey(Long id, String name, Integer contract,
                                      Float buyPercent, Double pointCount, BigDecimal pointCountDown, BigDecimal pointCountUp, BigDecimal deliveryMax,
                                      BigDecimal  deliveryMin, Float minProfitPercent,
                                      Double volatility, Double stopLossSet, Float minLossPercent, Double volatilityProfitLoss,
                                      Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                      Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet, Integer status) {
        Map map = new HashMap();
        map.put("id",id);//产品id
        map.put("name",name);//产品名字
        map.put("contract",contract);//产品合约
        map.put("buyPercent",buyPercent);//买入金额
        map.put("pointCount",pointCount);//点差
        map.put("pointCountDown",pointCountDown);//买跌点差
        map.put("pointCountUp",pointCountUp);//买涨点差
        map.put("deliveryMax",deliveryMax);//交割最大百分比
        map.put("deliveryMin",deliveryMin);//交割最小百分比
        map.put("minProfitPercent",minProfitPercent);//最低止盈点
        map.put("volatility",volatility);//最下小数波动
        map.put("minGramPerOrder",minGramPerOrder);//最小持仓克数
        map.put("maxGramPerOrder",maxGramPerOrder);//最大持仓克数
        map.put("maxPositionCount",maxPositionCount);//最大建仓次数
        map.put("maxBuyCountPerDay",maxBuyCountPerDay);//
        map.put("stopProfitSet",stopProfitSet);//止盈设置
        map.put("blowingUpSet",blowingUpSet);//止损设置
        map.put("stopLossSet",stopLossSet);//最大止损设置
        map.put("minLossPercent",minLossPercent);//最小止损设置
        map.put("volatilityProfitLoss",volatilityProfitLoss);//最小波动盈亏
        map.put("status",status);
        int i = goldRightDealConfMapper.modifyGoldRightCong(map);
        if (i != 0){
            return true;
        }
        return false;
    }

    @Override
    public GoldRightDealConf getGoldRight(Long id) {
        return goldRightDealConfMapper.selectOneById(id);
    }
}
