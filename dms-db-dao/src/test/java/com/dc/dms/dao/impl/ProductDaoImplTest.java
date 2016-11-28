package com.dc.dms.dao.impl;

import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDao;
import com.dc.dms.entity.ProductEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by sacjoshi on 11/22/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseJpaConfig.class)
public class ProductDaoImplTest extends AbstractDaoTestSupport {

    @Autowired
    private ProductDao productDao = null;


    @Test
    public void readByKey() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql", "test/sql/generic_create_product.sql");

        ProductEntity product = new ProductEntity();
        product.setProductId(new BigInteger("10"));

        ProductEntity fetchedProduct = productDao.readByKey(product);

        assertEquals(10, fetchedProduct.getProductId().intValue());
        assertEquals("Sedan Car", fetchedProduct.getProductName());
        assertEquals("Sedan Car", fetchedProduct.getProductDesc());
        assertEquals("SEDAN_CAR_CODE", fetchedProduct.getProductCode());
        assertEquals(1000001, fetchedProduct.getOrgId().intValue());
    }

    @Test
    public void create() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql");

        ProductEntity product = new ProductEntity();

        //set product attributes
        product.setOrgId(new BigInteger("1000001"));
        product.setProductCode("product1");
        product.setProductDesc("Product Description");
        product.setProductName(" Display Product 1");

        try {
            productDao.create(product);
            ProductEntity fetchedPE = productDao.readByKey(product);

            Assert.assertNotNull(fetchedPE);

        } catch (DMSDaoException dde) {
            Assert.fail("User creation test fail : " + dde.getMessage());
        }

    }

    @Test
    public void update() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql", "test/sql/generic_create_product.sql");

        //read prodyct by key
        ProductEntity pe = new ProductEntity();
        pe.setProductId(new BigInteger("10"));
        ProductEntity fetchedPE = productDao.readByKey(pe);

        //update attribute
        fetchedPE.setProductName("Updated Product Name");
        productDao.update(fetchedPE);

        ProductEntity updatedPE = productDao.readByKey(pe);

        Assert.assertTrue(updatedPE.getProductName().equals("Updated Product Name"));
    }

    @Test
    public void getOrgProducts() throws Exception {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void delete() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql", "test/sql/generic_create_product.sql");

        //read prodyct by key
        ProductEntity pe = new ProductEntity();
        pe.setProductId(new BigInteger("10"));
        ProductEntity fetchedPE = productDao.readByKey(pe);

        //delete product
        productDao.delete(fetchedPE);

        ProductEntity deletedPE = productDao.readByKey(pe);

        Assert.assertNull(deletedPE);
    }

}