webpackJsonp([56],{

/***/ 536:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(913)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(625),
  /* template */
  __webpack_require__(830),
  /* scopeId */
  "data-v-74687191",
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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

            stateOptions: [{
                value: "",
                label: "全部"
            }, {
                value: 0,
                label: '未支付'
            }, {
                value: 10,
                label: '未发货'
            }, {
                value: 20,
                label: '已发货'
            }, {
                value: 30,
                label: '已完成'
            }, {
                value: 100,
                label: '已作废'
            }],

            payTypeOptions: [{
                value: "",
                label: "全部"
            }, {
                value: 3,
                label: '手动交割'
            }, {
                value: 4,
                label: '强制交割'
            }, {
                value: 5,
                label: '选择交割'
            }],
            orderTotal: {
                logisticsFeeSum: "0",

                investGoldServiceSum: "0",
                poundageSum: "0",
                totalPoundage: "0",
                goldNumSum: "0",
                goldMoneySum: "0",
                processingServiceSum: "0",
                invoiceServiceSum: "0"
            },
            agentOptions: "",
            brokerOptions: "",
            sform: {
                userName: '',
                startTime: '',
                endTime: '',
                status: '',
                agentName: '',
                brokerName: '',
                payType: '',
                investGoldId: ''
            },
            newformAdd: {
                id: '',
                logisticsNo: ''
            },
            newformEdit: {
                logisticsNo: ''
            },
            rules: {
                logisticsNo: [{ required: true, message: '请填写物流单号', trigger: 'blur' }]
            },
            url: this.api + "inVestGoldOrder/selectByAllDelivery",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            brokerUrl1: this.api + "user/selectByBrokerMessage1",
            exportUrl: this.api + "inVestGoldOrder/excelAllDelivery",
            countUrl: this.api + "inVestGoldOrder/countByAllDelivery",
            addUrl: this.api + 'inVestGoldOrder/addLogisticsNoById',
            editUrl: this.api + 'inVestGoldOrder/updateLogisticsNoById',
            completeUrl: this.api + 'inVestGoldOrder/completeOrderById',
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            //disabledEdit: true,
            dialogFormVisible: false,
            dialogFormVisibleAdd: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleComplete: false,
            formLabelWidth: '120px',
            userId: ""
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
        //获取统计数据
        axios.get(this.countUrl).then(function (response) {
            if (response.data.code == 1001) {
                _this.orderTotal = response.data.data;
            }
        }).catch(function (error) {
            console.log(error);
        });
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

            if (sform.startTime != "") {
                date1 = this.dateFormat(sform.startTime);
            }
            if (sform.endTime != "") {
                date2 = this.dateFormat(sform.endTime);
            }

            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('userName', sform.userName);
            params.append('status', sform.status);

            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            params.append('payType', sform.payType);
            params.append('investGoldId', sform.investGoldId);

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

            //获取统计金额
            axios.post(this.countUrl, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.orderTotal = response.data.data;
                }
            }).catch(function (error) {
                console.log(error);
            });
        },

        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            var date1 = '';
            var date2 = '';

            if (this.sform.startTime != "") {
                date1 = this.dateFormat(this.sform.startTime);
            }
            if (this.sform.endTime != "") {
                date2 = this.dateFormat(this.sform.endTime);
            }

            this.pagesize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('userName', this.sform.userName);
            params.append('status', this.sform.status);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);
            params.append('payType', this.sform.payType);

            let _this = this;
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data.list;
                    //_this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
                    //_this.pagesize = response.data.data.pageSize;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = list;
                }
            }).catch(function (error) {});
        },
        //根据代理商取经纪人列表
        getBrokerOptions() {
            let _this = this;
            _this.sform.brokerName = "";
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
            let _this = this;
            //获取统计数据
            axios.get(this.countUrl).then(function (response) {
                if (response.data.code == 1001) {
                    _this.orderTotal = response.data.data;
                }
            }).catch(function (error) {
                console.log(error);
            });
            this.loadData(10, 1);
        },
        //导出
        onExport(sform) {
            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';

            if (sform.startTime != "") {
                date1 = this.dateFormat(sform.startTime);
            }
            if (sform.endTime != "") {
                date2 = this.dateFormat(sform.endTime);
            }

            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('userName', sform.userName);
            params.append('status', sform.status);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            params.append('payType', sform.payType);

            console.info(this.exportUrl + "?" + params);
            window.location = this.exportUrl + "?" + params;
        },
        //新增运单号
        handleAdd(index, row) {
            this.newformAdd = {
                logisticsNo: ''
            };
            this.tqId = row.id;
            this.userId = row.userId;
            this.dialogFormVisibleAdd = true;
        },
        //新增-确定
        confirmAdd(formName) {
            var params = new URLSearchParams();
            params.append('logisticsNo', this.newformAdd.logisticsNo);
            params.append('id', this.tqId);
            params.append('userId', this.userId);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    axios.post(this.addUrl, params).then(function (response) {
                        if (response.data.code == 1000) {
                            _this.$message({
                                message: '操作成功',
                                type: 'success'
                            });
                            _this.dialogFormVisibleAdd = false;
                            _this.loadData(_this.pagesize, 1);
                        } else {
                            _this.$message({
                                message: response.data.msg,
                                type: 'warning'
                            });
                        }
                    }).catch(function (error) {
                        _this.dialogFormVisibleAdd = false;
                        _this.$message.error('网络错误');
                    });
                }
            });
        },
        //编辑运单号
        handleEdit(index, row) {
            this.newformEdit = {
                logisticsNo: row.logisticsNo
            };
            this.tqId = row.id;
            this.userId = row.userId;
            this.dialogFormVisibleEdit = true;
        },
        //编辑-确定
        confirmEdit(formName) {
            var params = new URLSearchParams();
            params.append('logisticsNo', this.newformEdit.logisticsNo);
            params.append('id', this.tqId);
            params.append('userId', this.userId);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    axios.post(this.editUrl, params).then(function (response) {
                        if (response.data.code == 1000) {
                            _this.$message({
                                message: '操作成功',
                                type: 'success'
                            });
                            _this.dialogFormVisibleEdit = false;
                            _this.loadData(_this.pagesize, 1);
                        } else {
                            _this.$message({
                                message: response.data.msg,
                                type: 'warning'
                            });
                        }
                    }).catch(function (error) {
                        _this.dialogFormVisibleEdit = false;
                        _this.$message.error('网络错误');
                    });
                }
            });
        },
        //完成订单
        completeDialog(index, row) {
            this.dialogFormVisibleComplete = true;
            this.row = row;
            this.tqId = row.id;
            this.userId = row.userId;
        },
        //完成订单
        handleComplete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', this.tqId);
            params.append('userId', this.userId);
            axios.post(this.completeUrl, params).then(function (response) {
                if (response.data.code == 1000) {
                    _this.$message({
                        message: '操作成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleComplete = false;
                    _this.loadData(_this.pagesize, 1);
                } else {
                    _this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }
            }).catch(function (error) {
                _this.dialogFormVisibleEdit = false;
                _this.$message.error('网络错误');
            });
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

/***/ 735:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-74687191]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-74687191]{border-radius:4px}.bg-purple-dark[data-v-74687191]{background:#99a9bf}.bg-color1[data-v-74687191]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-74687191]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-74687191]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-74687191]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-74687191]{background:#d3dce6}.bg-purple-light[data-v-74687191]{background:#e5e9f2}.gridBox[data-v-74687191]{padding-left:20px}.grid-content[data-v-74687191]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-74687191]{padding:10px 0;background-color:#f9fafc}", ""]);

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
      "prop": "investGoldId"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.sform.investGoldId),
      callback: function($$v) {
        _vm.$set(_vm.sform, "investGoldId", $$v)
      },
      expression: "sform.investGoldId"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交割状态：",
      "prop": "payType"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.payType),
      callback: function($$v) {
        _vm.$set(_vm.sform, "payType", $$v)
      },
      expression: "sform.payType"
    }
  }, _vm._l((_vm.payTypeOptions), function(item) {
    return _c('el-option', {
      key: item.value,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "订单状态：",
      "prop": "status"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.chooseTime
    },
    model: {
      value: (_vm.sform.status),
      callback: function($$v) {
        _vm.$set(_vm.sform, "status", $$v)
      },
      expression: "sform.status"
    }
  }, _vm._l((_vm.stateOptions), function(item) {
    return _c('el-option', {
      key: item.value,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "交割时间：",
      "prop": "startTime"
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
      value: (_vm.sform.startTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "startTime", $$v)
      },
      expression: "sform.startTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "endTime"
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
      value: (_vm.sform.endTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "endTime", $$v)
      },
      expression: "sform.endTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "代理商：",
      "prop": "agentName"
    }
  }, [_c('el-select', {
    attrs: {
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
    attrs: {
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
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-row', {
    staticClass: "gridBox",
    attrs: {
      "gutter": 20,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color1"
  }, [_c('p', [_vm._v("订单黄金（克）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.goldNumSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("合约总金额（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.goldMoneySum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("交割手续费（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.investGoldServiceSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("交易手续费（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.poundageSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("手续费总计（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.totalPoundage))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color4"
  }, [_c('p', [_vm._v("加工费（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.processingServiceSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color3"
  }, [_c('p', [_vm._v("物流费（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.logisticsFeeSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color4"
  }, [_c('p', [_vm._v("发票费用（元）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.orderTotal.invoiceServiceSum))])])])], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "userName",
      "label": "用户账号",
      "width": "180",
      "fixed": "left"
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
      "prop": "goldTotalWeight",
      "label": "克重",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldBasePrice",
      "label": "买入价",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "goldMoney",
      "label": "合约金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "investGoldService",
      "label": "交割手续费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "poundage",
      "label": "交易手续费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "processingService",
      "label": "加工费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "logisticsFee",
      "label": "物流费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "invoiceService",
      "label": "发票",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalMoney",
      "label": "订单金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "discountAmount",
      "label": "优惠金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "actualPayment",
      "label": "实际/所需支付",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "contactName",
      "label": "联系人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "contactPhone",
      "label": "联系电话",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "deliveryAddress",
      "label": "收货地址",
      "width": "250"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "payTime",
      "label": "支付时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "交割时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "sendTime",
      "label": "发货时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "endTime",
      "label": "完成/作废时间",
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
      "prop": "payType",
      "label": "交割状态",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "id",
      "label": "订单号",
      "width": "240"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "logisticsNo",
      "label": "物流单号",
      "width": "240"
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
        return [(scope.row.status == '未发货') ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.handleAdd(scope.$index, scope.row)
            }
          }
        }, [_vm._v("填入单号\n                        ")])] : _vm._e(), _vm._v(" "), (scope.row.status == '已发货') ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.handleEdit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("修改单号\n                        ")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.completeDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("\n                            订单完成\n                        ")])] : _vm._e()]
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
      "title": "请填入物流单号",
      "center": "",
      "visible": _vm.dialogFormVisibleAdd,
      "width": "25%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleAdd = $event
      }
    }
  }, [_c('el-form', {
    ref: "newformAdd",
    attrs: {
      "model": _vm.newformAdd,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "物流单号：",
      "label-width": _vm.formLabelWidth,
      "prop": "logisticsNo"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 25
    },
    model: {
      value: (_vm.newformAdd.logisticsNo),
      callback: function($$v) {
        _vm.$set(_vm.newformAdd, "logisticsNo", $$v)
      },
      expression: "newformAdd.logisticsNo"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "说明：",
      "label-width": _vm.formLabelWidth
    }
  }, [_vm._v("\n                    物流单号保存后，默认发货。\n                ")])], 1), _vm._v(" "), _c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmAdd('newformAdd')
      }
    }
  }, [_vm._v("确 定")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small"
    },
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleAdd = false
      }
    }
  }, [_vm._v("取 消")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "请填入物流单号",
      "center": "",
      "visible": _vm.dialogFormVisibleEdit,
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEdit = $event
      }
    }
  }, [_c('el-form', {
    ref: "newformEdit",
    attrs: {
      "model": _vm.newformEdit,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "物流单号：",
      "label-width": _vm.formLabelWidth,
      "prop": "logisticsNo"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off",
      "maxlength": 25
    },
    model: {
      value: (_vm.newformEdit.logisticsNo),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "logisticsNo", $$v)
      },
      expression: "newformEdit.logisticsNo"
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "dialog-footer",
    attrs: {
      "slot": "footer"
    },
    slot: "footer"
  }, [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmEdit('newformEdit')
      }
    }
  }, [_vm._v("确 定")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small"
    },
    on: {
      "click": function($event) {
        _vm.dialogFormVisibleEdit = false
      }
    }
  }, [_vm._v("取 消")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "确认执行此操作？",
      "center": "",
      "width": "30%",
      "visible": _vm.dialogFormVisibleComplete
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleComplete = $event
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
        _vm.dialogFormVisibleComplete = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.handleComplete
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
  }), _vm._v("\n            交易管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("金权交割")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 913:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(735);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("330753fb", content, true);

/***/ })

});