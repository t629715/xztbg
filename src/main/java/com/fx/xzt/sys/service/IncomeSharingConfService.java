package com.fx.xzt.sys.service;



import com.fx.xzt.sys.entity.IncomeSharingConf;
public interface IncomeSharingConfService extends IService<IncomeSharingConf> {
    /**
     * 添加分成设置数据
     * @param incomeSharingConf
     * @return
     */
    public int insertIncomeShringConf(IncomeSharingConf incomeSharingConf);

    /**
     * 修改分成设置
     * @param incomeSharingConf
     * @return
     */
    public int updateIncomeShringConf(IncomeSharingConf incomeSharingConf);

    /**
     * 查询分成设置
     * @param userId
     * @return
     */
    IncomeSharingConf getIncomeSharingConf(Long userId);
}
