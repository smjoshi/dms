package com.dc.dms.rest.resource;

import com.dc.dms.Application;
import com.dc.dms.BaseTest;
import com.dc.dms.domain.model.User;
import com.dc.dms.model.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 12/20/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@TestPropertySource(properties = {"env = local"})
public class UserResourceTest extends BaseTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getUserDetails() throws Exception {

    }

    @Test
    public void registerUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        //prepare user object
        UserBuilder builder = new UserBuilder();

        User userToBeCreated = builder.withEmail("mailtosmj@gmail.com")
                        .withFirstName("Sachin")
                        .withLastName("Joshi")
                        .withLoginId("mailtosmj")
                        .withOrgName("Auto Trader India")
                        .withOrgType(" Car Delaership").build();

        String jsonString = mapper.writeValueAsString(userToBeCreated);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //create HTTP request
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonString, requestHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register", httpEntity, String.class);

        User createdUser = mapper.readValue(response.getBody(),User.class);
        //verify against the database
        Map<String, Object> userData = this.executeValidationQuery("Select * from Users where user_id = " + createdUser.getUserId());

        assertEquals(userData.get("USER_ID"),createdUser.getUserId());

    }

}