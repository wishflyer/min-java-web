Tools = components.tools;

console.log("example:load example/router/core/front/router.js....")
if(window.location.pathname == "/login" || window.location.hash == '#/login'){
	window.location.hash = "#/login";
    console.log("加载Login")
	//根据URL加载不同业务页面
	Tools.goJSX("#/login");

}else{
    console.log("example:load base.jsx")

	console.log("example:window.location.hash:"+window.location.hash)
	//根据URL加载不同业务页面
	Tools.goJSX(window.location.hash);


}

//加载基本框架
Tools.loadScript("static/pages/core/front/base.jsx",function(){window.dd.runScripts();});