webpackJsonp([47],{

/***/ 544:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(908)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(631),
  /* template */
  __webpack_require__(827),
  /* scopeId */
  "data-v-7880143e",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 631:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loading: true,
            url: this.api + "worldCupController/getGuessResult",
            currentPage: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            tableData: [],
            formLabelWidth: '120px'
        };
    },
    //页面渲染加载方法
    created() {
        this.loadData(10, 1);
    },
    methods: {

        //查询方法
        /*onSubmit(form) {
            var params = new URLSearchParams();
            let _this=this;
            var datetime1='',datetime2="",title="",state="",operator="";
            if(form.title==undefined){
                title="";
            }else {
                title=form.title;
            }
            if(form.date1!=""){
                datetime1=Date.parse(form.date1)
            }
            if(form.date2!=''){
                datetime2=Date.parse(form.date2)
            }
            if(form.state!==''){
                state=form.state
            }
            if(form.operator!=''){
                operator=form.operator
            }
            params.append('pageSize', _this.pageSize);
            params.append('pageNum',1);
            params.append('title',title);
            params.append('startTime',datetime1);
            params.append('endTime',datetime2);
            params.append('state',state);
            params.append('operator',operator);
            axios.post(this.url,params)
                .then(function (response) {
                    _this.tableData=response.data.data.list;
                    _this.currentPage = 1;
                    //_this.pagesize = response.data.data.pageSize;
                    _this.pageNum = response.data.data.pages;
                    _this.totalNum = response.data.data.total;
                })
                .catch(function (error) {
                });
         },*/
        //清空表单
        // resetForm1() {
        //     this.loadData(this.pageSize,1);
        //     this.$refs.form.resetFields();
        // },
        convertCompetition(value) {
            return value.teamAName + "vs" + value.teamBName;
        },
        sumNum(value) {
            return value.voucherNum1 + value.voucherNum2;
        },
        //刷新表格方法
        loadData(pageSize, pageNum) {
            var params = new URLSearchParams();
            let _this = this;

            axios.post(this.url, params).then(function (response) {
                _this.tableData = response.data.data;
            }).catch(function (error) {});
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.dialogFormVisible = false;
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            this.pageNum = val;
            this.loadData(this.pageSize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.pageSize = val;
            this.loadData(val, 1);
        }
    }

});

/***/ }),

/***/ 734:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 827:
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
  }), _vm._v(" "), _c('div', {
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
      "label": "序号",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "赛程",
      "formatter": _vm.convertCompetition,
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "startTime",
      "label": "赛程时间",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "guessNum",
      "label": "竞猜人数",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "winNum",
      "label": "猜胜人数",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "lossNum",
      "label": "猜负人数",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "发放卡券数",
      "formatter": _vm.sumNum,
      "width": "180"
    }
  })], 1)], 1)])])
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
  }, [_vm._v("竞猜统计")])]), _vm._v(" "), _c('h1')])
}]}

/***/ }),

/***/ 908:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(734);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("4f693747", content, true);

/***/ })

});