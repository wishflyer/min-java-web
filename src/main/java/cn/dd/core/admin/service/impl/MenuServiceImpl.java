package cn.dd.core.admin.service.impl;

import cn.dd.core.admin.dao.MenuDao;
import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.service.MenuService;
import javax.annotation.Resource;

import cn.dd.core.admin.utils.MenuUtils;
import cn.dd.core.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
	private MenuDao menuDao;

    @Override
    public String getMenuJSON(Map paramMap) {
        //获得List<Menu>
        Menu condition = new Menu();
        condition.setGroupId((String)paramMap.get("groupId"));
        List<Menu> menulist = menuDao.getByParam(condition);
        //获得Tree
        MenuUtils menuUtils = new MenuUtils();
        MenuUtils.TreeNodeEntity tree = menuUtils.getTree(menulist);
        tree.title = "目录根节点";
        //转换为JSON
        String menuJSON = null;
        try {
            menuJSON = JsonUtils.toJson(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(menuJSON);
        return menuJSON;
    }

    @Override
    public Map<String, Menu> getMenuMap(Map paramMap)  {
        //获得List<Menu>
        Menu condition = new Menu();
        condition.setGroupId((String)paramMap.get("groupId"));
        List<Menu> menulist = menuDao.getByParam(condition);
        //转换为Map
        Map<String,Menu> menuMap = new HashMap<String, Menu>();
        Iterator<Menu> iterator = menulist.iterator();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            menuMap.put(menu.getId(),menu);
        }
        //添加虚拟根目录节点-----
        Menu rootMenu = new Menu();
        rootMenu.setId("-1");
        rootMenu.setIcon("home");
        rootMenu.setParentId("-9999");
        rootMenu.setDescription("虚拟根目录节点");
        rootMenu.setName("目录根节点");
        rootMenu.setOther("-");
        rootMenu.setStatus(0);
        rootMenu.setUrl("-");
        rootMenu.setGroupId("-");
        rootMenu.setConfig("-");
        menuMap.put(rootMenu.getId(),rootMenu);
        //------------------
        return menuMap;
    }


    @Override
    public String getRouterConfig()  {
        //获得List<Menu>
        Menu condition = new Menu();
        List<Menu> menulist = menuDao.getByParam(condition);
        StringBuilder routerConfig = new StringBuilder();
        routerConfig.append("{");
        Iterator<Menu> iterator = menulist.iterator();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            if(!menu.getConfig().equals("")){
                routerConfig.append(menu.getConfig()).append(",");
            }
        }
        routerConfig.append("}");
        return routerConfig.toString();
    }

    @Override
    public int addMenu(Menu newMenu) {
        return menuDao.insert(newMenu);
    }

    @Override
    public int setInvalid(Menu entity) {
        return menuDao.setInvalid(entity);
    }

    @Override
    public int setValid(Menu entity) {
        return menuDao.setValid(entity);
    }

    @Override
    public int deleteById(String id) {
        return menuDao.deleteById(id);
    }

    @Override
    public int deleteBatch(String[] id) {
        return menuDao.deleteBatch(id);
    }

    @Override
    public int update(Menu entity) {
        return menuDao.update(entity);
    }

    @Override
    public int changeParent(String parentId, String childId) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("parentId",parentId);
        paramMap.put("childId",childId);
        return menuDao.changeParent(paramMap);
    }
}
