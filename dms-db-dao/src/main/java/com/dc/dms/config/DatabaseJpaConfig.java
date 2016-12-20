package com.dc.dms.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.dc.dms.dao.impl")
@Profile("default")
public class DatabaseJpaConfig {
	
	@Value("${db.drivername}")
	private  String PROPERTY_NAME_DATABASE_DRIVER;
	
	@Value("${db.password}")
    private String PROPERTY_NAME_DATABASE_PASSWORD;
	
	@Value("${db.url}")
    private  String PROPERTY_NAME_DATABASE_URL;
	
	@Value("${db.username}")
    private  String PROPERTY_NAME_DATABASE_USERNAME;
    
	
    private  final String HIBERNATE_DIALECT = "hibernate.dialect";
    private  final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private  final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dc.dms.entity";
	
	
	@Autowired
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
		emf.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());
		return emf;
		
	}
	
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(HIBERNATE_SHOW_SQL, "true");
        return properties;  
	}
	

}
