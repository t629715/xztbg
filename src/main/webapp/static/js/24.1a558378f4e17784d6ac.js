webpackJsonp([24],{530:function(e,t,a){a(739);var o=a(196)(a(583),a(691),"data-v-5f066f86",null);e.exports=o.exports},583:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!0,operatorList:"",form:{title:"",state:"",operator:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""},readoneform:{title:"",imagePath:"",content:""},newform:{title:"",content:"",imagePath:"",contentFrom:"",informationFrom:"",contentFromType:""},newformEdit:{title:"",imagePath:"",content:"",contentFrom:"",informationFrom:"",contentFromType:""},url:"infoNotice/seelctAll",addUrl:"infoNotice/add",editUrl:"infoNotice/edit",deleteUrl:"infoNotice/delete",operatorListUrl:"infoNotice/getOperators",currentPage:0,pageSize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisibleRead:!1,dialogFormVisible:!1,dialogFormVisibleEdit:!1,dialogFormVisibleDelete:!1,formLabelWidth:"120px",infoId:"",imagepath:"",content:"",informationFrom:"",types:[{id:1,name:"站内"},{id:2,name:"站外"}]}},created:function(){this.loadData(10,1);var e=this;axios.get(this.operatorListUrl).then(function(t){e.operatorList=t.data.data}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,o="",i="",l="",r="";l=void 0==e.title?"":e.title,""!=e.date1&&(o=Date.parse(e.date1)),""!=e.date2&&(i=Date.parse(e.date2)),""!=e.operator&&(r=e.operator),t.append("pageSize",this.pageSize),t.append("pageNum",this.currentPage),t.append("title",l),t.append("startTime",o),t.append("endTime",i),t.append("operator",r),axios.post(this.url,t).then(function(e){a.$message({message:"查询成功",type:"success"}),a.tableData=e.data.data.list}).catch(function(e){})},resetForm:function(){this.$refs.form.resetFields()},deleteDialog:function(e,t){this.dialogFormVisibleDelete=!0,this.row=t},jdState:function(e){return 1==e.state?"已发布":0==e.state?"已创建":-1==e.state?"已下线":void 0},loadData:function(e,t){var a=new URLSearchParams;a.append("pageSize",e),a.append("pageNum",t);var o=this;axios.post(this.url,a).then(function(e){o.currentPage=e.data.pageNum,o.pageSize=e.data.pageSize,o.pageNum=e.data.pages,o.totalNum=e.data.total,o.tableData=e.data.list}).catch(function(e){})},add:function(){this.newform={title:"",content:"",skipPath:"",skipType:""},this.dialogFormVisible=!0},confirmAdd:function(e){var t=this,a=new URLSearchParams;a.append("title",this.newform.title),a.append("content",this.newform.content),a.append("skipPath",this.newform.skipPath),a.append("skipType",this.newform.skipType);var o=this;e&&axios.post(this.addUrl,a).then(function(a){if(1==a.data.msg)return o.$message({message:"添加成功",type:"success"}),o.dialogFormVisible=!1,o.loadData(o.pageSize,1),void(e=!1);0==a.data.msg?(o.$message({message:"添加失败",type:"warning"}),o.dialogFormVisible=!1):-1==a.data.msg&&t.$router.push("/login")})},handleDelete:function(){var e=this,t=this,a=new URLSearchParams;a.append("serialNo",t.row.serialNo),axios.post(this.deleteUrl,a).then(function(a){1==a.data.msg?(t.$message({message:"删除成功",type:"success"}),t.dialogFormVisibleDelete=!1,t.loadData(t.pageSize,t.currentPage)):0==a.data.msg?(t.$message({message:"删除失败",type:"warning"}),t.dialogFormVisibleEdit=!1):-1==a.data.msg&&(t.dialogFormVisibleEdit=!1,e.$router.push("/login"),t.$message.error("请登录"))}).catch(function(e){})},readOneForm:function(e,t){this.readoneform={title:t.title,skipType:1==t.contentFromType?"站内":"站外",skipPath:t.skipPath,content:t.content},this.dialogFormVisibleRead=!0},handleEdit:function(e,t){this.newformEdit={title:t.title,skipPath:t.skipPath,content:t.content,skipType:t.skipType},this.serialNo=t.serialNo,this.dialogFormVisibleEdit=!0},confirmAddEdit:function(){var e=this,t=new URLSearchParams;t.append("title",this.newformEdit.title),t.append("skipPath",this.newformEdit.skipPath),t.append("content",this.newformEdit.content),t.append("skipType",this.newformEdit.skipType),t.append("serialNo",this.serialNo);var a=this;axios.post(this.editUrl,t).then(function(t){1==t.data.msg?(a.$message({message:"修改成功",type:"success"}),a.dialogFormVisibleEdit=!1,a.loadData(a.pageSize,1)):0==t.data.msg?(a.$message({message:"修改失败",type:"warning"}),a.dialogFormVisibleEdit=!1):-1==t.data.msg&&(a.dialogFormVisibleEdit=!1,e.$router.push("/login"),a.$message.error("请登录"))})},handleCurrentChange:function(e){this.loadData(this.pageSize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},632:function(e,t,a){t=e.exports=a(135)(void 0),t.push([e.i,"",""])},691:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0),e._v(" "),a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"formBox"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"标题",prop:"title"}},[a("el-input",{model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"发布时间",prop:"date1"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1),e._v(" "),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("-")])],1),e._v(" "),a("el-form-item",{attrs:{prop:"date2"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"发布人",prop:"operator"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.operator,callback:function(t){e.$set(e.form,"operator",t)},expression:"form.operator"}},e._l(e.operatorList,function(e){return a("el-option",{key:e.operator,attrs:{label:e.operator,value:e.operator}})}))],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit(e.form)}}},[e._v("查询")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.resetForm(e.form)}}},[e._v("清除")])],1)],1),e._v(" "),a("el-button",{attrs:{type:"success"},on:{click:e.add}},[e._v("发布")])],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"120"}}),e._v(" "),a("el-table-column",{attrs:{prop:"title",label:"标题",width:"120"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",label:"创建时间",width:"240"}}),e._v(" "),a("el-table-column",{attrs:{prop:"operator",label:"发布人",width:"240"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){e.readOneForm(t.$index,t.row)}}},[e._v("查看")]),e._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){e.deleteDialog(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-dialog",{attrs:{title:"预览",visible:e.dialogFormVisibleRead},on:{"update:visible":function(t){e.dialogFormVisibleRead=t}}},[a("el-form",{attrs:{model:e.readoneform}},[a("el-form-item",{attrs:{label:"标题",prop:e.readoneform.title,"label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.readoneform.title,callback:function(t){e.$set(e.readoneform,"title",t)},expression:"readoneform.title"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"内容","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.readoneform.content,callback:function(t){e.$set(e.readoneform,"content",t)},expression:"readoneform.content"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转路径","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.readoneform.skipPath,callback:function(t){e.$set(e.readoneform,"skipPath",t)},expression:"readoneform.skipPath"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.readoneform.skipType,callback:function(t){e.$set(e.readoneform,"skipType",t)},expression:"readoneform.skipType"}})],1)],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"发布",visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"newform",attrs:{model:e.newform}},[a("el-form-item",{attrs:{label:"标题","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newform.title,callback:function(t){e.$set(e.newform,"title",t)},expression:"newform.title"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"内容","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newform.content,callback:function(t){e.$set(e.newform,"content",t)},expression:"newform.content"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转路径","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newform.skipPath,callback:function(t){e.$set(e.newform,"skipPath",t)},expression:"newform.skipPath"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newform.skipType,callback:function(t){e.$set(e.newform,"skipType",t)},expression:"newform.skipType"}},e._l(e.types,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.confirmAdd}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"编辑",visible:e.dialogFormVisibleEdit},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[a("el-form",{attrs:{model:e.newformEdit}},[a("el-form-item",{attrs:{label:"标题","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.title,callback:function(t){e.$set(e.newformEdit,"title",t)},expression:"newformEdit.title"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"内容","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.content,callback:function(t){e.$set(e.newformEdit,"content",t)},expression:"newformEdit.content"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转路径","label-width":e.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.newformEdit.skipPath,callback:function(t){e.$set(e.newformEdit,"skipPath",t)},expression:"newformEdit.skipPath"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.newformEdit.skipType,callback:function(t){e.$set(e.newformEdit,"skipType",t)},expression:"newformEdit.skipType"}},e._l(e.types,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisibleEdit=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.confirmAddEdit}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"确认删除？",center:"",width:"300px",visible:e.dialogFormVisibleDelete},on:{"update:visible":function(t){e.dialogFormVisibleDelete=t}}},[a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:6}},[a("el-button",{on:{click:function(t){e.dialogFormVisibleDelete=!1}}},[e._v("取 消")])],1),e._v(" "),a("el-col",{attrs:{span:6,offset:6}},[a("el-button",{attrs:{type:"primary"},on:{click:e.handleDelete}},[e._v("确 定")])],1)],1)],1)])],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 运营管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("资讯管理")])]),e._v(" "),a("h1")])}]}},739:function(e,t,a){var o=a(632);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(197)("a3dd2906",o,!0)}});