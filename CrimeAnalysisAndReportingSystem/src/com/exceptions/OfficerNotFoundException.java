package com.exceptions;

public class OfficerNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1124121850774059649L;
	String message;

	public OfficerNotFoundException(String message) {
		this.message=message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
