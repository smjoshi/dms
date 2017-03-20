package com.dc.dms.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * This is Database japa configuration for Test datasource
 *
 * Test datasource is H2.
 *
 * Created by sacjoshi on 10/28/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.dc.dms.dao.impl")
@Profile("test")
public class TestDatabaseJpaConfig {

    private  final String HIBERNATE_DIALECT = "hibernate.dialect";
    private  final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private  final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dc.dms.entity";


    @Bean
    @Scope("singleton")
    public DataSource dataSource(){

        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = dbBuilder
                                .setType(EmbeddedDatabaseType.H2)
                                .addScript("db/sql/create-db-h2.sql")
                                //.addScript("db/sql/insert-master-data")
                                .build();

        return db;

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
