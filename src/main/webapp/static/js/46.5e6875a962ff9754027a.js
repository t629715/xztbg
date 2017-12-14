webpackJsonp([46],{

/***/ 511:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(742)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(564),
  /* template */
  __webpack_require__(690),
  /* scopeId */
  "data-v-4226ab70",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 564:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      actorList: "",
      form: {
        username: '',
        region: '',
        date1: '',
        date2: '',
        brokerName: "",
        agentsName: "",
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      brokerOptions: [],
      agentOptions: [],
      url: "userInfo/selectByAccountMessage",
      agentUrl: "user/selectByAgentMessage",
      brokerUrl: "user/selectByBrokerMessage",
      exportUrl: "userInfo/excelAccountMessage",
      countUrl: "userInfo/selectAccountCount",
      currentPage: 0,
      pagesize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      formLabelWidth: '120px',
      userId: "",
      countList: ""
    };
  },
  //页面渲染加载方法
  filters: {
    divide: function (value) {
      if (value != "" && value != null) {
        return (Number(value) / 100).toFixed(2);
      } else {
        return 0;
      }
      /*value = value/100;
      return value;*/
    }
  },
  created() {
    this.loadData(10, 1);
    this.countNum();
    let _this = this;
    //初始化代理商下拉列表
    axios.get(this.agentUrl).then(function (response) {
      _this.agentOptions = response.data.data;
    }).catch(function (error) {
      console.log(error);
    });
  },
  //定义方法
  methods: {
    //根据代理商取经纪人列表
    getBrokerOptions() {
      let _this = this;
      _this.form.brokerName = "";
      var params = new URLSearchParams();
      params.append('pid', _this.form.agentName);
      axios.post(_this.brokerUrl, params).then(function (response) {
        _this.brokerOptions = response.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    countNum() {
      var params = new URLSearchParams();
      let _this = this;
      var datetime1 = '',
          datetime2 = "",
          username = "",
          brokerName = "",
          agentsName = "";
      if (this.form.username != undefined) {
        username = this.form.username;
      }
      if (this.form.date1 != "") {
        datetime1 = this.dateFormat(this.form.date1);
      }
      if (this.form.date2 != '') {
        datetime2 = this.dateFormat(this.form.date2);
      }
      if (this.form.brokerName != undefined) {
        brokerName = this.form.brokerName;
      }
      console.log(this.form.agentName);
      if (this.form.agentName != undefined) {
        agentsName = this.form.agentName;
      }
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('userName', username);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      params.append('brokerName', brokerName);
      params.append('agentsName', agentsName);
      axios.get(this.countUrl + '?' + params).then(function (res) {
        console.log(res.data.data);
        _this.countList = res.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    //查询方法
    onSubmit(form) {
      this.countNum();
      var params = new URLSearchParams();
      let _this = this;
      var datetime1 = '',
          datetime2 = "",
          username = "",
          brokerName = "",
          agentsName = "";
      if (this.form.username != undefined) {
        username = this.form.username;
      }
      if (this.form.date1 != "") {
        datetime1 = this.dateFormat(this.form.date1);
      }
      if (this.form.date2 != '') {
        datetime2 = this.dateFormat(this.form.date2);
      }
      if (this.form.brokerName != undefined) {
        brokerName = this.form.brokerName;
      }
      console.log(this.form.agentName);
      if (this.form.agentName != undefined) {
        agentsName = this.form.agentName;
      }
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('userName', username);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      params.append('brokerName', brokerName);
      params.append('agentsName', agentsName);
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
          // _this.handelData(list);
          _this.tableData = list;
        } else {
          _this.$message({
            message: '查询失败',
            type: 'warning'
          });
        }
      }).catch(function (error) {});
    },
    //清空表单
    resetForm() {
      this.$refs.form.resetFields();
      this.loadData(10, 1);
      this.countNum();
    },
    exportFun() {
      var params = new URLSearchParams();
      let _this = this;
      var datetime1 = '',
          datetime2 = "",
          username = "",
          brokerName = "",
          agentsName = "";
      if (this.form.username != undefined) {
        username = this.form.username;
      }
      if (this.form.date1 != "") {
        datetime1 = this.dateFormat(this.form.date1);
      }
      if (this.form.date2 != '') {
        datetime2 = this.dateFormat(this.form.date2);
      }
      if (this.form.brokerName != undefined) {
        brokerName = this.form.brokerName;
      }
      if (this.form.agentName != undefined) {
        agentsName = this.form.agentName;
      }
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('userName', username);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      params.append('brokerName', brokerName);
      params.append('agentsName', agentsName);
      console.log(this.exportUrl + "?" + params);
      window.location = this.exportUrl + "?" + params;
    },
    //刷新表格方法
    loadData(pageSize, pageNum) {
      var params = new URLSearchParams();
      var datetime1 = '',
          datetime2 = "",
          username = "",
          brokerName = "",
          agentsName = "";
      if (this.form.username == undefined) {
        username = "";
      } else {
        username = this.form.username;
      }
      if (this.form.brokerName == undefined) {
        brokerName = "";
      } else {
        brokerName = this.form.brokerName;
      }
      if (this.form.agentsName == undefined) {
        agentsName = "";
      } else {
        agentsName = this.form.agentsName;
      }
      if (this.form.date1 != "") {
        datetime1 = this.dateFormat(this.form.date1);
      }
      if (this.form.date2 != '') {
        datetime2 = this.dateFormat(this.form.date2);
      }
      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('userName', username);
      params.append('brokerName', brokerName);
      params.append('agentsName', agentsName);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
        _this.totalNum = response.data.data.total;
        _this.pageNum = response.data.data.pages;
        _this.tableData = response.data.data.list;
      }).catch(function (error) {});
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      // this.pageNum=val;
      // this.onSubmit(this.form)
      this.loadData(this.pagesize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      this.loadData(val, 1);
    }
  }
});

/***/ }),

/***/ 627:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(undefined);
// imports


// module
exports.push([module.i, ".el-row[data-v-4226ab70]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-4226ab70]{border-radius:4px}.bg-purple-dark[data-v-4226ab70]{background:#99a9bf}.bg-color1[data-v-4226ab70]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-4226ab70]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-4226ab70]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-4226ab70]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-4226ab70]{background:#d3dce6}.bg-purple-light[data-v-4226ab70]{background:#e5e9f2}.gridBox[data-v-4226ab70]{padding-left:20px}.grid-content[data-v-4226ab70]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-4226ab70]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 690:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('div', {
    staticClass: "formBox"
  }, [_c('el-form', {
    ref: "form",
    attrs: {
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.form,
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户账号",
      "prop": "username"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.username),
      callback: function($$v) {
        _vm.$set(_vm.form, "username", $$v)
      },
      expression: "form.username"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "代理商",
      "prop": "agentName",
      "label-width": _vm.formLabelWidth
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
      value: (_vm.form.agentName),
      callback: function($$v) {
        _vm.$set(_vm.form, "agentName", $$v)
      },
      expression: "form.agentName"
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
      "label": "经纪人",
      "prop": "brokerName",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.brokerName),
      callback: function($$v) {
        _vm.$set(_vm.form, "brokerName", $$v)
      },
      expression: "form.brokerName"
    }
  }, _vm._l((_vm.brokerOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "注册时间",
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
  })], 1)], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
      "click": _vm.exportFun
    }
  }, [_vm._v("导出")])], 1)], 1)], 1), _vm._v(" "), _c('el-row', {
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
  }, [_c('p', [_vm._v("人民币余额总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm._f("divide")(_vm.countList.rmbSum)))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("人民币冻结总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm._f("divide")(_vm.countList.frozenRmbSum)))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("人民币理财总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm._f("divide")(_vm.countList.finaceSum)))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color4"
  }, [_c('p', [_vm._v("黄金（克）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.countList.goldSum))])])])], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    attrs: {
      "data": _vm.tableData,
      "fit": "",
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
      "prop": "RealName",
      "label": "姓名",
      "fixed": "left",
      "width": "100"
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
      "prop": "idcard",
      "label": "身份证号码",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "银行卡",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rmb ",
      "label": "人民币余额",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n              " + _vm._s(_vm._f("divide")(scope.row.rmb)) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "人民币冻结",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n              " + _vm._s(_vm._f("divide")(scope.row.frozenRmb)) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "人民币理财",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n              " + _vm._s(_vm._f("divide")(scope.row.finance)) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "利息",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n              " + _vm._s(_vm._f("divide")(scope.row.totalIncome)) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gold",
      "label": "黄金",
      "width": "180"
    }
  })], 1)], 1), _vm._v(" "), _c('br'), _c('br'), _vm._v(" "), _c('div', {
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
  }), _vm._v(" 客户管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("账户信息")])])])
}]}

/***/ }),

/***/ 742:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(627);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("27d612ad", content, true);

/***/ })

});