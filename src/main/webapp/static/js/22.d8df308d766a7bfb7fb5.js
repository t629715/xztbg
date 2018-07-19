webpackJsonp([22],{

/***/ 569:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(859)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(654),
  /* template */
  __webpack_require__(779),
  /* scopeId */
  "data-v-257eb8ce",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 654:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            formForModify: {
                productNo: "",
                productName: "",
                productType: "",
                productYearIncomPercent: "",
                productCycle: "",
                productMinGram: "",
                productMaxGram: "",
                productSettleMethod: "",
                productStatus: "",
                productDesc: ""
            },
            settleMethods: [{
                id: 1,
                name: "黄金结算"
            }],
            url: this.api + "financeConfRegulargold/getAllFinanceConf",
            editUrl: this.api + "financeConfRegulargold/modifySelective",
            deleteUrl: this.api + "financeConfRegulargold/removeById",
            modifyVisible: false,
            deleteVisible: false,
            tableData: [],
            formLabelWidth: '120px',
            productType: "",

            productStatus: "",
            productId: ""
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(1, 10);
    },
    //定义方法
    methods: {
        /*加载数据*/
        loadData(pageNum, pageSize) {
            let _this = this;
            var params = new URLSearchParams();
            params.append("pageNum", pageNum);
            params.append("pageSize", pageSize);
            axios.post(this.url, params).then(function (res) {
                if (res.data.code == 1001) {
                    _this.tableData = res.data.data.list;
                }
            });
        },
        /*转换产品状态*/
        convertStatus(value) {
            if (value.productStatus == 1) {
                return "在售";
            } else {
                return "已下线";
            }
        },
        /*转换结算方式*/
        convertSettleMethod(value) {

            if (value.productSettleMethod == 1) {
                return "黄金结算";
            }
        },
        /*转换产品类型*/
        convertType(value) {
            if (value.productType == 1) {
                return "稳赚金";
            } else {
                return "特价金";
            }
        },
        // 转换年化收益率
        convertPercent(value) {
            return (value.productYearIncomPercent * 100).toFixed(2) + '%';
        },
        /*修改弹窗*/
        dialogForModify(row) {
            this.modifyVisible = true;
            this.formForModify = {
                productNo: row.productNo,
                productName: row.productName,
                productType: row.productType,
                productYearIncomPercent: (row.productYearIncomPercent * 100).toFixed(2),
                productCycle: row.productCycle,
                productMinGram: row.productMinGram,
                productMaxGram: row.productMaxGram,
                productSettleMethod: row.productSettleMethod,
                productStatus: row.productStatus,
                productDesc: row.productDesc

            };
            this.productId = row.id;
        },
        /*提交修改的数据*/
        confirmForModify() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("id", _this.productId);
            params.append("productCycle", _this.formForModify.productCycle);
            params.append("productMinGram", _this.formForModify.productMinGram);
            params.append("productMaxGram", _this.formForModify.productMaxGram);
            params.append("productYearIncomPercent", (_this.formForModify.productYearIncomPercent / 100).toFixed(4));
            axios.post(_this.editUrl, params).then(function (res) {
                _this.modifyVisible = false;
                if (res.data.code == 1000) {
                    _this.loadData(1, 10);
                    _this.$message({
                        message: '修改成功',
                        type: "success"
                    });
                } else {
                    _this.$message({
                        message: '修改失败',
                        type: "waring"
                    });
                }
                _this.modifyVisible = false;
            });
        },
        /*删除确认弹窗*/
        dialogForDelete(row) {
            this.deleteVisible = true;
            this.productId = row.id;
        },
        /*确认删除*/
        confirmForDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("id", _this.productId);
            axios.post(_this.deleteUrl, params).then(function (res) {
                _this.deleteVisible = false;
                if (res.data.code == 1000) {
                    _this.loadData(1, 10);
                    _this.$message({
                        message: '删除成功',
                        type: "success"
                    });
                } else {
                    _this.$message({
                        message: '删除失败',
                        type: "waring"
                    });
                }
            });
        }
    }
});

/***/ }),

/***/ 689:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 779:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("稳赚金设定")]), _vm._v(" "), _c('div', {
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
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productNo",
      "label": "产品编号",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productName",
      "label": "产品名称",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productType",
      "label": "产品类型",
      "width": "100",
      "formatter": _vm.convertType
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productDesc",
      "label": "产品描述",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productCycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productYearIncomPercent",
      "label": "年化收益率",
      "width": "120",
      "formatter": _vm.convertPercent
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productMinGram",
      "label": "起投克重",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productMaxGram",
      "label": "最大克重",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productSettleMethod",
      "label": "结算方式",
      "width": "100",
      "formatter": _vm.convertSettleMethod
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productStatus",
      "label": "状态",
      "width": "200",
      "formatter": _vm.convertStatus
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
              _vm.dialogForModify(scope.row)
            }
          }
        }, [_vm._v("编辑\n                        ")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "width": "30%",
      "visible": _vm.modifyVisible,
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.modifyVisible = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.formForModify
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "产品编号：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productNo),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productNo", $$v)
      },
      expression: "formForModify.productNo"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "产品名称：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productName),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productName", $$v)
      },
      expression: "formForModify.productName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "周期：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productCycle),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productCycle", $$v)
      },
      expression: "formForModify.productCycle"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "年化收益率：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 12
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productYearIncomPercent),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productYearIncomPercent", $$v)
      },
      expression: "formForModify.productYearIncomPercent"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "起投克重：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productMinGram),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productMinGram", $$v)
      },
      expression: "formForModify.productMinGram"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大克重：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.formForModify.productMaxGram),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productMaxGram", $$v)
      },
      expression: "formForModify.productMaxGram"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "结算方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "disabled": true,
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.formForModify.productSettleMethod),
      callback: function($$v) {
        _vm.$set(_vm.formForModify, "productSettleMethod", $$v)
      },
      expression: "formForModify.productSettleMethod"
    }
  }, _vm._l((_vm.settleMethods), function(item) {
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
        _vm.modifyVisible = false
      }
    }
  }, [_vm._v("取 消\n                    ")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmForModify
    }
  }, [_vm._v("确 定\n                    ")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "删除",
      "visible": _vm.deleteVisible,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.deleteVisible = $event
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
        _vm.deleteVisible = false
      }
    }
  }, [_vm._v("取 消\n                    ")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmForDelete
    }
  }, [_vm._v("确 定\n                    ")])], 1)])], 1)])])
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
  }, [_vm._v("稳赚金设定")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 859:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(689);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("818b85a6", content, true);

/***/ })

});