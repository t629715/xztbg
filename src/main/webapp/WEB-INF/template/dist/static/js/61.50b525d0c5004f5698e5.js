webpackJsonp([61],{

/***/ 529:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(859)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(616),
  /* template */
  __webpack_require__(777),
  /* scopeId */
  "data-v-11df420c",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 616:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      agentOptions: "",
      brokerOptions: "",
      sform: {
        userName: '',
        agentName: '',
        brokerName: ''
      },
      url: this.api + "inOutGold/selectByInOutGold",
      agentUrl: this.api + "user/selectByAgentMessage",
      brokeUrl: this.api + "user/selectByBrokerMessage",
      exportUrl: this.api + "inOutGold/excelInOutGold",
      currentPage: 0,
      pageSize: 10,
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
  },
  //定义方法
  methods: {
    //查询
    onSubmit(sform) {
      var params = new URLSearchParams();
      let _this = this;

      params.append('userName', sform.userName);
      params.append('pageSize', this.pageSize);
      params.append('pageNum', this.currentPage);
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);

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
    loadData(pagesize, pageNum) {
      var params = new URLSearchParams();

      this.pageSize = pagesize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pageSize);
      params.append('pageNum', this.currentPage);
      params.append('agentName', this.sform.agentName);
      params.append('brokerName', this.sform.brokerName);

      let _this = this;
      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          // _this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
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
      _this.sform.brokerName = "";
      var params = new URLSearchParams();
      params.append('pid', _this.sform.agentName);
      axios.post(_this.brokeUrl, params).then(function (response) {
        _this.brokerOptions = response.data.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    //清空
    resetForm(sform) {
      this.$refs.sform.resetFields();
      this.loadData(10, 1);
    },
    //导出
    onExport(sform) {
      var params = new URLSearchParams();
      let _this = this;

      params.append('userName', sform.userName);
      params.append('agentName', sform.agentName);
      params.append('brokerName', sform.brokerName);

      console.info(this.exportUrl + "?" + params);
      window.location = this.exportUrl + "?" + params;
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      /*this.currentPage = val;
      this.onSubmit(this.sform);*/
      this.loadData(this.pageSize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      /*this.pageSize = val;
      this.pageNum = 1;
      this.onSubmit(this.sform);*/
      this.loadData(val, 1);
    }
  }
});

/***/ }),

/***/ 685:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 777:
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
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
      "width": "100",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realName",
      "label": "姓名",
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
      "prop": "rj",
      "label": "入金",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cj",
      "label": "出金",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "jrj",
      "label": "净入金",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rmb",
      "label": "账户余额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalIncome",
      "label": "黄金收益",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "tj",
      "label": "提金",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "lc",
      "label": "理财",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "hjb",
      "label": "黄金资产买入",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "hjs",
      "label": "黄金资产卖出",
      "width": "180"
    }
  })], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-pagination', {
    attrs: {
      "current-page": _vm.currentPage,
      "page-sizes": [10, 20, 30, 40],
      "page-size": _vm.pageSize,
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
  }), _vm._v(" 出入金管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("出入金查询")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 859:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(685);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("4a694e1e", content, true);

/***/ })

});