webpackJsonp([37],{

/***/ 554:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(886)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(639),
  /* template */
  __webpack_require__(807),
  /* scopeId */
  "data-v-67740e5f",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 639:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            stateOptions: [{
                value: "",
                label: "全部"
            }, {
                value: 1,
                label: '持有中'
            }, {
                value: 2,
                label: '已赎回'
            }],
            agentOptions: "",
            brokerOptions: "",
            sform: {
                userName: '',
                startTime: '',
                endTime: '',
                regStartTime: '',
                regEndTime: '',
                status: '',
                agentName: '',
                brokerName: ''
            },
            url: this.api + "financeRegulargoldOrder/selectByFinanceOrder",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            brokerUrl1: this.api + "user/selectByBrokerMessage1",
            exportUrl: this.api + "financeRegulargoldOrder/exportFinanceOrder",
            countUrl: this.api + "financeRegulargoldOrder/selectTotalAmountBuy",
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
            showAgent: false,
            showBroker: false,
            formLabelWidth: '120px',
            userId: ""
        };
    },
    //页面渲染加载方法
    created() {
        // this.loadData(10, 1);
        let _this = this;
        //初始化代理商下拉列表
        axios.get(this.agentUrl).then(function (response) {
            _this.agentOptions = response.data.data;
            _this.isShowAgent(_this.agentOptions.length);
        }).catch(function (error) {
            console.log(error);
        });
        axios.get(this.brokerUrl1).then(function (response) {
            _this.brokerOptions = response.data.data;
            _this.isShowBroker(_this.brokerOptions.length);
        }).catch(function (error) {
            console.log(error);
        });
        //获取统计数据
        /*axios.post(this.countUrl)
            .then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("lczjId").innerText =
                        _this.amountHandle1(response.data.data);
                }
            })
            .catch(function (error) {
                console.log(error);
            });*/
    },
    //定义方法
    methods: {
        //查询
        onSubmit() {

            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';
            var date3 = '';
            var date4 = '';

            if (this.sform.startTime != "") {
                date1 = this.dateFormat(this.sform.startTime);
            }
            if (this.sform.endTime != "") {
                date2 = this.dateFormat(this.sform.endTime);
            }
            if (this.sform.regStartTime != "") {
                date3 = this.dateFormat(this.sform.regStartTime);
            }
            if (this.sform.regEndTime != "") {
                date4 = this.dateFormat(this.sform.regEndTime);
            }
            _this.currentPage = 1;

            params.append('pageSize', _this.pageSize);
            params.append('pageNum', _this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('regStartTime', date3);
            params.append('regEndTime', date4);
            params.append('userName', this.sform.userName);
            params.append('status', 1);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);

            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: '查询成功',
                        type: 'success'
                    });
                    var list = response.data.data.list;
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

            //获取统计金额
            axios.get(this.countUrl, { params }).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("lczjId").innerText = _this.amountHandle1(response.data.data);
                }
            }).catch(function (error) {
                console.log(error);
            });
        },
        convertPrice(value) {
            return (value.buyPrice / 100).toFixed(2);
        },
        isShowAgent(value) {
            if (value == 1) {
                this.showAgent = false;
            } else {
                this.showAgent = true;
            }
        },
        isShowBroker(value) {
            if (value == 1 && this.showAgent) {
                this.showBroker = false;
            } else {
                this.showBroker = true;
            }
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            let _this = this;
            var params = new URLSearchParams();
            var date1 = '';
            var date2 = '';
            var date3 = '';
            var date4 = '';

            if (this.sform.startTime != "") {
                date1 = this.dateFormat(this.sform.startTime);
            }
            if (this.sform.endTime != "") {
                date2 = this.dateFormat(this.sform.endTime);
            }
            if (this.sform.regStartTime != "") {
                date3 = this.dateFormat(this.sform.regStartTime);
            }
            if (this.sform.regEndTime != "") {
                date4 = this.dateFormat(this.sform.regEndTime);
            }
            params.append('pageSize', pageSize);
            params.append('pageNum', pageNum);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('regStartTime', date3);
            params.append('regEndTime', date4);
            params.append('userName', this.sform.userName);
            params.append('status', 1);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);
            axios.get(this.url, { params }).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data.list;
                    _this.handelData(list);
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
                    list[i].buyAmount = this.amountHandle1(list[i].buyAmount);
                    list[i].initialPrice = this.amountHandle1(list[i].initialPrice);
                    //list[i].buyAmount != "" ? Number(list[i].buyAmount)/100 : 0;
                    list[i].income = this.amountHandle1(list[i].income);
                    //list[i].income != "" ? Number(list[i].income)/100 : 0;
                    //alert(Number(list[i].yearIncomPercent*100).toFixed(2));
                    list[i].yearIncomPercent = this.isNotEmpty(list[i].yearIncomPercent) ? Number(list[i].yearIncomPercent * 100).toFixed(2) + '%' : 0;
                    if (list[i].status == 1) {
                        list[i].status = '持有中';
                    } else if (list[i].status == 2) {
                        list[i].status = '已赎回';
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
            let _this = this;
            this.$refs.sform.resetFields();
            //获取统计数据
            axios.post(this.countUrl).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("lczjId").innerText = _this.amountHandle1(response.data.data.buyAmountSum);
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
            var date3 = '';
            var date4 = '';

            if (sform.startTime != "") {
                date1 = this.dateFormat(sform.startTime);
            }
            if (sform.endTime != "") {
                date2 = this.dateFormat(sform.endTime);
            }
            if (sform.regStartTime != "") {
                date3 = this.dateFormat(sform.regStartTime);
            }
            if (sform.regEndTime != "") {
                date4 = this.dateFormat(sform.regEndTime);
            }

            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('regStartTime', date3);
            params.append('regEndTime', date4);
            params.append('userName', sform.userName);
            params.append('status', sform.status);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            params.append("type", 3);
            window.location = this.exportUrl + "?" + params;
        },
        convertFrom(value) {
            return value.buyType == 2 ? '实金转入' : '现金买入';
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadData(this.pageSize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.pageSize = val;
            this.onSubmit(val, 1);
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

/***/ 716:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-67740e5f]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-67740e5f]{border-radius:4px}.bg-purple-dark[data-v-67740e5f]{background:#99a9bf}.bg-color1[data-v-67740e5f]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-67740e5f]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-67740e5f]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-67740e5f]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-67740e5f]{background:#d3dce6}.bg-purple-light[data-v-67740e5f]{background:#e5e9f2}.gridBox[data-v-67740e5f]{padding-left:20px}.grid-content[data-v-67740e5f]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-67740e5f]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 807:
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
      "label": "买入时间：",
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
      "label": "注册时间：",
      "prop": "regStartTime"
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
      value: (_vm.sform.regStartTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "regStartTime", $$v)
      },
      expression: "sform.regStartTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "regEndTime"
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
      value: (_vm.sform.regEndTime),
      callback: function($$v) {
        _vm.$set(_vm.sform, "regEndTime", $$v)
      },
      expression: "sform.regEndTime"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
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
      "click": _vm.onSubmit
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
      "gutter": 40,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color1"
  }, [_c('p', [_vm._v("稳赚金买入总计（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "lczjId"
    }
  })])])], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "registerTime",
      "label": "注册时间",
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
      "prop": "productNo",
      "label": "产品编号",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productName",
      "label": "产品名称",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productCycle",
      "label": "周期",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "productYearIncomPercent",
      "label": "收益率",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyType",
      "label": "买入来源",
      "formatter": _vm.convertFrom,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyPrice",
      "label": "买入价",
      "formatter": _vm.convertPrice,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gram",
      "label": "买入克重",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyAmount",
      "label": "买入金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "buyTime",
      "label": "买入时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "expireTime",
      "label": "赎回时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "状态",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "incomeGram",
      "label": "收益",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "backTotalGram",
      "label": "到期总克重",
      "width": "100"
    }
  })], 1), _vm._v(" "), _c('br'), _c('br'), _vm._v(" "), _c('el-pagination', {
    attrs: {
      "current-page": _vm.currentPage,
      "page-sizes": [10, 20, 30, 40],
      "page-size": _vm.pageSize,
      "layout": "total, sizes, prev, pager, next, jumper",
      "total": _vm.totalNum
    },
    on: {
      "size-change": _vm.handleSizeChange,
      "current-change": _vm.handleCurrentChange
    }
  })], 1)])
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
  }), _vm._v(" 交易结算")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("稳赚金交易结算")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 886:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(716);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("1cf2b21a", content, true);

/***/ })

});