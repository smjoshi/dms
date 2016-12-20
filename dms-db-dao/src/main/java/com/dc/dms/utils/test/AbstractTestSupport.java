package com.dc.dms.utils.test;

import com.dc.dms.utils.TestDatabaseHelper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by sacjoshi on 11/23/2016.
 */
public abstract class AbstractTestSupport implements ApplicationContextAware{

    protected  ApplicationContext appContext = null;
    protected TestDatabaseHelper dbHelper = TestDatabaseHelper.getInstantce();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    protected DataSource getDataSource(){
        return appContext.getBean(DataSource.class);
    }

    public void preparePreDatabaseCondition(String... scriptFiles) throws Exception{
          for (String  file: scriptFiles){
                dbHelper.executeScriptFile(file, getDataSource());
          }
    }

    public Map<String, Object> executeValidationQuery(String query) throws Exception{
        return dbHelper.excuteQuery(query, getDataSource());
    }


}
