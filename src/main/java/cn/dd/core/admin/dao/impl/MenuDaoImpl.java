package cn.dd.core.admin.dao.impl;

import cn.dd.core.myBatis.base.BaseMybatisDao;
import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.dao.MenuDao;
import org.springframework.stereotype.Repository;


@Repository("menuDao")
public class MenuDaoImpl extends BaseMybatisDao<Menu,String> implements MenuDao{
	
	@Override
	public String getMybatisMapperNamesapce() {
		return "cn.dd.core.admin.dao.Menu";
	}

}
