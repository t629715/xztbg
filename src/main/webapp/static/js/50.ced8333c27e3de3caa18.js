webpackJsonp([50],{

/***/ 543:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(926)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(632),
  /* template */
  __webpack_require__(843),
  /* scopeId */
  "data-v-9b73e93a",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 632:
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
                id: 'TopicPicture',
                name: "专题"
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
                imagePath: [{ required: true, message: '请输入图片地址', trigger: 'blur' }],
                skipPath: [{ required: true, message: '请输入跳转地址', trigger: 'blur' }],
                appID: [{ required: true, message: '请输入appID ', trigger: 'blur' }],
                description: [{ required: true, message: '请输入描述信息 ', trigger: 'blur' }]

            },

            url: this.api + "infoBanner/getAdPic1",
            addUrl: this.api + "infoBanner/insertBanner",
            editUrl: this.api + "infoBanner/edit",
            deleteUrl: this.api + "infoBanner/delete",
            upOrDown: this.api + "infoBanner/upDown",
            agentUrl: this.api + "user/selectByAgentMessage",
            brokerUrl: this.api + "user/selectByBrokerMessage",
            tableData: [],
            dialogFormVisibleRead: false,
            dialogFormVisible: false,
            dialogFormVisibleAdd: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleRead: false,
            dialogFormVisibleDelete: false,
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
        this.loadData();
        this.getAgentList();
    },
    methods: {

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
        loadData() {
            var params = new URLSearchParams();
            params.append('page', 5);
            let _this = this;
            axios.post(this.url, params).then(function (response) {
                _this.tableData = response.data.data;

                if (_this.tableData[0].SortNo != undefined) {
                    _this.maxSortNo = _this.tableData[0].SortNo;
                    _this.minSortNo = _this.tableData[0].SortNo;
                }

                for (var i = 0; _this.tableData.length; i++) {
                    if (_this.tableData[i].SortNo != undefined) {
                        if (_this.minSortNo > _this.tableData[i].SortNo) {
                            _this.minSortNo = _this.tableData[i].SortNo;
                        }
                    }
                }
                _this.currentPage = 1;
                //_this.pagesize = response.data.data.pageSize;
                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
            }).catch(function (error) {});
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

        //  确定添加用户
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
            params.append('page', '5');
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
                            _this.loadData();
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
        // 获取代理商列表
        getAgentList() {
            let _this = this;
            axios.get(this.agentUrl).then(function (response) {
                _this.agentList = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        getBrokerListForAdd() {
            let _this = this;
            _this.newform.brokerId = "";

            if (_this.newform.agentId != undefined) {
                var params = new URLSearchParams();
                if ("" != _this.newform.agentId) {
                    params.append('pid', _this.newform.agentId);
                }
                axios.post(_this.brokerUrl, params).then(function (response) {
                    _this.brokerList = response.data.data;
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        getBrokerListForEdit() {
            let _this = this;
            _this.newformEdit.brokerId = "";

            if (_this.newformEdit.agentId != undefined) {
                var params = new URLSearchParams();
                if ("" != _this.newformEdit.agentId) {
                    params.append('pid', _this.newformEdit.agentId);
                }
                axios.post(_this.brokerUrl, params).then(function (response) {
                    _this.brokerList = response.data.data;
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisibleAdd = false;
        },
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
                    _this.loadData();
                } else if (!response.data.data) {
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

        goUp(index, row) {
            let _this = this;
            var params = new URLSearchParams();
            params.append('upSortNo', this.tableData[index - 1].SortNo);
            params.append('downSortNo', row.SortNo);
            axios.post(this.upOrDown, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '调整成功',
                        type: 'success'
                    });
                    _this.loadData();
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
            params.append('downSortNo', this.tableData[index + 1].SortNo);
            axios.post(this.upOrDown, params).then(function (response) {
                if (response.data.data) {
                    _this.$message({
                        message: '调整成功',
                        type: 'success'
                    });
                    _this.dialogFormVisibleEdit = false;
                    _this.loadData();
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

        //  编辑按钮
        handleEdit(index, row) {
            this.brokerChange = row.brokerName;
            this.newformEdit = {
                skipPath: row.SkipPath,
                description: row.Description,
                appID: row.AppID,
                imagePath: row.PicturePath,
                agentId: row.agentId == null ? "" : row.agentId + "",
                brokerId: row.brokerName,
                isLogin: row.isLogin
                //存储 理财产品id
            };if (row.AppID == 'ActivityNormalPage') {
                this.show = true;
            } else {
                this.show = false;
            }
            this.sortNo = row.SortNo;
            this.dialogFormVisibleEdit = true;
        },

        //  确定编辑方法
        confirmAddEdit() {
            var params = new URLSearchParams();
            params.append('sortNo', this.sortNo);
            params.append('picturePath', this.newformEdit.imagePath);
            params.append('skipPath', this.newformEdit.skipPath);
            params.append('appID', this.newformEdit.appID);
            /*params.append('skipType', this.newformEdit.skipType );*/
            params.append('description', this.newformEdit.description);
            params.append('isLogin', this.newformEdit.isLogin);
            params.append('agentId', this.newformEdit.agentId);
            if (this.brokerChange != this.newformEdit.brokerId) {
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
                    _this.loadData();
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

/***/ 748:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "img[data-v-9b73e93a]{width:100%}", ""]);

// exports


/***/ }),

/***/ 843:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
  }, [_c('div', {
    staticClass: "tableBox"
  }, [_c('el-table', {
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
        _vm.getBrokerListForAdd()
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
        _vm.getBrokerListForEdit()
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
  }, [_vm._v("专题图片管理")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 926:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(748);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("25d0e262", content, true);

/***/ })

});