package com.tavant.springboot.exception;

public class InvalidLocationIdException extends Exception {

	public InvalidLocationIdException(String msg) {
		super(msg);
	}
	 
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
}
