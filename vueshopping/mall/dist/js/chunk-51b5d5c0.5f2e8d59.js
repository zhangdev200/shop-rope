(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-51b5d5c0"],{"18cb":function(e,r,t){},"36c0":function(e,r,t){"use strict";t("18cb")},"73cf":function(e,r,t){"use strict";t.r(r);var s=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{attrs:{id:"form"}},[t("h1",[e._v(" 欢 迎 注 册 ")]),t("br"),t("hr"),t("br"),t("br"),t("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,"status-icon":"",rules:e.rules,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"用户名"}},[t("el-input",{attrs:{type:"text"},model:{value:e.ruleForm.username,callback:function(r){e.$set(e.ruleForm,"username",r)},expression:"ruleForm.username"}})],1),t("br"),t("el-form-item",{attrs:{label:"密码",prop:"pass"}},[t("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.ruleForm.pass,callback:function(r){e.$set(e.ruleForm,"pass",r)},expression:"ruleForm.pass"}})],1),t("br"),t("el-form-item",{attrs:{label:"确认密码",prop:"checkPass"}},[t("el-input",{attrs:{type:"password",autocomplete:"off"},nativeOn:{keyup:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.submitForm.apply(null,arguments)}},model:{value:e.ruleForm.checkPass,callback:function(r){e.$set(e.ruleForm,"checkPass",r)},expression:"ruleForm.checkPass"}})],1),t("br"),t("el-form-item",[t("div",{staticStyle:{float:"right",width:"300px"}},[t("div",{staticStyle:{float:"right"}},[t("el-button",{attrs:{round:"",type:"primary"},on:{click:e.submitForm}},[e._v("注册")]),t("el-button",{attrs:{round:""},on:{click:e.resetForm}},[e._v("重置")])],1)])])],1),t("div",{staticStyle:{"font-size":"12px",float:"right"}},[e._v(" 已有账号？立即"),t("el-button",{attrs:{type:"text"},on:{click:e.toLogin}},[e._v("登录")])],1)],1)},o=[],l=(t("ac1f"),t("5319"),{data:function(){var e=this,r=function(r,t,s){""===t?s(new Error("请输入密码")):(""!==e.ruleForm.checkPass&&e.$refs.ruleForm.validateField("checkPass"),s())},t=function(r,t,s){""===t?s(new Error("请再次输入密码")):t!==e.ruleForm.pass?s(new Error("两次输入密码不一致!")):s()};return{ruleForm:{username:"",pass:"",checkPass:""},rules:{pass:[{validator:r,trigger:"blur"}],checkPass:[{validator:t,trigger:"blur"}]}}},methods:{submitForm:function(){var e=this;this.$refs.ruleForm.validate((function(r){if(!r)return!1;e.$http.post("user/regist",{username:e.ruleForm.username,password:e.ruleForm.pass}).then((function(r){e.$message.success(r.msg)})).catch((function(r){return e.$message.error(r)}))}))},resetForm:function(){this.$refs.ruleForm.resetFields()},toLogin:function(){this.$router.replace("login")}}}),a=l,n=(t("36c0"),t("2877")),u=Object(n["a"])(a,s,o,!1,null,"6b7d7cf6",null);r["default"]=u.exports}}]);
//# sourceMappingURL=chunk-51b5d5c0.5f2e8d59.js.map