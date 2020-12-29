package com.cg.spring.exception;

public class UserNotFoundException extends Exception{

	String message;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
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
		return "UserNotFoundException [message=" + message + "]";
	}
	
}
