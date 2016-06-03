package cn.dd.core.admin.dao;

import cn.dd.core.myBatis.base.GenericDao;
import cn.dd.core.admin.entity.Menu;

import java.util.Map;

public interface  MenuDao extends GenericDao<Menu,String>{

    /**
     * 改变父节点
     * @param paramMap 参数parentId childId
     * @return updage的结果
     */
    int changeParent(Map<String,String> paramMap);

}
