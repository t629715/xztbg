package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.WorldCupTeams;

import java.util.List;

public interface WorldCupTeamsMapper extends BaseMapper<WorldCupTeams>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 10:12
     * @Description：获取所有的参赛队信息
     * @return
     */
    List<WorldCupTeams> selectTeams();
}