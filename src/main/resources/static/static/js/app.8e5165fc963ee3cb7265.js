webpackJsonp([8],{NHnr:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});n("j1ja");var r=n("7+uW"),a={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var o=n("VU/8")({name:"App"},a,!1,function(e){n("qGWJ")},null,null).exports,u=n("/ocq");r.default.use(u.a);var s=new u.a({routes:[{path:"/",name:"HelloWorld",component:function(){return n.e(0).then(n.bind(null,"gORT"))}},{path:"/firstMes",name:"firstMes",component:function(){return n.e(4).then(n.bind(null,"feNh"))}},{path:"/secondMes",name:"secondMes",component:function(){return n.e(2).then(n.bind(null,"kn9h"))}},{path:"/test",name:"test",component:function(){return n.e(5).then(n.bind(null,"ua3t"))}},{path:"/books",name:"books",component:function(){return n.e(6).then(n.bind(null,"C5M9"))}},{path:"/chapters",name:"chapters",component:function(){return n.e(1).then(n.bind(null,"skU3"))}},{path:"/chapterDetail",name:"chapterDetail",component:function(){return n.e(3).then(n.bind(null,"l9E7"))}}]}),p=n("zL8q"),i=n.n(p),c=(n("tvR6"),n("//Fk")),l=n.n(c),d=n("Dd8w"),f=n.n(d),m=n("mtWM"),h=n.n(m),g=n("mw3O"),v=n.n(g);h.a.defaults.timeout=6e3,h.a.interceptors.request.use(function(e){return"post"===e.method?e.data=f()({},e.data,{_t:Date.parse(new Date)/1e3}):"get"===e.method&&(e.params=f()({_t:Date.parse(new Date)/1e3},e.params)),e},function(e){return p.Message.error({message:"请求错误!"}),l.a.resolve(e)}),h.a.interceptors.response.use(function(e){if(!e.status||200!==e.status||"error"!==e.data.status)return e;p.Message.error({message:e.data.msg})},function(e){return p.Message.error({message:"服务器错误!"}),l.a.resolve(e)});r.default.config.productionTip=!1,r.default.use(i.a),r.default.prototype.getRequest=function(e,t){return h()({method:"get",params:t,url:"local"+e,paramsSerializer:function(e){return v.a.stringify(e,{arrayFormat:"brackets"})}})},r.default.prototype.postRequest=function(e,t){return h()({method:"post",url:"local"+e,data:t,transformRequest:[function(e){var t="";for(var n in e)t+=encodeURIComponent(n)+"="+encodeURIComponent(e[n])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})},new r.default({el:"#app",router:s,components:{App:o},template:"<App/>"})},qGWJ:function(e,t){},tvR6:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.8e5165fc963ee3cb7265.js.map