package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.ActivityPrizeRule;
import com.fx.xzt.sys.service.ActivityPrizeRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ActivityPrizeRuleServiceImpl extends BaseService<ActivityPrizeRule> implements ActivityPrizeRuleService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityPrizeRuleServiceImpl.class);


    @Override
    public Object extractPrizeWorldCup(String prizeCode) {
        return null;
    }
}
