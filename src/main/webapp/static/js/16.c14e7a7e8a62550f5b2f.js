webpackJsonp([16],{

/***/ 581:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(914)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(671),
  /* template */
  __webpack_require__(830),
  /* scopeId */
  "data-v-6e2c0540",
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
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      newform: {
        username: '',
        password: '',
        actor: ''
      },
      newformEdit: {
        username: '',
        password: '',
        actor: ""
      },
      url: this.api + "user/selectByUsers",
      actorUrl: this.api + "role/selectByRoleAll",
      addUrl: this.api + "user/insertUser",
      editUrl: this.api + "user/updateUser",
      deleteUrl: this.api + "user/deleteUser",
      currentPage: 0,
      pageSize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      formLabelWidth: '120px',
      userId: ""
    };
  },
  //页面渲染加载方法
  created() {
    this.loadData(10, 1);
    let _this = this;
    axios.get(this.actorUrl).then(function (response) {
      _this.actorList = response.data;
    }).catch(function (error) {
      console.log(error);
    });
  },
  //定义方法
  methods: {
    //查询方法
    onSubmit(form) {
      var params = new URLSearchParams();
      let _this = this;
      var datetime1 = '',
          datetime2 = "",
          phoneNum = "";
      if (form.username == undefined) {
        phoneNum = "";
      } else {
        phoneNum = form.username;
      }
      if (form.date1 != "") {
        datetime1 = Date.parse(form.date1);
      }
      if (form.date2 != '') {
        datetime2 = Date.parse(form.date2);
      }
      params.append('pageSize', _this.pageSize);
      params.append('pageNum', _this.currentPage);
      params.append('phone', phoneNum);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      axios.post(this.url, params).then(function (response) {

        _this.$message({
          message: '查询成功',
          type: 'success'
        });
        _this.tableData = response.data.list;
      }).catch(function (error) {});
    },
    //清空表单
    resetForm() {
      this.$refs.form.resetFields();
    },
    //刷新表格方法
    loadData(pageSize, pageNum) {
      var params = new URLSearchParams();
      params.append('pageSize', pageSize);
      params.append('pageNum', pageNum);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
        // _this.currentPage =response.data.pageNum
        // _this.pageSize =response.data.pageSize
        _this.pageNum = response.data.pages;
        _this.totalNum = response.data.total;
        _this.tableData = response.data.list;
      }).catch(function (error) {});
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      this.currentPage = val;
      this.loadData(this.pageSize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      this.currentPage = val;
      this.loadData(val, 1);
    }
  }
});

/***/ }),

/***/ 734:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 830:
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
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户名",
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
      "label": "操作时间",
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
      "label": "用户名",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "password",
      "label": "密码",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rname",
      "label": "角色",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "创建时间",
      "width": "180"
    }
  })], 1), _vm._v(" "), _c('el-pagination', {
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
  }), _vm._v(" 系统管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("日志信息")])])])
}]}

/***/ }),

/***/ 914:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(734);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("2aa406c4", content, true);

/***/ })

});