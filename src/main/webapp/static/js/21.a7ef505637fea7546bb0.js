webpackJsonp([21],{

/***/ 572:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(854)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(659),
  /* template */
  __webpack_require__(772),
  /* scopeId */
  "data-v-054e91b7",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 659:
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
                buyPoundage: "",
                insurance: "",
                logisticsFee: "",
                maxBuyCount: "",
                minBuyCount: "",
                sellPoundage: ""
            },
            url: this.api + "realGoldConf/getRealGoldConf",
            editUrl: this.api + "realGoldConf/editRealGoldConf",
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
            _this.tableData = response.data.data;
        }).catch(function (error) {});
    },
    methods: {

        //  确定编辑方法
        convertsellPoundage(row) {
            var a = new Decimal(row.sellPoundage);
            var b = new Decimal(100);
            return a.times(b) + '%';
        },
        convertlogisticsFee(value) {
            return value.logisticsFee / 100;
        },
        convertinsurance(value) {
            return value.insurance / 100;
        },
        convertbuyPoundage(value) {
            return new Decimal(value.buyPoundage).times(new Decimal(100)) + "%";
        },
        //  编辑按钮
        handleEdit(index, row) {
            var buypoundage = new Decimal(row.buyPoundage).times(new Decimal(100)).toString();
            var sellpoundage = new Decimal(row.sellPoundage).times(new Decimal(100)).toString();
            // console.log(row)
            // row为行列信息
            this.newformEdit = {
                name: row.name,
                buyPoundage: buypoundage,
                insurance: row.insurance / 100,
                logisticsFee: row.logisticsFee / 100,
                maxBuyCount: row.maxBuyCount,
                minBuyCount: row.minBuyCount,
                sellPoundage: sellpoundage
                //获取要修改的数据的id
            };this.sid = row.s;
            this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        confirmAddEdit() {
            var params = new URLSearchParams();
            params.append('name', this.newformEdit.name);
            params.append('buyPoundage', this.newformEdit.buyPoundage / 100);
            params.append('insurance', this.newformEdit.insurance * 100);
            params.append('logisticsFee', this.newformEdit.logisticsFee * 100);
            params.append('maxBuyCount', this.newformEdit.maxBuyCount);
            params.append('minBuyCount', this.newformEdit.minBuyCount);
            params.append('sellPoundage', this.newformEdit.sellPoundage / 100);
            params.append('sid', this.sid);
            params.append('id', this.id);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data == 1) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.loadData();
                    _this.dialogFormVisibleEdit = false;
                } else if (response.data.data == 0) {
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

/***/ 680:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 772:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("实金买卖设定")]), _vm._v(" "), _c('el-table', {
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
      "prop": "buyPoundage",
      "formatter": _vm.convertbuyPoundage,
      "label": "买入手续费（每克）",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "maxBuyCount",
      "label": "买入上限",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "minBuyCount",
      "label": "买入下限",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "sellPoundage",
      "formatter": _vm.convertsellPoundage,
      "label": "卖出手续费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "insurance",
      "formatter": _vm.convertinsurance,
      "label": "保险费（每克）",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "logisticsFee",
      "formatter": _vm.convertlogisticsFee,
      "label": "物流费（每笔）",
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
      "label": "买入手续费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.buyPoundage),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "buyPoundage", $$v)
      },
      expression: "newformEdit.buyPoundage"
    }
  }), _vm._v("%\n              ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入上限",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.maxBuyCount),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "maxBuyCount", $$v)
      },
      expression: "newformEdit.maxBuyCount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入下限",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.minBuyCount),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "minBuyCount", $$v)
      },
      expression: "newformEdit.minBuyCount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "卖出手续费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.sellPoundage),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "sellPoundage", $$v)
      },
      expression: "newformEdit.sellPoundage"
    }
  }), _vm._v("%\n              ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "保险费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.insurance),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "insurance", $$v)
      },
      expression: "newformEdit.insurance"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "物流费",
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
  }, [_vm._v("实金买卖设定")])])])
}]}

/***/ }),

/***/ 854:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(680);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("b6035616", content, true);

/***/ })

});