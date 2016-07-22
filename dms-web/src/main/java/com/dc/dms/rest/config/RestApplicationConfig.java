package com.dc.dms.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
@ApplicationPath("/api")
public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig(){
		packages("com.dc.dms.rest.resource");
	}

}
