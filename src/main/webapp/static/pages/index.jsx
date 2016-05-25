
var React = components.React;
var ReactDOM = components.ReactDOM;

window.ddReact = React;
window.ddcomponents = components;

var IndexPage = React.createClass({

	componentDidMount: function() {
		console.log("example:window.location.hash>>>>"+window.location.hash)
		if(window.location.hash == ""){
			window.location.hash = "/index"
		}
	},

    render: function(){

        return(<div/>);
    }
});

ReactDOM.render(<IndexPage />, document.getElementById('react-content'));


