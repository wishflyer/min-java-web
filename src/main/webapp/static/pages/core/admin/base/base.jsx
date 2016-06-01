var React=components.React;
var ReactDOM = components.ReactDOM;

var Page = components.frame.page;
var Ajax = components.Ajax;

var Base = React.createClass({
	displayName: 'Base',
	getInitialState:function() {
		return {
			sidebarList:[
				{url:"/urlManager",name:"URL配置",icon:"star-o",children:[]},
				{url:"www.2.com",name:"目录2",children:[{url:"www.baidu.com",name:"子目录1",children:[]}]},
				{url:"/urlManager2",name:"目录3",children:[]},
			]
		}
	},
	componentDidMount:function() {
		console.log("base.jsx completed loaded")
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