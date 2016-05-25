console.log("example:loading pages/core/config.js start");

window.dd = window.dd || {}
//配置路由信息
window.dd.RouteConfig = {
	"static/pages/core/index.jsx":/\/$/ig,
	"static/pages/main/index.jsx":/\/index$/ig,
	"static/pages/main/main.jsx":/\/main$/ig,
}

window.dd.debugMode = true;
window.dd.vendorsPath = "static/vendors/";

console.log("example:loading pages/core/config.js");