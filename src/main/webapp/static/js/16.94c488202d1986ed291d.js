webpackJsonp([16],{538:function(e,t,a){a(736);var r=a(196)(a(591),a(688),"data-v-5412a6aa",null);e.exports=r.exports},591:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(64),o=a.n(r);t.default={data:function(){return{loading:!0,actorList:"",form:{username:"",region:"",date1:"",date2:"",brokerName:"",agentsName:"",delivery:!1,type:[],resource:"",desc:""},newformEdit:{brokerId:""},brokeList:[],agentOptions:[],url:"userInfo/selectSubClients",agentUrl:"user/selectByAgentMessage",brokerUrl:"user/selectByBrokerMessage",exportUrl:"userInfo/excelSubClients",countUrl:"userInfo/selectSubClientsCount",changeUrl:"userInfo/cheageBroker",dialogFormVisibleEdit:!1,currentPage:0,pageSize:10,pageNum:1,totalNum:0,tableData:[],formLabelWidth:"120px",userId:"",countList:""}},created:function(){this.loadData(10,1),this.countNum();this.agentChange()},methods:{agentChange:function(){var e=this;axios.get(this.brokerUrl,{params:{pid:""}}).then(function(t){e.brokeList=t.data.data}).catch(function(e){console.log(e)})},countNum:function(){var e=new URLSearchParams,t=this,a="",r="",o="",i="",s="";void 0!=this.form.username&&(o=this.form.username),""!=this.form.date1&&(a=this.dateFormat(this.form.date1)),""!=this.form.date2&&(r=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(i=this.form.brokerName),console.log(this.form.agentName),void 0!=this.form.agentName&&(s=this.form.agentName),e.append("pageSize",this.pageSize),e.append("pageNum",this.currentPage),e.append("userName",o),e.append("startTime",a),e.append("endTime",r),e.append("brokerName",i),e.append("agentsName",s),axios.get(this.countUrl+"?"+e).then(function(e){console.log(e.data.data),t.countList=e.data.data}).catch(function(e){console.log(e)})},onSubmit:function(e){this.countNum();var t=new URLSearchParams,a=this,r="",o="",i="",s="",n="";void 0!=this.form.username&&(i=this.form.username),""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(s=this.form.brokerName),console.log(this.form.agentName),void 0!=this.form.agentName&&(n=this.form.agentName),t.append("pageSize",this.pageSize),t.append("pageNum",this.currentPage),t.append("userName",i),t.append("startTime",r),t.append("endTime",o),t.append("brokerName",s),t.append("agentsName",n),axios.post(this.url,t).then(function(e){a.$message({message:"查询成功",type:"success"}),a.currentPage=1,a.pageNum=e.data.pages,a.totalNum=e.data.total,a.tableData=e.data.data.list}).catch(function(e){})},changeBroker:function(e,t){this.newformEdit={brokerId:t.brokerId},console.log(o()(t.userId)),this.userId=t.userId,console.log(this.userId),this.dialogFormVisibleEdit=!0},confirmChangeBroker:function(){var e=this,t=new URLSearchParams;t.append("brokerId",e.newformEdit.brokerId),t.append("userId",e.userId),axios.post(this.changeUrl,t).then(function(t){1==t.data.data?(e.$message({message:"变更成功",type:"success"}),e.loadData(e.pageSize,e.currentPage),e.dialogFormVisibleEdit=!1):(e.$message({message:"变更失败",type:"error"}),e.dialogFormVisibleEdit=!1)})},resetForm:function(){this.$refs.form.resetFields(),this.loadData(10,1),this.countNum()},exportFun:function(){var e=new URLSearchParams,t="",a="",r="",o="",i="";void 0!=this.form.username&&(r=this.form.username),""!=this.form.date1&&(t=this.dateFormat(this.form.date1)),""!=this.form.date2&&(a=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(o=this.form.brokerName),void 0!=this.form.agentName&&(i=this.form.agentName),e.append("pageSize",this.pageSize),e.append("pageNum",this.currentPage),e.append("userName",r),e.append("startTime",t),e.append("endTime",a),e.append("brokerName",o),e.append("agentsName",i),console.log(this.exportUrl+"?"+e),window.location=this.exportUrl+"?"+e},loadData:function(e,t){var a=new URLSearchParams,r="",o="",i="",s="",n="";i=void 0==this.form.username?"":this.form.username,s=void 0==this.form.brokerName?"":this.form.brokerName,n=void 0==this.form.agentsName?"":this.form.agentsName,""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),this.pageSize=e,this.currentPage=t,a.append("pageSize",this.pageSize),a.append("pageNum",this.currentPage),a.append("userName",i),a.append("brokerName",s),a.append("agentsName",n),a.append("startTime",r),a.append("endTime",o);var l=this;axios.post(this.url,a).then(function(e){l.totalNum=e.data.data.total,l.pageNum=e.data.data.pages,l.tableData=e.data.data.list}).catch(function(e){})},handleCurrentChange:function(e){this.loadData(this.pageSize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},629:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-5412a6aa]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-5412a6aa]{border-radius:4px}.bg-purple-dark[data-v-5412a6aa]{background:#99a9bf}.bg-purple1[data-v-5412a6aa]{background:#f2dede}.bg-purple2[data-v-5412a6aa]{background:#dff0d8}.bg-purple3[data-v-5412a6aa]{background:#d9edf7}.bg-purple4[data-v-5412a6aa]{background:#fcf8e3}.bg-purple5[data-v-5412a6aa]{background:#d3dce6}.bg-purple-light[data-v-5412a6aa]{background:#e5e9f2}.gridBox[data-v-5412a6aa]{padding-left:20px}.grid-content[data-v-5412a6aa]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.grid-content p[data-v-5412a6aa]:first-child{line-height:40px}.grid-content p[data-v-5412a6aa]:last-child{line-height:0}.row-bg[data-v-5412a6aa]{padding:10px 0;background-color:#f9fafc}",""])},688:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"formBox"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"用户账号",prop:"username"}},[a("el-input",{model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人",prop:"brokerName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.brokerName,callback:function(t){e.$set(e.form,"brokerName",t)},expression:"form.brokerName"}},e._l(e.brokeList,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.form)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.form)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.exportFun}},[e._v("导出")])],1)],1)],1),e._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-purple1"},[a("p",[e._v("人民币余额总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e.countList.rmbSum))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-purple2"},[a("p",[e._v("人民币冻结总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e.countList.frozenRmbSum))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-purple3"},[a("p",[e._v("人民币理财总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e.countList.finaceSum))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-purple4"},[a("p",[e._v("黄金（克）")]),e._v(" "),a("p",[e._v(e._s(e.countList.goldSum))])])])],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{attrs:{data:e.tableData,fit:"",border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"80"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rmb",label:"人民币余额",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"frozenRmb",label:"人民币冻结",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"finance",label:"人民币理财",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gold",label:"黄金",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",fixed:"right",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){e.changeBroker(t.index,t.row)}}},[e._v("变更")])]}}])})],1)],1),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"变更",visible:e.dialogFormVisibleEdit},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[a("el-form",{attrs:{model:e.newformEdit}},[a("el-form-item",{attrs:{label:"经纪人",prop:"brokerName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit.brokerId,callback:function(t){e.$set(e.newformEdit,"brokerId",t)},expression:"newformEdit.brokerId"}},e._l(e.brokeList,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisibleEdit=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.confirmChangeBroker}},[e._v("确 定")])],1)],1)],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 合作伙伴")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("下级客户")])])])}]}},736:function(e,t,a){var r=a(629);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("506147c4",r,!0)}});