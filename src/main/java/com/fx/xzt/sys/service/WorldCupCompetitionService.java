package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.util.CommonResponse;

public interface WorldCupCompetitionService {
    /**
     * 统计两队信息  laj
     * @param record
     * @return
     */
    CommonResponse updateByPrimaryKeySelective(WorldCupCompetition record);
}
