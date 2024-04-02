package com.exceptions;

public class InvalidOfficerDataException extends Exception {
	
	
	private static final long serialVersionUID = -765419204272670127L;
	String message;
	
	public InvalidOfficerDataException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
