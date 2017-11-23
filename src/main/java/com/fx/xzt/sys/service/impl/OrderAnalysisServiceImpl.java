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
import java.math.RoundingMode;
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
    @Override
    public PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime,  String agentName,
                                                       Integer upOrDown,Integer orderState, Integer profitLoss,Long agentId,
                                                       Integer pageNum, Integer pageSize) throws ParseException {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
        //df.setRoundingMode(RoundingMode.FLOOR);
        Map map = new HashMap();
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        if (startTime != null && startTime != ""){
            startTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(startTime)));
        }
        if (endTime != null && endTime != ""){
            endTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(endTime)));
        }
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("start",(pageNum-1)*pageSize);
        map.put("size",pageSize);
        map.put("agentId",agentId);
        List<Map<String, Object>> list = new ArrayList();
        try{
           list  = analysisOrderMapper.getAnalysis(map);
           for (Map m:list){
               int perCount = 0;
               Double perAmount = 0d;
               m.put("date",DateUtils.formatDateByMidLine1((Date)m.get("date")));
               if (m.get("financeAmount") != null){
                   perAmount = perAmount+Double.parseDouble((m.get("financeAmount")==null?0:m.get("financeAmount")).toString());
                   m.put("financeAmount",df.format(Double.parseDouble((m.get("financeAmount")==null?0:m.get("financeAmount")).toString())));
               }
               if (m.get("goldRightAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString());
                   m.put("goldRightAmount",df.format(Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString())));
               }
               if (m.get("realGoldAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("realGoldAmount")==null?0:m.get("realGoldAmount")).toString());
                   m.put("realGoldAmount",df.format(Double.parseDouble((m.get("realGoldAmount")==null?0:m.get("realGoldAmount")).toString())));
               }
               if (m.get("goldUpAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("goldUpAmount")==null?0:m.get("goldUpAmount")).toString());
                   m.put("goldUpAmount",df.format(Double.parseDouble((m.get("goldUpAmount")==null?0:m.get("goldUpAmount")).toString())));
               }
               if (m.get("financeCount") != null){
                   perCount = perCount+Integer.parseInt((m.get("financeCount")==null?0:m.get("financeCount")).toString());
               }
               if (m.get("goldRightCount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("goldRightCount")==null?0:m.get("goldRightCount")).toString());
               }
               if (m.get("realGoldCount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("realGoldCount")==null?0:m.get("realGoldCount")).toString());
               }
               if (m.get("goldUpCount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("goldUpCount")==null?0:m.get("goldUpCount")).toString());
               }
               m.put("perCount",perCount);
               m.put("perAmount",df.format(perAmount));
           }
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
                                                        Integer upOrDown,Integer orderState, Integer profitLoss, Long agentId,
                                                        Integer pageNum, Integer pageSize) {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
        //df.setRoundingMode(RoundingMode.FLOOR);
        Map map = new HashMap();
        if (startTime != null && startTime != ""){
            startTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(startTime)));
        }
        if (endTime != null && endTime != ""){
            endTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(endTime)));
        }
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        map.put("agentId",agentId);
        map.put("start",(pageNum-1)*pageSize);
        map.put("size",pageSize);
        Map map1 = new HashMap();
        try{
            map1 = analysisOrderMapper.getAnalysisCount(map);
            double amountTotal = 0;
            amountTotal = Double.parseDouble((map1.get("realGoldAmountTotal")==null?0.0:map1.get("realGoldAmountTotal")).toString())+
                    Double.parseDouble((map1.get("goldRightAmountTotal")==null?0.0:map1.get("goldRightAmountTotal")).toString())+
                    Double.parseDouble((map1.get("financeAmountTotal")==null?0.0:map1.get("financeAmountTotal")).toString())+
                    Double.parseDouble((map1.get("goldUpAmountTotal")==null?0.0:map1.get("goldUpAmountTotal")).toString())
                    ;
            int userTotal = 0;
            userTotal = Integer.valueOf((map1.get("realGoldCountTotal")==null?0:map1.get("realGoldCountTotal")).toString())+
                    Integer.valueOf((map1.get("goldRightCountTotal")==null?0:map1.get("goldRightCountTotal")).toString())+
                    Integer.valueOf((map1.get("financeCountTotal")==null?0:map1.get("financeCountTotal")).toString())+
                    Integer.valueOf((map1.get("goldUpCountTotal")==null?0:map1.get("goldUpCountTotal")).toString())
            ;

            map1.put("realGoldAmountTotal",df.format(Double.parseDouble((map1.get("realGoldAmountTotal")==null?0:map1.get("realGoldAmountTotal")).toString())));
            map1.put("goldRightAmountTotal",df.format(Double.parseDouble((map1.get("goldRightAmountTotal")==null?0:map1.get("goldRightAmountTotal")).toString())));
            map1.put("financeAmountTotal",df.format(Double.parseDouble((map1.get("financeAmountTotal")==null?0:map1.get("financeAmountTotal")).toString())));
            map1.put("goldUpAmountTotal",df.format(Double.parseDouble((map1.get("goldUpAmountTotal")==null?0:map1.get("goldUpAmountTotal")).toString())));
            map1.put("amountTotal",df.format(amountTotal));
            map1.put("userTotal",userTotal);


        }catch (Exception e){
            e.printStackTrace();
        }

        return map1;
    }

    /**
     * 导出交易分析
     * @param startTime
     * @param endTime
     * @param agentName
     * @param upOrDown
     * @param orderState
     * @param profitLoss
     * @param agentId
     * @return
     */
    @Override
    public List<Map<String, Object>> exportAnalysis(String startTime, String endTime,  String agentName,
                                                    Integer upOrDown,Integer orderState, Integer profitLoss,Long agentId
                                                       ) {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
        //df.setRoundingMode(RoundingMode.FLOOR);
        Map map = new HashMap();
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        if (startTime != null && startTime != ""){
            startTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(startTime)));
        }
        if (endTime != null && endTime != ""){
            endTime = DateUtils.formatDateByMidLine(DateUtil.convertTimeMillisToDate(Long.valueOf(endTime)));
        }
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("agentId",agentId);
        List<Map<String, Object>> list = new ArrayList();
        try{
            list  = analysisOrderMapper.exportAnalysis(map);
            for (Map m:list){
                int perCount = 0;
                Double perAmount = 0d;
                m.put("date",DateUtils.formatDateByMidLine1((Date)m.get("date")));
                if (m.get("financeAmount") != null){
                    perAmount = perAmount+Double.parseDouble((m.get("financeAmount")==null?0:m.get("financeAmount")).toString());
                    m.put("financeAmount",df.format(Double.parseDouble((m.get("financeAmount")==null?0:m.get("financeAmount")).toString())));
                }
                if (m.get("goldRightAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString());
                    m.put("goldRightAmount",df.format(Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString())));
                }
                if (m.get("realGoldAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("realGoldAmount")==null?0:m.get("realGoldAmount")).toString());
                    m.put("realGoldAmount",df.format(Double.parseDouble((m.get("realGoldAmount")==null?0:m.get("realGoldAmount")).toString())));
                }
                if (m.get("goldUpAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("goldUpAmount")==null?0:m.get("goldUpAmount")).toString());
                    m.put("goldUpAmount",df.format(Double.parseDouble((m.get("goldUpAmount")==null?0:m.get("goldUpAmount")).toString())));
                }
                if (m.get("financeCount") != null){
                    perCount = perCount+Integer.parseInt((m.get("financeCount")==null?0:m.get("financeCount")).toString());
                }
                if (m.get("goldRightCount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("goldRightCount")==null?0:m.get("goldRightCount")).toString());
                }
                if (m.get("realGoldCount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("realGoldCount")==null?0:m.get("realGoldCount")).toString());
                }
                if (m.get("goldUpCount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("goldUpCount")==null?0:m.get("goldUpCount")).toString());
                }
                m.put("perCount",perCount);
                m.put("perAmount",df.format(perAmount));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
