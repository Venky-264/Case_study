package com.exceptions;

public class AgencyNotFoundException extends Exception {

	
	private static final long serialVersionUID = 1064269144235989906L;
	String message;

	public AgencyNotFoundException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
