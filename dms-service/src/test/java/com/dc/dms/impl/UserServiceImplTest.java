package com.dc.dms.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, DatabaseJpaConfig.class})
public class UserServiceImplTest {

	@Autowired
	UserService userService = null;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegisterUser_withnoException() {
		
		
		UserEntity user1 = new UserEntity();
		//user1.setUserId(new BigInteger("2"));
		user1.setFirstName("Sachin");
		user1.setLoginId("sjoshi");

		try {
			userService.registerUser(user1);
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
