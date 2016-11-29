package com.dc.dms.dao.impl;

import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocConfDao;
import com.dc.dms.entity.ProductDocConfEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sacjoshi on 11/23/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseJpaConfig.class)
public class ProductDocConfDaoImplTest extends AbstractDaoTestSupport {

    @Autowired
    private ProductDocConfDao confDao = null;

    @Test
    public void readByKey() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql");

        ProductDocConfEntity conf = new ProductDocConfEntity();
        conf.setProductDocConfId(new BigInteger("300003"));

        ProductDocConfEntity fetchedConf = confDao.readByKey(conf);

        assertEquals(300003, fetchedConf.getProductDocConfId().intValue());
        assertEquals(10, fetchedConf.getProductId().intValue());
        assertEquals("RIGHTIMAGE", fetchedConf.getDocTypeCode());
        assertEquals("Vehicle Right side Image", fetchedConf.getDescription());
        assertEquals(true, fetchedConf.isMandatory());
        assertEquals("0", fetchedConf.getGrouPId());
        assertEquals(false, fetchedConf.isMultipleItemAllowed());

    }

    @Test
    public void create() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql");

        ProductDocConfEntity conf = new ProductDocConfEntity();

        //set product attributes
        conf.setProductId(new BigInteger(("10")));
        conf.setDescription("Left Side Vehicle Image");
        conf.setDocTypeCode("LEFTIMAGE");
        conf.setGrouPId("0");
        conf.setMandatory(true);
        conf.setMultipleItemAllowed(false);


        try {
            confDao.create(conf);
            ProductDocConfEntity fetchedConf = confDao.readByKey(conf);

            Assert.assertNotNull(fetchedConf);

        } catch (DMSDaoException dde) {
            Assert.fail("User creation test fail : " + dde.getMessage());
        }
    }

    @Test
    public void update() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql");

        //read the product DOC configuration
        ProductDocConfEntity conf = new ProductDocConfEntity();
        conf.setProductDocConfId(new BigInteger("300003"));

        ProductDocConfEntity fetchedConf = confDao.readByKey(conf);

        //update attribute
        fetchedConf.setDescription("Updated Product Configuration");
        confDao.update(fetchedConf);

        ProductDocConfEntity updatedConf = confDao.readByKey(conf);

        Assert.assertTrue(updatedConf.getDescription().equals("Updated Product Configuration"));


    }

    @Test
    public void getProductDocConfigurations() throws Exception {

        String PRODUCT_ID_UNDER_TEST = "10";
        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql",
                "test/sql/add_product_doc_conf.sql");

        //get Product Doc configuration
        List<ProductDocConfEntity> confList = confDao.getProductDocConfigurations(new BigInteger(PRODUCT_ID_UNDER_TEST));

        Assert.assertEquals(confList.size(), 5);

    }

    @Test
    public void delete() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql");

        //read prodyct by key
        ProductDocConfEntity conf = new ProductDocConfEntity();
        conf.setProductDocConfId(new BigInteger("300003"));
        ProductDocConfEntity fetchedConf = confDao.readByKey(conf);

        //delete product
        confDao.delete(fetchedConf);

        ProductDocConfEntity deletedConf = confDao.readByKey(conf);
        Assert.assertNull(deletedConf);

    }

}