webpackJsonp([43],{526:function(t,e,a){a(803);var o=a(198)(a(588),a(746),"data-v-b7387abe",null);t.exports=o.exports},588:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{loading:!0,statusOptions:[{value:"",label:"全部"},{value:"0",label:"审核中"},{value:"1",label:"审核通过"},{value:"2",label:"审核未通过"}],agentOptions:"",brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",status:"",agentName:"",brokerName:""},url:"userWithdrawCash/selectByWithdrawCash",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",brokerUrl1:"user/selectByBrokerMessage1",exportUrl:"userWithdrawCash/excelWithdrawCash",countUrl:"userWithdrawCash/selectByWithdrawCashCount",editUrl:"userWithdrawCash/auditPassedById",editUrl2:"userWithdrawCash/auditNoPassedById",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,dialogFormVisibleEdit2:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var t=this;axios.get(this.agentUrl).then(function(e){t.agentOptions=e.data.data}).catch(function(t){console.log(t)}),axios.get(this.brokerUrl1).then(function(e){t.brokerOptions=e.data.data}).catch(function(t){console.log(t)}),axios.post(this.countUrl).then(function(e){1001==e.data.code&&(document.getElementById("tqjeId").innerText=t.amountHandle1(e.data.data.withdrawAmtSum),document.getElementById("sxfId").innerText=t.amountHandle1(e.data.data.poundageSum))}).catch(function(t){console.log(t)})},methods:{onSubmit:function(t){var e=new URLSearchParams,a=this,o="",s="";""!=t.startTime&&(o=this.dateFormat(t.startTime)),""!=t.endTime&&(s=this.dateFormat(t.endTime)),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("startTime",o),e.append("endTime",s),e.append("userName",t.userName),e.append("status",this.isNotEmpty(t.status)?Number(t.status):""),e.append("agentName",t.agentName),e.append("brokerName",t.brokerName),axios.post(this.url,e).then(function(t){if(1001==t.data.code){a.$message({message:"查询成功",type:"success"});var e=t.data.data.list;a.currentPage=1,a.pageNum=t.data.data.pages,a.totalNum=t.data.data.total,a.handelData(e),a.tableData=e}else a.$message({message:"查询失败",type:"warning"})}).catch(function(t){}),axios.post(this.countUrl,e).then(function(t){1001==t.data.code&&(document.getElementById("tqjeId").innerText=a.amountHandle1(t.data.data.withdrawAmtSum),document.getElementById("sxfId").innerText=a.amountHandle1(t.data.data.poundageSum))}).catch(function(t){console.log(t)})},loadData:function(t,e){var a=new URLSearchParams,o="",s="";""!=this.sform.startTime&&(o=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(s=this.dateFormat(this.sform.endTime)),this.pagesize=t,this.currentPage=e,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",o),a.append("endTime",s),a.append("userName",this.sform.userName),a.append("status",this.isNotEmpty(this.sform.status)?Number(this.sform.status):""),a.append("agentName",this.sform.agentName),a.append("brokerName",this.sform.brokerName);var r=this;axios.post(this.url,a).then(function(t){if(1001==t.data.code){var e=t.data.data.list;r.handelData(e),r.pageNum=t.data.data.pages,r.totalNum=t.data.data.total,r.tableData=e}}).catch(function(t){})},handelData:function(t){if(t.length>0)for(var e=0;e<t.length;e++)t[e].withdrawAmt=this.amountHandle1(t[e].withdrawAmt),t[e].poundage=this.amountHandle1(t[e].poundage),"0"==t[e].status?t[e].status="审核中":"1"==t[e].status?t[e].status="审核通过":"2"==t[e].status&&(t[e].status="审核未通过"),"1"==t[e].type?t[e].type="银行卡":"2"==t[e].type&&(t[e].type="支付宝")},getBrokerOptions:function(){var t=this;t.sform.brokerName="";var e=new URLSearchParams;e.append("pid",t.sform.agentName),axios.post(t.brokeUrl,e).then(function(e){t.brokerOptions=e.data.data}).catch(function(t){console.log(t)})},resetForm:function(t){this.$refs.sform.resetFields();var e=this;axios.post(this.countUrl).then(function(t){1001==t.data.code&&(document.getElementById("tqjeId").innerText=e.amountHandle1(t.data.data.withdrawAmtSum),document.getElementById("sxfId").innerText=e.amountHandle1(t.data.data.poundageSum))}).catch(function(t){console.log(t)}),this.loadData(10,1)},onExport:function(t){var e=new URLSearchParams,a="",o="";""!=t.startTime&&(a=this.dateFormat(t.startTime)),""!=t.endTime&&(o=this.dateFormat(t.endTime)),e.append("startTime",a),e.append("endTime",o),e.append("userName",t.userName),e.append("status",this.isNotEmpty(t.status)?Number(t.status):""),e.append("agentName",t.agentName),e.append("brokerName",t.brokerName),console.info(this.exportUrl+"?"+e),window.location=this.exportUrl+"?"+e},editDialog:function(t,e){this.dialogFormVisibleEdit=!0,this.row=e},handleEdit:function(t,e){var a=this,o=new URLSearchParams;o.append("withdrawid",a.row.withdrawID),o.append("userId",a.row.userID),axios.post(this.editUrl,o).then(function(t){1e3==t.data.code?(a.$message({message:"操作成功",type:"success"}),a.dialogFormVisibleEdit=!1,a.loadData(a.pagesize,1)):(a.$message({message:"操作失败",type:"warning"}),a.dialogFormVisibleEdit=!1)}).catch(function(t){})},editNoDialog:function(t,e){this.dialogFormVisibleEdit2=!0,this.row=e},handleEdit2:function(t,e){var a=this,o=new URLSearchParams;o.append("withdrawid",a.row.withdrawID),o.append("userId",a.row.userID),axios.post(this.editUrl2,o).then(function(t){1e3==t.data.code?(a.$message({message:"操作成功",type:"success"}),a.dialogFormVisibleEdit2=!1,a.loadData(a.pagesize,1)):(a.$message({message:"操作失败",type:"warning"}),a.dialogFormVisibleEdit2=!1)}).catch(function(t){})},handleCurrentChange:function(t){this.loadData(this.pagesize,t)},handleSizeChange:function(t){this.loadData(t,1)},dateFormat:function(t){var e=new Date(t),a=e.getFullYear(),o=e.getMonth()+1;o=o<10?"0"+o:o;var s=e.getDate();s=s<10?"0"+s:s;var r=e.getHours();r=r<10?"0"+r:r;var i=e.getMinutes(),n=e.getSeconds();return i=i<10?"0"+i:i,n=n<10?"0"+n:n,a+"-"+o+"-"+s+" "+r+":"+i+":"+n}}}},678:function(t,e,a){e=t.exports=a(136)(void 0),e.push([t.i,".el-row[data-v-b7387abe]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-b7387abe]{border-radius:4px}.bg-purple-dark[data-v-b7387abe]{background:#99a9bf}.bg-color1[data-v-b7387abe]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-b7387abe]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-b7387abe]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-b7387abe]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-b7387abe]{background:#d3dce6}.bg-purple-light[data-v-b7387abe]{background:#e5e9f2}.gridBox[data-v-b7387abe]{padding-left:20px}.grid-content[data-v-b7387abe]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-b7387abe]{padding:10px 0;background-color:#f9fafc}",""])},746:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content"}},[t._m(0,!1,!1),t._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:t.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:t.sform.userName,callback:function(e){t.$set(t.sform,"userName",e)},expression:"sform.userName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"提取时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:t.sform.startTime,callback:function(e){t.$set(t.sform,"startTime",e)},expression:"sform.startTime"}})],1),t._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[t._v("----")])],1),t._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:t.sform.endTime,callback:function(e){t.$set(t.sform,"endTime",e)},expression:"sform.endTime"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"状态：",prop:"status"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.sform.status,callback:function(e){t.$set(t.sform,"status",e)},expression:"sform.status"}},t._l(t.statusOptions,function(t){return a("el-option",{key:t.id,attrs:{label:t.label,value:t.value}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(e){t.getBrokerOptions()}},model:{value:t.sform.agentName,callback:function(e){t.$set(t.sform,"agentName",e)},expression:"sform.agentName"}},t._l(t.agentOptions,function(t){return a("el-option",{key:t.id,attrs:{label:t.agentName,value:t.id}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.sform.brokerName,callback:function(e){t.$set(t.sform,"brokerName",e)},expression:"sform.brokerName"}},t._l(t.brokerOptions,function(t){return a("el-option",{key:t.id,attrs:{label:t.brokerName,value:t.id}})}))],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.onSubmit(t.sform)}}},[t._v("查询")])],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(e){t.resetForm(t.sform)}}},[t._v("清除")])],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(e){t.onExport(t.sform)}}},[t._v("导出")])],1)],1),t._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color1"},[a("p",[t._v("提取总金额（元）")]),t._v(" "),a("p",{attrs:{id:"tqjeId"}})])]),t._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[t._v("手续费（元）")]),t._v(" "),a("p",{attrs:{id:"sxfId"}})])])],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"80",fixed:"left"}}),t._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"150",fixed:"left"}}),t._v(" "),a("el-table-column",{attrs:{prop:"realName",label:"姓名",width:"150",fixed:"left"}}),t._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"150"}}),t._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"150"}}),t._v(" "),a("el-table-column",{attrs:{prop:"withdrawAmt",label:"提取金额",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"poundage",label:"手续费",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"type",label:"提现类型",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"bankName",label:"所属银行",width:"200"}}),t._v(" "),a("el-table-column",{attrs:{prop:"accountNum",label:"提现账号",width:"200"}}),t._v(" "),a("el-table-column",{attrs:{prop:"withdrawTime",label:"申请时间",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"finishTime",label:"审核时间",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"status",label:"状态",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",fixed:"right",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return["审核中"==e.row.status?[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.editDialog(e.$index,e.row)}}},[t._v("审核通过")]),t._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){t.editNoDialog(e.$index,e.row)}}},[t._v("审核不通过")])]:t._e()]}}])})],1)],1),t._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-sizes":[10,20,30,40],"page-size":t.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:t.totalNum},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),t._v(" "),a("el-dialog",{attrs:{title:"提示",width:"30%",visible:t.dialogFormVisibleEdit},on:{"update:visible":function(e){t.dialogFormVisibleEdit=e}}},[a("span",[t._v("确定执行此操作？")]),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleEdit=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.handleEdit}},[t._v("确 定")])],1)]),t._v(" "),a("el-dialog",{attrs:{title:"提示",width:"30%",visible:t.dialogFormVisibleEdit2},on:{"update:visible":function(e){t.dialogFormVisibleEdit2=e}}},[a("span",[t._v("确定执行此操作？")]),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleEdit2=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.handleEdit2}},[t._v("确 定")])],1)])],1)},staticRenderFns:[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),t._v(" 出入金管理")]),t._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[t._v("现金提取")])]),t._v(" "),a("h1")])}]}},803:function(t,e,a){var o=a(678);"string"==typeof o&&(o=[[t.i,o,""]]),o.locals&&(t.exports=o.locals);a(199)("18ce9b9d",o,!0)}});