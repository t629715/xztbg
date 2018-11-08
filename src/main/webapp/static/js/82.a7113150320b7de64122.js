webpackJsonp([82],{

/***/ 513:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(913)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(606),
  /* template */
  __webpack_require__(826),
  /* scopeId */
  "data-v-53a7001a",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 606:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      acceptPrizeOptions: [{
        value: "",
        label: "全部"
      }, {
        value: 0,
        label: '未领取'
      }, {
        value: 1,
        label: '已领取'
      }],
      agentOptions: "",
      brokerOptions: "",
      sform: {
        userName: '',
        startTime: '',
        endTime: '',
        acceptPrize: '',
        agentName: '',
        brokerName: ''
      },
      url: this.api + "InviteFriendsToReturnHome/getSelectAll",
      agentUrl: this.api + "user/selectByAgentMessage",
      brokeUrl: this.api + "user/selectByBrokerMessage",
      brokerUrl1: this.api + "user/selectByBrokerMessage1",
      exportUrl: this.api + "InviteFriendsToReturnHome/excelAll",
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
      params.append('acceptPrize', sform.acceptPrize);
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
      params.append('acceptPrize', this.sform.acceptPrize);
      params.append('agentName', this.sform.agentName);
      params.append('brokerName', this.sform.brokerName);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          //_this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
          //_this.pagesize = response.data.data.pageSize;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          _this.tableData = list;
        }
      }).catch(function (error) {});
    },
    //根据代理商取经纪人列表
    getBrokerOptions() {
      let _this = this;
      _this.sform.brokerName = [];
      var params = new URLSearchParams();

      if (_this.sform.agentName == null || _this.sform.agentName == "") {
        _this.brokerOptions = [];
        return;
      }
      params.append('pid', _this.sform.agentName);
      axios.post(_this.brokeUrl, params).then(function (response) {
        _this.brokerOptions = response.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    //清空
    resetForm(sform) {
      let _this = this;
      this.$refs.sform.resetFields();
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
      params.append('acceptPrize', sform.acceptPrize);
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

/***/ 727:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 826:
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
      "label": "首单交易时间：",
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
      "label": "代理商：",
      "prop": "agentName"
    }
  }, [_c('el-select', {
    staticClass: "demo",
    attrs: {
      "multiple": "multiple",
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
    staticClass: "demo2",
    attrs: {
      "multiple": "multiple",
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
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.sform)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.sform)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "export"
    },
    on: {
      "click": function($event) {
        _vm.onExport(_vm.sform)
      }
    }
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-table', {
    staticStyle: {
      "width": "100%"
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
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inviterName",
      "label": "推荐人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userName",
      "label": "被推荐人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "expireTime",
      "label": "注册时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyTime",
      "label": "首笔交易时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rewardMoney",
      "label": "交易返佣金额（元）",
      "width": "180"
    }
  })], 1), _vm._v(" "), _c('el-pagination', {
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
  }), _vm._v(" 活动管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("邀友返佣详情记录")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 913:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(727);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("66a778f1", content, true);

/***/ })

});