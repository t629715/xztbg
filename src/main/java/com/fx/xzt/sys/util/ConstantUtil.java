package com.fx.xzt.sys.util;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author htt
 * @ClassName: ConstantUtil.java
 * @Description: 常量
 * @date 2017-09-20 16:14
 */
public class ConstantUtil {

    /**
     * 客户信息-状态
     */
    public static enum userStatus {
        JY("禁用","0"),
        ZC("正常","1");
        private String name;
        private String index;
        private userStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            userStatus[] ds = userStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (userStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
}
