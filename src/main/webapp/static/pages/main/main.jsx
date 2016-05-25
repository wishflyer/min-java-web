
var React = components.React;
var ReactDOM = components.ReactDOM;



var MainPage = React.createClass({

    render: function(){

        return(<div>
           	This is the main.jsx file.
			<a href="/index" data-tohash>click to index.jsx</a>
        </div>);
    }
});

ReactDOM.render(<MainPage/>,document.getElementById("react-content"))

