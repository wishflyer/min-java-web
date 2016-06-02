package cn.dd.core.admin.utils;

import cn.dd.core.admin.entity.Menu;

import java.util.*;

/**
 * Created by wishflyer on 2016/6/2.
 */
public class MenuUtils {


    public class TreeNodeEntity{
        //树的key 即uuid,根节点为"root"
        public String key;
        //父节点，根节点为-1
        public String parentId;
        //是否禁掉响应
        //public String disabled;
        //禁掉 checkbox
        //public String disableCheckbox;
        //标题
        public String title;
        //设置为叶子节点
        //public String isLeaf;

        //图标
        public String icon;
        //状态
        public int status;

        public LinkedList<TreeNodeEntity> children;

    }


    public TreeNodeEntity getTreeNodeEntity(Menu menu){
        TreeNodeEntity treeNodeEntity = new TreeNodeEntity();
        treeNodeEntity.key = menu.getId();
        treeNodeEntity.title = menu.getName();
        treeNodeEntity.parentId = menu.getParentId();
        treeNodeEntity.icon = menu.getIcon();
        treeNodeEntity.status = menu.getStatus();
        treeNodeEntity.children = new LinkedList<TreeNodeEntity>();
        return treeNodeEntity;
    }


    public TreeNodeEntity getTree(List<Menu> menuList){

        String parentId;
        TreeNodeEntity treeNodeEntity;

        //初始化菜单根节点rootEntity
        TreeNodeEntity rootEntity = new TreeNodeEntity();
        rootEntity.title = "根节点";
        rootEntity.key= "-1";
        rootEntity.parentId = "-1";
        rootEntity.icon = "home";
        rootEntity.status = 0;
        rootEntity.children = new LinkedList<TreeNodeEntity>();



        //已建立索引的树(按菜单key）
        HashMap<String,TreeNodeEntity> index = new HashMap<String, TreeNodeEntity>();
        index.put(rootEntity.key,rootEntity);

        //保存未加入到主树的Entity(按菜单parentId）
        HashMap<String,LinkedList<TreeNodeEntity>> missParentTree = new HashMap<String, LinkedList<TreeNodeEntity>>();
        
        Iterator<Menu> iterator = menuList.iterator();
        while(iterator.hasNext()){

            Menu menu = iterator.next();

            //获取父节点
            parentId = menu.getParentId();

            //获取children节点
            TreeNodeEntity childrenEntity = getTreeNodeEntity(menu);

            //在missParentTree查找是否最新获取的这个children节点是某个节点的父节点
            if(missParentTree.containsKey(menu.getId())){
                //找到爸爸了～挂载到父节点中
                childrenEntity.children = missParentTree.get(menu.getId());

                //原来没找到父节点的元素现在已经找到了归宿了。。。
                missParentTree.remove(menu.getId());
            }

            //父节点是否已加入列表中,找到要插入点位置
            if(index.containsKey(parentId)){//已加入
                TreeNodeEntity parentParentEntity = index.get(parentId);
                parentParentEntity.children.add(childrenEntity);
            }else{
                //如果没找到可以挂载的节点，则先把这棵树加入到missParentTree中，等待后续找到parentId节点
                if(missParentTree.containsKey(parentId)){
                    missParentTree.get(parentId).add(childrenEntity);
                }else{
                    LinkedList<TreeNodeEntity> newLinkedListTreeNodeEntity = new LinkedList<TreeNodeEntity>();
                    newLinkedListTreeNodeEntity.add(childrenEntity);
                    missParentTree.put(parentId,newLinkedListTreeNodeEntity);
                }

            }
            //加入索引
            index.put(childrenEntity.key,childrenEntity);
        }

        //递归设置节点isSelected状态
        //processTreeSelectedStatus(rootEntity);

        return rootEntity;

    }

}
