webpackJsonp([35],{518:function(e,t,a){a(711);var r=a(196)(a(571),a(663),"data-v-04b20515",null);e.exports=r.exports},571:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,agentOptions:"",brokerOptions:"",sform:{userName:"",agentName:"",brokerName:""},url:"inOutGold/selectByInOutGold",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",exportUrl:"inOutGold/excelInOutGold",currentPage:0,pageSize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this;t.append("userName",e.userName),t.append("pageSize",this.pageSize),t.append("pageNum",this.currentPage),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"}),a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total;var t=e.data.data.list;a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){})},loadData:function(e,t){var a=new URLSearchParams;this.pageSize=e,this.currentPage=t,a.append("pageSize",this.pageSize),a.append("pageNum",this.currentPage),a.append("agentName",this.isNotEmpty(this.sform.agentName)?Number(this.sform.agentName):""),a.append("brokerName",this.isNotEmpty(this.sform.brokerName)?Number(this.sform.brokerName):"");var r=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;r.handelData(t),r.pageNum=e.data.data.pages,r.totalNum=e.data.data.total,r.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].cbf=this.amountHandle1(e[t].cbf),e[t].cj=this.amountHandle1(e[t].cj),e[t].rj=this.amountHandle1(e[t].rj),e[t].sjAmount=this.amountHandle1(e[t].sjAmount),e[t].totalIncome=this.amountHandle1(e[t].totalIncome),e[t].finance=this.amountHandle1(e[t].finance)},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",Number(e.sform.agentName)),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.$refs.sform.resetFields()},onExport:function(e){var t=new URLSearchParams;t.append("userName",e.userName),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pageSize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},604:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,"",""])},663:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"realName",label:"姓名",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"registerTime",label:"注册时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rj",label:"入金",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"cj",label:"出金",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"cbf",label:"成本",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"totalIncome",label:"利息",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"finance",label:"理财",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"sjAmount",label:"买入黄金资产",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 出入金管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("出入金查询")])]),e._v(" "),a("h1")])}]}},711:function(e,t,a){var r=a(604);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("45d6eda9",r,!0)}});