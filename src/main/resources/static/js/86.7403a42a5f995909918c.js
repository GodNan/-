webpackJsonp([86],{tAy4:function(a,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=s("rLcC"),i=s("yk4z"),t={components:{Citizen:r.default},computed:{},data:function(){return{cdpfId:this.$route.params.cdpfId,activeName:"citizen",cardGiveParams:{unit:"75113001",Mchnt:"003080000001",pos:"1234567890",opr:"000001",ip_addr:"172.30.0.61",serverport:"5001",sLossData:"",sReleaseData:"",sYearInfo:"",sInfoData:"",sNewCardInfo:"",sNewInfo:"",cPersonInfo:"",cPhotoInfo:"",citizenId:this.$route.params.citizenId,requestIdCard:this.$route.params.requestIdCard,oldCardNo:"",cardBusiness:"",cardmakestate:"",cardnoCheck:"",cardNoFlagT:"",newCardNo:"",check260:"0"}}},watch:{},created:function(){this.getCard(),this.sInfoData(),this.sYearInfo(),this.sLossData(),this.sReleaseData(),this.sNewCardInfo(),this.sNewInfo(),this.cPhotoInfo(),this.cPersonInfo()},methods:{getCard:function(){var a=this;Object(i.k)(this.cardGiveParams.requestIdCard).then(function(e){a.cardGiveParams.cardBusiness=e.data.cardBusiness,a.cardGiveParams.cardmakestate=e.data.cardMakeState,a.cardGiveParams.cardnoCheck=e.data.cardNo,a.cardGiveParams.oldCardNo=e.data.oldCardNo})},sInfoData:function(){var a=this;Object(i.q)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.sInfoData=e.data})},sYearInfo:function(){var a=this;Object(i.v)(this.cardGiveParams.requestIdCard).then(function(e){a.cardGiveParams.sYearInfo=e.data})},sLossData:function(){var a=this;Object(i.r)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.sLossData=e.data})},sReleaseData:function(){var a=this;Object(i.u)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.sReleaseData=e.data})},sNewCardInfo:function(){var a=this;Object(i.s)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.sNewCardInfo=e.data})},sNewInfo:function(){var a=this;Object(i.t)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.sNewInfo=e.data})},cPhotoInfo:function(){var a=this;Object(i.d)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.cPhotoInfo=e.data})},cPersonInfo:function(){var a=this;Object(i.c)(this.cardGiveParams.citizenId).then(function(e){a.cardGiveParams.cPersonInfo=e.data})},resetCardInfo:function(){Object(i.y)(),this.$router.push({name:"citizenList"})}}},n={render:function(){var a=this,e=a.$createElement,s=a._self._c||e;return s("div",[s("OBJECT",{attrs:{hidden:"",id:"yktReader",classid:"CLSID:19B0BDDF-D26D-4EF1-AF3C-1622B672E7D0"}}),a._v(" "),s("div",{staticClass:"app-container"},[s("el-form",{attrs:{size:"medium","label-position":"left"}},[s("el-row",[s("el-card",[s("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-info"},on:{click:function(e){a.resetCardInfo()}}},[a._v("重置卡内信息")]),a._v(" "),s("el-button",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"dialogButtom",type:"text"},on:{click:function(e){a.dialogFormVisible=!0}}},[a._v("打开嵌套表单的 Dialog")])],1)],1)],1),a._v(" "),s("el-tabs",{attrs:{type:"border-card"},model:{value:a.activeName,callback:function(e){a.activeName=e},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"基本信息",name:"citizen"}},[s("citizen",{ref:"idtcitizen",attrs:{idt:"",info:"",mark:this.$route.params.cdpfId}})],1)],1)],1),a._v(" "),s("div",[s("el-form",{ref:"cardGiveParams",attrs:{model:a.cardGiveParams,"label-width":"80px",size:"medium"}},[s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cardBusiness"},model:{value:a.cardGiveParams.cardBusiness,callback:function(e){a.$set(a.cardGiveParams,"cardBusiness",e)},expression:"cardGiveParams.cardBusiness"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cardmakestate"},model:{value:a.cardGiveParams.cardmakestate,callback:function(e){a.$set(a.cardGiveParams,"cardmakestate",e)},expression:"cardGiveParams.cardmakestate"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cardnoCheck"},model:{value:a.cardGiveParams.cardnoCheck,callback:function(e){a.$set(a.cardGiveParams,"cardnoCheck",e)},expression:"cardGiveParams.cardnoCheck"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"requestIdCard"},model:{value:a.cardGiveParams.requestIdCard,callback:function(e){a.$set(a.cardGiveParams,"requestIdCard",e)},expression:"cardGiveParams.requestIdCard"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"newCardNo"},model:{value:a.cardGiveParams.newCardNo,callback:function(e){a.$set(a.cardGiveParams,"newCardNo",e)},expression:"cardGiveParams.newCardNo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"check260"},model:{value:a.cardGiveParams.check260,callback:function(e){a.$set(a.cardGiveParams,"check260",e)},expression:"cardGiveParams.check260"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cardNoFlagT"},model:{value:a.cardGiveParams.cardNoFlagT,callback:function(e){a.$set(a.cardGiveParams,"cardNoFlagT",e)},expression:"cardGiveParams.cardNoFlagT"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"unit"},model:{value:a.cardGiveParams.unit,callback:function(e){a.$set(a.cardGiveParams,"unit",e)},expression:"cardGiveParams.unit"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"Mchnt"},model:{value:a.cardGiveParams.Mchnt,callback:function(e){a.$set(a.cardGiveParams,"Mchnt",e)},expression:"cardGiveParams.Mchnt"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"pos"},model:{value:a.cardGiveParams.pos,callback:function(e){a.$set(a.cardGiveParams,"pos",e)},expression:"cardGiveParams.pos"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"opr"},model:{value:a.cardGiveParams.opr,callback:function(e){a.$set(a.cardGiveParams,"opr",e)},expression:"cardGiveParams.opr"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"ip_addr"},model:{value:a.cardGiveParams.ip_addr,callback:function(e){a.$set(a.cardGiveParams,"ip_addr",e)},expression:"cardGiveParams.ip_addr"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"serverport"},model:{value:a.cardGiveParams.serverport,callback:function(e){a.$set(a.cardGiveParams,"serverport",e)},expression:"cardGiveParams.serverport"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"oldCardNo"},model:{value:a.cardGiveParams.oldCardNo,callback:function(e){a.$set(a.cardGiveParams,"oldCardNo",e)},expression:"cardGiveParams.oldCardNo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sLossData"},model:{value:a.cardGiveParams.sLossData,callback:function(e){a.$set(a.cardGiveParams,"sLossData",e)},expression:"cardGiveParams.sLossData"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sReleaseData"},model:{value:a.cardGiveParams.sReleaseData,callback:function(e){a.$set(a.cardGiveParams,"sReleaseData",e)},expression:"cardGiveParams.sReleaseData"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sYearInfo"},model:{value:a.cardGiveParams.sYearInfo,callback:function(e){a.$set(a.cardGiveParams,"sYearInfo",e)},expression:"cardGiveParams.sYearInfo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sInfoData"},model:{value:a.cardGiveParams.sInfoData,callback:function(e){a.$set(a.cardGiveParams,"sInfoData",e)},expression:"cardGiveParams.sInfoData"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sNewCardInfo"},model:{value:a.cardGiveParams.sNewCardInfo,callback:function(e){a.$set(a.cardGiveParams,"sNewCardInfo",e)},expression:"cardGiveParams.sNewCardInfo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"sNewInfo"},model:{value:a.cardGiveParams.sNewInfo,callback:function(e){a.$set(a.cardGiveParams,"sNewInfo",e)},expression:"cardGiveParams.sNewInfo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cPersonInfo"},model:{value:a.cardGiveParams.cPersonInfo,callback:function(e){a.$set(a.cardGiveParams,"cPersonInfo",e)},expression:"cardGiveParams.cPersonInfo"}}),a._v(" "),s("el-input",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{id:"cPhotoInfo"},model:{value:a.cardGiveParams.cPhotoInfo,callback:function(e){a.$set(a.cardGiveParams,"cPhotoInfo",e)},expression:"cardGiveParams.cPhotoInfo"}})],1)],1)],1)},staticRenderFns:[]},o=s("VU/8")(t,n,!1,null,null,null);e.default=o.exports}});