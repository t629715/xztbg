webpackJsonp([21],{

/***/ 537:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(736)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(590),
  /* template */
  __webpack_require__(684),
  /* scopeId */
  "data-v-293ed5c0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 590:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      upOrDownOptions: [{
        value: 0,
        label: '买涨'
      }, {
        value: 1,
        label: '买跌'
      }],
      brokerOptions: "",
      sform: {
        userName: '',
        orderNo: '',
        startTime: '',
        endTime: '',
        orderState: 1,
        brokerName: '',
        isUseCard: '',
        upOrDown: ''
      },
      url: "dealOrder/selectByDealOrder",
      brokeUrl: "user/selectByBrokerMessage",
      exportUrl: "dealOrder/excelDealOrderMessage",
      countUrl: "dealOrder/selectByDealOrderCount",
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
    //加载页面
    this.loadData(10, 1);
    let _this = this;
    //获取统计数据
    var params = new URLSearchParams();
    params.append('orderState', 1);
    axios.post(_this.countUrl, params).then(function (response) {
      if (response.data.code == 1001) {
        document.getElementById("bzjId").innerText = _this.amountHandle1(response.data.data.enSureAmountSum);
        // document.getElementById ("ykId").innerText = _this.amountHandle1(response.data.data.profitLossNumberCount);
        document.getElementById("ykId").innerText = Number(_this.amountHandle1(response.data.data.profitLossNumberCount) - _this.amountHandle1(response.data.data.enSureAmountSum)).toFixed(2);
        document.getElementById("jycbId").innerText = _this.amountHandle1(response.data.data.costSum);
        document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.shareAmountSum);
      }
    }).catch(function (error) {
      console.log(error);
    });
    //初始化经纪人列表
    var params1 = new URLSearchParams();
    params1.append('pid', 0);
    axios.post(_this.brokeUrl, params1).then(function (response) {
      _this.brokerOptions = response.data.data;
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

      if (sform.startTime != "") {
        date1 = this.dateFormat(sform.startTime);
      }
      if (sform.endTime != "") {
        date2 = this.dateFormat(sform.endTime);
      }

      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('userName', sform.userName);
      params.append('orderNo', sform.orderNo);
      params.append('orderState', 1);
      params.append('brokerName', sform.brokerName);
      params.append('isUseCard', this.isNotEmpty(sform.isUseCard) ? Number(sform.isUseCard) : '');
      params.append('upOrDown', sform.upOrDown);

      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          _this.$message({
            message: '查询成功',
            type: 'success'
          });
          _this.currentPage = 1;
          //_this.pagesize = response.data.data.pageSize;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          var list = response.data.data.list;
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
          document.getElementById("bzjId").innerText = _this.amountHandle1(response.data.data.enSureAmountSum);
          document.getElementById("ykId").innerText = Number(_this.amountHandle1(response.data.data.profitLossNumberCount) - _this.amountHandle1(response.data.data.enSureAmountSum)).toFixed(2);
          document.getElementById("jycbId").innerText = _this.amountHandle1(response.data.data.costSum);
          document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.shareAmountSum);
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

      if (this.sform.startTime != "") {
        date1 = this.dateFormat(this.sform.startTime);
      }
      if (this.sform.endTime != "") {
        date2 = this.dateFormat(this.sform.endTime);
      }

      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('userName', this.sform.userName);
      params.append('orderNo', this.sform.orderNo);
      params.append('orderState', 1);
      params.append('brokerName', this.sform.brokerName);
      params.append('isUseCard', this.isNotEmpty(this.sform.isUseCard) ? Number(sform.isUseCard) : '');
      params.append('upOrDown', this.sform.upOrDown);

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

          list[i].buyPreRmb = this.amountHandle1(list[i].buyPreRmb);
          list[i].buyAfterRmb = this.amountHandle1(list[i].buyAfterRmb);
          list[i].ensureAmount = this.amountHandle1(list[i].ensureAmount);
          list[i].voucherValue = this.amountHandle1(list[i].voucherValue);
          list[i].cost = this.amountHandle1(list[i].cost);

          if (this.isNotEmpty(list[i].profitLossNumber)) {
            list[i].profitLossNumber = Number(this.amountHandle1(list[i].profitLossNumber) - list[i].ensureAmount).toFixed(2);
          }

          list[i].shareAmount = this.amountHandle1(list[i].shareAmount);
          if (list[i].upOrDown == "0") {
            if (this.isNotEmpty(list[i].openPositionPrice)) {
              list[i].openPositionPrice = Number(list[i].openPositionPrice + Number(list[i].pointCount)).toFixed(2);
            }
            list[i].upOrDown = '买涨';
          } else if (list[i].upOrDown == "1") {}{
            if (this.isNotEmpty(list[i].closePositionPrice)) {
              list[i].closePositionPrice = Number(list[i].closePositionPrice + Number(list[i].pointCount)).toFixed(2);
            }
            list[i].upOrDown = '买跌';
          }
        }
      }
    },
    //清空
    resetForm(sform) {
      this.$refs.sform.resetFields();
      //获取统计数据
      let _this = this;
      var params = new URLSearchParams();
      params.append('orderState', 1);
      axios.post(_this.countUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          document.getElementById("bzjId").innerText = _this.amountHandle1(response.data.data.enSureAmountSum);
          // document.getElementById ("ykId").innerText = _this.amountHandle1(response.data.data.profitLossNumberCount);
          document.getElementById("ykId").innerText = Number(_this.amountHandle1(response.data.data.profitLossNumberCount) - _this.amountHandle1(response.data.data.enSureAmountSum)).toFixed(2);
          document.getElementById("jycbId").innerText = _this.amountHandle1(response.data.data.costSum);
          document.getElementById("syzcId").innerText = _this.amountHandle1(response.data.data.shareAmountSum);
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

      if (sform.startTime != "") {
        date1 = this.dateFormat(sform.startTime);
      }
      if (sform.endTime != "") {
        date2 = this.dateFormat(sform.endTime);
      }

      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('userName', sform.userName);
      params.append('orderNo', sform.orderNo);
      params.append('orderState', 1);
      params.append('brokerName', sform.brokerName);
      params.append('isUseCard', this.isNotEmpty(sform.isUseCard) ? Number(sform.isUseCard) : '');
      params.append('upOrDown', sform.upOrDown);

      console.info(this.exportUrl + "?" + params);
      window.location = this.exportUrl + "?" + params;
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      this.loadData(this.pagesize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
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

/***/ 621:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(undefined);
// imports


// module
exports.push([module.i, ".el-row[data-v-293ed5c0]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-293ed5c0]{border-radius:4px}.bg-purple-dark[data-v-293ed5c0]{background:#99a9bf}.bg-color1[data-v-293ed5c0]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-293ed5c0]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-293ed5c0]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-293ed5c0]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-293ed5c0]{background:#d3dce6}.bg-purple-light[data-v-293ed5c0]{background:#e5e9f2}.gridBox[data-v-293ed5c0]{padding-left:20px}.grid-content[data-v-293ed5c0]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-293ed5c0]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 684:
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
      "label": "交易订单号：",
      "prop": "orderNo"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.orderNo),
      callback: function($$v) {
        _vm.$set(_vm.sform, "orderNo", $$v)
      },
      expression: "sform.orderNo"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "建仓时间：",
      "prop": "startTime"
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
      value: (_vm.sform.startTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "startTime", $$v)
      },
      expression: "sform.startTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "endTime"
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
      value: (_vm.sform.endTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "endTime", $$v)
      },
      expression: "sform.endTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
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
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "方向：",
      "prop": "upOrDown"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.upOrDown),
      callback: function($$v) {
        _vm.$set(_vm.sform, "upOrDown", $$v)
      },
      expression: "sform.upOrDown"
    }
  }, _vm._l((_vm.upOrDownOptions), function(item) {
    return _c('el-option', {
      key: item.value,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否使用卡券：",
      "prop": "isUseCard",
      "label-width": "130px"
    }
  }, [_c('el-radio', {
    staticClass: "radio",
    attrs: {
      "label": "0"
    },
    model: {
      value: (_vm.sform.isUseCard),
      callback: function($$v) {
        _vm.$set(_vm.sform, "isUseCard", $$v)
      },
      expression: "sform.isUseCard"
    }
  }, [_vm._v("未使用")]), _vm._v(" "), _c('el-radio', {
    staticClass: "radio",
    attrs: {
      "label": "1"
    },
    model: {
      value: (_vm.sform.isUseCard),
      callback: function($$v) {
        _vm.$set(_vm.sform, "isUseCard", $$v)
      },
      expression: "sform.isUseCard"
    }
  }, [_vm._v("使用")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
  }, [_c('p', [_vm._v("保证金总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "bzjId"
    }
  })])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("盈亏总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "ykId"
    }
  })])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("交易成本总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "jycbId"
    }
  })])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color4"
  }, [_c('p', [_vm._v("收益总计（元）")]), _vm._v(" "), _c('p', {
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
      "prop": "brokerName",
      "label": "经纪人",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "orderNo",
      "label": "交易订单号",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productName",
      "label": "合约类型",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "upOrDown",
      "label": "方向",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "handNumber",
      "label": "黄金克数",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyPreRmb",
      "label": "建仓前余额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyAfterRmb",
      "label": "建仓后余额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "ensureAmount",
      "label": "合约金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "ensureAmount",
      "label": "买入金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "voucherValue",
      "label": "卡券抵扣",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cost",
      "label": "交易成本",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "openPositionPrice",
      "label": "买入点数",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "closePositionPrice",
      "label": "卖出点数",
      "width": "00"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "建仓时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "endTime",
      "label": "平仓时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "profitLossNumber",
      "label": "盈亏",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "shareAmount",
      "label": "交易分成",
      "width": "100"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "paginationBox"
  }, [_c('el-pagination', {
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
  }), _vm._v(" 合作伙伴")]), _vm._v(" "), _c('a', {
    staticClass: "tip-bottom",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("交易结算")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("金权交易")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 736:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(621);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("37af10a2", content, true);

/***/ })

});