package com.fx.xzt.sys.service.impl;
import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.entity.WorldCupRecord;
import com.fx.xzt.sys.mapper.WorldCupCompetitionMapper;
import com.fx.xzt.sys.mapper.WorldCupRecordMapper;
import com.fx.xzt.sys.service.WorldCupCompetitionService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
@Service
public class WorldCupCompetitionServiceImpl implements WorldCupCompetitionService {
    @Resource
    WorldCupCompetitionMapper worldCupCompetitionMapper;
    @Resource
    WorldCupRecordMapper worldCupRecordMapper;

    private final Logger logger = LoggerFactory.getLogger(WorldCupCompetitionServiceImpl.class);
    /**
     * 统计两队信息  laj
     * @param record
     * @return
     */
    @Override
    public CommonResponse updateByPrimaryKeySelective(WorldCupCompetition record) {
        CommonResponse commonResponse = new CommonResponse();

        if (record.getTeamB() != null && record.getTeamA() != null){
            logger.info("A队的名字：{}---B队的名字：{}",record.getTeamAName(),record.getTeamBName());
        }
        int l= worldCupCompetitionMapper.updateOne(record);
       if(l>0){
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);

        }else {
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
        }
        return commonResponse;
    }



    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 10:29
     * @Description：获取所有的额赛程信息
     * @param users
     * @return
     */
    @Override
    public CommonResponse getAllCompetitons(final Users users) {
        final CommonResponse commonResponse = new CommonResponse();
        this.logger.info("获取赛程信息-service");
        if (users == null) {
            commonResponse.setCode(1004);
            commonResponse.setMsg("登录已过期");
            return commonResponse;
        }
        try {
            final List<WorldCupCompetition> worldCupCompetitions = (List<WorldCupCompetition>)this.worldCupCompetitionMapper.selectCompetitions();
            if (worldCupCompetitions != null && worldCupCompetitions.size() != 0) {
                final Set<String> strings = new TreeSet<String>();
                for (final WorldCupCompetition worldCupCompetition : worldCupCompetitions) {
                    String startTime = DateUtils.formatDateByMidLine(worldCupCompetition.getStasrtTime());
                    startTime = startTime.substring(0, 10);
                    strings.add(startTime);
                }
                final List<Map> mapList = new ArrayList<Map>();
                for (final String s : strings) {
                    final Map map1 = new HashMap();
                    final List<Map> maps = new ArrayList<Map>();
                    String date = null;
                    for (final WorldCupCompetition worldCupCompetition2 : worldCupCompetitions) {
                        final String startTime2 = DateUtils.formatDateByMidLine(worldCupCompetition2.getStasrtTime());
                        date = startTime2.substring(0, 10);
                        final String time = startTime2.substring(10, startTime2.length() - 3);
                        if (s.equals(date)) {
                            final Map map2 = new HashMap();
                            map2.put("competitionId",worldCupCompetition2.getId());
                            map2.put("teamA", worldCupCompetition2.getTeamA());
                            map2.put("teamAName", worldCupCompetition2.getTeamAName());
                            map2.put("teamAFlag", worldCupCompetition2.getTeamAFlag());
                            map2.put("teamB", worldCupCompetition2.getTeamB());
                            map2.put("teamBName", worldCupCompetition2.getTeamBName());
                            map2.put("teamBFlag", worldCupCompetition2.getTeamBFlag());
                            map2.put("time", time);
                            map2.put("course", worldCupCompetition2.getCourse());
                            if (worldCupCompetition2.getTeamAScore() != null) {
                                map2.put("teamAScore", worldCupCompetition2.getTeamAScore());
                            }
                            else {
                                map2.put("teamAScore", "");
                            }
                            if (worldCupCompetition2.getTeamBScore() != null) {
                                map2.put("teamBScore", worldCupCompetition2.getTeamBScore());
                            }
                            else {
                                map2.put("teamBScore", "");
                            }
                            List<WorldCupRecord> worldCupRecords = worldCupRecordMapper.selectSettlement(worldCupCompetition2.getId());
                            if (worldCupRecords.size() != 0){
                                map2.put("settlement",false);
                            }else {
                                map2.put("settlement",true);
                            }
                            map2.put("type", worldCupCompetition2.getType());
                            maps.add(map2);
                        }
                    }
                    map1.put("date", s.substring(5, s.length()));
                    map1.put("competitios", maps);
                    mapList.add(map1);
                }
                commonResponse.setData((Object)mapList);
            }
            commonResponse.setCode(1001);
        }
        catch (Exception e) {
            this.logger.error("获取赛程异常-service");
            e.printStackTrace();
            commonResponse.setCode(1002);
            throw new GlobalException("获取赛程信息", "获取赛程信息异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 17:20
     * @Description：获取竞猜结果统计
     * @param users
     * @return
     */
    @Override
    public CommonResponse selectGuessResult(Users users) {
        CommonResponse commonResponse = new CommonResponse();
        if (users == null) {
            commonResponse.setCode(1004);
            commonResponse.setMsg("登录已过期");
            return commonResponse;
        }
        try{
            List<Map<String, Object>> guessResult = worldCupRecordMapper.selectGuessResult();
            commonResponse.setMsg("查询成功");
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(guessResult);
        }catch (Exception e){
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            commonResponse.setMsg("获取竞猜统计异常");
            e.printStackTrace();
            throw new GlobalException("获取竞猜统计","获取竞猜统计异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 10:06
     * @Description：修改赛程的比分
     * @param users
     * @param worldCupCompetition
     * @return
     */
    @Override
    public CommonResponse inputScore(Users users, WorldCupCompetition worldCupCompetition) {
        CommonResponse commonResponse = new CommonResponse();
        if (users == null) {
            commonResponse.setCode(1004);
            commonResponse.setMsg("登录已过期");
            return commonResponse;
        }
        try{
            int i = worldCupCompetitionMapper.updateOne(worldCupCompetition);

            if (i>0){
                if (!worldCupCompetition.getId().toString().equals("2118070008")){
                    worldCupRecordMapper.updateUserGuessing0(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuessing1(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuesse2(worldCupCompetition.getId());
                }else {
                    worldCupRecordMapper.updateUserGuessing0(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuessing1(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuesse2(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuesse3(worldCupCompetition.getId());
                    worldCupRecordMapper.updateUserGuesse4(worldCupCompetition.getId());
                }

                commonResponse.setMsg("操作成功");
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            }else {
                commonResponse.setMsg("操作失败");
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("输入赛程比分异常");
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            commonResponse.setMsg("网络异常");
            throw new GlobalException("赛程控制","修改赛程比分异常");
        }
        return commonResponse;
    }
}
