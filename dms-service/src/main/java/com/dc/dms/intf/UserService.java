package com.dc.dms.intf;

import com.dc.dms.domain.model.User;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;

/**
 * @author SJoshi
 *
 */
public interface UserService {
	
	public User registerUser(User user) throws DMSException;
	
	public User getUserByLogin(User user) throws DMSException;
	
	public User getUserByCredentials(User user) throws DMSException;

}
