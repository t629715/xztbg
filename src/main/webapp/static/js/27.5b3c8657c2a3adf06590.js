webpackJsonp([27],{

/***/ 571:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(915)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(663),
  /* template */
  __webpack_require__(829),
  /* scopeId */
  "data-v-60be6161",
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loading: true,
            newformEdit: {
                revisePrice: '',
                isStatus: '',
                state: '',
                invalidTime: '',
                type: "",
                reviseValue: ""
            },

            url: this.api + "goldBuyBackConf/selectGoldBuyBackConf",
            editUrl: this.api + "goldBuyBackConf/updateGoldBuyBackConf",
            tableData: [],
            dialogFormVisibleEdit: false,
            dialogFormVisibleEditGoldPrice: false,
            formLabelWidth: '140px',
            value: "",
            id: ""
        };
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            let _this = this;
            var params = new URLSearchParams();
            this.$axios.post(this.url).then(function (response) {
                _this.tableData = response.data.data;
                _this.newformEdit = {
                    revisePrice: _this.tableData[0].revisePrice,
                    isStatus: _this.tableData[0].isStatus,
                    state: _this.tableData[0].state,
                    invalidTime: _this.tableData[0].invalidTime,
                    type: _this.tableData[0].type,
                    reviseValue: _this.tableData[0].reviseValue

                };
            }).catch(function (error) {});
        },
        /*提交编辑*/
        confirmEdit() {
            var params = new URLSearchParams();
            params.append("revisePrice", this.newformEdit.revisePrice);
            params.append("reviseValue", this.newformEdit.reviseValue);
            params.append("isStatus", this.newformEdit.isStatus);
            params.append("state", this.newformEdit.state);
            params.append("invalidTime", this.newformEdit.invalidTime);
            params.append("type", this.newformEdit.type);
            params.append("id", this.id);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.code == 1001) {
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

/***/ 731:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".inputWidth[data-v-60be6161]{width:200px}.dialog-footer[data-v-60be6161]{margin-left:140px}", ""]);

// exports


/***/ }),

/***/ 829:
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
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEdit
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "回收价格 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.newformEdit.type),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "type", $$v)
      },
      expression: "newformEdit.type"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "0",
      "name": "3"
    }
  }, [_vm._v("实时价")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "3"
    }
  }, [_vm._v("昨收价")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "2",
      "name": "3"
    }
  }, [_vm._v("固定值")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label-width": _vm.formLabelWidth
    }
  }, [(_vm.newformEdit.type != 2) ? _c('spran', [_vm._v("+")]) : _vm._e(), _vm._v(" "), (_vm.newformEdit.type == 2) ? _c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.revisePrice),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "revisePrice", $$v)
      },
      expression: "newformEdit.revisePrice"
    }
  }) : _vm._e(), _vm._v(" "), (_vm.newformEdit.type != 2) ? _c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.reviseValue),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "reviseValue", $$v)
      },
      expression: "newformEdit.reviseValue"
    }
  }) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "预约过期时间 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.invalidTime),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "invalidTime", $$v)
      },
      expression: "newformEdit.invalidTime"
    }
  }), _vm._v("分钟\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易状态 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.newformEdit.isStatus),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "isStatus", $$v)
      },
      expression: "newformEdit.isStatus"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": true,
      "name": "1"
    }
  }, [_vm._v("可回收")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": false,
      "name": "1"
    }
  }, [_vm._v("禁止回收")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易操作 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.newformEdit.state),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "state", $$v)
      },
      expression: "newformEdit.state"
    }
  }, [_c('el-radio-button', {
    attrs: {
      "label": "0",
      "name": "2"
    }
  }, [_vm._v("正常规则")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "1",
      "name": "2"
    }
  }, [_vm._v("强制回购")]), _vm._v(" "), _c('el-radio-button', {
    attrs: {
      "label": "2",
      "name": "2"
    }
  }, [_vm._v("禁止回购")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "medium",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmEdit()
      }
    }
  }, [_vm._v("生 效")])], 1)], 1)])])
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
  }), _vm._v("\n            产品设定")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("回购规则设置")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 915:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(731);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("20b43403", content, true);

/***/ })

});