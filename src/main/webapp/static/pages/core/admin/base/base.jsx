var React=components.React;
var ReactDOM = components.ReactDOM;

var Page = components.frame.page;
var Ajax = components.Ajax;

var Base = React.createClass({
	displayName: 'Base',
	getInitialState:function() {
		return {
			//sidebarList:[{"key":"cc0c589fac5b4634917e8714e26249bc","parentId":"-1","title":"URL??","name":"URL??","icon":"heart-o","status":0,"url":"/urlManager","children":[]},{"key":"9efaac7d7e744dce951a9432f15938a3","parentId":"-1","title":"URL??2","name":"URL??2","icon":"","status":0,"url":"","children":[]}]
			sidebarList:[]
		}
	},
	componentDidMount:function() {
		console.log("base.jsx completed loaded")

		// {url:"/urlManager",name:"URL配置",icon:"star-o",children:[]},
		// 		{url:"www.2.com",name:"目录2",children:[{url:"www.baidu.com",name:"子目录1",children:[]}]},
		// 		{url:"/urlManager2",name:"目录3",children:[]},
		var self = this;
		//菜单未定义
		if(!window.dd.MenuConfig){
			console.log(">>getMenuConfig>>");
			$.ajax({
				url:"./menu/getMenuJSON",
				method:"POST",
        		data: JSON.stringify({"groupId":"1"}),
        		contentType:"application/json",
				dataType: 'json',
				success: function(data){
					if(data.children){
						self.setState({sidebarList:data.children});
						window.dd.MenuConfig = data.children;
					}
					//	window.dd.RouteConfig =	(new Function('return ' + data))();
					//window.dd.RouteConfig = eval('('+ data +')');
					//console.log(window.dd.RouteConfig);
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
		}else{
			this.setState({sidebarList:window.dd.MenuConfig});
		}

	},
	logout:function() {

	},
	render:function() {
		//let userName = getUserName() || "NA";
		var userName = "test1";
		return(<div>
			<Page
				userName={userName}
				title="业务系统控制台"
				onLogout={this.logout}
				sidebarList={this.state.sidebarList}>
				<div id="page-wrapper">
				</div>
			</Page>
		</div>)
	}
});

ReactDOM.render(<Base />, document.getElementById("base"));