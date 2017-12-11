package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.PayWay;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 付款方式持有层
 */
@Repository
public interface PayWayMapper extends BaseMapper<PayWay>{
    /**
     * 获取各种系统支持的付款方式
     * @return
     */
    List<Map<String, Object>> getDevices();
    /**
     * 获取每种设备支持的支付方式
     */

    List<Map<String, Object>> getPayWaysForDevice(Short deviceId);


    /**
     *修改哪些支付方式在哪些设备上可以使用
     */
    int editPayWays(Map<String,Object> map);

    /**
     * 设备的支付方式设为无效
     * @param map
     * @return
     */
    int editPayWay(Map<String,Object> map);
}