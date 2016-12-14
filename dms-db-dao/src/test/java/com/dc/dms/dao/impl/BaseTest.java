package com.dc.dms.dao.impl;

import com.dc.dms.utils.test.AbstractTestSupport;
import org.junit.Before;

/**
 * Created by sacjoshi on 12/8/2016.
 */
public class BaseTest extends AbstractTestSupport {

    @Before
    public void runTest(){
        //initialize database
        dbHelper.truncateTables(getDataSource());
    }
}
