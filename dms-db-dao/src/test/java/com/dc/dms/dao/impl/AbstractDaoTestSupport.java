package com.dc.dms.dao.impl;

import com.dc.dms.utils.TestDatabaseHelper;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;

/**
 * Created by sacjoshi on 11/23/2016.
 */
public abstract class AbstractDaoTestSupport implements ApplicationContextAware{

    private  ApplicationContext appContext = null;
    private TestDatabaseHelper dbHelper = TestDatabaseHelper.getInstantce();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    private DataSource getDataSource(){
        return appContext.getBean(DataSource.class);
    }

    public void preparePreDatabaseCondition(String... scriptFiles) throws Exception{
          for (String  file: scriptFiles){
                dbHelper.executeScriptFile(file, getDataSource());
          }
    }

    @Before
    public void runTest(){
        //initialize database
        dbHelper.truncateTables(getDataSource());
    }
}
