webpackJsonp([19],{541:function(t,a,e){e(723);var r=e(196)(e(594),e(675),"data-v-128c1567",null);t.exports=r.exports},594:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0}),a.default={data:function(){return{loading:!0,url1:"dealOrder/getHedgeArbitrage1",url2:"dealOrder/getHedgeArbitrage2",url3:"dealOrder/getHedgeArbitrage3",tableData1:[],tableData2:[],tableData3:[],formLabelWidth:"120px"}},created:function(){var t=this;this.loadData1(),this.loadData2(),this.loadData3(),this.intervalid1=setInterval(function(){t.loadData1(),t.loadData2(),t.loadData3()},1e3)},beforeDestroy:function(){clearInterval(this.intervalid1)},methods:{loadData1:function(){var t=new URLSearchParams,a=this;axios.post(this.url1,t).then(function(t){var e=new Array;e.push(t.data.data),a.tableData1=e}).catch(function(t){})},loadData2:function(){var t=new URLSearchParams,a=this;axios.post(this.url2,t).then(function(t){var e=new Array;e.push(t.data.data),a.tableData2=e}).catch(function(t){})},loadData3:function(){var t=new URLSearchParams,a=this;axios.post(this.url3,t).then(function(t){var e=new Array;e.push(t.data.data),a.tableData3=e}).catch(function(t){})}}}},615:function(t,a,e){a=t.exports=e(135)(void 0),a.push([t.i,"",""])},675:function(t,a){t.exports={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"content"}},[t._m(0,!1,!1),t._v(" "),e("div",{staticClass:"container-fluid"},[t._v("\n    沪金主链\n      "),e("div",{staticClass:"tableBox"},[e("el-table",{staticStyle:{width:"260px"},attrs:{data:t.tableData1,border:"",stripe:""}},[e("el-table-column",{attrs:{label:"买涨持仓克重",prop:"gramUp",width:""}}),t._v(" "),e("el-table-column",{attrs:{label:"买涨均价",prop:"avgUp",width:""}})],1)],1),t._v(" "),e("div",{staticClass:"tableBox"},[e("el-table",{staticStyle:{width:"260px"},attrs:{data:t.tableData2,border:"",stripe:""}},[e("el-table-column",{attrs:{label:"买跌持仓克重",prop:"gramDown",width:""}}),t._v(" "),e("el-table-column",{attrs:{label:"买跌均价",prop:"avgDown",width:""}})],1)],1),t._v(" "),e("div",{staticClass:"tableBox"},[e("el-table",{staticStyle:{width:"260px"},attrs:{data:t.tableData3,border:"",stripe:""}},[e("el-table-column",{attrs:{label:"净值",prop:"netValue",width:""}}),t._v(" "),e("el-table-column",{attrs:{label:"获利",prop:"profit",width:""}})],1)],1)])])},staticRenderFns:[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"content-header"}},[e("div",{attrs:{id:"breadcrumb"}},[e("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[e("i",{staticClass:"icon-home"}),t._v(" 产品设定")]),t._v(" "),e("a",{staticClass:"current",attrs:{href:"javascript:;"}},[t._v("对冲套利")])]),t._v(" "),e("h1")])}]}},723:function(t,a,e){var r=e(615);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);e(197)("44dbd1dd",r,!0)}});