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
    public final static String USER_ACCOUNT_RECORD_ACTION_TXJJFX = "100";
    
    /**
     * 用户账户记录状态
     */
    public final static Integer USER_ACCOUNT_RECORD_STATUS_WSH = 0;
    public final static Integer USER_ACCOUNT_RECORD_STATUS_YSH = 1;
    public final static Integer USER_ACCOUNT_RECORD_STATUS_SHSB = 2;
    
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
     * 用户统计分析、属性周期
     */
    public final static String USER_NPER_JT = "1";   //用户统计分析周期-今天
    public final static String USER_NPER_ZT = "2";   //用户统计分析周期-昨天
    public final static String USER_NPER_JQT = "3";  //用户统计分析周期-近七天
    public final static String USER_NPER_JSST = "4";   //用户统计分析周期-近三十天
    
    /**
     * 图片服务器地址
     */
    public final static String PHOTO_URL = "PHOTO_URL";   //图片服务器地址
    
    /**
     * 消息类型
     */
    public final static String USER_MESSAGE_TYPE_XT = "00";   //系统
    public final static String USER_MESSAGE_TYPE_XX = "10";   //消息

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
        WXSM("微信扫码","02"),
        ZFB("支付宝","03"),
        WX("微信","04"),
        ZFBSM("支付宝扫码","05"),
        GFZFB("官方支付宝","06"),
        GFWX("官方微信","07"),
        GFZFB1("官方支付宝(1)","08"),
        GFWX1("官方微信(1)","09"),
        YL1("银联(1)","10");
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
        YWC("审核通过","1"),
        SHBTG("审核不通过","2");
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
    * @ClassName: withdrawCashType 
    * @Description: 现金提取类型
    * @author htt
    * @date 2018-1-25 下午3:06:30 
    *
     */
    public static enum withdrawCashType {
        YHK("银行卡","1"),
        ZFB("支付宝","2");
        private String name;
        private String index;
        private withdrawCashType(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	withdrawCashType[] ds = withdrawCashType.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (withdrawCashType d : ds) {
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
        YFH("已发货","2"),
        YWC("已完成","3"),
        YQX("已取消","4");
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
    
    /**
     * 
    * @ClassName: useStatus 
    * @Description: 优惠券使用状态
    * @author htt
    * @date 2017-11-15 下午6:03:23 
    *
     */
    public static enum useStatus {
        WSY("未使用","0"),
        YSY("已使用","1");
        private String name;
        private String index;
        private useStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	useStatus[] ds = useStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (useStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: authApproveState 
    * @Description: 实名认证审核状态
    * @author htt
    * @date 2017-12-13 上午11:04:54 
    *
     */
    public static enum authApproveState {
        WSH("未审核","0"),
        SHTG("审核通过","1"),
        SHWTG("审核未通过","-1");
        private String name;
        private String index;
        private authApproveState(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	authApproveState[] ds = authApproveState.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (authApproveState d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 操作日志记录-类型
     */
    public static enum logRecordType {
        CK("查看",1),
        XZ("新增",2),
        XG("修改",3),
        LJSC("逻辑删除",4),
        WLSC("物理删除",5),
        DR("导入",6),
        DC("导出",7),
        SQ("授权",8),
        SH("审核",9),
        DL("登录",10),
        MMCZ("重置密码",11),
        CX("查询",12),
        CZ("充值",14),
        JS("计算",13);
        private String name;
        private Integer index;
        private logRecordType(String name,Integer index){
            this.name = name;
            this.index = index;
        }
        public Integer getIndex() {
        	return this.index;
        }
        public String getName() {
            return this.name;
        }
        public static Map<Integer, String> toMap() {
        	logRecordType[] ds = logRecordType.values();
            Map<Integer, String> rlt = new Hashtable<Integer, String>();
            for (logRecordType d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: logRecordModule 
    * @Description: 操作日志记录--模块
    * @author htt
    * @date 2017-11-28 下午4:04:44 
    *
     */
    public static enum logRecordModule {
        KHFX("客户分析","1"),
        ZCXX("注册信息","2"),
        ZHXX("账户信息","3"),
        FXPC("风险评测","4"),
        SMRZ("实名认证","5"),
        KHSX("客户属性","6"),
        JQJY("金权交易","7"),
        SJJY("实金交易","8"),
        LCJY("理财交易","9"),
        HJWZJY("黄金稳赚交易","10"),
        JYFX("交易分析","11"),
        JQGZSD("金权规则设定","12"),
        LCCPSD("理财产品设定","13"),
        SJMMSD("实金买卖设定","14"),
        DCTL("对冲套利","15"),
        CRJCX("出入金查询","16"),
        CRJFX("出入金分析","17"),
        HJSYJS("黄金收益结算","18"),
        LCSYJS("理财收益结算","19"),
        HJWZJS("黄金稳赚看涨结算","20"),
        HJTQ("黄金提取","21"),
        XJTQ("现金提取","22"),
        XJCZ("现金充值","23"),
        GGGL("公告管理","24"),
        GGWGL("图片管理","25"),
        HJKT("黄金课堂","26"),
        XODZX("希欧德中心","27"),
        ZXGL("咨询管理","28"),
        HDGL("活动管理","29"),
        SHGLYY("商户管理(运营商视角)","30"),
        SHGL("商户管理","31"),
        XJKH("下级客户","32"),
        ZHGL("账户管理","33"),
        JSGL("角色管理","34"),
        HJSHJL("黄金赎回记录","35"),
        HJSHJS("黄金赎回计算","36"),
        YHQ("优惠券","37"),
        JXQ("加息券","38"),
        HJLQ("黄金领取","39"),
        XSLC("新手理财","40"),
        BZHTJ("标准户统计","41"),
        TJYHMX("推荐用户明细","42"),
        DQJGL("金生金管理","43"),
        SJTZGL("金条订单","44"),
        HJJYS("黄金交易（卖）","45"),
        RGCZ("人工充值","46"),

        DQJJY("金生金交易","47"),
        SCTSZX("删除推送资讯","49"),
        TJTSZX("添加推送资讯","50"),
        CXTSZX("查询推送资讯","51"),
        HJJYB("黄金交易（买）","48"),
        XTCS("系统参数","52"),
        XTCD("系统菜单","53"),
        SCKZ("世界杯赛程控制","55"),
        CSDCX("参赛队查询","56"),
        RGHJCZ("人工黄金充值","57"),
        GJJGCX("冠军结果查询","58"),
        HDKZ("活动查询","54"),
        JTTZ("金条投资","60"),
        JTTZCPGZ("金条投资产品规则","59"),
        HGDD("回购订单","61"),
        HGGZSD("回购规则设置","62"),
        HGPZ("回购配置","63");

        private String name;
        private String index;
        private logRecordModule(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String getIndex() {
        	return this.index;
        }
        public String getName() {
            return this.name;
        }
        public static Map<String, String> toMap() {
        	logRecordModule[] ds = logRecordModule.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (logRecordModule d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: activityType 
    * @Description: 活动类型
    * @author htt
    * @date 2017-12-6 上午11:04:25 
    *
     */
    public static enum activityType {
        XSHD("新手活动","1"),
        TJYL("推荐有礼","2");
        private String name;
        private String index;
        private activityType(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	activityType[] ds = activityType.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (activityType d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: isNovice 
    * @Description: 是否新手专享
    * @author htt
    * @date 2018-1-16 下午2:15:36 
    *
     */
    public static enum isNovice {
        F("否","0"),
        Y("是","1");
        private String name;
        private String index;
        private isNovice(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	isNovice[] ds = isNovice.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (isNovice d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: isStandardUser 
    * @Description: 是否标准户
    * @author htt
    * @date 2018-2-3 下午2:38:50 
    *
     */
    public static enum isStandardUser {
        F("否","0"),
        Y("是","1");
        private String name;
        private String index;
        private isStandardUser(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	isStandardUser[] ds = isStandardUser.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (isStandardUser d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: acceptPrize 
    * @Description: 领奖标志
    * @author htt
    * @date 2018-3-5 上午11:15:35 
    *
     */
    public static enum acceptPrize {
        WLJ("未领奖","0"),
        YLJ("已领奖","1");
        private String name;
        private String index;
        private acceptPrize(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	acceptPrize[] ds = acceptPrize.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (acceptPrize d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 
    * @ClassName: payType 
    * @Description: 金条订单-提取类型
    * @author htt
    * @date 2018-5-25 下午2:29:26 
    *
     */
    public static enum payType {
        SJTQ("实金提取","1"),
        SJDH("实金兑换","2"),
        SDJG("手动交割","3"),
        QZJG("强制交割","4"),
        XZJG("选择交割","5");

        private String name;
        private String index;
        private payType(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	payType[] ds = payType.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (payType d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }


    /**
     * 
    * @ClassName: inVestGoldOrderStatus 
    * @Description: 今条订单状态
    * @author htt
    * @date 2018-5-28 下午4:09:02 
    *
     */
    public static enum inVestGoldOrderStatus {


        WZF("待支付","0"),
        WFH("未发货","10"),
        YFH("已发货","20"),
        YWC("已完成","30"),
        YQX("已取消","40"),
        YGB("已关闭","50"),
        YZF("已作废","100");

        private String name;
        private String index;
        private inVestGoldOrderStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	inVestGoldOrderStatus[] ds = inVestGoldOrderStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (inVestGoldOrderStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }


    /**
     * @ClassName: dealOrderStatus
     * @Description: 金权交易订单状态
     * @author liaijiao
     * @date 2018-8-28 下午5:00:02
     */
    public static enum dealOrderStatus {
         CCZ("持仓中","0"),
         YPC("已平仓","1"),
         DQR("待确认","2");

        private String name;
        private String index;
        private dealOrderStatus(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            dealOrderStatus[] ds = dealOrderStatus.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (dealOrderStatus d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    public static enum types {

        ZDJG("自动交割","0"),
        SDJG("手动交割","1");

        private String name;
        private String index;
        private types(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            types[] ds = types.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (types d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }

    /**
     * 回购订单状态配置
     */
    public static enum status2 {

        DWS("待完善","0"),
        SX("已生效","1"),
        YSX("已失效","2"),
        YQX("已取消","3"),
        YWC("已完成","4");

        private String name;
        private String index;
        private status2(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            status2[] ds = status2.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (status2 d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }

    public static enum statuss {

        YX("有效","1"),
        WX("无效","2");

        private String name;
        private String index;
        private statuss(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
            statuss[] st = statuss.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (statuss d : st) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }









    
    /**
     * 
    * @ClassName: saveGoldRecordType 
    * @Description: 存金记录类型
    * @author htt
    * @date 2018-5-29 下午3:26:43 
    *
     */
    public static enum saveGoldRecordType {
        B("买黄金","1"),
        S("卖黄金","4");
        private String name;
        private String index;
        private saveGoldRecordType(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	saveGoldRecordType[] ds = saveGoldRecordType.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (saveGoldRecordType d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }
    
    /**
     * 系统参数缓存数据区域
     */
    public static enum cacheRegion {
    	Local("Local(map)","0"),
    	Remote("Remote(redis)","1");
        private String name;
        private String index;
        private cacheRegion(String name,String index){
            this.name = name;
            this.index = index;
        }
        public String toString() {
            return this.index;
        }
        public static Map<String, String> toMap() {
        	cacheRegion[] ds = cacheRegion.values();
            Map<String, String> rlt = new Hashtable<String, String>();
            for (cacheRegion d : ds) {
                rlt.put(d.index, d.name);
            }
            return rlt;
        }
    }

}
