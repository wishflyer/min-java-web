Tools = components.tools;

console.log("load router.js....")
if(window.location.pathname == "/login" || window.location.hash == '#/login'){
	window.location.hash = "#/login";
    console.log("加载Login")
	//根据URL加载不同业务页面
	//Tools.goJSX("#/login");
	Tools.loadUrl("#/login");

}else{
	//加载基本框架
	Tools.loadJSX("static/pages/core/admin/base/base.jsx");

	console.log("example:window.location.hash:"+window.location.hash)
	//根据URL加载不同业务页面
	if(window.location.hash){
		Tools.loadUrl(window.location.hash);
	}


}
