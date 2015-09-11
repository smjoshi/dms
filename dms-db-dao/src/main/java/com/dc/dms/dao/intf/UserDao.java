package com.dc.dms.dao.intf;

import com.dc.dms.base.BaseDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.entity.UserEntity;

/**
 * Created by SJoshi on 6/24/2015.
 */
public interface UserDao extends BaseDao<UserEntity> {

    public UserEntity getUserByLoginId(String loginId) throws DMSDaoException;
    
    public UserEntity getUserByCredentials(UserEntity user) throws DMSDaoException;
    
    
    
}
