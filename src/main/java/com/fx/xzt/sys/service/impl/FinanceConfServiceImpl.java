package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.mapper.FinanceConfMapper;
import com.fx.xzt.sys.service.FinanceConfService;
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
    public PageInfo<FinanceConf> getFinanceConfs(Integer pageNum, Integer pageSize) {
        logger.debug("查询所有理财产品");
        PageHelper.startPage(pageNum,pageSize,true);
        List<FinanceConf> financeConfs = financeConfMapper.selectFinanceConfs();
        return new PageInfo<FinanceConf>(financeConfs);
    }

    /**
     * 根据产品编号删除理财产品
     * @param productNo 产品编号
     * @return
     */
    @Override
    public Boolean removeFinanceConfByProductNo(Integer productNo) {
        logger.debug("根据产品编号删除理财产品");
        Integer i = financeConfMapper.deleteFinanceConfByProductNo(productNo);
        if (i != null){
            return true;
        }
        return false;
    }
}
