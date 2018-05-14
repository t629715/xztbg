webpackJsonp([48],{

/***/ 520:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(768)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(581),
  /* template */
  __webpack_require__(708),
  /* scopeId */
  "data-v-11d33df4",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 581:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      lastLoginFromOptios: [{
        value: "",
        label: "全部"
      }, {
        value: 'iOS',
        label: 'iOS'
      }, {
        value: 'Android',
        label: 'Android'
      }, {
        value: 'WAP',
        label: 'WAP'
      }],
      registerFromOptions: [{
        value: "",
        label: "全部"
      }, {
        value: 'iOS',
        label: 'iOS'
      }, {
        value: 'Android',
        label: 'Android'
      }, {
        value: 'WAP',
        label: 'WAP'
      }],
      agentOptions: "",
      brokerOptions: "",
      attributionOptions: [{
        province: "全部"
      }],
      sform: {
        userName: '',
        startTime: '',
        endTime: '',
        registerFrom: '',
        registerIp: '',
        lastStartTime: '',
        lastEndTime: '',
        lastLoginFrom: '',
        agentName: '',
        brokerName: '',
        attribution: '全部'
      },
      url: "userInfo/selectByRegisterMessage",
      agentUrl: "user/selectByAgentMessage",
      brokeUrl: "user/selectByBrokerMessage",
      brokerUrl1: "user/selectByBrokerMessage1",
      exportUrl: "userInfo/excelRegisterMessage",
      editUrl: "userInfo/updateRegisterStatusById",
      provinceUrl: "userInfo/getByAttributionPro",
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
    axios.get(this.brokerUrl1).then(function (response) {
      _this.brokerOptions = response.data.data;
    }).catch(function (error) {
      console.log(error);
    });
    //初始归属地下拉列表
    axios.get(this.provinceUrl).then(function (response) {
      _this.attributionOptions = response.data.data;
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
      var date3 = '';
      var date4 = '';

      if (sform.startTime != "") {
        date1 = this.dateFormat(sform.startTime);
      }
      if (sform.endTime != "") {
        date2 = this.dateFormat(sform.endTime);
      }
      if (sform.lastStartTime != "") {
        date3 = this.dateFormat(sform.lastStartTime);
      }
      if (sform.lastEndTime != "") {
        date4 = this.dateFormat(sform.lastEndTime);
      }

      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('lastStartTime', date3);
      params.append('lastEndTime', date4);
      params.append('userName', sform.userName);
      params.append('registerFrom', sform.registerFrom);
      params.append('registerIp', sform.registerIp);
      params.append('lastLoginFrom', sform.lastLoginFrom);
      if (!"全部" == this.sform.attribution) {
        params.append('attribution', this.sform.attribution);
      }

      params.append('agentName', sform.agentName != "" ? Number(sform.agentName) : '');
      params.append('brokerName', sform.brokerName != "" ? Number(sform.brokerName) : '');

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
      if (this.sform.lastStartTime != "") {
        date3 = this.dateFormat(this.sform.lastStartTime);
      }
      if (this.sform.lastEndTime != "") {
        date4 = this.dateFormat(this.sform.lastEndTime);
      }

      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('lastStartTime', date3);
      params.append('lastEndTime', date4);
      params.append('userName', this.sform.userName);
      params.append('registerFrom', this.sform.registerFrom);
      params.append('registerIp', this.sform.registerIp);
      params.append('lastLoginFrom', this.sform.lastLoginFrom);
      if (!"全部" == this.sform.attribution) {
        params.append('attribution', this.sform.attribution);
      }
      params.append('agentName', this.sform.agentName != "" ? Number(this.sform.agentName) : '');
      params.append('brokerName', this.sform.brokerName != "" ? Number(this.sform.brokerName) : '');

      let _this = this;
      axios.post(this.url, params).then(function (response) {
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          // _this.handelData(list);
          _this.pagesize = response.data.data.pageSize;
          // _this.pageNum = response.data.data.pages;
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
      params.append('pid', Number(_this.sform.agentName));
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
      if (sform.lastStartTime != "") {
        date3 = this.dateFormat(sform.lastStartTime);
      }
      if (sform.lastEndTime != "") {
        date4 = this.dateFormat(sform.lastEndTime);
      }

      params.append('startTime', date1);
      params.append('endTime', date2);
      params.append('lastStartTime', date3);
      params.append('lastEndTime', date4);
      params.append('userName', sform.userName);
      params.append('registerFrom', sform.registerFrom);
      params.append('registerIp', sform.registerIp);
      params.append('lastLoginFrom', sform.lastLoginFrom);
      if (!"全部" == sform.attribution) {
        params.append('attribution', sform.attribution);
      }
      params.append('agentName', sform.agentName != "" ? Number(sform.agentName) : '');
      params.append('brokerName', sform.brokerName != "" ? Number(sform.brokerName) : '');

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
    handleEdit(num, row) {
      let _this = this;
      var params = new URLSearchParams();
      params.append('status', num);
      params.append('userId', row.UserID);
      axios.get(this.editUrl + '?' + params).then(function (res) {
        console.log(res);
        _this.loadData(_this.pagesize, _this.currentPage);
      }).catch(function (error) {
        console.log(error);
      });
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

/***/ 634:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-11d33df4]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-11d33df4]{border-radius:4px}.bg-purple-dark[data-v-11d33df4]{background:#99a9bf}.bg-purple[data-v-11d33df4]{background:#d3dce6}.bg-purple-light[data-v-11d33df4]{background:#e5e9f2}.grid-content[data-v-11d33df4]{border-radius:4px;min-height:36px}.row-bg[data-v-11d33df4]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 708:
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
      "size": "small",
      "maxlength": 4
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
      "label": "注册时间：",
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
      "label": "注册来源：",
      "prop": "registerFrom"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.registerFrom),
      callback: function($$v) {
        _vm.$set(_vm.sform, "registerFrom", $$v)
      },
      expression: "sform.registerFrom"
    }
  }, _vm._l((_vm.registerFromOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "注册IP：",
      "prop": "registerIp"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "maxlength": 3
    },
    model: {
      value: (_vm.sform.registerIp),
      callback: function($$v) {
        _vm.$set(_vm.sform, "registerIp", $$v)
      },
      expression: "sform.registerIp"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "登录时间：",
      "prop": "lastStartTime"
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
      value: (_vm.sform.lastStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "lastStartTime", $$v)
      },
      expression: "sform.lastStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "lastEndTime"
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
      value: (_vm.sform.lastEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "lastEndTime", $$v)
      },
      expression: "sform.lastEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "登录方式：",
      "prop": "lastLoginFrom"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.lastLoginFrom),
      callback: function($$v) {
        _vm.$set(_vm.sform, "lastLoginFrom", $$v)
      },
      expression: "sform.lastLoginFrom"
    }
  }, _vm._l((_vm.lastLoginFromOptios), function(item) {
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
      "label": "归属地：",
      "prop": "attribution"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.attribution),
      callback: function($$v) {
        _vm.$set(_vm.sform, "attribution", $$v)
      },
      expression: "sform.attribution"
    }
  }, _vm._l((_vm.attributionOptions), function(item) {
    return _c('el-option', {
      key: item.province,
      attrs: {
        "label": item.province,
        "value": item.province
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
      "width": "100",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "UserName",
      "label": "用户账号",
      "width": "180",
      "fixed": "left"
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
      "prop": "RegisterTime",
      "label": "注册时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RegisterFrom",
      "label": "注册来源",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RegisterIp",
      "label": "注册IP",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "attributionProvince",
      "label": "归属地省",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "attribution",
      "label": "归属地市",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "lastlogintime",
      "label": "最后一次登录时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "lastloginfrom",
      "label": "登录方式",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "lastfromip",
      "label": "登录IP",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "状态",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.Status == 1) ? _c('span', [_vm._v("正常")]) : _c('span', [_vm._v("禁用")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.Status == 1) ? _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.handleEdit(0, scope.row)
            }
          }
        }, [_vm._v("禁用")]) : _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.handleEdit(1, scope.row)
            }
          }
        }, [_vm._v("启用")])]
      }
    }])
  })], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-pagination', {
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
  }, [_vm._v("注册信息")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 768:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(634);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("48eb3030", content, true);

/***/ })

});