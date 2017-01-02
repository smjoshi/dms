package com.dc.dms.rest.resource;

import com.dc.dms.Application;
import com.dc.dms.BaseTest;
import com.dc.dms.domain.model.Product;
import com.dc.dms.domain.model.User;
import com.dc.dms.model.ProductBuilder;
import com.dc.dms.model.UserBuilder;
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

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 1/2/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(properties = {"env = local"})
public class ProductResourceTest extends BaseTest{

    @Autowired
    private TestRestTemplate restTemplate = null;

    @Test
    public void getAllProducts() throws Exception {

        preparePreDatabaseCondition("test/sql/scenario/get_products.sql"  );

        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.getForEntity("/api/products/org/1000001", String.class);

        TypeReference<List<Product>>  trf = new TypeReference<List<Product>>() {};
        List<Product> products = mapper.readValue(response.getBody(), trf);

        assertEquals(2, products.size());


    }

    @Test
    public void getProduct() throws Exception {

        preparePreDatabaseCondition("test/sql/scenario/get_products.sql"  );

        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.getForEntity("/api/products/10", String.class);
        Product product = mapper.readValue(response.getBody(), Product.class);

        assertEquals("Sedan Car", product.getProductName());
    }

    @Test
    public void addOrUpdateProduct() throws Exception {

        preparePreDatabaseCondition("test/sql/scenario/add_product.sql"  );

        ObjectMapper mapper = new ObjectMapper();

        //prepare user object
        ProductBuilder builder = new ProductBuilder();

        Product productToBeCreated = builder.withOrgId(1000001)
                .withProductName("Sports Utility Vehicle")
                .withDescription("Sports Utility Vehicle")
                .withProductCode("SUV")
                .build();

        String jsonString = mapper.writeValueAsString(productToBeCreated);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //create HTTP request
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonString, requestHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/products/product", httpEntity, String.class);

        Product createdProduct = mapper.readValue(response.getBody(),Product.class);
        //verify against the database
        Map<String, Object> productData = this.executeValidationQuery("Select * from PRODUCT where PRODUCT_ID = " + createdProduct.getProductId());

        assertEquals(productData.get("PRODUCT_ID"),createdProduct.getProductId().longValue());

    }

}