webpackJsonp([90],{lkut:function(t,e,l){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=l("Dd8w"),r=l.n(i),n=l("VsUZ");var a={data:function(){return{list:[],listLoading:!1,listQuery:{businessId:"",name:"",citizenId:"",flowId:""},options:[{value:1,label:"测试1"},{value:2,label:"测试2"},{value:3,label:"测试3"},{value:4,label:"测试4"}]}},computed:{key:function(){},listQueryX:function(){var t=this.listQuery;return r()({},t,{cityid:this.$store.getters.cityidQuery,pageNum:this.$store.getters.pageNum,pageRow:this.$store.getters.pageRow})},showList:function(){return this.$route.name.indexOf("List")>-1},query:function(){return this.getQuery(this.listQueryX)}},watch:{"listQueryX.cityid":"getList","listQueryX.pageNum":"getList","listQueryX.pageRow":function(t,e){1===this.$store.getters.pageNum?this.getList():this.$store.dispatch("TogglePageNum",1)}},created:function(){this.getList()},methods:{handleIntranetCardApply:function(t){var e=t.uuid;this.$router.push({name:"intranetCardApply",params:{cdpfId:e}})},submitForm:function(t){this.getList()},getList:function(){var t,e=this;this.listLoading=!0,(t=this.query,Object(n.a)({url:"/cardForApply/list",method:"post",data:t})).then(function(t){e.listLoading=!1,e.list=t.data.content,e.$store.dispatch("ToggleTotalCount",t.data.totalElements)})},resetForm:function(t){this.$refs[t].resetFields()}}},s={render:function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",[l("router-view"),t._v(" "),t.showList?l("div",{staticClass:"app-container"},[l("el-form",{ref:"query",attrs:{size:"medium",model:t.listQuery,"label-width":"70px"}},[l("el-row",[l("el-col",{attrs:{span:24}},[l("el-form-item",{staticClass:"queryTitle",attrs:{label:"查询条件","label-width":"100px"}})],1)],1),t._v(" "),l("el-row",{attrs:{gutter:25}},[l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{label:"姓名",prop:"name"}},[l("el-input",{model:{value:t.listQuery.name,callback:function(e){t.$set(t.listQuery,"name",e)},expression:"listQuery.name"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:12}},[l("el-form-item",{attrs:{label:"身份证号",prop:"citizenId"}},[l("el-input",{model:{value:t.listQuery.citizenId,callback:function(e){t.$set(t.listQuery,"citizenId",e)},expression:"listQuery.citizenId"}})],1)],1)],1),t._v(" "),l("el-row",{attrs:{gutter:20,type:"flex",justify:"center"}},[l("el-col",{attrs:{span:6}},[l("el-form-item")],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",[l("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){t.submitForm("query")}}},[t._v("查询")])],1)],1),t._v(" "),l("el-col",{attrs:{span:2}},[l("el-form-item",{attrs:{"label-width":"10px"}},[l("el-button",{attrs:{icon:"el-icon-refresh"},on:{click:function(e){t.resetForm("query")}}},[t._v("重置")])],1)],1),t._v(" "),l("el-col",{attrs:{span:10}},[l("el-form-item")],1)],1)],1),t._v(" "),l("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:t.list,"element-loading-text":"加载中",border:"",fit:"","highlight-current-row":""}},[l("el-table-column",{attrs:{align:"center",label:"操作",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-button",{attrs:{type:"text"},on:{click:function(l){t.handleIntranetCardApply(e.row)}}},[t._v("制卡")])]}}])}),t._v(" "),l("el-table-column",{attrs:{align:"center",prop:"name",label:"姓名",width:"130"}}),t._v(" "),l("el-table-column",{attrs:{align:"center",prop:"citizenId",label:"身份证号",width:"170"}}),t._v(" "),l("el-table-column",{attrs:{align:"center",prop:"jiguanCode",label:"户籍地址",width:"220"}}),t._v(" "),l("el-table-column",{attrs:{align:"center",label:"首次批准日期",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[null!=e.row.firstCertDate?l("span",[t._v("\n            "+t._s(e.row.firstCertDate)+"\n          ")]):l("span",[t._v("\n            未发证\n          ")])]}}])}),t._v(" "),l("el-table-column",{attrs:{align:"center",label:"首次发卡日期",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[null!=e.row.firstgivecarddate?l("span",[t._v("\n            "+t._s(e.row.firstgivecarddate)+"\n          ")]):l("span",[t._v("\n            未发卡\n          ")])]}}])})],1)],1):t._e()],1)},staticRenderFns:[]},o=l("VU/8")(a,s,!1,null,null,null);e.default=o.exports}});