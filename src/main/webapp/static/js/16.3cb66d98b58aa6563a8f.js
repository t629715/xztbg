webpackJsonp([16],{558:function(e,t,a){a(769);var i=a(198)(a(620),a(712),"data-v-2dbb0b72",null);e.exports=i.exports},620:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{loading:!1,isViewList:[{id:0,name:"否"},{id:1,name:"是"}],form:{username:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""},newform:{username:""},newformEdit:{username:""},checkeAll:!1,checkeAll1:!1,actorList:"",url:"role/selectRoleUsers",selectRolerUrl:"permission/selectPermissions",editSearchUrl:"role/selectPidByRid",addUrl:"role/insertRole",editUrl:"role/updateRole",deleteUrl:"role/deleteRole",currentPage:0,pagesize:10,pageNum:1,totalNum:0,tableData:[],dialogFormVisible:!1,dialogFormVisibleEdit:!1,dialogFormVisibleDelete:!1,row:"",formLabelWidth:"120px",userId:"",treeList:[],defaultProps:{children:"submenu",label:"text"},rules:{username:[{required:!0,message:"请输入角色名",trigger:"blur"}]}}},created:function(){this.loadData(10,1);var e=this;axios.get(this.selectRolerUrl).then(function(t){e.treeList=t.data.data.submenu}).catch(function(e){console.log(e)})},methods:{onSubmit:function(e){var t=new URLSearchParams,a=this,i="",s="",r="";r=void 0==e.username?"":e.username,""!=e.date1&&(i=Date.parse(e.date1)),""!=e.date2&&(s=Date.parse(e.date2)),t.append("pageSize",this.pagesize),t.append("pageNum",this.currentPage),t.append("roleName",r),t.append("startTime",i),t.append("endTime",s),axios.post(this.url,t).then(function(e){a.$message({message:"查询成功",type:"success"}),a.currentPage=e.data.pageNum,a.pagesize=e.data.pageSize,a.pageNum=e.data.pages,a.totalNum=e.data.total;var t=e.data.list;a.handelData(t),a.tableData=t}).catch(function(e){})},resetForm:function(){this.$refs.form.resetFields(),this.loadData(10,1)},convertIsView:function(e){if("number"==typeof e)return e;var t={};return t=this.isViewList.find(function(t){return t.name==e}),void 0==t?"":t.id},add:function(){this.newform={username:"",isView:0},this.checkeAll=!1;var e=this;this.dialogFormVisible=!0,e.$refs.tree.setCheckedNodes([])},handleCheckAllChange:function(e){var t=this;console.log(t.treeList),e.target.checked?this.$refs.tree.setCheckedNodes(t.treeList):this.$refs.tree.setCheckedNodes([])},handleCheckAllChange1:function(e){var t=this;console.log(t.treeList),e.target.checked?this.$refs.treeEdit.setCheckedNodes(t.treeList):this.$refs.treeEdit.setCheckedNodes([])},confirmAdd:function(e){var t=this;console.log(this.$refs.tree.getCheckedKeys());var a=new URLSearchParams;a.append("name",this.newform.username),a.append("isView",this.newform.isView),a.append("pids",this.$refs.tree.getCheckedKeys(!0));var i=this;this.$refs[e].validate(function(s){s?(t.$refs[e].resetFields(),axios.post(t.addUrl,a).then(function(e){1==e.data.msg?(i.loadData(10,1),i.$message({message:"添加成功",type:"success"}),i.dialogFormVisible=!1):(i.$message({message:"添加失败",type:"warning"}),i.dialogFormVisible=!1)}).catch(function(e){i.dialogFormVisible=!1,console.log(e)})):console.log("error submit!!")})},deleDialog:function(e,t){this.dialogFormVisibleDelete=!0,this.row=t},handleEdit:function(e,t){this.newformEdit={username:t.rname,isView:t.isView},this.userId=t.id,this.dialogFormVisibleEdit=!0;var a=new URLSearchParams;a.append("rid",this.userId);var i=this;axios.post(this.editSearchUrl,a).then(function(e){i.$refs.treeEdit.setCheckedKeys(e.data.pids);for(var t=0,a=0;a<i.treeList.length;a++)if(t++,i.treeList[a].submenu.length>0)for(var s=0;s<i.treeList[a].submenu.length;s++)if(t++,i.treeList[a].submenu[s].submenu.length>0)for(var r=0;r<i.treeList[a].submenu[s].submenu.length;r++)t++;t==e.data.pids.length?i.checkeAll1=!0:i.checkeAll1=!1}).catch(function(e){i.dialogFormVisibleEdit=!1,i.$message.error("网络错误")}),console.log(i.$refs)},confirmAddEdit:function(){var e=new URLSearchParams;e.append("pids",this.$refs.treeEdit.getCheckedKeys()),console.log(this.$refs.treeEdit.getCheckedKeys(!0)),e.append("name",this.newformEdit.username),e.append("isView",this.convertIsView(this.newformEdit.isView)),e.append("id",this.userId);var t=this;axios.post(this.editUrl,e).then(function(e){1==e.data.msg?(t.$message({message:"修改成功",type:"success"}),t.dialogFormVisibleEdit=!1,t.loadData(t.pagesize,1)):0==e.data.msg?(t.$message({message:"修改失败",type:"warning"}),t.dialogFormVisibleEdit=!1):(t.dialogFormVisibleEdit=!1,t.$message.error("网络错误"))}).catch(function(e){t.dialogFormVisibleEdit=!1,t.$message.error("网络错误")})},handleDelete:function(e,t){var a=this,i=new URLSearchParams;i.append("roleId",a.row.id),axios.post(this.deleteUrl,i).then(function(e){1==e.data.msg?(a.$message({message:"删除成功",type:"success"}),a.dialogFormVisibleDelete=!1,a.loadData(a.pagesize,1)):0==e.data.msg?(a.$message({message:"删除失败",type:"warning"}),a.dialogFormVisibleDelete=!1):(a.dialogFormVisibleDelete=!1,a.$message.error(e.data.explain))}).catch(function(e){})},loadData:function(e,t){var a=new URLSearchParams,i="",s="",r="";r=void 0==this.form.username?"":this.form.username,""!=this.form.date1&&(i=Date.parse(this.form.date1)),""!=this.form.date2&&(s=Date.parse(this.form.date2)),this.pagesize=e,this.currentPage=t,a.append("pageSize",this.pagesize),a.append("pageNum",this.currentPage),a.append("roleName",r),a.append("startTime",i),a.append("endTime",s);var l=this;axios.post(this.url,a).then(function(e){l.currentPage=e.data.pageNum,l.pagesize=e.data.pageSize,l.pageNum=e.data.pages,l.totalNum=e.data.total;var t=e.data.list;l.handelData(t),l.tableData=t}).catch(function(e){})},handelData:function(e){if(e.length>0)for(var t=0;t<e.length;t++)"0"==e[t].isView?e[t].isView="否":"1"==e[t].isView&&(e[t].isView="是")},handleCurrentChange:function(e){this.loadData(this.pagesize,e)},handleSizeChange:function(e){this.loadData(e,1)}}}},644:function(e,t,a){t=e.exports=a(136)(void 0),t.push([e.i,"",""])},712:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content"}},[e._m(0,!1,!1),e._v(" "),a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"formBox"},[a("el-form",{ref:"form",attrs:{inline:!0,"demo-form-inline":"",model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"角色名",prop:"username"}},[a("el-input",{attrs:{size:"small"},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"创建时间",prop:"date1"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{prop:"date2"}},[a("el-col",{attrs:{span:10}},[a("el-date-picker",{attrs:{size:"small",type:"datetime",placeholder:"选择日期时间",align:"right"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),e._v(" "),a("br"),e._v("  \n                "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){e.onSubmit(e.form)}}},[e._v("查询\n                    ")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(t){e.resetForm(e.form)}}},[e._v("清除\n                    ")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"success"},on:{click:e.add}},[e._v("新增角色")])],1)],1)],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{attrs:{data:e.tableData,fit:"",border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rname",label:"角色名"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userNames",label:"用户列表"}}),e._v(" "),a("el-table-column",{attrs:{prop:"isView",label:"允许查看用户名",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"rCreateTime",label:"创建时间",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑\n                        ")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){e.deleDialog(t.$index,t.row)}}},[e._v("删除\n                        ")])]}}])})],1)],1),e._v(" "),a("div",{staticClass:"paginationBox"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[10,20,30,40],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-dialog",{attrs:{title:"编辑用户",visible:e.dialogFormVisibleEdit,center:"",width:"30%"},on:{"update:visible":function(t){e.dialogFormVisibleEdit=t}}},[a("el-form",{attrs:{model:e.newformEdit}},[a("el-form-item",{attrs:{label:"用户名","label-width":e.formLabelWidth}},[a("el-input",{attrs:{size:"small","auto-complete":"off"},model:{value:e.newformEdit.username,callback:function(t){e.$set(e.newformEdit,"username",t)},expression:"newformEdit.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"允许查看用户名","label-width":e.formLabelWidth}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:e.newformEdit.isView,callback:function(t){e.$set(e.newformEdit,"isView",t)},expression:"newformEdit.isView"}},e._l(e.isViewList,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"菜单列表","label-width":e.formLabelWidth}},[a("el-checkbox",{on:{change:e.handleCheckAllChange1},model:{value:e.checkeAll1,callback:function(t){e.checkeAll1=t},expression:"checkeAll1"}},[e._v("全选\n                    ")]),e._v(" "),a("el-tree",{ref:"treeEdit",attrs:{data:e.treeList,"show-checkbox":"","node-key":"id","highlight-current":"",props:e.defaultProps}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogFormVisibleEdit=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.confirmAddEdit}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"确认删除？",center:"",width:"30%",visible:e.dialogFormVisibleDelete},on:{"update:visible":function(t){e.dialogFormVisibleDelete=t}}},[a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogFormVisibleDelete=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.handleDelete}},[e._v("确 定")])],1)]),e._v(" "),a("el-dialog",{attrs:{title:"新增角色",visible:e.dialogFormVisible,center:"",width:"30%"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"newform",attrs:{model:e.newform,rules:e.rules}},[a("el-form-item",{attrs:{label:"角色名","label-width":e.formLabelWidth,prop:"username"}},[a("el-input",{attrs:{size:"small","auto-complete":"off"},model:{value:e.newform.username,callback:function(t){e.$set(e.newform,"username",t)},expression:"newform.username"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"允许查看用户名","label-width":e.formLabelWidth}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:e.newform.isView,callback:function(t){e.$set(e.newform,"isView",t)},expression:"newform.isView"}},e._l(e.isViewList,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"菜单列表","label-width":e.formLabelWidth}},[a("el-checkbox",{on:{change:e.handleCheckAllChange},model:{value:e.checkeAll,callback:function(t){e.checkeAll=t},expression:"checkeAll"}},[e._v("全选\n                    ")]),e._v(" "),a("el-tree",{ref:"tree",attrs:{data:e.treeList,"show-checkbox":"","node-key":"id","highlight-current":"",props:e.defaultProps}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){e.confirmAdd("newform")}}},[e._v("确 定\n                ")])],1)],1)],1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),e._v(" 系统管理")]),e._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[e._v("角色管理")])])])}]}},769:function(e,t,a){var i=a(644);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a(199)("2d087008",i,!0)}});