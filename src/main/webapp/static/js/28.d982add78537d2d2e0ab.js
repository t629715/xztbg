webpackJsonp([28],{536:function(e,t,a){a(741);var r=a(196)(a(596),a(686),"data-v-03ab78cf",null);e.exports=r.exports},596:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",status:2,brokerName:""},url:"financeOrder/selectByFinanceOrder",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",exportUrl:"financeOrder/excelFinanceOrder",countUrl:"financeOrder/selectByFinanceOrderCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this,t=new URLSearchParams;t.append("pid",""),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)});var a=new URLSearchParams;a.append("status",2),axios.post(this.countUrl,a).then(function(t){1001==t.data.code&&(document.getElementById("syzcId").innerText=e.amountHandle1(t.data.data.shareAmountSum))}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,r="",o="";""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(o=this.dateFormat(e.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",o),t.append("userName",e.userName),t.append("status",2),t.append("brokerName",e.brokerName),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(this.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("syzcId").innerText=a.amountHandle1(e.data.data.shareAmountSum))}).catch(function(e){console.log(e)})},loadData:function(e,t){var a=new URLSearchParams,r="",o="";""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(o=this.dateFormat(this.sform.endTime)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",o),a.append("userName",this.sform.userName),a.append("status",2),a.append("brokerName",this.sform.brokerName);var n=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;n.handelData(t),n.pageNum=e.data.data.pages,n.totalNum=e.data.data.total,n.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].buyAmount=this.amountHandle1(e[t].buyAmount),e[t].initialPrice=this.amountHandle1(e[t].initialPrice),e[t].income=this.amountHandle1(e[t].income),e[t].yearIncomPercent=this.isNotEmpty(e[t].yearIncomPercent)?Number(100*e[t].yearIncomPercent).toFixed(2)+"%":0,e[t].shareAmount=this.amountHandle1(e[t].shareAmount)},resetForm:function(e){var t=this;this.$refs.sform.resetFields();var a=new URLSearchParams;a.append("status",2),axios.post(this.countUrl,a).then(function(e){1001==e.data.code&&(document.getElementById("syzcId").innerText=t.amountHandle1(e.data.data.shareAmountSum))}).catch(function(e){console.log(e)}),this.loadData(10,1)},onExport:function(e){var t=new URLSearchParams,a="",r="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(r=this.dateFormat(e.endTime)),t.append("startTime",a),t.append("endTime",r),t.append("userName",e.userName),t.append("status",2),t.append("brokerName",e.brokerName),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var o=t.getDate();o=o<10?"0"+o:o;var n=t.getHours();n=n<10?"0"+n:n;var s=t.getMinutes(),i=t.getSeconds();return s=s<10?"0"+s:s,i=i<10?"0"+i:i,a+"-"+r+"-"+o+" "+n+":"+s+":"+i}}}},620:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-03ab78cf]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-03ab78cf]{border-radius:4px}.bg-purple-dark[data-v-03ab78cf]{background:#99a9bf}.bg-color1[data-v-03ab78cf]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-03ab78cf]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-03ab78cf]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-03ab78cf]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-03ab78cf]{background:#d3dce6}.bg-purple-light[data-v-03ab78cf]{background:#e5e9f2}.gridBox[data-v-03ab78cf]{padding-left:20px}.grid-content[data-v-03ab78cf]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-03ab78cf]{padding:10px 0;background-color:#f9fafc}",""])},686:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{attrs:{size:"small"},model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"买入时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("br"),e._v("      \n        "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"info"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color1"},[a("p",[e._v("收益总计（元）")]),e._v(" "),a("p",{attrs:{id:"syzcId"}})])])],1),e._v(" "),a("el-table",{staticStyle:{width:"auto",display:"inline-block"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"100",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"orderNo",label:"交易订单号",width:"220"}}),e._v(" "),a("el-table-column",{attrs:{prop:"productNo",label:"产品编号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"productName",label:"产品名称",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"cycle",label:"周期",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"yearIncomPercent",label:"收益率",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"initialPrice",label:"买入价",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gram",label:"买入克重",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"buyAmount",label:"买入金额",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"buyTime",label:"买入时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"income",label:"收益支出",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"shareAmount",label:"交易分成",width:"100"}})],1),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 合作伙伴")]),e._v(" "),a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;"}},[e._v("交易结算")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("理财交易")])]),e._v(" "),a("h1")])}]}},741:function(e,t,a){var r=a(620);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("8b2ced7c",r,!0)}});