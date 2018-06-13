package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.Activity;

import java.util.List;

public interface ActivityMapper extends BaseMapper<Activity> {
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:54
     * @Description：获取所有的活动
     * @return
     */
    List<Activity> selectAllActivity();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:55
     * @Description：根据id删除活动
     * @param id
     * @return
     */
    int deleteActivityById(Long id);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:56
     * @Description：修改活动
     * @param activity
     * @return
     */
    int updateActivity(Activity activity);
}