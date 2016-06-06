package cn.dd.core.admin.rest;

import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.service.MenuService;
import cn.dd.core.utils.JsonUtils;
import cn.dd.core.utils.UUIDUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wishflyer on 2016/5/18.
 */
@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Resource
    private MenuService menuService;

    @RequestMapping("/getMenuJSON")
    public String getMenuJSON() {
        return menuService.getMenuJSON();
    }


    @RequestMapping("/getRouterConfig")
    public String getRouterConfig() throws IOException {
        String routerConfig = menuService.getRouterConfig();
        //String json =  JsonUtils.toJson(routerConfig);
        return routerConfig;
    }

//
//    @RequestMapping("/getMenuConfig")
//    public String getMenuConfig() throws IOException {
//        String menuConfig = menuService.getMenuConfig();
//        //String json =  JsonUtils.toJson(routerConfig);
//        return menuConfig;
//    }

    @RequestMapping("/getMenuMap")
    public String getMenuMap() {
        Map<String, Menu> menuMap = menuService.getMenuMap();
        String jsonMap = null;
        try {
            jsonMap = JsonUtils.toJson(menuMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }


    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public int addMenu(@RequestBody String jsonStr) {
        //System.out.println("/addMenu ..................................");
        //System.out.println("jsonStr:"+jsonStr);
        Map<String, Object> paramMap = new HashMap<>();
        int count = 0;
        try {
            paramMap = JsonUtils.parseMap(jsonStr);
            Menu newMenu = new Menu();
            newMenu.setId(UUIDUtils.getUUID());
            newMenu.setConfig((String) paramMap.get("config"));
            newMenu.setDescription((String) paramMap.get("description"));
            newMenu.setIcon((String) paramMap.get("icon"));
            newMenu.setName((String) paramMap.get("name"));
            newMenu.setOther((String) paramMap.get("other"));
            newMenu.setParentId((String) paramMap.get("id"));
            newMenu.setUrl((String) paramMap.get("url"));
            count = menuService.addMenu(newMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    @RequestMapping(value = "/setInvalid", method = RequestMethod.POST)
    public int setInvalid(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        Menu newMenu = new Menu();
        newMenu.setId((String)paramMap.get("id"));
        int count = menuService.setInvalid(newMenu);
        return count;
    }

    @RequestMapping(value = "/setValid", method = RequestMethod.POST)
    public int setValid(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        Menu newMenu = new Menu();
        newMenu.setId((String)paramMap.get("id"));
        int count = menuService.setValid(newMenu);
        return count;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public int deleteById(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        int count = menuService.deleteById((String)paramMap.get("id"));
        return count;
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public int deleteBatch(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        ArrayList<String> arrayList = (ArrayList<String>)paramMap.get("ids");
        int size=arrayList.size();
        String[] array = (String[])arrayList.toArray(new String[size]);
        int count = menuService.deleteBatch(array);
        return count;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        Menu menu = new Menu();
        menu.setId((String)paramMap.get("id"));
        menu.setUrl((String)paramMap.get("url"));
        menu.setOther((String)paramMap.get("other"));
        menu.setName((String)paramMap.get("name"));
        menu.setConfig((String)paramMap.get("config"));
        menu.setDescription((String)paramMap.get("description"));
        menu.setIcon((String)paramMap.get("icon"));
        int count = menuService.update(menu);
        return count;
    }

    @RequestMapping(value = "/changeParent", method = RequestMethod.POST)
    public int changeParent(@RequestBody String jsonStr) throws IOException {
        Map<String, Object> paramMap = JsonUtils.parseMap(jsonStr);
        int count = menuService.changeParent((String)paramMap.get("parentId"),(String)paramMap.get("childId"));
        return count;
    }

}