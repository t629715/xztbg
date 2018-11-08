webpackJsonp([27],{

/***/ 572:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(920)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(665),
  /* template */
  __webpack_require__(833),
  /* scopeId */
  "data-v-60be6161",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 665:
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
    data() {
        return {
            loading: true,
            newformEdit: {
                revisePrice: '',
                isStatus: '',
                state: '',
                invalidTime: '',
                type: "",
                companyAddress: "",
                companyPhone: "",
                name: "",
                pickUpTime: "",
                pickUpDateRevise: "",
                preNum: "",
                maxGoldWeight: ""

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
                    reviseValue: _this.tableData[0].reviseValue,
                    companyAddress: _this.tableData[0].companyAddress,
                    companyPhone: _this.tableData[0].companyPhone,
                    name: _this.tableData[0].name,
                    pickUpTime: _this.tableData[0].pickUpTime,
                    pickUpDateRevise: _this.tableData[0].pickUpDateRevise,
                    preNum: _this.tableData[0].preNum,
                    maxGoldWeight: _this.tableData[0].maxGoldWeight

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
            params.append("companyAddress", this.newformEdit.companyAddress);
            params.append("companyPhone", this.newformEdit.companyPhone);
            params.append("maxGoldWeight", this.newformEdit.maxGoldWeight);
            params.append("name", this.newformEdit.name);
            params.append("pickUpTime", this.newformEdit.pickUpTime);
            params.append("pickUpDateRevise", this.newformEdit.pickUpDateRevise);
            params.append("preNum", this.newformEdit.preNum);
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

/***/ 734:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".inputWidth[data-v-60be6161]{width:200px}.dialog-footer[data-v-60be6161]{margin-left:140px}", ""]);

// exports


/***/ }),

/***/ 833:
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
  }, [_c('el-input', {
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
  }), _vm._v("元/克\n                    ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司名称 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
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
      "label": "公司电话 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.companyPhone),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "companyPhone", $$v)
      },
      expression: "newformEdit.companyPhone"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司地址 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.companyAddress),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "companyAddress", $$v)
      },
      expression: "newformEdit.companyAddress"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大预约次数 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.preNum),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "preNum", $$v)
      },
      expression: "newformEdit.preNum"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大预约克重 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.maxGoldWeight),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "maxGoldWeight", $$v)
      },
      expression: "newformEdit.maxGoldWeight"
    }
  }), _vm._v("克\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "取货日期 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.pickUpDateRevise),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "pickUpDateRevise", $$v)
      },
      expression: "newformEdit.pickUpDateRevise"
    }
  }), _vm._v("客户申请日" + _vm._s(_vm.newformEdit.pickUpDateRevise) + "天内\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "上门取货时间 :",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticClass: "inputWidth",
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.pickUpTime),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "pickUpTime", $$v)
      },
      expression: "newformEdit.pickUpTime"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
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
  }), _vm._v("分钟\n                ")], 1)], 1), _vm._v(" "), _c('div', {
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

/***/ 920:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(734);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("69922107", content, true);

/***/ })

});