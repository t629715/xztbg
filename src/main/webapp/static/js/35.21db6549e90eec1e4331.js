webpackJsonp([35],{

/***/ 564:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(950)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(657),
  /* template */
  __webpack_require__(863),
  /* scopeId */
  "data-v-b1bf27d0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 657:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        var checkNum = (rule, value, callback) => {
            if (value === "" || value === undefined || value === null) {
                return callback(new Error('输入不能为空'));
            }
            if (isNaN(value)) {
                callback(new Error('请输入数字值'));
            } else {
                if (value > 100) {
                    callback(new Error('必须小于100'));
                } else {
                    callback();
                }
            }
        };
        return {
            loading: true,
            agentOptions: "",
            brokerOptions: "",
            operationOptios: "",
            sform: {
                orderNo: '',
                startTime: '',
                endTime: '',
                agentName: '',
                brokerName: '',
                operationName: '',
                userType: ''
            },
            newformEdit: {
                goldSharing: '',
                financeSharing: '',
                goldRightSharing: '',
                goldUpSharing: '',
                financeForNewSharing: ''
            },
            types: [{
                id: '1',
                name: "代理商"
            }, {
                id: '-9',
                name: "经纪人"
            }],
            rules: {
                goldSharing: [{ validator: checkNum, trigger: 'blur' }],
                financeSharing: [{ validator: checkNum, trigger: 'blur' }],
                goldRightSharing: [{ validator: checkNum, trigger: 'blur' }],
                goldUpSharing: [{ validator: checkNum, trigger: 'blur' }],
                financeForNewSharing: [{ validator: checkNum, trigger: 'blur' }]
            },
            url: this.api + "user/sightOfElephant",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            exportUrl: this.api + "user/excelSightOfElephant",
            editUrl: this.api + "incomeShringConf/modifySharing",
            deleteUrl: this.api + "user/deleteUser",
            dialogFormVisibleSharing: false,
            dialogFormVisibleDelete: false,
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
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
    },
    //定义方法
    methods: {
        //查询

        onSubmit(sform) {
            var params = new URLSearchParams();
            let _this = this;
            var date1 = '';
            var date2 = '';
            var type = null;
            if (sform.startTime != "") {
                date1 = this.dateFormat(sform.startTime);
            }
            if (sform.endTime != "") {
                date2 = this.dateFormat(sform.endTime);
            }

            type = sform.userType;
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            /*  params.append('agentName', this.isNotEmpty(sform.agentName) ? Number(sform.agentName) : '');
              params.append('brokerName', this.isNotEmpty(sform.brokerName) ? Number(sform.brokerName) : '');*/
            params.append('type', type);
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: '查询成功',
                        type: 'success'
                    });
                    var list = response.data.data.list;
                    _this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
                    _this.pageSize = response.data.data.pageSize;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = response.data.data.list;
                } else {
                    _this.$message({
                        message: '查询失败',
                        type: 'warning'
                    });
                }
            }).catch(function (error) {});
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            var date1 = '';
            var date2 = '';
            var type = null;

            if (this.sform.startTime != "") {
                date1 = this.dateFormat(this.sform.startTime);
            }
            if (this.sform.endTime != "") {
                date2 = this.dateFormat(this.sform.endTime);
            }
            if (this.sform.userType != "" && this.sform.userType != undefined) {
                type = this.sform.userType;
            } else {
                type = "";
            }
            this.pageSize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('type', type);
            params.append('operationName', this.sform.operationName);
            params.append('agentName', this.sform.agentName);
            params.append('brokerName', this.sform.brokerName);
            /* params.append('agentName', this.isNotEmpty(this.sform.agentName) ? Number(sform.agentName) : '');
             params.append('brokerName', this.isNotEmpty(this.sform.brokerName) ? Number(sform.brokerName) : '');*/
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data.list;
                    _this.currentPage = response.data.data.pageNum == 0 ? 1 : response.data.data.pageNum;
                    _this.pageSize = response.data.data.pageSize;
                    _this.totalNum = response.data.data.total;
                    _this.tableData = list;
                }
            }).catch(function (error) {});
        },
        //删除
        handleDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', _this.row.id);
            axios.post(this.deleteUrl, params).then(function (response) {
                console.log(response.data.data);
                if (response.data.data.msg == 1) {
                    console.log(response.data);
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleDelete = false;
                    _this.loadData(_this.pageSize, _this.currentPage);
                } else if (response.data.data.msg === 0) {
                    _this.$message({
                        message: '删除失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleDelete = false;
                } else {
                    _this.dialogFormVisibleEdit = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {});
        },
        deleteDialog(index, row) {
            this.dialogFormVisibleDelete = true;
            this.row = row;
        },
        //  编辑按钮
        sharing(index, row) {
            this.newformEdit = {
                goldSharing: row.goldSharing,
                financeSharing: row.financeSharing,
                goldRightSharing: row.goldRightSharing,
                goldUpSharing: row.goldUpSharing,
                financeForNewSharing: row.financeForNewSharing
                //存储 理财产品id
            };this.id = row.id;
            this.dialogFormVisibleSharing = true;
        },
        resetFormValidate(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisibleSharing = false;
        },
        //  确定编辑方法
        confirmSharing(formName) {
            var params = new URLSearchParams();
            params.append('realGoldPercent', this.newformEdit.goldSharing);
            params.append('randomPercent', this.newformEdit.financeSharing);
            params.append('goldRightPercent', this.newformEdit.goldRightSharing);
            params.append('goldPercent', this.newformEdit.goldUpSharing);
            params.append('financeForNewPercent', this.newformEdit.financeForNewSharing);
            params.append('agentId', this.id);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {

                    this.$refs[formName].resetFields();
                    axios.post(this.editUrl, params).then(function (response) {

                        if (response.data.data == 1) {

                            _this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            _this.dialogFormVisibleSharing = false;
                            _this.loadData(10, 1);
                        } else if (!(response.data.data == 1)) {
                            _this.$message({
                                message: '修改失败',
                                type: 'warning'
                            });
                            _this.dialogFormVisibleSharing = false;
                        } else {
                            _this.dialogFormVisibleSharing = false;
                            _this.$message.error('网络错误');
                        }
                    }).catch(function (error) {
                        _this.dialogFormVisibleSharing = false;
                        _this.$message.error('网络错误');
                    });
                } else {
                    console.log('error submit!!');
                }
            });
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
        jdType(value) {
            return value.pid == 1 ? "代理商" : value.pid != null ? "经纪人" : "管理员";
        },
        //清空
        resetForm(sform) {
            this.$refs.sform.resetFields();
        },
        //导出
        onExport(sform) {
            var params = new URLSearchParams();
            let _this = this;

            params.append('userName', sform.userName);
            params.append('agentName', sform.agentName);
            params.append('brokerName', sform.brokerName);
            /* params.append('agentName', this.isNotEmpty(sform.agentName) ? Number(sform.agentName) : '');
             params.append('brokerName', this.isNotEmpty(sform.brokerName) ? Number(sform.brokerName) : '');*/

            console.info(this.exportUrl + "?" + params);
            window.location = this.exportUrl + "?" + params;
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            /*this.currentPage = val;
            this.onSubmit(this.sform);*/
            this.loadData(this.pageSize, val);
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

/***/ 764:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 863:
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
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "创建时间：",
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
      "label": "类型：",
      "prop": "type"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.sform.userType),
      callback: function($$v) {
        _vm.$set(_vm.sform, "userType", $$v)
      },
      expression: "sform.userType"
    }
  }, _vm._l((_vm.types), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "userName",
      "label": "商户名",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "type",
      "formatter": _vm.jdType,
      "label": "类型",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "agentName",
      "label": "代理商",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "createTime",
      "label": "创建时间",
      "width": "180"
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
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(scope.index, scope.row)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
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
  })], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "分成设置",
      "center": "",
      "width": "30%",
      "visible": _vm.dialogFormVisibleSharing
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleSharing = $event
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
      "label": "黄金",
      "label-width": _vm.formLabelWidth,
      "prop": "goldSharing"
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
      value: (_vm.newformEdit.goldSharing),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "goldSharing", $$v)
      },
      expression: "newformEdit.goldSharing"
    }
  })], 1), _vm._v("\n                        %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "理财",
      "label-width": _vm.formLabelWidth,
      "prop": "financeSharing"
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
      value: (_vm.newformEdit.financeSharing),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "financeSharing", $$v)
      },
      expression: "newformEdit.financeSharing"
    }
  })], 1), _vm._v("\n                        %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "金权交易",
      "label-width": _vm.formLabelWidth,
      "prop": "goldRightSharing"
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
      value: (_vm.newformEdit.goldRightSharing),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "goldRightSharing", $$v)
      },
      expression: "newformEdit.goldRightSharing"
    }
  })], 1), _vm._v("\n                        %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "黄金稳赚",
      "label-width": _vm.formLabelWidth,
      "prop": "goldUpSharing"
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
      value: (_vm.newformEdit.goldUpSharing),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "goldUpSharing", $$v)
      },
      expression: "newformEdit.goldUpSharing"
    }
  })], 1), _vm._v("\n                        %\n                  ")], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "新手专属理财",
      "label-width": _vm.formLabelWidth,
      "prop": "financeForNewSharing"
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
      value: (_vm.newformEdit.financeForNewSharing),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "financeForNewSharing", $$v)
      },
      expression: "newformEdit.financeForNewSharing"
    }
  })], 1), _vm._v("\n                        %\n                  ")], 1)], 1), _vm._v(" "), _c('div', {
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
        _vm.resetFormValidate('newformEdit')
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmSharing('newformEdit')
      }
    }
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "确认删除？",
      "center": "",
      "width": "30%",
      "visible": _vm.dialogFormVisibleDelete
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
  }), _vm._v(" 合作伙伴")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("商户管理")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 950:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(764);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("341e3d79", content, true);

/***/ })

});