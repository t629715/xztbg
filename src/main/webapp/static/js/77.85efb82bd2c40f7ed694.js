webpackJsonp([77],{

/***/ 518:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(901)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(611),
  /* template */
  __webpack_require__(814),
  /* scopeId */
  "data-v-3dcee9c7",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 611:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      loading: true,
      agentOptions: "",
      brokerOptions: "",
      testList: [],
      sform: {
        realName: '',
        endTime: '',
        startTime: '',
        userName: ''
      },
      url: this.api + "userInfo/selectByRealNameAuth",
      cerfiUrl: this.api + "userInfo/certification",
      currentPage: 1,
      pagesize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      userId: "",
      dialogVisible1: false,
      dialogVisible2: false,
      IDCardNum: "",
      IDCardBackNum: "",
      IDCardPath: "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4210212233,3046822529&fm=27&gp=0.jpg",
      IDCardBackPath: "http://f.hiphotos.baidu.com/image/h%3D300/sign=80b7d9714e10b912a0c1f0fef3fffcb5/42a98226cffc1e17c72412fb4390f603728de965.jpg"
    };
  },
  //页面渲染加载方法
  created() {
    this.loadData(10, 1);
  },
  //定义方法
  methods: {
    //背面

    lookImg1(row) {
      this.dialogVisible1 = true;
      this.IDCardPath = row.IDCardPath;
      // alert(row.IDCard);
      this.IDCardNum = row.IDCard;
    },
    lookImg2(row) {
      console.log(row.IDCardBackPath);
      this.dialogVisible2 = true;
      this.IDCardBackPath = row.IDCardBackPath;
      this.IDCardBackNum = row.IDCard;
    },
    //查询
    onSubmit(sform) {
      var params = new URLSearchParams();
      var date1 = '';
      var date2 = '';
      if (this.sform.startTime != "") {
        date1 = this.dateFormat(this.sform.startTime);
      }
      if (this.sform.endTime != "") {
        date2 = this.dateFormat(this.sform.endTime);
      }
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('applyTimeStart', date1);
      params.append('applyTimeEnd', date2);
      params.append('userName', this.sform.userName);
      params.append('realName', this.sform.realName);
      let _this = this;
      axios.get(this.url + "?" + params).then(function (response) {
        if (response.data.code == 1001) {
          _this.$message({
            message: '查询成功',
            type: 'success'
          });
          var list = response.data.data.list;
          _this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
          _this.pagesize = response.data.data.pageSize;
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
      params.append('applyTimeStart', date1);
      params.append('applyTimeEnd', date2);
      params.append('userName', this.sform.userName);
      params.append('realName', this.sform.realName);
      let _this = this;
      axios.get(this.url + "?" + params).then(function (response) {
        if (response.data.code == 1001) {
          var list = response.data.data.list;
          _this.pageNum = response.data.data.pages;
          _this.totalNum = response.data.data.total;
          _this.tableData = list;
        }
      }).catch(function (error) {});
    },

    //清空
    resetForm(sform) {
      this.$refs.sform.resetFields();
      this.loadData(this.pagesize, 1);
    },

    //当前页改变是执行
    handleCurrentChange(val) {
      this.loadData(this.pagesize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      this.loadData(val, 1);
    },
    editDialog(num, row) {
      var params = new URLSearchParams();
      params.append('userId', row.UserID);
      params.append('type', num);
      params.append('IDCard', row.IDCard);
      params.append('userId', row.UserID);
      let _this = this;
      axios.get(this.cerfiUrl + "?" + params).then(function (res) {
        if (res.data.code == 1000) {
          _this.$message({
            message: '操作成功',
            type: 'success'
          });
          _this.loadData(_this.pagesize, _this.currentPage);
        } else {
          _this.$message({
            message: '审核失败',
            type: 'warning'
          });
        }
      }).catch(function (error) {});
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
exports.push([module.i, ".el-row[data-v-3dcee9c7]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-3dcee9c7]{border-radius:4px}.bg-purple-dark[data-v-3dcee9c7]{background:#99a9bf}.bg-purple[data-v-3dcee9c7]{background:#d3dce6}.bg-purple-light[data-v-3dcee9c7]{background:#e5e9f2}.grid-content[data-v-3dcee9c7]{border-radius:4px;min-height:36px}.row-bg[data-v-3dcee9c7]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 814:
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
      "label": "用户姓名：",
      "prop": "realName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.realName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "realName", $$v)
      },
      expression: "sform.realName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "申请时间：",
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
  })], 1)], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
  }, [_vm._v("清除")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "userName",
      "label": "用户账号",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RealName",
      "label": "姓名",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "IDCard",
      "label": "身份证号码",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "身份证图片（正）",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.lookImg1(scope.row)
            }
          }
        }, [_vm._v("点击查看")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "accessTime",
      "label": "身份证图片（反）",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.lookImg2(scope.row)
            }
          }
        }, [_vm._v("点击查看")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RealNameAuthApplyTime",
      "label": "申请时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.editDialog(1, scope.row)
            }
          }
        }, [_vm._v("通过")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.editDialog(-1, scope.row)
            }
          }
        }, [_vm._v("不通过")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "查看图片",
      "visible": _vm.dialogVisible1
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogVisible1 = $event
      }
    }
  }, [_c('img', {
    staticStyle: {
      "width": "50%",
      "min-height": "100px",
      "margin": "0 auto",
      "display": "block"
    },
    attrs: {
      "src": _vm.IDCardPath,
      "alt": "无法查看"
    }
  }), _vm._v(" "), _c('span', [_vm._v("身份证号：")]), _c('span', [_vm._v(_vm._s(_vm.IDCardNum))])]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "查看图片",
      "visible": _vm.dialogVisible2
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogVisible2 = $event
      }
    }
  }, [_c('img', {
    staticStyle: {
      "width": "50%",
      "min-height": "100px",
      "margin": "0 auto",
      "display": "block"
    },
    attrs: {
      "src": _vm.IDCardBackPath,
      "alt": "无法查看"
    }
  }), _vm._v(" "), _c('span', [_vm._v("身份证号：")]), _c('span', [_vm._v(_vm._s(_vm.IDCardBackNum))])]), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-pagination', {
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
      "href": "javascript:;"
    }
  }, [_c('i', {
    staticClass: "icon-home"
  }), _vm._v(" 客户管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("实名认证")])])])
}]}

/***/ }),

/***/ 901:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(715);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("14d0780c", content, true);

/***/ })

});