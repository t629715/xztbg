package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.ActivityPrizeRule;
import tk.mybatis.mapper.common.Mapper;

public interface ActivityPrizeRuleMapper extends BaseMapper<ActivityPrizeRule> {
    ActivityPrizeRule getByPrizeCode(String prizeCod);
}