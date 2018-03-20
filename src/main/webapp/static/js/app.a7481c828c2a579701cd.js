webpackJsonp([56],{

/***/ 197:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App__ = __webpack_require__(502);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__router__ = __webpack_require__(242);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios__ = __webpack_require__(223);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui__ = __webpack_require__(485);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_vue_resource__ = __webpack_require__(504);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__static_js_common_js__ = __webpack_require__(509);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__store_index__ = __webpack_require__(243);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css__ = __webpack_require__(498);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_babel_polyfill__ = __webpack_require__(135);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_babel_polyfill___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_babel_polyfill__);









__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_6__static_js_common_js__["a" /* default */]);




__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_4_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.$axios = __WEBPACK_IMPORTED_MODULE_3_axios___default.a;
new __WEBPACK_IMPORTED_MODULE_0_vue___default.a({
    router: __WEBPACK_IMPORTED_MODULE_2__router__["a" /* default */],
    store: __WEBPACK_IMPORTED_MODULE_7__store_index__["a" /* default */],
    render: function render(h) {
        return h(__WEBPACK_IMPORTED_MODULE_1__App___default.a);
    }
}).$mount('#app');
__WEBPACK_IMPORTED_MODULE_2__router__["a" /* default */].beforeEach(function (to, from, next) {
    if (to.matched.some(function (record) {
        return record.meta.requiresAuth;
    })) {
        if (!localStorage.token) {
            next({
                path: '/login',
                query: { redirect: to.fullPath }
            });
        } else {
            next();
        }
    } else {
        next();
    }
});

/***/ }),

/***/ 242:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(505);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({

    routes: [{
        path: '/',
        redirect: '/login'
    }, {
        path: '/index',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(512)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        },
        children: [{
            path: '/',
            name: '首页',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(32).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(538)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/customerAnalysis',
            name: '用户信息',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(46).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(522)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/accountInfo',
            name: '账户信息',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(50).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(518)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/certification',
            name: '实名认证',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(49).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(519)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/certificationAppro',
            name: '实名认证审核记录',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(48).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(520)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/clientProperty',
            name: '客户属性',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(47).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(521)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/registerInfo',
            name: '注册信息',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(45).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(523)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/riskAssessment',
            name: '风险评测',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(524)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/standardUser',
            name: '标准户统计分析',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(44).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(525)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialTran',
            name: '理财交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(561)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTrading',
            name: '金权交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(563)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTradingCenter',
            name: '黄金稳赚交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(564)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/realGoldTrading',
            name: '实金交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(565)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/tradingAnasysis',
            name: '交易分析',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(566)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialnewPlayer',
            name: '新手理财交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(562)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialProductSetting',
            name: '理财产品设定',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(553)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldRuleSetting',
            name: '金权规则设定',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(554)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/hedgeArbitrage',
            name: '对冲套利',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(555)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/realGoldTrandingSetting',
            name: '实金买卖设定',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(556)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/cashExtraction',
            name: '现金提取',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(43).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(526)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/cashRecharge',
            name: '现金充值',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(42).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(527)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/entryAndExitMngAnalysis',
            name: '出入金分析',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(528)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/entryAndExitMngInquiry',
            name: '出入金查询',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(41).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(529)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/financialCaculation',
            name: '理财收益计算',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(39).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(531)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialnewSettlement',
            name: '新手理财收益计算',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(40).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(530)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldCalulation',
            name: '黄金收益计算',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(38).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(532)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldExtraction',
            name: '黄金提取',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(37).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(533)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldSettlement',
            name: '黄金稳赚看涨结算',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(36).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(534)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, { path: '/payWay',
            name: '支付管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(35).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(535)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/notice',
            name: '公告管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(543)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/adverMng',
            name: 'banner图、弹出图管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(540)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldenClass',
            name: '黄金课堂',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(30).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(542)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/shouldCenter',
            name: '希欧德中心',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(544)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/fundMng',
            name: '资讯管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(31).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(541)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/actMng',
            name: '活动管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(539)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/accountMng',
            name: '账号管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(557)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/acterMng',
            name: '角色管理',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(558)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/dialogInfo',
            name: '日志信息',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(560)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/auditRecord',
            name: '审计日志',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(559)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/redeemRecord',
            name: '赎回记录',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(33).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(537)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/redeemCal',
            name: '赎回计算',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(34).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(536)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/valueCouncher',
            name: '优惠券',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(51).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(517)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/coupon',
            name: '加息券',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(52).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(516)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/activityGoldRecord',
            name: '黄金领取',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(54).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(514)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/shareRegisterRecord',
            name: '推荐用户明细',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(53).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(515)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/merchantMngOfOperator',
            name: '商户管理（运营商视角）',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(551)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/merchantMngOfMnger',
            name: '商户管理（小象管理视角）',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(550)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialTrading',
            name: '理财交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(545)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTrade',
            name: '实金交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(548)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldRightTrading',
            name: '金权交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(547)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldWenTrade',
            name: '黄金稳赚交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(549)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialnew',
            name: '新手理财交易',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(546)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/subClient',
            name: '下级客户',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(552)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }]
    }, {
        path: '/login',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(513)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
}));

/***/ }),

/***/ 243:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_get_iterator__ = __webpack_require__(200);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_get_iterator___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_get_iterator__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vuex__ = __webpack_require__(508);




__WEBPACK_IMPORTED_MODULE_1_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_2_vuex__["a" /* default */]);

var store = new __WEBPACK_IMPORTED_MODULE_2_vuex__["a" /* default */].Store({
  state: {
    options: [],
    activeIndex: '/user',
    userInfo: {}
  },
  mutations: {
    add_tabs: function add_tabs(state, data) {
      this.state.options.push(data);
    },
    delete_tabs: function delete_tabs(state, route) {
      var index = 0;
      var _iteratorNormalCompletion = true;
      var _didIteratorError = false;
      var _iteratorError = undefined;

      try {
        for (var _iterator = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_get_iterator___default()(state.options), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
          var option = _step.value;

          if (option.route === route) {
            break;
          }
          index++;
        }
      } catch (err) {
        _didIteratorError = true;
        _iteratorError = err;
      } finally {
        try {
          if (!_iteratorNormalCompletion && _iterator.return) {
            _iterator.return();
          }
        } finally {
          if (_didIteratorError) {
            throw _iteratorError;
          }
        }
      }

      this.state.options.splice(index, 1);
    },
    set_active_index: function set_active_index(state, index) {
      this.state.activeIndex = index;
    },
    save_detail_info: function save_detail_info(state, info) {
      this.state.userInfo = info;
    }
  }
});

/* harmony default export */ __webpack_exports__["a"] = (store);

/***/ }),

/***/ 478:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(undefined);
// imports
exports.i(__webpack_require__(479), "");

// module
exports.push([module.i, ".el-dialog--small{width:30%!important}", ""]);

// exports


/***/ }),

/***/ 479:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(undefined);
// imports


// module
exports.push([module.i, ".header{background-color:#242f42}.login-wrap{background:#324157}.plugins-tips{background:#eef1f6}.el-upload--text em,.plugins-tips a{color:#20a0ff}.pure-button{background:#20a0ff}", ""]);

// exports


/***/ }),

/***/ 498:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 502:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(506)

var Component = __webpack_require__(198)(
  /* script */
  null,
  /* template */
  __webpack_require__(503),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 503:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [_c('router-view')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 506:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(478);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(199)("2b2424e6", content, true);

/***/ }),

/***/ 509:
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

/***/ 510:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 511:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(135);
module.exports = __webpack_require__(197);


/***/ })

},[511]);