webpackJsonp([6],{

/***/ 586:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(872)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(671),
  /* template */
  __webpack_require__(793),
  /* scopeId */
  "data-v-44bc368e",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 671:
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
    //model 初始数据
    data() {
        return {
            loading: true,
            actorList: "",
            form: {
                date1: '',
                date2: '',
                agentId: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            brokeList: [],
            agentOptions: [{
                id: "",
                name: "全部"
            }, {
                id: 2,
                name: "直客"
            }, {
                id: -11,
                name: "代理商"
            }],
            url: this.api + "analysis/orderAnalysis",
            exportUrl: this.api + "analysis/exportAnalysis",
            countUrl: this.api + "analysis/orderAnalysisCount",
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            formLabelWidth: '120px',
            userId: "",
            countList: "",
            userTotal: ''
        };
    },
    //页面渲染加载方法
    filters: {
        divide: function (value) {
            if (value == null) {
                return 0;
            }
            return value;
        },
        convert: function (value) {
            if (value == null) {
                return 0;
            }
            return value;
        }
    },
    created() {
        /*this.loadData(10,1);*/
        /*this.countNum(10,1);*/
    },
    //定义方法
    methods: {

        selectTime() {},
        whicth() {},
        countNum(pageSize, pageNum) {
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                agentId = "",
                profitLoss = "",
                orderState = "",
                upOrDown = "",
                time = "";
            if (this.form.profitLoss != undefined) {
                profitLoss = this.form.profitLoss;
            }
            if (this.form.orderState != undefined) {
                if ("" != this.form.orderState) {
                    orderState = this.form.orderState;
                }
            }
            if (this.form.upOrDown != undefined) {
                upOrDown = this.form.upOrDown;
            }
            if (this.form.time != undefined) {
                time = this.form.time;
            }
            if (this.form.date1 != "") {
                datetime1 = Date.parse(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = Date.parse(this.form.date2);
            }
            if (this.form.agentId != undefined) {
                agentId = this.form.agentId;
            }
            this.pagesize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('agentId', agentId);
            params.append('profitLoss', profitLoss);
            params.append('orderState', orderState);
            params.append('upOrDown', upOrDown);
            params.append('time', time);
            axios.post(this.countUrl, params).then(function (res) {
                _this.countList = res.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        //查询方法
        onSubmit(form) {
            this.countNum(10, 1);
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                agentId = "",
                profitLoss = "",
                orderState = "",
                upOrDown = "",
                time = "";
            if (this.form.profitLoss != undefined) {
                profitLoss = this.form.profitLoss;
            }
            if (this.form.orderState != undefined) {
                if ("" != this.form.orderState) {
                    orderState = this.form.orderState;
                }
            }
            if (this.form.upOrDown != undefined) {
                upOrDown = this.form.upOrDown;
            }
            if (this.form.time != undefined) {
                time = this.form.time;
            }
            if (this.form.date1 != "") {
                datetime1 = Date.parse(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = Date.parse(this.form.date2);
            }
            if (this.form.agentId != undefined) {
                agentId = this.form.agentId;
            }
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('agentId', agentId);
            params.append('profitLoss', profitLoss);
            params.append('orderState', orderState);
            params.append('upOrDown', upOrDown);
            params.append('time', time);
            axios.post(this.url, params).then(function (response) {
                _this.$message({
                    message: '查询成功',
                    type: 'success'
                });
                _this.currentPage = 1;

                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {});
        },
        //清空表单
        resetForm() {
            this.$refs.form.resetFields();
        },
        exportFun() {
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                agentId = "",
                profitLoss = "",
                orderState = "",
                upOrDown = "",
                time = "";
            if (this.form.profitLoss != undefined) {
                profitLoss = this.form.profitLoss;
            }
            if (this.form.orderState != undefined) {
                if ("" != this.form.orderState) {
                    orderState = this.form.orderState;
                }
            }
            if (this.form.upOrDown != undefined) {
                upOrDown = this.form.upOrDown;
            }
            if (this.form.time != undefined) {
                time = this.form.time;
            }
            if (this.form.date1 != "") {
                datetime1 = Date.parse(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = Date.parse(this.form.date2);
            }
            if (this.form.agentId != undefined) {
                agentId = this.form.agentId;
            }
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('agentId', agentId);
            params.append('profitLoss', profitLoss);
            params.append('orderState', orderState);
            params.append('upOrDown', upOrDown);
            params.append('time', time);
            if (this.form.time == undefined && this.form.date1 == "" && this.form.date2 == "") {} else {
                window.location = this.exportUrl + "?" + params;
            }
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            var datetime1 = '',
                datetime2 = "",
                agentId = "",
                profitLoss = "",
                orderState = "",
                upOrDown = "",
                time = "";
            if (this.form.profitLoss != undefined) {
                profitLoss = this.form.profitLoss;
            }
            if (this.form.orderState != undefined) {
                if ("" != this.form.orderState) {
                    orderState = this.form.orderState;
                }
            }
            if (this.form.upOrDown != undefined) {
                upOrDown = this.form.upOrDown;
            }
            if (this.form.time != undefined) {
                time = this.form.time;
            }
            if (this.form.date1 != "") {
                datetime1 = Date.parse(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = Date.parse(this.form.date2);
            }
            if (this.form.agentId != undefined) {
                agentId = this.form.agentId;
            }
            this.pagesize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('agentId', agentId);
            params.append('profitLoss', profitLoss);
            params.append('orderState', orderState);
            params.append('upOrDown', upOrDown);
            params.append('time', time);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                _this.totalNum = response.data.data.total;
                _this.pageNum = response.data.data.pages;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {});
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            this.loadData(this.pagesize, val);
            this.countNum(this.pagesize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.loadData(val, 1);
            this.countNum(val, 1);
        }
    }
});

/***/ }),

/***/ 702:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-44bc368e]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-44bc368e]{border-radius:4px}.bg-purple-dark[data-v-44bc368e]{background:#99a9bf}.bg-color1[data-v-44bc368e]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-44bc368e]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-44bc368e]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-44bc368e]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-44bc368e]{background:#d3dce6}.bg-purple-light[data-v-44bc368e]{background:#e5e9f2}.gridBox[data-v-44bc368e]{padding-left:20px}.grid-content[data-v-44bc368e]{height:115px;border-radius:4px;min-height:75px;text-align:left;padding-left:40px;font-size:14px}.row-bg[data-v-44bc368e]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 793:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('el-form', {
    ref: "form",
    attrs: {
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.form,
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "时间：",
      "prop": "radio1"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.time),
      callback: function($$v) {
        _vm.$set(_vm.form, "time", $$v)
      },
      expression: "form.time"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "1"
    }
  }, [_vm._v("今天")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "2",
      "name": "1"
    }
  }, [_vm._v("昨天")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "3",
      "name": "1"
    }
  }, [_vm._v("最近7天")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "4",
      "name": "1"
    }
  }, [_vm._v("最近30天")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易类型：",
      "prop": "radio2"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.upOrDown),
      callback: function($$v) {
        _vm.$set(_vm.form, "upOrDown", $$v)
      },
      expression: "form.upOrDown"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "",
      "name": "2"
    }
  }, [_vm._v("全部")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "0",
      "name": "2"
    }
  }, [_vm._v("买涨")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "2"
    }
  }, [_vm._v("买跌")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易状态：",
      "prop": "radio3"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.orderState),
      callback: function($$v) {
        _vm.$set(_vm.form, "orderState", $$v)
      },
      expression: "form.orderState"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "",
      "name": "3",
      "value": 1
    }
  }, [_vm._v("全部")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "0",
      "name": "3",
      "value": 2
    }
  }, [_vm._v("持仓")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "3",
      "value": 3
    }
  }, [_vm._v("平仓")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "盈亏状态：",
      "prop": "radio4"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.profitLoss),
      callback: function($$v) {
        _vm.$set(_vm.form, "profitLoss", $$v)
      },
      expression: "form.profitLoss"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "",
      "name": "4"
    }
  }, [_vm._v("全部")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "4"
    }
  }, [_vm._v("盈利")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "2",
      "name": "4"
    }
  }, [_vm._v("亏损")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "3",
      "name": "4"
    }
  }, [_vm._v("持平")])], 1)], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "自定义",
      "prop": "date1"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.form.date1),
      callback: function($$v) {
        _vm.$set(_vm.form, "date1", $$v)
      },
      expression: "form.date1"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "date2"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.form.date2),
      callback: function($$v) {
        _vm.$set(_vm.form, "date2", $$v)
      },
      expression: "form.date2"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "代理商：",
      "prop": "agentId"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.agentId),
      callback: function($$v) {
        _vm.$set(_vm.form, "agentId", $$v)
      },
      expression: "form.agentId"
    }
  }, _vm._l((_vm.agentOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.form)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.form)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "info"
    },
    on: {
      "click": function($event) {
        _vm.exportFun(_vm.form)
      }
    }
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('div', [_c('el-row', {
    staticClass: "gridBox",
    attrs: {
      "gutter": 40,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("交易用户数：" + _vm._s(_vm._f("convert")(_vm.countList.userTotal)))]), _vm._v(" "), _c('p', [_vm._v("交易资金：" + _vm._s(_vm._f("divide")(_vm.countList.amountTotal)) + " ")])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("稳赚金用户：" + _vm._s(_vm._f("convert")(_vm.countList.financeUserAmount)))]), _vm._v(" "), _c('p', [_vm._v("稳赚金克重：" + _vm._s(_vm._f("divide")(_vm.countList.gram)) + " ")])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("金权交易用户：" + _vm._s(_vm._f("convert")(_vm.countList.goldRightUserAmount)))]), _vm._v(" "), _c('p', [_vm._v("金权交易资金：" + _vm._s(_vm._f("divide")(_vm.countList.goldRightAmount)) + " ")])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color4"
  }, [_c('p', [_vm._v("黄金用户：" + _vm._s(_vm._f("convert")(_vm.countList.goldUserAmount)))]), _vm._v(" "), _c('p', [_vm._v("买入克重：" + _vm._s(_vm._f("divide")(_vm.countList.buyGoldAmount)) + " ")]), _vm._v(" "), _c('p', [_vm._v("卖出克重：" + _vm._s(_vm._f("divide")(_vm.countList.saleGoldAmount)) + " ")])])])], 1), _vm._v(" "), _c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
    attrs: {
      "data": _vm.tableData,
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "index",
      "label": "序号",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "date",
      "label": "时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "perCount",
      "label": "交易用户",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "perAmount",
      "label": "交易金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyUserAmount",
      "label": "稳赚金用户",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gram",
      "label": "稳赚金克重",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldRightUserAmount",
      "label": "金权交易用户",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldRightAmount",
      "label": "金权交易金额",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldUserAmount",
      "label": "黄金用户",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyGoldAmount",
      "label": "黄金买入克重",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyRmbAmount",
      "label": "黄金买入金额",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "saleGoldAmount",
      "label": "黄金卖出克重",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "saleRmbAmount",
      "label": "黄金卖出金额",
      "width": "150"
    }
  })], 1), _vm._v(" "), _c('br'), _c('br'), _vm._v(" "), _c('el-pagination', {
    attrs: {
      "current-page": _vm.currentPage,
      "page-sizes": [10, 20, 30, 40],
      "page-size": _vm.pagesize,
      "layout": "total, sizes, prev, pager, next, jumper",
      "total": _vm.totalNum
    },
    on: {
      "size-change": _vm.handleSizeChange,
      "current-change": _vm.handleCurrentChange
    }
  })], 1)], 1)])
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
  }), _vm._v(" 交易管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("交易分析")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 872:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(702);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("0c25bf6c", content, true);

/***/ })

});