package com.dc.dms.web.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dc.dms.web.utils.RestUtils;

@Component
public class WebAppConfigurer {

	@Value("${restapi.url}")
	private String restApiURL = null;

	@PostConstruct
	public void initializeApp() {
		RestUtils.setApiRootContextURL(restApiURL);
	}
}
