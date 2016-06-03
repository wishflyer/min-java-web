package cn.dd.core.admin.dao.impl;

import cn.dd.core.myBatis.base.BaseMybatisDao;
import cn.dd.core.admin.entity.Menu;
import cn.dd.core.admin.dao.MenuDao;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository("menuDao")
public class MenuDaoImpl extends BaseMybatisDao<Menu,String> implements MenuDao{
	
	@Override
	public String getMybatisMapperNamesapce() {
		return "cn.dd.core.admin.dao.Menu";
	}

	/*------------------------------添加语句--------START--------------------------------------*/
	@Override
	public int changeParent(Map<String, String> paramMap) {
		int affectCount = this.getSqlSession().update(this.getChangeParentStatement(), paramMap);
		return affectCount;
	}

	public String getChangeParentStatement() {
		return this.getMybatisMapperNamesapce() + ".changeParent";
	}
	/*------------------------------添加语句--------END--------------------------------------*/
}
