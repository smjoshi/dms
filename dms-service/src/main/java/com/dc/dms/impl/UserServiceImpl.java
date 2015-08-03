package com.dc.dms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.domain.model.User;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao = null;

	@Override
	public User registerUser(User user) throws DMSException {
		try {
			
			UserEntity userToBeCreated = prepareUserEntity(user);
			userToBeCreated = userDao.create(userToBeCreated);
			user.setUserId(userToBeCreated.getUserId());
		} catch (DMSDaoException e) {
			e.printStackTrace();
			throw new DMSException();
		}
		return user;
	}


	@Override
	public User getUserByLogin(User user) throws DMSException {
		UserEntity fetchedUser = null;
		
		try {
			fetchedUser = userDao.getUserByLoginId(user.getLoginId());
			user = populateUserModel(fetchedUser);
		} catch (DMSDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DMSException();
		}
		return user;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	private UserEntity prepareUserEntity(User user){
		UserEntity userEntity = new UserEntity();
		
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setLoginId(user.getLoginId());
		userEntity.setOrgName(user.getOrgName());
		userEntity.setPassword(user.getPassword());
		userEntity.setUserId(user.getUserId());
		
		return userEntity;
		
	}
	
	
	private User populateUserModel(UserEntity userEntity){
		User user = null;
		
		user.setEmail(userEntity.getEmail());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setLoginId(userEntity.getLoginId());
		user.setOrgName(user.getOrgName());
		user.setPassword(userEntity.getPassword());
		user.setUserId(userEntity.getUserId());
		
		return user;
		
	}
	
	

}
