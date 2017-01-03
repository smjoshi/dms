package com.dc.dms.rest.resource;

import com.dc.dms.Application;
import com.dc.dms.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

/**
 * Created by sacjoshi on 12/15/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(properties = {"env = local"})
public class ProductDocDetailResourceITest extends BaseTest{

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TestRestTemplate restTemplate = null;


    @Test
    public void getProductDocuments() throws Exception {

        preparePreDatabaseCondition("test/sql/scenario/get_product_document_details.sql"  );

        try {
            ResponseEntity<String> response = restTemplate.getForEntity("/api/details/product/1000001/10", String.class);
            Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
            Assert.assertNotNull(response.getBody());
            System.out.print(response);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

}