webpackJsonp([8],{549:function(e,t,a){a(745);var r=a(196)(a(602),a(697),"data-v-6d4b89c1",null);e.exports=r.exports},602:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,agentOptions:"",brokerOptions:"",sform:{userName:"",orderNo:"",startTime:"",endTime:"",regStartTime:"",regEndTime:"",agentName:"",brokerName:""},url:"realGoldOrder/selectByRealGoldOrder",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",exportUrl:"realGoldOrder/excelRealGoldOrder",countUrl:"realGoldOrder/selectByRealGoldCount",currentPage:0,pageSize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)}),axios.get(this.countUrl).then(function(t){1001==t.data.code&&(alert(t.data.data.gramSum),document.getElementById("hjmrId").innerText=t.data.data.gramSum,document.getElementById("sxfId").innerText=e.amountHandle1(t.data.data.feeSum))}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,r="",o="",n="",s="";""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(o=this.dateFormat(e.endTime)),""!=e.regStartTime&&(n=this.dateFormat(e.regStartTime)),""!=e.regEndTime&&(s=this.dateFormat(e.regEndTime)),t.append("pageSize",this.pageSize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",o),t.append("regStartTime",n),t.append("regEndTime",s),t.append("userName",e.userName),t.append("orderNo",e.orderNo),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"}),a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total;var t=e.data.data.list;a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(this.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("hjmrId").innerText=e.data.data.gramSum,document.getElementById("sxfId").innerText=a.amountHandle1(e.data.data.feeSum))}).catch(function(e){console.log(e)})},loadData:function(e,t){var a=new URLSearchParams,r="",o="",n="",s="";""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(o=this.dateFormat(this.sform.endTime)),""!=this.sform.regStartTime&&(n=this.dateFormat(this.sform.regStartTime)),""!=this.sform.regEndTime&&(s=this.dateFormat(this.sform.regEndTime)),this.pageSize=e,this.currentPage=t,a.append("pageSize",this.pageSize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",o),a.append("regStartTime",n),a.append("regEndTime",s),a.append("userName",this.sform.userName),a.append("orderNo",this.sform.orderNo),a.append("agentName",this.isNotEmpty(this.sform.agentName)?Number(this.sform.agentName):""),a.append("brokerName",this.isNotEmpty(this.sform.brokerName)?Number(this.sform.brokerName):"");var i=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;i.handelData(t),i.pageNum=e.data.data.pages,i.totalNum=e.data.data.total,i.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].rmbAmount=this.amountHandle1(e[t].rmbAmount),e[t].fee=this.amountHandle1(e[t].fee)},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",Number(e.sform.agentName)),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.$refs.sform.resetFields()},onExport:function(e){var t=new URLSearchParams,a="",r="",o="",n="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(r=this.dateFormat(e.endTime)),""!=e.regStartTime&&(o=this.dateFormat(e.regStartTime)),""!=e.regEndTime&&(n=this.dateFormat(e.regEndTime)),t.append("startTime",a),t.append("endTime",r),t.append("regStartTime",o),t.append("regEndTime",n),t.append("userName",e.userName),t.append("agentName",this.isNotEmpty(e.agentName)?Number(e.agentName):""),t.append("brokerName",this.isNotEmpty(e.brokerName)?Number(e.brokerName):""),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pageSize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var o=t.getDate();o=o<10?"0"+o:o;var n=t.getHours();n=n<10?"0"+n:n;var s=t.getMinutes(),i=t.getSeconds();return s=s<10?"0"+s:s,i=i<10?"0"+i:i,a+"-"+r+"-"+o+" "+n+":"+s+":"+i}}}},638:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-6d4b89c1]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-6d4b89c1]{border-radius:4px}.bg-purple-dark[data-v-6d4b89c1]{background:#99a9bf}.bg-purple[data-v-6d4b89c1]{background:#d3dce6}.bg-purple-light[data-v-6d4b89c1]{background:#e5e9f2}.grid-content[data-v-6d4b89c1]{border-radius:4px;min-height:36px}.row-bg[data-v-6d4b89c1]{padding:10px 0;background-color:#f9fafc}",""])},697:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"交易订单号：",prop:"orderNo"}},[a("el-input",{model:{value:e.sform.orderNo,callback:function(t){e.$set(e.sform,"orderNo",t)},expression:"sform.orderNo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"买入时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"注册时间：",prop:"regStartTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.regStartTime,callback:function(t){e.$set(e.sform,"regStartTime",t)},expression:"sform.regStartTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"regEndTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.regEndTime,callback:function(t){e.$set(e.sform,"regEndTime",t)},expression:"sform.regEndTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-row",{attrs:{gutter:21}},[a("el-col",{attrs:{span:7}},[a("div",{staticClass:"grid-content bg-purple"},[e._v("黄金买入总计（克）"),a("br"),a("span",{attrs:{id:"hjmrId"}})])]),e._v(" "),a("el-col",{attrs:{span:7}},[a("div",{staticClass:"grid-content bg-purple"},[e._v("手续费总计（元）"),a("br"),a("span",{attrs:{id:"sxfId"}})])])],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"registerTime",label:"注册时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"orderNo",label:"交易订单号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"productName",label:"合约类型",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"buyPrice",label:"买入价",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gram",label:"黄金克数",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rmbAmount",label:"买入金额",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"fee",label:"手续费",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"buyTime",label:"买入时间",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 交易管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("实金交易")])]),e._v(" "),a("h1")])}]}},745:function(e,t,a){var r=a(638);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("fa8441d4",r,!0)}});