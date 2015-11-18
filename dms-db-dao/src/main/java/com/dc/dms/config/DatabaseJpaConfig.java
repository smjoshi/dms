package com.dc.dms.config;


import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.dc.dms")
public class DatabaseJpaConfig {
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "devstart";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://dms-dev.cceizkyh70ud.us-west-2.rds.amazonaws.com:3306/dms";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "dmsdev";
         
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dc.dms";
	
	
	@Resource
	private Environment env;
	
	@Bean
	DataSource dataSource(){
		BasicDataSource dataSrc = new BasicDataSource();
        
		dataSrc.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
		dataSrc.setUrl(PROPERTY_NAME_DATABASE_URL);
		dataSrc.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
		dataSrc.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
        
        return dataSrc;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf){
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());
		return emf;
		
	}
	
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
        return properties;  
	}
	

	
	
	

}
