package com.dc.dms.impl;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.UserDao;
import com.dc.dms.domain.model.User;
import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.exception.DuplicateUserException;
import com.dc.dms.intf.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DMSServiceConfig.class, TestDatabaseJpaConfig.class})
public class UserServiceImplTest {

    @InjectMocks
    @Autowired
    UserService userService = new UserServiceImpl();
    @Mock
    UserDao userDao;
    private BigInteger RETURNED_USER_ID = new BigInteger("1");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


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

            assertEquals(RETURNED_USER_ID, returnedUser.getUserId());

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


    @Test
    public void testRegisterUser_with_DuplicateUserException() {

        try {
            Mockito.when(userDao.getUserByCredentials(Matchers.isA(UserEntity.class))).thenReturn(createdUserEntity());

            User user = new User();

            user.setFirstName("Sachin");
            user.setLoginId("sjoshi");

            User returnedUser = userService.registerUser(user);

        } catch (Exception e) {
            assertThat(e, CoreMatchers.instanceOf(DuplicateUserException.class));
        }
    }

    @Test
    public void testGetUserByLogin() {

        User userToLogin = new User();
        try {

            Mockito.when(userDao.getUserByLoginId(Matchers.anyString())).thenReturn(createdUserEntity());
            userToLogin.setFirstName("Sachin");
            userToLogin.setLoginId("sjoshi");

            userToLogin = userService.getUserByLogin(userToLogin);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(RETURNED_USER_ID, userToLogin.getUserId());
    }


    public void testGetUserByCredentials() {

        User userToLogin = new User();

        try {
            Mockito.when(userDao.getUserByCredentials(Matchers.any(UserEntity.class))).thenReturn(createdUserEntity());
        } catch (Exception e) {
            // TODO: handle exception
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
