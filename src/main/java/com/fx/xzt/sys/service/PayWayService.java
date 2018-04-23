package com.fx.xzt.sys.service;


import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.PayWay;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.util.CommonResponse;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: PayWayService.java
 * @Description: 支付方式
 */
public interface PayWayService extends IService<PayWay>{
    /**
     * 获取每种设备所支持的支付方式
     * @return
     */
    List<Map<String, Object>> getDevices();

    /**
     * 修改设备所支持的支付方式
     * @return
     */
    int editPayWays(Short payDevice, String[] payWays);


}
