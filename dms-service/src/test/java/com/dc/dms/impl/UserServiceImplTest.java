package com.dc.dms.impl;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.domain.model.User;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.exception.DuplicateUserException;
import com.dc.dms.intf.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, DatabaseJpaConfig.class })
public class UserServiceImplTest {

	private BigInteger RETURNED_USER_ID = new BigInteger("1");
	
	@InjectMocks
	@Autowired
	UserService userService = null;

	@Mock
	UserDao userDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testRegisterUser_withnoException() {

		try {
			// mock expectations
			Mockito.when(userDao.getUserByCredentials(Matchers.isA(UserEntity.class))).thenReturn(null);
			Mockito.when(userDao.create(Matchers.isA(UserEntity.class))).thenReturn(createdUserEntity());

			User user = new User();

			user.setFirstName("Sachin");
			user.setLoginId("sjoshi");

			User returnedUser = userService.registerUser(user);
			
			Assert.assertEquals(RETURNED_USER_ID, returnedUser.getUserId());
			
		} catch (DMSDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	@Test(expected = DuplicateUserException.class)
	public void testRegisterUser_with_DuplicateUserException() {
		
		try {
			Mockito.when(userDao.getUserByCredentials(Matchers.isA(UserEntity.class))).thenReturn(createdUserEntity());
			
			User user = new User();

			user.setFirstName("Sachin");
			user.setLoginId("sjoshi");

			
			User returnedUser = userService.registerUser(user);
		} catch (DMSDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	private UserEntity createdUserEntity() {
		UserEntity createdUser = new UserEntity();
		createdUser.setEmail("mailtosmj@gmail.com");
		createdUser.setFirstName("Sachin");
		createdUser.setLastName("Joshi");
		createdUser.setLoginId("smjoshi22");
		createdUser.setOrgName("VMS India");
		createdUser.setUserId(new BigInteger("1"));

		return createdUser;
	}

}
