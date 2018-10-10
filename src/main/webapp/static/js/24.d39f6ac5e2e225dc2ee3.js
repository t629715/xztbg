webpackJsonp([24],{

/***/ 571:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(882)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(660),
  /* template */
  __webpack_require__(799),
  /* scopeId */
  "data-v-398ac525",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 660:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loading: true,
            newformEdit: {
                id: '',
                name: '',
                goldWeight: '',
                withdrawService: '',
                minBuyCount: '',
                maxBuyCount: '',
                logisticsFee: '',
                imgUrl: ''
            },
            newformEditPrice: {
                setPrice: '',
                price: ''
            },
            url: this.api + "investGoldConf/getAllInvestGoldConf",
            editUrl: this.api + "investGoldConf/modifyInvestGoldConf",
            urlPrice: this.api + "investGold/getInestGoldPrice",
            editUrlPrice: this.api + "investGold/updateInestGoldPrice",
            tableData: [],
            tableDataPirce: [],
            dialogFormVisibleEdit: false,
            dialogFormVisibleEditGoldPrice: false,
            formLabelWidth: '140px',
            id: "",
            status: ""
        };
    },
    created() {
        this.loadData();
        this.loadDataPrice();
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

                name: row.name,
                goldWeight: row.goldWeight,
                withdrawService: row.withdrawService,
                minBuyCount: row.minBuyCount,
                maxBuyCount: row.maxBuyCount,
                logisticsFee: row.logisticsFee,
                imgUrl: row.imgUrl
            };
            this.status = row.status;
            this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        /*提交编辑*/
        confirmEdit() {
            var params = new URLSearchParams();
            params.append("name", this.newformEdit.name);
            params.append("goldWeight", this.newformEdit.goldWeight);
            params.append("withdrawService", this.newformEdit.withdrawService);
            params.append("minBuyCount", this.newformEdit.minBuyCount);

            params.append("maxBuyCount", this.newformEdit.maxBuyCount);
            params.append("logisticsFee", this.newformEdit.logisticsFee);
            params.append("imgUrl", this.newformEdit.imgUrl);
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
        },

        //刷新表格方法
        loadDataPrice() {
            var params = new URLSearchParams();

            let _this = this;

            axios.post(this.urlPrice, params).then(function (response) {
                _this.tableDataPirce = response.data.data;
            }).catch(function (error) {
                self.$router.push('/login');
            });
        },

        handleEditPrice(num, row) {
            this.newformEditPrice.price = row.paramValue;
            this.dialogFormVisibleEditGoldPrice = true;
            this.newformEditPrice.setPrice = '';
        },
        confirmAddEditPrice: function () {
            var params = new URLSearchParams();
            params.append("setPrice", this.newformEditPrice.setPrice);
            let _this = this;

            axios.post(this.editUrlPrice, params).then(function (response) {
                var code = response.data.code;
                if (code == 1000) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEditGoldPrice = false;
                    _this.loadData();
                }
                if (code == 1002) {
                    _this.$message.error('操作失败!');
                    _this.dialogFormVisibleEditGoldPrice = false;
                    _this.loadData();
                }if (code == 1003) {
                    _this.$message.error('输入价格格式错误!');
                    //   _this.dialogFormVisibleEditGold = false;
                    // _this.loadData();
                }
                if (code == 9999) {
                    _this.dialogFormVisibleEditGoldPrice = false;
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

/***/ 704:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 799:
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
      "label": "产品名称",
      "prop": "name",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "金条规格",
      "prop": "goldWeight",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "每笔提金服务费(元)",
      "prop": "withdrawService",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最小购买条数",
      "prop": "minBuyCount",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最大购买条数",
      "prop": "maxBuyCount",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "物流费(元)",
      "prop": "logisticsFee",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "商品图片地址",
      "prop": "imgUrl",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "是否有效",
      "prop": "status",
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
      "label": "产品名称",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
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
      "label": "金条规格",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.goldWeight),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "goldWeight", $$v)
      },
      expression: "newformEdit.goldWeight"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "每笔提金服务费(元)",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.withdrawService),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "withdrawService", $$v)
      },
      expression: "newformEdit.withdrawService"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最小购买条数",
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
      "label": "最大购买条数",
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
      "label": "商品图片地址",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.imgUrl),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "imgUrl", $$v)
      },
      expression: "newformEdit.imgUrl"
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
  }, [_vm._v("确 定")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
    attrs: {
      "data": _vm.tableDataPirce,
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
              _vm.handleEditPrice(1, scope.row)
            }
          }
        }, [_vm._v("编辑\n                        ")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEditGoldPrice,
      "width": "30%",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEditGoldPrice = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEditPrice
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
      value: (_vm.newformEditPrice.price),
      callback: function($$v) {
        _vm.$set(_vm.newformEditPrice, "price", $$v)
      },
      expression: "newformEditPrice.price"
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
      value: (_vm.newformEditPrice.setPrice),
      callback: function($$v) {
        _vm.$set(_vm.newformEditPrice, "setPrice", $$v)
      },
      expression: "newformEditPrice.setPrice"
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
        _vm.dialogFormVisibleEditGoldPrice = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmAddEditPrice
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
  }, [_vm._v("金条投资产品规则")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 882:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(704);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("3e3d1c68", content, true);

/***/ })

});