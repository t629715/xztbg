package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface InviteRegisterRecordService {

    /**
     * 邀请注册记录
     * @param shareUserName 
     * @param newUserName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param isView
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getSelectAll( String shareUserName,String newUserName,String startTime,String endTime,
                                                String acceptPrize,String isView, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> exportAllRecords(String shareUserName, String newUserName, String startTime, String endTime,
                                           String acceptPrize, String isView, Integer pageNum, Integer pageSize);
}
