package com.dc.dms.base;

import com.dc.dms.dao.exception.DMSDaoException;

/**
 * Created by SJoshi on 6/24/2015.
 */
public interface BaseDao<T> {

    T readByKey(T t) throws DMSDaoException;

    T create(T t) throws DMSDaoException;

    boolean update(T t) throws DMSDaoException;

    boolean delete(T t) throws DMSDaoException;
}
