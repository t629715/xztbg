package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.mapper.RealGoldOrderMapper;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime,  String agentName, Integer pageNum, Integer pageSize) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("agentName",agentName);
        List<Map<String, Object>> goldUp=financeOrderMapper.goldUpAnalysis(map);
        List<Map<String, Object>> random=financeOrderMapper.randomAnalysis(map);
        List<Map<String, Object>> realGold = realGoldOrderMapper.realGoldOrderAnalysis(map);
        List<Map<String, Object>> goldRight = dealOrderMapper.dealOrderAnalysis(map);
        List<Map<String, Object>> analysis = new ArrayList();
        for (Map m: goldUp){
            Map<String, Object> ma = new HashMap<String, Object>();

            for (Map m1:random){
                if (m.get("goldUpTime").hashCode()==m1.get("randomTime").hashCode()){
                    ma.put("randomAmount",m1.get("randomAmount"));
                    ma.put("randomCmount",m1.get("randomCmount"));
                }
            }
            for (Map m2:realGold){
                if (m.get("goldUpTime").hashCode()==m2.get("realGoldTime").hashCode()){
                    ma.put("realGoldAmount",m2.get("realGoldAmount"));
                    ma.put("realGoldCmount",m2.get("realGoldCmount"));
                }
            }
            for (Map m3:goldRight){
                if (m.get("goldUpTime").hashCode()==m3.get("goldRightTime").hashCode()){
                    ma.put("goldRightCount",m3.get("goldRightCount"));
                    ma.put("goldRightAmount",m3.get("goldRightAmount"));
                }
            }
            ma.put("time",m.get("goldUptime"));
            ma.put("goldUpCount",m.get("goldUpCount"));
            ma.put("goldUpAmount",m.get("goldUpAmount"));
            analysis.add(ma);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(goldUp);
        return pageInfo;
    }
}
