package cn.dd.core.admin.service.impl;

import cn.dd.core.admin.dao.MenuDao;
import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.service.MenuService;
import javax.annotation.Resource;

import cn.dd.core.admin.utils.MenuUtils;
import cn.dd.core.utils.JsonUtils;
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
    public String getMenuJSON() {
        //获得List<Menu>
        Menu condition = new Menu();
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
        System.out.println(menuJSON);
        return menuJSON;
    }

    @Override
    public Map<String, Menu> getMenuMap()  {
        //获得List<Menu>
        Menu condition = new Menu();
        List<Menu> menulist = menuDao.getByParam(condition);
        //转换为Map
        Map<String,Menu> menuMap = new HashMap<String, Menu>();
        Iterator<Menu> iterator = menulist.iterator();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            menuMap.put(menu.getId(),menu);
        }
        return menuMap;
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
}
