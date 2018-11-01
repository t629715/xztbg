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
//
//
//
//
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
                invalidTime: ''
            },

            url: this.api + "goldBuyBackConf/selectGoldBuyBackConf",
            editUrl: this.api + "goldBuyBackConf/updateGoldBuyBackConf",
            urlPrice: this.api + "investGold/getInestGoldPrice",
            editUrlPrice: this.api + "investGold/updateInestGoldPrice",
            tableData: [],
            tableDataPirce: [],
            dialogFormVisibleEdit: false,
            dialogFormVisibleEditGoldPrice: false,
            formLabelWidth: '140px',
            id: ""
            // status: ""
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
                _this.tableData = response.data.data;
            }).catch(function (error) {});
        },

        /*编辑弹窗取值方法*/
        handleEdit(index, row) {
            this.newformEdit = {
                revisePrice: row.revisePrice,
                isStatus: row.isStatus,
                state: row.state,
                invalidTime: row.invalidTime

            };

            this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        /*提交编辑*/
        confirmEdit() {
            var params = new URLSearchParams();

            params.append("revisePrice", this.newformEdit.revisePrice);
            params.append("isStatus", this.newformEdit.isStatus);
            params.append("state", this.newformEdit.state);
            params.append("invalidTime", this.newformEdit.invalidTime);

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

/***/ 731:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

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
      "type": "index",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "公司地址",
      "prop": "companyAddress",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "公司电话",
      "prop": "companyPhone",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "收件人",
      "prop": "name",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "固定回收价格(元)",
      "prop": "revisePrice",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "交易操作",
      "prop": "isStatus",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "交易状态",
      "prop": "state",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "每天可预约次数",
      "prop": "preNum",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "预约过期时间",
      "prop": "invalidTime",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "创建人",
      "prop": "createBy",
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
        }, [_vm._v("编辑\n                        ")])]
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
      "label": "固定回收价格",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
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
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易操作",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.status),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "status", $$v)
      },
      expression: "newformEdit.status"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交易状态",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.state),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "state", $$v)
      },
      expression: "newformEdit.state"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "预约过期时间",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
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