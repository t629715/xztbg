webpackJsonp([7],{550:function(e,t,a){a(727);var r=a(196)(a(603),a(679),"data-v-30a8c0c2",null);e.exports=r.exports},603:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{selectTime:"",loading:!0,agentOptions:"",brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",agentName:"",brokerName:""},url:"api/goldIncomeRecord/selectByGoldIncome",agentUrl:"api/user/selectByAgentMessage",brokeUrl:"api/user/selectByBrokerMessage",exportUrl:"api/goldIncomeRecord/excelGoldIncome",countUrl:"api/goldIncomeRecord/selectByGoldIncomeCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)}),axios.get(this.countUrl).then(function(e){1001==e.data.code&&(document.getElementById("hjzzId").innerText=Number(e.data.data.gramSum)/100,document.getElementById("jxzcId").innerText=Number(e.data.data.incomeSum)/100)}).catch(function(e){console.log(e)})},methods:{whicth:function(){console.log(this.selectTime)},onSubmit:function(e){var t=new URLSearchParams,a=this,r="",n="";""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(n=this.dateFormat(e.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",n),t.append("userName",e.userName),t.append("agentName",""!=e.agentName?Number(e.agentName):""),t.append("brokerName",""!=e.brokerName?Number(e.brokerName):""),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=0==e.data.data.pageNum?1:e.data.data.pageNum,a.pagesize=e.data.data.pageSize,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(this.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("hjzzId").innerText=Number(e.data.data.gramSum)/100,document.getElementById("jxzcId").innerText=Number(e.data.data.incomeSum)/100)}).catch(function(e){console.log(e)})},search:function(e){var t=new URLSearchParams,a=this,r="",n="";""!=a.sform.startTime&&(r=this.dateFormat(a.sform.startTime)),""!=a.sform.endTime&&(n=this.dateFormat(a.sform.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",n),t.append("type",e),t.append("userName",a.sform.userName),t.append("agentName",""!=a.sform.agentName?Number(a.sform.agentName):""),t.append("brokerName",""!=a.sform.brokerName?Number(a.sform.brokerName):""),axios.post(a.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=0==e.data.data.pageNum?1:e.data.data.pageNum,a.pagesize=e.data.data.pageSize,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){}),axios.post(a.countUrl,t).then(function(e){1001==e.data.code&&(document.getElementById("hjzzId").innerText=Number(e.data.data.gramSum)/100,document.getElementById("jxzcId").innerText=Number(e.data.data.incomeSum)/100)}).catch(function(e){console.log(e)})},loadData:function(e,t){var a=new URLSearchParams,r="",n="";""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(n=this.dateFormat(this.sform.endTime)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",n),a.append("userName",this.sform.userName),a.append("agentName",""!=this.sform.agentName?Number(this.sform.agentName):""),a.append("brokerName",""!=this.sform.brokerName?Number(this.sform.brokerName):"");var o=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;o.handelData(t),o.pagesize=e.data.data.pageSize,o.pageNum=e.data.data.pages,o.totalNum=e.data.data.total,o.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)e[t].income=""!=e[t].income?Number(e[t].income)/100:0,e[t].totalIncom=""!=e[t].totalIncom?Number(e[t].totalIncom)/100:0,e[t].incomePercent=""!=e[t].incomePercent?100*Number(e[t].incomePercent)+"%":0},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",Number(e.sform.agentName)),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.$refs.sform.resetFields()},onExport:function(e){var t=new URLSearchParams,a="",r="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(r=this.dateFormat(e.endTime)),t.append("startTime",a),t.append("endTime",r),t.append("userName",e.userName),t.append("agentName",""!=e.agentName?Number(e.agentName):""),t.append("brokerName",""!=e.brokerName?Number(e.brokerName):""),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var n=t.getDate();n=n<10?"0"+n:n;var o=t.getHours();o=o<10?"0"+o:o;var s=t.getMinutes(),i=t.getSeconds();return s=s<10?"0"+s:s,i=i<10?"0"+i:i,a+"-"+r+"-"+n+" "+o+":"+s+":"+i}}}},620:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-30a8c0c2]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-30a8c0c2]{border-radius:4px}.bg-purple-dark[data-v-30a8c0c2]{background:#99a9bf}.bg-purple[data-v-30a8c0c2]{background:#d3dce6}.bg-purple-light[data-v-30a8c0c2]{background:#e5e9f2}.grid-content[data-v-30a8c0c2]{border-radius:4px;min-height:36px}.row-bg[data-v-30a8c0c2]{padding:10px 0;background-color:#f9fafc}",""])},679:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"时间：",prop:"radio3"}},[a("el-radio-group",{on:{change:e.whicth},model:{value:e.selectTime,callback:function(t){e.selectTime=t},expression:"selectTime"}},[a("el-radio-button",{attrs:{label:"全部",name:"1",value:1}}),e._v(" "),a("el-radio-button",{attrs:{label:"昨天",name:"2"}}),e._v(" "),a("el-radio-button",{attrs:{label:"最近7天",name:"3"}}),e._v(" "),a("el-radio-button",{attrs:{label:"最近30天",name:"4"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"发放时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-row",{attrs:{gutter:24}},[a("el-col",{attrs:{span:8}},[a("div",{staticClass:"grid-content bg-purple"},[e._v("黄金持有总重（克）"),a("br"),a("span",{attrs:{id:"hjzzId"}})])]),e._v(" "),a("el-col",{attrs:{span:8}},[a("div",{staticClass:"grid-content bg-purple"},[e._v("金息支出总计（元）"),a("br"),e._v(" "),a("span",{attrs:{id:"jxzcId"}})])]),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.search(1)}}},[e._v("昨天")]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.search(2)}}},[e._v("近七天")]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.search(3)}}},[e._v("本月")]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.search(4)}}},[e._v("上个月")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gram",label:"结算时所持黄金克重",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"price",label:"收盘金价",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"income",label:"发放收益",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"totalIncom",label:"已发放收益总计",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"incomePercent",label:"收益率",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"发放时间",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 交易管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("交易分析")])]),e._v(" "),a("h1")])}]}},727:function(e,t,a){var r=a(620);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("834cbf20",r,!0)}});