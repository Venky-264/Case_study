package com.exceptions;

public class InvalidVictimDataException extends Exception{

	
	private static final long serialVersionUID = -1754839678541010121L;
	String message;
	
	public InvalidVictimDataException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
