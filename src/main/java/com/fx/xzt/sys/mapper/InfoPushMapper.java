package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.InfoPush;

import java.util.List;

/**
 * @CreateBy：tianliya
 * @CreateTime：2018/6/5 14:14
 * @Description：首页资讯推送持久层
 */
public interface InfoPushMapper extends BaseMapper<InfoPush>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 14:14
     * @Description：获取所有的推送的资讯
     * @return
     */
    List<InfoPush> selectAllInfoPush();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 14:15
     * @Description：添加一条推送信息
     * @param infoPush
     * @return
     */
    int insertOne(InfoPush infoPush);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/5 14:16
     * @Description：根据id删除推送资讯
     * @param id
     * @return
     */
    int deleteInfoPushById(String id);
}