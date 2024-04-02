package com.exceptions;

public class InvalidReportDataException extends Exception{

	
	private static final long serialVersionUID = 312364485888155745L;
	String message;

	public InvalidReportDataException(String message) {
		
		this.message = message;
	}
	
	
	@Override
	public String getMessage() {
		return message;
	}
}
