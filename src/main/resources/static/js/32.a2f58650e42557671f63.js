webpackJsonp([32],{"6oWx":function(e,i,t){(e.exports=t("FZ+f")(!0)).push([e.i,"\n.el-checkbox.is-bordered.is-disabled.is-checked {\n    border-color: #409EFF;\n}\n.el-checkbox__input.is-disabled+span.el-checkbox__label {\n    color: #606266;\n}\n.el-checkbox__input.is-checked.is-disabled+span.el-checkbox__label {\n    color: #409EFF;\n}\n.el-checkbox__input.is-disabled.is-checked .el-checkbox__inner {\n    background-color: #409EFF;\n    border-color: #409EFF;\n}\n.el-input.is-disabled .el-input__inner {\n    background-color: #f5f7fa;\n    border-color: #e4e7ed;\n    color: #606266;\n    cursor: not-allowed;\n}\n.el-radio__input.is-disabled+span.el-radio__label {\n    color: #606266;\n    cursor: not-allowed;\n}\n.el-textarea.is-disabled .el-textarea__inner {\n    background-color: #f5f7fa;\n    border-color: #e4e7ed;\n    color: #606266;\n    cursor: not-allowed;\n}\n","",{version:3,sources:["D:/JAVA_WORKSPACE/idea/vue-bdpfnew/src/views/citizen/citizenModify.vue"],names:[],mappings:";AACA;IACI,sBAAsB;CACzB;AACD;IACI,eAAe;CAClB;AACD;IACI,eAAe;CAClB;AACD;IACI,0BAA0B;IAC1B,sBAAsB;CACzB;AACD;IACI,0BAA0B;IAC1B,sBAAsB;IACtB,eAAe;IACf,oBAAoB;CACvB;AACD;IACI,eAAe;IACf,oBAAoB;CACvB;AACD;IACI,0BAA0B;IAC1B,sBAAsB;IACtB,eAAe;IACf,oBAAoB;CACvB",file:"citizenModify.vue",sourcesContent:["\n.el-checkbox.is-bordered.is-disabled.is-checked {\n    border-color: #409EFF;\n}\n.el-checkbox__input.is-disabled+span.el-checkbox__label {\n    color: #606266;\n}\n.el-checkbox__input.is-checked.is-disabled+span.el-checkbox__label {\n    color: #409EFF;\n}\n.el-checkbox__input.is-disabled.is-checked .el-checkbox__inner {\n    background-color: #409EFF;\n    border-color: #409EFF;\n}\n.el-input.is-disabled .el-input__inner {\n    background-color: #f5f7fa;\n    border-color: #e4e7ed;\n    color: #606266;\n    cursor: not-allowed;\n}\n.el-radio__input.is-disabled+span.el-radio__label {\n    color: #606266;\n    cursor: not-allowed;\n}\n.el-textarea.is-disabled .el-textarea__inner {\n    background-color: #f5f7fa;\n    border-color: #e4e7ed;\n    color: #606266;\n    cursor: not-allowed;\n}\n"],sourceRoot:""}])},"9qR+":function(e,i,t){"use strict";Object.defineProperty(i,"__esModule",{value:!0});var a=t("Dd8w"),n=t.n(a),r=t("NYxO"),s=t("n4mu"),d=t("8urd"),l=t("E/IS"),o=t("Vge3"),c=t("yk4z"),u={name:"Citizen",components:{GuardianPhotoUpload:s.default},props:{idt:{type:Boolean,default:!1},info:{type:Boolean,default:!1},mark:{type:String,default:""}},data:function(){return{isSubmitting:!1,isPhotoLoading:!1,isLoading:!1,isDangAnLoading:!1,newApply:!0,disabled:!1,copyCity:!1,jiguanCodeFirst:!1,residentAddFirst:!1,dialogVisible:!1,buttonShow:!1,photoEdit:!0,imageBase64:"",card:{bankNo:"",cardNum:"",cardNo:""},gongan:{hjdz:""},dangan:{uuid:"",citizenId:"",workKind:""},citizen:{uuid:this.$route.params.cdpfId,requestId:"",name:"",citizenId:"",gender:"",race:"",marriager:"",birthdate:"",hukouKind:"",jiguanCode:"",eduLevel:"",residentAdd:"",phoneNo:"",guardian:"",guardianPhone:"",guardianRelation:"",cityid:"",zipcode:"",political:"",email:"",mobilePhone:"",guardianMobilePhone:"",guardianCitizenId:"",guardianGender:"",guardianOrAgent:"",residentZipcode:"",residentCity:"",cityidAddrstr:"",residentcityAddrstr:""},rules:{name:[{required:!0,message:"请填写姓名",trigger:"blur"}],citizenId:[{validator:this.validateIdcard,trigger:"blur"}],gender:[{required:!0,message:"请选择性别",trigger:"change"}],race:[{required:!0,message:"请选择民族",trigger:"change"}],marriager:[{required:!0,message:"请选择婚姻状况",trigger:"change"}],hukouKind:[{required:!0,message:"请选择户口类别",trigger:"change"}],eduLevel:[{required:!0,message:"请选择文化程度",trigger:"change"}],residentAdd:[{required:!0,message:"请选择居住地社区",trigger:"change"}],phoneNo:[{validator:this.validatePhone,trigger:"blur"}],guardian:[{required:!0,message:"请填写联系人姓名",trigger:"blur"}],guardianPhone:[{validator:this.validatePhone,trigger:"blur"}],guardianRelation:[{required:!0,message:"请选择与联系人关系",trigger:"change"}],cityid:[{required:!0,message:"请选择户籍地社区",trigger:"change"}],zipcode:[{validator:this.validateZipcode,trigger:"blur"}],political:[{required:!0,message:"请选择政治面貌",trigger:"change"}],email:[{validator:this.validateEmail,trigger:"blur"}],mobilePhone:[{validator:this.validateMobile,trigger:"blur"}],guardianMobilePhone:[{validator:this.validateMobile,trigger:"blur"}],guardianCitizenId:[{validator:this.validateIdcard,trigger:"blur"}],guardianGender:[{required:!0,message:"请选择联系人性别",trigger:"change"}],guardianOrAgent:[{required:!0,message:"请选择联系人类别",trigger:"change"}],residentZipcode:[{validator:this.validateZipcode,trigger:"blur"}],residentCity:[{required:!0,message:"请选择居住地社区",trigger:"change"}],cityidAddrstr:[{required:!0,message:"请填写户籍地详细地址",trigger:"blur"}],residentcityAddrstr:[{required:!0,message:"请填写居住地详细地址",trigger:"blur"}]},hjquxian:"",hjjiedao:"",jzquxian:"",jzjiedao:"",hjwatch:!1,jzwatch:!1,areas:{hjqxList:[],hjjdList:[],hjsqList:[],jzqxList:[],jzjdList:[],jzsqList:[]}}},computed:n()({},Object(r.b)(["dict"])),watch:{"citizen.citizenId":function(e){this.citizen.birthdate=this.getBirthdateByCitizenid(e),this.getGonganDataHjdzNow(e),this.getCitizenPhotoNow(e),this.getDangAnInfo(e)},"citizen.cityid":function(e){this.copyCity=!1,this.jiguanCodeFirst?this.jiguanCodeFirst=!1:(this.citizen.jiguanCode="",this.citizen.jiguanCode+=this.getHjJzAdd(this.hjquxian,this.areas.hjqxList),this.citizen.jiguanCode+=this.getHjJzAdd(this.hjjiedao,this.areas.hjjdList),this.citizen.jiguanCode+=this.getHjJzAdd(this.citizen.cityid,this.areas.hjsqList))},"citizen.residentCity":function(e){this.residentAddFirst?this.residentAddFirst=!1:(this.citizen.residentAdd="",this.citizen.residentAdd+=this.getHjJzAdd(this.jzquxian,this.areas.jzqxList),this.citizen.residentAdd+=this.getHjJzAdd(this.jzjiedao,this.areas.jzjdList),this.citizen.residentAdd+=this.getHjJzAdd(this.citizen.residentCity,this.areas.jzsqList),this.copyCity&&(this.citizen.residentAdd=this.citizen.jiguanCode,this.copyCity=!1))},"citizen.guardianOrAgent":function(e){3===e&&(this.citizen.guardianGender=this.citizen.gender,this.citizen.guardianRelation=7,this.citizen.guardianGender=this.citizen.gender,this.citizen.guardian=this.citizen.name,this.citizen.guardianCitizenId=this.citizen.citizenId,this.citizen.guardianMobilePhone=this.citizen.mobilePhone,this.citizen.guardianPhone=this.citizen.phoneNo)},hjquxian:function(e){this.getAreas(e,"hjjdList"),this.hjwatch&&(this.hjjiedao="")},hjjiedao:function(e){this.getAreas(e,"hjsqList"),this.hjwatch&&(this.citizen.cityid=""),this.hjwatch=!0},jzquxian:function(e){this.getAreas(e,"jzjdList"),this.jzwatch&&(this.jzjiedao="")},jzjiedao:function(e){this.getAreas(e,"jzsqList"),this.jzwatch&&(this.citizen.residentCity=""),this.jzwatch=!0}},created:function(){console.log("citizenModify this.$route.params.cdpfId"+this.$route.params.cdpfId),console.log("disabled: this.info"+this),this.getAreas("11","hjqxList"),this.getAreas("11","jzqxList"),""!==this.citizen.uuid&&this.getCitizenInfo()},methods:{getDangAnInfo:function(e){var i=this;Object(l.a)(e).then(function(e){"success"===e.type&&null!=e.data&&(i.dangan=e.data)})},dangAnSave:function(){var e=this;this.isDangAnLoading=!0,this.dangan.citizenId=this.citizen.citizenId,Object(l.b)(this.dangan).then(function(i){i.type,alert(i.content),e.isDangAnLoading=!1})},addNewCitizen:function(){},getGongAnData:function(){},getCitizenPhotoNow:function(e){var i=this;this.isPhotoLoading=!0,Object(o.d)(e).then(function(e){i.isPhotoLoading=!1,"success"===e.type?i.imageBase64=e.data.photo:i.imageBase64=""})},getCdpfCardNow:function(e){var i=this;Object(c.k)(e).then(function(e){"success"===e.type&&(i.card.bankNo=e.data.bankNo,i.card.cardNo=e.data.cardNo)})},getGonganDataHjdzNow:function(e){var i=this;Object(o.f)(e).then(function(e){"success"===e.type&&(i.gongan.hjdz=e.data.hjdz)})},getHjJzAdd:function(e,i){if(""!==e&&i.length>0){var t=i.find(function(i){return i.id===e});return null!==t&&void 0!==t?t.name:""}return""},copyCityid:function(){""!==this.citizen.cityid&&(this.citizen.residentCity=this.citizen.cityid,this.jzwatch=!1,this.copyCity=!0,this.getJzDz(this.citizen.cityid))},handleApply:function(){this.$store.dispatch("ToggleKindstr",""),this.$router.push({name:"handleApply",params:{requestId:this.citizen.requestId,cdpfId:this.citizen.uuid}})},saveCitizen:function(){var e=this;2===this.citizen.guardianOrAgent?Object(o.k)(this.$route.params.cdpfId).then(function(i){return"success"!==i.type?(e.isSubmitting=!1,void e.$message.error("监护人证明图片数量验证失败,请稍后再试")):i.data<1?(e.isSubmitting=!1,void e.$message.error("请上传监护人证明图片")):void e.updateCitizen()}):this.updateCitizen()},updateCitizen:function(){var e=this;this.isSubmitting=!0,this.$refs.citizen.validate(function(i){return i?""===e.citizen.uuid?(e.isSubmitting=!1,!1):void Object(o.m)(e.citizen).then(function(i){e.isSubmitting=!1,e.citizen.uuid!==i.data?alert("更新出错,uuid不同"):(e.disabled=!0,e.buttonShow=!1,e.newApply=!1,alert(i.content))}):(e.isSubmitting=!1,!1)})},getCitizenInfo:function(){var e=this;this.isLoading=!0,Object(o.b)(this.citizen.uuid).then(function(i){e.jiguanCodeFirst=!0,e.residentAddFirst=!0,e.isLoading=!1,e.setCitizenFromData(i.data),e.card.cardNum=i.data.cardNum,e.getCdpfCardNow(i.data.requestIdCard),e.trimSomeInfo(),e.getHjDz(i.data.cityid),e.getJzDz(i.data.residentCity),e.setAge(i.data.citizenId),2===e.citizen.guardianOrAgent&&(e.buttonShow=!0)})},trimSomeInfo:function(){this.citizen.zipcode=this.citizen.zipcode.trim(),this.citizen.residentZipcode=this.citizen.residentZipcode.trim(),this.citizen.cityidAddrstr=this.citizen.cityidAddrstr.trim(),this.citizen.residentcityAddrstr=this.citizen.residentcityAddrstr.trim(),this.citizen.residentCity=this.citizen.residentCity.trim(),this.citizen.guardian=this.citizen.guardian.trim(),this.citizen.guardianCitizenId=this.citizen.guardianCitizenId.trim(),this.citizen.jiguanCode=this.citizen.jiguanCode.trim()},setCitizenFromData:function(e){this.citizen.requestId=e.requestId,this.citizen.name=e.name,this.citizen.citizenId=e.citizenId,this.citizen.gender=e.gender,this.citizen.race=e.race,this.citizen.marriager=e.marriager,this.citizen.birthdate=e.birthdate,this.citizen.hukouKind=e.hukouKind,this.citizen.jiguanCode=e.jiguanCode,this.citizen.eduLevel=e.eduLevel,this.citizen.residentAdd=e.residentAdd,this.citizen.phoneNo=e.phoneNo,this.citizen.guardian=e.guardian,this.citizen.guardianPhone=e.guardianPhone,this.citizen.guardianRelation=e.guardianRelation,this.citizen.cityid=e.cityid,this.citizen.zipcode=e.zipcode,this.citizen.political=e.political,this.citizen.email=e.email,this.citizen.mobilePhone=e.mobilePhone,this.citizen.guardianMobilePhone=e.guardianMobilePhone,this.citizen.guardianCitizenId=e.guardianCitizenId,this.citizen.guardianGender=e.guardianGender,this.citizen.guardianOrAgent=e.guardianOrAgent,this.citizen.residentZipcode=e.residentZipcode,this.citizen.residentCity=e.residentCity,this.citizen.cityidAddrstr=e.cityidAddrstr,this.citizen.residentcityAddrstr=e.residentcityAddrstr},setBirthdate:function(){},setAge:function(e){console.log("setAge(citizenId):",e);var i=this.getAgeByCitizenid(e);this.$store.dispatch("ToggleCitizenId",e),this.$store.dispatch("ToggleAge",i)},getHjDz:function(e){null!=e&&(this.hjquxian=e.substr(0,6),this.hjjiedao=e.substr(0,9))},getJzDz:function(e){null!=e&&(this.jzquxian=e.substr(0,6),this.jzjiedao=e.substr(0,9))},getAreas:function(e,i){var t=this;""!==e&&null!==e&&void 0!==e&&Object(d.b)(e).then(function(e){t.areas[i]=e.data})},guardianShow:function(e){this.buttonShow=2===e},guardianPhoto:function(){this.dialogVisible=!0},getGuardianPhoto:function(){this.$store.dispatch("ToggleShowGuardianPhoto",1)}}},h={render:function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",[t("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.isLoading,expression:"isLoading"}],ref:"citizen",attrs:{model:e.citizen,rules:e.rules,"label-width":"80px",size:"medium","element-loading-text":"加载中"}},[t("el-container",{staticStyle:{border:"2px solid #f3f5f7"}},[t("el-aside",{staticClass:"verticalTitle",attrs:{width:"50px"}},[e._v("申请人基本情况")]),e._v(" "),t("el-main",[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"姓名",prop:"name"}},[t("el-input",{attrs:{readonly:"",disabled:e.disabled},model:{value:e.citizen.name,callback:function(i){e.$set(e.citizen,"name",i)},expression:"citizen.name"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"身份证号",prop:"citizenId"}},[t("el-input",{attrs:{readonly:"",disabled:e.disabled},model:{value:e.citizen.citizenId,callback:function(i){e.$set(e.citizen,"citizenId",i)},expression:"citizen.citizenId"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"出生年月",prop:"birthdate"}},[t("el-input",{attrs:{readonly:"",disabled:e.disabled},model:{value:e.citizen.birthdate,callback:function(i){e.$set(e.citizen,"birthdate",i)},expression:"citizen.birthdate"}})],1)],1)],1),e._v(" "),t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"性别",prop:"gender"}},[t("el-radio-group",{model:{value:e.citizen.gender,callback:function(i){e.$set(e.citizen,"gender",i)},expression:"citizen.gender"}},[t("el-radio",{attrs:{label:1,border:"",disabled:e.disabled}},[e._v("男")]),e._v(" "),t("el-radio",{attrs:{label:2,border:"",disabled:e.disabled}},[e._v("女")])],1)],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"民族",prop:"race"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.race,callback:function(i){e.$set(e.citizen,"race",i)},expression:"citizen.race"}},e._l(e.dict.raceList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"文化程度",prop:"eduLevel"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.eduLevel,callback:function(i){e.$set(e.citizen,"eduLevel",i)},expression:"citizen.eduLevel"}},e._l(e.dict.eduLevelList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1),e._v(" "),t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"婚姻状况",prop:"marriager",disabled:e.disabled}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.marriager,callback:function(i){e.$set(e.citizen,"marriager",i)},expression:"citizen.marriager"}},e._l(e.dict.marriagerList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"政治面貌",prop:"political"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.political,callback:function(i){e.$set(e.citizen,"political",i)},expression:"citizen.political"}},e._l(e.dict.politicalList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"户口类别",prop:"hukouKind"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.hukouKind,callback:function(i){e.$set(e.citizen,"hukouKind",i)},expression:"citizen.hukouKind"}},e._l(e.dict.hukouKindList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1),e._v(" "),t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{required:"",label:"移动电话",prop:"mobilePhone"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.mobilePhone,callback:function(i){e.$set(e.citizen,"mobilePhone",i)},expression:"citizen.mobilePhone"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"固定电话",prop:"phoneNo"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.phoneNo,callback:function(i){e.$set(e.citizen,"phoneNo",i)},expression:"citizen.phoneNo"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"电子邮箱",prop:"email"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.email,callback:function(i){e.$set(e.citizen,"email",i)},expression:"citizen.email"}})],1)],1)],1)],1),e._v(" "),t("el-aside",{attrs:{width:"200px"}},[t("img",{directives:[{name:"loading",rawName:"v-loading.body",value:e.isPhotoLoading,expression:"isPhotoLoading",modifiers:{body:!0}}],staticClass:"citizenPhoto",attrs:{src:"data:image/jpeg;base64,"+e.imageBase64,"element-loading-text":"图片加载中"}})])],1),e._v(" "),e.info?t("el-container",{staticStyle:{border:"2px solid #f3f5f7"}},[t("el-aside",{staticClass:"verticalTitle",attrs:{width:"50px"}},[e._v("卡信息")]),e._v(" "),t("el-main",[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"残疾证号"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.card.cardNum,callback:function(i){e.$set(e.card,"cardNum",i)},expression:"card.cardNum"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"一卡通号"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.card.cardNo,callback:function(i){e.$set(e.card,"cardNo",i)},expression:"card.cardNo"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"银行卡号"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.card.bankNo,callback:function(i){e.$set(e.card,"bankNo",i)},expression:"card.bankNo"}})],1)],1)],1)],1)],1):e._e(),e._v(" "),t("el-container",{staticStyle:{border:"2px solid #f3f5f7"}},[t("el-aside",{staticClass:"verticalTitle",attrs:{width:"50px"}},[e._v("档案号")]),e._v(" "),t("el-main",[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"档案号"}},[t("el-input",{model:{value:e.dangan.workKind,callback:function(i){e.$set(e.dangan,"workKind",i)},expression:"dangan.workKind"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:4}},[t("el-form-item",[t("el-button",{attrs:{size:"mini",type:"primary",loading:e.isDangAnLoading},on:{click:function(i){e.dangAnSave()}}},[e._v("保存")])],1)],1)],1)],1)],1),e._v(" "),t("el-container",{staticStyle:{border:"2px solid #f3f5f7"}},[t("el-aside",{staticClass:"verticalTitle",attrs:{width:"50px"}},[e._v("户籍地信息")]),e._v(" "),t("el-main",[t("el-row",{attrs:{gutter:30}},[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{required:"",label:"户籍地邮政编码",prop:"zipcode","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.zipcode,callback:function(i){e.$set(e.citizen,"zipcode",i)},expression:"citizen.zipcode"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{required:"",label:"居住地邮政编码",prop:"residentZipcode","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.residentZipcode,callback:function(i){e.$set(e.citizen,"residentZipcode",i)},expression:"citizen.residentZipcode"}})],1)],1)],1),e._v(" "),t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:9}},[t("el-form-item",{attrs:{required:"",label:"户籍地","label-width":"150px"}},[t("el-select",{attrs:{placeholder:"请选择区县",disabled:!0},model:{value:e.hjquxian,callback:function(i){e.hjquxian=i},expression:"hjquxian"}},e._l(e.areas.hjqxList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:5}},[t("el-form-item",{attrs:{label:"","label-width":"0",width:"100%"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择街道",disabled:!0},model:{value:e.hjjiedao,callback:function(i){e.hjjiedao=i},expression:"hjjiedao"}},e._l(e.areas.hjjdList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:5}},[t("el-form-item",{attrs:{label:"",prop:"cityid","label-width":"0",width:"100%"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择社区",disabled:!0},model:{value:e.citizen.cityid,callback:function(i){e.$set(e.citizen,"cityid",i)},expression:"citizen.cityid"}},e._l(e.areas.hjsqList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:5}})],1),e._v(" "),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"户籍地地址",prop:"cityidAddrstr","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.cityidAddrstr,callback:function(i){e.$set(e.citizen,"cityidAddrstr",i)},expression:"citizen.cityidAddrstr"}})],1)],1)],1),e._v(" "),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"公安户籍地地址","label-width":"150px"}},[t("el-alert",{attrs:{title:e.gongan.hjdz,type:"error",closable:!1}})],1)],1)],1),e._v(" "),t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:9}},[t("el-form-item",{attrs:{required:"",label:"居住地","label-width":"150px"}},[t("el-select",{attrs:{placeholder:"请选择区县",disabled:e.disabled},model:{value:e.jzquxian,callback:function(i){e.jzquxian=i},expression:"jzquxian"}},e._l(e.areas.jzqxList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:5}},[t("el-form-item",{attrs:{label:"","label-width":"0",width:"100%"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择街道",disabled:e.disabled},model:{value:e.jzjiedao,callback:function(i){e.jzjiedao=i},expression:"jzjiedao"}},e._l(e.areas.jzjdList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:5}},[t("el-form-item",{attrs:{label:"",prop:"residentCity","label-width":"0",width:"100%"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择社区",disabled:e.disabled},model:{value:e.citizen.residentCity,callback:function(i){e.$set(e.citizen,"residentCity",i)},expression:"citizen.residentCity"}},e._l(e.areas.jzsqList,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1),e._v(" "),e.disabled?e._e():t("el-col",{attrs:{span:5}},[t("el-button",{attrs:{type:"primary",round:"",size:"mini",icon:"el-icon-refresh"},on:{click:function(i){e.copyCityid()}}},[e._v("与户籍地一致")])],1)],1),e._v(" "),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"居住地地址",prop:"residentcityAddrstr","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.residentcityAddrstr,callback:function(i){e.$set(e.citizen,"residentcityAddrstr",i)},expression:"citizen.residentcityAddrstr"}})],1)],1),e._v(" "),t("el-col",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.isSubmitting,expression:"isSubmitting",modifiers:{fullscreen:!0,lock:!0}}],attrs:{"element-loading-text":"操作处理中"}})],1)],1)],1),e._v(" "),t("el-container",{staticStyle:{border:"2px solid #f3f5f7"}},[t("el-aside",{staticClass:"verticalTitle",attrs:{width:"50px"}},[e._v("联系人信息")]),e._v(" "),t("el-main",[t("el-row",[t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"联系人类型",prop:"guardianOrAgent","label-width":"150px"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},on:{change:e.guardianShow},model:{value:e.citizen.guardianOrAgent,callback:function(i){e.$set(e.citizen,"guardianOrAgent",i)},expression:"citizen.guardianOrAgent"}},e._l(e.dict.guardianOrAgentList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"联系人性别",prop:"guardianGender","label-width":"150px"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.guardianGender,callback:function(i){e.$set(e.citizen,"guardianGender",i)},expression:"citizen.guardianGender"}},e._l(e.dict.guardianGenderList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1),e._v(" "),t("el-col",{attrs:{span:8}},[t("el-form-item",{attrs:{label:"与联系人关系",prop:"guardianRelation","label-width":"150px"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",disabled:e.disabled},model:{value:e.citizen.guardianRelation,callback:function(i){e.$set(e.citizen,"guardianRelation",i)},expression:"citizen.guardianRelation"}},e._l(e.dict.guardianRelationList,function(e){return t("el-option",{key:e.id,attrs:{label:e.description,value:e.id}})}))],1)],1)],1),e._v(" "),t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"联系人姓名",prop:"guardian","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.guardian,callback:function(i){e.$set(e.citizen,"guardian",i)},expression:"citizen.guardian"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"联系人身份证号",prop:"guardianCitizenId","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.guardianCitizenId,callback:function(i){e.$set(e.citizen,"guardianCitizenId",i)},expression:"citizen.guardianCitizenId"}})],1)],1)],1),e._v(" "),t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{required:"",label:"联系人移动电话",prop:"guardianMobilePhone","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.guardianMobilePhone,callback:function(i){e.$set(e.citizen,"guardianMobilePhone",i)},expression:"citizen.guardianMobilePhone"}})],1)],1),e._v(" "),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"联系人固定电话",prop:"guardianPhone","label-width":"150px"}},[t("el-input",{attrs:{disabled:e.disabled},model:{value:e.citizen.guardianPhone,callback:function(i){e.$set(e.citizen,"guardianPhone",i)},expression:"citizen.guardianPhone"}})],1)],1)],1)],1)],1),e._v(" "),e.newApply?t("el-row",{staticStyle:{"margin-top":"10px"},attrs:{gutter:15}},[t("el-col",{attrs:{span:6}},[t("el-form-item",{attrs:{"label-width":"0"}})],1),e._v(" "),t("el-col",{attrs:{span:5}},[t("el-form-item",{attrs:{"label-width":"0"}},[e.buttonShow?t("el-button",{attrs:{type:"primary",plain:""},on:{click:e.guardianPhoto}},[e._v("监护人证明照片")]):e._e()],1)],1),e._v(" "),t("el-col",{attrs:{span:2}},[t("el-form-item",{attrs:{"label-width":"0"}},[e.isSubmitting?e._e():t("el-button",{attrs:{type:"primary",icon:"el-icon-success"},on:{click:e.saveCitizen}},[e._v("保存")])],1)],1)],1):e._e()],1),e._v(" "),t("el-dialog",{attrs:{title:"监护人证明",visible:e.dialogVisible},on:{"update:visible":function(i){e.dialogVisible=i},opened:e.getGuardianPhoto}},[t("guardian-photo-upload",{ref:"guardianPhoto",attrs:{photoEdit:e.photoEdit,mark:this.$route.params.cdpfId}})],1)],1)},staticRenderFns:[]};var p=t("VU/8")(u,h,!1,function(e){t("ixN5")},null,null);i.default=p.exports},ixN5:function(e,i,t){var a=t("6oWx");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("12ea977e",a,!0)}});