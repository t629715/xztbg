package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.UserGoldAccountRecord;


public interface UserGoldAccountRecordMapper extends BaseMapper<UserGoldAccountRecord>{

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 11:12
     * @Description：添加一条充值黄金记录
     * @param record
     * @return
     */
    int insertOne(UserGoldAccountRecord record);



}