webpackJsonp([10],{554:function(t,e,a){a(778);var o=a(196)(a(611),a(726),"data-v-d679c810",null);t.exports=o.exports},611:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{loading:!0,actorList:"",form:{date1:"",date2:"",agentId:"",delivery:!1,type:[],resource:"",desc:""},brokeList:[],agentOptions:[{id:2,name:"直客"},{id:-11,name:"代理商"}],url:"analysis/orderAnalysis",exportUrl:"analysis/exportAnalysis",countUrl:"analysis/orderAnalysisCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],formLabelWidth:"120px",userId:"",countList:"",userTotal:""}},filters:{divide:function(t){return null==t?0:t},convert:function(t){return null==t?0:t}},created:function(){},methods:{selectTime:function(){},whicth:function(){},countNum:function(t,e){var a=new URLSearchParams,o=this,r="",i="",s="",n="",l="",d="",p="";void 0!=this.form.profitLoss&&(n=this.form.profitLoss),void 0!=this.form.orderState&&(l=this.form.orderState),void 0!=this.form.upOrDown&&(d=this.form.upOrDown),void 0!=this.form.time&&(p=this.form.time),""!=this.form.date1&&(r=Date.parse(this.form.date1)),""!=this.form.date2&&(i=Date.parse(this.form.date2)),void 0!=this.form.agentId&&(s=this.form.agentId),this.pagesize=t,this.currentPage=e,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",i),a.append("agentId",s),a.append("profitLoss",n),a.append("orderState",l),a.append("upOrDown",d),a.append("time",p),axios.post(this.countUrl,a).then(function(t){o.countList=t.data.data}).catch(function(t){console.log(t)})},onSubmit:function(t){this.countNum(10,1);var e=new URLSearchParams,a=this,o="",r="",i="",s="",n="",l="",d="";void 0!=this.form.profitLoss&&(s=this.form.profitLoss),void 0!=this.form.orderState&&(n=this.form.orderState),void 0!=this.form.upOrDown&&(l=this.form.upOrDown),void 0!=this.form.time&&(d=this.form.time),""!=this.form.date1&&(o=Date.parse(this.form.date1)),""!=this.form.date2&&(r=Date.parse(this.form.date2)),void 0!=this.form.agentId&&(i=this.form.agentId),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("startTime",o),e.append("endTime",r),e.append("agentId",i),e.append("profitLoss",s),e.append("orderState",n),e.append("upOrDown",l),e.append("time",d),axios.post(this.url,e).then(function(t){a.$message({message:"查询成功",type:"success"}),a.currentPage=1,a.pageNum=t.data.data.pages,a.totalNum=t.data.data.total,a.tableData=t.data.data.list}).catch(function(t){})},resetForm:function(){this.$refs.form.resetFields()},exportFun:function(){var t=new URLSearchParams,e="",a="",o="",r="",i="",s="",n="";void 0!=this.form.profitLoss&&(r=this.form.profitLoss),void 0!=this.form.orderState&&(i=this.form.orderState),void 0!=this.form.upOrDown&&(s=this.form.upOrDown),void 0!=this.form.time&&(n=this.form.time),""!=this.form.date1&&(e=Date.parse(this.form.date1)),""!=this.form.date2&&(a=Date.parse(this.form.date2)),void 0!=this.form.agentId&&(o=this.form.agentId),t.append("startTime",e),t.append("endTime",a),t.append("agentId",o),t.append("profitLoss",r),t.append("orderState",i),t.append("upOrDown",s),t.append("time",n),void 0==this.form.time&&""==this.form.date1&&""==this.form.date2||(window.location=this.exportUrl+"?"+t)},loadData:function(t,e){var a=new URLSearchParams,o="",r="",i="",s="",n="",l="",d="";void 0!=this.form.profitLoss&&(s=this.form.profitLoss),void 0!=this.form.orderState&&(n=this.form.orderState),void 0!=this.form.upOrDown&&(l=this.form.upOrDown),void 0!=this.form.time&&(d=this.form.time),""!=this.form.date1&&(o=Date.parse(this.form.date1)),""!=this.form.date2&&(r=Date.parse(this.form.date2)),void 0!=this.form.agentId&&(i=this.form.agentId),this.pagesize=t,this.currentPage=e,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",o),a.append("endTime",r),a.append("agentId",i),a.append("profitLoss",s),a.append("orderState",n),a.append("upOrDown",l),a.append("time",d);var p=this;axios.post(this.url,a).then(function(t){p.totalNum=t.data.data.total,p.pageNum=t.data.data.pages,p.tableData=t.data.data.list}).catch(function(t){})},handleCurrentChange:function(t){this.loadData(this.pagesize,t),this.countNum(this.pagesize,t)},handleSizeChange:function(t){this.loadData(t,1),this.countNum(t,1)}}}},663:function(t,e,a){e=t.exports=a(135)(void 0),e.push([t.i,".el-row[data-v-d679c810]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-d679c810]{border-radius:4px}.bg-purple-dark[data-v-d679c810]{background:#99a9bf}.bg-color1[data-v-d679c810]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-d679c810]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-d679c810]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-d679c810]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-d679c810]{background:#d3dce6}.bg-purple-light[data-v-d679c810]{background:#e5e9f2}.gridBox[data-v-d679c810]{padding-left:20px}.grid-content[data-v-d679c810]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-d679c810]{padding:10px 0;background-color:#f9fafc}",""])},726:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content"}},[t._m(0,!1,!1),t._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:t.form,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"时间：",prop:"radio1"}},[a("el-radio-group",{attrs:{size:"small"},model:{value:t.form.time,callback:function(e){t.$set(t.form,"time",e)},expression:"form.time"}},[a("el-radio-button",{attrs:{label:"1",name:"1"}},[t._v("今天")]),t._v(" "),a("el-radio-button",{attrs:{label:"2",name:"1"}},[t._v("昨天")]),t._v(" "),a("el-radio-button",{attrs:{label:"3",name:"1"}},[t._v("最近7天")]),t._v(" "),a("el-radio-button",{attrs:{label:"4",name:"1"}},[t._v("最近30天")])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"交易类型：",prop:"radio2"}},[a("el-radio-group",{attrs:{size:"small"},model:{value:t.form.upOrDown,callback:function(e){t.$set(t.form,"upOrDown",e)},expression:"form.upOrDown"}},[a("el-radio-button",{attrs:{label:"",name:"2"}},[t._v("全部")]),t._v(" "),a("el-radio-button",{attrs:{label:"0",name:"2"}},[t._v("买涨")]),t._v(" "),a("el-radio-button",{attrs:{label:"1",name:"2"}},[t._v("买跌")])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"交易状态：",prop:"radio3"}},[a("el-radio-group",{attrs:{size:"small"},model:{value:t.form.orderState,callback:function(e){t.$set(t.form,"orderState",e)},expression:"form.orderState"}},[a("el-radio-button",{attrs:{label:"",name:"3",value:1}},[t._v("全部")]),t._v(" "),a("el-radio-button",{attrs:{label:"0",name:"3",value:2}},[t._v("持仓")]),t._v(" "),a("el-radio-button",{attrs:{label:"1",name:"3",value:3}},[t._v("平仓")])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"盈亏状态：",prop:"radio4"}},[a("el-radio-group",{attrs:{size:"small"},model:{value:t.form.profitLoss,callback:function(e){t.$set(t.form,"profitLoss",e)},expression:"form.profitLoss"}},[a("el-radio-button",{attrs:{label:"",name:"4"}},[t._v("全部")]),t._v(" "),a("el-radio-button",{attrs:{label:"1",name:"4"}},[t._v("盈利")]),t._v(" "),a("el-radio-button",{attrs:{label:"2",name:"4"}},[t._v("亏损")]),t._v(" "),a("el-radio-button",{attrs:{label:"3",name:"4"}},[t._v("持平")])],1)],1),a("br"),t._v(" "),a("el-form-item",{attrs:{label:"自定义",prop:"date1"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:t.form.date1,callback:function(e){t.$set(t.form,"date1",e)},expression:"form.date1"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{prop:"date2"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:t.form.date2,callback:function(e){t.$set(t.form,"date2",e)},expression:"form.date2"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentId"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.form.agentId,callback:function(e){t.$set(t.form,"agentId",e)},expression:"form.agentId"}},t._l(t.agentOptions,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(e){t.onSubmit(t.form)}}},[t._v("查询")])],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(e){t.resetForm(t.form)}}},[t._v("清除")])],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"info"},on:{click:function(e){t.exportFun(t.form)}}},[t._v("导出")])],1)],1),t._v(" "),a("div",[a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[t._v("用户："+t._s(t._f("convert")(t.countList.userTotal)))]),t._v(" "),a("p",[t._v("交易资金："+t._s(t._f("divide")(t.countList.amountTotal))+" ")])])]),t._v(" "),a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[t._v("黄金稳赚用户："+t._s(t._f("convert")(t.countList.goldUpCountTotal)))]),t._v(" "),a("p",[t._v("黄金稳赚交易资金："+t._s(t._f("divide")(t.countList.goldUpAmountTotal))+" ")])])]),t._v(" "),a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-color3"},[a("p",[t._v("金权交易用户："+t._s(t._f("convert")(t.countList.goldRightCountTotal)))]),t._v(" "),a("p",[t._v("金权交易资金："+t._s(t._f("divide")(t.countList.goldRightAmountTotal))+" ")])])]),t._v(" "),a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-color4"},[a("p",[t._v("实物黄金用户："+t._s(t._f("convert")(t.countList.realGoldCountTotal)))]),t._v(" "),a("p",[t._v("实物黄金交易资金："+t._s(t._f("divide")(t.countList.realGoldAmountTotal))+" ")])])]),t._v(" "),a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-color4"},[a("p",[t._v("随意存用户："+t._s(t._f("convert")(t.countList.financeCountTotal)))]),t._v(" "),a("p",[t._v("随意存交易资金："+t._s(t._f("divide")(t.countList.financeAmountTotal))+" ")])])])],1),t._v(" "),a("el-table",{staticStyle:{width:"auto",display:"inline-block"},attrs:{data:t.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"date",label:"时间",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"perCount",label:"交易用户",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"perAmount",label:"交易金额",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goldUpCount",label:"黄金稳赚交易用户",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goldUpAmount",label:"黄金稳赚交易金额",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"financeCount",label:"随意存交易用户",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"financeAmount",label:"随意存交易金额",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goldRightCount",label:"金权交易用户",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goldRightAmount",label:"金权交易金额",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"realGoldCount",label:"实物黄金交易用户",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"realGoldAmount",label:"实物黄金交易金额",width:"180"}})],1),t._v(" "),a("br"),a("br"),t._v(" "),a("el-pagination",{attrs:{"current-page":t.currentPage,"page-sizes":[10,20,30,40],"page-size":t.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:t.totalNum},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)])},staticRenderFns:[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),t._v(" 交易管理")]),t._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[t._v("交易分析")])]),t._v(" "),a("h1")])}]}},778:function(t,e,a){var o=a(663);"string"==typeof o&&(o=[[t.i,o,""]]),o.locals&&(t.exports=o.locals);a(197)("5f28e450",o,!0)}});