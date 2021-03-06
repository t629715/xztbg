package com.fx.xzt.sys.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fx.xzt.sys.util.ConstantUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.DealOrder;
import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.mapper.GoldRightDealConfMapper;
import com.fx.xzt.sys.service.DealOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: DealOrderServiceImpl.java
 * @Description:
 * @date 2017-09-25 13:46
 */
@Service
public class DealOrderServiceImpl extends BaseService<DealOrder> implements DealOrderService  {

    @Resource
    DealOrderMapper dealOrderMapper;
    @Resource
    GoldRightDealConfMapper goldRightDealConfMapper;

    /**
     * 查询金权交易
     * @param userName  用户名
     * @param orderNo   订单号
     * @param startTime  建仓开始时间
     * @param endTime    建仓结束是时间
     * @param regStartTime  注册开始时间
     * @param regEndTime  注册结束时间
     * @param agentName  运营商用户名
     * @param brokerName 经纪人用户名
     * @param orderState 订单状态
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> selectByDealOrder(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, Integer isUseCard,
    		String upOrDown, String isView, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        if(!StringUtils.isBlank(agentName))	{
            String [ ] agentNames=agentName.split(",");
            if(agentNames !=null || agentNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:agentNames) {
                    list.add(s);
                }
                map.put("agentName", list);
            }
        }
        if(!StringUtils.isBlank(brokerName)){
            String [ ] brokerNames=brokerName.split(",");
            if(brokerNames !=null || brokerNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:brokerNames) {
                    list.add(s);
                }
                map.put("brokerName", list);
            }
        }

        map.put("orderState", orderState);
        /*map.put("isUseCard", isUseCard);
        map.put("upOrDown", upOrDown);*/
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = dealOrderMapper.selectByDealOrder(map);
        handle(list);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
    }

    /**
     * 导出金权交易信息
     * @param userName   用户名
     * @param orderNo   订单号
     * @param startTime  建仓开始时间
     * @param endTime  建仓结束是时间
     * @param regStartTime  注册开始时间
     * @param regEndTime  注册结束时间
     * @param agentName  运营商用户名
     * @param brokerName  经纪人用户名
     * @param orderState 订单状态
     * @return
     */
    public List<Map<String, Object>> excelDealOrderMessage(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown, String isView) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        if(!StringUtils.isBlank(agentName))	{
            String [ ] agentNames=agentName.split(",");
            if(agentNames !=null || agentNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:agentNames) {
                    list.add(s);
                }
                map.put("agentName", list);
            }
        }
        if(!StringUtils.isBlank(brokerName)){
            String [ ] brokerNames=brokerName.split(",");
            if(brokerNames !=null || brokerNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:brokerNames) {
                    list.add(s);
                }
                map.put("brokerName", list);
            }
        }

        map.put("orderState", orderState);
       /* map.put("isUseCard", isUseCard);
        map.put("upOrDown", upOrDown);*/
        map.put("isView", isView);
        List<Map<String, Object>> list = dealOrderMapper.selectByDealOrder(map);
        handle(list);
        return list;
    }

    /**
     *  金权交易金额统计
     * @param userName   用户名
     * @param orderNo   订单号
     * @param startTime  建仓开始时间
     * @param endTime  建仓结束是时间
     * @param regStartTime  注册开始时间
     * @param regEndTime  注册结束时间
     * @param agentName  运营商用户名
     * @param brokerName  经纪人用户名
     * @param orderState 订单状态
     * @return
     */
    public Map<String, Object> selectByDealOrderCount(String userName, String orderNo, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, 
    		Integer orderState, Integer isUseCard, String upOrDown) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);

        if(!StringUtils.isBlank(agentName))	{
            String [ ] agentNames=agentName.split(",");
            if(agentNames !=null || agentNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:agentNames) {
                    list.add(s);
                }
                map.put("agentName", list);
            }
        }
        if(!StringUtils.isBlank(brokerName)){
            String [ ] brokerNames=brokerName.split(",");
            if(brokerNames !=null || brokerNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:brokerNames) {
                    list.add(s);
                }
                map.put("brokerName", list);
            }
        }
        map.put("orderState", orderState);
        map.put("isUseCard", isUseCard);
        map.put("upOrDown", upOrDown);
        return dealOrderMapper.selectByDealOrderCount(map);
    }

    /**
     * 获取对冲套利的数据信息 均价，持仓克重、净值、获利
     * @return
     * @author tianliya
     */
    @Override
    public Map<String, Object> selectHandNumBuyAmount() {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            //买涨的总手数、买入价
            Map<String, Object> mapUp = dealOrderMapper.selectHandNumberAndOpenPositionForUp();
            //买涨的总手数、买入价
            Map<String, Object> mapDown = dealOrderMapper.selectHandNumberAndOpenPositionForDown();
            //买涨的总手数
            Integer handNumUp = 0;
            if (mapUp != null){
                if (mapUp.get("handNumUp") != null){
                    handNumUp = Integer.valueOf(mapUp.get("handNumUp").toString());
                }
            }
            //买跌的总手数
            Integer handNumDown = 0;
            if (mapDown != null){
                if (mapDown.get("handNumDown") != null){
                    handNumDown = Integer.valueOf(mapDown.get("handNumDown").toString());
                }
            }
            //买涨买跌的总手数
            Integer handNum = handNumUp+handNumDown;
            //买涨均价：买入价*手数的合计/总手数
            Double avgUp = 0D;
            if (mapUp != null&&handNum != 0){
                if (mapUp.get("openPositionPriceUp") != null){
                    avgUp = (Double) mapUp.get("openPositionPriceUp")/handNumUp;
                }
            }
            BigDecimal bu   =   new   BigDecimal(avgUp);
            double   fu   =   bu.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("avgUp",fu);
            //买跌均价：买入价*手数的合计/总手数
            Double avgDown = 0D;
            if (mapDown != null&&handNum != 0){
                if (mapDown.get("openPositionPriceDown") != null){
                    avgDown = (Double) mapDown.get("openPositionPriceDown")/handNumDown;
                }
            }
            BigDecimal b   =   new   BigDecimal(avgDown);
            double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("avgDown",f1);
            //净值=买涨均价-买跌均价
            Double netValue = avgUp - avgDown;
            BigDecimal netVa  =   new   BigDecimal(netValue);
            double  netVal  =   netVa.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("netValue",netVal);
            //获利=买涨总额-买跌总额
            Double profit = avgUp*handNumUp-avgDown*handNumDown;
            BigDecimal profi   =   new   BigDecimal(profit);
            double   profit2   =   profi.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("profit",profit2);
            //买涨持仓克重
            GoldRightDealConf goldRightDealConf = new GoldRightDealConf();
            Long productIdUp = (Long) mapUp.get("productId");
            goldRightDealConf.setId(productIdUp);
            //合约  一手=多少克
//            Integer contract = goldRightDealConfMapper.selectOne(goldRightDealConf).getContract();
//        Integer gramUp = handNumUp*contract;
            Integer gramUp = handNumUp;
            map.put("gramUp",gramUp);
            //买跌持仓克重
            Long productIdDown = (Long) mapUp.get("productId");
            goldRightDealConf.setId(productIdDown);
            //Integer contractDown = goldRightDealConfMapper.selectOne(goldRightDealConf).getContract();
//        Integer gramDown = handNumDown*contractDown;
            Integer gramDown = handNumDown;
            map.put("gramDown",gramDown);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 金权交易金额--持仓中保证金统计
	 */
	public Map<String, Object> selectByDealOrderCount2(String userName,
			String orderNo, String startTime, String endTime,
			String regStartTime, String regEndTime, String agentName,
			String brokerName, Integer orderState, Integer isUseCard,
			String upOrDown) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        if(!StringUtils.isBlank(agentName))	{
            String [ ] agentNames=agentName.split(",");
            if(agentNames !=null || agentNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:agentNames) {
                    list.add(s);
                }
                map.put("agentName", list);
            }
        }
        if(!StringUtils.isBlank(brokerName)){
            String [ ] brokerNames=brokerName.split(",");
            if(brokerNames !=null || brokerNames.length!=0 ){
                List<String> list = new ArrayList();
                for(String s:brokerNames) {
                    list.add(s);
                }
                map.put("brokerName", list);
            }
        }
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("orderState", orderState);
        map.put("isUseCard", isUseCard);
        map.put("upOrDown", upOrDown);
        return dealOrderMapper.selectByDealOrderCount2(map);
	}

    public void handle(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Object statusObj = map.get("orderState");
                if (statusObj != null && statusObj != "") {
                    map.put("orderState", ConstantUtil.dealOrderStatus.toMap().get(statusObj.toString()));
                }
                Object poundageObj = map.get("poundage");
                if (poundageObj != null && poundageObj != "") {
                    Double poundage = Double.valueOf(poundageObj.toString());
                    map.put("poundage",poundage/100);
                }
            }
        }
    }

}
