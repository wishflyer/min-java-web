var React=components.React;
var ReactDOM = components.ReactDOM;

var Page = components.frame.page;
var Ajax = components.ajax;
const Query = components.query;
const Command = components.command;

var antd = components.antd;
const Button = antd.Button;
const Input = antd.Input;
const Select = antd.Select;
const DatePicker = antd.DatePicker;

const Row = antd.Row;
const Col = antd.Col;

const UrlManager=React.createClass({


    locals:{
        params:{},
        createDate:"",
        updateDate:""
    },

    getInitialState(){
        return {

        }
    },

    componentDidMount(){
    },

    render(){

		return(<div>
            <Command>
                <Button type="primary" onClick={null}  >添加关系</Button>
            </Command>
            <Query>
			    <Input labelName="产品映射ID:" name="id" placeholder="通过产品映射ID查询"/>
				<Input labelName="云公司产品编码:" name="ctProductCode" placeholder="通过云公司产品编码查询" />
				<Input labelName="华为产品编码:" name="hwProductCode" placeholder="通过华为产品编码查询" />
				<Input labelName="云资源规格编码:" name="speCode" placeholder="通过云资源规格编码查询" />
				<Input labelName="资源池编码:" name="zoneCode"  placeholder="通过资源池编码查询" />
				<Input labelName="资源类型:" name="resourceType"  placeholder="通过资源类型查询" />
				<Input labelName="状态:" name="status"  placeholder="通过状态查询" />
            </Query>
		</div>);
	}
});

ReactDOM.render(<UrlManager />, document.getElementById("page-wrapper"));