package com.fx.xzt.sys.service;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.util.CommonResponse;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author:tianliya
 * @CreateTime:2018-04-18 14:35
 * @Description:定期金业务相关接口
 **/
public interface FinanceRegulargoldOrderService {
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
    CommonResponse getAllByConditions(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                      String redeemStartTime, String redeemEndTime,String buyType, String agentName, String brokerName, Integer status, String isView,Integer pageNum,  Integer pageSize) throws GlobalException;
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/18 14:41
     * @Description：导出定期理财交易
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
    List<Map> exportAllByConditions(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                    String redeemStartTime, String redeemEndTime, String buyType, String agentName, String brokerName, Integer status, String isView, Integer pageNum, Integer pageSize) throws GlobalException;
}
