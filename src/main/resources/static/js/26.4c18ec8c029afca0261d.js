webpackJsonp([26],{"OV+8":function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=l("Dd8w"),r=l.n(i),a=l("NYxO"),s=l("7+PW"),n={data:function(){return{list:[],listLoading:!1,btnLoading:!1,listQuery:{isInclude:!1,citizenId:"",name:"",idtKind:"",idtLevel:"",gender:"",eduLevel:"",race:"",marriager:"",newType:"",hukouKind:"",political:"",dataFrom:"",idtType:"",firstCertDateStart:"",firstCertDateEnd:"",currentState:"",startDate:"",endDate:""}}},computed:r()({},Object(a.b)(["dict"]),{listQueryX:function(){var e=this.listQuery;return r()({},e,{cityid:this.$store.getters.cityidQuery,pageNum:this.$store.getters.pageNum,pageRow:this.$store.getters.pageRow})},showList:function(){return this.$route.name.indexOf("List")>-1},query:function(){return this.getQuery(this.listQueryX)}}),watch:{"listQueryX.cityid":"getList","listQueryX.pageNum":"getList","listQueryX.pageRow":function(e,t){1===this.$store.getters.pageNum?this.getList():this.$store.dispatch("TogglePageNum",1)}},created:function(){this.getList()},methods:{handleBusiness:function(e){this.$store.dispatch("ToggleKindstr",e.kindstr),this.$store.dispatch("ToggleHandleState",30);var t=null!=e.requestIdFinished?e.requestIdFinished:e.requestId,l=e.uuid,i=e.requestId;this.$router.push({name:"documentsBusiness",params:{requestId:i,requestIdFinished:t,cdpfId:l}})},submitForm:function(e){this.getList()},getList:function(){var e=this;this.listLoading=!0,Object(s.d)(this.listQueryX).then(function(t){e.listLoading=!1,e.list=t.data.content,e.$store.dispatch("ToggleTotalCount",t.data.totalElements)})},resetForm:function(e){this.$refs[e].resetFields()},exportForm:function(e){var t=this;this.$message("导出数据较大时时间比较长,请耐心等待!."),this.btnLoading=!0,Object(s.g)(this.listQueryX).then(function(e){t.btnLoading=!1,window.open(e.data)})}}},o={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",[l("router-view"),e._v(" "),e.showList?l("div",{staticClass:"app-container"},[l("el-form",{ref:"query",attrs:{size:"medium",model:e.listQuery,"label-width":"100px"}},[l("el-row",[l("el-col",{attrs:{span:24}},[l("el-form-item",{staticClass:"queryTitle",attrs:{label:"查询条件"}})],1)],1),e._v(" "),l("el-row",[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"身份证号",prop:"citizenId"}},[l("el-input",{model:{value:e.listQuery.citizenId,callback:function(t){e.$set(e.listQuery,"citizenId",t)},expression:"listQuery.citizenId"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"姓名",prop:"name"}},[l("el-input",{model:{value:e.listQuery.name,callback:function(t){e.$set(e.listQuery,"name",t)},expression:"listQuery.name"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"残疾类别",prop:"idtKind"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.idtKind,callback:function(t){e.$set(e.listQuery,"idtKind",t)},expression:"listQuery.idtKind"}},e._l(e.dict.disKindList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"包含多重"}},[l("el-checkbox",{key:"true",attrs:{disabled:""==e.listQuery.idtKind,label:"是",border:"",size:"medium"},model:{value:e.listQuery.isInclude,callback:function(t){e.$set(e.listQuery,"isInclude",t)},expression:"listQuery.isInclude"}})],1)],1)],1),e._v(" "),l("el-row",[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"残疾等级",prop:"idtLevel"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.idtLevel,callback:function(t){e.$set(e.listQuery,"idtLevel",t)},expression:"listQuery.idtLevel"}},e._l(e.dict.disLevelList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"性别",prop:"gender"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.gender,callback:function(t){e.$set(e.listQuery,"gender",t)},expression:"listQuery.gender"}},e._l(e.dict.guardianGenderList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"文化程度",prop:"eduLevel"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.eduLevel,callback:function(t){e.$set(e.listQuery,"eduLevel",t)},expression:"listQuery.eduLevel"}},e._l(e.dict.eduLevelList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"民族",prop:"race"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.race,callback:function(t){e.$set(e.listQuery,"race",t)},expression:"listQuery.race"}},e._l(e.dict.raceList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1),e._v(" "),l("el-row",[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"婚姻状况",prop:"marriager"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.marriager,callback:function(t){e.$set(e.listQuery,"marriager",t)},expression:"listQuery.marriager"}},e._l(e.dict.marriagerList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"户口类别",prop:"hukouKind"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.hukouKind,callback:function(t){e.$set(e.listQuery,"hukouKind",t)},expression:"listQuery.hukouKind"}},e._l(e.dict.hukouKindList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"政治面貌",prop:"political"}},[l("el-select",{staticStyle:{width:"100%"},attrs:{clearable:"",placeholder:"请选择"},model:{value:e.listQuery.political,callback:function(t){e.$set(e.listQuery,"political",t)},expression:"listQuery.political"}},e._l(e.dict.politicalList,function(e){return l("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1),e._v(" "),l("el-row",[l("el-col",{attrs:{span:8}},[l("el-form-item",{attrs:{label:"起始时间"}},[l("el-date-picker",{attrs:{type:"date",format:"yyyy-MM-dd",placeholder:"起始时间"},model:{value:e.listQuery.firstCertDateStart,callback:function(t){e.$set(e.listQuery,"firstCertDateStart",t)},expression:"listQuery.firstCertDateStart"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:8}},[l("el-form-item",{attrs:{label:"结束时间"}},[l("el-date-picker",{attrs:{type:"date",format:"yyyy-MM-dd",placeholder:"结束时间"},model:{value:e.listQuery.firstCertDateEnd,callback:function(t){e.$set(e.listQuery,"firstCertDateEnd",t)},expression:"listQuery.firstCertDateEnd"}})],1)],1)],1),e._v(" "),l("el-row",[l("el-col",{attrs:{span:5}},[l("el-form-item")],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{"label-width":"10px"}},[l("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(t){e.submitForm("query")}}},[e._v("查询")])],1)],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{"label-width":"10px"}},[l("el-button",{attrs:{icon:"el-icon-refresh"},on:{click:function(t){e.resetForm("query")}}},[e._v("重置")])],1)],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{"label-width":"10px"}},[l("el-button",{attrs:{icon:"el-icon-refresh",loading:e.btnLoading},on:{click:function(t){e.exportForm("query")}}},[e._v("导出")])],1)],1),e._v(" "),l("el-col",{attrs:{span:5}},[l("el-form-item")],1)],1)],1),e._v(" "),l("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:e.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:e.list,"element-loading-text":"加载中",border:"",fit:"","highlight-current-row":""}},[l("el-table-column",{attrs:{align:"center",label:"操作",width:"85"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{type:"text"},on:{click:function(l){e.handleBusiness(t.row)}}},[e._v("查看")])]}}])}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"name",label:"姓名",width:"110"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"cardNum",label:"残疾证号",width:"150"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"gender",label:"性别",width:"85",formatter:e.genderFormatter}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"idtKind",label:"残疾类别",width:"85",formatter:e.idtKindFormatter}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"idtLevel",label:"残疾等级",width:"85",formatter:e.idtLevelFormatter}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"cityidAddrstr",label:"地区",width:"150"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"bankNo",label:"银行卡号"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"jiguanCode",label:"户籍地",width:"200"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"cityidAddrstr",label:"户籍地地址",width:"200"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"residentAdd",label:"居住地",width:"200"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"residentcityAddrstr",label:"居住地地址",width:"200"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"phoneNo",label:"固定电话",width:"150"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"mobilePhone",label:"移动电话",width:"150"}}),e._v(" "),l("el-table-column",{attrs:{align:"center",prop:"workKind",label:"档案号"}})],1)],1):e._e()],1)},staticRenderFns:[]};var c=l("VU/8")(n,o,!1,function(e){l("qjMR")},null,null);t.default=c.exports},S4aH:function(e,t,l){(e.exports=l("FZ+f")(!0)).push([e.i,"\n.el-col {\n  border-radius: 4px;\n}\n","",{version:3,sources:["D:/JAVA_WORKSPACE/idea/vue-bdpfnew/src/views/query/documents.vue"],names:[],mappings:";AACA;EACE,mBAAmB;CACpB",file:"documents.vue",sourcesContent:["\n.el-col {\n  border-radius: 4px;\n}\n"],sourceRoot:""}])},qjMR:function(e,t,l){var i=l("S4aH");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);l("rjj0")("16b344bc",i,!0)}});