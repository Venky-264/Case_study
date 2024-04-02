package com.exceptions;

public class IncidentNotFoundException extends Exception {

	
	
	private static final long serialVersionUID = 2363512216975797969L;
	
	String message;

	public IncidentNotFoundException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
