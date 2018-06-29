package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.ActivityPrizeRule;
import com.fx.xzt.sys.entity.UserVoucherFinance;
import com.fx.xzt.sys.entity.WorldCupRecord;
import com.fx.xzt.sys.mapper.ActivityPrizeRuleMapper;
import com.fx.xzt.sys.mapper.UserVoucherFinanceMapper;
import com.fx.xzt.sys.mapper.WorldCupRecordMapper;
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
    @Resource
    private WorldCupRecordMapper worldCupRecordMapper;

    /**
     * 发卡券方法
     * @param competitionId 比赛Id
     * @param userId userId
     * @return
     */
    @Override
    @Transactional
    public void extractPrizeWorldCup(short isGuessing,Long competitionId,Long userId) {
        int updateNum=worldCupRecordMapper.updateSettlement(competitionId,userId);
        if(updateNum==1){//修改状态成功，发放卡券
            if(isGuessing==1){//1竞猜胜负正确
                this.extract(userId,"world_cup_68mg_coupon");//发放68mg稳赚金加赠券
            }else if(isGuessing==2){//2:竞猜胜负比分都正确
                this.extract(userId,"world_cup_18yuan_voucher");//发放18元存金宝代金券
                this.extract(userId,"world_cup_68mg_coupon");//发放68mg稳赚金加赠券
            }
        }
    }

    private void extract(Long userId,String prizeCode) {
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
    }
}
