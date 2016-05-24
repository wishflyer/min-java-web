package cn.dd.core.myBatis.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by czdujb on 2015/12/24.
 */

public abstract class BaseMybatisDao<E, PK extends Serializable> extends SqlSessionDaoSupport implements GenericDao<E, PK> {
    private static transient Logger logger = LoggerFactory.getLogger(BaseMybatisDao.class);

    public BaseMybatisDao() {
    }

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public E getById(PK id) {
        return this.getSqlSession().selectOne(this.getFindByPrimaryKeyStatement(), id);
    }

    public int deleteById(PK id) {
        int affectCount = this.getSqlSession().delete(this.getDeleteStatement(), id);
        return affectCount;
    }

    public int insert(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().insert(this.getInsertStatement(), entity);
        return affectCount;
    }

    public int update(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().update(this.getUpdateStatement(), entity);
        return affectCount;
    }

    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    public abstract String getMybatisMapperNamesapce();

    public String getFindByPrimaryKeyStatement() {
        return this.getMybatisMapperNamesapce() + ".getById";
    }

    public String getInsertStatement() {
        return this.getMybatisMapperNamesapce() + ".insert";
    }

    public String getUpdateStatement() {
        return this.getMybatisMapperNamesapce() + ".update";
    }

    public String getDeleteStatement() {
        return this.getMybatisMapperNamesapce() + ".delete";
    }

    public void flush() {
    }
}
