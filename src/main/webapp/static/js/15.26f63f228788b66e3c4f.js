webpackJsonp([15],{

/***/ 577:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(905)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(662),
  /* template */
  __webpack_require__(826),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 662:
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
            url: this.api + "usersPermission/selectByAll",
            addUrl: this.api + "usersPermission/add",
            modifyUrl: this.api + "usersPermission/edit",
            deleteUrl: this.api + "usersPermission/delete",
            tableData: [],
            tableData1: [],
            formLabelWidth: '120px',
            firstShow: false,
            secondShow: false,
            dialogForDelete: false,
            dialogForDelete: false,
            dialogForEditChild: false,
            dialogForAdd: false,
            dialogForCheckChild: false,
            dialogForEditParent: false,
            dialogForCheckParent: false,
            permissionId: "",

            formForAdd: {
                id: "",
                text: "",
                title: "",
                type: "",
                sref: "",
                firstPid: "",
                secondPid: ""
            },
            formForModify: {
                id: "",
                text: "",
                title: "",
                type: "",
                sref: "",
                firstPid: "",
                secondPid: ""
            },

            typeList: [{
                id: 1,
                name: '一级菜单'
            }, {
                id: 2,
                name: '二级菜单'
            }, {
                id: 3,
                name: '三级菜单'
            }],

            expandsIds: [],
            expandsParentNames: []
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData();
    },
    //定义方法
    methods: {
        getRowKeys(row) {
            return row.id;
        },
        toggleRowExpansion(row) {
            if (row == null) {
                this.expandsIds = [];
            } else {
                this.expandsIds = [];
                this.expandsIds.push(row.id);
            }
        },
        // 选择菜单的级别并控制一级菜单输入框与二级菜单输入框是否显示
        selectType(value) {
            if (value == 2) {
                this.firstShow = true;
                this.secondShow = false;
            } else if (value == 3) {
                this.firstShow = true;
                this.secondShow = true;
            } else if (value == 1) {
                this.firstShow = false;
                this.secondShow = false;
            }
        },
        // 通过选择一级菜单获取二级菜单
        selectSecondByFirst(value) {
            let obj = {};
            obj = this.tableData.find(item => {
                return item.id == value;
            });
            this.tableData1 = obj.subs;
        },
        //刷新表格方法
        loadData() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.tableData = response.data.data.subs;
                } else {
                    /*_this.$router.push("/login")*/
                }
            }).catch(function (error) {});
        },
        addForm() {
            this.formForAdd = {
                id: "",
                text: "",
                title: "",
                type: "",
                sref: "",
                firstPid: "",
                secondPid: ""
            };

            this.dialogForAdd = true;
        },

        modify() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('text', this.formForModify.text);
            params.append('translate', this.formForModify.text);
            params.append('sref', this.formForModify.sref);
            params.append('pid', this.formForModify.pid);
            params.append("id", this.id);
            axios.post(this.modifyUrl, params).then(function (response) {
                if (response.data.code == 1000) {
                    _this.dialogForEditChild = false;
                    _this.dialogForEditParent = false;
                    _this.loadData();
                    _this.$message({
                        message: response.data.msg,
                        type: 'success'
                    });
                } else {
                    _this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});
        },
        add() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('text', this.formForAdd.text);
            params.append('translate', this.formForAdd.text);
            if (this.formForAdd.type == 1) {
                params.append('pid', 0);
            } else if (this.formForAdd.type == 2) {
                params.append('pid', this.formForAdd.firstPid);
            } else if (this.formForAdd.type == 3) {
                params.append('pid', this.formForAdd.secondPid);
            }
            params.append('type', this.formForAdd.type);
            params.append('sref', this.formForAdd.sref);
            params.append('icon', "el-icon-date");
            axios.post(this.addUrl, params).then(function (response) {
                if (response.data.code == 1000) {
                    _this.firstShow = false;
                    _this.secondShow = false;
                    _this.dialogForAdd = false;
                    _this.loadData();
                    _this.$message({
                        message: response.data.msg,
                        type: 'success'
                    });
                } else {
                    _this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});
        },
        editDialog(row) {
            this.formForModify = {
                text: row.title,
                sref: row.sref,
                pid: row.pid
            };
            this.id = row.id;
            this.dialogForEditParent = true;
        },
        /*删除*/
        deleteDialog(row) {
            this.id = row.id, this.dialogForDelete = true;
        },

        confirmForDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("id", _this.id);
            axios.post(this.deleteUrl, params).then(function (res) {
                if (res.data.code == 1000) {
                    _this.$message({
                        message: "删除成功",
                        type: "success"
                    });
                    _this.loadData();
                    _this.dialogForDelete = false;
                } else {
                    _this.$message({
                        message: "操作失败",
                        type: "warning"
                    });
                }
            });
            _this.dialogForDelete = false;
        }
    }
});

/***/ }),

/***/ 735:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-table__body tr:nth-child(n) td{padding:0!important;border-spacing:0}.demo-table-expand-2 tr:nth-child(n),.out-table tr:nth-child(2n){background-color:#eef1f6}.demo-table-expand-1 tr:nth-child(n){background-color:#fff}", ""]);

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
  }, [_c('div', [_c('el-form', {
    attrs: {
      "inline": true,
      "label-width": "100px"
    }
  }, [_c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.addForm()
      }
    }
  }, [_vm._v("添加\n                    ")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.loadData()
      }
    }
  }, [_vm._v("刷新\n                    ")])], 1)], 1)], 1), _vm._v(" "), _c('div', [_c('el-table', {
    staticClass: "out-table",
    attrs: {
      "data": _vm.tableData,
      "border": "",
      "row-key": _vm.getRowKeys,
      "expand-row-keys": _vm.expandsIds
    },
    on: {
      "current-change": _vm.toggleRowExpansion
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "expand"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(props) {
        return [_c('el-table', {
          class: props.$index % 2 == 1 ? 'demo-table-expand-1' : 'demo-table-expand-2',
          attrs: {
            "data": props.row.subs,
            "show-header": false,
            "label-position": "center",
            "inline": "",
            "border": false
          }
        }, [_c('el-table-column', {
          attrs: {
            "label": "",
            "width": "149"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "type": "expand"
          },
          scopedSlots: _vm._u([{
            key: "default",
            fn: function(props) {
              return [_c('el-table', {
                class: props.$index % 2 == 1 ? 'demo-table-expand-1' : 'demo-table-expand-2',
                attrs: {
                  "data": props.row.subs,
                  "show-header": false,
                  "label-position": "center",
                  "inline": "",
                  "border": false
                }
              }, [_c('el-table-column', {
                attrs: {
                  "label": "",
                  "width": "346"
                }
              }), _vm._v(" "), _c('el-table-column', {
                attrs: {
                  "type": "index",
                  "label": "序号",
                  "width": "150"
                }
              }), _vm._v(" "), _c('el-table-column', {
                attrs: {
                  "prop": "title",
                  "label": "菜单名",
                  "width": "150"
                }
              }), _vm._v(" "), _c('el-table-column', {
                attrs: {
                  "prop": "sref",
                  "label": "菜单路由",
                  "width": "250"
                }
              }), _vm._v(" "), _c('el-table-column', {
                attrs: {
                  "label": "操作",
                  "width": "199"
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
                    }, [_vm._v("\n                                                        编辑\n                                                    ")]), _vm._v(" "), _c('el-button', {
                      attrs: {
                        "size": "small",
                        "type": "danger"
                      },
                      on: {
                        "click": function($event) {
                          _vm.deleteDialog(scope.row)
                        }
                      }
                    }, [_vm._v("\n                                                        删除\n                                                    ")])]
                  }
                }])
              })], 1)]
            }
          }])
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "type": "index",
            "label": "序号",
            "width": "150"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "title",
            "label": "二级菜单名",
            "width": "150"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "",
            "label": "三级菜单名",
            "width": "150"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "sref",
            "label": "菜单路由",
            "width": "250"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "label": "操作",
            "width": "199"
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
              }, [_vm._v("\n                                            编辑\n                                        ")]), _vm._v(" "), _c('el-button', {
                attrs: {
                  "size": "small",
                  "type": "danger"
                },
                on: {
                  "click": function($event) {
                    _vm.deleteDialog(scope.row)
                  }
                }
              }, [_vm._v("\n                                            删除\n                                        ")])]
            }
          }])
        })], 1)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "type": "index",
      "label": "序号",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "title",
      "label": "一级菜单名",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "",
      "label": "二级菜单名",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "",
      "label": "三级菜单名",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "index",
      "label": "菜单路由",
      "width": "250"
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
  }, [_c('span', [_vm._v("确定要删除该信息吗？")]), _vm._v(" "), _c('div', {
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
      "title": "修改菜单",
      "visible": _vm.dialogForEditParent,
      "width": "30",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogForEditParent = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.formForModify
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "菜单名",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.text),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "text", $$v)
      },
      expression: "formForModify.text"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单路由",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.sref),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "sref", $$v)
      },
      expression: "formForModify.sref"
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
        _vm.dialogForEditParent = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.modify
    }
  }, [_vm._v("提 交\n                    ")])], 1)], 1)], 1), _vm._v(" "), _c('div', [_c('el-dialog', {
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
    attrs: {
      "model": _vm.formForAdd
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "菜单级别",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.selectType
    },
    model: {
      value: (_vm.formForAdd.type),
      callback: function($$v) {
        _vm.$set(_vm.formForAdd, "type", $$v)
      },
      expression: "formForAdd.type"
    }
  }, _vm._l((_vm.typeList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "value": item.id,
        "label": item.name
      }
    })
  }))], 1), _vm._v(" "), (_vm.firstShow || _vm.secondShow) ? _c('el-form-item', {
    attrs: {
      "label": "一级菜单",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.selectSecondByFirst
    },
    model: {
      value: (_vm.formForAdd.firstPid),
      callback: function($$v) {
        _vm.$set(_vm.formForAdd, "firstPid", $$v)
      },
      expression: "formForAdd.firstPid"
    }
  }, _vm._l((_vm.tableData), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "value": item.id,
        "label": item.title
      }
    })
  }))], 1) : _vm._e(), _vm._v(" "), (_vm.secondShow) ? _c('el-form-item', {
    attrs: {
      "label": "二级菜单",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.formForAdd.secondPid),
      callback: function($$v) {
        _vm.$set(_vm.formForAdd, "secondPid", $$v)
      },
      expression: "formForAdd.secondPid"
    }
  }, _vm._l((_vm.tableData1), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "value": item.id,
        "label": item.title
      }
    })
  }))], 1) : _vm._e(), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单名",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForAdd.text),
      callback: function($$v) {
        _vm.$set(_vm.formForAdd, "text", $$v)
      },
      expression: "formForAdd.text"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单路由",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForAdd.sref),
      callback: function($$v) {
        _vm.$set(_vm.formForAdd, "sref", $$v)
      },
      expression: "formForAdd.sref"
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
      "click": _vm.add
    }
  }, [_vm._v("提 交\n                    ")])], 1)], 1)], 1)])])
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
  }, [_vm._v("菜单管理")])])])
}]}

/***/ }),

/***/ 905:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(735);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("4b265acc", content, true);

/***/ })

});