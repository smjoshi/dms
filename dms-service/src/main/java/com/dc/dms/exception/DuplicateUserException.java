package com.dc.dms.exception;

public class DuplicateUserException extends Exception {
	
	private int errorCode = 102;
	
	public DuplicateUserException(){
		super("User Already Exists");
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	
	
	

}
