webpackJsonp([22],{

/***/ 575:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(882)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(666),
  /* template */
  __webpack_require__(796),
  /* scopeId */
  "data-v-24a3b424",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 666:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            url: this.api + "investGold/getInestGoldPrice",
            editUrl: this.api + "investGold/updateInestGoldPrice",
            tableData: [],
            formLabelWidth: '120px',
            dialogFormVisibleEditGold: false,
            newformEdit: {
                setPrice: '',
                price: ''
            }
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData();
    },
    //定义方法
    methods: {
        //刷新表格方法
        loadData() {
            var params = new URLSearchParams();

            let _this = this;

            axios.post(this.url, params).then(function (response) {
                _this.tableData = response.data.data;
            }).catch(function (error) {
                self.$router.push('/login');
            });
        },

        handleEdit(num, row) {
            this.newformEdit.price = row.paramValue;
            this.dialogFormVisibleEditGold = true;
            this.newformEdit.setPrice = '';
        },
        confirmAddEdit: function () {
            var params = new URLSearchParams();
            params.append("setPrice", this.newformEdit.setPrice);
            let _this = this;

            axios.post(this.editUrl, params).then(function (response) {
                var code = response.data.code;
                if (code == 1000) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEditGold = false;
                    _this.loadData();
                }
                if (code == 1002) {
                    _this.$message.error('操作失败!');
                    _this.dialogFormVisibleEditGold = false;
                    _this.loadData();
                }if (code == 1003) {
                    _this.$message.error('输入价格格式错误!');
                    //   _this.dialogFormVisibleEditGold = false;
                    // _this.loadData();
                }
                if (code == 9999) {
                    _this.dialogFormVisibleEditGold = false;
                    _this.$message.error('网络错误');
                    _this.loadData();
                }
            }).catch(function (error) {
                self.$router.push('/login');
            });
        }

    }
});

/***/ }),

/***/ 700:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 796:
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
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "paramValue",
      "label": "金条投资当前报价(元)",
      "width": "200"
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
              _vm.handleEdit(1, scope.row)
            }
          }
        }, [_vm._v("编辑\n                        ")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEditGold,
      "width": "30%",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEditGold = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEdit
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "当前报价:",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.price),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "price", $$v)
      },
      expression: "newformEdit.price"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "更改报价:",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.setPrice),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "setPrice", $$v)
      },
      expression: "newformEdit.setPrice"
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
        _vm.dialogFormVisibleEditGold = false
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
  }), _vm._v("运营管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("金条投资报价")])])])
}]}

/***/ }),

/***/ 882:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(700);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("a909ed9e", content, true);

/***/ })

});