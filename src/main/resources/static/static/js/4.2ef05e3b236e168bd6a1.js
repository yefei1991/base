webpackJsonp([4],{dvDL:function(e,t){},feNh:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={data:function(){var e=this;return{searchForm:{projectName:"",productName:"",modelNum:"",pointNum:"",machine:""},multipleSelection:[],btndisabled:!0,loading:!1,showhead:!0,columns7:[{type:"selection",width:60,align:"center"},{title:"Name",key:"name",render:function(e,t){return e("div",[e("Icon",{props:{type:"person"}}),e("strong",t.row.name)])}},{title:"Age",key:"age"},{title:"Address",key:"address"},{title:"Action",key:"action",width:150,align:"center",render:function(t,a){return t("div",[t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.show(a.index)}}},"View"),t("Button",{props:{type:"error",size:"small"},on:{click:function(){e.remove(a.index)}}},"Delete")])}}],data6:[{name:"John Brown",age:18,address:"New York No. 1 Lake Park"},{name:"Jim Green",age:24,address:"London No. 1 Lake Park"},{name:"Joe Black",age:30,address:"Sydney No. 1 Lake Park"},{name:"Jon Snow",age:26,address:"Ottawa No. 2 Lake Park"}]}},methods:{show:function(e){this.$Modal.info({title:"User Info",content:"Name："+this.data6[e].name+"<br>Age："+this.data6[e].age+"<br>Address："+this.data6[e].address})},remove:function(e){this.data6.splice(e,1)},onSelectChange:function(e){this.multipleSelection=e,0===e.length?this.btndisabled=!0:this.btndisabled=!1},freeze:function(){alert("freeze")},unfreeze:function(){alert("unfreeze")},discard:function(){alert("discard")}}},r={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"firstMes"},[a("Form",{ref:"searchForm",attrs:{model:e.searchForm,inline:"","label-width":80}},[a("FormItem",{attrs:{label:"项目名"}},[a("Input",{model:{value:e.searchForm.projectName,callback:function(t){e.$set(e.searchForm,"projectName",t)},expression:"searchForm.projectName"}})],1),e._v(" "),a("FormItem",{attrs:{label:"品名"}},[a("Input",{model:{value:e.searchForm.productName,callback:function(t){e.$set(e.searchForm,"productName",t)},expression:"searchForm.productName"}})],1),e._v(" "),a("FormItem",{attrs:{label:"模具号"}},[a("Input",{model:{value:e.searchForm.modelNum,callback:function(t){e.$set(e.searchForm,"modelNum",t)},expression:"searchForm.modelNum"}})],1),e._v(" "),a("FormItem",{attrs:{label:"穴号"}},[a("Input",{model:{value:e.searchForm.pointNum,callback:function(t){e.$set(e.searchForm,"pointNum",t)},expression:"searchForm.pointNum"}})],1),e._v(" "),a("FormItem",{attrs:{label:"机台"}},[a("Input",{model:{value:e.searchForm.machine,callback:function(t){e.$set(e.searchForm,"machine",t)},expression:"searchForm.machine"}})],1),e._v(" "),a("FormItem",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formInline")}}},[e._v("Signin")])],1)],1),e._v(" "),a("Table",{ref:"firstMes",attrs:{border:"",loading:e.loading,"show-header":e.showhead,columns:e.columns7,data:e.data6},on:{"on-selection-change":e.onSelectChange}}),e._v(" "),a("Divider"),e._v(" "),a("Button",{attrs:{icon:"md-lock",disabled:e.btndisabled,type:"warning"},on:{click:e.freeze}},[e._v("批量冻结")]),e._v(" "),a("Button",{attrs:{icon:"md-key",disabled:e.btndisabled,type:"info"},on:{click:e.unfreeze}},[e._v("批量解冻")]),e._v(" "),a("Button",{attrs:{icon:"md-trash",disabled:e.btndisabled,type:"error"},on:{click:e.discard}},[e._v("批量报废")])],1)},staticRenderFns:[]};var o=a("VU/8")(n,r,!1,function(e){a("dvDL")},"data-v-10e48593",null);t.default=o.exports}});
//# sourceMappingURL=4.2ef05e3b236e168bd6a1.js.map