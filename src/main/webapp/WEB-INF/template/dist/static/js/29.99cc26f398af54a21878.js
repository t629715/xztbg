webpackJsonp([29],{

/***/ 567:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(893)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(657),
  /* template */
  __webpack_require__(809),
  /* scopeId */
  "data-v-444b6e41",
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
            newformEdit: {
                name: "",
                buyFee: "",
                buyMax: "",
                buyMin: "",
                saleFee: ""
            },
            url: this.api + "saveGoldConf/getSaveGoldConf",
            editUrl: this.api + "saveGoldConf/editSaveGoldConf",
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            formLabelWidth: '120px',
            userId: ""
        };
    },
    created() {
        let _this = this;
        axios.post(this.url).then(function (response) {

            if (response.data.code == 1001) {}
            _this.tableData = response.data.data;
        }).catch(function (error) {});
    },
    methods: {

        //  确定编辑方法
        convertToYuan(row) {
            return (row.buyFee / 100).toFixed(2);
        },
        convertToYuan1(row) {
            return (row.saleFee / 100).toFixed(2);
        },

        convertbuyPoundage(value) {
            alert(value.buyFee);
            return new Decimal(value.buyFee).times(new Decimal(100)) + "%";
        },
        //  编辑按钮
        handleEdit(index, row) {
            // console.log(row)
            // row为行列信息
            this.newformEdit = {
                name: row.name,
                buyFee: (row.buyFee / 100).toFixed(2),
                buyMax: row.buyMax,
                buyMin: row.buyMin,
                saleFee: (row.saleFee / 100).toFixed(2)
                //获取要修改的数据的id
            };this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        confirmAddEdit() {
            var params = new URLSearchParams();
            params.append('name', this.newformEdit.name);
            params.append('buyFee', (this.newformEdit.buyFee * 100).toFixed(0));
            params.append('saleFee', (this.newformEdit.saleFee * 100).toFixed(0));
            params.append('buyMin', this.newformEdit.buyMin);
            params.append('buyMax', this.newformEdit.buyMax);
            params.append('id', this.id);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.loadData();
                    _this.dialogFormVisibleEdit = false;
                } else if (response.data.code == 1002) {
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
        //刷新表格方法
        loadData() {
            let _this = this;
            axios.post(this.url).then(function (response) {
                _this.tableData = response.data.data;
            }).catch(function (error) {});
        }

    }

});

/***/ }),

/***/ 713:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".inputWidth[data-v-444b6e41]{width:350px}", ""]);

// exports


/***/ }),

/***/ 809:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("存金宝设定")]), _vm._v(" "), _c('el-table', {
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
      "prop": "name",
      "label": "产品名称",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyFee",
      "label": "买入手续费(元/克)",
      "formatter": _vm.convertToYuan,
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "saleFee",
      "label": "卖出手续费(元/克)",
      "formatter": _vm.convertToYuan1,
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyMin",
      "label": "买入下限(克)",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyMax",
      "label": "买入上限(克)",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "120"
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
        }, [_vm._v("编辑")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
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
      "label": "产品名称",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.name),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "name", $$v)
      },
      expression: "newformEdit.name"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入上限",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.buyMax),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "buyMax", $$v)
      },
      expression: "newformEdit.buyMax"
    }
  }), _vm._v("(克)\n              ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入下限",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.buyMin),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "buyMin", $$v)
      },
      expression: "newformEdit.buyMin"
    }
  }), _vm._v("(克)\n              ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入手续费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.buyFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "buyFee", $$v)
      },
      expression: "newformEdit.buyFee"
    }
  }), _vm._v("(元/克)\n              ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "卖出手续费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.saleFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "saleFee", $$v)
      },
      expression: "newformEdit.saleFee"
    }
  }), _vm._v("(元/克)\n              ")], 1)], 1), _vm._v(" "), _c('div', {
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
  }), _vm._v(" 产品设定")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("存金宝设定")])])])
}]}

/***/ }),

/***/ 893:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(713);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("44b97874", content, true);

/***/ })

});