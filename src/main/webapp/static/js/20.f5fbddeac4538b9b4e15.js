webpackJsonp([20],{

/***/ 578:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(889)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(670),
  /* template */
  __webpack_require__(802),
  /* scopeId */
  "data-v-26e60117",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 670:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    //model 初始数据
    data() {
        return {
            loading: true,
            url: this.api + "registFrom/countRegistFrom",
            currentPage: 0,
            pagesize: 10,
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
    //定义方法
    methods: {
        //刷新表格方法
        loadData(pagesize, pageNum) {
            var params = new URLSearchParams();
            this.pagesize = pagesize;
            this.currentPage = pageNum;
            params.append('pageSize', this.pagesize);
            params.append('pageNum', this.currentPage);
            // params.append('phone', phoneNum);
            // params.append('startTime', datetime1);
            // params.append('endTime', datetime2);
            let _this = this;

            axios.post(this.url, params).then(function (response) {
                _this.currentPage = response.data.data.pageNum;
                _this.pagesize = response.data.data.pageSize;
                _this.pageNum = response.data.data.pages;
                _this.totalNum = response.data.data.total;
                _this.tableData = response.data.data.list;
            }).catch(function (error) {
                self.$router.push('/login');
            });
        },
        //当前页改变是执行
        handleCurrentChange(val) {
            // this.pageNum=val;
            // this.onSubmit(this.form)
            this.loadData(this.pagesize, val);
        },
        //页数size 改变时执行
        handleSizeChange(val) {
            this.loadData(val, 1);
        }
    }
});

/***/ }),

/***/ 705:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 802:
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
      "fit": "",
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
      "prop": "source",
      "label": "source",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "media",
      "label": "media",
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "num",
      "label": "注册数量",
      "width": "180"
    }
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
  })], 1)])])
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
  }), _vm._v("运营管理")]), _vm._v(" "), _c('a', {
    staticClass: "current",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v("推广页注册统计")])])])
}]}

/***/ }),

/***/ 889:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(705);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("10cfa5fc", content, true);

/***/ })

});