package com.example.demo.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String statusCode;

	private String message;

	public ApplicationException(String message,String statusCode) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

}
