webpackJsonp([34],{520:function(e,t,a){a(731);var o=a(196)(a(573),a(683),"data-v-33c977d7",null);e.exports=o.exports},573:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,agentOptions:"",brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",agentName:"",brokerName:""},url:"资讯goldIncomeRecord/selectByGoldIncome",agentUrl:"资讯user/selectByAgentMessage",brokeUrl:"资讯user/selectByBrokerMessage",exportUrl:"资讯goldIncomeRecord/excelGoldIncome",countUrl:"资讯goldIncomeRecord/selectByGoldIncomeCount",gramCountUrl:"资讯goldIncomeRecord/selectByGoldGramCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",type:"",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)}),axios.get(this.countUrl).then(function(t){1001==t.data.code&&(document.getElementById("jxzcId").innerText=e.amountHandle1(t.data.data.incomeSum))}).catch(function(e){console.log(e)}),axios.get(this.gramCountUrl).then(function(t){1001==t.data.code&&(document.getElementById("hjzzId").innerText=e.amountHandle1(t.data.data.goldSum))}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,o="",n="";""!=e.startTime&&(o=this.dateFormat(e.startTime)),""!=e.endTime&&(n=this.dateFormat(e.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",o),t.append("endTime",n),t.append("userName",e.userName),t.append("agentName",e.agentName),t.append("brokerName",e.brokerName),t.append("type",this.type),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(this.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("jxzcId").innerText=a.amountHandle1(e.data.data.incomeSum))}).catch(function(e){console.log(e)})},search:function(e){var t=new URLSearchParams,a=this,o="",n="";""!=a.sform.startTime&&(o=this.dateFormat(a.sform.startTime)),""!=a.sform.endTime&&(n=this.dateFormat(a.sform.endTime)),a.type=e,t.append("pageSize",a.pagesize),t.append("pageNum",a.currentPage),t.append("startTime",o),t.append("endTime",n),t.append("type",a.type),t.append("userName",a.sform.userName),t.append("agentName",a.sform.agentName),t.append("brokerName",a.sform.brokerName),axios.post(a.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(a.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("jxzcId").innerText=a.amountHandle1(e.data.data.incomeSum))}).catch(function(e){console.log(e)})},loadData:function(e,t){var a=new URLSearchParams,o="",n="";""!=this.sform.startTime&&(o=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(n=this.dateFormat(this.sform.endTime)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",o),a.append("endTime",n),a.append("userName",this.sform.userName),a.append("agentName",this.sform.agentName),a.append("brokerName",this.sform.brokerName),a.append("type",this.type);var r=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;r.handelData(t),r.pageNum=e.data.data.pages,r.totalNum=e.data.data.total,r.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].income=this.amountHandle1(e[t].income),e[t].totalIncom=this.amountHandle1(e[t].totalIncom),e[t].fee=this.amountHandle1(e[t].fee),e[t].incomePercent=this.isNotEmpty(e[t].incomePercent)?100*Number(e[t].incomePercent)+"%":0},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",e.sform.agentName),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.type="",this.$refs.sform.resetFields();var t=this;axios.get(this.countUrl).then(function(e){1001==e.data.code&&(document.getElementById("jxzcId").innerText=t.amountHandle1(e.data.data.incomeSum))}).catch(function(e){console.log(e)}),axios.get(this.gramCountUrl).then(function(e){1001==e.data.code&&(document.getElementById("hjzzId").innerText=t.amountHandle1(e.data.data.goldSum))}).catch(function(e){console.log(e)}),this.loadData(10,1)},onExport:function(e){var t=new URLSearchParams,a="",o="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(o=this.dateFormat(e.endTime)),t.append("startTime",a),t.append("endTime",o),t.append("userName",e.userName),t.append("agentName",e.agentName),t.append("brokerName",e.brokerName),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),o=t.getMonth()+1;o=o<10?"0"+o:o;var n=t.getDate();n=n<10?"0"+n:n;var r=t.getHours();r=r<10?"0"+r:r;var s=t.getMinutes(),i=t.getSeconds();return s=s<10?"0"+s:s,i=i<10?"0"+i:i,a+"-"+o+"-"+n+" "+r+":"+s+":"+i}}}},623:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-33c977d7]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-33c977d7]{border-radius:4px}.bg-purple-dark[data-v-33c977d7]{background:#99a9bf}.bg-color1[data-v-33c977d7]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-33c977d7]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-33c977d7]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-33c977d7]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-33c977d7]{background:#d3dce6}.bg-purple-light[data-v-33c977d7]{background:#e5e9f2}.gridBox[data-v-33c977d7]{padding-left:20px}.grid-content[data-v-33c977d7]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-33c977d7]{padding:10px 0;background-color:#f9fafc}",""])},683:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"发放时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color1"},[a("p",[e._v("黄金持有总重（克）")]),e._v(" "),a("p",{attrs:{id:"hjzzId"}})])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[e._v("金息支出总计（元）")]),e._v(" "),a("p",{attrs:{id:"jxzcId"}})])]),e._v(" "),a("el-col",{attrs:{span:10}},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.search(1)}}},[e._v("昨天")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.search(2)}}},[e._v("近七天")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.search(3)}}},[e._v("本月")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.search(4)}}},[e._v("上个月")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gram",label:"结算时所持黄金克重",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"price",label:"收盘金价",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"income",label:"发放收益",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"totalIncom",label:"已发放收益总计",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"incomePercent",label:"收益率",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"发放时间",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 客户管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("黄金收益结算")])]),e._v(" "),a("h1")])}]}},731:function(e,t,a){var o=a(623);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(197)("5f8a5b69",o,!0)}});