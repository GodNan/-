webpackJsonp([92],{KwEl:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("Dd8w"),l=a.n(n),r=a("GKtG"),i=a("1onU"),o={name:"CardLogs",props:{mark:{type:String,default:""}},components:{Pagination:i.a},data:function(){return{list:[],listLoading:!1,loaded:!1,listQuery:{requestId:this.mark}}},computed:{listQueryX:function(){var t=this.listQuery;return l()({},t,{pageNum:this.$store.getters.pageNum,pageRow:this.$store.getters.pageRow})},query:function(){return this.getQuery(this.listQueryX)}},watch:{"listQueryX.pageNum":"getIdtInfo","listQueryX.pageRow":function(t,e){1===this.$store.getters.pageNum?this.getIdtInfo():this.$store.dispatch("TogglePageNum",1)}},methods:{getIdtInfo:function(){var t=this;this.listLoading=!0,Object(r.i)(this.query).then(function(e){"success"===e.type&&(t.loaded=!0,t.listLoading=!1,t.list=e.data.content,t.$store.dispatch("ToggleTotalCount",e.data.totalElements))})}}},s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:t.list,"element-loading-text":"加载中",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{type:"index",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{width:"180",align:"center",prop:"handleDate",label:"处理时间"}}),t._v(" "),a("el-table-column",{attrs:{width:"130",align:"center",prop:"pcno",label:"批次号"}}),t._v(" "),a("el-table-column",{attrs:{width:"140",align:"center",prop:"cardStatus",label:"制卡状态",formatter:t.formatterCardStatus}}),t._v(" "),a("el-table-column",{attrs:{width:"130",align:"center",prop:"opername",label:"处理人"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"logdata",label:"操作记录"}})],1),t._v(" "),a("pagination")],1)},staticRenderFns:[]},d=a("VU/8")(o,s,!1,null,null,null);e.default=d.exports}});