package com.cg.spring.exception;

public class InvalidDataException extends Exception{

	private String message;

	public InvalidDataException() {
		super();
	}

	public InvalidDataException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidDataException [message=" + message + "]";
	}

}
