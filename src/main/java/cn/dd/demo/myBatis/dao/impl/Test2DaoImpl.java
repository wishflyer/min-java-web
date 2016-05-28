package cn.dd.demo.myBatis.dao.impl;

import cn.dd.core.myBatis.base.BaseMybatisDao;
import cn.dd.demo.myBatis.entity.Test2;
import cn.dd.demo.myBatis.dao.Test2Dao;
import org.springframework.stereotype.Repository;


@Repository("test2Dao")
public class Test2DaoImpl extends BaseMybatisDao<Test2,String> implements Test2Dao{
	
	@Override
	public String getMybatisMapperNamesapce() {
		return "cn.dd.demo.myBatis.dao.Test2";
	}

}
