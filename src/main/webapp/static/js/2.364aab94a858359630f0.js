webpackJsonp([2],{539:function(e,t,o){o(767);var a=o(196)(o(593),o(718),"data-v-f016aeea",null);e.exports=a.exports},552:function(e,t,o){e.exports={default:o(554),__esModule:!0}},553:function(e,t,o){"use strict";t.__esModule=!0;var a=o(552),l=function(e){return e&&e.__esModule?e:{default:e}}(a);t.default=function(e,t,o){return t in e?(0,l.default)(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}},554:function(e,t,o){o(555);var a=o(63).Object;e.exports=function(e,t,o){return a.defineProperty(e,t,o)}},555:function(e,t,o){var a=o(86);a(a.S+a.F*!o(49),"Object",{defineProperty:o(50).f})},593:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a,l=o(553),i=o.n(l);t.default={data:function(){var e;return e={show:!1,loading:!0,actorList:"",form:{username:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""},newformEdit:{productNo:"",productName:"",cycle:"",yearIncomPercent:"",minMoney:"",calcMethod:"",redeemMethod:"",settleMethod:"",nper:""},newformEdit1:{productNo:"",productName:"",cycle:"",yearIncomPercent:"",calcStartPoint:"",calcMethod:"",redeemMethod:"",settleMethod:""},url:"financeConf/getAllFinanceConf",url1:"realGoldBuyConf/getRealGoldBuyConf",editUrl:"financeConf/modifyFinanceConf",editUrl1:"realGoldBuyConf/editRealGoldBuyConf",deleteUrl:"financeConf/removeFinanceConfById"},i()(e,"deleteUrl","realGoldBuyConf/removeRealGoldBuyConf"),i()(e,"currentPage",0),i()(e,"pageSize",10),i()(e,"pageNum",1),i()(e,"totalNum",0),i()(e,"tableData1",[]),i()(e,"tableData2",[]),i()(e,"dialogFormVisible",!1),i()(e,"dialogFormVisibleEdit",!1),i()(e,"dialogFormVisibleDelete",!1),i()(e,"dialogFormVisibleEditGold",!1),i()(e,"formLabelWidth","120px"),i()(e,"productId",""),i()(e,"settleMethods",[{id:1,name:"期限任选，赎回时付"},{id:2,name:"期限任选，按月付息"},{id:3,name:"期限任选，按天付息"},{id:4,name:"期限任选，到期还息"}]),i()(e,"redeemMethods",[{id:1,name:"到期后自由赎回"},{id:2,name:"到期后自动赎回"}]),e},created:function(){this.loadData(10,1),this.loadData1()},filters:{format:function(e){var t=new Decimal(e),o=new Decimal(100);return t.times(o)}},methods:(a={handleEdit:function(e,t){0!=t.nper?this.show=!0:this.show=!1,2==e?(t.calcStartPoint,this.newformEdit1={productNo:t.productNo,productName:t.name,cycle:t.cycle,yearIncomPercent:new Decimal(Number(t.yearIncomPercent)).times(new Decimal(100)).toString(),calcStartPoint:t.calcStartPoint,calcMethod:t.calcMethod,redeemMethod:t.redeemMethod,settleMethod:t.settleMethod}):this.newformEdit={productNo:t.productNo,productName:t.productName,cycle:t.cycle,yearIncomPercent:new Decimal(Number(t.yearIncomPercent)).times(new Decimal(100)).toString(),minMoney:t.minMoney/100,calcMethod:t.calcMethod,redeemMethod:t.redeemMethod,settleMethod:t.settleMethod,nper:t.nper},this.productId=t.id,1==e?(this.type=t.type,this.dialogFormVisibleEdit=!0):this.dialogFormVisibleEditGold=!0},convertRedeemMethod:function(e){return 1==e.redeemMethod?"到期后自由赎回":"到期后自动赎回"},convertYearIncomPercent:function(e){var t=new Decimal(e.yearIncomPercent),o=new Decimal(100);return t.times(o)+"%"},convertMinMoney:function(e){return e.minMoney/100},convertCalcMethod:function(e){return null==e.calcMethod?"-":0===e.calcMethod?"-":"T+"+e.calcMethod},convertNper:function(e){return null==e.nper?"-":0===e.nper?"-":+e.nper},convertSettleMethod:function(e){return 1==e.settleMethod?"期限任选，赎回时付":2==e.settleMethod?"期限任选，按月付息":3==e.settleMethod?"期限任选，按天付息":4==e.settleMethod?"期限任选，到期还息":void 0},confirmAddEdit:function(){var e=new URLSearchParams;e.append("productNo",this.newformEdit.productNo),e.append("productName",this.newformEdit.productName),e.append("cycle",this.newformEdit.cycle),e.append("yearIncomPercent",this.newformEdit.yearIncomPercent/100),e.append("minMoney",100*this.newformEdit.minMoney),e.append("calcMethod",this.newformEdit.calcMethod),e.append("redeemMethod",this.newformEdit.redeemMethod),e.append("settleMethod",this.newformEdit.settleMethod),e.append("id",this.productId),e.append("type",this.type),e.append("nper",this.newformEdit.nper);var t=this;axios.post(this.editUrl,e).then(function(e){e.data.data?(t.$message({message:"修改成功",type:"success"}),t.dialogFormVisibleEdit=!1,t.loadData(t.pageSize,1)):e.data.data?(t.dialogFormVisibleEdit=!1,t.$message.error("网络错误")):(t.$message({message:"修改失败",type:"warning"}),t.dialogFormVisibleEdit=!1)}).catch(function(e){t.dialogFormVisibleEdit=!1,t.$message.error("网络错误")})},deleteDialog:function(e,t){this.dialogFormVisibleDelete=!0,this.row=t},confirmAddEdit1:function(){var e=new URLSearchParams;e.append("productNo",this.newformEdit1.productNo),e.append("name",this.newformEdit1.productName),e.append("cycle",this.newformEdit1.cycle),e.append("yearIncomPercent",this.newformEdit1.yearIncomPercent/100),e.append("calcStartPoint",this.newformEdit1.calcStartPoint),e.append("calcMethod",this.newformEdit1.calcMethod),e.append("redeemMethod",this.newformEdit1.redeemMethod),e.append("settleMethod",this.newformEdit1.settleMethod),e.append("id",this.productId);var t=this;axios.post(this.editUrl1,e).then(function(e){e.data.data?(t.$message({message:"修改成功",type:"success"}),t.dialogFormVisibleEditGold=!1,t.loadData(t.pageSize,1),t.loadData1()):e.data.data?(t.dialogFormVisibleEditGold=!1,t.$message.error("网络错误")):(t.$message({message:"修改失败",type:"warning"}),t.dialogFormVisibleEditGold=!1)}).catch(function(e){t.dialogFormVisibleEditGold=!1,t.$message.error("网络错误")})}},i()(a,"deleteDialog",function(e,t){this.dialogFormVisibleDelete=!0,this.row=t}),i()(a,"handleDelete",function(){var e=this,t=new URLSearchParams;t.append("id",this.row.id),t.append("type",this.row.type),1==this.num?axios.post(this.deleteUrl1,t).then(function(t){t.data.data?(e.$message({message:"删除成功",type:"success"}),e.dialogFormVisibleDelete=!1,e.loadData(e.pageSize,1)):t.data.data?(e.dialogFormVisibleEdit=!1,e.$message.error("网络错误")):(e.$message({message:"删除失败",type:"warning"}),e.dialogFormVisibleEdit=!1)}).catch(function(e){}):axios.post(this.deleteUrl,t).then(function(t){t.data.data?(e.$message({message:"删除成功",type:"success"}),e.dialogFormVisibleDelete=!1,e.loadData(e.pageSize,1),e.loadData1()):t.data.data?(e.dialogFormVisibleEdit=!1,e.$message.error("网络错误")):(e.$message({message:"删除失败",type:"warning"}),e.dialogFormVisibleEdit=!1)}).catch(function(e){})}),i()(a,"loadData",function(e,t){var o=new URLSearchParams;o.append("pageSize",e),o.append("pageNum",t);var a=this;axios.post(this.url,o).then(function(e){a.currentPage=e.data.data.pageNum,a.pageSize=e.data.data.pageSize,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.tableData1=e.data.data.list}).catch(function(e){})}),i()(a,"loadData1",function(){var e=(new URLSearchParams,this);axios.post(this.url1).then(function(t){e.tableData2=t.data.data}).catch(function(e){})}),a)}},658:function(e,t,o){t=e.exports=o(135)(void 0),t.push([e.i,"",""])},718:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),o("div",{staticClass:"container-fluid"},[e._v("\n    理财产品设定\n      "),e._v(" "),o("div",{staticClass:"tableBox"},[o("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData1,border:"",stripe:""}},[o("el-table-column",{attrs:{type:"index",label:"序号",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"productNo",label:"产品编号",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"productName",label:"产品名称",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"nper",label:"期数",width:"120",formatter:e.convertNper}}),e._v(" "),o("el-table-column",{attrs:{prop:"cycle",label:"周期",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"yearIncomPercent",formatter:e.convertYearIncomPercent,label:"年化收益率",width:"120"}}),e._v(" "),o("el-table-column"),e._v(" "),o("el-table-column",{attrs:{prop:"minMoney",formatter:e.convertMinMoney,label:"起投金额",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"calcMethod",formatter:e.convertCalcMethod,label:"计息方式",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"redeemMethod",formatter:e.convertRedeemMethod,label:"赎回方式",width:"240"}}),e._v(" "),o("el-table-column",{attrs:{prop:"settleMethod",formatter:e.convertSettleMethod,label:"结算方式",width:"240"}}),e._v(" "),o("el-table-column",{attrs:{label:"操作",fixed:"right",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{size:"small"},on:{click:function(o){e.handleEdit(1,t.row)}}},[e._v("编辑")]),e._v(" "),o("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(o){e.deleteDialog(1,t.row)}}},[e._v("删除")])]}}])})],1),e._v(" "),o("el-dialog",{attrs:{title:"编辑",visible:e.dialogFormVisibleEdit},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[o("el-form",{attrs:{model:e.newformEdit}},[o("el-form-item",{attrs:{label:"产品编号：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.productNo,callback:function(t){e.$set(e.newformEdit,"productNo",t)},expression:"newformEdit.productNo"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"产品名称：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.productName,callback:function(t){e.$set(e.newformEdit,"productName",t)},expression:"newformEdit.productName"}})],1),e._v(" "),e.show?[o("el-form-item",{attrs:{label:"期数：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.nper,callback:function(t){e.$set(e.newformEdit,"nper",t)},expression:"newformEdit.nper"}})],1)]:e._e(),e._v(" "),o("el-form-item",{attrs:{label:"周期：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.cycle,callback:function(t){e.$set(e.newformEdit,"cycle",t)},expression:"newformEdit.cycle"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"年化收益率：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.yearIncomPercent,callback:function(t){e.$set(e.newformEdit,"yearIncomPercent",t)},expression:"newformEdit.yearIncomPercent"}}),e._v("%\n                  ")],1),e._v(" "),o("el-form-item",{attrs:{label:"起投金额：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.minMoney,callback:function(t){e.$set(e.newformEdit,"minMoney",t)},expression:"newformEdit.minMoney"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"计息方式：      T+","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.calcMethod,callback:function(t){e.$set(e.newformEdit,"calcMethod",t)},expression:"newformEdit.calcMethod"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"赎回方式：","label-width":e.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit.redeemMethod,callback:function(t){e.$set(e.newformEdit,"redeemMethod",t)},expression:"newformEdit.redeemMethod"}},e._l(e.redeemMethods,function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),o("el-form-item",{attrs:{label:"结算方式：","label-width":e.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit.settleMethod,callback:function(t){e.$set(e.newformEdit,"settleMethod",t)},expression:"newformEdit.settleMethod"}},e._l(e.settleMethods,function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],2),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogFormVisibleEdit=!1}}},[e._v("取 消")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:e.confirmAddEdit}},[e._v("确 定")])],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"删除",visible:e.dialogFormVisibleDelete},on:{"update:visible":function(t){e.dialogFormVisibleDelete=t}}},[o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogFormVisibleDelete=!1}}},[e._v("取 消")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:e.handleDelete}},[e._v("确 定")])],1)])],1),e._v("\n      贵金属\n      "),o("div",{staticClass:"tableBox"},[o("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData2,border:"",stripe:""}},[o("el-table-column",{attrs:{type:"index",label:"序号",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"productNo",label:"产品编号",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"name",label:"产品名称",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"cycle",label:"周期",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"yearIncomPercent",formatter:e.convertYearIncomPercent,label:"年化收益率",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"calcStartPoint",label:"计息起点",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"calcMethod",formatter:e.convertCalcMethod,label:"计息方式",width:"120"}}),e._v(" "),o("el-table-column",{attrs:{prop:"redeemMethod",formatter:e.convertRedeemMethod,label:"赎回方式",width:"240"}}),e._v(" "),o("el-table-column",{attrs:{prop:"settleMethod",formatter:e.convertSettleMethod,label:"结算方式",width:"240"}}),e._v(" "),o("el-table-column",{attrs:{label:"操作",fixed:"right",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{size:"small"},on:{click:function(o){e.handleEdit(2,t.row)}}},[e._v("编辑")]),e._v(" "),o("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(o){e.deleteDialog(2,t.row)}}},[e._v("删除")])]}}])})],1),e._v(" "),o("el-dialog",{attrs:{title:"编辑",visible:e.dialogFormVisibleEditGold},on:{"update:visible":function(t){e.dialogFormVisibleEditGold=t}}},[o("el-form",{attrs:{model:e.newformEdit1}},[o("el-form-item",{attrs:{label:"产品编号：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.productNo,callback:function(t){e.$set(e.newformEdit1,"productNo",t)},expression:"newformEdit1.productNo"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"产品名称：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.productName,callback:function(t){e.$set(e.newformEdit1,"productName",t)},expression:"newformEdit1.productName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"周期：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.cycle,callback:function(t){e.$set(e.newformEdit1,"cycle",t)},expression:"newformEdit1.cycle"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"年化收益率：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.yearIncomPercent,callback:function(t){e.$set(e.newformEdit1,"yearIncomPercent",t)},expression:"newformEdit1.yearIncomPercent"}}),e._v("%\n                  ")],1),e._v(" "),o("el-form-item",{attrs:{label:"计息起点：","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.calcStartPoint,callback:function(t){e.$set(e.newformEdit1,"calcStartPoint",t)},expression:"newformEdit1.calcStartPoint"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"计息方式：      T+","label-width":e.formLabelWidth}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit1.calcMethod,callback:function(t){e.$set(e.newformEdit1,"calcMethod",t)},expression:"newformEdit1.calcMethod"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"赎回方式：","label-width":e.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit1.redeemMethod,callback:function(t){e.$set(e.newformEdit1,"redeemMethod",t)},expression:"newformEdit1.redeemMethod"}},e._l(e.redeemMethods,function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),o("el-form-item",{attrs:{label:"结算方式：","label-width":e.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit1.settleMethod,callback:function(t){e.$set(e.newformEdit1,"settleMethod",t)},expression:"newformEdit1.settleMethod"}},e._l(e.settleMethods,function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogFormVisibleEditGold=!1}}},[e._v("取 消")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:e.confirmAddEdit1}},[e._v("确 定")])],1)],1)],1)])])},staticRenderFns:[function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"content-header"}},[o("div",{attrs:{id:"breadcrumb"}},[o("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[o("i",{staticClass:"icon-home"}),e._v(" 产品设定")]),e._v(" "),o("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("理财产品设定")])]),e._v(" "),o("h1")])}]}},767:function(e,t,o){var a=o(658);"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);o(197)("2658b792",a,!0)}});