webpackJsonp([48],{

/***/ 195:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App__ = __webpack_require__(496);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__router__ = __webpack_require__(239);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios__ = __webpack_require__(220);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui__ = __webpack_require__(479);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_vue_resource__ = __webpack_require__(498);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__static_js_common_js__ = __webpack_require__(502);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__ = __webpack_require__(492);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill__ = __webpack_require__(134);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_babel_polyfill__);








__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_6__static_js_common_js__["a" /* default */]);




__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_4_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_5_vue_resource__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.$axios = __WEBPACK_IMPORTED_MODULE_3_axios___default.a;
new __WEBPACK_IMPORTED_MODULE_0_vue___default.a({
    router: __WEBPACK_IMPORTED_MODULE_2__router__["a" /* default */],
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

/***/ 239:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(499);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({

    routes: [{
        path: '/',
        redirect: '/login'
    }, {
        path: '/index',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(505)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        },
        children: [{
            path: '/',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(30).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(525)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/customerAnalysis',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(41).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(512)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/accountInfo',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(44).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(509)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/certification',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(43).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(510)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/clientProperty',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(42).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(511)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/registerInfo',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(40).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(513)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/riskAssessment',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(514)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialTran',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(547)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTrading',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(548)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTradingCenter',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(549)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/realGoldTrading',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(550)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/tradingAnasysis',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(551)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialProductSetting',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(539)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldRuleSetting',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(540)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/hedgeArbitrage',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(541)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/realGoldTrandingSetting',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(542)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/cashExtraction',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(39).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(515)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/cashRecharge',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(38).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(516)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/entryAndExitMngAnalysis',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(517)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/entryAndExitMngInquiry',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(37).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(518)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/financialCaculation',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(36).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(519)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldCalulation',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(35).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(520)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldExtraction',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(34).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(521)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldSettlement',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(33).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(522)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/notice',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(530)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/adverMng',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(527)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldenClass',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(529)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/shouldCenter',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(531)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/fundMng',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(528)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/actMng',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(526)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/accountMng',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(543)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/acterMng',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(544)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/dialogInfo',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(546)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/auditRecord',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(545)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/redeemRecord',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(31).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(524)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/redeemCal',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(32).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(523)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/valueCouncher',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(45).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(508)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/coupon',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(46).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(507)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/merchantMngOfOperator',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(537)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/merchantMngOfMnger',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(536)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/finacialTrading',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(532)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldTrade',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(534)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldRightTrading',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(533)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/goldWenTrade',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(535)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }, {
            path: '/subClient',
            component: function component(resolve) {
                return __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(538)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
            }
        }]
    }, {
        path: '/login',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(506)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
}));

/***/ }),

/***/ 472:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(135)(undefined);
// imports
exports.i(__webpack_require__(473), "");

// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 473:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(135)(undefined);
// imports


// module
exports.push([module.i, ".header{background-color:#242f42}.login-wrap{background:#324157}.plugins-tips{background:#eef1f6}.el-upload--text em,.plugins-tips a{color:#20a0ff}.pure-button{background:#20a0ff}", ""]);

// exports


/***/ }),

/***/ 492:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 496:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(500)

var Component = __webpack_require__(196)(
  /* script */
  null,
  /* template */
  __webpack_require__(497),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 497:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [_c('router-view')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 500:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(472);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(197)("2b2424e6", content, true);

/***/ }),

/***/ 502:
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

/***/ 503:
/***/ (function(module, exports) {

/* (ignored) */

/***/ }),

/***/ 504:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(134);
module.exports = __webpack_require__(195);


/***/ })

},[504]);