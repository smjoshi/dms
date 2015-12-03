package com.dc.dms.exception;

public class DMSException extends Exception {

	private int dmsErrorCode;
	private String errorMessage;

	public DMSException(){
		//Default constructor
	}
	
	public DMSException(int errorCode, String errorMessage){
		this.dmsErrorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getDmsErrorCode() {
		return dmsErrorCode;
	}

	public void setDmsErrorCode(int dmsErrorCode) {
		this.dmsErrorCode = dmsErrorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
