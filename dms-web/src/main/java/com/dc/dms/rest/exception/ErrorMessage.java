package com.dc.dms.rest.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.BeanUtils;

public class ErrorMessage {

	@XmlElement(name="code")
	int code;
	
	@XmlElement(name="status")
	int status;
	
	@XmlElement(name="message")
	String message;
	
	@XmlElement(name="url")
	String url;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ErrorMessage(ApplicationRestException ex){
		BeanUtils.copyProperties(ex, this);
	}
	
	public ErrorMessage(NotFoundException nfe){
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.message = nfe.getMessage();
		
	}
	
	public ErrorMessage(){
		//Default constructor
	}
}
