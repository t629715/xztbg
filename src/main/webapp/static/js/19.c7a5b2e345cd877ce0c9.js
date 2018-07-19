webpackJsonp([19],{

/***/ 572:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(896)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(657),
  /* template */
  __webpack_require__(817),
  /* scopeId */
  "data-v-75848323",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 657:
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            types: [{
                id: 1,
                name: "公司"
            }, {
                id: 2,
                name: "非公司"
            }],
            actorList: "",
            actorName: "",
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
                actor1: '',
                type: ''
            },
            newformEdit: {
                username: '',
                password: '',
                actor: "",
                type: ''
            },
            url: this.api + "user/selectByUsers",
            actorUrl: this.api + "role/selectByRoleAll",
            addUrl: this.api + "user/insertUser",
            editUrl: this.api + "user/updateUser",
            deleteUrl: this.api + "user/deleteUser",
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleDelete: false,
            formLabelWidth: '120px',
            userId: "",
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                actor1: [{ required: true, message: '请选择角色', trigger: 'blur' }],
                type: [{ required: true, message: '请选择类型', trigger: 'blur' }]
            }
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        let _this = this;
        axios.get(this.actorUrl).then(function (response) {
            _this.actorList = response.data;
        }).catch(function (error) {
            self.$router.push('/login');
            console.log(error);
        });
    },
    //定义方法
    methods: {
        actorChange(value) {
            if (value != '') {
                let obj = {};
                obj = this.actorList.find(item => {
                    return item.id == value;
                });
                if (obj.name === '代理商' || obj.name === '经纪人') {
                    this.actorName = '2';
                } else {
                    this.actorName = '1';
                }
            }
        },
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
            params.append('phone', phoneNum);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            axios.post(this.url, params).then(function (response) {

                _this.$message({
                    message: '查询成功',
                    type: 'success'
                });
                _this.currentPage = response.data.data.pageNum;
                _this.pagesize = response.data.data.pageSize;
                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {
                self.$router.push('/login');
            });
        },
        //清空表单
        resetForm() {
            this.$refs.form.resetFields();
            this.loadData(10, 1);
        },
        //  添加用户按钮
        add() {
            this.newform = {
                username: '',
                password: '',
                actor1: '',
                type: ''
            };
            this.dialogFormVisible = true;
        },
        //  确定添加用户
        confirmAdd(formName) {
            var params = new URLSearchParams();
            params.append('password', this.newform.password);
            params.append('userName', this.newform.username);
            params.append("type", this.actorName);
            // params.append('phone', 132);
            params.append('rids', this.newform.actor1);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.$refs[formName].resetFields();
                    axios.post(_this.addUrl, params).then(function (response) {
                        if (response.data.data.msg == 1) {
                            _this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            _this.dialogFormVisible = false;
                            _this.loadData(_this.pagesize, 1);
                        } else if (response.data.data.msg == -1) {
                            _this.$message({
                                message: '账户已存在',
                                type: 'warning'
                            });
                            _this.dialogFormVisible = false;
                        } else {
                            _this.$message.error('网络错误');
                            _this.dialogFormVisible = false;
                        }
                    });
                }
            });
        },
        //  编辑按钮
        handleEdit(index, row) {
            // console.log(row)
            // row为行列信息
            this.newformEdit = {
                username: row.userName,
                password: "",
                actor: row.rid,
                type: row.pid != null ? 2 : 1
                //存储 用户角色id
            };this.userId = row.id;
            this.dialogFormVisibleEdit = true;
        },
        //  确定编辑方法
        confirmAddEdit() {

            var params = new URLSearchParams();
            params.append('password', this.newformEdit.password);
            params.append('userName', this.newformEdit.username);
            params.append('rids', this.newformEdit.actor);
            params.append("type", this.actorName);
            params.append('id', this.userId);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data.msg == 1) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData(_this.pagesize, 1);
                } else if (response.data.data.msg == 0) {
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
                self.$router.push('/login');
                _this.dialogFormVisibleEdit = false;
                _this.$message.error('网络错误');
            });
        },
        deleDialog(index, row) {
            this.dialogFormVisibleDelete = true;
            this.row = row;
        },
        //删除
        handleDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', _this.row.id);
            axios.post(this.deleteUrl, params).then(function (response) {
                if (response.data.data.msg == 1) {
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleDelete = false;
                    _this.loadData(_this.pagesize, 1);
                } else if (response.data.data.msg == 0) {
                    _this.$message({
                        message: '删除失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleDelete = false;
                } else {
                    _this.dialogFormVisibleDelete = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {
                self.$router.push('/login');
            });
        },
        //刷新表格方法
        loadData(pagesize, pageNum) {
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
            this.pagesize = pagesize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('phone', phoneNum);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            let _this = this;

            axios.post(this.url, params).then(function (response) {
                _this.currentPage = response.data.data.pageNum;
                _this.pagesize = response.data.data.pageSize;
                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {
                self.$router.push('/login');
            });
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

/***/ 726:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 817:
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
  })], 1)], 1), _vm._v(" "), _c('br'), _vm._v("  \n                "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.form)
      }
    }
  }, [_vm._v("查询\n                    ")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.form)
      }
    }
  }, [_vm._v("清除\n                    ")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "success"
    },
    on: {
      "click": _vm.add
    }
  }, [_vm._v("新增用户")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
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
      "prop": "userName",
      "label": "用户名",
      "width": "100"
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
        }, [_vm._v("编辑\n                        ")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("删除\n                        ")])]
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
      "title": "新增用户",
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
      "label": "用户名",
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
      "label": "密码",
      "label-width": _vm.formLabelWidth,
      "prop": "password"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.password),
      callback: function($$v) {
        _vm.$set(_vm.newform, "password", $$v)
      },
      expression: "newform.password"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "角色",
      "label-width": _vm.formLabelWidth,
      "prop": "actor1"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.actorChange($event)
      }
    },
    model: {
      value: (_vm.newform.actor1),
      callback: function($$v) {
        _vm.$set(_vm.newform, "actor1", $$v)
      },
      expression: "newform.actor1"
    }
  }, _vm._l((_vm.actorList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id + ''
      }
    })
  }))], 1)], 1), _vm._v(" "), _c('div', {
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
  }, [_vm._v("确 定\n                ")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑用户",
      "center": "",
      "width": "30%",
      "visible": _vm.dialogFormVisibleEdit
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
      "label": "密码",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.password),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "password", $$v)
      },
      expression: "newformEdit.password"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "角色",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.actorChange($event)
      }
    },
    model: {
      value: (_vm.newformEdit.actor),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "actor", $$v)
      },
      expression: "newformEdit.actor"
    }
  }, _vm._l((_vm.actorList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1)], 1), _vm._v(" "), _c('div', {
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
  }, [_vm._v("确 定")])], 1)])], 1)])
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

/***/ 896:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(726);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("7f570120", content, true);

/***/ })

});