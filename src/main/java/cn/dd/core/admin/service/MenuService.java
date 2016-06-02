package cn.dd.core.admin.service;

import cn.dd.core.admin.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface MenuService {

    /**
     * 获得Menu的JSON对象
     * @return Menu的JSON对象
     */
    String getMenuJSON();

    /**
     * 获得Menu的Map对象
     * @return Menu的Map对象
     */
    Map<String,Menu> getMenuMap();

    /**
     * 添加Menu
     * @return 成功添加条数
     */
    int addMenu(Menu newMenu);


    /**
     * 置无效
     * @return 成功置无效条数
     */
    int setInvalid(Menu entity);


    /**
     * 置无效
     * @return 成功置有效条数
     */
    int setValid(Menu entity);

}
