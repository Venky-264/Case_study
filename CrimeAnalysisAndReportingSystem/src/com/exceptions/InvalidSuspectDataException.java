
package com.exceptions;

public class InvalidSuspectDataException extends Exception {

	private static final long serialVersionUID = 7825321804528693190L;
	String message;
	public InvalidSuspectDataException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
