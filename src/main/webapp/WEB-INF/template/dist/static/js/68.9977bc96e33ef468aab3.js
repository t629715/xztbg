webpackJsonp([68],{

/***/ 524:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(895)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(614),
  /* template */
  __webpack_require__(811),
  /* scopeId */
  "data-v-45ba24ab",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 614:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      bzhOptios: [{
        value: "",
        label: "全部"
      }, {
        value: '0',
        label: '否'
      }, {
        value: '1',
        label: '是'
      }],
      agentOptions: "",
      brokerOptions: "",
      sform: {
        userName: '',
        // startTime: '',
        // endTime: '',
        agentName: '',
        brokerName: '',
        regStartTime: '',
        regEndTime: '',
        bzh: ''
      },
      url: this.api + "standardUser/selectByStandardUser",
      agentUrl: this.api + "user/selectByAgentMessage",
      brokeUrl: this.api + "user/selectByBrokerMessage",
      brokerUrl1: this.api + "user/selectByBrokerMessage1",
      exportUrl: this.api + "standardUser/excelStandardUser",
      currentPage: 1,
      pagesize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisibleEdit: false,
      formLabelWidth: '120px',
      userId: "",
      loading2: false
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
  },
  //定义方法
  methods: {
    changeAgent() {
      let _this = this;
      var params = new URLSearchParams();
      params.append("pid", _this.sform.agentName);
      axios.get(_this.brokerUrl1, { params }).then(function (res) {
        _this.brokerOptions = res.data.data;
      });
    },
    //查询
    onSubmit(sform) {

      var params = new URLSearchParams();
      let _this = this;
      _this.loading2 = true;
      var date1 = '';
      var date2 = '';
      var date3 = '';
      var date4 = '';

      if (sform.startTime != "") {
        date1 = this.dateFormat(sform.startTime);
      }
      if (sform.endTime != "") {
        date2 = this.dateFormat(sform.endTime);
      }
      if (sform.regStartTime != "") {
        date3 = this.dateFormat(sform.regStartTime);
      }
      if (sform.regEndTime != "") {
        date4 = this.dateFormat(sform.regEndTime);
      }

      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('regStartTime', date3);
      params.append('regEndTime', date4);
      params.append('bzh', sform.bzh);
      params.append('userName', sform.userName);
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);

      axios.post(this.url, params).then(function (response) {
        _this.loading2 = false;
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
          _this.tableData = list;
        } else {
          _this.$message({
            message: '查询失败',
            type: 'warning'
          });
        }
      }).catch(function (error) {});
    },
    //刷新表格方法
    loadData(pageSize, pageNum) {
      var params = new URLSearchParams();
      var date1 = '';
      var date2 = '';
      var date3 = '';
      var date4 = '';

      if (this.sform.startTime != "") {
        date1 = this.dateFormat(this.sform.startTime);
      }
      if (this.sform.endTime != "") {
        date2 = this.dateFormat(this.sform.endTime);
      }
      if (this.sform.regStartTime != "") {
        date3 = this.dateFormat(this.sform.regStartTime);
      }
      if (this.sform.regEndTime != "") {
        date4 = this.dateFormat(this.sform.regEndTime);
      }

      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('regStartTime', date3);
      params.append('regEndTime', date4);
      params.append('bzh', this.sform.bzh);
      params.append('userName', this.sform.userName);
      params.append('agentName', this.sform.agentName);
      params.append('brokerName', this.sform.brokerName);

      let _this = this;
      _this.loading2 = true;
      axios.post(this.url, params).then(function (response) {
        _this.loading2 = false;
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          //_this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
          //_this.pagesize = response.data.data.pageSize;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          _this.tableData = response.data.data.list;
        }
      }).catch(function (error) {});
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
      params.append('pid', Number(_this.sform.agentName));
      axios.post(_this.brokerUrl1, params).then(function (response) {
        _this.brokerOptions = response.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    //清空
    resetForm(sform) {
      // let _this = this;
      this.$refs.sform.resetFields();
      this.loadData(10, 1);
    },
    //导出
    onExport(sform) {
      var params = new URLSearchParams();
      let _this = this;
      var date1 = '';
      var date2 = '';
      var date3 = '';
      var date4 = '';

      if (sform.startTime != "") {
        date1 = this.dateFormat(sform.startTime);
      }
      if (sform.endTime != "") {
        date2 = this.dateFormat(sform.endTime);
      }
      if (sform.regStartTime != "") {
        date3 = this.dateFormat(sform.regStartTime);
      }
      if (sform.regEndTime != "") {
        date4 = this.dateFormat(sform.regEndTime);
      }

      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('regStartTime', date3);
      params.append('regEndTime', date4);
      params.append('bzh', sform.bzh);
      params.append('userName', sform.userName);
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);
      window.open(this.exportUrl + "?" + params);
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

/***/ 715:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-45ba24ab]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-45ba24ab]{border-radius:4px}.bg-purple-dark[data-v-45ba24ab]{background:#99a9bf}.bg-color1[data-v-45ba24ab]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-45ba24ab]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-45ba24ab]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-45ba24ab]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-45ba24ab]{background:#d3dce6}.bg-purple-light[data-v-45ba24ab]{background:#e5e9f2}.gridBox[data-v-45ba24ab]{padding-left:20px}.grid-content[data-v-45ba24ab]{height:110px;border-radius:4px;min-height:110px;text-align:center;font-size:14px}.row-bg[data-v-45ba24ab]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 811:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loading2),
      expression: "loading2"
    }],
    staticClass: "container-fluid",
    attrs: {
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgba(0, 0, 0, 0.8)"
    }
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
      "label": "标准户：",
      "prop": "bzh"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.bzh),
      callback: function($$v) {
        _vm.$set(_vm.sform, "bzh", $$v)
      },
      expression: "sform.bzh"
    }
  }, _vm._l((_vm.bzhOptios), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
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
      "prop": "brokerName",
      "label-width": _vm.formLabelWidth
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
      "label": "注册时间：",
      "prop": "regStartTime"
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
      value: (_vm.sform.regStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "regStartTime", $$v)
      },
      expression: "sform.regStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "regEndTime"
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
      value: (_vm.sform.regEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "regEndTime", $$v)
      },
      expression: "sform.regEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('br'), _vm._v("     \n        "), _c('el-form-item', [_c('el-button', {
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
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "UserName",
      "label": "用户账号",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RegisterTime",
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
      "prop": "RealName",
      "label": "真实姓名",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "bzh",
      "label": "是否标准户",
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
  }), _vm._v(" 客户管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("标准户统计分析")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 895:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(715);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("298aa3ec", content, true);

/***/ })

});