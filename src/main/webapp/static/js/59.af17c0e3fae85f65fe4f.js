webpackJsonp([59],{

/***/ 525:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(835)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(607),
  /* template */
  __webpack_require__(758),
  /* scopeId */
  "data-v-057786e2",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 607:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            rechargechannelOptions: [{
                value: "",
                label: "默认"
            }, {
                value: '01',
                label: '银联'
            }, {
                value: '02',
                label: '微信扫码'
            }, {
                value: '03',
                label: '支付宝'
            }, {
                value: '04',
                label: '微信'
            }, {
                value: '05',
                label: '支付宝扫码'
            }],
            agentOptions: "",
            brokerOptions: "",
            sform: {
                userName: '',
                startTime: '',
                endTime: '',
                rechargechannel: '',
                agentName: '',
                brokerName: ''
            },
            url: "userRecharge/selectByUserRecharge",
            agentUrl: "user/selectByAgentMessage",
            brokerUrl1: "user/selectByBrokerMessage1",
            exportUrl: "userRecharge/excelUserRecharge",
            countUrl: "userRecharge/selectByUserRechargeCount",
            currentPage: 0,
            pagesize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            dialogFormVisible: false,
            dialogFormVisibleEdit: false,
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
        var params = new URLSearchParams();
        params.append('status', 1);
        axios.post(this.countUrl, params).then(function (response) {
            if (response.data.code == 1001) {
                document.getElementById("czjeId").innerText = response.data.data.rmbAmtSum;
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
            params.append('rechargechannel', sform.rechargechannel);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            params.append('status', 1);

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

            //获取统计数据
            axios.post(this.countUrl, params).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("czjeId").innerText = response.data.data.rmbAmtSum;
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
            params.append('rechargechannel', this.sform.rechargechannel);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);
            params.append('status', 1);

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

        /*加载选中的代理商所有的经纪人*/
        changeAgent() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("pid", _this.sform.agentName);
            axios.get(_this.brokerUrl1, { params }).then(function (res) {
                _this.brokerOptions = res.data.data;
            });
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
            var params = new URLSearchParams();
            params.append('status', 1);
            axios.post(this.countUrl, params).then(function (response) {
                if (response.data.code == 1001) {
                    document.getElementById("czjeId").innerText = response.data.data.rmbAmtSum;
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
            params.append('rechargechannel', sform.rechargechannel);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            params.append('status', 1);

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

/***/ 671:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-057786e2]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-057786e2]{border-radius:4px}.bg-purple-dark[data-v-057786e2]{background:#99a9bf}.bg-color1[data-v-057786e2]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-057786e2]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-057786e2]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-057786e2]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-057786e2]{background:#d3dce6}.bg-purple-light[data-v-057786e2]{background:#e5e9f2}.gridBox[data-v-057786e2]{padding-left:20px}.grid-content[data-v-057786e2]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-057786e2]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 758:
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
      "label": "充值时间：",
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
      "label": "充值渠道：",
      "prop": "rechargechannel"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.rechargechannel),
      callback: function($$v) {
        _vm.$set(_vm.sform, "rechargechannel", $$v)
      },
      expression: "sform.rechargechannel"
    }
  }, _vm._l((_vm.rechargechannelOptions), function(item) {
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
        _vm.changeAgent()
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
      "gutter": 40,
      "justify": "end"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-color1"
  }, [_c('p', [_vm._v("充值总金额（元）")]), _vm._v(" "), _c('p', {
    attrs: {
      "id": "czjeId"
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
      "prop": "UserName",
      "label": "用户账号",
      "width": "180",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realName",
      "label": "用户姓名",
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
      "prop": "RMBAmt",
      "label": "充值金额",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "MerchantOrderNum",
      "label": "商户订单号",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RechargeChannel",
      "label": "充值渠道",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "PlatformName",
      "label": "充值平台",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "RechargeTime",
      "label": "充值时间",
      "width": "180"
    }
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
  }), _vm._v(" 出入金管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("现金充值")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 835:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(671);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("43b945ee", content, true);

/***/ })

});