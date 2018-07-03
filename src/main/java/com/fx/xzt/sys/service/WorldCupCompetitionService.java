package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.util.CommonResponse;

public interface WorldCupCompetitionService {
    /**
     * 统计两队信息  laj
     * @param record
     * @return
     */
    CommonResponse updateByPrimaryKeySelective(WorldCupCompetition record);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 10:29
     * @Description：获取所有的赛程信息
     * @return
     */
    public CommonResponse getAllCompetitons(Users users);
}
