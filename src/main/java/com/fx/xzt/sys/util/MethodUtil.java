package com.fx.xzt.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianliya
 * @Description: 提高代码的复用性
 * @date 13:08 2017/10/21
 */
public class MethodUtil {
    /**
     * @param startTime
     * @param endTime
     * @return
     * @Author:  tianliya
     * @Description: 将前端返回的时间  转换成yyyy-MM-ss hh:mm:ss
     * @Date:13:16 2017/10/21
    */
    public static Map<String, String> formatTime(String startTime,String endTime){
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ss hh:mm:ss");
        if (startTime != "" && !"NaN".equals(startTime) && startTime != null){
            Long start = new Long(startTime);
            Date ds = new Date(start);
            String st = simpleDateFormat.format(ds);
            map.put("startTime",st );
        }
        if (endTime != "" && !"NaN".equals(endTime) && endTime != null){
            Long end = new Long(endTime);
            Date de = new Date(end);
            String et = simpleDateFormat.format(de);
            map.put("endTime", et);
        }
        return map;
    }
}
