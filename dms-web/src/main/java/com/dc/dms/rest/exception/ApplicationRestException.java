package com.dc.dms.rest.exception;

public class ApplicationRestException extends Exception {

	private int status;
	private int code;
	private String message;
	private String url;
	
	public ApplicationRestException() {
		// Default Constructor
	}
	
	public ApplicationRestException(int status, int code, String message, String url){
		this.status = status;
		this.code = code;
		this.message = message;
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

}
