webpackJsonp([20],{

/***/ 550:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(795)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(611),
  /* template */
  __webpack_require__(735),
  /* scopeId */
  "data-v-673e75be",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 611:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            actorList: "",
            form: {
                username: '',
                region: '',
                date1: '',
                date2: '',
                brokerName: "",
                agentName: "",
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            newformEdit: {
                brokerId: ''
            },
            brokeList: [],
            agentOptions: [{
                id: "",
                agentName: "全部"
            }],
            brokerOptions: [],
            url: "userInfo/selectSubClients",
            agentUrl: "user/selectByAgentMessage",
            brokerUrl: "user/selectByBrokerMessage",
            brokerUrl1: "user/selectByBrokerMessage1",
            exportUrl: "userInfo/excelSubClients",
            countUrl: "userInfo/selectSubClientsCount",
            changeUrl: "userInfo/cheageBroker",
            dialogFormVisibleEdit: false,
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            formLabelWidth: '120px',
            userId: "",
            countList: ""
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        this.countNum();
        let _this = this;
        //初始化代理商下拉列表
        axios.get(this.agentUrl).then(function (response) {
            _this.agentOptions = response.data.data;
        }).catch(function (error) {
            console.log(error);
        });
        axios.get(this.brokerUrl1).then(function (response) {
            _this.brokerOptions = response.data.data;
        }).catch(function (error) {
            console.log(error);
        });
        //初始化代理商下拉列表
        this.agentChange();
    },
    //定义方法
    methods: {
        //根据代理商取经纪人列表
        getBrokerOptions() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('pid', _this.form.agentName);
            axios.post(_this.brokerUrl1, params).then(function (response) {
                _this.brokerOptions = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        agentChange() {
            let _this = this;
            var selectagentName = "";

            axios.get(this.brokerUrl, {
                params: { 'pid': selectagentName }
            }).then(function (response) {
                _this.brokeList = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        countNum() {
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                username = "",
                brokerName = "",
                agentsName = "";
            if (this.form.username != undefined) {
                username = this.form.username;
            }
            if (this.form.date1 != "") {
                datetime1 = this.dateFormat(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = this.dateFormat(this.form.date2);
            }
            if (this.form.brokerName != undefined) {
                brokerName = this.form.brokerName;
            }
            if (this.form.agentName != undefined) {
                agentsName = this.form.agentName;
            }
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('userName', username);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('brokerName', brokerName);
            params.append('agentsName', agentsName);
            axios.get(this.countUrl + '?' + params).then(function (res) {
                console.log(res.data.data);
                _this.countList = res.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        //查询方法
        onSubmit(form) {
            this.countNum();
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                username = "",
                brokerName = "",
                agentsName = "";
            if (this.form.username != undefined) {
                username = this.form.username;
            }
            if (this.form.date1 != "") {
                datetime1 = this.dateFormat(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = this.dateFormat(this.form.date2);
            }
            if (this.form.brokerName != undefined) {
                brokerName = this.form.brokerName;
            }
            console.log(this.form.agentName);
            if (this.form.agentName != undefined) {
                agentsName = this.form.agentName;
            }
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('userName', username);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('brokerName', brokerName);
            params.append('agentsName', agentsName);
            axios.post(this.url, params).then(function (response) {
                _this.$message({
                    message: '查询成功',
                    type: 'success'
                });
                _this.currentPage = 1;
                _this.pageNum = response.data.pages;
                _this.totalNum = response.data.total;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {});
        },
        changeBroker(index, row) {
            this.newformEdit = {
                brokerId: row.brokerId
            };
            console.log(typeof row.userId);
            this.userId = row.userId;
            console.log(this.userId);
            this.dialogFormVisibleEdit = true;
        },
        confirmChangeBroker() {
            let _this = this;
            var params = new URLSearchParams();
            params.append("brokerId", _this.newformEdit.brokerId);
            params.append("userId", _this.userId);
            axios.post(this.changeUrl, params).then(function (response) {
                if (response.data.data == 1) {
                    _this.$message({
                        message: "变更成功",
                        type: 'success'
                    });
                    _this.loadData(_this.pageSize, _this.currentPage);
                    _this.dialogFormVisibleEdit = false;
                } else {
                    _this.$message({
                        message: "变更失败",
                        type: 'error'
                    });
                    _this.dialogFormVisibleEdit = false;
                }
            });
        },
        //清空表单
        resetForm() {
            this.$refs.form.resetFields();
            this.loadData(10, 1);
            this.countNum();
        },
        exportFun() {
            var params = new URLSearchParams();
            let _this = this;
            var datetime1 = '',
                datetime2 = "",
                username = "",
                brokerName = "",
                agentsName = "";
            if (this.form.username != undefined) {
                username = this.form.username;
            }
            if (this.form.date1 != "") {
                datetime1 = this.dateFormat(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = this.dateFormat(this.form.date2);
            }
            if (this.form.brokerName != undefined) {
                brokerName = this.form.brokerName;
            }
            if (this.form.agentName != undefined) {
                agentsName = this.form.agentName;
            }
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('userName', username);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            params.append('brokerName', brokerName);
            params.append('agentsName', agentsName);
            console.log(this.exportUrl + "?" + params);
            window.location = this.exportUrl + "?" + params;
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            var datetime1 = '',
                datetime2 = "",
                username = "",
                brokerName = "",
                agentsName = "";
            if (this.form.username == undefined) {
                username = "";
            } else {
                username = this.form.username;
            }
            if (this.form.brokerName == undefined) {
                brokerName = "";
            } else {
                brokerName = this.form.brokerName;
            }
            if (this.form.agentsName == undefined) {
                agentsName = "";
            } else {
                agentsName = this.form.agentsName;
            }
            if (this.form.date1 != "") {
                datetime1 = this.dateFormat(this.form.date1);
            }
            if (this.form.date2 != '') {
                datetime2 = this.dateFormat(this.form.date2);
            }
            this.pageSize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('userName', username);
            params.append('brokerName', brokerName);
            params.append('agentsName', agentsName);
            params.append('startTime', datetime1);
            params.append('endTime', datetime2);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                _this.totalNum = response.data.data.total;
                _this.pageNum = response.data.data.pages;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {});
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            // this.pageNum=val;
            // this.onSubmit(this.form)
            this.loadData(this.pageSize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.loadData(val, 1);
        }
    }
});

/***/ }),

/***/ 661:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-673e75be]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-673e75be]{border-radius:4px}.bg-purple-dark[data-v-673e75be]{background:#99a9bf}.bg-purple1[data-v-673e75be]{background:#f2dede}.bg-purple2[data-v-673e75be]{background:#dff0d8}.bg-purple3[data-v-673e75be]{background:#d9edf7}.bg-purple4[data-v-673e75be]{background:#fcf8e3}.bg-purple5[data-v-673e75be]{background:#d3dce6}.bg-purple-light[data-v-673e75be]{background:#e5e9f2}.gridBox[data-v-673e75be]{padding-left:20px}.grid-content[data-v-673e75be]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.grid-content p[data-v-673e75be]:first-child{line-height:40px}.grid-content p[data-v-673e75be]:last-child{line-height:0}.row-bg[data-v-673e75be]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 735:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('div', {
    staticClass: "formBox"
  }, [_c('el-form', {
    ref: "form",
    attrs: {
      "inline": true,
      "demo-form-inline": "",
      "model": _vm.form,
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "用户账号",
      "prop": "username"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.form.username),
      callback: function($$v) {
        _vm.$set(_vm.form, "username", $$v)
      },
      expression: "form.username"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
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
      value: (_vm.form.agentName),
      callback: function($$v) {
        _vm.$set(_vm.form, "agentName", $$v)
      },
      expression: "form.agentName"
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
      "label": "经纪人",
      "prop": "brokerName",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.brokerName),
      callback: function($$v) {
        _vm.$set(_vm.form, "brokerName", $$v)
      },
      expression: "form.brokerName"
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
        _vm.onSubmit(_vm.form)
      }
    }
  }, [_vm._v("查询")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "danger"
    },
    on: {
      "click": function($event) {
        _vm.resetForm(_vm.form)
      }
    }
  }, [_vm._v("清除")])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "info"
    },
    on: {
      "click": _vm.exportFun
    }
  }, [_vm._v("导出")])], 1)], 1)], 1), _vm._v(" "), _c('el-row', {
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
    staticClass: "grid-content bg-purple1"
  }, [_c('p', [_vm._v("人民币余额总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm.countList.rmbSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple2"
  }, [_c('p', [_vm._v("人民币冻结总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm.countList.frozenRmbSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple3"
  }, [_c('p', [_vm._v("人民币理财总计（元）")]), _vm._v(" "), _c('p', [_vm._v("  " + _vm._s(_vm.countList.finaceSum))])])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple4"
  }, [_c('p', [_vm._v("黄金（克）")]), _vm._v(" "), _c('p', [_vm._v(_vm._s(_vm.countList.goldSum))])])])], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
    attrs: {
      "data": _vm.tableData,
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
      "prop": "userName",
      "label": "用户账号"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "registerTime",
      "label": "注册时间"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "agentName",
      "label": "代理商"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "brokerName",
      "label": "经纪人"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "rmb",
      "label": "人民币余额"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "frozenRmb",
      "label": "人民币冻结"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "finance",
      "label": "人民币理财"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gold",
      "label": "黄金"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": ""
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
              _vm.changeBroker(scope.index, scope.row)
            }
          }
        }, [_vm._v("变更")])]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "paginationBox"
  }, [_c('el-pagination', {
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
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "变更",
      "visible": _vm.dialogFormVisibleEdit,
      "width": "30%",
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
      "label": "经纪人",
      "prop": "brokerName",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newformEdit.brokerId),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "brokerId", $$v)
      },
      expression: "newformEdit.brokerId"
    }
  }, _vm._l((_vm.brokeList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
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
        _vm.dialogFormVisibleEdit = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.confirmChangeBroker
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1)])
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
  }), _vm._v(" 合作伙伴")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("下级客户")])])])
}]}

/***/ }),

/***/ 795:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(661);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("5d6a2b02", content, true);

/***/ })

});