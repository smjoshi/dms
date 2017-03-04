package com.dc.dms.rest.config;

import javax.ws.rs.ApplicationPath;

import com.dc.dms.rest.resource.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
@ApplicationPath("/api")
public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig(){

	/* Due to Jersey limitation with Spring-boot , instead of scanning resources in classpath,
	   register these resources manually here and look for addressing this issue later
	 */
	  //packages("com.dc.dms.rest.resource");
		register(OrgnizationResource.class);
		register(ProductDocConfResource.class);
		register(ProductDocDetailResource.class);
		register(ProductResource.class);
		register(UserResource.class);

	}

}
