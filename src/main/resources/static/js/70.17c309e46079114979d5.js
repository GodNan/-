webpackJsonp([70],{yy9u:function(t,o,e){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var n={name:"SpeechNoPassed",components:{IdtPhotoUpload:e("kX85").default},props:{mark:{type:String,default:""},info:{type:Boolean,default:!1},showIdtPhoto:{type:Boolean,default:!0}},methods:{getIdtInfo:function(){this.showIdtPhoto&&this.$refs.idtPhoto.getIdtPhotoNow()}}},s={render:function(){var t=this.$createElement,o=this._self._c||t;return o("div",[this.showIdtPhoto?o("idt-photo-upload",{ref:"idtPhoto",attrs:{mark:this.mark,kind:3,info:this.info}}):this._e()],1)},staticRenderFns:[]},d=e("VU/8")(n,s,!1,null,null,null);o.default=d.exports}});