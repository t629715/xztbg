webpackJsonp([56],{

/***/ 541:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(894)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(634),
  /* template */
  __webpack_require__(806),
  /* scopeId */
  "data-v-2ae8165b",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 634:
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

/* harmony default export */ __webpack_exports__["default"] = ({
  data() {
    return {
      loading: true,
      channelOptions: "",
      sform: {
        userName: '',
        gram: '',
        price: '',
        amount: '',
        poundage: '',
        total: ''
      },
      url: this.api + "goldRedeemConf/selectByGoldRedeemConfEnable",
      handleUrl: this.api + "goldRedeemConf/calculationGoldRedeem",
      submitUrl: this.api + "goldRedeem/excelGoldRedeem",
      checkUrl: this.api + "goldRedeem/checkUserName",
      addUrl: this.api + "goldRedeem/saveGoldRedeem",
      formLabelWidth: '120px',
      poundagePercent: '',
      redeemPrice: '',
      redeemGram: '',
      redeemUserName: '',
      redeemAccountId: '',
      redeemUserId: ''
    };
  },
  //页面渲染加载方法
  created() {
    //查询赎回配置
    let _this = this;
    axios.post(this.url).then(function (response) {
      if (response.data.code == 1001) {
        _this.redeemPrice = response.data.data.price;
        _this.poundagePercent = response.data.data.poundagePercent;
        _this.sform.price = response.data.data.price;
        _this.sform = {
          userName: '',
          gram: '',
          price: response.data.data.price,
          amount: '',
          poundage: '',
          total: ''
        };
      }
      /*          _this.$message({
                  message: '查询成功',
                  type: 'success'
                });*/
    }).catch(function (error) {});
  },
  //定义方法
  methods: {
    handle(sform) {
      console.log(sform);
      var params = new URLSearchParams();
      params.append('price', sform.price);
      params.append('gram', sform.gram);
      params.append('poundagePercent', this.poundagePercent);
      let _this = this;
      axios.post(this.handleUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          _this.sform.amount = response.data.data.amount;
          _this.sform.poundage = response.data.data.poundage;
          _this.sform.total = response.data.data.total;
        } else {
          _this.$message.error('网络错误');
          _this.dialogFormVisible = false;
        }
      }).catch(function (error) {
        console.log(error);
      });
    },
    checkUserName(sform) {
      console.log(sform);
      var params = new URLSearchParams();
      params.append('userName', sform.userName);
      let _this = this;
      axios.post(this.checkUrl, params).then(function (response) {
        if (response.data.code == 1001) {
          _this.redeemAccountId = response.data.data.id;
          _this.redeemUserId = response.data.data.UserID;
        } else {
          _this.$message.error('用户名不存在！');
          _this.dialogFormVisible = false;
        }
      }).catch(function (error) {
        console.log(error);
      });
    },

    onSubmit(sform) {
      console.log(this.newform);
      var params = new URLSearchParams();
      params.append('userIdString', this.redeemUserId);
      params.append('gram', this.sform.gram);
      //params.append('amount', Number(this.sform.total));
      params.append('amount', (Number(this.sform.total) * 100).toFixed(0));
      params.append('price', this.sform.price);
      params.append('poundage', (Number(this.sform.poundage) * 100).toFixed(0));
      params.append('accountId', this.redeemAccountId);
      params.append('userName', this.sform.userName);
      let _this = this;
      axios.post(this.addUrl, params).then(function (response) {
        if (response.data.code == 1000) {
          _this.$message({
            message: '添加成功',
            type: 'success'
          });
          setTimeout(function () {
            window.location.reload();
          }, 2000);
        } else {
          _this.$message.error('网络错误');
          _this.dialogFormVisible = false;
        }
      }).catch(function (error) {
        console.log(error);
      });
    }
  }
});

/***/ }),

/***/ 708:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 806:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('el-form', {
    ref: "sform",
    attrs: {
      "model": _vm.sform,
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "赎回价格：",
      "prop": "price",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "disabled": true
    },
    model: {
      value: (_vm.sform.price),
      callback: function($$v) {
        _vm.$set(_vm.sform, "price", $$v)
      },
      expression: "sform.price"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回克重：",
      "prop": "gram",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    on: {
      "blur": function($event) {
        _vm.handle(_vm.sform)
      }
    },
    model: {
      value: (_vm.sform.gram),
      callback: function($$v) {
        _vm.$set(_vm.sform, "gram", $$v)
      },
      expression: "sform.gram"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回金额：",
      "prop": "amount",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small",
      "disabled": true
    },
    model: {
      value: (_vm.sform.amount),
      callback: function($$v) {
        _vm.$set(_vm.sform, "amount", $$v)
      },
      expression: "sform.amount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "所需手续费：",
      "prop": "poundage",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small",
      "disabled": true
    },
    model: {
      value: (_vm.sform.poundage),
      callback: function($$v) {
        _vm.$set(_vm.sform, "poundage", $$v)
      },
      expression: "sform.poundage"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "总计：",
      "prop": "total",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small",
      "disabled": true
    },
    model: {
      value: (_vm.sform.total),
      callback: function($$v) {
        _vm.$set(_vm.sform, "total", $$v)
      },
      expression: "sform.total"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "充值账号：",
      "prop": "userName",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "300px"
    },
    attrs: {
      "size": "small"
    },
    on: {
      "blur": function($event) {
        _vm.checkUserName(_vm.sform)
      }
    },
    model: {
      value: (_vm.sform.userName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "userName", $$v)
      },
      expression: "sform.userName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label-width": _vm.formLabelWidth
    }
  }, [_c('div', {
    staticStyle: {
      "color": "red"
    }
  }, [_vm._v("提示：填写赎回克重自动计算赎回金额、所需手续费、总计。")])]), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.sform)
      }
    }
  }, [_vm._v("确定")])], 1)], 1)], 1)])
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
  }), _vm._v(" 黄金赎回")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("赎回计算")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 894:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(708);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("7cf1b34a", content, true);

/***/ })

});