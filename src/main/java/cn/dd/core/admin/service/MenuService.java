package cn.dd.core.admin.service;

import cn.dd.core.admin.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;


public interface MenuService {

    /**
     * 获得Menu的JSON对象
     * @return Menu的JSON对象
     */
    String getMenuJSON(Map paramMap);

    /**
     * 获得Menu的Map对象
     * @return Menu的Map对象
     */
    Map<String,Menu> getMenuMap(Map paramMap);

    /**
     * 获得RouterConfig对象
     * @return RouterConfig对象
     */
    String getRouterConfig();

    /**
     * 获得MenuConfig对象
     * @return MenuConfig对象
     */
    //String getMenuConfig() throws IOException;

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


    /**
     * 删除
     * @return 成功删除条数
     */
    int deleteById(String id);

    /**
     * 批量删除
     */
    int deleteBatch(String[] id);

    /**
     * 批量删除
     */
    int update(Menu entity);

    /**
     * 把子节点挂在父节点上
     * @param parentId 父亲节点ID
     * @param childId 子节点ID
     * @return 处理结果
     */
    int changeParent(String parentId,String childId);

}
