window.dd = window.dd || {}
//配置路由信息
window.dd.RouteConfig = {
	"static/pages/core/index.jsx":/\/$/ig,
	"static/pages/main/index.jsx":/\/index$/ig,
	"static/pages/main/main.jsx":/\/main$/ig,

	//路径配置页面

	"static/pages/core/admin/url/urlManager.jsx":/\/urlManager$/ig,
	"static/pages/core/admin/url/urlManager2.jsx":/\/urlManager2$/ig,
}

window.dd.debugMode = true;
window.dd.vendorsPath = "static/vendors/";

console.log("load config.js");