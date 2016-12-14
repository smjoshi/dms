package com.dc.dms.impl.integration;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.domain.model.User;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sacjoshi on 11/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, TestDatabaseJpaConfig.class })
public class UserServiceIntegrationTest extends BaseServiceTest{

    @Autowired
    private UserService userService = null;

    @Test
    public void default_create_test(){

        //create test user
        User userToBeCreated = getTestUser();
        User createdUser = null;
        try {
            createdUser = userService.addUser(userToBeCreated);
        } catch (DMSException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(createdUser.getUserId());
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private User getTestUser(){
        User user = new User();

        user.setEmail("testmail@gmaoil.com");
        user.setFirstName("Test First Name");
        user.setLastName("Test Last Name");
        user.setLoginId("testId123");
        user.setOrgName("Test Organization");
        user.setPassword("password");

        return user;
    }

}
