package com.dc.dms.config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = { "com.dc.dms.web.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
//	@Bean (name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//	public DispatcherServlet dispatcherServlet(){
//		return new DispatcherServlet();
//	}
//
//	
//	@Bean
//	public ServletRegistrationBean dispatcherServletRegistration(){
//		ServletRegistrationBean dispatcherRegistration = new ServletRegistrationBean(this.dispatcherServlet(), "/web/*");
//		dispatcherRegistration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//		
//		return dispatcherRegistration;
//		
//	}
	
	
//	/**
//	 * @return
//	 */
//	@Bean 
//	ServletRegistrationBean jerseyServletRegistration(){
//		ServletRegistrationBean jerSeyRegistration = new ServletRegistrationBean(new ServletContainer(), "/api/*");
//		jerSeyRegistration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, RestApplicationConfig.class.getName());
//		
//		return jerSeyRegistration;
//	}
	
	

}
