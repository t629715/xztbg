webpackJsonp([31],{

/***/ 562:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(860)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(649),
  /* template */
  __webpack_require__(778),
  /* scopeId */
  "data-v-12e33d2f",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 649:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
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
                operationName: ''
            },
            newform: {
                nickname: ''
            },
            types: [{
                id: '1',
                name: "代理商"
            }, {
                id: '',
                name: "经纪人"
            }],
            createcodeform: {
                pathAndName: ""
            },
            rules: {
                userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            },
            url: this.api + "user/sightOfCarrieroperator",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokeUrl: this.api + "user/selectByBrokerMessage",
            saveQRCodeUrl: this.api + "user/generateQRCode",
            createQRCodeUrl: this.api + "user/createQRCode",
            addUrl: this.api + "user/insertAgent",
            deleteUrl: this.api + "user/deleteUser",
            dialogFormVisibleAdd: false,
            dialogFormVisibleDelete: false,
            dialogFormVisibleCreateCode: false,
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            formLabelWidth: '120px',
            userId: "",
            uri: ""

        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
        var curWwwPath = window.document.location.href;
        this.uri = curWwwPath.split("#")[0];
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
            if (sform.userType != "" && sform.userType != undefined) {
                type = sform.userType;
            }
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('pid', this.isNotEmpty(sform.agentName) ? Number(sform.agentName) : '');
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
            if (this.sform.type != "" && this.sform.type != undefined) {
                type = this.sform.userType;
            }
            this.pageSize = pageSize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pageSize);
            params.append('pageNum', this.currentPage);
            params.append('startTime', date1);
            params.append('endTime', date2);
            params.append('agentName', this.isNotEmpty(this.sform.agentName) ? Number(sform.agentName) : '');
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
        deleteDialog(index, row) {
            this.dialogFormVisibleDelete = true;
            this.row = row;
        },
        //运营管理部分页面
        //删除
        createCode(index, row) {
            var ip_addr = document.location.hostname;
            var port = document.location.port;
            this.row = row;
            this.createcodeform = {
                //pathAndName:"http://"+ip_addr+":18080/user/createQRCode?userName="+row.userName+"&id="+row.id,
                pathAndName: this.createQRCodeUrl + "?userName=" + row.userName + "&id=" + row.id

                //pathAndName:"user/createQRCode?text="+row.userName,
            };
            this.dialogFormVisibleCreateCode = true;
        },
        saveCodePicture() {
            var params = new URLSearchParams();
            let _this = this;
            params.append("userName", _this.row.userName);
            params.append("id", _this.row.id);
            window.location = this.saveQRCodeUrl + "?" + params;
            _this.$message({
                message: '保存成功',
                type: 'success'
            });
            this.dialogFormVisibleCreateCode = false;
        },
        handleDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('id', _this.row.id);
            axios.post(this.deleteUrl, params).then(function (response) {
                if (response.data.code == 1001) {

                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleDelete = false;
                    _this.loadData(_this.pageSize, _this.currentPage);
                } else if (response.data.code === 1002) {
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
        },
        //根据代理商取经纪人列表
        getBrokerOptions() {
            let _this = this;
            _this.sform.brokerName = "";
            var params = new URLSearchParams();
            params.append('pid', Number(_this.sform.agentName));
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
            this.loadData(this.pageSize, 1);
        },
        resetForm1(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisibleAdd = false;
        },
        //新建经纪人
        addAgent() {
            this.newform = {
                userName: ''
            };
            this.dialogFormVisibleAdd = true;
        },
        confirmAddAgent(formName) {
            var params = new URLSearchParams();
            params.append('userName', this.newform.userName);
            params.append('password', this.newform.password);
            params.append('phone', this.newform.phone);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.$refs[formName].resetFields();
                    axios.post(this.addUrl, params).then(function (response) {
                        if (response.data.data == 1) {
                            _this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            _this.loadData(_this.pageSize, _this.pageNum);
                            _this.dialogFormVisibleAdd = false;
                        } else if (response.data.data == -1) {
                            _this.$message({
                                message: '账户已存在',
                                type: 'warning'
                            });
                            _this.dialogFormVisibleAdd = false;
                        } else {
                            _this.$message.error('网络错误');
                            _this.dialogFormVisibleAdd = false;
                        }
                    });
                } else {
                    console.log('error submit!!');
                    this.$refs[formName].resetFields();
                }
            });
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

/***/ 686:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "img[data-v-12e33d2f]{wdith:200px;height:200px;display:block;margin:0 auto}", ""]);

// exports


/***/ }),

/***/ 778:
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
  })], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
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
  })], 1)], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
      "type": "success"
    },
    on: {
      "click": function($event) {
        _vm.addAgent()
      }
    }
  }, [_vm._v("新建经纪人")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "width": "180"
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
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.createCode(scope.$index, scope.row)
            }
          }
        }, [_vm._v("生成二维码")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(scope.$index, scope.row)
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
      "title": "添加",
      "visible": _vm.dialogFormVisibleAdd,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleAdd = $event
      }
    }
  }, [_c('el-form', {
    ref: "newform",
    attrs: {
      "model": _vm.newform,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "商户名",
      "label-width": _vm.formLabelWidth,
      "prop": "userName"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.userName),
      callback: function($$v) {
        _vm.$set(_vm.newform, "userName", $$v)
      },
      expression: "newform.userName"
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
        _vm.resetForm1('newform')
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmAddAgent('newform')
      }
    }
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "",
      "visible": _vm.dialogFormVisibleCreateCode,
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleCreateCode = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.createcodeform
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('img', {
    attrs: {
      "src": _vm.createcodeform.pathAndName
    }
  })])], 1), _vm._v(" "), _c('div', {
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
        _vm.dialogFormVisibleCreateCode = false
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.saveCodePicture
    }
  }, [_vm._v("保存图片")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
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

/***/ 860:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(686);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("0fd11ed4", content, true);

/***/ })

});