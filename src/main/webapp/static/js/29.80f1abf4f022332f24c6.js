webpackJsonp([29],{

/***/ 570:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(915)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(663),
  /* template */
  __webpack_require__(828),
  /* scopeId */
  "data-v-587da40a",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 663:
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
    data() {
        return {
            loading: true,
            newformEdit: {
                id: '',
                handlingFee: '',
                processingFee: '',
                invoiceFee: '',
                logisticsFee: '',
                custodyFee: '',
                custodyStartDate: '',
                type: ''
            },
            url: this.api + "deliveryGoldConf/getAllDeliveryGoldConf",
            editUrl: this.api + "deliveryGoldConf/modifyDeliveryGoldConf",

            tableData: [],

            dialogFormVisibleEdit: false,
            dialogFormVisibleOpenTrading: false,
            dialogFormVisibleCloseTrading: false,
            dialogFormVisibleTrading: false,

            formLabelWidth: '120px',
            id: "",
            status: ""
        };
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            let _this = this;
            var params = new URLSearchParams();
            axios.post(this.url).then(function (response) {
                _this.tableData = response.data.data.list;
            }).catch(function (error) {});
        },

        /*编辑弹窗取值方法*/
        handleEdit(index, row) {
            this.newformEdit = {

                handlingFee: row.handlingFee,
                processingFee: row.processingFee,
                invoiceFee: row.invoiceFee,
                logisticsFee: row.logisticsFee,
                custodyFee: row.custodyFee,
                custodyStartDate: row.custodyStartDate,
                type: row.type
            };
            this.status = row.status;
            this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        /*提交编辑*/
        confirmEdit() {
            var params = new URLSearchParams();
            params.append("handlingFee", this.newformEdit.handlingFee);
            params.append("processingFee", this.newformEdit.processingFee);
            params.append("invoiceFee", this.newformEdit.invoiceFee);
            params.append("logisticsFee", this.newformEdit.logisticsFee);

            params.append("custodyFee", this.newformEdit.custodyFee);
            params.append("custodyStartDate", this.newformEdit.custodyStartDate);

            params.append("id", this.id);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit = false;
                } else {
                    _this.dialogFormVisibleEdit = false;
                    _this.$message.error('网络错误');
                }
            });
        }

    }
});

/***/ }),

/***/ 729:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 828:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
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
      "label": "序号",
      "prop": "id",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "手续费率",
      "prop": "handlingFee",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "加工费(元)",
      "prop": "processingFee",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "发票费率",
      "prop": "invoiceFee",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "物流费(元)",
      "prop": "logisticsFee",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "保管费(元) 元/克天",
      "prop": "custodyFee",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "免收保管费天数",
      "prop": "custodyStartDate",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "交割状态",
      "prop": "type",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "220"
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
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-dialog', {
    attrs: {
      "title": "编辑",
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
      "label": "手续费率",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.handlingFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "handlingFee", $$v)
      },
      expression: "newformEdit.handlingFee"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "加工费(元)",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.processingFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "processingFee", $$v)
      },
      expression: "newformEdit.processingFee"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "发票费率",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.invoiceFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "invoiceFee", $$v)
      },
      expression: "newformEdit.invoiceFee"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "物流费(元)",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.logisticsFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "logisticsFee", $$v)
      },
      expression: "newformEdit.logisticsFee"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "保管费(元) 元/克天",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.custodyFee),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "custodyFee", $$v)
      },
      expression: "newformEdit.custodyFee"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "免收保管费天数",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.custodyStartDate),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "custodyStartDate", $$v)
      },
      expression: "newformEdit.custodyStartDate"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交割状态",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.type),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "type", $$v)
      },
      expression: "newformEdit.type"
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
      "click": _vm.confirmEdit
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1)])])
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
  }, [_vm._v("金权交割规则设定")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 915:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(729);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("0b34789e", content, true);

/***/ })

});