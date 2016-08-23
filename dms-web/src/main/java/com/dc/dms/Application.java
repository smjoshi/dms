package com.dc.dms;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, WebMvcAutoConfiguration.class, ErrorMvcAutoConfiguration.class})
@ComponentScan
public class Application {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String args[]) throws Exception{
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		System.out.println("Inspecting Spring boot beans");

	    String[] beanNames = context.getBeanDefinitionNames();
	    Arrays.sort(beanNames);
	    for (String beanName : beanNames)
	    {
	            System.out.println(beanName);
	            logger.info(beanName);
	    }
	}
	
	

}
