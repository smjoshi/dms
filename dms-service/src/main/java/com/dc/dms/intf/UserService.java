package com.dc.dms.intf;

import com.dc.dms.domain.model.User;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.exception.DuplicateUserException;

/**
 * @author SJoshi
 *
 */
public interface UserService {
	
	public User registerUser(User user) throws DMSException, DuplicateUserException;
	
	public User getUserByLogin(User user) throws DMSException;
	
	public User getUserByCredentials(User user) throws DMSException;
	
	public User addUser(User user) throws DMSException;
	
	public User deleteUser(User user) throws DMSException;
	
	public User upsertUser(User user) throws DMSException;

}
