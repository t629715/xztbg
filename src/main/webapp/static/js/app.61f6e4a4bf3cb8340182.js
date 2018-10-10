webpackJsonp([83],{

/***/ 196:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App__ = __webpack_require__(498);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__router__ = __webpack_require__(240);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios__ = __webpack_require__(221);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui__ = __webpack_require__(481);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_vue_resource__ = __webpack_require__(500);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__static_js_common_js__ = __webpack_require__(505);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__store_index__ = __webpack_require__(241);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css__ = __webpack_require__(494);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_babel_polyfill__ = __webpack_require__(135);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_babel_polyfill___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_babel_polyfill__);









__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_6__static_js_common_js__["a" /* default */]);

 // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题

__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.api = '';
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_4_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.$axios = __WEBPACK_IMPORTED_MODULE_3_axios___default.a;
new __WEBPACK_IMPORTED_MODULE_0_vue___default.a({
    router: __WEBPACK_IMPORTED_MODULE_2__router__["a" /* default */],
    store: __WEBPACK_IMPORTED_MODULE_7__store_index__["a" /* default */],
    render: h => h(__WEBPACK_IMPORTED_MODULE_1__App___default.a)
}).$mount('#app');
__WEBPACK_IMPORTED_MODULE_2__router__["a" /* default */].beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        //这里判断用户是否登录，我例子中是验证本地存储是否有token
        if (!localStorage.token) {
            next({
                path: '/login',
                query: { redirect: to.fullPath }
            });
        } else {
            next();
        }
    } else {
        next(); // 确保一定要调用 next()
    }
});

/***/ }),

/***/ 240:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(501);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({

    routes: [{
        path: '/',
        redirect: '/login'
    }, {
        path: '/index',
        component: resolve => __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(509)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe),
        children: [
        //客户 管理
        {
            path: '/',
            name: '首页',
            component: resolve => __webpack_require__.e/* require */(52).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(541)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/customerAnalysis',
            name: '客户分析',
            component: resolve => __webpack_require__.e/* require */(71).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(520)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/accountInfo',
            name: '账户信息',
            component: resolve => __webpack_require__.e/* require */(75).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(516)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/certification',
            name: '实名认证',
            component: resolve => __webpack_require__.e/* require */(74).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(517)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/certificationAppro',
            name: '实名认证审核记录',
            component: resolve => __webpack_require__.e/* require */(73).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(518)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/clientProperty',
            name: '客户属性',
            component: resolve => __webpack_require__.e/* require */(72).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(519)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/registerInfo',
            name: '注册信息',
            component: resolve => __webpack_require__.e/* require */(70).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(521)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/riskAssessment',
            name: '风险评测',
            component: resolve => __webpack_require__.e/* require */(69).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(522)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/standardUser',
            name: '标准户统计分析',
            component: resolve => __webpack_require__.e/* require */(68).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(523)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        // 交易管理
        {
            path: '/finacialTran',
            name: '理财交易',
            component: resolve => __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(582)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldTrading',
            name: '金权交易',
            component: resolve => __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(584)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/periodicGoldTrading',
            name: '金生金交易',
            component: resolve => __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(586)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldTradingCenter',
            name: '黄金稳赚交易',
            component: resolve => __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(585)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/realGoldTrading',
            name: '实金交易',
            component: resolve => __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(587)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/tradingAnasysis',
            name: '交易分析',
            component: resolve => __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(590)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/finacialnewPlayer',
            name: '新手理财交易',
            component: resolve => __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(583)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/saveGoldBuy',
            name: '黄金交易（买）',
            component: resolve => __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(588)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/saveGoldSale',
            name: '黄金交易（卖）',
            component: resolve => __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(589)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },

        // 产品设定
        {
            path: '/finacialProductSetting',
            name: '理财产品设定',
            component: resolve => __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(568)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/periodicMng',
            name: '金生金设定',
            component: resolve => __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(572)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldRuleSetting',
            name: '金权规则设定',
            component: resolve => __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(569)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/hedgeArbitrage',
            name: '对冲套利',
            component: resolve => __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(570)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/realGoldTrandingSetting',
            name: '实金买卖设定',
            component: resolve => __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(574)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/saveGoldSetting',
            name: '存金宝设定',
            component: resolve => __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(566)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/deliveryGoldSetting',
            name: '金权交割规则设定',
            component: resolve => __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(567)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/investGoldConfSetting',
            name: '金条投资产品规则',
            component: resolve => __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(571)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //出入金管理
        {
            path: '/cashExtraction',
            name: '现金提取',
            component: resolve => __webpack_require__.e/* require */(66).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(525)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/cashRecharge',
            name: '现金充值',
            component: resolve => __webpack_require__.e/* require */(65).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(526)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/cashRechargeFail',
            name: '现金充值失败',
            component: resolve => __webpack_require__.e/* require */(64).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(527)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/manualRecharge',
            name: '人工充值',
            component: resolve => __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(537)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/entryAndExitMngAnalysis',
            name: '出入金分析',
            component: resolve => __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(528)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/entryAndExitMngInquiry',
            name: '出入金查询',
            component: resolve => __webpack_require__.e/* require */(63).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(529)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/financialCaculation',
            name: '理财收益计算',
            component: resolve => __webpack_require__.e/* require */(61).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(531)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/finacialnewSettlement',
            name: '新手理财收益计算',
            component: resolve => __webpack_require__.e/* require */(62).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(530)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldCalulation',
            name: '黄金收益计算',
            component: resolve => __webpack_require__.e/* require */(60).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(532)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldExtraction',
            name: '黄金提取',
            component: resolve => __webpack_require__.e/* require */(59).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(533)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldSettlement',
            name: '黄金稳赚看涨结算',
            component: resolve => __webpack_require__.e/* require */(58).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(534)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, { path: '/payWay',
            name: '支付管理',
            component: resolve => __webpack_require__.e/* require */(55).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(538)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inVestGold',
            name: '金条订单',
            component: resolve => __webpack_require__.e/* require */(57).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(535)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/periodicGoldTradeCalc',
            name: '金生金交易结算',
            component: resolve => __webpack_require__.e/* require */(67).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(524)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inVestGoldDelivery',
            name: '金权交割',
            component: resolve => __webpack_require__.e/* require */(56).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(536)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //运营管理
        {
            path: '/notice',
            name: '公告管理',
            component: resolve => __webpack_require__.e/* require */(42).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(553)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/adverMng',
            name: 'banner图、弹出图管理',
            component: resolve => __webpack_require__.e/* require */(45).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(549)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldenClass',
            name: '黄金课堂',
            component: resolve => __webpack_require__.e/* require */(43).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(551)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/shouldCenter',
            name: '希欧德中心',
            component: resolve => __webpack_require__.e/* require */(41).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(554)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/fundMng',
            name: '资讯管理',
            component: resolve => __webpack_require__.e/* require */(44).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(550)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/actMng',
            name: '活动管理',
            component: resolve => __webpack_require__.e/* require */(46).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(548)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/topicPictureMng',
            name: '专题图片管理',
            component: resolve => __webpack_require__.e/* require */(50).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(543)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/actController',
            name: '活动控制',
            component: resolve => __webpack_require__.e/* require */(47).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(546)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/actJulGameResults',
            name: '世界杯赛程',
            component: resolve => __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(547)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/manualRechargeGold',
            name: '黄金充值',
            component: resolve => __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(552)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/worldCupGuesse',
            name: '竞猜统计',
            component: resolve => __webpack_require__.e/* require */(49).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(544)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/getWinner',
            name: '冠军猜测结果',
            component: resolve => __webpack_require__.e/* require */(51).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(542)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/worldCupWinner',
            name: '竞猜冠军统计',
            component: resolve => __webpack_require__.e/* require */(48).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(545)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/registCount',
            name: '推广页注册统计',
            component: resolve => __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(575)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //系统管理
        {
            path: '/accountMng',
            name: '账号管理',
            component: resolve => __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(576)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/resourceMng',
            name: '菜单管理',
            component: resolve => __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(581)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/acterMng',
            name: '角色管理',
            component: resolve => __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(577)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/dialogInfo',
            name: '日志信息',
            component: resolve => __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(580)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/auditRecord',
            name: '审计日志',
            component: resolve => __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(578)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/configParam',
            name: '系统参数',
            component: resolve => __webpack_require__.e/* require */(81).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(579)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //黄金赎回
        {
            path: '/redeemRecord',
            name: '赎回记录',
            component: resolve => __webpack_require__.e/* require */(53).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(540)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/redeemCal',
            name: '赎回计算',
            component: resolve => __webpack_require__.e/* require */(54).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(539)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //优惠券
        {
            path: '/valueCouncher',
            name: '优惠券',
            component: resolve => __webpack_require__.e/* require */(76).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(515)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/coupon',
            name: '加息券',
            component: resolve => __webpack_require__.e/* require */(77).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(514)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //活动管理
        {
            path: '/activityGoldRecord',
            name: '黄金领取',
            component: resolve => __webpack_require__.e/* require */(80).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(511)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/shareRegisterRecord',
            name: '推荐用户明细',
            component: resolve => __webpack_require__.e/* require */(78).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(513)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/recommendRegisterRecord',
            name: '推荐用户详情',
            component: resolve => __webpack_require__.e/* require */(79).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(512)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        },
        //合作伙伴
        {
            path: '/merchantMngOfOperator',
            name: '商户管理（运营商视角）',
            component: resolve => __webpack_require__.e/* require */(33).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(562)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/merchantMngOfMnger',
            name: '商户管理（小象管理视角）',
            component: resolve => __webpack_require__.e/* require */(34).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(561)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/finacialTrading',
            name: '理财交易',
            component: resolve => __webpack_require__.e/* require */(39).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(556)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldTrade',
            name: '实金交易',
            component: resolve => __webpack_require__.e/* require */(36).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(559)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldRightTrading',
            name: '金权交易',
            component: resolve => __webpack_require__.e/* require */(37).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(558)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/goldWenTrade',
            name: '黄金稳赚交易',
            component: resolve => __webpack_require__.e/* require */(35).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(560)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/finacialnew',
            name: '新手理财交易',
            component: resolve => __webpack_require__.e/* require */(38).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(557)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/saveGoldRecordBuy',
            name: '黄金交易（买）',
            component: resolve => __webpack_require__.e/* require */(32).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(563)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/saveGoldRecordSale',
            name: '黄金交易（卖）',
            component: resolve => __webpack_require__.e/* require */(31).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(564)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/periodicGoldTrade',
            name: '金生金交易结算',
            component: resolve => __webpack_require__.e/* require */(40).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(555)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/subClient',
            name: '下级客户',
            component: resolve => __webpack_require__.e/* require */(30).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(565)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/investGoldPrice',
            name: '金条投资报价',
            component: resolve => __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(573)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }]
    }, {
        path: '/login',
        component: resolve => __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(510)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
    }]
}));

/***/ }),

/***/ 241:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(504);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */]);

/**
 * Vuex全局状态管理
 * @param options {Array} 用于渲染tabs的数组
 */
const store = new __WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */].Store({
  state: {
    options: [],
    activeIndex: '/user',
    userInfo: {}
  },
  mutations: {
    // 添加tabs
    add_tabs(state, data) {
      this.state.options.push(data);
    },
    // 删除tabs
    delete_tabs(state, route) {
      let index = 0;
      for (let option of state.options) {
        if (option.route === route) {
          break;
        }
        index++;
      }
      this.state.options.splice(index, 1);
    },
    // 设置当前激活的tab
    set_active_index(state, index) {
      this.state.activeIndex = index;
    },
    // 设置详情信息
    save_detail_info(state, info) {
      this.state.userInfo = info;
    }
  }
});

/* harmony default export */ __webpack_exports__["a"] = (store);

/***/ }),

/***/ 474:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports
exports.i(__webpack_require__(475), "");

// module
exports.push([module.i, ".el-dialog--small{width:30%!important}", ""]);

// exports


/***/ }),

/***/ 475:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".header{background-color:#242f42}.login-wrap{background:#324157}.plugins-tips{background:#eef1f6}.el-upload--text em,.plugins-tips a{color:#20a0ff}.pure-button{background:#20a0ff}", ""]);

// exports


/***/ }),

/***/ 494:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 498:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(502)

var Component = __webpack_require__(197)(
  /* script */
  null,
  /* template */
  __webpack_require__(499),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 499:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [_c('router-view')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 502:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(474);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("02d9f1a7", content, true);

/***/ }),

/***/ 505:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony default export */ __webpack_exports__["a"] = ({
    install(Vue,options){
      //  Vue.prototype.url= "http://www.baidu.com/"   //可以自定义变量
      //自定义方法 调用时 this.fun1
      Vue.prototype.fun1=function(){
        alert('hello1')
      };

      //判断是否为空
      Vue.prototype.isNotEmpty = function(str) {
          if (str != "" && str != null) {
            return true;
          } else {
            return false;
          }
      };

      //分转为圆，保留2位小数,为空返回0
      Vue.prototype.amountHandle1 = function(str) {
        return  Vue.prototype.isNotEmpty(str) ? (Number(str)/100).toFixed(2) : 0;
      };
      //分转为圆，保留2位小数,为空返回''
      Vue.prototype.amountHandle2 = function(str) {
        return  Vue.prototype.isNotEmpty(str) ? (Number(str)/100).toFixed(2) : '';
      };
      Vue.prototype.dateFormat = function(date) {
        var dateobj = new Date(date);
        var y = dateobj.getFullYear();
        var m = dateobj.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = dateobj.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = dateobj.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = dateobj.getMinutes();
        var second = dateobj.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
      };

//控制页面所有路由开始之前结束之后的方法
      Vue.http.interceptors.push(function ( request, next ) {
    // 请求发送前的处理逻辑
            next(function (response) {
                // 请求发送后的处理逻辑
                // 更具请求的状态， response参数会返回给 successCallback或errorCallback
                    if(response.data.result == undefined){
                        this.$router.push('/')
                    }
//              return response
            });

        });
    }
});


/***/ }),

/***/ 506:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 507:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(135);
module.exports = __webpack_require__(196);


/***/ })

},[507]);