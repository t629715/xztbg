webpackJsonp([19],{543:function(e,t,a){a(740);var i=a(196)(a(598),a(690),"data-v-3c2019f6",null);e.exports=i.exports},598:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,actorList:"",form:{username:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""},newformEdit:{name:"",buyPoundage:"",insurance:"",logisticsFee:"",maxBuyCount:"",minBuyCount:"",sellPoundage:""},url:"realGoldConf/getRealGoldConf",editUrl:"realGoldConf/editRealGoldConf",tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){var e=this;axios.post(this.url).then(function(t){e.tableData=t.data.data}).catch(function(e){})},methods:{convertsellPoundage:function(e){var t=new Decimal(e.sellPoundage),a=new Decimal(100);return t.times(a)+"%"},convertlogisticsFee:function(e){return e.logisticsFee/100},convertinsurance:function(e){return e.insurance/100},convertbuyPoundage:function(e){return new Decimal(e.buyPoundage).times(new Decimal(100))+"%"},handleEdit:function(e,t){var a=new Decimal(t.buyPoundage).times(new Decimal(100)).toString(),i=new Decimal(t.sellPoundage).times(new Decimal(100)).toString();this.newformEdit={name:t.name,buyPoundage:a,insurance:t.insurance/100,logisticsFee:t.logisticsFee/100,maxBuyCount:t.maxBuyCount,minBuyCount:t.minBuyCount,sellPoundage:i},this.sid=t.s,this.id=t.id,this.dialogFormVisibleEdit=!0},confirmAddEdit:function(){var e=new URLSearchParams;e.append("name",this.newformEdit.name),e.append("buyPoundage",this.newformEdit.buyPoundage/100),e.append("insurance",100*this.newformEdit.insurance),e.append("logisticsFee",100*this.newformEdit.logisticsFee),e.append("maxBuyCount",this.newformEdit.maxBuyCount),e.append("minBuyCount",this.newformEdit.minBuyCount),e.append("sellPoundage",this.newformEdit.sellPoundage/100),e.append("sid",this.sid),e.append("id",this.id);var t=this;axios.post(this.editUrl,e).then(function(e){1==e.data.data?(t.$message({message:"修改成功",type:"success"}),t.loadData(),t.dialogFormVisibleEdit=!1):0==e.data.data?(t.$message({message:"修改失败",type:"warning"}),t.dialogFormVisibleEdit=!1):(t.dialogFormVisibleEdit=!1,t.$message.error("网络错误"))}).catch(function(e){t.dialogFormVisibleEdit=!1,t.$message.error("网络错误")})},loadData:function(){var e=this;axios.post(this.url).then(function(t){e.tableData=t.data.data}).catch(function(e){})}}}},629:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,"",""])},690:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("h2",[e._v("实金买卖设定")]),e._v(" "),a("el-table",{staticStyle:{width:"auto",display:"inline-block"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{prop:"name",label:"产品名称",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"buyPoundage",formatter:e.convertbuyPoundage,label:"买入手续费（每克）",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"maxBuyCount",label:"买入上限",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"minBuyCount",label:"买入下限",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"sellPoundage",formatter:e.convertsellPoundage,label:"卖出手续费",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"insurance",formatter:e.convertinsurance,label:"保险费（每克）",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"logisticsFee",formatter:e.convertlogisticsFee,label:"物流费（每笔）",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")])]}}])})],1),e._v(" "),a("el-dialog",{attrs:{title:"编辑",visible:e.dialogFormVisibleEdit},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[a("el-form",{attrs:{model:e.newformEdit}},[a("el-form-item",{attrs:{label:"产品名称","label-width":e.formLabelWidth}},[a("el-input",{attrs:{disabled:!0,"auto-complete":"off"},model:{value:e.newformEdit.name,callback:function(t){e.$set(e.newformEdit,"name",t)},expression:"newformEdit.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"买入手续费","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.buyPoundage,callback:function(t){e.$set(e.newformEdit,"buyPoundage",t)},expression:"newformEdit.buyPoundage"}}),e._v("%\n              ")],1),e._v(" "),a("el-form-item",{attrs:{label:"买入上限","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.maxBuyCount,callback:function(t){e.$set(e.newformEdit,"maxBuyCount",t)},expression:"newformEdit.maxBuyCount"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"买入下限","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.minBuyCount,callback:function(t){e.$set(e.newformEdit,"minBuyCount",t)},expression:"newformEdit.minBuyCount"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"卖出手续费","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.sellPoundage,callback:function(t){e.$set(e.newformEdit,"sellPoundage",t)},expression:"newformEdit.sellPoundage"}}),e._v("%\n              ")],1),e._v(" "),a("el-form-item",{attrs:{label:"保险费","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.insurance,callback:function(t){e.$set(e.newformEdit,"insurance",t)},expression:"newformEdit.insurance"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"物流费","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.logisticsFee,callback:function(t){e.$set(e.newformEdit,"logisticsFee",t)},expression:"newformEdit.logisticsFee"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisibleEdit=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.confirmAddEdit}},[e._v("确 定")])],1)],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 产品设定")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("实金买卖设定")])])])}]}},740:function(e,t,a){var i=a(629);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a(197)("cba31896",i,!0)}});