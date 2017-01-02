package com.dc.dms.rest.resource;

import com.dc.dms.BaseTest;
import com.dc.dms.domain.model.Organization;
import com.dc.dms.domain.model.User;
import com.dc.dms.model.OrgBuilder;
import com.dc.dms.model.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 12/22/2016.
 */
public class OrgnizationResourceTest extends BaseTest{

    @Autowired
    private TestRestTemplate restTemplate  = null;

    @Test
    public void addOrUpdateOrganization() throws Exception {


        //prepare pre test data
        preparePreDatabaseCondition("");


        ObjectMapper mapper = new ObjectMapper();

        //prepare user object
        OrgBuilder builder = new OrgBuilder();
        Organization orgToBeCreated = builder.withOrgName("Car Saudagar")
                .withOrgType("Car Dealership")
                .withUserId(new Long(1L))
                .build();

        String jsonString = mapper.writeValueAsString(orgToBeCreated);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //create HTTP request
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonString, requestHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/orgs/org", httpEntity, String.class);

        Organization createdOrg = mapper.readValue(response.getBody(),Organization.class);
        //verify against the database
        Map<String, Object> orgData = this.executeValidationQuery("Select * from Organization where org_id = " + createdOrg.getOrgId());

        assertEquals(orgData.get("ORG_ID"),createdOrg.getUserId().longValue());




    }


}