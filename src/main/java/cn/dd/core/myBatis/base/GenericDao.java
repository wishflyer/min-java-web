package cn.dd.core.myBatis.base;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by czdujb on 2015/12/24.
 */

public interface GenericDao<E, PK extends Serializable> {
    E getById(PK id) throws DataAccessException;

    //分页查询
    List<E> getByPage(Map param) throws DataAccessException;

    int deleteById(PK id) throws DataAccessException;

    int insert(E entity) throws DataAccessException;

    int update(E entity) throws DataAccessException;

    //批量删除
    int deleteBatch(PK[] ids) throws DataAccessException;

    //批量插入
    int insertBatch(List<E> entityList) throws DataAccessException;
}
