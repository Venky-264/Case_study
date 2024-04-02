
package com.exceptions;

public class VictimNotFoundException extends Exception{

	private static final long serialVersionUID = 7906121247837060512L;
	String message;
	
	public VictimNotFoundException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}