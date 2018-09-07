webpackJsonp([53],{

/***/ 538:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(871)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(625),
  /* template */
  __webpack_require__(789),
  /* scopeId */
  "data-v-2dcdaae2",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 625:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  //model 初始数据
  data() {
    return {
      editPayWay: {
        payWays: [],
        payDevice: ""
      },
      tableData1: [],
      getPayWayUrl: this.api + "inOutGold/getPayWaysForDevice",
      editPayWayUrl: this.api + "inOutGold/editPayWaysForDevice",
      dialogFormVisibleEdit: false
    };
  },
  //页面渲染加载方法
  created() {
    this.getPaysForDevice();
  },
  //定义方法
  methods: {
    getPaysForDevice() {
      var _this = this;
      axios.get(this.getPayWayUrl).then(function (response) {
        var list = "";
        list = response.data.data.list;
        for (var i = 0; i < list.length; i++) {
          var str = "";
          for (var j = 0; j < list[i].payWays.length; j++) {

            if (list[i].payWays[j].payWay == 1) {

              if (str != "") {
                str += "，支付宝";
              } else {
                str += "支付宝";
              }
            } else if (list[i].payWays[j].payWay == 4) {
              if (str != "") {
                str += "，微信扫码";
              } else {
                str += "微信扫码";
              }
            } else if (list[i].payWays[j].payWay == 3) {
              if (str != "") {
                str += "，银联";
              } else {
                str += "银联";
              }
            } else if (list[i].payWays[j].payWay == 2) {
              if (str != "") {
                str += "，微信";
              } else {
                str += "微信";
              }
            }
          }
          list[i].payWaysForDevice = str;
        }
        _this.tableData1 = list;
      }).catch(function (error) {});
    },
    convertDevice(row) {
      if (row.payDevice == 1) {
        return "IOS";
      } else return "Android";
    },
    confirmEditPayWaysForDevice() {
      let _this = this;
      var params = new URLSearchParams();
      params.append("payDevice", this.editPayWay.payDevice == "IOS" ? 1 : 2);
      params.append("payWays", this.editPayWay.payWays);
      axios.post(this.editPayWayUrl, params).then(function (response) {
        if (response.data.data != 0) {
          _this.$message({
            message: '修改成功',
            type: 'success'
          });
          _this.getPaysForDevice();
          _this.dialogFormVisibleEdit = false;
        } else if (response.data.data === 0) {
          _this.$message({
            message: '修改成功',
            type: 'success'
          });
          _this.getPaysForDevice();
          _this.dialogFormVisibleEdit = false;
        } else {
          _this.dialogFormVisibleEdit = false;
          _this.$message.error('网络错误');
        }
      });
    },

    editPayWayDevice(index, row) {
      var arr = [];
      for (var i = 0; i < row.payWays.length; i++) {
        arr[i] = row.payWays[i].payWay.toString();
      }
      this.editPayWay = {
        payDevice: row.payDevice == 1 ? "IOS" : "Android",
        payWays: arr
      };
      this.dialogFormVisibleEdit = true;
    }
  }
});

/***/ }),

/***/ 697:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 789:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('el-table', {
    staticStyle: {
      "width": "80%"
    },
    attrs: {
      "data": _vm.tableData1,
      "fit": "",
      "border": "",
      "stripe": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "index",
      "label": "序号",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "payDevice",
      "label": "设备",
      "formatter": _vm.convertDevice
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "payWaysForDevice",
      "label": "付款方式"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.editPayWayDevice(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")])]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('el-dialog', {
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
    ref: "editPayWay",
    attrs: {
      "model": _vm.editPayWay
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "支付设备"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.editPayWay.payDevice),
      callback: function($$v) {
        _vm.$set(_vm.editPayWay, "payDevice", $$v)
      },
      expression: "editPayWay.payDevice"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "支付方式"
    }
  }, [_c('el-checkbox-group', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.editPayWay.payWays),
      callback: function($$v) {
        _vm.$set(_vm.editPayWay, "payWays", $$v)
      },
      expression: "editPayWay.payWays"
    }
  }, [_c('el-checkbox', {
    attrs: {
      "label": "1",
      "name": "payWays"
    }
  }, [_vm._v("支付宝")]), _vm._v(" "), _c('el-checkbox', {
    attrs: {
      "label": "4",
      "name": "payWays"
    }
  }, [_vm._v("微信扫码")]), _vm._v(" "), _c('el-checkbox', {
    attrs: {
      "label": "3",
      "name": "payWays"
    }
  }, [_vm._v("银联")]), _vm._v(" "), _c('el-checkbox', {
    attrs: {
      "label": "2",
      "name": "payWays"
    }
  }, [_vm._v("微信")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
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
      "click": _vm.confirmEditPayWaysForDevice
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1)
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
  }), _vm._v(" 出入金管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("支付管理")])])])
}]}

/***/ }),

/***/ 871:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(697);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("48588a0c", content, true);

/***/ })

});