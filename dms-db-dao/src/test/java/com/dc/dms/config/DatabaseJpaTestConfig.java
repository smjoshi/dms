package com.dc.dms.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile("test")
public class DatabaseJpaTestConfig {

	private  final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private  final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private  final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dc.dms.entity";
	
	
	@Bean
	DataSource dataSource(){
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("")
				.addScript("")
				.build();
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
