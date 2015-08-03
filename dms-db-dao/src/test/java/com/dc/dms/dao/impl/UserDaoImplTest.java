package com.dc.dms.dao.impl;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.entity.UserEntity;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseJpaConfig.class)
public class UserDaoImplTest {
	
		
	@Autowired
	UserDao userDao = null;

	
	

	@Test
	public void testGetUserByLoginId() {
		Assert.fail("Not yet implemented");
	}

	public void testReadByKey() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void testCreateUser_withNoException() {
		
		UserEntity user = new UserEntity();
		user.setUserId(new BigInteger("1"));
		user.setFirstName("Test_First");
		user.setLastName("Test_Last");
		user.setLoginId("testId1");
		user.setOrgName("Test Orgnization");
		user.setPassword("testpwd");
		user.setEmail("test@testorg.com");
		
		try {
			userDao.create(user);
			UserEntity fetchedUser = userDao.readByKey(user);
			
			Assert.assertNotNull(fetchedUser);
			
		} catch (DMSDaoException dde) {
			Assert.fail("User creation test fail : " + dde.getMessage());
		}
		
		
	}

	public void testUpdate() {
		Assert.fail("Not yet implemented");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
