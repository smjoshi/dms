package com.dc.dms.rest.resource;

import com.dc.dms.Application;
import com.dc.dms.BaseTest;
import com.dc.dms.domain.model.Product;
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.model.ProductDocConfigurationBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 1/3/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(properties = {"env = local"})
public class ProductDocConfResourceTest extends BaseTest{

    @Autowired
    private TestRestTemplate restTemplate = null;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllConfigurations() throws Exception {

        //pre test data
        preparePreDatabaseCondition("test/sql/scenario/get_document_configurations.sql"  );

        ResponseEntity<String> response = restTemplate.getForEntity("/api/configs/product/10", String.class);

        TypeReference<List<ProductDocConfiguration>> trf = new TypeReference<List<ProductDocConfiguration>>() {};
        List<ProductDocConfiguration> configurations = objectMapper.readValue(response.getBody(), trf);

        assertEquals(4, configurations.size());



    }

    @Test
    public void getConfiguration() throws Exception {

        //pre test data
        preparePreDatabaseCondition("test/sql/scenario/get_document_configurations.sql"  );

        ResponseEntity<String> response = restTemplate.getForEntity("/api/configs/300003", String.class);
        ProductDocConfiguration configuration = objectMapper.readValue(response.getBody(), ProductDocConfiguration.class);

        assertEquals(300003, configuration.getDocConfId());

    }

    @Test
    public void upsertConfigurations() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        //pre test data
        preparePreDatabaseCondition("test/sql/scenario/get_document_configurations.sql"  );

        List<ProductDocConfiguration> configurations = prepareTwoDocumentConfigurations();

        String jsonString = mapper.writeValueAsString(configurations);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //create HTTP request
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonString, requestHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/configs/list", httpEntity, String.class);

        TypeReference<List<ProductDocConfiguration>> trf = new TypeReference<List<ProductDocConfiguration>>() {};
        List<ProductDocConfiguration> fetchedConfigurations = objectMapper.readValue(response.getBody(), trf);

        assertEquals(2, fetchedConfigurations.size());
    }

    @Test
    public void upsertConfiguration() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        //pre test data
        preparePreDatabaseCondition("test/sql/scenario/get_document_configurations.sql"  );
        List<ProductDocConfiguration> configurations = prepareTwoDocumentConfigurations();

        String jsonString = mapper.writeValueAsString(configurations.get(0));

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //create HTTP request
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonString, requestHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/configs/configuration", httpEntity, String.class);
        ProductDocConfiguration configuration = objectMapper.readValue(response.getBody(), ProductDocConfiguration.class);

        assertEquals("GLAMSHOT", configuration.getDocTypeCode());


    }

    @Test
    public void deleteConfigConfiguration() throws Exception {

    }

    private List<ProductDocConfiguration> prepareTwoDocumentConfigurations(){

        List<ProductDocConfiguration> configurations = new ArrayList<ProductDocConfiguration>();

        ProductDocConfigurationBuilder builder1 = new ProductDocConfigurationBuilder();
        configurations.add(builder1.withProductId(10)
                .withDescription("Glammershot Image")
                .withDocTypeCode("GLAMSHOT")
                .withGroupId(null)
                .withIsMandatory(true)
                .withGroupId(null)
                .withIsMultipleItemAllowed(false)
                .build());

        configurations.add(builder1.withProductId(10)
                .withDescription("Optional Image")
                .withDocTypeCode("OPTIMAGE")
                .withGroupId(null)
                .withIsMandatory(false)
                .withGroupId(null)
                .withIsMultipleItemAllowed(false)
                .build());


        return configurations;

    }

}