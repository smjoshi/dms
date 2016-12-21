package com.dc.dms.dao.impl;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * Created by SJoshi on 6/24/2015.
 */
@Repository
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Qualifier("userDao")
public class UserDaoImpl extends AbstractDmsDao implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserEntity getUserByLoginId(String loginId) throws DMSDaoException {

        UserEntity user = null;

        try {
            String queryStr = "SELECT u FROM UserEntity u where u.loginId = ?1";
            TypedQuery<UserEntity> query = entityManager.createQuery(queryStr,
                    UserEntity.class);
            query.setParameter(1, loginId);
            user = query.getSingleResult();
        } catch (Exception e) {
            logger.debug("{componentName:UserDaoImpl , methodName:getUserByLoginId , Exception: " + e.getCause() + "}");
            user = null;
        }

        return user;

    }

    public UserEntity getUserByEmailId(String emailId) throws DMSDaoException {
        logger.debug("{componentName:UserDaoImpl , methodName:getUserByEmailId ");
        UserEntity user = null;

        try {
            String queryStr = "SELECT u FROM UserEntity u where u.email = ?1";
            TypedQuery<UserEntity> query = entityManager.createQuery(queryStr,
                    UserEntity.class);
            query.setParameter(1, emailId);
            user = query.getSingleResult();
        } catch (Exception e) {
            //Set user to null;
            logger.debug("{componentName:UserDaoImpl , methodName:getUserByEmailId , Exception: " + e.getCause() + "}");
            user = null;
        }

        return user;

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


    public UserEntity getUserByCredentials(UserEntity user) throws DMSDaoException {

        logger.debug("{componentName:UserDaoImpl , methodName:getUserByCredentials ");
        UserEntity fetchedUser = null;
        if (user.getLoginId() != null) {
            fetchedUser = getUserByLoginId(user.getLoginId());
        } else if (user.getEmail() != null) {
            fetchedUser = getUserByEmailId(user.getEmail());
        }
        return fetchedUser;
    }

    public boolean delete(UserEntity userEntity) throws DMSDaoException {
        boolean deleted = true;

        entityManager.remove(entityManager.contains(userEntity) ? userEntity : entityManager.merge(userEntity));

        if (readByKey(userEntity) != null) {
            deleted = false;
        }
        return deleted;
    }


}
