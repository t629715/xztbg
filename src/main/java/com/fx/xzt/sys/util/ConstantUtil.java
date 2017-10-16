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
     * 返回状态码
     */
    public final static Integer COMMON_RESPONSE_CODE_SUCCESS = 1000;         //成功无数据
    public final static Integer COMMON_RESPONSE_CODE_SUCCESS_DATA = 1001;    //成功有数据
    public final static Integer COMMON_RESPONSE_CODE_NOAUTH = 1002;          //无操作权限
    public final static Integer COMMON_RESPONSE_CODE_EXCEPTION = 9999;

    /**
     * 理财产品类型
     */
    public final static Integer FINANCE_TYPE_LCCP = 1;    //理财产品
    public final static Integer FINANCE_TYPE_HJWZ = 2;    //黄金稳赚
    
    public final static Integer GOLD_INCOME_RECORD_TYPE_ZT = 1;  //黄金收益记录统计类型-昨天
    public final static Integer GOLD_INCOME_RECORD_TYPE_JQT = 2; //黄金收益记录统计类型-近7天
    public final static Integer GOLD_INCOME_RECORD_TYPE_BY = 3;  //黄金收益记录统计类型-本月
    public final static Integer GOLD_INCOME_RECORD_TYPE_SGY = 4;  //黄金收益记录统计类型-上个月

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

    /**
     * 金权交易-买涨买跌
     */
    public static enum dealOrderUpOrDown {
        UP("买涨","0"),
        DOWN("买跌","1");
        private String name;
        private String index;
        private dealOrderUpOrDown(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            dealOrderUpOrDown[] ds = dealOrderUpOrDown.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (dealOrderUpOrDown d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }

    /**
     * 理财交易订单状态
     */
    public static enum financeOrderStatus {
        CYZ("持有中","1"),
        YSH("已赎回","2");
        private String name;
        private String index;
        private financeOrderStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            financeOrderStatus[] ds = financeOrderStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (financeOrderStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: rechargeStatus 
    * @Description: 现金充值状态
    * @author htt
    * @date 2017-9-29 下午2:45:48 
    *
     */
    public static enum rechargeStatus {
        SB("失败","0"),
        CG("成功","1");
        private String name;
        private String index;
        private rechargeStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	rechargeStatus[] ds = rechargeStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (rechargeStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: rechargeChannel 
    * @Description: 现金充值渠道
    * @author htt
    * @date 2017-9-29 下午2:51:19 
    *
     */
    public static enum rechargeChannel {
        YL("银联","01"),
        WX("微信","02"),
        ZFB("支付宝","03");
        private String name;
        private String index;
        private rechargeChannel(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	rechargeChannel[] ds = rechargeChannel.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (rechargeChannel d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: withdrawCashStatus 
    * @Description: 现金提取状态
    * @author htt
    * @date 2017-9-29 下午4:31:50 
    *
     */
    public static enum withdrawCashStatus {
        SHZ("审核中","0"),
        YWC("已完成","1");
        private String name;
        private String index;
        private withdrawCashStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	withdrawCashStatus[] ds = withdrawCashStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (withdrawCashStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }

}
