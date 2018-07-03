package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.util.CommonResponse;

public interface WorldCupTeamsService {
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 10:36
     * @Description：获取所有的额国家队信息
     * @param users
     * @return
     */
    public CommonResponse getAllTeams(Users users);
}
