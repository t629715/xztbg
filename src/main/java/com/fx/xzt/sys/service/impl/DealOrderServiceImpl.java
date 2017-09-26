package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.DealOrder;
import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.service.DealOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PageInfo<Map<String, Object>> selectByDealOrderAll(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("orderState", orderState);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = dealOrderMapper.selectByDealOrderAll(map);
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
    public List<Map<String, Object>> excelDealOrderMessage(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer orderState) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("orderState", orderState);
        List<Map<String, Object>> list = dealOrderMapper.selectByDealOrderAll(map);
        return list;
    }

    /**
     *  金权交易金额统计
     * @return
     */
    public Map<String, Object> selectByDealOrderCount() {
        return dealOrderMapper.selectByDealOrderCount();
    }

}
