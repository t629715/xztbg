package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.Activity;
import com.fx.xzt.sys.util.CommonResponse;

/**
 * @CreateBy：tianliya
 * @CreateTime：2018/6/12 15:58
 * @Description：活动相关控制接口
 */
public interface ActivityService {
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:59
     * @Description：获取所有活动的接口
     */
    CommonResponse getActivities();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:59
     * @Description：根据id删除活动
     */
    CommonResponse removeActivity(Long id);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 16:00
     * @Description：修改选中的活动
     */
    CommonResponse modifyActivity(Activity activity);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/13 16:19
     * @Description：添加活动
     * @param activity
     * @return
     */
    CommonResponse addActivity(Activity activity);
}


