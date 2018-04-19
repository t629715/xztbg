package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.mapper.FinanceRegulargoldOrderMapper;
import com.fx.xzt.sys.service.FinanceRegulargoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author:tianliya
 * @CreateTime:2018-04-18 14:35
 * @Description:定期金业务相关实现
 **/
@Service
public class FinanceRegulargoldOrderServiceImpl implements FinanceRegulargoldOrderService {
    @Resource
    private FinanceRegulargoldOrderMapper financeRegulargoldOrderMapper;
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/18 14:41
     * @Description：查询定期理财交易
     * @param userName 用户名
     * @param orderNo 订单号
     * @param startTime 买入时间开始
     * @param endTime 买入时间结束
     * @param regStartTime 注册时间开始
     * @param regEndTime 注册时间结束
     * @param redeemStartTime 赎回时间开始
     * @param redeemEndTime 赎回时间结束
     * @param agentName 代理商 名字
     * @param brokerName 经纪人名字
     * @param buyType 购买类型 1是现金买入，2是实金转入
     * @param status 状态 1:持有中2:已赎回
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonResponse getAllByConditions(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                      String redeemStartTime, String redeemEndTime, String buyType, String agentName, String brokerName, Integer status, String isView,Integer pageNum, Integer pageSize) throws GlobalException {
        CommonResponse commonResponse = new CommonResponse();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userName",userName);
        conditions.put("orderNo",orderNo);
        conditions.put("startTime",startTime);
        conditions.put("endTime",endTime);
        conditions.put("regStartTime",regStartTime);
        conditions.put("regEndTime",regEndTime);
        conditions.put("redeemStartTime",redeemStartTime);
        conditions.put("redeemEndTime",redeemEndTime);
        conditions.put("buyType",buyType);
        conditions.put("brokerName",brokerName);
        conditions.put("agentName",agentName);
        conditions.put("status",status);
        conditions.put("isView",isView);
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<Map> financeRegulargoldOrders = financeRegulargoldOrderMapper.selectAllByConditions(conditions);
            PageInfo<Map> pageInfo = new PageInfo<>(financeRegulargoldOrders);
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(pageInfo);
            commonResponse.setMsg("获取信息成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException("获取定期金订单","获取定期金订单异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/18 17:58
     * @Description：导出定期理财交易
     * @param userName 用户名
     * @param orderNo 订单号
     * @param startTime 买入时间开始
     * @param endTime 买入时间结束
     * @param regStartTime 注册时间开始
     * @param regEndTime 注册时间结束
     * @param redeemStartTime 赎回时间开始
     * @param redeemEndTime 赎回时间结束
     * @param buyType 购买类型 1是现金买入，2是实金转入
     * @param agentName 代理商 名字
     * @param brokerName 经纪人名字
     * @param status 状态 1:持有中2:已赎回
     * @param isView
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     */
    @Override
    public List<Map> exportAllByConditions(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                             String redeemStartTime, String redeemEndTime, String buyType, String agentName, String brokerName, Integer status, String isView,Integer pageNum, Integer pageSize) throws GlobalException {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userName",userName);
        conditions.put("orderNo",orderNo);
        conditions.put("startTime",startTime);
        conditions.put("endTime",endTime);
        conditions.put("regStartTime",regStartTime);
        conditions.put("regEndTime",regEndTime);
        conditions.put("redeemStartTime",redeemStartTime);
        conditions.put("redeemEndTime",redeemEndTime);
        conditions.put("buyType",buyType);
        conditions.put("brokerName",brokerName);
        conditions.put("agentName",agentName);
        conditions.put("status",status);
        List<Map> financeRegulargoldOrders = null;
        try{
            financeRegulargoldOrders = financeRegulargoldOrderMapper.selectAllByConditions(conditions);
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException("导出定期金订单","导出定期金订单异常");
        }
        return financeRegulargoldOrders;
    }
}
