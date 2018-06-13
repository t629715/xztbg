package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.Activity;
import com.fx.xzt.sys.mapper.ActivityMapper;
import com.fx.xzt.sys.service.ActivityService;
import com.fx.xzt.sys.service.IService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * @CreateBy：tianliya
 * @CreateTime：2018/6/12 15:58
 * @Description：活动相关控制接口
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:59
     * @Description：获取所有活动的接口
     */
    @Override
    public CommonResponse getActivities() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Activity> activityList = activityMapper.selectAllActivity();
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(activityList);
        }catch (Exception e){
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            e.printStackTrace();
            logger.info("获取所有活动业务实现出现异常");
            throw new GlobalException("活动控制-查询","活动控制-查询异常");
        }
        logger.info("获取所有活动业成功");
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 15:59
     * @Description：根据id删除活动
     */
    @Override
    @Transactional
    public CommonResponse removeActivity(Long id) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            int i = activityMapper.deleteActivityById(id);
            if (i > 0){
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            }else {
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
                commonResponse.setMsg("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            throw new GlobalException("活动控制删除","活动控制删除异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/12 16:00
     * @Description：修改选中的活动
     */
    @Override
    @Transactional
    public CommonResponse modifyActivity(Activity activity) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            if (activity.getIsPopup() != null && activity.getIsPopup() == 1){
                activityMapper.updateAll();
            }
            int i = activityMapper.updateActivity(activity);
            if (i > 0){
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            }else {
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
                commonResponse.setMsg("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            throw new GlobalException("活动控制修改","活动控制修改异常");
        }
        return commonResponse;
    }
}


