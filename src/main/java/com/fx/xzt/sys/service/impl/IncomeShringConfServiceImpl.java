package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.IncomeSharingConf;
import com.fx.xzt.sys.mapper.IncomeSharingConfMapper;
import com.fx.xzt.sys.service.IncomeSharingConfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class IncomeShringConfServiceImpl extends BaseService<IncomeSharingConf> implements IncomeSharingConfService {
    @Resource
    private IncomeSharingConfMapper incomeSharingConfMapper;

    /**
     * 添加分成设置
     * @param incomeSharingConf
     * @return
     */
    @Override
    @Transactional
    public int insertIncomeShringConf(IncomeSharingConf incomeSharingConf) {
        if (incomeSharingConf.getGoldRightPercent() != null){
            incomeSharingConf.setGoldRightPercent(incomeSharingConf.getGoldRightPercent()/100);
        }
        if (incomeSharingConf.getGoldPercent() != null){
            incomeSharingConf.setGoldPercent(incomeSharingConf.getGoldPercent()/100);
        }
        if (incomeSharingConf.getRandomPercent() != null){
            incomeSharingConf.setRandomPercent(incomeSharingConf.getRandomPercent()/100);
        }
        if (incomeSharingConf.getRealGoldPercent() != null){
            incomeSharingConf.setRealGoldPercent(incomeSharingConf.getRealGoldPercent()/100);
        }
        return incomeSharingConfMapper.insertIncomeSharingConf(incomeSharingConf);
    }

    /**
     * 修改分成设置
     * @param incomeSharingConf
     * @return
     */
    @Override
    @Transactional
    public int updateIncomeShringConf(IncomeSharingConf incomeSharingConf) {
        IncomeSharingConf incomeSharingConf1 = incomeSharingConfMapper.selectIncomeSharingConfByAgentId(incomeSharingConf.getAgentId());
        if (incomeSharingConf.getGoldRightPercent() != null){
            incomeSharingConf.setGoldRightPercent(incomeSharingConf.getGoldRightPercent()/100);
        }
        if (incomeSharingConf.getGoldPercent() != null){
            incomeSharingConf.setGoldPercent(incomeSharingConf.getGoldPercent()/100);
        }
        if (incomeSharingConf.getRandomPercent() != null){
            incomeSharingConf.setRandomPercent(incomeSharingConf.getRandomPercent()/100);
        }
        if (incomeSharingConf.getRealGoldPercent() != null){
            incomeSharingConf.setRealGoldPercent(incomeSharingConf.getRealGoldPercent()/100);
        }
        if (incomeSharingConf.getFinanceForNewPercent() != null){
            incomeSharingConf.setFinanceForNewPercent(incomeSharingConf.getFinanceForNewPercent()/100);
        }
        if (incomeSharingConf1 != null){
            return incomeSharingConfMapper.updateIncomeSharingConf(incomeSharingConf);
        }


        else {
            return incomeSharingConfMapper.insertIncomeSharingConf(incomeSharingConf);
        }

    }

    /**
     * 查询分成设置
     * @param userId
     * @return
     */
    @Override
    public IncomeSharingConf getIncomeSharingConf(Long userId) {
        IncomeSharingConf incomeSharingConf = incomeSharingConfMapper.selectIncomeSharingConf(userId);
        System.out.println(incomeSharingConf.getFinanceForNewPercent()+"financeForNew");
        incomeSharingConf.setGoldRightPercent(incomeSharingConf.getGoldPercent()*100);
        incomeSharingConf.setGoldPercent(incomeSharingConf.getGoldPercent()*100);
        incomeSharingConf.setRandomPercent(incomeSharingConf.getRandomPercent()*100);
        incomeSharingConf.setRealGoldPercent(incomeSharingConf.getRealGoldPercent()*100);
        incomeSharingConf.setFinanceForNewPercent(incomeSharingConf.getFinanceForNewPercent()*100);
        System.out.println(incomeSharingConf.getFinanceForNewPercent()+"financeForNew");
        return incomeSharingConf;
    }
}
