package com.exceptions;

public class InvalidIncidentDataException extends Exception {
	
	private static final long serialVersionUID = 1444159234683526076L;
	String message;
	
	public InvalidIncidentDataException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}