package com.dc.dms.model;

import com.dc.dms.domain.model.User;

import java.math.BigInteger;

/**
 * Created by sacjoshi on 12/20/2016.
 */
public class UserBuilder {

    private String email = "testuser@testorg.com";
    private String firstName = "First Name";
    private String lastName = "Last Name";
    private String orgName = "Test Org Name";
    private String orgType = "Car dealership";
    private String password = "password";
    private String loginId = "smjoshi";
    private Integer userId = null;


    public UserBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withOrgName(String orgName){
        this.orgName = orgName;
        return this;
    }

    public UserBuilder withOrgType(String orgType){
        this.orgType = orgType;
        return this;
    }

    public UserBuilder withLoginId(String loginId){
        this.loginId = loginId;
        return this;
    }

    public UserBuilder withUserId(Integer userId){
        this.userId = userId;
        return this;
    }

    public User build(){
        User user = new User();
        if (this.userId != null){
            user.setUserId(new BigInteger(this.userId.toString()));
        }
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setOrgName(this.orgName);
        user.setLoginId(this.loginId);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setOrgType(this.orgType);

        return user;
    }



}
