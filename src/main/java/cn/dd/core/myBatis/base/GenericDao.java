package cn.dd.core.myBatis.base;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;

/**
 * Created by czdujb on 2015/12/24.
 */

public interface GenericDao<E, PK extends Serializable> {
    E getById(PK id) throws DataAccessException;

    int deleteById(PK id) throws DataAccessException;

    int insert(E entity) throws DataAccessException;

    int update(E entity) throws DataAccessException;
}
