package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface InviteRegisterRecordService {

    /**
     * 邀请注册记录
     * @param userName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param isView
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getSelectAll( String userName,String startTime,String endTime,
                                                String acceptPrize,String agentName ,String brokerName, String isView, Integer pageNum, Integer pageSize);

    /**
     * 邀友返佣记录
     * @param userName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param isView
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getSelectInviteFriendsAll( String userName,String startTime,String endTime,
                                                String acceptPrize,String agentName ,String brokerName, String isView, Integer pageNum, Integer pageSize);

    /**
     * 导出
     * @param userName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param isView
     * @param agentName
     * @param brokerName
     * @return
     */
    List<Map<String, Object>> exportAllRecords(String userName, String startTime, String endTime,
                                           String acceptPrize,String isView, String agentName ,String brokerName);

    /**
     * 邀友返佣明细导出
     * @param userName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param isView
     * @param agentName
     * @param brokerName
     * @return
     */
    List<Map<String, Object>> exportInviteFriendsRecords(String userName, String startTime, String endTime,
                                               String acceptPrize,String isView, String agentName ,String brokerName);
}
