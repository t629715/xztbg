webpackJsonp([83],{

/***/ 581:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(672),
  /* template */
  __webpack_require__(804),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 672:
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
    //model 初始数据
    data() {
        return {
            loading: true,
            cacheRegionOptions: [{
                value: 0,
                label: 'Local(map)'
            }, {
                value: 1,
                label: 'Remote(redis)'
            }],
            formForQuery: {
                paramName: ""
            },
            formModufyData: {
                paramName: "",
                paramValue: "",
                valueType: "",
                description: "",
                cacheRegion: ""
            },
            rules: {
                paramName: [{ required: true, message: '请填写参数名', trigger: 'blur' }],
                paramValue: [{ required: true, message: '请填写参数值', trigger: 'blur' }]
            },
            url: this.api + "configParam/selectByAll",
            addUrl: this.api + "configParam/add",
            modifyUrl: this.api + "configParam/edit",
            deleteUrl: this.api + "configParam/delete",
            currentPage: 1,
            pageSize: 10,
            pageNum: 1,
            totalCount: 0,
            totalPage: 0,
            tableData: [],
            formLabelWidth: '120px',
            paramName: "",
            dialogForDelete: false,
            dialogForEdit: false,
            dialogForAdd: false
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(1, 10);
    },
    //定义方法
    methods: {
        onSubmit() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('pageSize', _this.pageSize);
            params.append('pageNum', _this.currentPage);
            params.append('paramName', this.formForQuery.paramName);

            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: response.data.msg,
                        type: 'success'
                    });
                    var pageInfo = response.data.data;
                    _this.currentPage = pageInfo.pageNum == 0 ? 1 : pageInfo.pageNum;
                    _this.pageSize = pageInfo.pageSize;
                    _this.totalPage = pageInfo.pages;
                    _this.tableData = pageInfo.list;
                    _this.totalCount = pageInfo.total;
                } else {
                    _this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});
        },
        //刷新表格方法
        loadData(pageNum, pageSize) {
            var params = new URLSearchParams();
            params.append('pageSize', pageSize);
            params.append('pageNum', pageNum);
            params.append('paramName', this.formForQuery.paramName);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    var pageInfo = response.data.data;
                    _this.currentPage = pageInfo.pageNum == 0 ? 1 : pageInfo.pageNum;
                    _this.pageSize = pageInfo.pageSize;
                    _this.totalPage = pageInfo.pages;
                    _this.tableData = pageInfo.list;
                    _this.totalCount = pageInfo.total;
                } else {
                    /*_this.$router.push("/login")*/
                }
            }).catch(function (error) {});
        },
        //清空
        resetForm() {
            this.$refs.formForQuery.resetFields();
            this.loadData(1, this.pageSize);
        },
        addForm() {
            this.formModufyData = {
                paramName: "",
                paramValue: "",
                valueType: "",
                description: "",
                cacheRegion: ""
            }, this.dialogForAdd = true;
        },
        /*编辑*/
        editDialog(row) {
            this.formModufyData = {
                paramName: row.paramName,
                paramValue: row.paramValue,
                valueType: row.valueType,
                description: row.description,
                cacheRegion: row.cacheRegion
            }, this.dialogForEdit = true;
        },

        /*删除*/
        deleteDialog(row) {
            this.paramName = row.paramName, this.dialogForDelete = true;
        },
        modify(formName) {
            let _this = this;
            var params = new URLSearchParams();
            params.append("paramName", this.formModufyData.paramName);
            params.append("paramValue", this.formModufyData.paramValue);
            params.append("valueType", this.formModufyData.valueType);
            params.append("description", this.formModufyData.description);
            var reg = /^[0-9]*$/g;
            if (reg.test(this.formModufyData.cacheRegion)) {
                params.append("cacheRegion", this.formModufyData.cacheRegion);
            }

            this.$refs[formName].validate(valid => {
                if (valid) {
                    axios.post(this.modifyUrl, params).then(function (res) {
                        if (res.data.code == 1001) {
                            _this.$message({
                                message: res.data.msg,
                                type: "success"
                            });
                            _this.loadData(_this.currentPage, _this.pageSize);
                            _this.dialogForEdit = false;
                        } else {
                            _this.$message({
                                message: res.data.msg,
                                type: "warning"
                            });
                        }
                    });
                    _this.dialogForEdit = false;
                }
            });
        },
        add(formName) {
            let _this = this;
            var params = new URLSearchParams();
            params.append("paramName", this.formModufyData.paramName);
            params.append("paramValue", this.formModufyData.paramValue);
            params.append("valueType", this.formModufyData.valueType);
            params.append("description", this.formModufyData.description);
            params.append("cacheRegion", this.formModufyData.cacheRegion);
            this.$refs[formName].validate(valid => {
                if (valid) {
                    axios.post(this.addUrl, params).then(function (res) {
                        if (res.data.code == 1001) {
                            _this.$message({
                                message: res.data.msg,
                                type: "success"
                            });
                            _this.loadData(_this.currentPage, _this.pageSize);
                            _this.dialogForAdd = false;
                        } else {
                            _this.$message({
                                message: res.data.msg,
                                type: "warning"
                            });
                        }
                    });
                    _this.dialogForAdd = false;
                }
            });
        },

        confirmForDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("paramName", _this.paramName);
            axios.post(this.deleteUrl, params).then(function (res) {
                if (res.data.code == 1001) {
                    _this.$message({
                        message: res.data.msg,
                        type: "success"
                    });
                    _this.loadData(_this.currentPage, _this.pageSize);
                    _this.dialogForDelete = false;
                } else {
                    _this.$message({
                        message: res.data.msg,
                        type: "warning"
                    });
                }
            });
            _this.dialogForDelete = false;
        },

        //当前页改变是执行
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadData(val, this.pageSize);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.loadData(this.currentPage, val);
        }

    }
});

/***/ }),

/***/ 804:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('div', [_c('el-form', {
    ref: "formForQuery",
    attrs: {
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.formForQuery,
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "参数名称：",
      "prop": "paramName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.formForQuery.paramName),
      callback: function($$v) {
        _vm.$set(_vm.formForQuery, "paramName", $$v)
      },
      expression: "formForQuery.paramName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit()
      }
    }
  }, [_vm._v("查询\n                    ")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm()
      }
    }
  }, [_vm._v("清除\n                    ")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.addForm()
      }
    }
  }, [_vm._v("添加\n                    ")])], 1)], 1)], 1), _vm._v(" "), _c('div', [_c('el-table', {
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
      "prop": "paramName",
      "label": "参数名称",
      "width": "250",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "paramValue",
      "label": "参数值",
      "width": "450"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "valueType",
      "label": "参数值类型",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cacheRegion",
      "label": "缓存区域",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "description",
      "label": "参数描述",
      "width": "450"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "200",
      "fixed": "right"
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
              _vm.editDialog(scope.row)
            }
          }
        }, [_vm._v("\n                            编辑\n                        ")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(scope.row)
            }
          }
        }, [_vm._v("\n                            删除\n                        ")])]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('div', [_c('el-dialog', {
    attrs: {
      "title": "删除",
      "visible": _vm.dialogForDelete,
      "width": "30",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogForDelete = $event
      }
    }
  }, [_c('span', [_vm._v("确定要删除该参数吗？")]), _vm._v(" "), _c('div', {
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
        _vm.dialogForDelete = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmForDelete
    }
  }, [_vm._v("确 定\n                    ")])], 1)])], 1), _vm._v(" "), _c('div', [_c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogForEdit,
      "width": "30",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogForEdit = $event
      }
    }
  }, [_c('el-form', {
    ref: "newformEdit",
    attrs: {
      "model": _vm.formModufyData,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "参数名称：",
      "label-width": _vm.formLabelWidth,
      "prop": "paramName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off",
      "maxlength": 60
    },
    model: {
      value: (_vm.formModufyData.paramName),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "paramName", $$v)
      },
      expression: "formModufyData.paramName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数值：",
      "label-width": _vm.formLabelWidth,
      "prop": "paramValue"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 250
    },
    model: {
      value: (_vm.formModufyData.paramValue),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "paramValue", $$v)
      },
      expression: "formModufyData.paramValue"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数值类型：",
      "label-width": _vm.formLabelWidth,
      "prop": "valueType"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 30
    },
    model: {
      value: (_vm.formModufyData.valueType),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "valueType", $$v)
      },
      expression: "formModufyData.valueType"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "缓存区域：",
      "label-width": _vm.formLabelWidth,
      "prop": "cacheRegion"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.formModufyData.cacheRegion),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "cacheRegion", $$v)
      },
      expression: "formModufyData.cacheRegion"
    }
  }, _vm._l((_vm.cacheRegionOptions), function(item) {
    return _c('el-option', {
      key: item.value,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数描述：",
      "label-width": _vm.formLabelWidth,
      "prop": "description"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 250
    },
    model: {
      value: (_vm.formModufyData.description),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "description", $$v)
      },
      expression: "formModufyData.description"
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
        _vm.dialogForEdit = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.modify('newformEdit')
      }
    }
  }, [_vm._v("提交\n                    ")])], 1)], 1)], 1), _vm._v(" "), _c('div', [_c('el-dialog', {
    attrs: {
      "title": "添加",
      "visible": _vm.dialogForAdd,
      "width": "30",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogForAdd = $event
      }
    }
  }, [_c('el-form', {
    ref: "newformAdd",
    attrs: {
      "model": _vm.formModufyData,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "参数名称：",
      "label-width": _vm.formLabelWidth,
      "prop": "paramName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 60
    },
    model: {
      value: (_vm.formModufyData.paramName),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "paramName", $$v)
      },
      expression: "formModufyData.paramName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数值：",
      "label-width": _vm.formLabelWidth,
      "prop": "paramValue"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 250
    },
    model: {
      value: (_vm.formModufyData.paramValue),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "paramValue", $$v)
      },
      expression: "formModufyData.paramValue"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数值类型：",
      "label-width": _vm.formLabelWidth,
      "prop": "valueType"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 30
    },
    model: {
      value: (_vm.formModufyData.valueType),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "valueType", $$v)
      },
      expression: "formModufyData.valueType"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "缓存区域：",
      "label-width": _vm.formLabelWidth,
      "prop": "cacheRegion"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.formModufyData.cacheRegion),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "cacheRegion", $$v)
      },
      expression: "formModufyData.cacheRegion"
    }
  }, _vm._l((_vm.cacheRegionOptions), function(item) {
    return _c('el-option', {
      key: item.value,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "参数描述：",
      "label-width": _vm.formLabelWidth,
      "prop": "description"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 250
    },
    model: {
      value: (_vm.formModufyData.description),
      callback: function($$v) {
        _vm.$set(_vm.formModufyData, "description", $$v)
      },
      expression: "formModufyData.description"
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
        _vm.dialogForAdd = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.add('newformAdd')
      }
    }
  }, [_vm._v("提交\n                    ")])], 1)], 1)], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('div', [_c('el-pagination', {
    attrs: {
      "current-page": _vm.currentPage,
      "page-sizes": [10, 20, 30, 40],
      "page-size": _vm.pageSize,
      "layout": "total, sizes, prev, pager, next, jumper",
      "total": _vm.totalCount
    },
    on: {
      "size-change": _vm.handleSizeChange,
      "current-change": _vm.handleCurrentChange
    }
  })], 1)])])
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
  }), _vm._v("\n            系统管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("参数维护")])])])
}]}

/***/ })

});