webpackJsonp([43],{509:function(e,t,a){a(738);var r=a(196)(a(562),a(690),"data-v-5a07565f",null);e.exports=r.exports},562:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,actorList:"",form:{username:"",region:"",date1:"",date2:"",brokerName:"",agentsName:"",delivery:!1,type:[],resource:"",desc:""},brokeList:[],agentOptions:[],url:"userInfo/selectByAccountMessage",agentUrl:"user/selectByAgentMessage",brokerUrl:"user/selectByBrokerMessage",exportUrl:"userInfo/excelAccountMessage",countUrl:"userInfo/selectAccountCount",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],formLabelWidth:"120px",userId:"",countList:""}},filters:{divide:function(e){return e/=100}},created:function(){this.loadData(10,1),this.countNum();var e=this;axios.get(this.agentUrl).then(function(t){e.agentOptions=t.data.data}).catch(function(e){console.log(e)})},methods:{agentChange:function(){var e=this,t="";void 0!=this.form.agentName&&(t=this.form.agentName),axios.get(this.brokerUrl,{params:{pid:t}}).then(function(t){e.brokeList=t.data.data}).catch(function(e){console.log(e)})},countNum:function(){var e=new URLSearchParams,t=this,a="",r="",o="",n="",s="";void 0!=this.form.username&&(o=this.form.username),""!=this.form.date1&&(a=this.dateFormat(this.form.date1)),""!=this.form.date2&&(r=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(n=this.form.brokerName),console.log(this.form.agentName),void 0!=this.form.agentName&&(s=this.form.agentName),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("userName",o),e.append("startTime",a),e.append("endTime",r),e.append("brokerName",n),e.append("agentsName",s),axios.get(this.countUrl+"?"+e).then(function(e){console.log(e.data.data),t.countList=e.data.data}).catch(function(e){console.log(e)})},onSubmit:function(e){this.countNum();var t=new URLSearchParams,a=this,r="",o="",n="",s="",i="";void 0!=this.form.username&&(n=this.form.username),""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(s=this.form.brokerName),console.log(this.form.agentName),void 0!=this.form.agentName&&(i=this.form.agentName),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("userName",n),t.append("startTime",r),t.append("endTime",o),t.append("brokerName",s),t.append("agentsName",i),axios.post(this.url,t).then(function(e){a.$message({message:"查询成功",type:"success"}),a.currentPage=1,a.pageNum=e.data.pages,a.totalNum=e.data.total,a.tableData=e.data.data.list}).catch(function(e){})},resetForm:function(){this.$refs.form.resetFields(),this.loadData(10,1),this.countNum()},exportFun:function(){var e=new URLSearchParams,t="",a="",r="",o="",n="";void 0!=this.form.username&&(r=this.form.username),""!=this.form.date1&&(t=this.dateFormat(this.form.date1)),""!=this.form.date2&&(a=this.dateFormat(this.form.date2)),void 0!=this.form.brokerName&&(o=this.form.brokerName),void 0!=this.form.agentName&&(n=this.form.agentName),e.append("pageSize",this.pagesize),e.append("pageNum",this.currentPage),e.append("userName",r),e.append("startTime",t),e.append("endTime",a),e.append("brokerName",o),e.append("agentsName",n),console.log(this.exportUrl+"?"+e),window.location=this.exportUrl+"?"+e},loadData:function(e,t){var a=new URLSearchParams,r="",o="",n="",s="",i="";n=void 0==this.form.username?"":this.form.username,s=void 0==this.form.brokerName?"":this.form.brokerName,i=void 0==this.form.agentsName?"":this.form.agentsName,""!=this.form.date1&&(r=this.dateFormat(this.form.date1)),""!=this.form.date2&&(o=this.dateFormat(this.form.date2)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("userName",n),a.append("brokerName",s),a.append("agentsName",i),a.append("startTime",r),a.append("endTime",o);var l=this;axios.post(this.url,a).then(function(e){l.totalNum=e.data.data.total,l.pageNum=e.data.data.pages,l.tableData=e.data.data.list}).catch(function(e){})},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},631:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,".el-row[data-v-5a07565f]{margin-bottom:20px;&:last-child{margin-bottom:0}}.el-col[data-v-5a07565f]{border-radius:4px}.bg-purple-dark[data-v-5a07565f]{background:#99a9bf}.bg-color1[data-v-5a07565f]{color:#da542e;border:.5px solid #da542e;background:#f2dede}.bg-color2[data-v-5a07565f]{color:#468849;background:#dff0d8;border:.5px solid #468847}.bg-color3[data-v-5a07565f]{color:#27a9e3;background:#d9edf7;border:.5px solid #3a87ad}.bg-color4[data-v-5a07565f]{color:#c3881f;background:#fcf8e3;border:.5px solid #c3881e}.bg-color5[data-v-5a07565f]{background:#d3dce6}.bg-purple-light[data-v-5a07565f]{background:#e5e9f2}.gridBox[data-v-5a07565f]{padding-left:20px}.grid-content[data-v-5a07565f]{height:75px;border-radius:4px;min-height:75px;text-align:center;font-size:14px}.row-bg[data-v-5a07565f]{padding:10px 0;background-color:#f9fafc}",""])},690:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"formBox"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"用户账号",prop:"username"}},[a("el-input",{model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"代理商",prop:"agentName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:e.agentChange},model:{value:e.form.agentName,callback:function(t){e.$set(e.form,"agentName",t)},expression:"form.agentName"}},e._l(e.agentOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.agentName,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"经纪人",prop:"brokerName","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.brokerName,callback:function(t){e.$set(e.form,"brokerName",t)},expression:"form.brokerName"}},e._l(e.brokeList,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"注册时间",prop:"date1"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("-")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"date2"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.form)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.form)}}},[e._v("清除")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.exportFun}},[e._v("导出")])],1)],1)],1),e._v(" "),a("el-row",{staticClass:"gridBox",attrs:{gutter:40,justify:"end"}},[a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color1"},[a("p",[e._v("人民币余额总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.rmbSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color2"},[a("p",[e._v("人民币冻结总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.frozenRmbSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color3"},[a("p",[e._v("人民币理财总计（元）")]),e._v(" "),a("p",[e._v("  "+e._s(e._f("divide")(e.countList.finaceSum)))])])]),e._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-color4"},[a("p",[e._v("黄金（克）")]),e._v(" "),a("p",[e._v(e._s(e.countList.goldSum))])])])],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{attrs:{data:e.tableData,fit:"",border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"80"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"用户账号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"RealName",label:"姓名"}}),e._v(" "),a("el-table-column",{attrs:{prop:"registerTime",label:"注册时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"agentName",label:"代理商",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"brokerName",label:"经纪人",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"idcard",label:"身份证号码",width:"200"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"银行卡",width:"200"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rmb ",label:"人民币余额",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.rmb))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"人民币冻结",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.frozenRmb))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"人民币理财",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.finance))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"利息",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(e._f("divide")(t.row.totalIncome))+"\n            ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"gold",label:"黄金",width:"180"}})],1)],1),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 客户管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("账户信息")])])])}]}},738:function(e,t,a){var r=a(631);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(197)("580ff2f8",r,!0)}});