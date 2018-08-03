webpackJsonp([25],{

/***/ 566:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(883)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(651),
  /* template */
  __webpack_require__(804),
  /* scopeId */
  "data-v-6527b4aa",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 651:
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            editstate: false,
            show: false,
            loading: true,
            actorList: "",
            num: "",
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
                productNo: "",
                productName: "",
                cycle: "",
                yearIncomPercent: "",
                minMoney: "",
                calcMethod: "",
                redeemMethod: "",
                settleMethod: "",
                nper: ""
            },
            newformEdit1: {
                productNo: "",
                productName: "",
                cycle: "",
                yearIncomPercent: "",
                calcStartPoint: "",
                calcMethod: "",
                redeemMethod: "",
                settleMethod: ""
            },
            newformEdit2: {
                productNo: "",
                productName: "",
                cycle: "",
                yearIncomPercent: "",
                calcStartPoint: "",
                calcMethod: "",
                redeemMethod: "",
                settleMethod: ""
            },

            url: this.api + "financeConf/getAllFinanceConf",
            url1: this.api + "realGoldBuyConf/getRealGoldBuyConf",
            url2: this.api + "financeConf/getAllFinanceConfForNew",
            editUrl: this.api + "financeConf/modifyFinanceConf",
            editUrl1: this.api + "realGoldBuyConf/editRealGoldBuyConf",
            editUrl2: this.api + "financeConf/modifyFinanceConfForNew",
            deleteUrl: this.api + "financeConf/removeFinanceConfById",
            deleteUrl1: this.api + "realGoldBuyConf/removeRealGoldBuyConf",
            deleteUrl2: this.api + "financeConf/removeFinanceConfForNew",
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData1: [],
            tableData2: [],
            tableData3: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleEditForNew: false,
            dialogFormVisibleDelete: false,
            dialogFormVisibleEditGold: false,
            formLabelWidth: '120px',
            productId: "",

            settleMethods: [{
                id: 1,
                name: "期限任选，赎回时付"
            }, {
                id: 2,
                name: "期限任选，按月付息"
            }, {
                id: 3,
                name: "期限任选，按天付息"
            }, {
                id: 4,
                name: "期限任选，到期还息"
            }],
            redeemMethods: [{
                id: 1,
                name: "到期后自由赎回"
            }, {
                id: 2,
                name: "到期后自动赎回"
            }]

        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        this.loadData1();
        this.loadData2();
    },
    filters: {
        format: function (val) {
            var a = new Decimal(val);
            var b = new Decimal(100);
            return a.times(b);
        }
    },
    //定义方法
    methods: {
        editState(num) {
            if (num === 0) return this.editstate = true;else return this.editstate = false;
        },
        //  编辑按钮
        handleEdit(num, row) {
            var calcStartPoint = null;
            if (row.nper != 0) {
                this.show = true;
            } else {
                this.show = false;
            }
            this.editState(row.calcMethod);
            if (num == 2) {
                calcStartPoint = row.calcStartPoint;
                this.newformEdit1 = {
                    productNo: row.productNo,
                    productName: row.name,
                    cycle: row.cycle,
                    yearIncomPercent: new Decimal(Number(row.yearIncomPercent)).times(new Decimal(100)).toString(),
                    calcStartPoint: row.calcStartPoint,
                    calcMethod: row.calcMethod,
                    redeemMethod: row.redeemMethod,
                    settleMethod: row.settleMethod

                };
            }
            if (num == 3) {
                calcStartPoint = row.calcStartPoint;
                this.newformEdit2 = {
                    productNo: row.productNo,
                    productName: row.productName,
                    cycle: row.cycle,
                    yearIncomPercent: new Decimal(Number(row.yearIncomPercent)).times(new Decimal(100)).toString(),
                    minWeight: row.minWeight,
                    calcMethod: row.calcMethod,
                    redeemMethod: row.redeemMethod,
                    settleMethod: row.settleMethod

                };
            } else {
                this.newformEdit = {
                    productNo: row.productNo,
                    productName: row.productName,
                    cycle: row.cycle,
                    yearIncomPercent: new Decimal(Number(row.yearIncomPercent)).times(new Decimal(100)).toString(),
                    minMoney: row.minMoney / 100,
                    calcMethod: row.calcMethod,
                    redeemMethod: row.redeemMethod,
                    settleMethod: row.settleMethod,
                    nper: row.nper
                };
            }

            //存储 理财产品id

            this.productId = row.id;

            if (num == 1) {
                this.type = row.type;
                this.dialogFormVisibleEdit = true;
            } else if (num == 2) {
                this.dialogFormVisibleEditGold = true;
            } else {
                this.dialogFormVisibleEditForNew = true;
            }
        },
        convertRedeemMethod(value) {
            return value.redeemMethod == 1 ? "到期后自由赎回" : "到期后自动赎回";
        },
        convertYearIncomPercent(row) {
            var a = new Decimal(row.yearIncomPercent);
            var b = new Decimal(100);
            return a.times(b) + '%';
        },
        convertMinMoney(value) {
            return value.minMoney / 100;
        },
        convertCalcMethod(value) {
            return value.calcMethod == null ? "-" : value.calcMethod === 0 ? "-" : "T+" + value.calcMethod;
        },
        convertNper(value) {
            return value.nper == null ? "-" : value.nper === 0 ? "-" : +value.nper;
        },
        convertSettleMethod(value) {

            if (value.settleMethod == 1) return "期限任选，赎回时付";
            if (value.settleMethod == 2) return "期限任选，按月付息";
            if (value.settleMethod == 3) return "期限任选，按天付息";
            if (value.settleMethod == 4) return "期限任选，到期还息";
        },
        //  确定编辑方法
        confirmAddEdit() {
            var params = new URLSearchParams();
            params.append('productNo', this.newformEdit.productNo);
            params.append('productName', this.newformEdit.productName);
            params.append('cycle', this.newformEdit.cycle);
            params.append('yearIncomPercent', this.newformEdit.yearIncomPercent / 100);
            params.append('minMoney', this.newformEdit.minMoney * 100);
            params.append('calcMethod', this.newformEdit.calcMethod);
            params.append('redeemMethod', this.newformEdit.redeemMethod);
            params.append('settleMethod', this.newformEdit.settleMethod);
            params.append('id', this.productId);
            params.append('type', this.type);
            params.append('nper', this.newformEdit.nper);
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData(_this.pageSize, 1);
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
            }).catch(function (error) {
                _this.dialogFormVisibleEdit = false;
                _this.$message.error('网络错误');
            });
        },
        confirmAddEdit2() {
            var params = new URLSearchParams();
            params.append('yearIncomPercent', this.newformEdit2.yearIncomPercent / 100);
            params.append('id', this.productId);
            let _this = this;
            axios.post(this.editUrl2, params).then(function (response) {
                if (response.data.code === 1000) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEditForNew = false;
                    _this.loadData2();
                } else if (response.data.code === 1001) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEditForNew = false;
                } else {
                    _this.dialogFormVisibleEditForNew = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {
                _this.dialogFormVisibleEdit = false;
                _this.$message.error('网络错误');
            });
        },
        deleteDialog(num, row) {
            if (num == 1) {
                this.dialogFormVisibleDelete = true;
            } else if (num == 2) {
                this.dialogFormVisibleDelete = true;
            } else {
                this.dialogFormVisibleDelete = true;
            }
            this.num = num;
            this.row = row;
        },
        //  确定编辑方法
        confirmAddEdit1() {
            var params = new URLSearchParams();
            params.append('productNo', this.newformEdit1.productNo);
            params.append('name', this.newformEdit1.productName);
            params.append('cycle', this.newformEdit1.cycle);
            params.append('yearIncomPercent', this.newformEdit1.yearIncomPercent / 100);
            params.append('calcStartPoint', this.newformEdit1.calcStartPoint);
            params.append('calcMethod', this.newformEdit1.calcMethod);
            params.append('redeemMethod', this.newformEdit1.redeemMethod);
            params.append('settleMethod', this.newformEdit1.settleMethod);
            params.append('id', this.productId);
            let _this = this;
            axios.post(this.editUrl1, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEditGold = false;
                    _this.loadData(_this.pageSize, 1);
                    _this.loadData1();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '修改失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEditGold = false;
                } else {
                    _this.dialogFormVisibleEditGold = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {
                _this.dialogFormVisibleEditGold = false;
                _this.$message.error('网络错误');
            });
        },

        //删除
        handleDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', this.row.id);
            params.append('type', this.row.type);
            if (this.num == 1) {

                axios.post(this.deleteUrl, params).then(function (response) {
                    if (response.data.data) {
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.dialogFormVisibleDelete = false;
                        _this.loadData(_this.pageSize, 1);
                    } else if (!response.data.data) {
                        _this.$message({
                            message: '删除失败',
                            type: 'warning'
                        });
                        _this.dialogFormVisibleEdit = false;
                    } else {
                        _this.dialogFormVisibleEdit = false;
                        _this.$message.error('网络错误');
                    }
                }).catch(function (error) {});
            } else if (this.num == 2) {
                axios.post(this.deleteUrl1, params).then(function (response) {
                    if (response.data.data) {
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.dialogFormVisibleDelete = false;
                        _this.loadData(_this.pageSize, 1);
                        _this.loadData1();
                    } else if (!response.data.data) {
                        _this.$message({
                            message: '删除失败',
                            type: 'warning'
                        });
                        _this.dialogFormVisibleEdit = false;
                    } else {
                        _this.dialogFormVisibleEdit = false;
                        _this.$message.error('网络错误');
                    }
                }).catch(function (error) {});
            } else {
                axios.post(this.deleteUrl2, params).then(function (response) {
                    console.log(response.data);
                    if (response.data.code == 1000) {
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.dialogFormVisibleDelete = false;
                        _this.loadData2();
                    } else if (response.data.code == 1001) {
                        _this.$message({
                            message: '删除失败',
                            type: 'warning'
                        });
                        _this.dialogFormVisibleDelete = false;
                    } else {
                        _this.dialogFormVisibleDelete = false;
                        _this.$message.error('网络错误');
                    }
                }).catch(function (error) {});
            }
        },

        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            params.append('pageSize', pageSize);
            params.append('pageNum', pageNum);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                _this.currentPage = response.data.data.pageNum;
                _this.pageSize = response.data.data.pageSize;
                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
                _this.tableData1 = response.data.data.list;
            }).catch(function (error) {});
        },
        loadData1() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url1).then(function (response) {
                _this.tableData2 = response.data.data;
            }).catch(function (error) {});
        },
        loadData2() {
            var params = new URLSearchParams();
            let _this = this;
            axios.post(this.url2).then(function (response) {
                _this.tableData3 = response.data.data;
            }).catch(function (error) {});
        }
    }
});

/***/ }),

/***/ 713:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 804:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("理财产品设定")]), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
    attrs: {
      "data": _vm.tableData1,
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
      "prop": "nper",
      "label": "期数",
      "width": "100",
      "formatter": _vm.convertNper
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "yearIncomPercent",
      "formatter": _vm.convertYearIncomPercent,
      "label": "年化收益率",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "minMoney",
      "formatter": _vm.convertMinMoney,
      "label": "起投金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "calcMethod",
      "formatter": _vm.convertCalcMethod,
      "label": "计息方式",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "redeemMethod",
      "formatter": _vm.convertRedeemMethod,
      "label": "赎回方式",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "settleMethod",
      "formatter": _vm.convertSettleMethod,
      "label": "结算方式",
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
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(1, scope.row)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "width": "30%",
      "visible": _vm.dialogFormVisibleEdit,
      "center": ""
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
      value: (_vm.newformEdit.productNo),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "productNo", $$v)
      },
      expression: "newformEdit.productNo"
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
      value: (_vm.newformEdit.productName),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "productName", $$v)
      },
      expression: "newformEdit.productName"
    }
  })], 1), _vm._v(" "), (false) ? [_c('el-form-item', {
    attrs: {
      "label": "期数："
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.nper),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "nper", $$v)
      },
      expression: "newformEdit.nper"
    }
  })], 1)] : _vm._e(), _vm._v(" "), _c('el-form-item', {
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
      value: (_vm.newformEdit.cycle),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "cycle", $$v)
      },
      expression: "newformEdit.cycle"
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
      value: (_vm.newformEdit.yearIncomPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "yearIncomPercent", $$v)
      },
      expression: "newformEdit.yearIncomPercent"
    }
  })], 1), _vm._v("\n                             %\n\n\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "起投金额：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.minMoney),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "minMoney", $$v)
      },
      expression: "newformEdit.minMoney"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "计息方式：T+",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.calcMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "calcMethod", $$v)
      },
      expression: "newformEdit.calcMethod"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "disabled": true,
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newformEdit.redeemMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "redeemMethod", $$v)
      },
      expression: "newformEdit.redeemMethod"
    }
  }, _vm._l((_vm.redeemMethods), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
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
      value: (_vm.newformEdit.settleMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "settleMethod", $$v)
      },
      expression: "newformEdit.settleMethod"
    }
  }, _vm._l((_vm.settleMethods), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1)], 2), _vm._v(" "), _c('div', {
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
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "删除",
      "visible": _vm.dialogFormVisibleDelete,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleDelete = $event
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
        _vm.dialogFormVisibleDelete = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.handleDelete
    }
  }, [_vm._v("确 定")])], 1)])], 1), _vm._v(" "), _c('h2', [_vm._v("      新手专属")]), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
    attrs: {
      "data": _vm.tableData3,
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
      "prop": "cycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "yearIncomPercent",
      "formatter": _vm.convertYearIncomPercent,
      "label": "年化收益率",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "minWeight",
      "label": "起投黄金重量(克)",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "calcMethod",
      "formatter": _vm.convertCalcMethod,
      "label": "计息方式",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "redeemMethod",
      "formatter": _vm.convertRedeemMethod,
      "label": "赎回方式",
      "width": "240"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "settleMethod",
      "formatter": _vm.convertSettleMethod,
      "label": "结算方式",
      "width": "240"
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
              _vm.handleEdit(3, scope.row)
            }
          }
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(3, scope.row)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEditForNew,
      "width": "30%",
      "center": ""
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEditForNew = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEdit2
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
      value: (_vm.newformEdit2.productNo),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "productNo", $$v)
      },
      expression: "newformEdit2.productNo"
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
      value: (_vm.newformEdit2.productName),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "productName", $$v)
      },
      expression: "newformEdit2.productName"
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
      value: (_vm.newformEdit2.cycle),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "cycle", $$v)
      },
      expression: "newformEdit2.cycle"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "年化收益率：",
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
      value: (_vm.newformEdit2.yearIncomPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "yearIncomPercent", $$v)
      },
      expression: "newformEdit2.yearIncomPercent"
    }
  })], 1), _vm._v("  %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "起投黄金重量：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit2.minWeight),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "minWeight", $$v)
      },
      expression: "newformEdit2.minWeight"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "计息方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_vm._v("\n                          T +  \n                      ")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit2.calcMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "calcMethod", $$v)
      },
      expression: "newformEdit2.calcMethod"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "disabled": true,
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newformEdit2.redeemMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "redeemMethod", $$v)
      },
      expression: "newformEdit2.redeemMethod"
    }
  }, _vm._l((_vm.redeemMethods), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
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
      value: (_vm.newformEdit2.settleMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit2, "settleMethod", $$v)
      },
      expression: "newformEdit2.settleMethod"
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
        _vm.dialogFormVisibleEditForNew = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmAddEdit2
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1), _vm._v(" "), _c('h2', [_vm._v("      贵金属")]), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    staticStyle: {
      "width": "auto",
      "display": "inline-block"
    },
    attrs: {
      "data": _vm.tableData2,
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
      "prop": "name",
      "label": "产品名称",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "yearIncomPercent",
      "formatter": _vm.convertYearIncomPercent,
      "label": "年化收益率",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "calcStartPoint",
      "label": "计息起点",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "calcMethod",
      "formatter": _vm.convertCalcMethod,
      "label": "计息方式",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "redeemMethod",
      "formatter": _vm.convertRedeemMethod,
      "label": "赎回方式",
      "width": "240"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "settleMethod",
      "formatter": _vm.convertSettleMethod,
      "label": "结算方式",
      "width": "240"
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
              _vm.handleEdit(2, scope.row)
            }
          }
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(2, scope.row)
            }
          }
        }, [_vm._v("删除")])]
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
      "model": _vm.newformEdit1
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
      value: (_vm.newformEdit1.productNo),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "productNo", $$v)
      },
      expression: "newformEdit1.productNo"
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
      value: (_vm.newformEdit1.productName),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "productName", $$v)
      },
      expression: "newformEdit1.productName"
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
      value: (_vm.newformEdit1.cycle),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "cycle", $$v)
      },
      expression: "newformEdit1.cycle"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "年化收益率：",
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
      value: (_vm.newformEdit1.yearIncomPercent),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "yearIncomPercent", $$v)
      },
      expression: "newformEdit1.yearIncomPercent"
    }
  })], 1), _vm._v("  %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "计息起点：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit1.calcStartPoint),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "calcStartPoint", $$v)
      },
      expression: "newformEdit1.calcStartPoint"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "计息方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_vm._v("\n                          T +  \n                       ")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "disabled": true,
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit1.calcMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "calcMethod", $$v)
      },
      expression: "newformEdit1.calcMethod"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "赎回方式：",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "disabled": true,
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newformEdit1.redeemMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "redeemMethod", $$v)
      },
      expression: "newformEdit1.redeemMethod"
    }
  }, _vm._l((_vm.redeemMethods), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
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
      value: (_vm.newformEdit1.settleMethod),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit1, "settleMethod", $$v)
      },
      expression: "newformEdit1.settleMethod"
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
        _vm.dialogFormVisibleEditGold = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmAddEdit1
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
  }), _vm._v(" 产品设定")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("理财产品设定")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 883:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(713);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("1eccc23e", content, true);

/***/ })

});