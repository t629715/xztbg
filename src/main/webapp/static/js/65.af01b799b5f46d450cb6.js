webpackJsonp([65],{

/***/ 531:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(957)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(624),
  /* template */
  __webpack_require__(870),
  /* scopeId */
  "data-v-f5a1146c",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 624:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      stateOptions: [{
        value: "",
        label: "全部"
      }, {
        value: 1,
        label: '持有中'
      }, {
        value: 2,
        label: '已赎回'
      }],
      agentOptions: "",
      brokerOptions: "",
      sform: {
        userName: '',
        redeemStartTime: '',
        redeemEndTime: '',
        status: '2',
        agentName: '',
        brokerName: ''
      },
      url: this.api + "financeNewplayerOrder/selectByAll",
      agentUrl: this.api + "user/selectByAgentMessage",
      brokerUrl1: this.api + "user/selectByBrokerMessage1",
      exportUrl: this.api + "financeNewplayerOrder/excelAll",
      countUrl: this.api + "financeNewplayerOrder/selectByAllCount",
      currentPage: 0,
      pagesize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisibleEdit: false,
      formLabelWidth: '120px',
      userId: ""
    };
  },
  //页面渲染加载方法
  created() {
    this.loadData(10, 1);
    let _this = this;
    //初始化代理商下拉列表
    axios.get(this.agentUrl).then(function (response) {
      _this.agentOptions = response.data.data;
    }).catch(function (error) {
      console.log(error);
    });
    /*axios.get(this.brokerUrl1)
        .then(function (response) {
            _this.brokerOptions = response.data.data;
        })
        .catch(function (error) {
            console.log(error);
        });*/
    //获取统计数据
    var params = new URLSearchParams();
    params.append('status', 2);
    axios.post(this.countUrl, params).then(function (response) {
      if (response.data.code == 1001) {
        document.getElementById("lczjId").innerText = _this.amountHandle1(response.data.data.buyAmountSum);
        document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.incomeSum);
      }
    }).catch(function (error) {
      console.log(error);
    });
  },
  //定义方法
  methods: {
    //查询
    onSubmit(sform) {

      var params = new URLSearchParams();
      let _this = this;
      var date1 = '';
      var date2 = '';

      if (sform.redeemStartTime != "") {
        date1 = this.dateFormat(sform.redeemStartTime);
      }
      if (sform.redeemEndTime != "") {
        date2 = this.dateFormat(sform.redeemEndTime);
      }

      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('redeemStartTime', date1);
      params.append('redeemEndTime', date2);
      params.append('userName', sform.userName);
      params.append('status', '2');
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);

      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          _this.$message({
            message: '查询成功',
            type: 'success'
          });
          var list = response.data.data.list;
          _this.currentPage = 1;
          //_this.pagesize = response.data.data.pageSize;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          _this.handelData(list);
          _this.tableData = list;
        } else {
          _this.$message({
            message: '查询失败',
            type: 'warning'
          });
        }
      }).catch(function (error) {});

      //获取统计金额
      axios.post(this.countUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          document.getElementById("lczjId").innerText = _this.amountHandle1(response.data.data.buyAmountSum);
          document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.incomeSum);
        }
      }).catch(function (error) {
        console.log(error);
      });
    },
    //刷新表格方法
    loadData(pageSize, pageNum) {
      var params = new URLSearchParams();
      var date1 = '';
      var date2 = '';

      if (this.sform.redeemStartTime != "") {
        date1 = this.dateFormat(this.sform.redeemStartTime);
      }
      if (this.sform.redeemEndTime != "") {
        date2 = this.dateFormat(this.sform.redeemEndTime);
      }

      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('redeemStartTime', date1);
      params.append('redeemEndTime', date2);
      params.append('userName', this.sform.userName);
      params.append('status', '2');
      params.append('agentName', this.sform.agentName);
      params.append('brokerName', this.sform.brokerName);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          _this.handelData(list);
          //_this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
          //_this.pagesize = response.data.data.pageSize;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          _this.tableData = list;
        }
      }).catch(function (error) {});
    },
    //数据处理
    handelData(list) {
      if (list.length > 0) {
        for (var i = 0; i < list.length; i++) {
          list[i].buyAmount = this.amountHandle1(list[i].buyAmount);
          list[i].initialPrice = this.amountHandle1(list[i].initialPrice);
          list[i].clearPrice = this.amountHandle1(list[i].clearPrice);
          list[i].floatPercentMax = this.isNotEmpty(list[i].floatPercentMax) ? Number(list[i].floatPercentMax * 100).toFixed(2) + '%' : 0;
          //list[i].buyAmount != "" ? Number(list[i].buyAmount)/100 : 0;
          list[i].income = this.amountHandle1(list[i].income);
          //list[i].income != "" ? Number(list[i].income)/100 : 0;
          //alert(Number(list[i].yearIncomPercent*100).toFixed(2));
          list[i].yearIncomPercent = this.isNotEmpty(list[i].yearIncomPercent) ? Number(list[i].yearIncomPercent * 100).toFixed(2) + '%' : 0;
          if (list[i].status == 1) {
            list[i].status = '持有中';
          } else if (list[i].status == 2) {
            list[i].status = '已赎回';
          }
        }
      }
    },
    //根据代理商取经纪人列表
    getBrokerOptions() {
      let _this = this;
      _this.sform.brokerName = "";
      var params = new URLSearchParams();
      if (_this.sform.agentName == null || _this.sform.agentName == "") {
        _this.brokerOptions = [];
        return;
      }
      params.append('pid', _this.sform.agentName);
      axios.post(_this.brokerUrl1, { params }).then(function (response) {
        _this.brokerOptions = response.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    //清空
    resetForm(sform) {
      let _this = this;
      this.$refs.sform.resetFields();
      //获取统计数据
      var params = new URLSearchParams();
      params.append('status', 2);
      axios.post(this.countUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          document.getElementById("lczjId").innerText = _this.amountHandle1(response.data.data.buyAmountSum);
          document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.incomeSum);
        }
      }).catch(function (error) {
        console.log(error);
      });
      this.loadData(10, 1);
    },
    //导出
    onExport(sform) {
      var params = new URLSearchParams();
      let _this = this;
      var date1 = '';
      var date2 = '';

      if (sform.redeemStartTime != "") {
        date1 = this.dateFormat(sform.redeemStartTime);
      }
      if (sform.redeemEndTime != "") {
        date2 = this.dateFormat(sform.redeemEndTime);
      }

      params.append('redeemStartTime', date1);
      params.append('redeemEndTime', date2);
      params.append('userName', sform.userName);
      params.append('status', '2');
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);

      console.info(this.exportUrl + "?" + params);
      window.location = this.exportUrl + "?" + params;
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      /*this.currentPage = val;
      this.onSubmit(this.sform);*/
      this.loadData(this.pagesize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      /*this.pageSize = val;
      this.pageNum = 1;
      this.onSubmit(this.sform);*/
      this.loadData(val, 1);
    },
    //时间格式化
    dateFormat(date) {
      var dateobj = new Date(date);
      var y = dateobj.getFullYear();
      var m = dateobj.getMonth() + 1;
      m = m < 10 ? '0' + m : m;
      var d = dateobj.getDate();
      d = d < 10 ? '0' + d : d;
      var h = dateobj.getHours();
      h = h < 10 ? '0' + h : h;
      var minute = dateobj.getMinutes();
      var second = dateobj.getSeconds();
      minute = minute < 10 ? '0' + minute : minute;
      second = second < 10 ? '0' + second : second;
      return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    }
  }
});

/***/ }),

/***/ 771:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-f5a1146c]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-f5a1146c]{border-radius:4px}.bg-purple-dark[data-v-f5a1146c]{background:#99a9bf}.bg-color1[data-v-f5a1146c]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-f5a1146c]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-f5a1146c]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-f5a1146c]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-f5a1146c]{background:#d3dce6}.bg-purple-light[data-v-f5a1146c]{background:#e5e9f2}.gridBox[data-v-f5a1146c]{padding-left:20px}.grid-content[data-v-f5a1146c]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-f5a1146c]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 870:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('el-form', {
    ref: "sform",
    attrs: {
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.sform,
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户账号：",
      "prop": "userName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.userName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "userName", $$v)
      },
      expression: "sform.userName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回时间：",
      "prop": "redeemStartTime"
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
      value: (_vm.sform.redeemStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "redeemStartTime", $$v)
      },
      expression: "sform.redeemStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "redeemEndTime"
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
      value: (_vm.sform.redeemEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "redeemEndTime", $$v)
      },
      expression: "sform.redeemEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "代理商：",
      "prop": "agentName"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerOptions()
      }
    },
    model: {
      value: (_vm.sform.agentName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "agentName", $$v)
      },
      expression: "sform.agentName"
    }
  }, _vm._l((_vm.agentOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "经纪人：",
      "prop": "brokerName"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.brokerName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "brokerName", $$v)
      },
      expression: "sform.brokerName"
    }
  }, _vm._l((_vm.brokerOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('br'), _vm._v("    \n        "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.sform)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.sform)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "info"
    },
    on: {
      "click": function($event) {
        _vm.onExport(_vm.sform)
      }
    }
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-row', {
    staticClass: "gridBox",
    attrs: {
      "gutter": 40,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color1"
  }, [_c('p', [_vm._v("理财赎回总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "lczjId"
    }
  })])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("收益支出总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "syzcId"
    }
  })])])], 1), _vm._v(" "), _c('el-table', {
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
      "width": "100",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userName",
      "label": "用户账号",
      "width": "180",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "registerTime",
      "label": "注册时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "agentName",
      "label": "代理商",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "brokerName",
      "label": "经纪人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "orderNo",
      "label": "交易订单号",
      "width": "250"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productNo",
      "label": "产品编号",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productName",
      "label": "产品名称",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "yearIncomPercent",
      "label": "收益率",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "floatPercentMax",
      "label": "最大浮动收益率",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "initialPrice",
      "label": "买入价",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "clearPrice",
      "label": "结算价",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gram",
      "label": "买入克重",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyAmount",
      "label": "买入金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyTime",
      "label": "买入时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "redeemTime",
      "label": "赎回时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "状态",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "income",
      "label": "收益支出",
      "width": "100"
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
  })], 1)])
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
  }, [_vm._v("新手理财交易")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 957:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(771);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("03371110", content, true);

/***/ })

});