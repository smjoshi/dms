package com.dc.dms.rest.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.dc.dms.rest.resource.UserResource;

public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig(){
		packages("com.dc.dms.rest.resource");
		//register(UserResource.class);
	}

}
