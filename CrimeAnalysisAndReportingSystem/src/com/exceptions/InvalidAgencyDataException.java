package com.exceptions;

public class InvalidAgencyDataException extends Exception {

	
	
	private static final long serialVersionUID = 2867266269361568225L;
	
	String message;

	public InvalidAgencyDataException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
