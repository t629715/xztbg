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
        incomeSharingConfMapper.deleteByAgentId(incomeSharingConf.getAgentId());
        return incomeSharingConfMapper.insertIncomeSharingConf(incomeSharingConf);
    }

    /**
     * 查询分成设置
     * @param userId
     * @return
     */
    @Override
    public IncomeSharingConf getIncomeSharingConf(Long userId) {
        return incomeSharingConfMapper.selectIncomeSharingConf(userId);
    }
}
