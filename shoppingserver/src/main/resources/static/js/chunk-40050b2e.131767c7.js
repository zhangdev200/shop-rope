(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-40050b2e"],{aa84:function(t,e,a){},c1fa:function(t,e,a){"use strict";a("aa84")},fd6f:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"carousel"}},[a("div",{staticStyle:{height:"30px"}},[a("el-button",{staticStyle:{float:"left","margin-bottom":"20px"},attrs:{type:"primary",round:""},on:{click:t.add}},[t._v(" 添加轮播图 ")])],1),a("el-table",{staticStyle:{width:"100%","font-size":"16px","border-radius":"15px","box-shadow":"0 2px 12px 0 rgba(0, 0, 0, 0.3)"},attrs:{data:t.tableData,border:"",stripe:"","empty-text":"暂无数据"}},[a("el-table-column",{attrs:{label:"商品 ID",prop:"prodId"}}),a("el-table-column",{attrs:{label:"图片",prop:"imgUrl"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-popover",{attrs:{trigger:"hover",placement:"right-end","close-delay":0}},[a("img",{attrs:{src:e.row.imgUrl,alt:"加载失败"}}),a("div",{staticClass:"name-wrapper",staticStyle:{width:"70px"},attrs:{slot:"reference"},slot:"reference"},[a("el-tag",{attrs:{size:"medium"}},[t._v("查看图片")])],1)])]}}])}),a("el-table-column",{attrs:{label:"操作",width:"200",align:"center"}},[a("el-button",{attrs:{type:"danger",round:""}},[t._v("删除")])],1)],1),a("el-dialog",{attrs:{visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e},close:t.closeDialog}},[a("el-form",{staticStyle:{"text-align":"left"},attrs:{model:t.form}},[a("el-form-item",{attrs:{label:"商品id","label-width":t.formLabelWidth}},[a("el-input",{staticClass:"inputWidth",attrs:{autocomplete:"off"},model:{value:t.form.productName,callback:function(e){t.$set(t.form,"productName",e)},expression:"form.productName"}})],1),a("el-form-item",{attrs:{label:"商品图片","label-width":t.formLabelWidth}},[a("el-upload",{ref:"upload",staticClass:"upload-demo",staticStyle:{width:"300px"},attrs:{action:"","before-upload":t.beforeUpload,limit:1,"list-type":"picture","auto-upload":!1}},[a("el-button",{attrs:{slot:"trigger",size:"small",type:"primary"},slot:"trigger"},[t._v("选取文件")]),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("图片比例为1:1")])],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("确 定")])],1)],1)],1)},l=[],i={name:"SystemCarousel",data:function(){return{form:{productId:1},tableData:[],dialogFormVisible:!1,formLabelWidth:"120px"}},methods:{add:function(){this.dialogFormVisible=!0},beforeUpload:function(t){var e=this,a=new FormData;return a.append("file",t),a.append("productId","1"),this.$http.post("file/indexImg",a).then((function(t){alert(e.form.productId),1e4===t.code?e.$message.success("操作成功！"):e.$message.error(t.msg)})),!1},submitForm:function(){this.$refs.upload.submit(),this.dialogFormVisible=!1},closeDialog:function(){this.form={productId:null}}},created:function(){var t=this;this.$http.get("/index/indeximg").then((function(e){1e4===e.code&&(t.tableData=e.data)}))}},r=i,s=(a("c1fa"),a("2877")),d=Object(s["a"])(r,o,l,!1,null,"38d916c4",null);e["default"]=d.exports}}]);
//# sourceMappingURL=chunk-40050b2e.131767c7.js.map