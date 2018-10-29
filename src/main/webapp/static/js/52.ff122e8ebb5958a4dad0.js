webpackJsonp([52],{

/***/ 542:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(917)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(632),
  /* template */
  __webpack_require__(833),
  /* scopeId */
  "data-v-73dc5406",
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            logoImgSrc: "../../../static/img/logo.png"
        };
    },

    created() {
        var curWwwPath = window.document.location.href;
        var uri = curWwwPath.split("#")[0];
        this.logoImgSrc = uri + "static/img/logo.png";
    }

});

/***/ }),

/***/ 737:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(136)(false);
// imports


// module
exports.push([module.i, ".hello[data-v-73dc5406]{width:100%;height:100%;background:#31252b!important;font-size:40px;color:blue;text-align:center;padding-top:200px}", ""]);

// exports


/***/ }),

/***/ 833:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "hello"
  }, [_c('img', {
    staticClass: "mr25",
    attrs: {
      "src": _vm.logoImgSrc,
      "width": "200px",
      "alt": "Logo"
    }
  })])
},staticRenderFns: []}

/***/ }),

/***/ 917:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(737);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("84c78678", content, true);

/***/ })

});