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
    
    /**
     * 黄金收益记录统计类型
     */
    public final static Integer GOLD_INCOME_RECORD_TYPE_ZT = 1;  //黄金收益记录统计类型-昨天
    public final static Integer GOLD_INCOME_RECORD_TYPE_JQT = 2; //黄金收益记录统计类型-近7天
    public final static Integer GOLD_INCOME_RECORD_TYPE_BY = 3;  //黄金收益记录统计类型-本月
    public final static Integer GOLD_INCOME_RECORD_TYPE_SGY = 4;  //黄金收益记录统计类型-上个月
    
    /**
     * 黄金赎回配置--是否启用
     */
    public final static Integer GOLD_REDEEM_CONF_ISENABLE_QY = 1; //启用
    public final static Integer GOLD_REDEEM_CONF_ISENABLE_JY = 0; //禁用
    
    /**
     * 是否使用卡券
     */
    public final static Integer ISUSECARD_SY = 1;
    public final static Integer ISUSECARD_BSY = 0;

    /**
     * 用户账户记录行为
     */
    public final static String USER_ACCOUNT_RECORD_ACTION_CZ = "10";
    public final static String USER_ACCOUNT_RECORD_ACTION_TX = "20";
    public final static String USER_ACCOUNT_RECORD_ACTION_LCJL = "30";
    public final static String USER_ACCOUNT_RECORD_ACTION_SJDH = "40";
    public final static String USER_ACCOUNT_RECORD_ACTION_JYSY = "50";
    public final static String USER_ACCOUNT_RECORD_ACTION_DJ = "60";
    public final static String USER_ACCOUNT_RECORD_ACTION_HQLCSF = "70";
    public final static String USER_ACCOUNT_RECORD_ACTION_LC = "80";
    public final static String USER_ACCOUNT_RECORD_ACTION_HJSH = "90";
    
    /**
     * 用户账户记录状态
     */
    public final static Integer USER_ACCOUNT_RECORD_STATUS_WSH = 0;
    public final static Integer USER_ACCOUNT_RECORD_STATUS_YSH = 1;
    
    /**
     * 用户账户记录方向
     */
    public final static String USER_ACCOUNT_RECORD_SIDE_J = "I";
    public final static String USER_ACCOUNT_RECORD_SIDE_C = "O";
    
    /**
     * 出入金统计分析周期
     */
    public final static String IN_OUT_GOLD_NPER_JT = "1";   //出入金统计分析周期-今天
    public final static String IN_OUT_GOLD_NPER_ZT = "2";   //出入金统计分析周期-昨天
    public final static String IN_OUT_GOLD_NPER_JQT = "3";  //出入金统计分析周期-近七天
    public final static String IN_OUT_GOLD_NPER_BY = "4";   //出入金统计分析周期-本月
    

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
        YWC("已审核","1");
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
    
    /**
     * 
    * @ClassName: goldWithdrawtype 
    * @Description: 黄金提取类型
    * @author htt
    * @date 2017-10-17 下午2:40:15 
    *
     */
    public static enum goldWithdrawType {
        SWTJ("实物提金","1");
        private String name;
        private String index;
        private goldWithdrawType(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	goldWithdrawType[] ds = goldWithdrawType.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (goldWithdrawType d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: goldWithdrawStatus 
    * @Description: 黄金提取状态
    * @author htt
    * @date 2017-10-17 下午2:42:15 
    *
     */
    public static enum goldWithdrawStatus {
        WFH("未发货","1"),
        YFH("已发货","2");
        private String name;
        private String index;
        private goldWithdrawStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	goldWithdrawStatus[] ds = goldWithdrawStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (goldWithdrawStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    

}
