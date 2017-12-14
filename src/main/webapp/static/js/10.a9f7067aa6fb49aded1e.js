webpackJsonp([10],{

/***/ 548:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(739)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(601),
  /* template */
  __webpack_require__(687),
  /* scopeId */
  "data-v-39b40e5e",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 601:
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
      loading: false,
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
        username: ''
      },
      newformEdit: {
        username: ''
      },
      checkeAll: false,
      checkeAll1: false,
      actorList: "",
      url: "role/selectRoleUsers",
      selectRolerUrl: "permission/selectPermissions",
      editSearchUrl: 'role/selectPidByRid',
      addUrl: "role/insertRole",
      editUrl: "role/updateRole",
      deleteUrl: "user/deleteUser",
      currentPage: 0,
      pagesize: 10,
      pageNum: 1,
      totalNum: 0,
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisibleEdit: false,
      dialogFormVisibleDelete: false,
      row: "",
      formLabelWidth: '120px',
      userId: "",
      treeList: [],
      defaultProps: {
        children: 'submenu',
        label: 'text'
      },
      rules: {
        username: [{ required: true, message: '请输入角色名', trigger: 'blur' }],
        checkeAll: [{ required: true, message: '请选择菜单', trigger: 'blur' }]

      }

    };
  },
  //页面渲染加载方法
  created() {
    this.loadData(10, 1);
    let _this = this;
    axios.get(this.selectRolerUrl).then(function (response) {
      _this.treeList = response.data.data.submenu;
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
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('roleName', phoneNum);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      axios.post(this.url, params).then(function (response) {
        _this.$message({
          message: '查询成功',
          type: 'success'
        });

        _this.currentPage = response.data.pageNum;
        _this.pagesize = response.data.pageSize;
        _this.pageNum = response.data.pages;
        _this.totalNum = response.data.total;
        _this.tableData = response.data.list;
      }).catch(function (error) {});
    },
    //清空表单
    resetForm() {
      this.$refs.form.resetFields();
      this.loadData(10, 1);
    },
    //  添加用户按钮
    add() {
      this.newform = {
        username: ''
      };
      this.checkeAll = false;
      let _this = this;
      this.dialogFormVisible = true;
      //  console.log(_this.$refs.tree)
      _this.$refs.tree.setCheckedNodes([]);
    },
    handleCheckAllChange(event) {
      let _this = this;
      console.log(_this.treeList);
      if (event.target.checked) {
        this.$refs.tree.setCheckedNodes(_this.treeList);
      } else {
        this.$refs.tree.setCheckedNodes([]);
      }
    },
    handleCheckAllChange1(event) {
      let _this = this;
      console.log(_this.treeList);
      if (event.target.checked) {
        this.$refs.treeEdit.setCheckedNodes(_this.treeList);
      } else {
        this.$refs.treeEdit.setCheckedNodes([]);
      }
    },
    //  确定添加用户
    confirmAdd(formName) {
      console.log(this.$refs.tree.getCheckedKeys());
      //   console.log(this.newform.username)
      var params = new URLSearchParams();
      params.append('name', this.newform.username);
      params.append('pids', this.$refs.tree.getCheckedKeys(true));
      let _this = this;

      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$refs[formName].resetFields();
          axios.post(this.addUrl, params).then(function (response) {
            if (response.data.msg == 1) {
              _this.loadData(10, 1);
              _this.$message({
                message: '添加成功',
                type: 'success'
              });
              _this.dialogFormVisible = false;
            } else {
              _this.$message({
                message: '添加失败',
                type: 'warning'
              });
              _this.dialogFormVisible = false;
            }
          }).catch(function (error) {
            _this.dialogFormVisible = false;
            console.log(error);
          });
        } else {
          console.log('error submit!!');
        }
      });
    },
    deleDialog(index, row) {
      this.dialogFormVisibleDelete = true;
      this.row = row;
    },
    //  编辑按钮
    handleEdit(index, row) {
      // console.log(row)
      // row为行列信息
      this.newformEdit = {
        username: row.rname
      };
      this.userId = row.id;
      this.dialogFormVisibleEdit = true;
      var params = new URLSearchParams();
      params.append('rid', this.userId);
      let _this = this;
      axios.post(this.editSearchUrl, params).then(function (response) {
        _this.$refs.treeEdit.setCheckedKeys(response.data.pids);
        var listLength = 0;
        for (var i = 0; i < _this.treeList.length; i++) {
          listLength++;
          if (_this.treeList[i].submenu.length > 0) {
            for (var j = 0; j < _this.treeList[i].submenu.length; j++) {
              listLength++;
              if (_this.treeList[i].submenu[j].submenu.length > 0) {
                for (var k = 0; k < _this.treeList[i].submenu[j].submenu.length; k++) {
                  listLength++;
                }
              }
            }
          }
        }
        if (listLength == response.data.pids.length) {
          _this.checkeAll1 = true;
        } else {
          _this.checkeAll1 = false;
        }
      }).catch(function (error) {
        _this.dialogFormVisibleEdit = false;
        _this.$message.error('网络错误');
      });
      console.log(_this.$refs);
      //存储 用户角色id

    },
    //  确定编辑方法
    confirmAddEdit() {

      var params = new URLSearchParams();
      params.append('pids', this.$refs.treeEdit.getCheckedKeys());
      console.log(this.$refs.treeEdit.getCheckedKeys(true));
      params.append('name', this.newformEdit.username);
      params.append('id', this.userId);
      let _this = this;
      axios.post(this.editUrl, params).then(function (response) {
        if (response.data.msg == 1) {
          _this.$message({
            message: '修改成功',
            type: 'success'
          });
          _this.dialogFormVisibleEdit = false;
          _this.loadData(_this.pagesize, 1);
        } else if (response.data.msg == 0) {
          _this.$message({
            message: '修改失败',
            type: 'warning'
          });
          _this.dialogFormVisibleEdit = false;
        } else {
          _this.dialogFormVisibleEdit = false;
          _this.$message.error('网络错误');
        }
      }).catch(function (error) {
        _this.dialogFormVisibleEdit = false;
        _this.$message.error('网络错误');
      });
    },
    //删除
    handleDelete(index, row) {
      let _this = this;
      var params = new URLSearchParams();
      params.append('id', row.id);
      axios.post(this.deleteUrl, params).then(function (response) {
        if (response.data.msg == 1) {
          _this.$message({
            message: '删除成功',
            type: 'success'
          });
          _this.dialogFormVisibleEdit = false;
          _this.loadData(_this.pagesize, 1);
        } else if (response.data.msg == 0) {
          _this.$message({
            message: '删除失败',
            type: 'warning'
          });
          _this.dialogFormVisibleEdit = false;
        } else {
          _this.dialogFormVisibleEdit = false;
          _this.$message.error('网络错误');
        }
      }).catch(function (error) {});
    },
    //刷新表格方法
    loadData(pageSize, pageNum) {
      var params = new URLSearchParams();
      var datetime1 = '',
          datetime2 = "",
          phoneNum = "";
      if (this.form.username == undefined) {
        phoneNum = "";
      } else {
        phoneNum = this.form.username;
      }
      if (this.form.date1 != "") {
        datetime1 = Date.parse(this.form.date1);
      }
      if (this.form.date2 != '') {
        datetime2 = Date.parse(this.form.date2);
      }
      this.pagesize = pageSize;
      this.currentPage = pageNum;
      params.append('pageSize', this.pagesize);
      params.append('pageNum', this.currentPage);
      params.append('roleName', phoneNum);
      params.append('startTime', datetime1);
      params.append('endTime', datetime2);
      let _this = this;
      axios.post(this.url, params).then(function (response) {
        _this.currentPage = response.data.pageNum;
        _this.pagesize = response.data.pageSize;
        _this.pageNum = response.data.pages;
        _this.totalNum = response.data.total;
        _this.tableData = response.data.list;
      }).catch(function (error) {});
    },
    //当前页改变是执行
    handleCurrentChange(val) {
      this.loadData(this.pagesize, val);
    },
    //页数size 改变时执行
    handleSizeChange(val) {
      this.loadData(val, 1);
    }
  }
});

/***/ }),

/***/ 624:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(undefined);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 687:
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
      "label": "角色名",
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
      "label": "创建时间",
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
  })], 1)], 1), _vm._v(" "), _c('br'), _vm._v("  \n            "), _c('el-form-item', [_c('el-button', {
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
      "type": "success"
    },
    on: {
      "click": _vm.add
    }
  }, [_vm._v("新增角色")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
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
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rname",
      "label": "角色名"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userNames",
      "label": "用户列表"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rCreateTime",
      "label": "创建时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "200"
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
              _vm.handleEdit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('div', {
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
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑用户",
      "visible": _vm.dialogFormVisibleEdit,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEdit = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEdit
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户名",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.username),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "username", $$v)
      },
      expression: "newformEdit.username"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单列表",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-checkbox', {
    on: {
      "change": _vm.handleCheckAllChange1
    },
    model: {
      value: (_vm.checkeAll1),
      callback: function($$v) {
        _vm.checkeAll1 = $$v
      },
      expression: "checkeAll1"
    }
  }, [_vm._v("全选")]), _vm._v(" "), _c('el-tree', {
    ref: "treeEdit",
    attrs: {
      "data": _vm.treeList,
      "show-checkbox": "",
      "node-key": "id",
      "highlight-current": "",
      "props": _vm.defaultProps
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "small"
    },
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleEdit = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmAddEdit
    }
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "确认删除？",
      "center": "",
      "width": "30%",
      "visible": _vm.dialogFormVisibleDelete
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleDelete = $event
      }
    }
  }, [_c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "small"
    },
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleDelete = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.handleDelete
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "新增角色",
      "visible": _vm.dialogFormVisible,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisible = $event
      }
    }
  }, [_c('el-form', {
    ref: "newform",
    attrs: {
      "model": _vm.newform,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "角色名",
      "label-width": _vm.formLabelWidth,
      "prop": "username"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.username),
      callback: function($$v) {
        _vm.$set(_vm.newform, "username", $$v)
      },
      expression: "newform.username"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单列表",
      "label-width": _vm.formLabelWidth,
      "prop": "checkeAll"
    }
  }, [_c('el-checkbox', {
    on: {
      "change": _vm.handleCheckAllChange
    },
    model: {
      value: (_vm.checkeAll),
      callback: function($$v) {
        _vm.checkeAll = $$v
      },
      expression: "checkeAll"
    }
  }, [_vm._v("全选")]), _vm._v(" "), _c('el-tree', {
    ref: "tree",
    attrs: {
      "data": _vm.treeList,
      "show-checkbox": "",
      "node-key": "id",
      "highlight-current": "",
      "props": _vm.defaultProps
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "small"
    },
    on: {
      "click": function($event) {
        _vm.dialogFormVisible = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmAdd('newform')
      }
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1)])
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
  }, [_vm._v("账号管理")])])])
}]}

/***/ }),

/***/ 739:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(624);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("d7d3fdf4", content, true);

/***/ })

});