webpackJsonp([46],{510:function(e,t,a){a(753);var r=a(196)(a(567),a(701),"data-v-594ce82e",null);e.exports=r.exports},567:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(64),o=a.n(r);t.default={data:function(){return{loading:!0,actorList:"",newformEdit:{agentId:"",brokerId:""},form:{username:"",region:"",date1:"",date2:"",brokerName:"",agentsName:"",delivery:!1,type:[],resource:"",desc:""},rules:{agentId:[{required:!0,message:"请选择代理商",trigger:"blur"}],brokerId:[{required:!0,message:"请选择经纪人",trigger:"blur"}]},brokerOptions:[],agentOptions:[],agentList:[],brokeList:[],url:"userInfo/selectByAccountMessage",agentUrl:"user/selectByAgentMessage",brokerUrl:"user/selectByBrokerMessage",exportUrl:"userInfo/excelAccountMessage",countUrl:"userInfo/selectAccountCount",alertUrl:"userInfo/alertAgentAndBroker",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],formLabelWidth:"120px",userId:"",countList:"",dialogFormVisibleEdit:!1}},filters:{divide:function(e){return""!=e&&null!=e?(Number(e)/100).toFixed(2):0}},created:function(){this.loadData(10,1),this.countNum();var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)})},methods:{clos:function(e){this.dialogFormVisibleEdit=!1,this.$refs[e].resetFields()},changeBroker:function(e,t){this.newformEdit={agentId:"",brokerId:""},this.brokerOptions=[],this.getBrokerOptions1(),this.brokerOptions="",console.log(o()(t.userId)),this.userId=t.userId,console.log(this.userId),this.dialogFormVisibleEdit=!0},confirmChangeBroker:function(e){var t=this,a=this,r=new URLSearchParams;r.append("agentId",a.newformEdit.agentId),r.append("brokerId",a.newformEdit.brokerId),r.append("userId",a.userId),this.$refs[e].validate(function(e){e&&axios.post(t.alertUrl,r).then(function(e){1==e.data.data?(a.$message({message:"变更成功",type:"success"}),a.loadData(a.pagesize,a.currentPage),a.dialogFormVisibleEdit=!1):(a.$message({message:"变更失败",type:"error"}),a.dialogFormVisibleEdit=!1),this.brokerOptions=[]})})},getBrokerOptions:function(){this.newformEdit={brokerId:""};var e=this;e.form.brokerName="";var t=new URLSearchParams;t.append("pid",e.form.agentName),axios.post(e.brokerUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},getBrokerOptions1:function(){var e=this;e.newformEdit.brokerName="";var t=new URLSearchParams;t.append("pid",e.newformEdit.agentId),axios.post(e.brokerUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},countNum:function(){var e=new URLSearchParams,t=this,a="",r="",o="",i="",s="";void 0!=this.form.username&&(o=this.form.username),""!=this.form.date1&&(a=this.dateFormat(this.form.date1)),""!=this.form.date2&&(r=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(i=this.form.brokerName),void 0!=this.form.agentName&&(s=this.form.agentName),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("userName",o),e.append("startTime",a),e.append("endTime",r),e.append("brokerName",i),e.append("agentsName",s),axios.get(this.countUrl+"?"+e).then(function(e){t.countList=e.data.data}).catch(function(e){console.log(e)})},onSubmit:function(e){this.countNum();var t=new URLSearchParams,a=this,r="",o="",i="",s="",n="";void 0!=this.form.username&&(i=this.form.username),""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(s=this.form.brokerName),void 0!=this.form.agentName&&(n=this.form.agentName),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("userName",i),t.append("startTime",r),t.append("endTime",o),t.append("brokerName",s),t.append("agentsName",n),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){})},resetForm:function(){this.$refs.form.resetFields(),this.loadData(10,1),this.countNum()},exportFun:function(){var e=new URLSearchParams,t="",a="",r="",o="",i="";void 0!=this.form.username&&(r=this.form.username),""!=this.form.date1&&(t=this.dateFormat(this.form.date1)),""!=this.form.date2&&(a=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(o=this.form.brokerName),void 0!=this.form.agentName&&(i=this.form.agentName),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("userName",r),e.append("startTime",t),e.append("endTime",a),e.append("brokerName",o),e.append("agentsName",i),window.location=this.exportUrl+"?"+e},loadData:function(e,t){var a=new URLSearchParams,r="",o="",i="",s="",n="";i=void 0==this.form.username?"":this.form.username,s=void 0==this.form.brokerName?"":this.form.brokerName,n=void 0==this.form.agentsName?"":this.form.agentsName,""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("userName",i),a.append("brokerName",s),a.append("agentsName",n),a.append("startTime",r),a.append("endTime",o);var l=this;axios.post(this.url,a).then(function(e){l.totalNum=e.data.data.total,l.pageNum=e.data.data.pages,l.tableData=e.data.data.list}).catch(function(e){})},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},638:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-594ce82e]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-594ce82e]{border-radius:4px}.bg-purple-dark[data-v-594ce82e]{background:#99a9bf}.bg-color1[data-v-594ce82e]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-594ce82e]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-594ce82e]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-594ce82e]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-594ce82e]{background:#d3dce6}.bg-purple-light[data-v-594ce82e]{background:#e5e9f2}.gridBox[data-v-594ce82e]{padding-left:20px}.grid-content[data-v-594ce82e]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-594ce82e]{padding:10px 0;background-color:#f9fafc}",""])},701:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"formBox"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"用户账号",prop:"username"}},[a("el-input",{attrs:{size:"small"},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商",prop:"agentName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.form.agentName,callback:function(t){e.$set(e.form,"agentName",t)},expression:"form.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人",prop:"brokerName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:e.form.brokerName,callback:function(t){e.$set(e.form,"brokerName",t)},expression:"form.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("br"),e._v(" "),a("el-form-item",{attrs:{label:"注册时间",prop:"date1"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{prop:"date2"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){e.onSubmit(e.form)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(t){e.resetForm(e.form)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"info"},on:{click:e.exportFun}},[e._v("导出")])],1)],1)],1),e._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color1"},[a("p",[e._v("人民币余额总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.rmbSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[e._v("人民币冻结总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.frozenRmbSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color3"},[a("p",[e._v("人民币理财总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.finaceSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color4"},[a("p",[e._v("黄金（克）")]),e._v(" "),a("p",[e._v(e._s(e.countList.goldSum))])])])],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{attrs:{data:e.tableData,fit:"",border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"100",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"RealName",label:"姓名",fixed:"left",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"registerTime",label:"注册时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"idcard",label:"身份证号码",width:"200"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"银行卡",width:"200"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rmb ",label:"人民币余额",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.rmb))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"人民币冻结",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.frozenRmb))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"人民币理财",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.finance))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"利息",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.totalIncome))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"gold",label:"黄金",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",fixed:"right",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(a){e.changeBroker(t.index,t.row)}}},[e._v("变更")])]}}])})],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"变更",visible:e.dialogFormVisibleEdit,width:"30%",center:""},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[a("el-form",{ref:"newformEdit",attrs:{model:e.newformEdit,rules:e.rules}},[a("el-form-item",{attrs:{label:"代理商","label-width":e.formLabelWidth,prop:"agentId"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions1()}},model:{value:e.newformEdit.agentId,callback:function(t){e.$set(e.newformEdit,"agentId",t)},expression:"newformEdit.agentId"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人","label-width":e.formLabelWidth,prop:"brokerId"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:e.newformEdit.brokerId,callback:function(t){e.$set(e.newformEdit,"brokerId",t)},expression:"newformEdit.brokerId"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(t){e.clos("newformEdit")}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){e.confirmChangeBroker("newformEdit")}}},[e._v("确 定")])],1)],1),e._v(" "),a("br"),a("br"),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 客户管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("账户信息")])])])}]}},753:function(e,t,a){var r=a(638);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("489cba90",r,!0)}});