webpackJsonp([3],{536:function(e,t,a){a(726);var r=a(196)(a(589),a(678),"data-v-1ddcd58e",null);e.exports=r.exports},589:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(604),i=a.n(r),o=a(64),n=a.n(o);t.default={data:function(){var e=function(e,t,a){if(!t)return a(new Error("输入不能为空"));setTimeout(function(){console.log(void 0===t?"undefined":n()(t)),i()(t)?t>100?a(new Error("必须小于100")):a():a(new Error("请输入数字值"))},100)};return{loading:!0,agentOptions:"",brokerOptions:"",operationOptios:"",sform:{orderNo:"",startTime:"",endTime:"",agentName:"",brokerName:"",operationName:""},newformEdit:{goldSharing:"",financeSharing:"",goldRightSharing:"",goldUpSharing:""},types:[{id:"1",name:"代理商"},{id:"",name:"经纪人"}],rules:{goldSharing:[{validator:e,trigger:"blur"}],financeSharing:[{validator:e,trigger:"blur"}],goldRightSharing:[{validator:e,trigger:"blur"}],goldUpSharing:[{validator:e,trigger:"blur"}]},url:"资讯user/sightOfElephant",agentUrl:"资讯user/selectByAgentMessage",brokeUrl:"资讯user/selectByBrokerMessage",exportUrl:"资讯user/excelSightOfElephant",editUrl:"资讯incomeShringConf/modifySharing",deleteUrl:"资讯user/deleteUser",dialogFormVisibleSharing:!1,dialogFormVisibleDelete:!1,currentPage:0,pageSize:10,pageNum:1,totalNum:0,tableData:[],formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,r="",i="",o=null;""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(i=this.dateFormat(e.endTime)),""!=e.userType&&void 0!=e.userType&&(o=e.userType),t.append("pageSize",this.pageSize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",i),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),t.append("type",o),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});e.data.data.list;a.currentPage=0==e.data.data.pageNum?1:e.data.data.pageNum,a.pageSize=e.data.data.pageSize,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.tableData=e.data.data.list}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){})},loadData:function(e,t){var a=new URLSearchParams,r="",i="",o=null;""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(i=this.dateFormat(this.sform.endTime)),""!=this.sform.type&&void 0!=this.sform.type&&(o=this.sform.userType),this.pageSize=e,this.currentPage=t,a.append("pageSize",this.pageSize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",i),a.append("type",o),a.append("operationName",this.sform.operationName),a.append("agentName",this.isNotEmpty(this.sform.agentName)?Number(sform.agentName):""),a.append("brokerName",this.isNotEmpty(this.sform.brokerName)?Number(sform.brokerName):"");var n=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;n.currentPage=0==e.data.data.pageNum?1:e.data.data.pageNum,n.pageSize=e.data.data.pageSize,n.totalNum=e.data.data.total,n.tableData=t}}).catch(function(e){})},handleDelete:function(){var e=this,t=new URLSearchParams;t.append("id",e.row.id),axios.post(this.deleteUrl,t).then(function(t){console.log(t),1==t.data.msg?(e.$message({message:"删除成功",type:"success"}),e.dialogFormVisibleDelete=!1,e.loadData(e.pageSize,e.currentPage)):0===t.data.msg?(e.$message({message:"删除失败",type:"warning"}),e.dialogFormVisibleDelete=!1):(e.dialogFormVisibleEdit=!1,e.$message.error("网络错误"))}).catch(function(e){})},deleteDialog:function(e,t){this.dialogFormVisibleDelete=!0,this.row=t},sharing:function(e,t){this.newformEdit={goldSharing:t.goldSharing,financeSharing:t.financeSharing,goldRightSharing:t.goldRightSharing,goldUpSharing:t.goldUpSharing},this.id=t.id,this.dialogFormVisibleSharing=!0},resetFormValidate:function(e){this.$refs[e].resetFields(),this.dialogFormVisibleSharing=!1},confirmSharing:function(e){var t=this,a=new URLSearchParams;a.append("realGoldPercent",this.newformEdit.goldSharing),a.append("randomPercent",this.newformEdit.financeSharing),a.append("goldRightPercent",this.newformEdit.goldRightSharing),a.append("goldPercent",this.newformEdit.goldUpSharing),a.append("agentId",this.id);var r=this;this.$refs[e].validate(function(i){i?(t.$refs[e].resetFields(),axios.post(t.editUrl,a).then(function(e){e.data.data?(r.$message({message:"修改成功",type:"success"}),r.dialogFormVisibleSharing=!1,r.loadData(10,1)):e.data.data?(r.dialogFormVisibleSharing=!1,r.$message.error("网络错误")):(r.$message({message:"修改失败",type:"warning"}),r.dialogFormVisibleSharing=!1)}).catch(function(e){r.dialogFormVisibleSharing=!1,r.$message.error("网络错误")})):console.log("error submit!!")})},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",Number(e.sform.agentName)),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},jdType:function(e){return 1==e.pid?"代理商":null!=e.pid?"经纪人":"管理员"},resetForm:function(e){this.$refs.sform.resetFields()},onExport:function(e){var t=new URLSearchParams;t.append("userName",e.userName),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pageSize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var i=t.getDate();i=i<10?"0"+i:i;var o=t.getHours();o=o<10?"0"+o:o;var n=t.getMinutes(),s=t.getSeconds();return n=n<10?"0"+n:n,s=s<10?"0"+s:s,a+"-"+r+"-"+i+" "+o+":"+n+":"+s}}}},604:function(e,t,a){e.exports={default:a(605),__esModule:!0}},605:function(e,t,a){a(607),e.exports=a(63).Number.isInteger},606:function(e,t,a){var r=a(65),i=Math.floor;e.exports=function(e){return!r(e)&&isFinite(e)&&i(e)===e}},607:function(e,t,a){var r=a(86);r(r.S,"Number",{isInteger:a(606)})},618:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,"",""])},678:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"创建时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"类型：",prop:"type"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.userType,callback:function(t){e.$set(e.sform,"userType",t)},expression:"sform.userType"}},e._l(e.types,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"商户名",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"type",formatter:e.jdType,label:"类型",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"创建时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[1==t.row.pid?[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){e.sharing(t.index,t.row)}}},[e._v("分成设置")])]:e._e(),e._v(" "),a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){e.deleteDialog(t.index,t.row)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"分成设置",visible:e.dialogFormVisibleSharing},on:{"update:visible":function(t){e.dialogFormVisibleSharing=t}}},[a("el-form",{ref:"newformEdit",attrs:{model:e.newformEdit,rules:e.rules}},[a("el-form-item",{attrs:{label:"黄金","label-width":e.formLabelWidth,prop:"goldSharing"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.goldSharing,callback:function(t){e.$set(e.newformEdit,"goldSharing",e._n(t))},expression:"newformEdit.goldSharing"}}),e._v("%\n                ")],1),e._v(" "),a("el-form-item",{attrs:{label:"理财","label-width":e.formLabelWidth,prop:"financeSharing"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.financeSharing,callback:function(t){e.$set(e.newformEdit,"financeSharing",e._n(t))},expression:"newformEdit.financeSharing"}}),e._v("%\n                ")],1),e._v(" "),a("el-form-item",{attrs:{label:"金权交易","label-width":e.formLabelWidth,prop:"goldRightSharing"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.goldRightSharing,callback:function(t){e.$set(e.newformEdit,"goldRightSharing",e._n(t))},expression:"newformEdit.goldRightSharing"}}),e._v("%\n                ")],1),e._v(" "),a("el-form-item",{attrs:{label:"黄金稳赚","label-width":e.formLabelWidth,prop:"goldUpSharing"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.goldUpSharing,callback:function(t){e.$set(e.newformEdit,"goldUpSharing",e._n(t))},expression:"newformEdit.goldUpSharing"}}),e._v("%\n                ")],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.resetFormValidate("newformEdit")}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.confirmSharing("newformEdit")}}},[e._v("确 定")])],1)],1)],1),e._v(" "),a("div",[a("el-dialog",{attrs:{title:"确认删除？",center:"",width:"300px",visible:e.dialogFormVisibleDelete},on:{"update:visible":function(t){e.dialogFormVisibleDelete=t}}},[a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:6}},[a("el-button",{on:{click:function(t){e.dialogFormVisibleDelete=!1}}},[e._v("取 消")])],1),e._v(" "),a("el-col",{attrs:{span:6,offset:6}},[a("el-button",{attrs:{type:"primary"},on:{click:e.handleDelete}},[e._v("确 定")])],1)],1)],1)])],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 合作伙伴")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("商户管理")])]),e._v(" "),a("h1")])}]}},726:function(e,t,a){var r=a(618);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("a71ce8fc",r,!0)}});