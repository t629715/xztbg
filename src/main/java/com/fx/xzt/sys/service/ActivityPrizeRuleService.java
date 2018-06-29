package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.ActivityPrizeRule;

public interface ActivityPrizeRuleService extends IService<ActivityPrizeRule> {


    void extractPrizeWorldCup(short isGuessing, Long competitionId,Long userId);

}
