webpackJsonp([42],{

/***/ 548:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(869)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(633),
  /* template */
  __webpack_require__(790),
  /* scopeId */
  "data-v-4327cb6a",
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            show: false,
            loading: true,
            readoneform: {
                title: '',
                imagePath: '',
                contentPath: ''
            },
            newform: {
                skipPath: '',
                skipType: '',
                description: '',
                appID: '',
                imagePath: '',
                contentPath: '',
                agentId: '',
                brokerId: '',
                isLogin: "1"
            },
            newformEdit: {
                skipPath: '',
                skipType: '',
                description: '',
                appID: '',
                imagePath: '',
                contentPath: '',
                agentId: '',
                brokerId: '',
                isLogin: "1"

            },
            newformFirst: {
                skipPath: '',
                skipType: '',
                description: '',
                appID: '',
                imagePath: '',
                contentPath: '',
                agentId: '',
                brokerId: '',
                isLogin: "1"
            },
            readform: {
                picturePath: ''
            },
            types: [{
                id: 1,
                name: "站内"
            }, {
                id: 2,
                name: "站外"
            }],
            appIds: [{
                id: 'RechargePage',
                name: "充值"
            }, {
                id: 'GoldEarnBuyPage',
                name: "黄金稳赚购买页面"
            }, {
                id: 'InfoGoldClassPage',
                name: "黄金课堂"
            }, {
                id: 'ActivityNormalPage',
                name: "活动页面"
            }, {
                id: 'GoldMarketPage',
                name: "金权交易行情页面"
            }, {
                id: 'SimulationMarketPage',
                name: "模拟交易行情页面"
            }, {
                id: 'BuyGoldByGramPage',
                name: "实物交易按克购买页面"
            }, {
                id: 'RealNameCertificationPage',
                name: "实名认证"
            }, {
                id: 'FixedDepositBuyPage',
                name: "随意存购买页面"
            }, {
                id: 'WithrawCashPage',
                name: "提现"
            }, {
                id: 'InfoXioudePage',
                name: "希欧德"
            }, {
                id: 'RegisterPage',
                name: "注册"
            }],
            rules: {
                imagePath: [{ required: true, message: '请输入图片路径', trigger: 'blur' }],
                skipPath: [{ required: true, message: '请输入跳转路径', trigger: 'blur' }],
                appID: [{ required: true, message: '请输入appID ', trigger: 'blur' }],
                description: [{ required: true, message: '请输入描述信息 ', trigger: 'blur' }]

            },

            url1: this.api + "infoBanner/getAdPic1",
            url2: this.api + "infoBanner/getAdPic2",
            addUrl: this.api + "infoBanner/insertBanner",
            editUrl: this.api + "infoBanner/edit",
            deleteUrl: this.api + "infoBanner/delete",
            upOrDown: this.api + "infoBanner/upDown",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokerUrl: this.api + "user/selectByBrokerMessage",
            tableData1: [],
            tableData2: [],
            dialogFormVisibleRead: false,
            dialogFormVisible: false,
            dialogFormVisibleAdd: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleDelete: false,
            dialogFormVisibleEditPop: false,
            dialogFormVisibleAddFirstPage: false,
            formLabelWidth: '120px',
            imagepath: "",
            contentpath: "",
            agentList: "",
            brokerList: "",
            maxSortNo: "",
            minSortNo: "",
            brokerChange: false

        };
    },
    //页面渲染加载方法
    created() {
        this.loadData1();
        this.loadData2();
        this.getAgentList();
    },
    methods: {
        getAgentList() {
            let _this = this;
            axios.get(this.agentUrl).then(function (response) {
                _this.agentList = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        getBrokerList() {
            let _this = this;
            if (_this.newform.agentId != undefined) {
                _this.newform.brokerId = "";
                var params = new URLSearchParams();
                if ("" != _this.newformEdit.agentId) {
                    params.append('pid', _this.newform.agentId);
                }
                axios.post(_this.brokerUrl, params).then(function (response) {
                    _this.brokerList = response.data.data;
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        convertIsLogin(value) {
            return value.isLogin === 1 ? '是' : value.isLogin === 0 ? '否' : '';
        },
        ifShowBtnUp(row) {

            if (Number.parseInt(this.maxSortNo) === Number.parseInt(row.SortNo)) {
                return true;
            } else {
                return false;
            }
        },
        ifShowBtnDown(row) {
            if (Number.parseInt(this.minSortNo) === Number.parseInt(row.SortNo)) {
                return true;
            } else {
                return false;
            }
        },
        ifShow(event) {
            if (event === "ActivityNormalPage") {
                this.show = true;
            } else {
                this.show = false;
            }
        },
        //刷新表格方法
        loadData1() {
            var params = new URLSearchParams();
            params.append('page', 3);
            let _this = this;
            axios.post(this.url1, params).then(function (response) {
                _this.tableData1 = response.data.data;
                if (_this.tableData1[0].SortNo != undefined) {
                    _this.maxSortNo = _this.tableData1[0].SortNo;
                    _this.minSortNo = _this.tableData1[0].SortNo;
                }

                for (var i = 0; _this.tableData1.length; i++) {
                    if (_this.tableData1[i].SortNo != undefined) {
                        if (_this.minSortNo > _this.tableData1[i].SortNo) {
                            _this.minSortNo = _this.tableData1[i].SortNo;
                        }
                    }
                }
            }).catch(function (error) {});
        },
        loadData2() {
            var params = new URLSearchParams();
            params.append('page', 1);
            let _this = this;
            axios.post(this.url2, params).then(function (response) {
                console.log(response.data.data);
                _this.tableData2 = response.data.data;
            }).catch(function (error) {});
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisibleAddFirstPage = false;
            this.dialogFormVisibleAdd = false;
        },
        //查看
        readOne(index, row) {
            console.log(row.PicturePath);
            this.readform = {
                picturePath: row.PicturePath
            };
            this.dialogFormVisibleRead = true;
        },
        //  添加用户按钮
        add() {
            this.newform = {
                title: '',
                contentPath: '',
                imagePath: '',
                appID: '',
                skipPath: "",
                agentId: "",
                brokerId: "",
                description: "",
                isLogin: 1
            };
            this.show = false;
            this.dialogFormVisibleAdd = true;
        },
        //  确定添加图片
        confirmAdd(formName) {

            var params = new URLSearchParams();
            params.append('picturePath', this.newform.imagePath);
            params.append('skipPath', this.newform.skipPath);
            if (this.newform.appID == undefined) {
                params.append('appID', "");
            } else {
                params.append('appID', this.newform.appID);
            }
            params.append('agentId', this.newform.agentId);
            params.append('brokerId', this.newform.brokerId);
            params.append("isLogin", this.newform.isLogin);
            params.append('page', '3');
            params.append('valid', '1');
            if (this.newform.description != undefined) {
                params.append('description', this.newform.description);
            }

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
                            _this.dialogFormVisibleAdd = false;
                            _this.loadData1();
                            _this.loadData2();
                        } else if (response.data.data == -1) {
                            _this.$message({
                                message: '账户已存在',
                                type: 'warning'
                            });
                            _this.dialogFormVisible = false;
                        } else {
                            _this.$message.error('网络错误');
                            _this.dialogFormVisible = false;
                        }
                    });
                } else {
                    console.log('error submit!!');
                }
            });
        },

        //  添加首页轮播图按钮
        addFirstPage() {
            this.newformFirst = {
                title: '',
                contentPath: '',
                imagePath: '',
                appID: '',
                skipPath: "",
                agentId: "",
                brokerId: "",
                description: "",
                isLogin: 1
            };
            this.dialogFormVisibleAddFirstPage = true;
        },
        confirmAddFirstPage(formName) {
            var params = new URLSearchParams();
            params.append('picturePath', this.newformFirst.imagePath);
            params.append('skipPath', this.newformFirst.skipPath);
            if (this.newformFirst.appID == undefined) {
                params.append('appID', "");
            } else {
                params.append('appID', this.newformFirst.appID);
            }
            params.append('agentId', this.newformFirst.agentId);
            params.append('brokerId', this.newformFirst.brokerId);
            params.append("isLogin", this.newformFirst.isLogin);
            if (this.newformFirst.description != undefined) {
                params.append('description', this.newformFirst.description);
            }

            params.append('page', '1');
            params.append('valid', '1');
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
                    axios.post(this.addUrl, params).then(function (response) {
                        if (response.data.data == 1) {
                            _this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            _this.dialogFormVisibleAddFirstPage = false;
                            _this.loadData1();
                            _this.loadData2();
                        } else if (response.data.data == -1) {
                            _this.$message({
                                message: '账户已存在',
                                type: 'warning'
                            });
                            _this.dialogFormVisible = false;
                        } else {
                            _this.$message.error('网络错误');
                            _this.dialogFormVisibleAddFirstPage = false;
                        }
                    });
                } else {
                    console.log('error submit!!');
                }
            });
        },
        deleteDialog(index, row) {
            this.dialogFormVisibleDelete = true;
            this.row = row;
        },
        //运营管理部分页面
        //删除
        handleDelete() {
            let _this = this;
            var params = new URLSearchParams();
            params.append('serialNo', _this.row.SerialNo);
            axios.post(this.deleteUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleDelete = false;
                    _this.loadData1();
                    _this.loadData2();
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
        },
        goUp(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('upSortNo', this.tableData1[index - 1].SortNo);
            params.append('downSortNo', row.SortNo);
            axios.post(this.upOrDown, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '调整成功',
                        type: 'success'
                    });
                    _this.loadData1();
                    _this.loadData2();
                    _this.dialogFormVisibleEdit = false;
                } else if (!response.data.data) {
                    _this.$message({
                        message: '调整失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit = false;
                } else {
                    _this.dialogFormVisibleEdit = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {});
        },
        goDown(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('upSortNo', row.SortNo);
            params.append('downSortNo', this.tableData1[index + 1].SortNo);
            axios.post(this.upOrDown, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '调整成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData1();
                    _this.loadData2();
                } else if (!response.data.data) {
                    _this.$message({
                        message: '调整失败',
                        type: 'warning'
                    });
                    _this.dialogFormVisibleEdit = false;
                } else {
                    _this.dialogFormVisibleEdit = false;
                    _this.$message.error('网络错误');
                }
            }).catch(function (error) {});
        },

        //  编辑按钮
        handleEdit(index, row) {
            this.newformEdit = {
                skipPath: row.SkipPath,
                description: row.Description,
                appID: row.AppID,
                imagePath: row.PicturePath,
                agentId: row.agentId + "",
                brokerId: row.brokerName,
                isLogin: row.isLogin
            };
            if (row.AppID === 'ActivityNormalPage') {
                this.show = true;
            } else {
                this.row = false;
            }
            //存储 理财产品id
            this.sortNo = row.SortNo;
            this.dialogFormVisibleEdit = true;
        },
        handleEditPop(index, row) {
            this.newformEdit = {
                skipPath: row.SkipPath,
                description: row.Description,
                appID: row.AppID,
                imagePath: row.PicturePath,
                agentId: row.agentId + "",
                brokerId: row.brokerName,
                isLogin: row.isLogin
                //存储 理财产品id

            };this.sortNo = row.SortNo;
            this.dialogFormVisibleEditPop = true;
        },

        //  确定编辑方法
        confirmAddEdit() {
            var params = new URLSearchParams();

            params.append('sortNo', this.sortNo);
            params.append('picturePath', this.newformEdit.imagePath);
            params.append('skipPath', this.newformEdit.skipPath);
            params.append('appID', this.newformEdit.appID);
            params.append('description', this.newformEdit.description);
            params.append('isLogin', this.newformEdit.isLogin);
            if (this.newformEdit.agentId != null && this.newformEdit.agentId != 'null' + "") {
                params.append('agentId', this.newformEdit.agentId);
            }

            if (this.brokerChange != this.newformEdit.brokerId && this.newformEdit.brokerId != null) {
                params.append('brokerId', this.newformEdit.brokerId);
            }
            let _this = this;
            axios.post(this.editUrl, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.dialogFormVisibleEditPop = false;
                    _this.loadData1();
                    _this.loadData2();
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
        }
    }

});

/***/ }),

/***/ 699:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "img[data-v-4327cb6a]{width:50%;display:block;margin:0 auto}", ""]);

// exports


/***/ }),

/***/ 790:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("\n            Banner图管理\n        ")]), _vm._v(" "), _c('div', {
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
      "label": "顺序",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "图片",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.readOne(scope.$index, scope.row)
            }
          }
        }, [_vm._v("查看")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "跳转地址",
      "prop": "SkipPath",
      "width": "400"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "是否登录可见",
      "prop": "isLogin",
      "formatter": _vm.convertIsLogin,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "可见用户的代理商",
      "prop": "agentName",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "可见用户的经纪人",
      "prop": "brokerName",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "描述",
      "prop": "Description",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "300"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.SerialNo != -1) ? [_c('el-button', {
          attrs: {
            "size": "small",
            "disabled": _vm.ifShowBtnUp(scope.row)
          },
          on: {
            "click": function($event) {
              _vm.goUp(scope.$index, scope.row)
            }
          }
        }, [_vm._v("向上")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "disabled": _vm.ifShowBtnDown(scope.row)
          },
          on: {
            "click": function($event) {
              _vm.goDown(scope.$index, scope.row)
            }
          }
        }, [_vm._v("向下")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.handleEdit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("删除")])] : _vm._e(), _vm._v(" "), (scope.row.SerialNo == -1) ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "success"
          },
          on: {
            "click": function($event) {
              _vm.add(scope.$index, scope.row)
            }
          }
        }, [_vm._v("添加")])] : _vm._e()]
      }
    }])
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
      "label": "图片地址",
      "label-width": _vm.formLabelWidth,
      "prop": "imagePath"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.imagePath),
      callback: function($$v) {
        _vm.$set(_vm.newform, "imagePath", $$v)
      },
      expression: "newform.imagePath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "AppID",
      "label-width": _vm.formLabelWidth,
      "prop": "appID"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.ifShow($event)
      }
    },
    model: {
      value: (_vm.newform.appID),
      callback: function($$v) {
        _vm.$set(_vm.newform, "appID", $$v)
      },
      expression: "newform.appID"
    }
  }, _vm._l((_vm.appIds), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), (_vm.show) ? [_c('el-form-item', {
    attrs: {
      "label": "跳转地址",
      "label-width": _vm.formLabelWidth,
      "prop": "skipPath"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.skipPath),
      callback: function($$v) {
        _vm.$set(_vm.newform, "skipPath", $$v)
      },
      expression: "newform.skipPath"
    }
  })], 1)] : _vm._e(), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否登录可见",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    model: {
      value: (_vm.newform.isLogin),
      callback: function($$v) {
        _vm.$set(_vm.newform, "isLogin", $$v)
      },
      expression: "newform.isLogin"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": 1
    }
  }, [_vm._v("是")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": 0
    }
  }, [_vm._v("否")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户代理商",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerList()
      }
    },
    model: {
      value: (_vm.newform.agentId),
      callback: function($$v) {
        _vm.$set(_vm.newform, "agentId", $$v)
      },
      expression: "newform.agentId"
    }
  }, _vm._l((_vm.agentList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户经纪人",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newform.brokerId),
      callback: function($$v) {
        _vm.$set(_vm.newform, "brokerId", $$v)
      },
      expression: "newform.brokerId"
    }
  }, _vm._l((_vm.brokerList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入描述",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newform.description),
      callback: function($$v) {
        _vm.$set(_vm.newform, "description", $$v)
      },
      expression: "newform.description"
    }
  })], 1)], 2), _vm._v(" "), _c('div', {
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
        _vm.resetForm('newform')
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmAdd('newform')
      }
    }
  }, [_vm._v("确 定")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEdit,
      "width": "30% ",
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
      "label": "图片地址",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.imagePath),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "imagePath", $$v)
      },
      expression: "newformEdit.imagePath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入AppID",
      "label-width": _vm.formLabelWidth,
      "prop": "appID"
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.ifShow($event)
      },
      "chaange": function($event) {
        _vm.ifShow($event)
      }
    },
    model: {
      value: (_vm.newformEdit.appID),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "appID", $$v)
      },
      expression: "newformEdit.appID"
    }
  }, _vm._l((_vm.appIds), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.name,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), (_vm.show) ? [_c('el-form-item', {
    attrs: {
      "label": "跳转地址",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.skipPath),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "skipPath", $$v)
      },
      expression: "newformEdit.skipPath"
    }
  })], 1)] : _vm._e(), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否登录可见",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    model: {
      value: (_vm.newformEdit.isLogin),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "isLogin", $$v)
      },
      expression: "newformEdit.isLogin"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": 1
    }
  }, [_vm._v("是")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": 0
    }
  }, [_vm._v("否")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户代理商",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerList()
      }
    },
    model: {
      value: (_vm.newformEdit.agentId),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "agentId", $$v)
      },
      expression: "newformEdit.agentId"
    }
  }, _vm._l((_vm.agentList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户经纪人",
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
  }, _vm._l((_vm.brokerList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入描述",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.description),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "description", $$v)
      },
      expression: "newformEdit.description"
    }
  })], 1)], 2), _vm._v(" "), _c('div', {
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
      "title": "编辑",
      "visible": _vm.dialogFormVisibleRead,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleRead = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.readform
    }
  }, [_c('img', {
    attrs: {
      "src": _vm.readform.picturePath
    }
  })])], 1), _vm._v(" "), _c('el-dialog', {
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
  }, [_vm._v("确 定")])], 1)])], 1), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('h2', [_vm._v("\n            首页弹出图（默认所有人弹出）\n        ")]), _vm._v(" "), _c('div', {
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
      "label": "顺序",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "图片",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small"
          },
          on: {
            "click": function($event) {
              _vm.readOne(scope.$index, scope.row)
            }
          }
        }, [_vm._v("查看")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "跳转地址",
      "prop": "SkipPath",
      "width": "400"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "是否登录可见",
      "prop": "isLogin",
      "formatter": _vm.convertIsLogin,
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "可见用户的代理商",
      "prop": "agentName",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "可见用户的经纪人",
      "prop": "brokerName",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "描述",
      "prop": "Description",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "生效时间",
      "prop": "CreateTime",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "240"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.SerialNo != -1) ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.handleEditPop(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteDialog(scope.$index, scope.row)
            }
          }
        }, [_vm._v("删除")])] : _vm._e(), _vm._v(" "), (scope.row.SerialNo == -1) ? [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.addFirstPage(scope.$index, scope.row)
            }
          }
        }, [_vm._v("添加")])] : _vm._e()]
      }
    }])
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "tableBox"
  }, [_c('el-dialog', {
    attrs: {
      "title": "添加",
      "visible": _vm.dialogFormVisibleAddFirstPage,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleAddFirstPage = $event
      }
    }
  }, [_c('el-form', {
    ref: "newformFirst",
    attrs: {
      "model": _vm.newformFirst,
      "rules": _vm.rules
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "图片路径",
      "label-width": _vm.formLabelWidth,
      "prop": "imagePath"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformFirst.imagePath),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "imagePath", $$v)
      },
      expression: "newformFirst.imagePath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入跳转路径",
      "label-width": _vm.formLabelWidth,
      "prop": "skipPath"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformFirst.skipPath),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "skipPath", $$v)
      },
      expression: "newformFirst.skipPath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否登录可见",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    model: {
      value: (_vm.newformFirst.isLogin),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "isLogin", $$v)
      },
      expression: "newformFirst.isLogin"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": 1
    }
  }, [_vm._v("是")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": 0
    }
  }, [_vm._v("否")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户代理商",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerList()
      }
    },
    model: {
      value: (_vm.newformFirst.agentId),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "agentId", $$v)
      },
      expression: "newformFirst.agentId"
    }
  }, _vm._l((_vm.agentList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户经纪人",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.newformFirst.brokerId),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "brokerId", $$v)
      },
      expression: "newformFirst.brokerId"
    }
  }, _vm._l((_vm.brokerList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入描述",
      "label-width": _vm.formLabelWidth,
      "prop": "description"
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformFirst.description),
      callback: function($$v) {
        _vm.$set(_vm.newformFirst, "description", $$v)
      },
      expression: "newformFirst.description"
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
        _vm.resetForm('newformFirst')
      }
    }
  }, [_vm._v("取 消")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.confirmAddFirstPage('newformFirst')
      }
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "编辑",
      "visible": _vm.dialogFormVisibleEditPop,
      "center": "",
      "width": "30%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleEditPop = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.newformEdit
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "图片路径",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.imagePath),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "imagePath", $$v)
      },
      expression: "newformEdit.imagePath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入跳转路径",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.skipPath),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "skipPath", $$v)
      },
      expression: "newformEdit.skipPath"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否登录可见",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-radio-group', {
    model: {
      value: (_vm.newformEdit.isLogin),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "isLogin", $$v)
      },
      expression: "newformEdit.isLogin"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": 1
    }
  }, [_vm._v("是")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": 0
    }
  }, [_vm._v("否")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户代理商",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-select', {
    attrs: {
      "size": "small",
      "placeholder": "请选择"
    },
    on: {
      "change": function($event) {
        _vm.getBrokerList()
      }
    },
    model: {
      value: (_vm.newformEdit.agentId),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "agentId", $$v)
      },
      expression: "newformEdit.agentId"
    }
  }, _vm._l((_vm.agentList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.agentName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "可见用户经纪人",
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
  }, _vm._l((_vm.brokerList), function(item) {
    return _c('el-option', {
      key: item.id,
      attrs: {
        "label": item.brokerName,
        "value": item.id
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "输入描述",
      "label-width": _vm.formLabelWidth
    }
  }, [_c('el-input', {
    attrs: {
      "size": "small",
      "auto-complete": "off"
    },
    model: {
      value: (_vm.newformEdit.description),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "description", $$v)
      },
      expression: "newformEdit.description"
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
        _vm.dialogFormVisibleEditPop = false
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
      "title": "查看",
      "visible": _vm.dialogFormVisibleRead,
      "center": "",
      "width": "50%"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogFormVisibleRead = $event
      }
    }
  }, [_c('el-form', {
    attrs: {
      "model": _vm.readform
    }
  }, [_c('img', {
    attrs: {
      "src": _vm.readform.picturePath,
      "alt": "暂无图片"
    }
  })])], 1), _vm._v(" "), _c('el-dialog', {
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
  }), _vm._v(" 运营管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("Banner及弹出图管理")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 869:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(699);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("251fb781", content, true);

/***/ })

});