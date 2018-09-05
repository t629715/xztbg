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
        java.text.DecimalFormat   df1   =new   java.text.DecimalFormat("#0.000");
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
//               金生金kezhong
               if (m.get("buyAmount") != null){
                   perAmount = perAmount+Double.parseDouble((m.get("buyAmount")==null?0:m.get("buyAmount")).toString());

               }
//               金生金克重
               if (m.get("gram") != null){
                   m.put("gram",df1.format(Double.parseDouble((m.get("gram")==null?0:m.get("gram")).toString())));
               }
//               金权交易金额
               if (m.get("goldRightAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString());
                   m.put("goldRightAmount",df.format(Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString())));
               }
//               黄金买入金额
               if (m.get("buyRmbAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("buyRmbAmount")==null?0:m.get("buyRmbAmount")).toString());
//

               }
//               黄金买入克重
               if (m.get("buyGoldAmount")!= null){
                   m.put("buyGoldAmount",df1.format(Double.parseDouble((m.get("buyGoldAmount")==null?0:m.get("buyGoldAmount")).toString())));
               }
//               黄金卖出金额
               if (m.get("saleRmbAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("saleRmbAmount")==null?0:m.get("saleRmbAmount")).toString());

               }
                // 交割金额
               if (m.get("goldDeliveryAmount")!= null){
                   perAmount = perAmount+Double.parseDouble((m.get("goldDeliveryAmount")==null?0:m.get("goldDeliveryAmount")).toString());

               }
               //                   黄金卖出克重
               if (m.get("saleGoldAmount")!= null ){
                   m.put("saleGoldAmount",df1.format(Double.parseDouble((m.get("saleGoldAmount")==null?0:m.get("saleGoldAmount")).toString())));
               }
//               黄金用户
               if (m.get("goldUserAmount") != null){
                   perCount = perCount+Integer.parseInt((m.get("goldUserAmount")==null?0:m.get("goldUserAmount")).toString());
               }
//               金权交易用户
               if (m.get("goldRightUserAmount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("goldRightUserAmount")==null?0:m.get("goldRightUserAmount")).toString());
               }

                //金权交割用户
               if (m.get("goldDeliveryUserAmount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("goldDeliveryUserAmount")==null?0:m.get("goldDeliveryUserAmount")).toString());
               }
//               金生金用户
               if (m.get("buyUserAmount")!= null){
                   perCount = perCount+Integer.parseInt((m.get("buyUserAmount")==null?0:m.get("buyUserAmount")).toString());
               }
//
//               总的交易用户
               m.put("perCount",perCount);
//               总的交易金额
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
        java.text.DecimalFormat   df1   =new   java.text.DecimalFormat("#0.000");
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
            amountTotal = Double.parseDouble((map1.get("buyRmbAmount")==null?0.0:map1.get("buyRmbAmount")).toString())+
                    Double.parseDouble((map1.get("saleRmbAmount")==null?0.0:map1.get("saleRmbAmount")).toString())+
                    Double.parseDouble((map1.get("goldRightAmount")==null?0.0:map1.get("goldRightAmount")).toString())+
                    Double.parseDouble((map1.get("goldDeliveryAmount")==null?0.0:map1.get("goldDeliveryAmount")).toString())+
                    Double.parseDouble((map1.get("buyAmount")==null?0.0:map1.get("buyAmount")).toString())
                    ;
            int userTotal = 0;
            userTotal = Integer.valueOf((map1.get("buyGoldUserAmount")==null?0:map1.get("buyGoldUserAmount")).toString())+
                    Integer.valueOf((map1.get("saleGoldUserAmount")==null?0:map1.get("saleGoldUserAmount")).toString())+
                    Integer.valueOf((map1.get("goldRightUserAmount")==null?0:map1.get("goldRightUserAmount")).toString())+

                    Integer.valueOf((map1.get("goldDeliveryUserAmount")==null?0:map1.get("goldDeliveryUserAmount")).toString())+
                    Integer.valueOf((map1.get("financeUserAmount")==null?0:map1.get("financeUserAmount")).toString())
            ;
//           黄金-用户数
            map1.put("goldUserAmount",
                    Integer.parseInt((map1.get("buyGoldUserAmount")==null?0:map1.get("buyGoldUserAmount")).toString())+Integer.parseInt((map1.get("saleGoldUserAmount")==null?0:map1.get("saleGoldUserAmount")).toString())
            );
//            买入黄金-克重
            map1.put("buyGoldAmount",df1.format(Double.parseDouble((map1.get("buyGoldAmount")==null?0:map1.get("buyGoldAmount")).toString())));
//            卖出黄金-克重
            map1.put("saleGoldAmount",df1.format(Double.parseDouble((map1.get("saleGoldAmount")==null?0:map1.get("saleGoldAmount")).toString())));
//            金权交易-金额
            map1.put("goldRightAmount",df.format(Double.parseDouble((map1.get("goldRightAmount")==null?0:map1.get("goldRightAmount")).toString())));
//            金权交易-用户数
            map1.put("goldRightUserAmount",map1.get("goldRightUserAmount")==null?0:map1.get("goldRightUserAmount"));

            //金权交割-金额
            map1.put("goldDeliveryAmount",df.format(Double.parseDouble((map1.get("goldDeliveryAmount")==null?0:map1.get("goldDeliveryAmount")).toString())));
//            金权交割-用户数
            map1.put("goldDeliveryUserAmount",map1.get("goldDeliveryUserAmount")==null?0:map1.get("goldDeliveryUserAmount"));
//            金生金-克重
            map1.put("gram",df1.format(Double.parseDouble((map1.get("gram")==null?0:map1.get("gram")).toString())));
//            金生金-用户数
            map1.put("financeUserAmount",map1.get("financeUserAmount")==null?0:map1.get("financeUserAmount"));

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
        java.text.DecimalFormat   df1   =new   java.text.DecimalFormat("#0.000");
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
                //               金生金kezhong
                if (m.get("buyAmount") != null){
                    perAmount = perAmount+Double.parseDouble((m.get("buyAmount")==null?0:m.get("buyAmount")).toString());

                }
//               金生金克重
                if (m.get("gram") != null){
                    m.put("gram",df1.format(Double.parseDouble((m.get("gram")==null?0:m.get("gram")).toString())));
                }
//               金权交易金额
                if (m.get("goldRightAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString());
                    m.put("goldRightAmount",df.format(Double.parseDouble((m.get("goldRightAmount")==null?0:m.get("goldRightAmount")).toString())));
                }
 //               金权交割金额
                if (m.get("goldDeliveryAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("goldDeliveryAmount")==null?0:m.get("goldDeliveryAmount")).toString());
                    m.put("goldDeliveryAmount",df.format(Double.parseDouble((m.get("goldDeliveryAmount")==null?0:m.get("goldDeliveryAmount")).toString())));
                }
//               黄金买入金额
                if (m.get("buyRmbAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("buyRmbAmount")==null?0:m.get("buyRmbAmount")).toString());
//

                }
//               黄金买入克重
                if (m.get("buyGoldAmount")!= null){
                    m.put("buyGoldAmount",df1.format(Double.parseDouble((m.get("buyGoldAmount")==null?0:m.get("buyGoldAmount")).toString())));
                }
//               黄金卖出金额
                if (m.get("saleRmbAmount")!= null){
                    perAmount = perAmount+Double.parseDouble((m.get("saleRmbAmount")==null?0:m.get("saleRmbAmount")).toString());

                }
                //                   黄金卖出克重
                if (m.get("saleGoldAmount")!= null ){
                    m.put("saleGoldAmount",df1.format(Double.parseDouble((m.get("saleGoldAmount")==null?0:m.get("saleGoldAmount")).toString())));
                }
//               黄金用户
                if (m.get("goldUserAmount") != null){
                    perCount = perCount+Integer.parseInt((m.get("goldUserAmount")==null?0:m.get("goldUserAmount")).toString());
                }
//               金权交易用户
                if (m.get("goldRightUserAmount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("goldRightUserAmount")==null?0:m.get("goldRightUserAmount")).toString());
                }
 //               金权交割用户
                if (m.get("goldDeliveryUserAmount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("goldDeliveryUserAmount")==null?0:m.get("goldDeliveryUserAmount")).toString());
                }
//               金生金用户
                if (m.get("buyUserAmount")!= null){
                    perCount = perCount+Integer.parseInt((m.get("buyUserAmount")==null?0:m.get("buyUserAmount")).toString());
                }
//
//               总的交易用户
                m.put("perCount",perCount);
//               总的交易金额
                m.put("perAmount",df.format(perAmount));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
