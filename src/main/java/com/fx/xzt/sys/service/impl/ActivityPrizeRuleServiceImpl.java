package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.ActivityPrizeRule;
import com.fx.xzt.sys.entity.UserVoucherFinance;
import com.fx.xzt.sys.mapper.ActivityPrizeRuleMapper;
import com.fx.xzt.sys.mapper.UserVoucherFinanceMapper;
import com.fx.xzt.sys.service.ActivityPrizeRuleService;
import com.fx.xzt.sys.util.DateUtils;
import com.fx.xzt.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


@Service
public class ActivityPrizeRuleServiceImpl extends BaseService<ActivityPrizeRule> implements ActivityPrizeRuleService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityPrizeRuleServiceImpl.class);

    @Resource
    private ActivityPrizeRuleMapper activityPrizeRuleMapper;
    @Resource
    private UserVoucherFinanceMapper userVoucherFinanceMapper;

    /**
     * 发卡券方法
     * @param prizeCode 奖品code
     * @param userId userId
     * @return
     */
    @Override
    @Transactional
    public Object extractPrizeWorldCup(String prizeCode,Long userId) {
        ActivityPrizeRule activityPrizeRule=activityPrizeRuleMapper.getByPrizeCode(prizeCode);
        if(activityPrizeRule!=null){
            Date nowDate=new Date();
            UserVoucherFinance userVoucherFinance=new UserVoucherFinance();
            userVoucherFinance.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
            BigDecimal value=activityPrizeRule.getValueRangeMax();
            userVoucherFinance.setDeductionValue(value.longValue());
            userVoucherFinance.setDescription(activityPrizeRule.getInfo());
            userVoucherFinance.setUserId(userId);
            userVoucherFinance.setCreateTime(nowDate);
            userVoucherFinance.setUseStatus((short)0);
            userVoucherFinance.setDataStart(nowDate);
            Integer validTime=activityPrizeRule.getValidTime();
            userVoucherFinance.setDateEnd(DateUtils.addDay(nowDate,validTime));
            userVoucherFinance.setAddType((short)2);
            userVoucherFinance.setSource("世界杯活动");
            Integer investGoldMin=activityPrizeRule.getInvestGoldMin();
            userVoucherFinance.setGoldCondition(new BigDecimal(investGoldMin));
            Integer type=activityPrizeRule.getType();
            if(type==4){//加赠券
                userVoucherFinance.setType((short)2);
            }else if(type==5){//代金券
                userVoucherFinance.setType((short)3);
            }
            userVoucherFinanceMapper.insertSelective(userVoucherFinance);
        }
        return null;
    }
}
