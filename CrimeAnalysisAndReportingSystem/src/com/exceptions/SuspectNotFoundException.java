package com.exceptions;

public class SuspectNotFoundException extends Exception {

	private static final long serialVersionUID = 2661868570315314359L;
	String message;
	public SuspectNotFoundException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
