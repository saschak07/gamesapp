package com.gameapp.exception;

public class NoDetailsFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public NoDetailsFoundException(String message) {
		super();
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}
	
	
}
