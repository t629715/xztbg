package com.fx.xzt.util;

import java.util.Calendar;

/**
 * @Author: Administrator.
 * @Description:
 * @Date:Created in 2017/8/9.
 * @Modified By:
 */
public class IdUtil {
//    public static long getIDForBigInt(){
//        Long code1 = (long) ((Math.random() * 9 + 1) * 1000000000000000000L);
//        return(code1);
//    }
    
    /**
     * 格式：yyyymmddhhMMssSSS+2位随机
     * @return 
     */
    public static long generateyyyymmddhhMMssSSSAnd2Random() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) * 1000000000000000l 
                + (c.get(Calendar.MONTH) + 1) * 10000000000000l
                + c.get(Calendar.DAY_OF_MONTH) * 100000000000l
                + c.get(Calendar.HOUR_OF_DAY) * 1000000000l
                + c.get(Calendar.MINUTE) * 10000000l
                + c.get(Calendar.SECOND) * 100000
                + c.get(Calendar.MILLISECOND) * 100 
                + (int)(Math.random() * 100);
    }
    
    /**
     * 格式：yymmddhhMMssSSS+4位随机
     * @return
     */
    public static long generateyymmddhhMMssSSSAnd4Random() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) % 100 * 100000000000000000l 
                + (c.get(Calendar.MONTH) + 1) * 1000000000000000l
                + c.get(Calendar.DAY_OF_MONTH) * 10000000000000l
                + c.get(Calendar.HOUR_OF_DAY) * 100000000000l
                + c.get(Calendar.MINUTE) * 1000000000l
                + c.get(Calendar.SECOND) * 10000000l
                + c.get(Calendar.MILLISECOND) * 10000 
                + (int)(Math.random() * 10000);
    }
}
