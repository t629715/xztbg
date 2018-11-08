webpackJsonp([57],{

/***/ 540:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(917)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(633),
  /* template */
  __webpack_require__(830),
  /* scopeId */
  "data-v-5b1796be",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 633:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,

            agentOptions: "",
            brokerOptions: "",
            sform: {
                userName: '',
                orderNo: '',
                gtmStartTime: '',
                gtmEndTime: '',
                comStartTime: '',
                comEndTime: '',
                subStartTime: '',
                subEndTime: '',
                expStartTime: '',
                expEndTime: '',
                agentName: '',
                brokerName: ''
            },
            url: this.api + "goldBuyBackOrder/selectGoldBuyBackOrder",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            brokerUrl1: this.api + "user/selectByBrokerMessage1",
            exportUrl: this.api + "goldBuyBackOrder/excelGoldBuyBackOrder",
            editUrl: this.api + "goldBuyBackOrder/updateState",
            cancelUrl: this.api + "goldBuyBackOrder/cancelState",
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisibleEdit: false,
            dialogFormVisibleCancel: false,
            formLabelWidth: '120px',
            userId: "",
            id: ""
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        let _this = this;
        //初始化代理商下拉列表
        axios.get(this.agentUrl).then(function (response) {
            _this.agentOptions = response.data.data;
        }).catch(function (error) {
            console.log(error);
        });
        /*axios.get(this.brokerUrl1)
            .then(function (response) {
                _this.brokerOptions = response.data.data;
            })
            .catch(function (error) {
                console.log(error);
            });*/
    },
    //定义方法
    methods: {
        //查询
        chooseTime(value) {
            let _this = this;
            /* if (value === "") {
                 _this.disabledEdit = true;
              } else {
                 _this.disabledEdit = false;
             }*/
        },
        onSubmit(sform) {

            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';
            var date3 = '';
            var date4 = '';
            var date5 = '';
            var date6 = '';
            var date7 = '';
            var date8 = '';
            if (sform.gtmStartTime != "") {
                date1 = this.dateFormat(sform.gtmStartTime);
            }
            if (sform.gtmEndTime != "") {
                date2 = this.dateFormat(sform.gtmEndTime);
            }
            if (sform.comStartTime != "") {
                date3 = this.dateFormat(sform.comStartTime);
            }
            if (sform.comEndTime != "") {
                date4 = this.dateFormat(sform.comEndTime);
            }
            if (sform.subStartTime != "") {
                date5 = this.dateFormat(sform.subStartTime);
            }
            if (sform.subEndTime != "") {
                date6 = this.dateFormat(sform.subEndTime);
            }
            if (sform.expStartTime != "") {
                date7 = this.dateFormat(sform.expStartTime);
            }
            if (sform.expEndTime != "") {
                date8 = this.dateFormat(sform.expEndTime);
            }
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('orderNo', sform.orderNo);
            params.append('gtmStartTime', date1);
            params.append('gtmEndTime', date2);
            params.append('comStartTime', date3);
            params.append('comEndTime', date4);
            params.append('subStartTime', date5);
            params.append('subEndTime', date6);
            params.append('expStartTime', date7);
            params.append('expEndTime', date8);
            params.append('userName', sform.userName);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);

            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: '查询成功',
                        type: 'success'
                    });
                    var list = response.data.data.list;
                    _this.currentPage = 1;
                    //_this.pagesize = response.data.data.pageSize;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = list;
                } else {
                    _this.$message({
                        message: '查询失败',
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});
        },
        //收货确认无误
        confirmationOfReceipt(index, row) {
            this.dialogFormVisibleEdit = true;
            this.row = row;
        },
        //收货确认无误
        handleEdit(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', _this.row.id);
            axios.post(this.editUrl, params).then(function (response) {

                if (response.data.code == 1001) {
                    _this.$message({
                        message: '操作成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData(_this.pagesize, 1);
                } else {
                    _this.$message({
                        message: '操作失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit = false;
                }
            }).catch(function (error) {});
        },
        //取消
        cancelOfReceipt(index, row) {
            this.dialogFormVisibleCancel = true;
            this.row = row;
        },
        handleCancel(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', _this.row.id);
            axios.post(this.cancelUrl, params).then(function (response) {

                if (response.data.code == 1001) {
                    _this.$message({
                        message: '操作成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleCancel = false;
                    _this.loadData(_this.pagesize, 1);
                } else {
                    _this.$message({
                        message: '操作失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleCancel = false;
                }
            }).catch(function (error) {});
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';
            var date3 = '';
            var date4 = '';
            var date5 = '';
            var date6 = '';
            var date7 = '';
            var date8 = '';
            if (this.sform.gtmStartTime != "") {
                date1 = this.dateFormat(this.sform.gtmStartTime);
            }
            if (this.sform.gtmEndTime != "") {
                date2 = this.dateFormat(this.sform.gtmEndTime);
            }
            if (this.sform.comStartTime != "") {
                date3 = this.dateFormat(this.sform.comStartTime);
            }
            if (this.sform.comEndTime != "") {
                date4 = this.dateFormat(this.sform.comEndTime);
            }
            if (this.sform.subStartTime != "") {
                date5 = this.dateFormat(this.sform.subStartTime);
            }
            if (this.sform.subEndTime != "") {
                date6 = this.dateFormat(this.sform.subEndTime);
            }
            if (this.sform.expStartTime != "") {
                date7 = this.dateFormat(this.sform.expStartTime);
            }
            if (this.sform.expEndTime != "") {
                date8 = this.dateFormat(this.sform.expEndTime);
            }
            this.pagesize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('orderNo', this.sform.orderNo);
            params.append('gtmStartTime', date1);
            params.append('gtmEndTime', date2);
            params.append('comStartTime', date3);
            params.append('comEndTime', date4);
            params.append('subStartTime', date5);
            params.append('subEndTime', date6);
            params.append('expStartTime', date7);
            params.append('expEndTime', date8);
            params.append('userName', this.sform.userName);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);

            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data.list;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = list;
                }
            }).catch(function (error) {});
        },
        //根据代理商取经纪人列表
        getBrokerOptions() {
            let _this = this;
            _this.sform.brokerName = [];
            var params = new URLSearchParams();

            if (_this.sform.agentName == null || _this.sform.agentName == "") {
                _this.brokerOptions = [];
                return;
            }
            params.append('pid', _this.sform.agentName);
            axios.post(_this.brokeUrl, params).then(function (response) {
                _this.brokerOptions = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        //清空
        resetForm(sform) {
            this.$refs.sform.resetFields();
            this.loadData(10, 1);
        },

        //导出
        onExport(sform) {
            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';
            var date3 = '';
            var date4 = '';
            var date5 = '';
            var date6 = '';
            var date7 = '';
            var date8 = '';
            if (sform.gtmStartTime != "") {
                date1 = this.dateFormat(sform.gtmStartTime);
            }
            if (sform.gtmEndTime != "") {
                date2 = this.dateFormat(sform.gtmEndTime);
            }
            if (sform.comStartTime != "") {
                date3 = this.dateFormat(sform.comStartTime);
            }
            if (sform.comEndTime != "") {
                date4 = this.dateFormat(sform.comEndTime);
            }
            if (sform.subStartTime != "") {
                date5 = this.dateFormat(sform.subStartTime);
            }
            if (sform.subEndTime != "") {
                date6 = this.dateFormat(sform.subEndTime);
            }
            if (sform.expStartTime != "") {
                date7 = this.dateFormat(sform.expStartTime);
            }
            if (sform.expEndTime != "") {
                date8 = this.dateFormat(sform.expEndTime);
            }
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('orderNo', sform.orderNo);
            params.append('gtmStartTime', date1);
            params.append('gtmEndTime', date2);
            params.append('comStartTime', date3);
            params.append('comEndTime', date4);
            params.append('subStartTime', date5);
            params.append('subEndTime', date6);
            params.append('expStartTime', date7);
            params.append('expEndTime', date8);
            params.append('userName', sform.userName);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);

            console.info(this.exportUrl + "?" + params);
            window.location = this.exportUrl + "?" + params;
        },

        //当前页改变是执行
        handleCurrentChange(val) {
            /*this.currentPage = val;
            this.onSubmit(this.sform);*/
            this.loadData(this.pagesize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            /*this.pageSize = val;
            this.pageNum = 1;
            this.onSubmit(this.sform);*/
            this.loadData(val, 1);
        },
        //时间格式化
        dateFormat(date) {
            var dateobj = new Date(date);
            var y = dateobj.getFullYear();
            var m = dateobj.getMonth() + 1;
            m = m < 10 ? '0' + m : m;
            var d = dateobj.getDate();
            d = d < 10 ? '0' + d : d;
            var h = dateobj.getHours();
            h = h < 10 ? '0' + h : h;
            var minute = dateobj.getMinutes();
            var second = dateobj.getSeconds();
            minute = minute < 10 ? '0' + minute : minute;
            second = second < 10 ? '0' + second : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        }
    }
});

/***/ }),

/***/ 731:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-5b1796be]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-5b1796be]{border-radius:4px}.bg-purple-dark[data-v-5b1796be]{background:#99a9bf}.bg-color1[data-v-5b1796be]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-5b1796be]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-5b1796be]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-5b1796be]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-5b1796be]{background:#d3dce6}.bg-purple-light[data-v-5b1796be]{background:#e5e9f2}.gridBox[data-v-5b1796be]{padding-left:20px}.grid-content[data-v-5b1796be]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-5b1796be]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 830:
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
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.sform,
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户账号：",
      "prop": "userName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
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
      "label": "订单号：",
      "prop": "orderNo"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.orderNo),
      callback: function($$v) {
        _vm.$set(_vm.sform, "orderNo", $$v)
      },
      expression: "sform.orderNo"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "创建时间：",
      "prop": "gtmStartTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.gtmStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "gtmStartTime", $$v)
      },
      expression: "sform.gtmStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "gtmEndTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.gtmEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "gtmEndTime", $$v)
      },
      expression: "sform.gtmEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "提交时间：",
      "prop": "subStartTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.subStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "subStartTime", $$v)
      },
      expression: "sform.subStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "subEndTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.subEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "subEndTime", $$v)
      },
      expression: "sform.subEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "过期时间：",
      "prop": "expStartTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.expStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "expStartTime", $$v)
      },
      expression: "sform.expStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "expEndTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.expEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "expEndTime", $$v)
      },
      expression: "sform.expEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "确认时间：",
      "prop": "comStartTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.comStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "comStartTime", $$v)
      },
      expression: "sform.comStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "comEndTime"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('el-date-picker', {
    attrs: {
      "size": "small",
      "type": "datetime",
      "placeholder": "选择日期时间",
      "align": "right"
    },
    model: {
      value: (_vm.sform.comEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "comEndTime", $$v)
      },
      expression: "sform.comEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "代理商：",
      "prop": "agentName"
    }
  }, [_c('el-select', {
    staticClass: "demo",
    attrs: {
      "multiple": "multiple",
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerOptions()
      }
    },
    model: {
      value: (_vm.sform.agentName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "agentName", $$v)
      },
      expression: "sform.agentName"
    }
  }, _vm._l((_vm.agentOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "经纪人：",
      "prop": "brokerName"
    }
  }, [_c('el-select', {
    staticClass: "demo2",
    attrs: {
      "multiple": "multiple",
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.brokerName),
      callback: function($$v) {
        _vm.$set(_vm.sform, "brokerName", $$v)
      },
      expression: "sform.brokerName"
    }
  }, _vm._l((_vm.brokerOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.sform)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.sform)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "info"
    },
    on: {
      "click": function($event) {
        _vm.onExport(_vm.sform)
      }
    }
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "width": "100",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "orderNo",
      "label": "订单号",
      "width": "180",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userName",
      "label": "账户名",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "agentName",
      "label": "代理商",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "brokerName",
      "label": "经纪人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldWeight",
      "label": "订单克数",
      "width": "220"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "recoverPrice",
      "label": "预约价格",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "repurchaseAmount",
      "label": "回购金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "senderName",
      "label": "寄件人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "senderPhone",
      "label": "联系电话",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "订单状态",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "trackingNum",
      "label": "物流单号",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gtmCreate",
      "label": "创建时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "expireTime",
      "label": "过期时间/取消时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "submitTime",
      "label": "提交时间",
      "width": "250"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "completeTime",
      "label": "确认时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "200"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.status == '已生效') ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.confirmationOfReceipt(scope.$index, scope.row)
            }
          }
        }, [_vm._v("收货核实无误\n                        ")])] : _vm._e(), _vm._v(" "), (scope.row.status == '已生效') ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.cancelOfReceipt(scope.$index, scope.row)
            }
          }
        }, [_vm._v("取消\n                        ")])] : _vm._e()]
      }
    }])
  })], 1), _vm._v(" "), _c('br'), _c('br'), _vm._v(" "), _c('el-pagination', {
    attrs: {
      "current-page": _vm.currentPage,
      "page-sizes": [10, 20, 30, 40],
      "page-size": _vm.pagesize,
      "layout": "total, sizes, prev, pager, next, jumper",
      "total": _vm.totalNum
    },
    on: {
      "size-change": _vm.handleSizeChange,
      "current-change": _vm.handleCurrentChange
    }
  }), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "提示",
      "width": "30%",
      "visible": _vm.dialogFormVisibleEdit
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEdit = $event
      }
    }
  }, [_c('span', [_vm._v("确定执行此操作？")]), _vm._v(" "), _c('span', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleEdit = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.handleEdit
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "提示",
      "width": "30%",
      "visible": _vm.dialogFormVisibleCancel
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleCancel = $event
      }
    }
  }, [_c('span', [_vm._v("确定执行此操作？")]), _vm._v(" "), _c('span', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleCancel = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.handleCancel
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
  }), _vm._v(" 出入金管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("回购订单")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 917:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(731);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("f8bfac26", content, true);

/***/ })

});