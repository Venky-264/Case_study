package com.exceptions;

public class InvalidEvidenceException extends Exception{

	
	private static final long serialVersionUID = -127181990150497512L;
	String message;

	public InvalidEvidenceException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
