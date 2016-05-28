package cn.dd.core.myBatis.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    public List<E> getByPage(Map param){
        return this.getSqlSession().selectList(this.getByPageStatement(), param);
    }

    public int deleteById(PK id) {
        int affectCount = this.getSqlSession().delete(this.getDeleteStatement(), id);
        return affectCount;
    }

    public int deleteBatch(PK[] id) {
        int affectCount = this.getSqlSession().delete(this.getDeleteBatchStatement(), id);
        return affectCount;
    }

    public int insert(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().insert(this.getInsertStatement(), entity);
        return affectCount;
    }

    public int insertBatch(List<E> entityList){
        this.prepareObjectListForSaveOrUpdate(entityList);
        int affectCount = this.getSqlSession().insert(this.getInsertBatchStatement(), entityList);
        return affectCount;
    }

    public int update(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().update(this.getUpdateStatement(), entity);
        return affectCount;
    }

    public int setValid(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().update(this.getSetValidStatement(), entity);
        return affectCount;
    }
    public int setValidBatch(PK[] id) {
        int affectCount = this.getSqlSession().update(this.getSetValidBatchStatement(), id);
        return affectCount;
    }
    public int setInvalid(E entity) {
        this.prepareObjectForSaveOrUpdate(entity);
        int affectCount = this.getSqlSession().update(this.getSetInvalidStatement(), entity);
        return affectCount;
    }
    public int setInvalidBatch(PK[] id) {
        int affectCount = this.getSqlSession().update(this.getSetInvalidBatchStatement(), id);
        return affectCount;
    }

    protected void prepareObjectForSaveOrUpdate(E o) {
    }


    protected void prepareObjectListForSaveOrUpdate(List<E> o) {
    }

    public abstract String getMybatisMapperNamesapce();

    public String getFindByPrimaryKeyStatement() {
        return this.getMybatisMapperNamesapce() + ".getById";
    }

    public String getByPageStatement(){return this.getMybatisMapperNamesapce() + ".getByPage";}

    public String getInsertStatement() {
        return this.getMybatisMapperNamesapce() + ".insert";
    }

    public String getInsertBatchStatement() {
        return this.getMybatisMapperNamesapce() + ".insertBatch";
    }

    public String getUpdateStatement() {
        return this.getMybatisMapperNamesapce() + ".update";
    }

    public String getDeleteStatement() {
        return this.getMybatisMapperNamesapce() + ".delete";
    }

    public String getDeleteBatchStatement() {
        return this.getMybatisMapperNamesapce() + ".deleteBatch";
    }

    public String getSetValidStatement() {
        return this.getMybatisMapperNamesapce() + ".setValid";
    }

    public String getSetValidBatchStatement() {
        return this.getMybatisMapperNamesapce() + ".setValidBatch";
    }

    public String getSetInvalidStatement() {
        return this.getMybatisMapperNamesapce() + ".setInvalid";
    }

    public String getSetInvalidBatchStatement() {
        return this.getMybatisMapperNamesapce() + ".setInvalidBatch";
    }

    public void flush() {
    }
}
