webpackJsonp([47],{507:function(e,t,a){a(762);var r=a(196)(a(562),a(712),"data-v-89424ed6",null);e.exports=r.exports},562:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,activityTypeOptions:[{value:1,label:"新手活动"},{value:2,label:"推荐有礼"}],agentOptions:"",brokerOptions:"",sform:{userName:"",startTime:"",endTime:"",activityType:"",agentName:"",brokerName:""},url:"userGoldAccountActivityRecord/selectByAll",agentUrl:"user/selectByAgentMessage",brokeUrl:"user/selectByBrokerMessage",exportUrl:"userGoldAccountActivityRecord/excelByAll",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,formLabelWidth:"120px",userId:""}},created:function(){this.loadData(10,1);var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,r="",i="";""!=e.startTime&&(r=this.dateFormat(e.startTime)),""!=e.endTime&&(i=this.dateFormat(e.endTime)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("startTime",r),t.append("endTime",i),t.append("userName",e.userName),t.append("activityType",e.activityType),t.append("agentName",e.agentName),t.append("brokerName",e.brokerName),axios.post(this.url,t).then(function(e){if(1001==e.data.code){a.$message({message:"查询成功",type:"success"});var t=e.data.data.list;a.currentPage=1,a.pageNum=e.data.data.pages,a.totalNum=e.data.data.total,a.handelData(t),a.tableData=t}else a.$message({message:"查询失败",type:"warning"})}).catch(function(e){})},loadData:function(e,t){var a=new URLSearchParams,r="",i="";""!=this.sform.startTime&&(r=this.dateFormat(this.sform.startTime)),""!=this.sform.endTime&&(i=this.dateFormat(this.sform.endTime)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("startTime",r),a.append("endTime",i),a.append("userName",this.sform.userName),a.append("activityType",this.sform.activityType),a.append("agentName",this.sform.agentName),a.append("brokerName",this.sform.brokerName);var o=this;axios.post(this.url,a).then(function(e){if(1001==e.data.code){var t=e.data.data.list;o.handelData(t),o.pageNum=e.data.data.pages,o.totalNum=e.data.data.total,o.tableData=t}}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)1==e[t].activityType?e[t].activityType="新手活动":2==e[t].activityType&&(e[t].activityType="推荐有礼")},getBrokerOptions:function(){var e=this;e.sform.brokerName="";var t=new URLSearchParams;t.append("pid",e.sform.agentName),axios.post(e.brokeUrl,t).then(function(t){e.brokerOptions=t.data.data}).catch(function(e){console.log(e)})},resetForm:function(e){this.$refs.sform.resetFields(),this.loadData(10,1)},onExport:function(e){var t=new URLSearchParams,a="",r="";""!=e.startTime&&(a=this.dateFormat(e.startTime)),""!=e.endTime&&(r=this.dateFormat(e.endTime)),t.append("startTime",a),t.append("endTime",r),t.append("userName",e.userName),t.append("activityType",e.activityType),t.append("agentName",e.agentName),t.append("brokerName",e.brokerName),console.info(this.exportUrl+"?"+t),window.location=this.exportUrl+"?"+t},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)},dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),r=t.getMonth()+1;r=r<10?"0"+r:r;var i=t.getDate();i=i<10?"0"+i:i;var o=t.getHours();o=o<10?"0"+o:o;var s=t.getMinutes(),n=t.getSeconds();return s=s<10?"0"+s:s,n=n<10?"0"+n:n,a+"-"+r+"-"+i+" "+o+":"+s+":"+n}}}},651:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,"",""])},712:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("el-form",{ref:"sform",attrs:{inline:!0,"demo-form-inline":"",model:e.sform,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户账号：",prop:"userName"}},[a("el-input",{model:{value:e.sform.userName,callback:function(t){e.$set(e.sform,"userName",t)},expression:"sform.userName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"领取时间：",prop:"startTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.startTime,callback:function(t){e.$set(e.sform,"startTime",t)},expression:"sform.startTime"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("----")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"endTime"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.sform.endTime,callback:function(t){e.$set(e.sform,"endTime",t)},expression:"sform.endTime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"活动类型：",prop:"activityType"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.activityType,callback:function(t){e.$set(e.sform,"activityType",t)},expression:"sform.activityType"}},e._l(e.activityTypeOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商：",prop:"agentName"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.getBrokerOptions()}},model:{value:e.sform.agentName,callback:function(t){e.$set(e.sform,"agentName",t)},expression:"sform.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人：",prop:"brokerName"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.sform.brokerName,callback:function(t){e.$set(e.sform,"brokerName",t)},expression:"sform.brokerName"}},e._l(e.brokerOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.brokerName,value:e.id}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.sform)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.sform)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"export"},on:{click:function(t){e.onExport(e.sform)}}},[e._v("导出")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"180",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180",fixed:"left"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"amount",label:"黄金克重",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"description",label:"描述",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"activityType",label:"活动类型",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"领取时间",width:"180"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 活动管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("黄金领取记录")])]),e._v(" "),a("h1")])}]}},762:function(e,t,a){var r=a(651);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("8f8ac85c",r,!0)}});