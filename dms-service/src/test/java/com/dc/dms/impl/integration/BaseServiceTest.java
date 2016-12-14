package com.dc.dms.impl.integration;

import com.dc.dms.utils.test.AbstractTestSupport;
import org.junit.Before;

/**
 * Created by sacjoshi on 12/8/2016.
 */
public class BaseServiceTest extends AbstractTestSupport {

    @Before
    public void runTest(){
        //initialize database
        dbHelper.truncateTables(getDataSource());
    }

}
