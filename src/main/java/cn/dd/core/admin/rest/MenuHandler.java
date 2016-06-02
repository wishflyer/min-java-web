package cn.dd.core.admin.rest;

import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.service.MenuService;
import cn.dd.core.utils.JsonUtils;
import cn.dd.core.utils.UUIDUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
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
        System.out.println("/addMenu ..................................");
        System.out.println("jsonStr:"+jsonStr);
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


}