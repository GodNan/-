webpackJsonp([62],{"8PSe":function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=o("Dd8w"),n=o.n(l),a=o("NYxO"),i={name:"Logout",props:{idt:{type:Boolean,default:!1},info:{type:Boolean,default:!1}},data:function(){return{isLoading:!1,disabled:this.info}},computed:n()({},Object(a.b)(["dict","kindstr"]),{logoutReason:function(){return parseInt(this.kindstr,10)}})},s={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("el-card",[o("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.isLoading,expression:"isLoading"}],ref:"logout",attrs:{"label-width":"150px",size:"medium","element-loading-text":"加载中"}},[o("el-row",[o("el-col",{attrs:{span:12}},[o("el-form-item",{attrs:{label:"注销原因:",prop:"logoutReason"}},[o("el-select",{attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.logoutReason,callback:function(t){e.logoutReason=t},expression:"logoutReason"}},e._l(e.dict.logoutReasonList,function(e){return o("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1)],1)],1)},staticRenderFns:[]},r=o("VU/8")(i,s,!1,null,null,null);t.default=r.exports}});