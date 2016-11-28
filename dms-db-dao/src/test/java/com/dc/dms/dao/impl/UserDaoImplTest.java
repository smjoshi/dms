package com.dc.dms.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.utils.TestDatabaseHelper;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.entity.UserEntity;

import javax.sql.DataSource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseJpaConfig.class)
public class UserDaoImplTest implements ApplicationContextAware{

	@Autowired
	UserDao userDao = null;

	private ApplicationContext appContext;


	@Test
	public void testGetUserByLoginId() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void testReadByKey() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void testCreateUser_withNoException() {
		
		UserEntity user = new UserEntity();
		//
		// user.setUserId(new BigInteger("1"));
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

	@Test
	public void test_user_Update(){

		// This method tests the updates of entity
		//Prepare pre test data
		TestDatabaseHelper dbHelper = TestDatabaseHelper.getInstantce();
		try {
			dbHelper.executeScriptFile("test/sql/user_update_scenario.sql", getDataSource());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//read by key
		UserEntity user = new UserEntity();
		user.setUserId(new BigInteger("29"));
		UserEntity fetchedUser = null;
		try {
			fetchedUser = userDao.readByKey(user);
		} catch (DMSDaoException e) {
			e.printStackTrace();
		}

		fetchedUser.setEmail("mailtosmj@gmail.com");

		try {
			userDao.update(fetchedUser);
		} catch (DMSDaoException e) {
			e.printStackTrace();
		}

		UserEntity updatedUser = null;
		try {
			updatedUser = userDao.readByKey(user);
		} catch (DMSDaoException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(updatedUser.getEmail(),"mailtosmj@gmail.com");
	}


	@Test
	public void test_user_delete(){

		//delete previously created
		// This method tests the updates of entity
		//Prepare pre test data
		boolean isDeleted = false;
		TestDatabaseHelper dbHelper = TestDatabaseHelper.getInstantce();
		try {
			dbHelper.executeScriptFile("test/sql/user_update_scenario.sql", getDataSource());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//read by key
		UserEntity user = new UserEntity();
		user.setUserId(new BigInteger("29"));

		try {
			isDeleted = userDao.delete(user);
		} catch (DMSDaoException e) {
			e.printStackTrace();
			isDeleted = false;
		}

		Assert.assertTrue(isDeleted);

	}

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	     this.appContext = applicationContext;
	}

	private DataSource getDataSource(){
		return appContext.getBean(DataSource.class);
	}
}
