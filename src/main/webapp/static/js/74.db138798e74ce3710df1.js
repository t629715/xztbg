webpackJsonp([74],{

/***/ 520:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(873)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(612),
  /* template */
  __webpack_require__(786),
  /* scopeId */
  "data-v-04a1ac83",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 612:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      agentOptions: "",
      brokerOptions: "",
      sform: {
        startTime: '',
        endTime: '',
        type: 1,
        agentName: ''
      },
      url: this.api + "userInfo/getByUserAttribute",
      agentUrl: this.api + "user/selectByAgentMessage",
      brokeUrl: this.api + "user/selectByBrokerMessage",
      exportUrl: this.api + "userInfo/excelByUserAttribute",
      countUrl: this.api + "userInfo/getByUserAttributeCount",
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
    //获取统计数据
    var params = new URLSearchParams();
    params.append('type', _this.sform.type);
    axios.post(this.countUrl, params).then(function (response) {
      if (response.data.code == 1001) {
        document.getElementById("zNum").innerText = response.data.data[0].zNum;
        document.getElementById("dlNum").innerText = response.data.data[0].dlNum;
        document.getElementById("nNum").innerText = response.data.data[0].nNum;
        document.getElementById("nvNum").innerText = response.data.data[0].nvNum;
        document.getElementById("wzNum").innerText = response.data.data[0].wzNum;
        document.getElementById("azNum").innerText = response.data.data[0].azNum;
        document.getElementById("pgNum").innerText = response.data.data[0].pgNum;
        document.getElementById("qtNum").innerText = response.data.data[0].qtNum;
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
      params.append('type', sform.type);
      params.append('agentName', sform.agentName);

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

      //获取统计
      axios.post(this.countUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          document.getElementById("zNum").innerText = response.data.data[0].zNum;
          document.getElementById("dlNum").innerText = response.data.data[0].dlNum;
          document.getElementById("nNum").innerText = response.data.data[0].nNum;
          document.getElementById("nvNum").innerText = response.data.data[0].nvNum;
          document.getElementById("wzNum").innerText = response.data.data[0].wzNum;
          document.getElementById("azNum").innerText = response.data.data[0].azNum;
          document.getElementById("pgNum").innerText = response.data.data[0].pgNum;
          document.getElementById("qtNum").innerText = response.data.data[0].qtNum;
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
      params.append('type', this.sform.type);
      params.append('agentName', this.sform.agentName);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
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
    //清空
    resetForm(sform) {
      let _this = this;
      this.$refs.sform.resetFields();
      this.sform.type = '1';
      //获取统计数据
      var params = new URLSearchParams();
      params.append('type', this.sform.type);
      axios.post(this.countUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          document.getElementById("zNum").innerText = response.data.data[0].zNum;
          document.getElementById("dlNum").innerText = response.data.data[0].dlNum;
          document.getElementById("nNum").innerText = response.data.data[0].nNum;
          document.getElementById("nvNum").innerText = response.data.data[0].nvNum;
          document.getElementById("wzNum").innerText = response.data.data[0].wzNum;
          document.getElementById("azNum").innerText = response.data.data[0].azNum;
          document.getElementById("pgNum").innerText = response.data.data[0].pgNum;
          document.getElementById("qtNum").innerText = response.data.data[0].qtNum;
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
      params.append('type', sform.type);
      params.append('agentName', sform.agentName);

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

/***/ 689:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-04a1ac83]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-04a1ac83]{border-radius:4px}.bg-purple-dark[data-v-04a1ac83]{background:#99a9bf}.bg-color1[data-v-04a1ac83]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-04a1ac83]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-04a1ac83]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-04a1ac83]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-04a1ac83]{background:#d3dce6}.bg-purple-light[data-v-04a1ac83]{background:#e5e9f2}.gridBox[data-v-04a1ac83]{padding-left:20px}.grid-content[data-v-04a1ac83]{height:110px;border-radius:4px;min-height:110px;text-align:center;font-size:14px}.row-bg[data-v-04a1ac83]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 786:
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
      "label": "时间：",
      "prop": "radio1"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.type),
      callback: function($$v) {
        _vm.$set(_vm.sform, "type", $$v)
      },
      expression: "sform.type"
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
      "label": "自定义：",
      "prop": "startTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "date",
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
      "type": "date",
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
    attrs: {
      "size": "small",
      "placeholder": "请选择"
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
  }))], 1), _vm._v(" "), _c('br'), _vm._v("     \n        "), _c('el-form-item', [_c('el-button', {
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
  }, [_c('p', [_vm._v("总用户："), _c('span', {
    attrs: {
      "id": "zNum"
    }
  })]), _vm._v(" "), _c('p', [_vm._v("登录用户："), _c('span', {
    attrs: {
      "id": "dlNum"
    }
  })])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("男："), _c('span', {
    attrs: {
      "id": "nNum"
    }
  })]), _vm._v(" "), _c('p', [_vm._v("女："), _c('span', {
    attrs: {
      "id": "nvNum"
    }
  })]), _vm._v(" "), _c('p', [_vm._v("性别未知："), _c('span', {
    attrs: {
      "id": "wzNum"
    }
  })])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("苹果："), _c('span', {
    attrs: {
      "id": "pgNum"
    }
  })]), _vm._v(" "), _c('p', [_vm._v("安卓："), _c('span', {
    attrs: {
      "id": "azNum"
    }
  })]), _vm._v(" "), _c('p', [_vm._v("其他："), _c('span', {
    attrs: {
      "id": "qtNum"
    }
  })])])])], 1), _vm._v(" "), _c('el-table', {
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
      "label": "日期",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "zNum",
      "label": "总用户",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "dlNum",
      "label": "登录用户",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "nNum",
      "label": "男",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "nvNum",
      "label": "女",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "wzNum",
      "label": "性别未确定",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "pgNum",
      "label": "苹果用户",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "azNum",
      "label": "安卓用户",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "qtNum",
      "label": "其他",
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
  }), _vm._v(" 客户管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("用户属性")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 873:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(689);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("3a4282f8", content, true);

/***/ })

});