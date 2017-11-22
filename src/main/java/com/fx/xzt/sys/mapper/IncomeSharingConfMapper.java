package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.IncomeSharingConf;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeSharingConfMapper extends BaseMapper<IncomeSharingConf> {

    int deleteByAgentId(Long agentId);


    int insertIncomeSharingConf(IncomeSharingConf record);

    IncomeSharingConf selectIncomeSharingConf(Long id);
    IncomeSharingConf selectIncomeSharingConfByAgentId(Long agentId);

    int updateIncomeSharingConf(IncomeSharingConf record);

}