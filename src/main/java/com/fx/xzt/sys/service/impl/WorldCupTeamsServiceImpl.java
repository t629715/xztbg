package com.fx.xzt.sys.service.impl;
import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.entity.WorldCupTeams;
import com.fx.xzt.sys.mapper.WorldCupCompetitionMapper;
import com.fx.xzt.sys.mapper.WorldCupTeamsMapper;
import com.fx.xzt.sys.service.WorldCupCompetitionService;
import com.fx.xzt.sys.service.WorldCupTeamsService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

@Service
public class WorldCupTeamsServiceImpl implements WorldCupTeamsService {
    @Resource
    WorldCupTeamsMapper worldCupTeamsMapper;

    private final Logger logger = LoggerFactory.getLogger(WorldCupTeamsServiceImpl.class);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 10:29
     * @Description：获取所有的额赛程信息
     * @param users
     * @return
     */
    @Override
    public CommonResponse getAllTeams(final Users users) {
        final CommonResponse commonResponse = new CommonResponse();
        this.logger.info("获取国家队信息-service");
        if (users == null) {
            commonResponse.setCode(1004);
            commonResponse.setMsg("登录已过期");
            return commonResponse;
        }
        try {
            final List<WorldCupTeams> worldCupCompetitions = (List<WorldCupTeams>)this.worldCupTeamsMapper.selectTeams();
            commonResponse.setData(worldCupCompetitions);
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
        }
        catch (Exception e) {
            this.logger.error("获取参赛国家队信息异常-service");
            e.printStackTrace();
            commonResponse.setCode(1002);
            throw new GlobalException("获取参赛国家队信息", "获取参赛国家队信息异常");
        }
        return commonResponse;
    }
}
