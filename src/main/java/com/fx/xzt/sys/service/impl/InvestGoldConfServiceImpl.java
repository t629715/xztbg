package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.InvestGoldConf;
import com.fx.xzt.sys.mapper.InvestGoldConfMapper;
import com.fx.xzt.sys.service.InvestGoldConfService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 * Author: liaijiao
 * Date:  2018/10/9 9:36
 */
@Service
public class InvestGoldConfServiceImpl implements InvestGoldConfService {
    private static final Logger logger = LoggerFactory.getLogger(InvestGoldConfServiceImpl.class);
    @Resource
    private InvestGoldConfMapper investGoldConfMapper;
    @Override
    public PageInfo<Map<String, Object>> getAllInvestGoldConf(Integer pageNum, Integer pageSize) {
        logger.debug("查询所有投资金条信息");
        PageHelper.startPage(pageNum,pageSize,true);
        List<Map<String, Object>> investGoldConf = investGoldConfMapper.selectAllInvestGoldConf();

        for (Map m:investGoldConf){
            m.put("id",m.get("id").toString());

        }
        handle(investGoldConf);
        return new PageInfo<Map<String, Object>>(investGoldConf);
    }

    @Override
    @Transactional
    public Boolean updateByPrimaryKey(Long id, String name, Integer goldWeight, String  withdrawService,
                                      Integer minBuyCount, Integer maxBuyCount, String logisticsFee, String imgUrl) {
        Map map = new HashMap();

        map.put("id",id);
        map.put("name",name);

        map.put("goldWeight",goldWeight);
       Float withdrawServices=new Float(withdrawService);

        map.put("withdrawService",withdrawServices*100);
        Float logisticsFees=new Float(logisticsFee);

        map.put("logisticsFee",logisticsFees*100);

        map.put("minBuyCount",minBuyCount);
        map.put("maxBuyCount",maxBuyCount);
        map.put("imgUrl",imgUrl);
        Date updateTime=new Date();
        map.put("updateTime",updateTime);
        int l=  investGoldConfMapper.updateById(map);

        if(l!=0){
            return  true;
        }
        return  false;
     }

    @Override
    public InvestGoldConf getInvestGoldConf(Long id) {
        return investGoldConfMapper.selectById(id);
    }
    public void handle(List<Map<String, Object>> list)  {
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Object statusObj = map.get("status");
                if (statusObj != null && statusObj != "") {
                    map.put("status", ConstantUtil.statuss.toMap().get(statusObj.toString()));
                }
                Object withdrawServiceObj = map.get("withdrawService");
                if (withdrawServiceObj != null && withdrawServiceObj != "") {
                    Double withdrawService = Double.valueOf(withdrawServiceObj.toString());
                    map.put("withdrawService",withdrawService/100);
                }
                Object logisticsFeeObj = map.get("logisticsFee");
                if (logisticsFeeObj != null && logisticsFeeObj != "") {
                    Double logisticsFee = Double.valueOf(logisticsFeeObj.toString());
                    map.put("logisticsFee",logisticsFee/100);
                }


            }

        }
    }
}
