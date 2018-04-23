webpackJsonp([29],{

/***/ 541:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(791)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(602),
  /* template */
  __webpack_require__(731),
  /* scopeId */
  "data-v-5c3823c8",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 602:
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
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loading: true,
            operatorList: "",
            form: {
                title: '',
                state: '',
                operator: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            readoneform: {
                title: '',
                imagePath: '',
                content: ''
            },
            newform: {
                title: '',
                content: '',
                imagePath: '',
                contentFrom: '',
                informationFrom: '',
                contentFromType: ''

            },
            newformEdit: {
                title: '',
                imagePath: '',
                content: '',
                contentFrom: '',
                informationFrom: '',
                contentFromType: ''
            },
            rules: {
                title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
                content: [{ required: true, message: '请输入内容地址', trigger: 'blur' }]
                /*skipPath:[
                    {required:true, message:'请输入跳转地址', trigger:'blur'}
                ]*/
            },
            url: "infoNotice/seelctAll",
            addUrl: "infoNotice/add",
            editUrl: "infoNotice/edit",
            deleteUrl: "infoNotice/delete",
            operatorListUrl: "infoNotice/getOperators",
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisibleRead: false,
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleDelete: false,
            formLabelWidth: '120px',
            infoId: "",
            imagepath: "",
            content: "",
            informationFrom: '',
            types: [{
                id: 1,
                name: "站内"
            }, {
                id: 2,
                name: "站外"
            }]
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        let _this = this;
        axios.get(this.operatorListUrl).then(function (response) {

            _this.operatorList = response.data.data;
        }).catch(function (error) {
            console.log(error);
        });
    },
    methods: {

        //查询方法
        onSubmit(form) {
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                title = "",
                operator = "";
            if (form.title == undefined) {
                title = "";
            } else {
                title = form.title;
            }
            if (form.date1 != "") {
                datetime1 = Date.parse(form.date1);
            }
            if (form.date2 != '') {
                datetime2 = Date.parse(form.date2);
            }
            if (form.operator != '') {
                operator = form.operator;
            }
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('title', title);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('operator', operator);
            axios.post(this.url, params).then(function (response) {

                _this.$message({
                    message: '查询成功',
                    type: 'success'
                });
                _this.currentPage = response.data.pageNum;
                _this.pageSize = response.data.pageSize;
                _this.pageNum = response.data.pages;
                _this.totalNum = response.data.total;
                _this.tableData = response.data.list;
            }).catch(function (error) {});
        },
        //清空表单
        resetForm() {
            this.$refs.form.resetFields();
        },
        resetForm1(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible = false;
        },
        deleteDialog(index, row) {
            this.dialogFormVisibleDelete = true;
            this.row = row;
        },
        jdState(value) {
            if (value.state == 1) return "已发布";
            if (value.state == 0) return "已创建";
            if (value.state == -1) return "已下线";
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            params.append('pageSize', pageSize);
            params.append('pageNum', pageNum);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                _this.currentPage = response.data.pageNum;
                _this.pageSize = response.data.pageSize;
                _this.pageNum = response.data.pages;
                _this.totalNum = response.data.total;
                _this.tableData = response.data.list;
            }).catch(function (error) {});
        },

        //  添加用户按钮
        add() {
            this.newform = {
                title: '',
                content: ''
                /*skipPath: '',*/
                /*skipType: '',*/
            };
            this.dialogFormVisible = true;
        },
        //  确定添加用户
        confirmAdd(formName) {
            const self = this;
            var params = new URLSearchParams();
            params.append('title', this.newform.title);
            params.append('content', this.newform.content);
            /*params.append('skipPath', this.newform.skipPath);*/
            /*params.append('skipType', this.newform.skipType);*/
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.$refs[formName].resetFields();
                    axios.post(this.addUrl, params).then(function (response) {
                        if (response.data.msg == 1) {
                            _this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            _this.dialogFormVisible = false;
                            _this.loadData(_this.pageSize, 1);
                        } else if (response.data.msg == 0) {
                            _this.$message({
                                message: '添加失败',
                                type: 'warning'
                            });
                            _this.dialogFormVisible = false;
                        } else if (response.data.msg == -1) {
                            self.$router.push('/login');
                        }
                    });
                } else {
                    console.log('error submit!!');
                }
            });
        },

        //删除
        handleDelete() {
            const self = this;
            let _this = this;
            var params = new URLSearchParams();
            params.append('serialNo', _this.row.serialNo);
            axios.post(this.deleteUrl, params).then(function (response) {
                if (response.data.msg == 1) {
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleDelete = false;
                    _this.loadData(_this.pageSize, _this.currentPage);
                } else if (response.data.msg == 0) {
                    _this.$message({
                        message: '删除失败',
                        type: 'warning'
                    });

                    _this.dialogFormVisibleEdit = false;
                } else if (response.data.msg == -1) {
                    _this.dialogFormVisibleEdit = false;
                    self.$router.push('/login');
                    _this.$message.error('请登录');
                }
            }).catch(function (error) {});
        },
        readOneForm(index, row) {
            this.readoneform = {
                title: row.title,
                /*skipType: (row.contentFromType == 1 ? "站内" : "站外"),
                skipPath: row.skipPath,*/
                content: row.content
            };
            this.dialogFormVisibleRead = true;
        },

        //  编辑按钮
        handleEdit(index, row) {
            this.newformEdit = {
                title: row.title,
                /*skipPath: row.skipPath,*/
                content: row.content
                /*skipType: row.skipType*/

                //存储 理财产品id
            };this.serialNo = row.serialNo;
            this.dialogFormVisibleEdit = true;
        },

        //  确定编辑方法
        confirmAddEdit() {
            const self = this;
            var params = new URLSearchParams();
            params.append('title', this.newformEdit.title);
            /*params.append('skipPath', this.newformEdit.skipPath);*/
            params.append('content', this.newformEdit.content);
            /*params.append('skipType', this.newformEdit.skipType);*/
            params.append('serialNo', this.serialNo);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.msg == 1) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData(_this.pageSize, 1);
                } else if (response.data.msg == 0) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit = false;
                } else if (response.data.msg == -1) {
                    _this.dialogFormVisibleEdit = false;
                    self.$router.push('/login');
                    _this.$message.error('请登录');
                }
            });
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            this.loadData(this.pageSize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.loadData(val, 1);
        }
    }
});

/***/ }),

/***/ 657:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 731:
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
      "label": "标题",
      "prop": "title"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.title),
      callback: function($$v) {
        _vm.$set(_vm.form, "title", $$v)
      },
      expression: "form.title"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "发布时间",
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
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "发布人",
      "prop": "operator"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.operator),
      callback: function($$v) {
        _vm.$set(_vm.form, "operator", $$v)
      },
      expression: "form.operator"
    }
  }, _vm._l((_vm.operatorList), function(item) {
    return _c('el-option', {
      key: item.operator,
      attrs: {
        "label": item.operator,
        "value": item.operator
      }
    })
  }))], 1), _vm._v(" "), _c('br'), _vm._v("   \n                "), _c('el-form-item', [_c('el-button', {
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
  }, [_vm._v("发布")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
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
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "title",
      "label": "标题",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "创建时间",
      "width": "240"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "operator",
      "label": "发布人",
      "width": "240"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "300"
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
              _vm.readOneForm(scope.$index, scope.row)
            }
          }
        }, [_vm._v("查看")]), _vm._v(" "), _c('el-button', {
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
              _vm.deleteDialog(scope.$index, scope.row)
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
      "page-size": _vm.pageSize,
      "layout": "total, sizes, prev, pager, next, jumper",
      "total": _vm.totalNum
    },
    on: {
      "size-change": _vm.handleSizeChange,
      "current-change": _vm.handleCurrentChange
    }
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "预览",
      "visible": _vm.dialogFormVisibleRead,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleRead = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.readoneform
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "标题",
      "prop": _vm.readoneform.title,
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.readoneform.title),
      callback: function($$v) {
        _vm.$set(_vm.readoneform, "title", $$v)
      },
      expression: "readoneform.title"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "内容地址",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.readoneform.content),
      callback: function($$v) {
        _vm.$set(_vm.readoneform, "content", $$v)
      },
      expression: "readoneform.content"
    }
  })], 1)], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "发布",
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
      "label": "标题",
      "label-width": _vm.formLabelWidth,
      "prop": "title"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.title),
      callback: function($$v) {
        _vm.$set(_vm.newform, "title", $$v)
      },
      expression: "newform.title"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "内容地址",
      "label-width": _vm.formLabelWidth,
      "prop": "content"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.content),
      callback: function($$v) {
        _vm.$set(_vm.newform, "content", $$v)
      },
      expression: "newform.content"
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
        _vm.resetForm1('newform')
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
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEdit,
      "width": "30%",
      "center": ""
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
      "label": "标题",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.title),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "title", $$v)
      },
      expression: "newformEdit.title"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "内容地址",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.content),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "content", $$v)
      },
      expression: "newformEdit.content"
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
      "width": "30%",
      "center": "",
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
  }), _vm._v(" 运营管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("资讯管理")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 791:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(657);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("3ccef178", content, true);

/***/ })

});