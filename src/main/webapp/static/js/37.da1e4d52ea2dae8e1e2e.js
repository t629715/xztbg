webpackJsonp([37],{516:function(e,t,a){a(715);var r=a(196)(a(569),a(667),"data-v-1367d12b",null);e.exports=r.exports},569:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,rechargechannelOptions:[{value:"01",label:"银联"},{value:"02",label:"微信"},{value:"03",label:"支付宝"}],agentOptions:"",brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",rechargechannel:"",agentName:"",brokerName:""},url:"userRecharge/selectByUserRecharge",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",exportUrl:"userRecharge/excelUserRecharge",countUrl:"userRecharge/selectByUserRechargeCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)}),axios.post(this.countUrl).then(function(t){1001==t.data.code&&(document.getElementById("czjeId").innerText=e.amountHandle1(t.data.data.rmbAmtSum))}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,r="",n="";""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(n=this.dateFormat(e.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",n),t.append("userName",e.userName),t.append("rechargechannel",e.rechargechannel),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(this.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("czjeId").innerText=a.amountHandle1(e.data.data.rmbAmtSum))}).catch(function(e){console.log(e)})},loadData:function(e,t){var a=new URLSearchParams,r="",n="";""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(n=this.dateFormat(this.sform.endTime)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",n),a.append("userName",this.sform.userName),a.append("rechargechannel",this.sform.rechargechannel),a.append("agentName",this.isNotEmpty(this.sform.agentName)?Number(this.sform.agentName):""),a.append("brokerName",this.isNotEmpty(this.sform.brokerName)?Number(this.sform.brokerName):"");var o=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;o.handelData(t),o.pageNum=e.data.data.pages,o.totalNum=e.data.data.total,o.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].RMBAmt=this.amountHandle1(e[t].RMBAmt),"01"==e[t].RechargeChannel?e[t].RechargeChannel="银联":"02"==e[t].RechargeChannel?e[t].RechargeChannel="微信":"03"==e[t].RechargeChannel&&(e[t].RechargeChannel="支付宝")},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",Number(e.sform.agentName)),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.$refs.sform.resetFields()},onExport:function(e){var t=new URLSearchParams,a="",r="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(r=this.dateFormat(e.endTime)),t.append("startTime",a),t.append("endTime",r),t.append("userName",e.userName),t.append("rechargechannel",e.rechargechannel),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var n=t.getDate();n=n<10?"0"+n:n;var o=t.getHours();o=o<10?"0"+o:o;var s=t.getMinutes(),i=t.getSeconds();return s=s<10?"0"+s:s,i=i<10?"0"+i:i,a+"-"+r+"-"+n+" "+o+":"+s+":"+i}}}},608:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-1367d12b]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-1367d12b]{border-radius:4px}.bg-purple-dark[data-v-1367d12b]{background:#99a9bf}.bg-purple[data-v-1367d12b]{background:#d3dce6}.bg-purple-light[data-v-1367d12b]{background:#e5e9f2}.grid-content[data-v-1367d12b]{border-radius:4px;min-height:36px}.row-bg[data-v-1367d12b]{padding:10px 0;background-color:#f9fafc}",""])},667:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"充值时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"充值渠道：",prop:"rechargechannel"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.rechargechannel,callback:function(t){e.$set(e.sform,"rechargechannel",t)},expression:"sform.rechargechannel"}},e._l(e.rechargechannelOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-row",{attrs:{gutter:21}},[a("el-col",{attrs:{span:7}},[a("div",{staticClass:"grid-content bg-purple"},[e._v("充值总金额（元）"),a("br"),a("span",{attrs:{id:"czjeId"}})])])],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"UserName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"RMBAmt",label:"充值金额",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"RechargeChannel",label:"充值渠道",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"RechargeTime",label:"充值时间",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 出入金管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("现金充值")])]),e._v(" "),a("h1")])}]}},715:function(e,t,a){var r=a(608);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("470b1d36",r,!0)}});