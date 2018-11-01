package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.mapper.GoldRightDealConfMapper;
import com.fx.xzt.sys.mapper.InviteRegisterRecordMapper;
import com.fx.xzt.sys.service.InviteRegisterRecordService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InviteRegisterRecordServiceImpl implements InviteRegisterRecordService {
    @Resource
    InviteRegisterRecordMapper inviteRegisterRecordMapper;

    private final Logger logger = LoggerFactory.getLogger(InviteRegisterRecordServiceImpl.class);

    @Resource
    private GoldRightDealConfMapper goldRightDealConfMapper;

    /**
     * 邀请注册记录  liaijiao
     */
    @Override
    public PageInfo<Map<String, Object>> getSelectAll(String userName, String startTime,
                                                      String endTime, String agentName, String brokerName, String isView, String acceptPrize, Integer pageNum, Integer pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            DecimalFormat df = new DecimalFormat("#.00");
            map.put("userName", userName);
            // map.put("newUserName",newUserName);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("acceptPrize", acceptPrize);
            map.put("agentName", agentName);
            map.put("brokerName", brokerName);
            map.put("isView", isView);
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String, Object>> list = inviteRegisterRecordMapper.selectAllRecords(map);
            list = this.handleData(list);
            PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
            return pagehelper;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("邀请注册记录查询", "邀请注册记录查询出现异常");
        }

    }

    /**
     * 邀友返佣明细
     * @param userName
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param agentName
     * @param brokerName
     * @param isView
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getSelectInviteFriendsAll(String userName, String startTime, String endTime, String acceptPrize, String agentName, String brokerName, String isView, Integer pageNum, Integer pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            DecimalFormat df = new DecimalFormat("#.00");
            map.put("userName", userName);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("acceptPrize", acceptPrize);
            map.put("agentName", agentName);
            map.put("brokerName", brokerName);
            map.put("isView", isView);
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String, Object>> list = inviteRegisterRecordMapper.selectInviteFriendsRecords(map);
            if (list != null && list.size() > 0) {
                for (Map<String, Object> mapList : list) {
                    Object rewardMoneyObj = mapList.get("rewardMoney");
                    if (rewardMoneyObj != null && rewardMoneyObj != "") {
                        Double rewardMoney = Double.valueOf(rewardMoneyObj.toString());
                        mapList.put("rewardMoney",(new BigDecimal(rewardMoney).multiply(new BigDecimal(0.0001))).intValue());
                    }
                }
            }
            PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
            return pagehelper;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("邀友返佣记录查询", "邀友返佣记录查询出现异常");
        }
    }

    /**
     * 导出
     */

    @Override
    public List<Map<String, Object>> exportAllRecords(String userName, String startTime,
                                                      String endTime, String isView, String acceptPrize, String agentName, String brokerName) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("userName", userName);
            // map.put("newUserName",newUserName);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("acceptPrize", acceptPrize);
            map.put("agentName", agentName);
            map.put("brokerName", brokerName);
            map.put("isView", isView);
            List<Map<String, Object>> list = inviteRegisterRecordMapper.selectAllRecords(map);
            list = this.handleData(list);
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("邀请注册记录查询", "邀请注册记录查询出现异常");
        }

    }

    @Override
    public List<Map<String, Object>> exportInviteFriendsRecords(String userName, String startTime, String endTime, String acceptPrize, String isView, String agentName, String brokerName) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("userName", userName);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("acceptPrize", acceptPrize);
            map.put("agentName", agentName);
            map.put("brokerName", brokerName);
            map.put("isView", isView);
            List<Map<String, Object>> list = inviteRegisterRecordMapper.selectInviteFriendsRecords(map);
//            list = this.handleData(list);
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("邀友返佣记录查询", "邀友返佣记录查询出现异常");
        }
    }

    private List handleData(List<Map<String, Object>> list) {
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df1 = new DecimalFormat("0.000");
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map1 : list) {
                Long userId = (Long) map1.get("newUserid");
                Map condition = new HashMap();
                condition.put("userId", userId);
                try {
                    //获取已领取的金权交易的最新的交易时间
                    condition.put("buyType", "1");
                    String lastTimeForGoldRight = inviteRegisterRecordMapper.getLastTime(condition);
                    condition.put("tableName", "deal_order");
                    condition.put("lastTime", lastTimeForGoldRight);
                    //获取未领取的金权交易产生的推荐收益
                    Map unreceivedGram = inviteRegisterRecordMapper.getUnreceivedGram(condition);
                    //获取金权交易点差
                    List<Map<String, Object>> pointCont = inviteRegisterRecordMapper.selectPointCont();

                    if (unreceivedGram != null) {
                        if (unreceivedGram.get("unreceivedGram") != null) {
                            BigDecimal reward = new BigDecimal("2").multiply(new BigDecimal(pointCont.get(0).get("pointCont").toString())
                                    .divide(new BigDecimal("0.01"))).multiply(new BigDecimal(unreceivedGram.get("unreceivedGram").toString()))
                                    .multiply(new BigDecimal("0.005"));
                            if (reward.compareTo(BigDecimal.ZERO) > 0 && reward.compareTo(new BigDecimal(1)) < 0) {
                                map1.put("unreceivedMoney", "0" + df.format(reward));
                            } else {
                                map1.put("unreceivedMoney", df.format(reward));
                            }

                        } else {
                            map1.put("unreceivedMoney", 0);
                        }

                    } else {
                        map1.put("unreceivedMoney", 0);
                    }
                } catch (Exception e) {
                    logger.error("获取已领取的金权交易分成异常--异常原因：{}", e.getMessage().toString());
                }
                //获取已领取的金生金的最新的交易时间
                condition.put("buyType", "2");
                try {
                    String lastTimeForGold = inviteRegisterRecordMapper.getLastTime(condition);
                    condition.put("tableName", "finance_regulargold_order");
                    condition.put("lastTime", lastTimeForGold);
                    //获取未领取的金权交易产生的推荐收益
                    Map unreceivedGram = inviteRegisterRecordMapper.getUnreceivedGramForGold(condition);
                    if (unreceivedGram != null) {
                        //获取未领取的金生金产生的黄金收益
                        if (unreceivedGram.get("unreceivedGram") != null) {
                            BigDecimal reward = new BigDecimal(unreceivedGram.get("unreceivedGram").toString())
                                    .multiply(new BigDecimal("0.005"));
                            map1.put("unreceivedGram", df1.format(reward));
                        } else {
                            map1.put("unreceivedGram", 0);
                        }

                    } else {
                        map1.put("unreceivedGram", 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("获取已领取的金权交易分成异常--异常原因：{}", e.getMessage().toString());
                }
                Map gram = inviteRegisterRecordMapper.getReceivedGram(userId);
                if (gram != null) {
                    if (gram.get("gram") == null) {
                        map1.put("gram", 0);
                    } else {
                        map1.put("gram", df1.format(new BigDecimal(gram.get("gram").toString())));
                    }
                } else {
                    map1.put("gram", 0);
                }
                Map money = inviteRegisterRecordMapper.getReceivedMoney(userId);
                if (money != null) {
                    if (money.get("money") == null) {
                        map1.put("money", 0);
                    } else {
                        if (new BigDecimal(money.get("money").toString()).divide(new BigDecimal(100)).compareTo(BigDecimal.ZERO) > 0 && new BigDecimal(money.get("money").toString()).divide(new BigDecimal(100)).compareTo(new BigDecimal(1)) < 0) {
                            map1.put("money", "0" + df.format(new BigDecimal(money.get("money").toString()).divide(new BigDecimal(100))));
                        } else {
                            map1.put("money", df.format(new BigDecimal(money.get("money").toString()).divide(new BigDecimal(100))));
                        }
                    }

                } else {
                    map1.put("money", 0);
                }
            }
        }
        return list;
    }
}
