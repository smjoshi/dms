package com.dc.dms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,  ErrorMvcAutoConfiguration.class, FlywayAutoConfiguration.class})
public class Application {


    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) throws Exception {
        logger.info("Application about to start ");

        ApplicationContext context = null;
        try {
            context = SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Inspecting Spring boot beans");

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    }


}
