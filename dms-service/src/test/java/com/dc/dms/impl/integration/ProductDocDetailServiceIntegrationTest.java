package com.dc.dms.impl.integration;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.config.TestDatabaseJpaConfig;
import com.dc.dms.dao.intf.ProductDao;
import com.dc.dms.domain.model.ProductDocDetail;
import com.dc.dms.intf.ProductDocDetailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sacjoshi on 12/5/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, TestDatabaseJpaConfig.class })
public class ProductDocDetailServiceIntegrationTest extends BaseServiceTest{

    @Autowired
    ProductDocDetailService detailService = null;

    @Test
    public void getProductDocuments() throws Exception {

        //create a pre data condition in database
        int ORG_ID = 1000001;
        int PRODUCT_ID = 10;
        //prepare pre database condition for test
        preparePreDatabaseCondition("test/sql/scenario/get_product_document_details.sql"  );

        List<ProductDocDetail> details = detailService.getProductDocuments(ORG_ID, PRODUCT_ID);
        Assert.assertNotNull(details);
        Assert.assertTrue(details.size() > 0);
    }
}

