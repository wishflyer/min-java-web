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

const Tree = antd.Tree;
const TreeNode = Tree.TreeNode;
const Card = antd.Card;

const MyTable = components.table.myTable;
const Breadcrumb = antd.Breadcrumb;
var Ajax = components.ajax;

var Debug = components.debug;
var Icon = antd.Icon;

const UrlManager=React.createClass({


    locals:{
        menuMap:{},
        selectedMenuNode:[]
    },
    componentDidMount(){
        console.log("componentDidMount()....");
        this.loadTreeData();
    },

    getInitialState() {
        return {
            gData:[],
            defaultExpandedKeys: this.props.defaultExpandedKeys || ['-1'],
            defaultSelectedKeys: this.props.defaultSelectedKeys,
            defaultCheckedKeys: this.props.defaultCheckedKeys || ['-1'],
            table:{
                pageSize:10,
                pageNo:1,
                totalRows:1000,
                data: [],
            }
        };
    },
    loadTreeData(){
        var self = this;
        Ajax.post("/menu/getMenuJSON", {}, treeJson => {
            this.setState({
                gData:[treeJson]
            });
            Ajax.post("/menu/getMenuMap", {}, menuMap => {
                self.locals.menuMap = menuMap;
                self.rebuildTable(this.locals.selectedMenuNode);
            });
        });
    },
    addChildNodeAction(node){
        let newMenu = {
            id:node.id,
            config:"1",
            description:"description",
            icon:"icon",
            name:"name",
            other:"other",
            url:"url",
        };
        var self = this;
        Ajax.post("/menu/addMenu", newMenu, r => {
            alert("success!!!count:"+ r);
            if(r > 0){
                self.loadTreeData();
            }
        });
    },
    setValidAction(node){
        let jsonStr = {
            id:node.id
        };
        var self = this;
        Ajax.post("/menu/setValid", jsonStr, r => {
            alert("setValidAction success!!!count:"+ r);
            if(r > 0){
                // 方法1：重新刷新请求表格
                self.loadTreeData();
                // 方法2：不刷新，直接改数据吧
                //this.locals.menuMap[node.id]["status"] = <span style={{"color":"red"}}>失效</span>;
            }
        });
    },
    setInvalidAction(node){
        let jsonStr = {
            id:node.id
        };
        var self = this;
        Ajax.post("/menu/setInvalid", jsonStr, r => {
            alert("setInvalidAction success!!!count:"+ r);
            if(r > 0){
                // 方法1：重新刷新请求表格
                self.loadTreeData();
                // 方法2：不刷新，直接改数据吧
                // this.locals.menuMap[node.id]["status"] = "有效";

            }
        });
    },
    rebuildTable(nodes){
        let tableData = [];
        nodes.forEach((item) => {
            let _item = this.locals.menuMap[item];
            if(_item.status == 0 || _item.status == "有效"){
                _item.status = "有效";
            }else{
                _item.status = <span style={{"color":"red"}}>失效</span>;
            }
            tableData.push(_item);
        });
        this.setState({
            table:{
                pageSize:10,
                pageNo:1,
                totalRows:tableData.length,
                data: tableData,
            },
        })
    },
    onSelect(nodes) {
        console.log('selected', nodes);
        this.rebuildTable(nodes);
        this.locals.selectedMenuNode = nodes;
    },
    onCheck(info) {
        console.log('onCheck', info);
    },
    onDragEnter(info) {
        console.log(info);
        // expandedKeys 需要受控时设置
        // this.setState({
        //   expandedKeys: info.expandedKeys,
        // });
    },
    onDrop(info) {
        console.log(info);
        const dropKey = info.node.props.eventKey;
        const dragKey = info.dragNode.props.eventKey;
        // const dragNodesKeys = info.dragNodesKeys;
        const loop = (data, key, callback) => {
            data.forEach((item, index, arr) => {
                if (item.key === key) {
                    return callback(item, index, arr);
                }
                if (item.children) {
                    return loop(item.children, key, callback);
                }
            });
        };
        const data = [...this.state.gData];
        let dragObj;
        loop(data, dragKey, (item, index, arr) => {
            arr.splice(index, 1);
            dragObj = item;
        });
        if (info.dropToGap) {
            let ar;
            let i;
            loop(data, dropKey, (item, index, arr) => {
                ar = arr;
                i = index;
            });
            ar.splice(i, 0, dragObj);
        } else {
            loop(data, dropKey, (item) => {
                item.children = item.children || [];
                // where to insert 示例添加到尾部，可以是随意位置
                item.children.push(dragObj);
            });
        }
        this.setState({
            gData: data,
        });
    },

    render(){


        const getTableData = data =>{
            var dataTable = data;
            let statusControl;
            for(let i=0;i<dataTable.length;i++){
                if(dataTable[i]['status'] == "0" || dataTable[i]['status'] == "有效" ){
                    statusControl = <a onClick={this.setInvalidAction.bind(this,dataTable[i])}>置无效</a>
                }else{
                    statusControl = <a onClick={this.setValidAction.bind(this,dataTable[i])}>置有效</a>
                }
                dataTable[i]['control']= <div><a  onClick={this.addChildNodeAction.bind(this,dataTable[i])}>增加子节点</a>&nbsp;&nbsp;|&nbsp;&nbsp;{statusControl}&nbsp;&nbsp;|&nbsp;&nbsp;<a onClick={null}>编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a onClick={null}>删除</a></div>;
            }
            let tableProps={
                //title:['UUID','名称','URL','配置','图标','描述','其他','GroupID','parentID','状态','操作'],
                //jsonKey:['id','name','url','config','icon','description','other','groupId','parentId','status','control'],
                title:['名称','URL','配置','图标','描述','其他','GroupID','状态','操作'],
                jsonKey:['name','url','config','icon','description','other','groupId','status','control'],
                data:dataTable,
                doList:this.doList,
                pageSize:this.state.table.pageSize,
                pageNo:this.state.table.pageNo, //page:this.state.offset
                totalRows:this.state.table.totalRows,
                checkType:"none"
            };
            return tableProps;
        }


        const loop = data => data.map((item) => {

            let nodeTitle;
            if(item.icon){
                nodeTitle = <span><Icon type={item.icon}/>&nbsp;{item.title}</span>;
            }else{
                nodeTitle = item.title
            }
            //判断是否有效
            if(item.status == 9999){
                nodeTitle = <span style={{"color":"red"}}>{nodeTitle}</span>;
            }

            ////根节点
            //if(item.parentId == "-1"){
            //    return <TreeNode key={item.key} title={nodeTitle} disableCheckbox disabled >{loop(item.children)}</TreeNode>;
            //}
            //目录节点
            if (item.children && item.children.length > 0) {
                return <TreeNode key={item.key} title={nodeTitle} disableCheckbox >{loop(item.children)}</TreeNode>;
            }
            //叶子节点
            return <TreeNode key={item.key} title={nodeTitle} disableCheckbox />;
        });

		return(<div>
            <Row style={{ width: "96%","marginLeft":"2%","marginTop":"5px" }}>
                    <Col span={4}>
                        <Tree openAnimation={{}} draggable multiple
                              onDragEnter={this.onDragEnter}
                              onDrop={this.onDrop}
                              defaultCheckedKeys={this.state.defaultCheckedKeys}
                              defaultExpandedKeys={this.state.defaultExpandedKeys}
                              defaultSelectedKeys={this.state.defaultSelectedKeys}
                              onSelect={this.onSelect}
                              onCheck={this.onCheck}>
                            {loop(this.state.gData)}
                        </Tree>
                    </Col>
                    <Col span={20}>
                        <Command>
                            <Button type="primary" onClick={null} >删除全部选中</Button>
                        </Command>
                        <Query>
                            <Input labelName="名称:" name="id" placeholder="名称"/>
                            <Input labelName="URL:" name="ctProductCode" placeholder="URL" />
                            <Input labelName="配置:" name="hwProductCode" placeholder="配置" />
                            <Input labelName="权限组:" name="id" placeholder="权限组"/>
                            <Input labelName="其他:" name="speCode" placeholder="其他" />
                            <Input labelName="状态:" name="zoneCode"  placeholder="状态" />
                        </Query>
                        <MyTable {...getTableData(this.state.table.data)}/>
                    </Col>
            </Row>
		</div>);
	}
});

ReactDOM.render(<UrlManager />, document.getElementById("page-wrapper"));