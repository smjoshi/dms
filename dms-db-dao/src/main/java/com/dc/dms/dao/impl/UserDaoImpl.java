package com.dc.dms.dao.impl;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.entity.UserEntity;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by SJoshi on 6/24/2015.
 */
@Repository
@Qualifier("userDao")
@Transactional
public class UserDaoImpl extends AbstractDmsDao implements UserDao {


    public UserEntity getUserByLoginId(String loginId) throws DMSDaoException {
        String queryStr = "SELECT u FROM User u where u.loginId = ?1";
        TypedQuery<UserEntity> query = entityManager.createQuery(queryStr, UserEntity.class);
        query.setParameter(1, loginId);
        return query.getSingleResult();

    }

    public UserEntity readByKey(UserEntity user) throws DMSDaoException {
        return entityManager.find(UserEntity.class, user.getUserId());
    }

    public UserEntity create(UserEntity user) throws DMSDaoException {
        entityManager.persist(user);
        return user;
    }

    public boolean update(UserEntity user) throws DMSDaoException {
        entityManager.merge(user);
        return true;
    }
}
