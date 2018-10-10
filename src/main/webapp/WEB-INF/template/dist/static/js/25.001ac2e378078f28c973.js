webpackJsonp([25],{

/***/ 570:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(940)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(659),
  /* template */
  __webpack_require__(857),
  /* scopeId */
  "data-v-ff7e9ef4",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 659:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            value1: '',
            loading: true,
            url1: this.api + "dealOrder/getHedgeArbitrage1",
            url2: this.api + "dealOrder/getHedgeArbitrage2",
            url3: this.api + "dealOrder/getHedgeArbitrage3",
            url4: this.api + "saveGoldRecord/getSaveGoldAmount",
            tableData1: [],
            tableData2: [],
            tableData3: [],
            tableData4: [],
            formLabelWidth: '120px'
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData1();
        this.loadData2();
        this.loadData3();
        this.loadData();
        this.intervalid1 = setInterval(() => {
            this.loadData1();
            this.loadData2();
            this.loadData3();
            this.loadData();
        }, 10000);
    },
    beforeDestroy() {
        clearInterval(this.intervalid1);
    },
    methods: {
        //刷新表格方法
        loadData1() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url1, params).then(function (response) {
                var arr = new Array();
                arr.push(response.data.data);
                _this.tableData1 = arr;
            }).catch(function (error) {});
        },
        loadData2() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url2, params).then(function (response) {
                var arr = new Array();
                arr.push(response.data.data);
                _this.tableData2 = arr;
            }).catch(function (error) {});
        },
        loadData3() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url3, params).then(function (response) {
                var arr = new Array();
                arr.push(response.data.data);
                _this.tableData3 = arr;
            }).catch(function (error) {});
        },
        loadData() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url4, params).then(function (response) {
                if (response.data.code == 1001) {
                    var arr = new Array();
                    arr.push(response.data.data);
                    _this.tableData4 = arr;
                }
            }).catch(function (error) {});
        }
    }

});

/***/ }),

/***/ 762:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 857:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("对冲套利")]), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "data": _vm.tableData1,
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "买涨持仓克重(克)",
      "prop": "gramUp",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买涨均价(元)",
      "prop": "avgUp",
      "width": ""
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "data": _vm.tableData2,
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "买跌持仓克重(克)",
      "prop": "gramDown",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买跌均价",
      "prop": "avgDown",
      "width": ""
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "data": _vm.tableData3,
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "净值(元)",
      "prop": "netValue",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "获利(元)",
      "prop": "profit",
      "width": ""
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "data": _vm.tableData4,
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "平台存金宝(克)",
      "prop": "saveGoldAmount",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "未到期金生金(克)",
      "prop": "steadyGoldAmount",
      "width": ""
    }
  })], 1)], 1)])])
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content-header"
    }
  }, [_c('div', {
    attrs: {
      "id": "breadcrumb"
    }
  }, [_c('a', {
    staticClass: "tip-bottom",
    attrs: {
      "href": "javascript:;",
      "title": "Go to Home"
    }
  }, [_c('i', {
    staticClass: "icon-home"
  }), _vm._v(" 产品设定")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("对冲套利")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 940:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(762);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("96e9192e", content, true);

/***/ })

});