package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.Activity;
import com.fx.xzt.sys.entity.WorldCupRecord;
import com.fx.xzt.sys.mapper.ActivityMapper;
import com.fx.xzt.sys.mapper.WorldCupRecordMapper;
import com.fx.xzt.sys.service.ActivityPrizeRuleService;
import com.fx.xzt.sys.service.ActivityService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private WorldCupRecordMapper worldCupRecordMapper;

    @Autowired
    private ActivityPrizeRuleService activityPrizeRuleService;

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

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/13 16:20
     * @Description：添加活动
     * @param activity
     * @return
     */
    @Override
    @Transactional
    public CommonResponse addActivity(Activity activity) {
        CommonResponse commonResponse = new CommonResponse();
        logger.info("进入活动业务层，调用添加方法");
        try{
            if (activity.getIsPopup() != null && activity.getIsPopup() == 1){
                activityMapper.updateAll();
            }
            int i = activityMapper.insertActivity(activity);

            if (i > 0){
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);

            }else {
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            }
            logger.info("添加成功");
        }catch (Exception e){
            logger.error("添加出现异常");
            e.printStackTrace();
            throw new GlobalException("活动控制-添加活动","活动控制-添加活动异常");
        }
        return commonResponse;
    }

    /**
     *  世界杯竞猜胜负比分活动结算
     * @author xiejunxing
     * @param competitionId 比赛ID
     * @param type  1:常规比赛竞猜,2:八队冠军竞猜 只有guessing_winner有值
     */
    public Object worldCupSettlement(Long competitionId,short type){
        CommonResponse commonResponse=new CommonResponse();
        List<WorldCupRecord> worldCupRecordList=worldCupRecordMapper.findByCompetitionIdAndType(competitionId,type);
        if(worldCupRecordList!=null&&worldCupRecordList.size()>0){
            int size=worldCupRecordList.size();
            for (int i=0;i<size;i++){
                WorldCupRecord worldCupRecord=worldCupRecordList.get(i);
                long userId=worldCupRecord.getUserId();
                short isGuessing=worldCupRecord.getIsGuessing();
                try{
                    activityPrizeRuleService.extractPrizeWorldCup(isGuessing, competitionId, userId);
                }catch (Exception e){
                    logger.error("给UserId={},发放卡券失败，失败原因："+e.toString());
                }
            }
        }
        commonResponse.setCode(Constant.RESCODE_SUCCESS);
        commonResponse.setMsg("发放成功");
        return commonResponse;
    }
}


