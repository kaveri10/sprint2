package com.cg.spring.exception;

public class InvalidUserDetails extends Exception{

	private String message;

	public InvalidUserDetails(String message) {
		super();
		this.message = message;
	}
	
	public InvalidUserDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidUserDetails [message=" + message + "]";
	}
	
}
