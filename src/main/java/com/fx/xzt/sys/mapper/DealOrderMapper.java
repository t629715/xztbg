package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.DealOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: DealOrderMapper.java
 * @Description:金权交易订单
 * @date 2017-09-25 13:38
 */
@Repository
public interface DealOrderMapper extends BaseMapper<DealOrder> {

    /**
     *  金权交易查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByDealOrder (Map<String,Object> map);

    /**
     *  金权交易统计
     * @return
     */
    Map<String, Object> selectByDealOrderCount(Map<String,Object> map);
    
    /**
     * 
    * @Title: selectByDealOrderCount2 
    * @Description: 金权交易金额--持仓中保证金统计
    * @param map
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByDealOrderCount2(Map<String,Object> map);

    /**
     * 获取买涨手数、买入价
     * @return
     */
    Map<String, Object> selectHandNumberAndOpenPositionForUp();

    /**
     * 获取买跌得手数、买入价
     * @return
     */
    Map<String, Object> selectHandNumberAndOpenPositionForDown();

    /**
     * 金权交易分析 交易用户、交易金额-tianliya
     * @return
     * @Date:11:10 2017/10/19
    */
    Map<String, Object> dealOrderAnalysis(Map<String,Object> map);
 }
