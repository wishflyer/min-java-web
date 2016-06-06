Tools = components.tools;

console.log("load router.js....")

window.dd = window.dd || {}

const loadPage = (func) =>{
	if(window.dd.RouteConfig){
		func();
	}else{
		$.ajax({
			url:"./menu/getRouterConfig",
			method:"GET",
			contentType:"application/text",
			dataType: 'text',
			success: function(data){
				//console.log(data);
				//	window.dd.RouteConfig =	(new Function('return ' + data))();
				window.dd.RouteConfig = eval('('+ data +')');
				//console.log(window.dd.RouteConfig);
				func();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				//console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
				//alert('error!!!!');
			},
			complete: function(){
				//alert('complete!!!!');
			},
		}) 
	}
}

if(window.location.pathname == "/login" || window.location.hash == '#/login'){
	window.location.hash = "#/login";
    console.log("加载Login")
	//根据URL加载不同业务页面
	//Tools.goJSX("#/login");
	
	loadPage(function(){Tools.loadUrl("#/login");})

}else{
	


	loadPage(function(){

		//加载基本框架
		Tools.loadJSX("static/pages/core/admin/base/base.jsx");
		console.log("example:window.location.hash:"+window.location.hash)
		//根据URL加载不同业务页面
		if(window.location.hash){
			Tools.loadUrl(window.location.hash);
		}


	})

	


}
