package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.mapper.RealGoldOrderMapper;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tianliya
 * @Description:
 * @date 15:56 2017/10/19
 */
@Service
public class OrderAnalysisServiceImpl implements OrderAnalysisService {
    @Resource
    private FinanceOrderMapper financeOrderMapper;
    @Resource
    DealOrderMapper dealOrderMapper;
    @Resource
    RealGoldOrderMapper realGoldOrderMapper;
    @Override
    public PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime,  String agentName,
                                                       Short upOrDown,Short orderState, Short profitLoss,
                                                       Integer pageNum, Integer pageSize) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("agentName",agentName);
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        List<Map<String, Object>> goldUp=financeOrderMapper.goldUpAnalysis(map);
        List<Map<String, Object>> random=financeOrderMapper.randomAnalysis(map);
        List<Map<String, Object>> realGold = realGoldOrderMapper.realGoldOrderAnalysis(map);
        List<Map<String, Object>> goldRight = dealOrderMapper.dealOrderAnalysis(map);
        List<Map<String, Object>> analysis = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date maxTime1 = new Date();
        Date maxTime2 = new Date();
        Date maxTime3 = new Date();
        Date maxTime4 = new Date();
        Date minTime1 = new Date();
        Date minTime2 = new Date();
        Date minTime3 = new Date();
        Date minTime4 = new Date();
        if (goldUp!=null && goldUp.size()!=0){
            try {
                maxTime1 = simpleDateFormat.parse(goldUp.get(0).get("goldUpTime").toString());
                minTime1 = simpleDateFormat.parse(goldUp.get(goldUp.size()-1).get("goldUpTime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (random!=null && random.size()!=0){
            try {
                maxTime2 = simpleDateFormat.parse(random.get(0).get("randomTime").toString());
                minTime2 = simpleDateFormat.parse(random.get(random.size()-1).get("randomTime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (realGold!=null && realGold.size()!=0){
            try {
                maxTime3 = simpleDateFormat.parse(realGold.get(0).get("realGoldTime").toString());
                minTime3 = simpleDateFormat.parse(realGold.get(realGold.size()-1).get("realGoldTime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (goldUp!=null && goldUp.size()!=0){
            try {
                maxTime4 = simpleDateFormat.parse(goldRight.get(0).get("goldRightTime").toString());
                minTime4 = simpleDateFormat.parse(goldRight.get(goldRight.size()-1).get("goldRightTime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (maxTime1.before(maxTime2)){
            maxTime1=maxTime2;
        }
        if (maxTime1.before(maxTime3)){
            maxTime1 = maxTime3;
        }
        if (maxTime1.before(maxTime4)){
            maxTime1 = maxTime4;
        }
        if (minTime1.after(minTime2)){
            minTime1 = minTime2;
        }
        if (minTime1.after(minTime3)){
            minTime1 = minTime3;
        }
        if (minTime1.after(minTime4)){
            minTime1 = minTime4;
        }
        for(Date t = maxTime1;t.after(minTime1);){

            int g = 0;
            Map m = new HashMap();
            for (int i = 0;i<goldUp.size();){
                Map ma = goldUp.get(i);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(ma.get("goldUpTime").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t.compareTo(date)!=0){
                    m.put("goldUpAmount",0);
                    m.put("goldUpCount",0);
                    i++;
                }else{
                    m.put("goldUpAmount",ma.get("goldUpAmount"));
                    m.put("goldUpCount",ma.get("goldUpCount"));
                    i=0;
                    g++;
                    break;
                }
            }
            for (int j = 0;j<random.size();){
                Map ma = random.get(j);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(ma.get("randomTime").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t.compareTo(date)!=0){
                    m.put("randomAmount",0);
                    m.put("randomCount",0);
                    j++;
                }else{
                    m.put("randomAmount",ma.get("randomAmount"));
                    m.put("randomCount",ma.get("randomCount"));
                    j=0;
                    g++;
                    break;

                }
            }
            for (int k = 0;k<goldRight.size();){
                Map ma = goldRight.get(k);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(ma.get("goldRightTime").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t.compareTo(date)!=0){
                    m.put("goldRightAmount",0);
                    m.put("goldRightCount",0);
                    k++;
                }else{
                    m.put("goldRightAmount",ma.get("goldRightAmount"));
                    m.put("goldRightCount",ma.get("goldRightCount"));
                    k=0;
                    g++;
                    break;
                }
            }
            for (int l = 0;l<realGold.size();){
                Map ma = realGold.get(l);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(ma.get("realGoldTime").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t.compareTo(date)!=0){
                    m.put("realGoldAmount",0);
                    m.put("realGoldCount",0);
                    l++;
                }else{
                    m.put("realGoldAmount",ma.get("realGoldAmount"));
                    m.put("realGoldCount",ma.get("realGoldCount"));
                    l=0;
                    g++;
                    break;
                }
            }
            if (g!=0){
                m.put("time",t);
                analysis.add(m);
            }
            try {
                t = simpleDateFormat.parse(simpleDateFormat.format(t.getTime()-24*3600*1000));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(analysis);
        return pageInfo;
    }
}
