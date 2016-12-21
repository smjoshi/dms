package com.dc.dms.dao.impl;

import java.math.BigInteger;

import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.utils.test.AbstractTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.entity.UserEntity;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseJpaConfig.class)
@Transactional
public class UserImplTest extends BaseTest {

	@Autowired
	UserDao userDao = null;

	@Test
	public void testGetUserByLoginId() throws Exception{

		String USER_LOGIN_ID = "smjoshi";
		//prepare test data
		preparePreDatabaseCondition("test/sql/user_update_scenario.sql");

		UserEntity fetchedUser = userDao.getUserByLoginId(USER_LOGIN_ID);

		assertEquals("smjoshi", fetchedUser.getLoginId());
		assertEquals("mailtosmj@gmail.com", fetchedUser.getEmail());
		assertEquals("Sachin", fetchedUser.getFirstName());
		assertEquals("Joshi", fetchedUser.getLastName());

	}

	@Test
	public void testReadByKey() throws Exception{
		//prepare pre database condition for test
		preparePreDatabaseCondition("test/sql/user_update_scenario.sql");

		UserEntity user  = new UserEntity();
		user.setUserId(new BigInteger("29"));

		UserEntity fetchedUser = userDao.readByKey(user);

		assertEquals("smjoshi", fetchedUser.getLoginId());
		assertEquals("mailtosmj@gmail.com", fetchedUser.getEmail());
		assertEquals("Sachin", fetchedUser.getFirstName());
		assertEquals("Joshi", fetchedUser.getLastName());

	}

	@Test
	public void testCreateUser_withNoException() {
		
		UserEntity user = new UserEntity();

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
	public void test_user_Update() throws Exception{

		// This method tests the updates of entity
		//prepare pre database condition for test
		preparePreDatabaseCondition("test/sql/user_update_scenario.sql");

		//read by key
		UserEntity user = new UserEntity();
		user.setUserId(new BigInteger("29"));
		UserEntity fetchedUser = null;

		fetchedUser = userDao.readByKey(user);
		fetchedUser.setEmail("testmail@gmail.com");

		//update user attribute
		userDao.update(fetchedUser);
		UserEntity updatedUser = userDao.readByKey(user);

		Assert.assertEquals(updatedUser.getEmail(),"testmail@gmail.com");
	}


	@Test
	public void test_user_delete() throws Exception{

		//prepare pre database condition for test
		preparePreDatabaseCondition("test/sql/user_update_scenario.sql");

		//read by key
		UserEntity user = new UserEntity();
		user.setUserId(new BigInteger("29"));

		boolean	isDeleted = userDao.delete(user);
		Assert.assertTrue(isDeleted);

	}


}
