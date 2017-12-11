package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.IncomeSharingConf;
import com.fx.xzt.sys.entity.PayWay;
import com.fx.xzt.sys.mapper.IncomeSharingConfMapper;
import com.fx.xzt.sys.mapper.PayWayMapper;
import com.fx.xzt.sys.service.IncomeSharingConfService;
import com.fx.xzt.sys.service.PayWayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayWayServiceImpl extends BaseService<PayWay> implements PayWayService {
    @Resource
    private PayWayMapper payWayMapper;

    /**
     * 获取设备所支持的支付方式
     * @return
     */
    @Override
    public List<Map<String, Object>> getDevices() {
        List<Map<String,Object>> payWays = payWayMapper.getDevices();
        for (Map map:payWays){
            List<Map<String,Object>> payWay = payWayMapper.getPayWaysForDevice(Short.valueOf(map.get("payDevice").toString()));
            map.put("payWays",payWay);
        }
        return payWays;
    }

    @Override
    @Transactional
    public int editPayWays(Short payDevice, String[] payWays) {
        Map map = new HashMap();
        map.put("payDevice",payDevice);
        payWayMapper.editPayWay(map);
        int msg = 0;
        for (String s:payWays){
            map.put("payWay",Short.valueOf(s));
            msg = payWayMapper.editPayWays(map);
        }
        return msg;
    }
}
