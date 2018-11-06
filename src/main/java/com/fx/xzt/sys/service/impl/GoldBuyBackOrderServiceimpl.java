package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.GoldBuyBackOrder;
import com.fx.xzt.sys.mapper.GoldBuyBackOrderMapper;
import com.fx.xzt.sys.service.GoldBuyBackOrderService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/10/30/030
 */
@Service
public class GoldBuyBackOrderServiceimpl extends BaseService<GoldBuyBackOrder> implements GoldBuyBackOrderService {
    @Resource
    GoldBuyBackOrderMapper goldBuyBackOrderMapper;
    @Override
    public PageInfo<Map<String, Object>> selectGoldBuyBackOrder( String userName, String orderNo, String agentName, String brokerName,
             String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
             String subEndTime, String expStartTime, String expEndTime, Integer pageNum, Integer pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", userName);
            map.put("orderNo", orderNo);
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

            map.put("gtmStartTime", gtmStartTime);
            map.put("gtmEndTime", gtmEndTime);
            map.put("comStartTime",comStartTime);
            map.put("comEndTime",comEndTime);
            map.put("subStartTime", subStartTime);
            map.put("subEndTime", subEndTime);
            map.put("expStartTime",expStartTime);
            map.put("expEndTime", expEndTime);
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String, Object>> list = goldBuyBackOrderMapper.selectYgGold(map);
            this.handle(list);
            PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
            return pagehelper;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("回购记录查询", "回购查询出现异常");
        }
    }

    /**
     * 导出
     * @param
     * @return
     */
    @Override
   public List<Map<String, Object>> excelGoldBuyBackOrder(String userName, String orderNo, String agentName, String brokerName,
                                                            String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
                                                            String subEndTime, String expStartTime, String expEndTime){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", userName);
            map.put("orderNo", orderNo);
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

            map.put("gtmStartTime", gtmStartTime);
            map.put("gtmEndTime", gtmEndTime);
            map.put("comStartTime",comStartTime);
            map.put("comEndTime",comEndTime);
            map.put("subStartTime", subStartTime);
            map.put("subEndTime", subEndTime);
            map.put("expStartTime",expStartTime);
            map.put("expEndTime", expEndTime);
            List<Map<String, Object>> list = goldBuyBackOrderMapper.selectYgGold(map);
            this.handle(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("回购记录查询", "回购查询出现异常");
        }


    }

    /**
     * 收货确认无误
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int updateState(String id) {
        GoldBuyBackOrder goldBuyBackOrder=new GoldBuyBackOrder();
        goldBuyBackOrder.setId(Long.parseLong(id));
        goldBuyBackOrder.setCompleteTime(new Date());
        goldBuyBackOrder.setStatus(new Short("4"));
        return goldBuyBackOrderMapper.updateState(goldBuyBackOrder);
    }

    /**
     * 取消
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int cancelState(String id) {
        GoldBuyBackOrder goldBuyBackOrder=new GoldBuyBackOrder();
        goldBuyBackOrder.setId(Long.parseLong(id));
        goldBuyBackOrder.setExpireTime(new Date());
        goldBuyBackOrder.setStatus(new Short("3"));
        return goldBuyBackOrderMapper.updateState(goldBuyBackOrder);
    }
    public void handle(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Object statusObj = map.get("status");
                if (statusObj != null && statusObj != "") {
                    map.put("status", ConstantUtil.status2.toMap().get(statusObj.toString()));
                }
            }
        }
    }

}
