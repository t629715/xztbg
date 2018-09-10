webpackJsonp([64],{

/***/ 525:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(866)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(612),
  /* template */
  __webpack_require__(784),
  /* scopeId */
  "data-v-257ae93c",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 612:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            statusOptions: [{
                value: "",
                label: "全部"
            }, {
                value: '0',
                label: '审核中'
            }, {
                value: '1',
                label: '审核通过'
            }, {
                value: '2',
                label: '审核未通过'
            }],
            agentOptions: "",
            brokerOptions: "",
            sform: {
                userName: '',
                startTime: '',
                endTime: '',
                status: '',
                agentName: '',
                brokerName: ''
            },
            url: this.api + "userWithdrawCash/selectByWithdrawCash",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            brokerUrl1: this.api + "user/selectByBrokerMessage1",
            exportUrl: this.api + "userWithdrawCash/excelWithdrawCash",
            countUrl: this.api + "userWithdrawCash/selectByWithdrawCashCount",
            editUrl: this.api + "userWithdrawCash/auditPassedById",
            editUrl2: this.api + "userWithdrawCash/auditNoPassedById",
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleEdit2: false,
            clickState: false,
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
        axios.post(this.countUrl).then(function (response) {
            if (response.data.code == 1001) {
                document.getElementById("tqjeId").innerText = _this.amountHandle1(response.data.data.withdrawAmtSum);
                document.getElementById("sxfId").innerText = _this.amountHandle1(response.data.data.poundageSum);
            }
        }).catch(function (error) {
            console.log(error);
        });
    },
    //定义方法
    methods: {
        //查询
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
            params.append('status', this.isNotEmpty(sform.status) ? Number(sform.status) : '');
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
                    _this.handelData(list);
                    _this.tableData = list;
                } else {
                    _this.$message({
                        message: '查询失败',
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});

            //获取统计数据
            axios.post(this.countUrl, params).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("tqjeId").innerText = _this.amountHandle1(response.data.data.withdrawAmtSum);
                    document.getElementById("sxfId").innerText = _this.amountHandle1(response.data.data.poundageSum);
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
            params.append('status', this.isNotEmpty(this.sform.status) ? Number(this.sform.status) : '');
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);

            let _this = this;
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data.list;
                    _this.handelData(list);
                    //_this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
                    //_this.pagesize = response.data.data.pageSize;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = list;
                }
            }).catch(function (error) {});
        },
        //数据处理
        handelData(list) {
            if (list.length > 0) {
                for (var i = 0; i < list.length; i++) {
                    list[i].withdrawAmt = this.amountHandle1(list[i].withdrawAmt);
                    list[i].poundage = this.amountHandle1(list[i].poundage);
                    if (list[i].status == "0") {
                        list[i].status = '审核中';
                    } else if (list[i].status == "1") {
                        list[i].status = '审核通过';
                    } else if (list[i].status == "2") {
                        list[i].status = '审核未通过';
                    }
                    if (list[i].type == "1") {
                        list[i].type = '银行卡';
                    } else if (list[i].type == "2") {
                        list[i].type = '支付宝';
                    }
                }
            }
        },
        //根据代理商取经纪人列表
        getBrokerOptions() {
            let _this = this;
            _this.sform.brokerName = "";
            var params = new URLSearchParams();
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
            axios.post(this.countUrl).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("tqjeId").innerText = _this.amountHandle1(response.data.data.withdrawAmtSum);
                    document.getElementById("sxfId").innerText = _this.amountHandle1(response.data.data.poundageSum);
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
            params.append('status', this.isNotEmpty(sform.status) ? Number(sform.status) : '');
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);

            console.info(this.exportUrl + "?" + params);
            window.location = this.exportUrl + "?" + params;
        },
        //审核通过
        editDialog(index, row) {
            this.dialogFormVisibleEdit = true;
            this.row = row;
        },
        //审核通过
        handleEdit(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('withdrawid', _this.row.withdrawID);
            params.append('userId', _this.row.userID);
            _this.clickState = true;
            axios.post(this.editUrl, params).then(function (response) {
                setTimeout(function () {
                    _this.clickState = false;
                }, 3000);
                if (response.data.code == 1000) {
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
        //审核不通过
        editNoDialog(index, row) {
            this.dialogFormVisibleEdit2 = true;
            this.row = row;
        },
        //审核不通过
        handleEdit2(index, row) {
            let _this = this;
            _this.clickState = true;
            var params = new URLSearchParams();
            params.append('withdrawid', _this.row.withdrawID);
            params.append('userId', _this.row.userID);
            axios.post(this.editUrl2, params).then(function (response) {
                setTimeout(function () {
                    _this.clickState = false;
                }, 3000);

                if (response.data.code == 1000) {
                    _this.$message({
                        message: '操作成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit2 = false;
                    _this.loadData(_this.pagesize, 1);
                } else {
                    _this.$message({
                        message: '操作失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit2 = false;
                }
            }).catch(function (error) {});
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

/***/ 692:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-257ae93c]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-257ae93c]{border-radius:4px}.bg-purple-dark[data-v-257ae93c]{background:#99a9bf}.bg-color1[data-v-257ae93c]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-257ae93c]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-257ae93c]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-257ae93c]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-257ae93c]{background:#d3dce6}.bg-purple-light[data-v-257ae93c]{background:#e5e9f2}.gridBox[data-v-257ae93c]{padding-left:20px}.grid-content[data-v-257ae93c]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-257ae93c]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 784:
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
      "label": "提取时间：",
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
      "label": "状态：",
      "prop": "status"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.status),
      callback: function($$v) {
        _vm.$set(_vm.sform, "status", $$v)
      },
      expression: "sform.status"
    }
  }, _vm._l((_vm.statusOptions), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.label,
        "value": item.value
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
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
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.onSubmit(_vm.sform)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.sform)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "export"
    },
    on: {
      "click": function($event) {
        _vm.onExport(_vm.sform)
      }
    }
  }, [_vm._v("导出")])], 1)], 1), _vm._v(" "), _c('el-row', {
    staticClass: "gridBox",
    attrs: {
      "gutter": 40,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color1"
  }, [_c('p', [_vm._v("提取总金额（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "tqjeId"
    }
  })])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color2"
  }, [_c('p', [_vm._v("手续费（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "sxfId"
    }
  })])])], 1), _vm._v(" "), _c('el-table', {
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
      "type": "index",
      "label": "序号",
      "width": "80",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userName",
      "label": "用户账号",
      "width": "150",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realName",
      "label": "姓名",
      "width": "150",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "agentName",
      "label": "代理商",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "brokerName",
      "label": "经纪人",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "withdrawAmt",
      "label": "提取金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "poundage",
      "label": "手续费",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "type",
      "label": "提现类型",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "bankName",
      "label": "所属银行",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "accountNum",
      "label": "提现账号",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "withdrawTime",
      "label": "申请时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "finishTime",
      "label": "审核时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "状态",
      "width": "120"
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
        return [(scope.row.status == '审核中') ? [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.editDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("审核通过\n                        ")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.editNoDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("\n                            审核不通过\n                        ")])] : _vm._e()]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "paginationBox"
  }, [_c('el-pagination', {
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
  })], 1), _vm._v(" "), _c('el-dialog', {
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
      "type": "primary",
      "disabled": _vm.clickState
    },
    on: {
      "click": _vm.handleEdit
    }
  }, [_vm._v("确 定")])], 1)]), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "提示",
      "width": "30%",
      "visible": _vm.dialogFormVisibleEdit2
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEdit2 = $event
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
        _vm.dialogFormVisibleEdit2 = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.clickState
    },
    on: {
      "click": _vm.handleEdit2
    }
  }, [_vm._v("确 定")])], 1)])], 1)
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
  }, [_vm._v("现金提取")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 866:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(692);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("6e5d938a", content, true);

/***/ })

});