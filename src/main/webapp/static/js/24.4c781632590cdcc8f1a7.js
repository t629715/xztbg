webpackJsonp([24],{

/***/ 569:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(874)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(656),
  /* template */
  __webpack_require__(793),
  /* scopeId */
  "data-v-3cb2d502",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 656:
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            form: {
                username: '',
                region: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            newformEdit: {
                contract: '',
                buyPercent: "",
                poundage: "",
                pointCount: "",
                maxBuyCountPerDay: '',
                maxPositionCount: '',
                minGramPerOrder: '',
                name: '',
                stopProfitSet: '',
                poundage: '',
                volatility: ''

            },
            url: this.api + "goldRightDealConf/getAllGoldRight",
            editUrl: this.api + "goldRightDealConf/modifyGoldRightDealConf",
            forceUrl: this.api + "goldRightDealConf/force-close-repository",
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleOpenTrading: false,
            dialogFormVisibleCloseTrading: false,
            dialogFormVisibleTrading: false,
            dialogFormVisibleForceTrading: false,
            formLabelWidth: '130px',
            formInputWidth: '60px',
            userId: "",
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

        confirmDialog(num, row) {
            if (num === 0) {
                this.dialogFormVisibleTrading = true;
            } else if (num == 1) {
                this.dialogFormVisibleOpenTrading = true;
            } else if (num == 2) {
                this.dialogFormVisibleCloseTrading = true;
            } else if (num == 3) {
                this.dialogFormVisibleForceTrading = true;
            }

            this.row = row;
        },
        /*编辑弹窗取值方法*/
        handleEdit(index, row) {
            this.newformEdit = {
                contract: row.contract,
                buyPercent: row.buyPercent,

                pointCount: row.pointCount,
                poundage: row.poundage,
                pointCountUp: row.pointCountUp,
                pointCountDown: row.pointCountDown,
                deliveryMin: row.deliveryMin,
                deliveryMax: row.deliveryMax,
                minProfitPercent: row.minProfitPercent * 100,

                maxBuyCountPerDay: row.maxBuyCountPerDay,
                maxPositionCount: row.maxPositionCount,
                minGramPerOrder: row.minGramPerOrder,
                name: row.name,
                stopProfitSet: row.stopProfitSet * 100,
                volatility: row.volatility,
                stopLossSet: row.stopLossSet * 100,
                minLossPercent: row.minLossPercent * 100,

                volatilityProfitLoss: row.volatilityProfitLoss
            };
            this.status = row.status;
            this.id = row.id;
            this.dialogFormVisibleEdit = true;
        },
        /*提交编辑*/
        confirmEdit() {
            var params = new URLSearchParams();
            params.append("name", this.newformEdit.name);
            params.append("contract", this.newformEdit.contract);
            params.append("buyPercent", this.newformEdit.buyPercent);
            params.append("poundage", this.newformEdit.poundage);
            params.append("pointCount", this.newformEdit.pointCount);

            params.append("pointCountUp", this.newformEdit.pointCountUp);
            params.append("pointCountDown", this.newformEdit.pointCountDown);
            params.append("deliveryMin", this.newformEdit.deliveryMin);
            params.append("deliveryMax", this.newformEdit.deliveryMax);
            params.append("minProfitPercent", this.newformEdit.minProfitPercent / 100);

            params.append("maxBuyCountPerDay", this.newformEdit.maxBuyCountPerDay);
            params.append("maxPositionCount", this.newformEdit.maxPositionCount);
            params.append("minGramPerOrder", this.newformEdit.minGramPerOrder);
            params.append("stopProfitSet", this.newformEdit.stopProfitSet / 100);
            params.append("volatility", this.newformEdit.volatility);
            params.append("stopLossSet", this.newformEdit.stopLossSet / 100);
            params.append("minLossPercent", this.newformEdit.minLossPercent / 100);

            params.append("volatilityProfitLoss", this.newformEdit.volatilityProfitLoss);
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
        forceTrading() {
            let _this = this;
            axios.post(this.forceUrl).then(function (response) {
                if (response.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleForceTrading = false;
                    _this.loadData();
                } else if (!response.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleForceTrading = false;
                } else {
                    _this.dialogFormVisibleForceTrading = false;
                    _this.$message.error('网络错误');
                }
            });
        },
        closeTrading() {
            var params = new URLSearchParams();
            let _this = this;
            params.append("status", 2);
            params.append("id", this.row.id);
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleCloseTrading = false;
                    _this.loadData();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleCloseTrading = false;
                } else {
                    _this.dialogFormVisibleCloseTrading = false;
                    _this.$message.error('网络错误');
                }
            });
        },
        openTrading(row) {
            var params = new URLSearchParams();
            let _this = this;
            params.append("id", this.row.id);
            params.append("status", 1);
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleOpenTrading = false;
                    _this.loadData();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleOpenTrading = false;
                } else {
                    _this.dialogFormVisibleOpenTrading = false;
                    _this.$message.error('网络错误');
                }
            });
        },

        convertstopLossSet(value) {
            return value.stopLossSet * 100 + "%";
        },
        convertminLossPercent(value) {
            return value.minLossPercent * 100 + "%";
        },
        convertstopProfitSet(value) {
            return value.stopProfitSet * 100 + "%";
        },
        convertvolatilityProfitLoss(value) {
            return value.volatilityProfitLoss * 100 + "%";
        },
        minProfitPercent(value) {
            return value.minProfitPercent * 100 + "%";
        },

        trading() {
            var params = new URLSearchParams();
            let _this = this;
            params.append("status", 0);
            params.append("id", this.row.id);
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleTrading = false;
                    _this.loadData();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleTrading = false;
                } else {
                    _this.dialogFormVisibleTrading = false;
                    _this.$message.error('网络错误');
                }
            });
        },
        convertblowingUpSet(value) {
            return value.blowingUpSet * 100 + "%";
        },
        convertstopProfitSet(value) {
            return value.stopProfitSet * 100 + "%";
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

/***/ 793:
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
      "label": "产品名称",
      "prop": "name",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "品种合约（克）",
      "prop": "contract",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买入金额",
      "prop": "buyPercent",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "手续费",
      "prop": "poundage",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "点差",
      "prop": "pointCount",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "单笔最小下单克重（克）",
      "prop": "minGramPerOrder",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "交割最小百分比",
      "prop": "deliveryMin",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "交割最大百分比",
      "prop": "deliveryMax",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买入金额",
      "prop": "buyPercent",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买涨点差",
      "prop": "pointCountUp",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "买跌点差",
      "prop": "pointCountDown",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "品种合约（克）",
      "prop": "contract",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最小波动点数",
      "prop": "volatility",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最大持仓（克）",
      "prop": "maxPositionCount",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最大建仓次数",
      "prop": "maxBuyCountPerDay",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最大止损设置",
      "prop": "stopLossSet",
      "formatter": _vm.convertstopLossSet,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最大止盈设置",
      "prop": "stopProfitSet",
      "formatter": _vm.convertstopProfitSet,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最小止损设置",
      "prop": "minLossPercent",
      "formatter": _vm.convertminLossPercent,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最低止盈设置",
      "prop": "minProfitPercent",
      "formatter": _vm.minProfitPercent,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "最小波动盈亏",
      "prop": "volatilityProfitLoss",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "500"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "disabled": scope.row.status == 2,
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.confirmDialog(2, scope.row)
            }
          }
        }, [_vm._v("闭市（运营）")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "disabled": scope.row.status == 0,
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.confirmDialog(0, scope.row)
            }
          }
        }, [_vm._v("开市（运营）")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "disabled": scope.row.status == 1,
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.confirmDialog(1, scope.row)
            }
          }
        }, [_vm._v("开市（研发）")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.confirmDialog(3, scope.row)
            }
          }
        }, [_vm._v("强制平仓")]), _vm._v(" "), _c('el-button', {
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
      "width": "40%"
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
      "label": "单笔最小下单克重",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.minGramPerOrder),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "minGramPerOrder", $$v)
      },
      expression: "newformEdit.minGramPerOrder"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "手续费",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.poundage),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "poundage", $$v)
      },
      expression: "newformEdit.poundage"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "点差",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.pointCount),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "pointCount", $$v)
      },
      expression: "newformEdit.pointCount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买入金额",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.buyPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "buyPercent", $$v)
      },
      expression: "newformEdit.buyPercent"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买涨点差",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.pointCountUp),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "pointCountUp", $$v)
      },
      expression: "newformEdit.pointCountUp"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "买跌点差",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.pointCountDown),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "pointCountDown", $$v)
      },
      expression: "newformEdit.pointCountDown"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交割最小百分比",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.deliveryMin),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "deliveryMin", $$v)
      },
      expression: "newformEdit.deliveryMin"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交割最大百分比",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.deliveryMax),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "deliveryMax", $$v)
      },
      expression: "newformEdit.deliveryMax"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "品种合约",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.contract),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "contract", $$v)
      },
      expression: "newformEdit.contract"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大持仓",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.maxPositionCount),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "maxPositionCount", $$v)
      },
      expression: "newformEdit.maxPositionCount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大建仓次数",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.maxBuyCountPerDay),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "maxBuyCountPerDay", $$v)
      },
      expression: "newformEdit.maxBuyCountPerDay"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大止损设置",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.stopLossSet),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "stopLossSet", $$v)
      },
      expression: "newformEdit.stopLossSet"
    }
  })], 1), _vm._v("\n                        %\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最大止盈设置",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.stopProfitSet),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "stopProfitSet", $$v)
      },
      expression: "newformEdit.stopProfitSet"
    }
  })], 1), _vm._v("\n                        %\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最小止盈设置",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.minProfitPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "minProfitPercent", $$v)
      },
      expression: "newformEdit.minProfitPercent"
    }
  })], 1), _vm._v("\n                        %\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最小止损设置",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.minLossPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "minLossPercent", $$v)
      },
      expression: "newformEdit.minLossPercent"
    }
  })], 1), _vm._v("\n                       %\n                ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最小波动盈亏",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.volatilityProfitLoss),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "volatilityProfitLoss", $$v)
      },
      expression: "newformEdit.volatilityProfitLoss"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "最小波动点数",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.volatility),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "volatility", $$v)
      },
      expression: "newformEdit.volatility"
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
  }, [_vm._v("确 定")])], 1)], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "关闭交易",
      "visible": _vm.dialogFormVisibleCloseTrading,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleCloseTrading = $event
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
        _vm.dialogFormVisibleCloseTrading = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.closeTrading
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "开启交易",
      "visible": _vm.dialogFormVisibleOpenTrading,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleOpenTrading = $event
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
        _vm.dialogFormVisibleOpenTrading = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.openTrading
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "强制平仓",
      "visible": _vm.dialogFormVisibleForceTrading,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleForceTrading = $event
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
        _vm.dialogFormVisibleForceTrading = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.forceTrading
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "正常交易",
      "visible": _vm.dialogFormVisibleTrading,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleTrading = $event
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
        _vm.dialogFormVisibleTrading = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.trading
    }
  }, [_vm._v("确 定")])], 1)])], 1)])
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
  }, [_vm._v("金权规则设定")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 874:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(700);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("2e18e3fa", content, true);

/***/ })

});