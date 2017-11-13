package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.mapper.AnalysisOrderMapper;
import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.mapper.RealGoldOrderMapper;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * @author tianliya
 * @Description:
 * @date 15:56 2017/10/19
 */
@Service
public class OrderAnalysisServiceImpl implements OrderAnalysisService {
    @Resource
    private AnalysisOrderMapper analysisOrderMapper;
    @Resource
    private DealOrderMapper dealOrderMapper;
    @Resource
    private FinanceOrderMapper financeOrderMapper;
    @Resource
    private RealGoldOrderMapper realGoldOrderMapper;
    @Override
    public PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime,  String agentName,
                                                       Short upOrDown,Short orderState, Short profitLoss,Long agentId,
                                                       Integer pageNum, Integer pageSize) throws ParseException {
        Map map = new HashMap();
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        Date start = null;
        Date end = null;
        if (startTime != null && startTime !=""){
            start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
        }
        if (endTime != null && endTime !=""){
            end = DateUtil.convertTimeMillisToDate(Long.valueOf(endTime));
        }
        List<String> dates = new ArrayList<String>();
        for (Date date = start;date.before(end);date = DateUtil.modify(date,0,0,1,0,0,0)){
            String time = DateUtils.formatDateByMidLine1(date);
            dates.add(time);
        }
        map.put("dates",dates);
        map.put("start",(pageNum-1)*pageSize);
        map.put("size",pageSize);
        map.put("agentId",agentId);
        List list = new ArrayList();
        try{
           list  = analysisOrderMapper.getAnalysis(map);
        }catch(Exception e){
            e.printStackTrace();
        }

        PageInfo pageInfo = new PageInfo(list);
        int count = analysisOrderMapper.getTotal(map);
        pageInfo.setTotal(count);
        pageInfo.setPages(count%pageSize==0?count/pageSize:count/pageSize+1);
       return pageInfo;
    }

    @Override
    public Map orderAnalysisCount(String startTime, String endTime,
                                                        Short upOrDown,Short orderState, Short profitLoss, Long agentId,
                                                        Integer pageNum, Integer pageSize) {
        /*Map map1 = new HashMap();
        if (startTime != null && startTime != ""){
            startTime = DateUtils.formatDateByMidLine1(DateUtil.convertTimeMillisToDate(Long.valueOf(startTime)));
        }
        if (endTime != null && endTime != ""){
            endTime = DateUtils.formatDateByMidLine1(DateUtil.convertTimeMillisToDate(Long.valueOf(endTime)));
        }
        map1.put("startTime",startTime);
        map1.put("endTime",endTime);
        map1.put("upOrDown",upOrDown);
        map1.put("orderState",orderState);
        map1.put("profitLoss",profitLoss);
        int start = (pageNum-1)*pageSize;
        map1.put("start",start);
        map1.put("size",pageSize);
        Map<String, Object> goldRightMap = dealOrderMapper.dealOrderAnalysis(map1);
        Map<String, Object> realGoldMap = realGoldOrderMapper.realGoldOrderAnalysis(map1);
        Map<String, Object> goldUpMap = financeOrderMapper.goldUpAnalysis(map1);
        Map<String, Object> randomMap = financeOrderMapper.randomAnalysis(map1);
        Map<String,Object> map = new HashMap();
        map.put("goldRightMap",goldRightMap);
        map.put("realGoldMap",realGoldMap);
        map.put("goldUpMap",goldUpMap);
        map.put("randomMap",randomMap);
        try{
            Long goldRightAmount = Long.valueOf(goldRightMap.get("goldRightAmount").toString());
            Long randomAmount = Long.valueOf(randomMap.get("randomAmount").toString());
            Long goldUpAmount = Long.valueOf(goldUpMap.get("goldUpAmount").toString());
            Long realGoldAmount = Long.valueOf(realGoldMap.get("realGoldAmount").toString());
            map.put("amountTotal",goldRightAmount+randomAmount+goldUpAmount+realGoldAmount);
        }catch (Exception e){
            e.printStackTrace();
        }

        Long usersTotal = Long.valueOf(goldRightMap.get("goldRightAmount").toString())+
                Long.valueOf(randomMap.get("randomCount").toString())+
                Long.valueOf(goldUpMap.get("goldUpCount").toString())+
                Long.valueOf(realGoldMap.get("realGoldCount").toString());
        map.put("usersTotal",usersTotal);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        return list;*/
        Map map = new HashMap();
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        map.put("agentId",agentId);
        Date start = null;
        Date end = null;
        if (startTime != null && startTime !=""){
            start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
        }
        if (endTime != null && endTime !=""){
            end = DateUtil.convertTimeMillisToDate(Long.valueOf(endTime));
        }
        List<String> dates = new ArrayList<String>();
        for (Date date = start;date.before(end);date = DateUtil.modify(date,0,0,1,0,0,0)){
            String time = DateUtils.formatDateByMidLine1(date);
            dates.add(time);
        }
        map.put("dates",dates);
        map.put("start",(pageNum-1)*pageSize);
        map.put("size",pageSize);
        Map map1 = new HashMap();
        try{
            map1 = analysisOrderMapper.getAnalysisCount(map);
        }catch (Exception e){
            e.printStackTrace();
        }

        return map1;
    }
}
