webpackJsonp([42,61],{n0BQ:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("Dd8w"),r=s.n(a),o=s("NYxO"),i=s("rLcC"),n=s("p/d9"),l=s("5u+E"),d=s("zHhc"),u=s("jSlZ"),c=s("Q3je"),p={components:{Citizen:i.default,LogoutResume:n.default,TasksLog:l.default,OperLog:d.default},data:function(){var e=this;return{isLoading:!1,dialogVisible:!1,activeStep:0,activeName:"citizen",steps:[],requestNote:{requestId:this.$route.params.requestId,userId:this.$store.getters.userId,userName:this.$store.getters.nickname,note:"",handleState:this.$store.getters.handleState,finishState:0,handleType:"",backState:""},requestStakeholder:{requestId:this.$route.params.requestId,userId:"",cityid:this.$store.getters.cityid+"%",permissionId:"",delFlag:0},dtoState:{processId:this.$store.getters.processId,stateType:3,stateCode:0},rules:{handleType:[{required:!0,message:"请选择处理结果",trigger:"change"}],note:[{validator:function(t,s,a){""!==e.requestNote.handleType&&1!==e.requestNote.handleType&&""===s&&a(new Error("请填写处理意见!")),a()},trigger:"blur"}]}}},computed:r()({},Object(o.b)(["dict"])),watch:{steps:function(e){var t=this;e.length>0&&(this.activeStep=this.steps.find(function(e){return e.stateCode===t.$store.getters.handleState}).orderNum-1)},"requestNote.handleType":function(e){var t=this;1===e?(this.requestStakeholder.delFlag=0,this.activeStep===this.steps.length-1?(this.requestNote.finishState=this.requestNote.handleState,this.requestStakeholder.delFlag=1,this.requestStakeholder.permissionId="nonebdpfnew"):(this.requestStakeholder.delFlag=0,this.requestNote.finishState=this.steps.find(function(e){return e.orderNum===t.activeStep+2}).stateCode,this.requestStakeholder.permissionId=this.steps.find(function(e){return e.stateCode===t.requestNote.finishState}).permissionId)):3===e?(this.requestNote.finishState=99,this.requestStakeholder.delFlag=1,this.requestStakeholder.permissionId="nonebdpfnew"):this.requestStakeholder.delFlag=0}},created:function(){this.getSteps()},methods:{submit:function(){var e=this;this.$refs.handle.validate(function(t){if(!t)return!1;3===e.requestNote.handleType?confirm('选择"不通过"这个流程将会结束,是否确定操作?')&&e.handleBusiness():e.handleBusiness()})},handleBusiness:function(){var e=this,t={requestNote:this.requestNote,requestStakeholder:this.requestStakeholder,processId:this.$store.getters.processId};Object(c.b)(t).then(function(t){e.$router.push({name:"tasksTodoList"})})},handleClick:function(e,t){"taskslog"===e.name&&this.$refs.taskslog.getIdtInfo(),"operlog"===e.name&&this.$refs.idtoperlog.getIdtInfo()},getSteps:function(){var e=this;this.isLoading=!0,this.dtoState.stateType=3,Object(u.c)(this.dtoState).then(function(t){e.isLoading=!1,e.steps=t.data.sort(function(e,t){return e.orderNum-t.orderNum})})},getBackSteps:function(){var e=this;this.dtoState.stateType=1,this.dtoState.stateCode=this.steps.find(function(t){return t.orderNum===e.activeStep+1}).stateCode,Object(u.b)(this.dtoState).then(function(t){e.backSteps=t.data.filter(function(e){return e.orderNum>1})})}}},h={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"app-container"},[this.$route.params.handle?s("el-card",[s("el-form",{directives:[{name:"loading",rawName:"v-loading.body",value:e.isLoading,expression:"isLoading",modifiers:{body:!0}}],ref:"handle",attrs:{model:e.requestNote,rules:e.rules,size:"medium","label-position":"left","label-width":"85px","element-loading-text":"加载中"}},[s("el-row",[s("el-col",[s("el-form-item",{attrs:{label:"当前流程信息:","label-width":"170px"}},[s("el-steps",{attrs:{active:e.activeStep}},e._l(e.steps,function(e){return s("el-step",{key:e.stateCode,attrs:{title:e.name,icon:e.icon}})}))],1)],1)],1),e._v(" "),s("el-row",[s("el-col",{attrs:{span:8}},[s("el-form-item",{attrs:{label:"处理结果:",prop:"handleType"}},[s("el-radio-group",{model:{value:e.requestNote.handleType,callback:function(t){e.$set(e.requestNote,"handleType",t)},expression:"requestNote.handleType"}},[s("el-radio",{attrs:{label:1}},[e._v("通过")]),e._v(" "),s("el-radio",{attrs:{label:3}},[e._v("不通过")])],1)],1)],1)],1),e._v(" "),s("el-row",[s("el-col",{attrs:{span:12}},[s("el-form-item",{attrs:{label:"处理意见:",prop:"note"}},[s("el-input",{attrs:{type:"textarea"},model:{value:e.requestNote.note,callback:function(t){e.$set(e.requestNote,"note",t)},expression:"requestNote.note"}})],1)],1)],1),e._v(" "),s("el-row",[s("el-col",[s("el-form-item",{attrs:{"label-width":"0"}},[s("el-button",{attrs:{type:"primary",icon:"el-icon-success"},on:{click:e.submit}},[e._v("提交")])],1)],1)],1)],1)],1):e._e(),e._v(" "),s("el-tabs",{attrs:{type:"border-card"},on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"基本信息",name:"citizen"}},[s("citizen",{ref:"idtcitizen",attrs:{idt:"",info:"",mark:this.$route.params.cdpfId}})],1),e._v(" "),s("el-tab-pane",{attrs:{label:"注销恢复原因",name:"logoutresume"}},[s("logout-resume",{ref:"idtlogoutresume",attrs:{info:""}})],1),e._v(" "),s("el-tab-pane",{attrs:{label:"任务历史",name:"taskslog"}},[s("tasks-log",{ref:"taskslog",attrs:{mark:this.$route.params.requestId}})],1),e._v(" "),s("el-tab-pane",{attrs:{label:"操作历史",name:"operlog"}},[s("oper-log",{ref:"operlog",attrs:{mark:this.$route.params.cdpfId}})],1)],1)],1)},staticRenderFns:[]},m=s("VU/8")(p,h,!1,null,null,null);t.default=m.exports},"p/d9":function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("Dd8w"),r=s.n(a),o=s("NYxO"),i={name:"LogoutResume",props:{idt:{type:Boolean,default:!1},info:{type:Boolean,default:!1}},data:function(){return{isLoading:!1,disabled:this.info}},computed:r()({},Object(o.b)(["dict","kindstr"]),{logoutResumeReason:function(){return parseInt(this.kindstr,10)}}),created:function(){this.getDict("d_logout_resume_reason","logoutResumeReasonList")}},n={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("el-card",[s("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.isLoading,expression:"isLoading"}],ref:"logout",attrs:{"label-width":"150px",size:"medium","element-loading-text":"加载中"}},[s("el-row",[s("el-col",{attrs:{span:12}},[s("el-form-item",{attrs:{label:"注销恢复原因:",prop:"logoutReason"}},[s("el-select",{attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.logoutResumeReason,callback:function(t){e.logoutResumeReason=t},expression:"logoutResumeReason"}},e._l(e.dict.logoutResumeReasonList,function(e){return s("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1)],1)],1)},staticRenderFns:[]},l=s("VU/8")(i,n,!1,null,null,null);t.default=l.exports}});