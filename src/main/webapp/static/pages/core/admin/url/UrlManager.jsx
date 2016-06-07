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
var message = antd.message;

const Modal = antd.Modal;
const confirm = Modal.confirm;

const Form = antd.Form;
const FormItem =  Form.Item;
const createForm = Form.create;

const Tools = components.tools;

var UrlManager=React.createClass({

    getDefaultProps() {
        return {
            menuGroupId:"0"
        }
    },


    locals:{
        menuMap:{},
        selectedMenuNode:[],
        keys:[],
        params:{},

        _nodeId:-999,
    },
    componentDidMount(){
        this.reloadData();
    },

    getInitialState() {
        return {
            gData:[],
            defaultExpandedKeys: this.props.defaultExpandedKeys || ['-1'],
            defaultSelectedKeys: this.props.defaultSelectedKeys,
            defaultCheckedKeys: this.props.defaultCheckedKeys || ['-1'],
            selectedKeys:[],
            table:{
                pageSize:999,
                pageNo:1,
                totalRows:999,
                data: [],
            },
            showCreateModalState:false,
            showModifyModalState:false,
            modify_id:"",
            modify_config:"",
            modify_url:"",
            modify_other:"",
            modify_name:"",
            modify_icon:"",
            modify_description:"",
        };
    },
    loadTreeData(){
        var self = this;
        Ajax.post("./menu/getMenuJSON", {groupId:this.props.menuGroupId}, treeJson => {
            this.setState({
                gData:[treeJson]
            });
        });
    },
    reloadData(){
        var self = this;
        Ajax.post("./menu/getMenuJSON", {groupId:this.props.menuGroupId}, treeJson => {
            this.setState({
                gData:[treeJson], 
                selectedKeys:[],
            });
            Ajax.post("./menu/getMenuMap", {groupId:this.props.menuGroupId}, menuMap => {
                self.locals.menuMap = menuMap;
                self.rebuildTable(this.locals.selectedMenuNode);
            });
        });
    },
    addChildNodeAction(e){

        e.preventDefault();
        console.log(e.target.config);
        let newMenu = {
            id:this.locals._nodeId,
            config:e.target.config.value,
            description:e.target.description.value,
            icon:e.target.icon.value,
            name:e.target.name.value,
            other:e.target.other.value,
            url:e.target.url.value
        }

        
        console.log(newMenu);

        var self = this;
        var target = e.target;
        Ajax.post("./menu/addMenu", newMenu, r => {
            if(r > 0){
                message.success("成功添加菜单！");
                //清空数据
                target.config.value = "";
                target.description.value = "";
                target.name.value = "";
                target.icon.value = "";
                target.other.value= "";
                target.url.value = "";
                this.hideAddModal();
                self.reloadData();
            }else{
                message.error("操作失败！请联系管理员！");
            }
        });
    },
    setValidAction(node){
        let jsonStr = {
            id:node.id
        };
        var self = this;
        Ajax.post("./menu/setValid", jsonStr, r => {
            if(r > 0){
                message.success("操作成功！");
                // 方法1：重新刷新请求表格
                // self.reloadData();
                // 方法2：不刷新，直接改数据吧
                console.log(self.locals.menuMap[node.id]);
                this.loadTreeData();
                self.locals.menuMap[node.id]["status"] = "有效";
                self.rebuildTable(this.state.selectedKeys);
            }else{
                message.error("操作失败！");
            }
        });
    },
    setInvalidAction(node){
        let jsonStr = {
            id:node.id
        };
        var self = this;
        Ajax.post("./menu/setInvalid", jsonStr, r => {
            if(r > 0){
                message.success("操作成功！");
                // 方法1：重新刷新请求表格
                // self.reloadData();
                // 方法2：不刷新，直接改数据吧
                this.loadTreeData();
                self.locals.menuMap[node.id]["status"] = <span style={{"color":"red"}}>失效</span>;
                self.rebuildTable(this.state.selectedKeys);
            }else{
                message.error("操作失败！");
            }
        });
    },
    deleteAction(node){
        var self = this;
        confirm({
            title: '您是否确认要删除这项内容',
            content: '该操作会把该节点及其下属节点都删除且无法恢复！！',
            onOk() {
                let deleteKeys = self.getSelectedTreeNodes([node.id]);
                console.log(deleteKeys);
                let jsonStr = {
                    ids:deleteKeys
                };
                Ajax.post("./menu/deleteBatch", jsonStr, r => {
                    if(r > 0){
                        message.success("操作成功！");
                        self.reloadData();
                    }else{
                        message.error("操作失败！");
                    }
                });
            },
            onCancel() {},
        });
    },
    
    deleteBatchAction(){
        if(this.state.selectedKeys.length == 0) return;
        var self = this;
        confirm({
            title: '您是否确认要删除这项内容',
            content: '该操作会把该节点及其下属节点都删除且无法恢复！！',
            onOk() {
                let deleteKeys = self.getSelectedTreeNodes();
                let jsonStr = {
                    ids:deleteKeys
                };
                Ajax.post("./menu/deleteBatch", jsonStr, r => {
                    if(r > 0){
                        message.success("操作成功！");
                        self.locals.selectedMenuNode=[];
                        self.reloadData();
                    }else{
                        message.error("操作失败！");
                    }
                });
            },
            onCancel() {},
        });

    },
    rebuildTable(nodes){
        let tableData = [];
        nodes.forEach((item) => {
            let _item = this.locals.menuMap[item];
            if(_item != undefined){
                if(_item.status == 0 || _item.status == "有效"){
                    _item.status = "有效";
                }else{
                    _item.status = <span style={{"color":"red"}}>失效</span>;
                }
                tableData.push(_item);
            }

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
    /*********************************************************************
     * 获得该组节点及其下得全部节点
     * @param  {Array} nodeArray 节点数组 ["a","b","c"]
     * @return {Array} 该组节点及其下得全部节点
     *********************************************************************/
    getTreeNodes(node){
        //找到了
        //console.log(node);
        let nodeArray = [];
        nodeArray.push(node.key);
        for(var index in node.children){
            nodeArray = nodeArray.concat(this.getTreeNodes(node.children[index]));
        }
        return nodeArray
    },
    searchInTree(searchNodeArray,tree){
        //如果当前树的节点在查找的数组中，则返回该节点及其下方所有节点
        //console.log($.inArray(tree.key, searchNodeArray));
        if($.inArray(tree.key, searchNodeArray) != -1){
            return this.getTreeNodes(tree);;
        }

        //在该节点的孩子中查找
        let allNodeArray = [];
        for(var index in tree.children){
            //allNodeArray = $.unique(allNodeArray,arr2)
            allNodeArray = allNodeArray.concat(this.searchInTree(searchNodeArray,tree.children[index]));
        }
        //allNodeArray = $.unique(allNodeArray)
        return allNodeArray;
    },
    /*********************************************************************/
    getSelectedTreeNodes(){
        console.log("this.state.selectedKeys:",this.state.selectedKeys)
        return this.searchInTree(this.state.selectedKeys,this.state.gData[0]);
    },

    onSelect(nodes, info) {
        //console.log('selected', nodes);
        // console.log('onSelect', info);

        // this.locals.keys = [];
        // this.locals.keys.push(info.node.props.eventKey);
        // this.getTreeChildrenNodes(info.node);
        // console.log(this.locals.keys);
        
        //var aa = this.searchInTree([info.node.props.eventKey],this.state.gData[0]);
        //console.log("Search Result:",this.getSelectedTreeNodes(nodes));
        //
        //设置根目录不能点击
        //if(info.node.props.eventKey != -1){
        //    this.setState({selectedKeys:nodes });
        //}
        this.setState({selectedKeys:nodes });
        this.rebuildTable(nodes);
        this.locals.selectedMenuNode = nodes;
    },
    onCheck(info) {
        console.log('onCheck', info);
    },
    onDragEnter(info) {
        //console.log(info);
        // expandedKeys 需要受控时设置
        // this.setState({
        //   expandedKeys: info.expandedKeys,
        // });
    },
    onDrop(info) {
        const dropKey = info.node.props.eventKey;
        const dragKey = info.dragNode.props.eventKey;
        
        if(dropKey == "-1" && info.dropToGap){
            //不能拖到根节点之外
            message.warning("不能拖到根节点之外");
            return;
        }
        
        
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
            //移动节点作为目标节点的兄弟节点
            let ar;
            let i;
            let parentId;
            loop(data, dropKey, (item, index, arr) => {

                ar = arr;
                i = index;
                parentId =  item.parentId;
                console.log("final:",item);
                ar.splice(i, 0, dragObj);

                let jsonStr = {
                    parentId:parentId,
                    childId:dragKey
                };

                Ajax.post("./menu/changeParent", jsonStr, r => {
                    if(r > 0){

                        message.success("操作成功！");

                        this.setState({
                            gData: data,
                        });

                    }else{
                        message.error("操作失败！");
                    }
                });
            });

        } else {
            //移动节点作为目标节点的叶子节点
            let jsonStr = {
                parentId:dropKey,
                childId:dragKey
            };
            Ajax.post("./menu/changeParent", jsonStr, r => {
                if(r > 0){
                    message.success("操作成功！");
                    loop(data, dropKey, (item) => {
                        item.children = item.children || [];
                        // where to insert 示例添加到尾部，可以是随意位置
                        item.children.push(dragObj);
                    });
                    this.setState({
                        gData: data,
                    });
                }else{
                    message.error("操作失败！");
                }
            });
        }
    },

    showAddModal(node){
        //console.log(this.props.form);
        //this.props.form.resetFields();
        this.locals._nodeId = node.id;
        this.setState({
            showCreateModalState:true
        });
    },
    hideAddModal(){
        this.setState({
            showCreateModalState:false
        });
    },

    showModifyModal(node){
        this.setState({
            modify_id:node.id,
            modify_config:node.config,
            modify_description:node.description,
            modify_icon:node.icon,
            modify_name:node.name,
            modify_other:node.other,
            modify_url:node.url,

            showModifyModalState:true
        });
    },
    hideModifyModal(){
        this.setState({
            showModifyModalState:false
        });
    },

    modifyMenuAction(e) {
        e.preventDefault();

        let params = {
            id:e.target.modify_id.value,
            description:e.target.modify_description.value,
            icon:e.target.modify_icon.value,
            config:e.target.modify_config.value,
            name:e.target.modify_name.value,
            other:e.target.modify_other.value,
            url:this.state.modify_url
        }

        Ajax.post("./menu/update", params, r => {
            if(r > 0) {
                message.success('修改成功！');
                this.hideModifyModal();
                this.reloadData();
            } else {
                message.error('修改失败！');
            }
        });

    },

    query(){

        if(this.locals.params.createDate.length == 0){
            this.locals.params.createDate = "";
        }
        if(this.locals.params.updateDate.length == 0){
            this.locals.params.updateDate = "";
        }
        this.doList();
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
                dataTable[i]['control']= <div><a  onClick={this.showAddModal.bind(this,dataTable[i])}>增加子节点</a>&nbsp;&nbsp;|&nbsp;&nbsp;{statusControl}&nbsp;&nbsp;|&nbsp;&nbsp;<a onClick={this.showModifyModal.bind(this,dataTable[i])}>编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a onClick={this.deleteAction.bind(this,dataTable[i])}>删除</a></div>;
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
                checkType:"none",
                turnable:false,
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
                              onSelect={this.onSelect} selectedKeys={this.state.selectedKeys}
                              onCheck={this.onCheck}>
                            {loop(this.state.gData)}
                        </Tree>
                    </Col>
                    <Col span={20}>
                        <Command>
                            <Button type="primary" onClick={this.deleteBatchAction} >删除全部选中</Button>
                        </Command>
                        <br/><br/>
                        <MyTable {...getTableData(this.state.table.data)}/>
                    </Col>
            </Row>
            <Modal title="新增菜单" visible={this.state.showCreateModalState} footer={null} onCancel={this.hideAddModal}>
                <Form horizontal form={this.props.form} onSubmit={this.addChildNodeAction}>
                    <FormItem  label="菜单名称: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="菜单名称:" name="name" placeholder="菜单名称"   required />
                    </FormItem>
                    <FormItem  label="URL: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="URL:" name="url"  placeholder="URL"/>
                    </FormItem>
                    <FormItem  label="配置: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="配置:" name="config"  placeholder="配置" />
                    </FormItem>
                    <FormItem  label="图标: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="图标:" name="icon"  placeholder="图标" />
                    </FormItem>
                    <FormItem  label="描述: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="描述:" name="description"  placeholder="描述" />
                    </FormItem>
                    <FormItem  label="其他: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="其他:" name="other"  placeholder="其他" />
                    </FormItem>
                    <FormItem  wrapperCol={{  offset: 10 }} >
                        <Button type="primary" htmlType="submit">提交</Button>
                    </FormItem>
                </Form>
            </Modal>

            <Modal title="编辑菜单" visible={this.state.showModifyModalState} footer={null} onCancel={this.hideModifyModal}>
                <Form horizontal form={this.props.form} onSubmit={this.modifyMenuAction}>
                    <FormItem  label="菜单编码: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="菜单编码:" name="modify_id" placeholder="菜单编码" value={this.state.modify_id} onChange={e => {this.setState({modify_id :e.target.value})}} disabled />
                    </FormItem>
                    <FormItem  label="菜单名称: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="菜单名称:" name="modify_name" value={this.state.modify_name} onChange={e => {this.setState({modify_name :e.target.value})}} placeholder="菜单名称"   required />
                    </FormItem>
                    <FormItem  label="URL: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="URL:" name="modify_url" value={this.state.modify_url} onChange={e => {this.setState({modify_url :e.target.value})}} placeholder="URL"/>
                    </FormItem>
                    <FormItem  label="配置: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="配置:" name="modify_config" value={this.state.modify_config} onChange={e => {this.setState({modify_config :e.target.value})}} placeholder="配置" />
                    </FormItem>
                    <FormItem  label="图标: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="图标:" name="modify_icon" value={this.state.modify_icon} onChange={e => {this.setState({modify_icon :e.target.value})}} placeholder="图标" />
                    </FormItem>
                    <FormItem  label="描述: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="描述:" name="modify_description" value={this.state.modify_description} onChange={e => {this.setState({modify_description :e.target.value})}} placeholder="描述" />
                    </FormItem>
                    <FormItem  label="其他: " labelCol={{ span: 7 }} wrapperCol={{ span: 13 }}>
                        <Input labelName="其他:" name="modify_other" value={this.state.modify_other} onChange={e => {this.setState({modify_other :e.target.value})}} placeholder="其他" />
                    </FormItem>
                    <FormItem  wrapperCol={{  offset: 10 }} >
                        <Button type="primary" htmlType="submit">提交</Button>
                    </FormItem>
                </Form>
            </Modal>
		</div>);
	}
});

//UrlManager = createForm()(UrlManager);
//
//<Query onSubmit= {formData =>{this.locals.params = formData;this.query()}}>
//    <Input labelName="名称:" name="id" placeholder="名称"/>
//    <Input labelName="URL:" name="url" placeholder="URL" />
//    <Input labelName="配置:" name="config" placeholder="配置" />
//    <Input labelName="权限组:" name="groupId" placeholder="权限组"/>
//    <Input labelName="其他:" name="other" placeholder="其他" />
//    <Input labelName="状态:" name="status"  placeholder="状态" />
//</Query>

//ReactDOM.render(<UrlManager />, document.getElementById("page-wrapper"));
//
Tools.tempVar("UrlManager",UrlManager);