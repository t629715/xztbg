package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.mapper.FinanceConfMapper;
import com.fx.xzt.sys.service.FinanceConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceConfServiceImpl extends BaseService<FinanceConf> implements FinanceConfService {
    @Resource
    private FinanceConfMapper financeConfMapper;
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(FinanceConfServiceImpl.class);

    /**
     * 查询所有的理财产品
     * @param pageNum 当前页码
     * @param pageSize 每页显示多少条
     * @return
     */
    @Override
    public PageInfo<Map<String,Object>> getFinanceConfs(Integer pageNum, Integer pageSize) {
        logger.debug("查询所有理财产品");
        PageHelper.startPage(pageNum,pageSize,true);
        List<Map<String,Object>> financeConfs = financeConfMapper.selectFinanceConfs();
        return new PageInfo<Map<String,Object>>(financeConfs);
    }

    /**
     * 根据产品编号删除理财产品
     * @param id 产品主键
     * @return
     */
    @Transactional
    public Boolean removeFinanceConfById(Long id, Integer type) {
        logger.debug("根据产品编号删除理财产品");
        Integer i = 0;
        if (type==1){
            i = financeConfMapper.deleteFinanceConfById(id);
        }else {
            i = financeConfMapper.deleteFinanceConfEarnById(id);
        }

        if (i != null){
            return true;
        }
        return false;
    }

    /**
     * 根据id修改产品信息
     * @param id
     * @param productNo
     * @param productName
     * @param yearIncomPercent
     * @param cycle
     * @param minMoney
     * @param calcMethod
     * @param redeemMethod
     * @param settleMethod
     * @return
     */
    @Transactional
    public Boolean modifyFinanceConf(Integer id, String productNo, String productName,
                                     Float yearIncomPercent, Integer cycle, Float minMoney,
                                     Integer calcMethod, Short redeemMethod, Short settleMethod,Integer type,
                                     Integer nper) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("productNo",productNo);
        map.put("productName",productName);
        map.put("yearIncomPercent",yearIncomPercent);
        map.put("cycle",cycle);
        map.put("minMoney",minMoney);
        map.put("calcMethod",calcMethod);
        map.put("redeemMethod",redeemMethod);
        map.put("settleMethod",settleMethod);
        map.put("nper",nper);
        int i = 0;
        if (type == 1){
            i = financeConfMapper.modifyFinanceConf(map);

        }else {
            i = financeConfMapper.modifyFinanceConfEarn(map);
        }

        if (i != 0){
            return true;
        }
        return false;
    }
}
