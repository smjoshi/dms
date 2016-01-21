package com.dc.dms.rest.config;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplicationConfig extends ResourceConfig {
	
	public RestApplicationConfig(){
		packages("com.dc.dms.rest.resource");
	}

}
