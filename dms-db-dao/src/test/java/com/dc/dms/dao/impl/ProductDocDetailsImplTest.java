package com.dc.dms.dao.impl;

import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocDetailDao;
import com.dc.dms.entity.ProductDocDetailEntity;
import com.dc.dms.entity.ProductEntity;
import com.dc.dms.utils.test.AbstractTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 11/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseJpaConfig.class)
public class ProductDocDetailsImplTest extends BaseTest {

    @Autowired
    private ProductDocDetailDao detailDao = null;

    @Test
    public void readByKey() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql",
                "test/sql/generic_create_product_doc_detail.sql");

        ProductDocDetailEntity detail = new ProductDocDetailEntity();
        detail.setProductDocDetailId(new BigInteger("80008"));

        ProductDocDetailEntity fetchedDetail = detailDao.readByKey(detail);

        assertEquals(80008, fetchedDetail.getProductDocDetailId().intValue());
        assertEquals(10, fetchedDetail.getProductId().intValue());
        assertEquals(300003, fetchedDetail.getProductDocConfId().intValue());
        assertEquals("http://devdmsproducts01.s3.amazonaws.com/dev/maruti-suzuki-swift-image-9925.jpg", fetchedDetail.getDocUrl());

    }

    @Test
    public void create() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql");

        ProductDocDetailEntity detail = new ProductDocDetailEntity();

        //set product attributes
        detail.setProductId(new BigInteger(("10")));
        detail.setProductDocConfId(new BigInteger("300003"));
        detail.setDocUrl("http://devdmsproducts01.s3.amazonaws.com/dev/maruti-suzuki-swift-image-9925.jpg");


        try {
            detailDao.create(detail);
            ProductDocDetailEntity fetchedDetail = detailDao.readByKey(detail);

            Assert.assertNotNull(fetchedDetail);

        } catch (DMSDaoException dde) {
            Assert.fail("User creation test fail : " + dde.getMessage());
        }

    }

    @Test
    public void update() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql",
                "test/sql/generic_create_product_doc_detail.sql");

        //read the product DOC configuration
        ProductDocDetailEntity detail = new ProductDocDetailEntity();
        detail.setProductDocDetailId(new BigInteger("80008"));

        ProductDocDetailEntity fetchedDetail = detailDao.readByKey(detail);

        //update attribute
        fetchedDetail.setDocUrl("http://devdmsproducts01.s3.amazonaws.com/dev/1157235016.jpg");
        detailDao.update(fetchedDetail);

        ProductDocDetailEntity updatedDetail = detailDao.readByKey(detail);

        Assert.assertTrue(updatedDetail.getDocUrl().equals("http://devdmsproducts01.s3.amazonaws.com/dev/1157235016.jpg"));

    }

    @Test
    public void getProductDocDetails() throws Exception {

        String PRODUCT_ID_UNDER_TEST = "10";
        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql",
                "test/sql/generic_create_product_doc_detail.sql");

        //get Product Doc configuration
        List<ProductDocDetailEntity> docList = detailDao.getProductDocDetails(new BigInteger(PRODUCT_ID_UNDER_TEST));

        Assert.assertEquals(docList.size(), 1);
    }

    @Test
    public void delete() throws Exception {

        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/generic_create_org.sql",
                "test/sql/generic_create_product.sql",
                "test/sql/generic_create_product_doc_conf.sql",
                "test/sql/generic_create_product_doc_detail.sql");

        //read prodyct by key
        ProductDocDetailEntity detail = new ProductDocDetailEntity();
        detail.setProductDocDetailId(new BigInteger("80008"));
        ProductDocDetailEntity fetchedDetail = detailDao.readByKey(detail);

        //delete product
        detailDao.delete(fetchedDetail);

        ProductDocDetailEntity deletedDetail = detailDao.readByKey(detail);
        Assert.assertNull(deletedDetail);
    }


    @Test
    public void getProductDocuments() throws Exception {

        int ORG_ID = 1000001;
        int PRODUCT_ID = 10;
        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/scenario/get_product_document_details.sql"  );
        ProductEntity selectedProduct = detailDao.getProductDocuments(ORG_ID,PRODUCT_ID);

        Assert.assertNotNull(selectedProduct);
    }

}