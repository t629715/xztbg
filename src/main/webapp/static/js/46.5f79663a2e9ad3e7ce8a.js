webpackJsonp([46],{

/***/ 552:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(907)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(645),
  /* template */
  __webpack_require__(820),
  /* scopeId */
  "data-v-44ca76c7",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 645:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            newformAdd: {
                id: '',
                logisticsNo: ''
            },
            newformEdit: {
                companyAddress: '',
                companyPhone: '',
                name: ''
            },

            url: this.api + "goldBuyBackConf/selectBuyBackConf",
            editUrl: this.api + "goldBuyBackConf/updateBuyBackConf",

            tableData: [],
            //disabledEdit: true,
            //checkbox:false,
            dialogFormVisible: false,
            dialogFormVisibleAdd: false,
            dialogFormVisibleEdit: false,
            dialogFormVisibleComplete: false,
            formLabelWidth: '120px',
            id: ""

        };
    },
    //页面渲染加载方法
    created() {

        this.loadData(10, 1);
        let _this = this;
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
            let _this = this;
            axios.post(this.url).then(function (response) {
                if (response.data.code == 1001) {
                    _this.$message({
                        message: '查询成功',
                        type: 'success'
                    });
                    _this.tableData = response.data.data;
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
            let _this = this;
            axios.post(this.url).then(function (response) {
                if (response.data.code == 1001) {
                    var list = response.data.data;

                    _this.tableData = list;
                }
            }).catch(function (error) {});
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

        //编辑
        handleEdit(index, row) {
            this.newformEdit = {
                companyAddress: row.companyAddress,
                companyPhone: row.companyPhone,
                name: row.name

            };
            this.tqId = row.id;
            this.dialogFormVisibleEdit = true;
        },
        //编辑-确定
        confirmEdit(formName) {
            var params = new URLSearchParams();
            params.append('companyAddress', this.newformEdit.companyAddress);
            params.append('companyPhone', this.newformEdit.companyPhone);
            params.append('name', this.newformEdit.name);
            params.append('id', this.tqId);
            let _this = this;
            this.$refs[formName].validate(valid => {
                if (valid) {
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

/***/ 721:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".el-row[data-v-44ca76c7]{margin-bottom:20px;& :last-child{margin-bottom:0}}.el-col[data-v-44ca76c7]{border-radius:4px}.bg-purple-dark[data-v-44ca76c7]{background:#99a9bf}.bg-color1[data-v-44ca76c7]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-44ca76c7]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-44ca76c7]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-44ca76c7]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-44ca76c7]{background:#d3dce6}.bg-purple-light[data-v-44ca76c7]{background:#e5e9f2}.gridBox[data-v-44ca76c7]{padding-left:20px}.grid-content[data-v-44ca76c7]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-44ca76c7]{padding:10px 0;background-color:#f9fafc}", ""]);

// exports


/***/ }),

/***/ 820:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "content"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "container-fluid"
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
      "prop": "companyAddress",
      "label": "公司地址",
      "width": "180",
      "fixed": "left"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "companyPhone",
      "label": "公司电话",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "preNum",
      "label": "每天最多预约次数",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "invalidTime",
      "label": "预约单失效时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "pickUpTime",
      "label": "上门取货时间",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "收件人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "收件人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "收件人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "收件人",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "收件人",
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
        return [
          [_c('el-button', {
            attrs: {
              "size": "small",
              "type": "primary"
            },
            on: {
              "click": function($event) {
                _vm.handleEdit(scope.$index, scope.row)
              }
            }
          }, [_vm._v("修改\n                        ")])]
        ]
      }
    }])
  })], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "title": "修改",
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
      "model": _vm.newformEdit
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "公司地址：",
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
      value: (_vm.newformEdit.companyAddress),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "companyAddress", $$v)
      },
      expression: "newformEdit.companyAddress"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司电话：",
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
      value: (_vm.newformEdit.companyPhone),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "companyPhone", $$v)
      },
      expression: "newformEdit.companyPhone"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "收件人：",
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
      value: (_vm.newformEdit.name),
      callback: function($$v) {
        _vm.$set(_vm.newformEdit, "name", $$v)
      },
      expression: "newformEdit.name"
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
  }, [_vm._v("取 消")])], 1)], 1)], 1)])
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
  }), _vm._v("\n            运营管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("回购设置")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 907:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(721);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("86f7e67e", content, true);

/***/ })

});