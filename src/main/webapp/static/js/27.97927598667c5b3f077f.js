webpackJsonp([27],{526:function(t,e,a){a(748);var i=a(196)(a(579),a(700),"data-v-81804f4e",null);t.exports=i.exports},579:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{loading:!0,readoneform:{title:"",imagePath:"",contentPath:""},newform:{skipPath:"",skipType:"",description:"",appID:"",imagePath:"",contentPath:""},newformFirst:{skipPath:"",skipType:"",description:"",appID:"",imagePath:"",contentPath:""},newformEdit:{picturePath:"",imagePath:"",contentPath:"",description:""},readform:{picturePath:""},types:[{id:1,name:"站内"},{id:2,name:"站外"}],url1:"infoBanner/getAdPic1",url2:"infoBanner/getAdPic2",addUrl:"infoBanner/insertBanner",editUrl:"infoBanner/edit",deleteUrl:"infoBanner/delete",upOrDown:"infoBanner/upDown",tableData1:[],tableData2:[],dialogFormVisibleRead:!1,dialogFormVisible:!1,dialogFormVisibleAdd:!1,dialogFormVisibleEdit:!1,dialogFormVisibleDelete:!1,dialogFormVisibleAddFirstPage:!1,formLabelWidth:"120px",imagepath:"",contentpath:""}},created:function(){this.loadData1(),this.loadData2()},methods:{loadData1:function(){var t=new URLSearchParams;t.append("page",3);var e=this;axios.post(this.url1,t).then(function(t){e.tableData1=t.data}).catch(function(t){})},loadData2:function(){var t=new URLSearchParams;t.append("page",1);var e=this;axios.post(this.url2,t).then(function(t){e.tableData2=t.data}).catch(function(t){})},readOne:function(t,e){console.log(e.PicturePath),this.readform={picturePath:e.PicturePath},this.dialogFormVisibleRead=!0},add:function(){this.newform={title:"",contentPath:"",imagePath:"",skipType:""},this.dialogFormVisibleAdd=!0},confirmAdd:function(){var t=new URLSearchParams;t.append("picturePath",this.newform.imagePath),t.append("skipPath",this.newform.skipPath),t.append("skipType",this.newform.skipType),t.append("appID",this.newform.appID),t.append("description",this.newform.description),t.append("page","3"),t.append("valid","1");var e=this;axios.post(this.addUrl,t).then(function(t){1==t.data.data?(e.$message({message:"添加成功",type:"success"}),e.dialogFormVisibleAdd=!1,e.loadData1(),e.loadData2()):-1==t.data.data?(e.$message({message:"账户已存在",type:"warning"}),e.dialogFormVisible=!1):(e.$message.error("网络错误"),e.dialogFormVisible=!1)})},addFirstPage:function(){this.newformFirst={picturePath:"",skipPath:"",appID:"",description:"",skipType:""},this.dialogFormVisibleAddFirstPage=!0},confirmAddFirstPage:function(){var t=new URLSearchParams;t.append("picturePath",this.newformFirst.imagePath),t.append("skipPath",this.newformFirst.skipPath),t.append("appID",this.newformFirst.appID),t.append("skipType",this.newformFirst.skipType),t.append("description",this.newformFirst.description),t.append("page","1"),t.append("valid","1");var e=this;axios.post(this.addUrl,t).then(function(t){1==t.data.data?(e.$message({message:"添加成功",type:"success"}),e.dialogFormVisibleAddFirstPage=!1,e.loadData1(),e.loadData2()):-1==t.data.data?(e.$message({message:"账户已存在",type:"warning"}),e.dialogFormVisible=!1):(e.$message.error("网络错误"),e.dialogFormVisibleAddFirstPage=!1)})},deleteDialog:function(t,e){this.dialogFormVisibleDelete=!0,this.row=e},handleDelete:function(){var t=this,e=new URLSearchParams;e.append("serialNo",t.row.SerialNo),axios.post(this.deleteUrl,e).then(function(e){e.data.data?(t.$message({message:"删除成功",type:"success"}),t.dialogFormVisibleDelete=!1,t.loadData1(),t.loadData2()):e.data.data?(t.dialogFormVisibleEdit=!1,t.$message.error("网络错误")):(t.$message({message:"删除失败",type:"warning"}),t.dialogFormVisibleEdit=!1)}).catch(function(t){})},goUp:function(t,e){var a=this,i=new URLSearchParams;i.append("upSortNo",this.tableData1[t-1].SortNo),i.append("downSortNo",e.SortNo),axios.post(this.upOrDown,i).then(function(t){t.data.data?(a.$message({message:"调整成功",type:"success"}),a.loadData1(),a.loadData2(),a.dialogFormVisibleEdit=!1):t.data.data?(a.dialogFormVisibleEdit=!1,a.$message.error("网络错误")):(a.$message({message:"调整失败",type:"warning"}),a.dialogFormVisibleEdit=!1)}).catch(function(t){})},goDown:function(t,e){var a=this,i=new URLSearchParams;i.append("upSortNo",e.SortNo),i.append("downSortNo",this.tableData1[t+1].SortNo),axios.post(this.upOrDown,i).then(function(t){t.data.data?(a.$message({message:"调整成功",type:"success"}),a.dialogFormVisibleEdit=!1,a.loadData1(),a.loadData2()):t.data.data?(a.dialogFormVisibleEdit=!1,a.$message.error("网络错误")):(a.$message({message:"调整失败",type:"warning"}),a.dialogFormVisibleEdit=!1)}).catch(function(t){})},handleEdit:function(t,e){this.newformEdit={picturePath:e.PicturePath,skipPath:e.SkipPath,skipType:e.SkipType,appID:e.AppID,description:e.Description},this.sortNo=e.SortNo,this.dialogFormVisibleEdit=!0},confirmAddEdit:function(){var t=new URLSearchParams;t.append("picturePath",this.newformEdit.picturePath),t.append("skipPath",this.newformEdit.skipPath),t.append("appID",this.newformEdit.appID),t.append("skipType",this.newformEdit.skipType),t.append("description",this.newformEdit.description),t.append("sortNo",this.sortNo);var e=this;axios.post(this.editUrl,t).then(function(t){t.data.data?(e.$message({message:"修改成功",type:"success"}),e.dialogFormVisibleEdit=!1,e.loadData1(),e.loadData2()):t.data.data?(e.dialogFormVisibleEdit=!1,e.$message.error("网络错误")):(e.$message({message:"修改失败",type:"warning"}),e.dialogFormVisibleEdit=!1)}).catch(function(t){e.dialogFormVisibleEdit=!1,e.$message.error("网络错误")})}}}},641:function(t,e,a){e=t.exports=a(135)(void 0),e.push([t.i,"",""])},700:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content"}},[t._m(0,!1,!1),t._v(" "),a("div",{staticClass:"container-fluid"},[t._v("\n        活动区域入口图\n        "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData1,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"顺序",width:""}}),t._v(" "),a("el-table-column",{attrs:{label:"图片",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.readOne(e.$index,e.row)}}},[t._v("查看")])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"跳转网址",prop:"SkipPath",width:""}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:""},scopedSlots:t._u([{key:"default",fn:function(e){return[-1!=e.row.SerialNo?[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.goUp(e.$index,e.row)}}},[t._v("向上")]),t._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){t.goDown(e.$index,e.row)}}},[t._v("向下")]),t._v(" "),a("el-button",{attrs:{size:"small"},on:{click:function(a){t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){t.deleteDialog(e.$index,e.row)}}},[t._v("删除")])]:t._e(),t._v(" "),-1==e.row.SerialNo?[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){t.add(e.$index,e.row)}}},[t._v("添加")])]:t._e()]}}])})],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"添加",visible:t.dialogFormVisibleAdd},on:{"update:visible":function(e){t.dialogFormVisibleAdd=e}}},[a("el-form",{attrs:{model:t.newform}},[a("el-form-item",{attrs:{label:"图片路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newform.imagePath,callback:function(e){t.$set(t.newform,"imagePath",e)},expression:"newform.imagePath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入跳转路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newform.skipPath,callback:function(e){t.$set(t.newform,"skipPath",e)},expression:"newform.skipPath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":t.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.newform.skipType,callback:function(e){t.$set(t.newform,"skipType",e)},expression:"newform.skipType"}},t._l(t.types,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"输入AppID","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newform.appID,callback:function(e){t.$set(t.newform,"appID",e)},expression:"newform.appID"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入描述","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newform.description,callback:function(e){t.$set(t.newform,"description",e)},expression:"newform.description"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleAdd=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.confirmAdd}},[t._v("确 定")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"编辑",visible:t.dialogFormVisibleEdit},on:{"update:visible":function(e){t.dialogFormVisibleEdit=e}}},[a("el-form",{attrs:{model:t.newformEdit}},[a("el-form-item",{attrs:{label:"图片路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.picturePath,callback:function(e){t.$set(t.newformEdit,"picturePath",e)},expression:"newformEdit.picturePath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入跳转路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.skipPath,callback:function(e){t.$set(t.newformEdit,"skipPath",e)},expression:"newformEdit.skipPath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":t.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.newformEdit.skipType,callback:function(e){t.$set(t.newformEdit,"skipType",e)},expression:"newformEdit.skipType"}},t._l(t.types,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"输入AppID","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.appID,callback:function(e){t.$set(t.newformEdit,"appID",e)},expression:"newformEdit.appID"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入描述","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.description,callback:function(e){t.$set(t.newformEdit,"description",e)},expression:"newformEdit.description"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleEdit=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.confirmAddEdit}},[t._v("确 定")])],1)],1)],1)]),t._v(" "),a("div",{staticClass:"container-fluid"},[t._v("\n        首页弹出图（默认所有人弹出）\n        "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData2,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"顺序",width:""}}),t._v(" "),a("el-table-column",{attrs:{label:"图片",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.readOne(e.$index,e.row)}}},[t._v("查看")])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"跳转网址",prop:"SkipPath",width:""}}),t._v(" "),a("el-table-column",{attrs:{label:"生效时间",prop:"CreateTime",width:""}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:""},scopedSlots:t._u([{key:"default",fn:function(e){return[-1!=e.row.SerialNo?[a("el-button",{attrs:{size:"small"},on:{click:function(a){t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){t.deleteDialog(e.$index,e.row)}}},[t._v("删除")])]:t._e(),t._v(" "),-1==e.row.SerialNo?[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){t.addFirstPage(e.$index,e.row)}}},[t._v("添加")])]:t._e()]}}])})],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"添加",visible:t.dialogFormVisibleAddFirstPage},on:{"update:visible":function(e){t.dialogFormVisibleAddFirstPage=e}}},[a("el-form",{attrs:{model:t.newform}},[a("el-form-item",{attrs:{label:"图片路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformFirst.imagePath,callback:function(e){t.$set(t.newformFirst,"imagePath",e)},expression:"newformFirst.imagePath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入跳转路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformFirst.skipPath,callback:function(e){t.$set(t.newformFirst,"skipPath",e)},expression:"newformFirst.skipPath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":t.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.newformFirst.skipType,callback:function(e){t.$set(t.newformFirst,"skipType",e)},expression:"newformFirst.skipType"}},t._l(t.types,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"输入AppID","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformFirst.appID,callback:function(e){t.$set(t.newformFirst,"appID",e)},expression:"newformFirst.appID"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入描述","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformFirst.description,callback:function(e){t.$set(t.newformFirst,"description",e)},expression:"newformFirst.description"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleAddFirstPage=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.confirmAddFirstPage}},[t._v("确 定")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"编辑",visible:t.dialogFormVisibleEdit},on:{"update:visible":function(e){t.dialogFormVisibleEdit=e}}},[a("el-form",{attrs:{model:t.newformEdit}},[a("el-form-item",{attrs:{label:"图片路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.picturePath,callback:function(e){t.$set(t.newformEdit,"picturePath",e)},expression:"newformEdit.picturePath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入跳转路径","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.skipPath,callback:function(e){t.$set(t.newformEdit,"skipPath",e)},expression:"newformEdit.skipPath"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"跳转类型","label-width":t.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.newformEdit.skipType,callback:function(e){t.$set(t.newformEdit,"skipType",e)},expression:"newformEdit.skipType"}},t._l(t.types,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"输入AppID","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.appID,callback:function(e){t.$set(t.newformEdit,"appID",e)},expression:"newformEdit.appID"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"输入描述","label-width":t.formLabelWidth}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:t.newformEdit.description,callback:function(e){t.$set(t.newformEdit,"description",e)},expression:"newformEdit.description"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibleEdit=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.confirmAddEdit}},[t._v("确 定")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-dialog",{attrs:{title:"查看",visible:t.dialogFormVisibleRead},on:{"update:visible":function(e){t.dialogFormVisibleRead=e}}},[a("el-form",{attrs:{model:t.readform}},[a("img",{attrs:{src:t.readform.picturePath}})])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"确认删除？",center:"",width:"300px",visible:t.dialogFormVisibleDelete},on:{"update:visible":function(e){t.dialogFormVisibleDelete=e}}},[a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:6}},[a("el-button",{on:{click:function(e){t.dialogFormVisibleDelete=!1}}},[t._v("取 消")])],1),t._v(" "),a("el-col",{attrs:{span:6,offset:6}},[a("el-button",{attrs:{type:"primary"},on:{click:t.handleDelete}},[t._v("确 定")])],1)],1)],1)])],1)])},staticRenderFns:[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"content-header"}},[a("div",{attrs:{id:"breadcrumb"}},[a("a",{staticClass:"tip-bottom",attrs:{href:"javascript:;",title:"Go to Home"}},[a("i",{staticClass:"icon-home"}),t._v(" 运营管理")]),t._v(" "),a("a",{staticClass:"current",attrs:{href:"javascript:;"}},[t._v("活动管理")])]),t._v(" "),a("h1")])}]}},748:function(t,e,a){var i=a(641);"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);a(197)("f4378aa0",i,!0)}});